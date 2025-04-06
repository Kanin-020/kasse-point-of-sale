package com.control;

import static com.utils.Constants.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.dao.ProductDAO;
import com.data.Product;
import com.view.GeneralManagerView;
import com.view.LoginView;
import com.view.TicketView;
import com.view.SellerView;

public class SellerController implements ActionListener {

    private SellerView sellerView;
    private DefaultTableModel saleTable;
    private int tableIndex;

    public SellerController(SellerView sellerView) {
        this.sellerView = sellerView;
        this.saleTable = (DefaultTableModel) sellerView.getSaleTable().getModel();
        this.tableIndex = 1;

        addActionListeners();
    }

    private void addActionListeners() {
        sellerView.getFindButton().addActionListener(this);
        sellerView.getAddButton().addActionListener(this);
        sellerView.getDeleteButton().addActionListener(this);
        sellerView.getLogoutButton().addActionListener(this);
        sellerView.getSwitchButton().addActionListener(this);
        sellerView.getDoneButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == sellerView.getFindButton())
            searchProduct();
        else if (source == sellerView.getAddButton())
            addProduct();
        else if (source == sellerView.getDeleteButton())
            deleteProduct();
        else if (source == sellerView.getDoneButton())
            closeSale();
        else if (source == sellerView.getLogoutButton())
            Logout();
        else if (source == sellerView.getSwitchButton())
            changeToGeneralManagerWindow();
    }

    private void searchProduct() {
        int code = Integer.parseInt(sellerView.getCodeField().getText());
        String name = sellerView.getNameField().getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo de nombre vacío", "Alerta", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Product product = new Product(code, name);
            ArrayList<Product> productList = ProductDAO.select(product);

            if (!productList.isEmpty()) {
                productFinded(productList.get(0));
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Entrada inválida", "Alerta", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addProduct() {
        try {

            int code = Integer.parseInt(sellerView.getCodeField().getText().trim());
            String name = sellerView.getNameField().getText().trim();
            int quantity = Integer.parseInt(sellerView.getQuantityField().getText().trim());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campos vacíos", "Alerta", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Product product = new Product(code, name);
            ArrayList<Product> productList = ProductDAO.select(product);

            if (!productList.isEmpty()) {

                if (isOnList(productList.get(0))) {
                    addQuantity(productList.get(0), quantity);
                } else {
                    addProductToTable(productList.get(0), quantity);
                }

                updateTotalCost();

            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Entrada inválida", "Alerta", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProduct() {

        int index = sellerView.getSaleTable().getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto", "Alerta", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int answer = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el producto?", "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION) {
            saleTable.removeRow(index);
            updateTotalCost();
            tableIndex--;
        }
    }

    private void closeSale() {
        
        if(saleTable.getRowCount() > 0){

            if (confirmAction("¿Está seguro de efectuar la venta?")) {
                TicketView ticketView = new TicketView();
                new TicketController(ticketView, saleTable, sellerView.getTotalLabel().getText());
                ticketView.setVisible(true);
                cleanMenuData();
            }

        } else{
            JOptionPane.showMessageDialog(null, "Lista vacía", "Alerta", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void Logout() {
        if (confirmAction("¿Está seguro de cerrar sesión?")) {
            sellerView.dispose();
            LoginView loginView = new LoginView();
            new LoginController(loginView);
            loginView.setVisible(true);
        }
    }

    private void changeToGeneralManagerWindow() {

        int answer = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de regresar a la ventana Administrador?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION) {
            sellerView.dispose();
            GeneralManagerView generalManager = new GeneralManagerView();
            new GeneralManagerController(generalManager);
            generalManager.setVisible(true);
        }
    }

    private void addProductToTable(Product product, int quantity) {

        Object[] row = {
                tableIndex++,
                product.getCode(),
                product.getName(),
                quantity,
                product.getCostOfSale(),
                quantity * product.getCostOfSale()
        };

        saleTable.addRow(row);
        updateTotalCost();
    }

    private void addQuantity(Product product, int quantity) {

        for (int i = 0; i < saleTable.getRowCount(); i++) {
            if (product.getCode() == Integer.parseInt(saleTable.getValueAt(i, SALE_CODE_INDEX).toString())) {

                int currentQuantity = (Integer) saleTable.getValueAt(i, SALE_QUANTITY_INDEX);
                double currentTotal = (Double) saleTable.getValueAt(i, SALE_TOTAL_INDEX);

                currentTotal += (product.getCostOfSale() * quantity);

                saleTable.setValueAt(currentQuantity += quantity, i, SALE_QUANTITY_INDEX);
                saleTable.setValueAt(currentTotal, i, SALE_TOTAL_INDEX);
            }
        }

    }

    private boolean isOnList(Product product) {
        boolean isOnList = false;

        for (int i = 0; i < saleTable.getRowCount(); i++) {
            if (product.getCode() == Integer.parseInt(saleTable.getValueAt(i, SALE_CODE_INDEX).toString())) {
                isOnList = true;
            }
        }

        return isOnList;
    }

    private void updateTotalCost() {

        double totalCost = 0;

        for (int i = 0; i < saleTable.getRowCount(); i++) {
            totalCost += (double) saleTable.getValueAt(i, SALE_TOTAL_INDEX);
        }

        sellerView.getTotalLabel().setText(String.valueOf(totalCost));
    }

    private void cleanMenuData() {
        sellerView.getCodeField().setText("");
        sellerView.getNameField().setText("");
        sellerView.getQuantityField().setText("");
        saleTable.setRowCount(0);
        updateTotalCost();
    }

    private void productFinded(Product product) {
        String output = "Nombre: " + product.getName() + " " +
                "Precio unitario: " + product.getCostOfSale() + " " +
                "Cantidad disponible: " + product.getQuantity();

        JOptionPane.showMessageDialog(null, output, "Mensaje", JOptionPane.INFORMATION_MESSAGE);

        sellerView.getCodeField().setText(String.valueOf(product.getCode()));
        sellerView.getNameField().setText(product.getName());
    }

    private boolean confirmAction(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Confirmación",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

}