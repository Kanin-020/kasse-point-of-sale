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
        this.saleTable = (DefaultTableModel) sellerView.getTablaVentas().getModel();
        this.tableIndex = 1;

        addActionListeners();
    }

    private void addActionListeners() {
        sellerView.getBotonBuscar().addActionListener(this);
        sellerView.getBotonAgregar().addActionListener(this);
        sellerView.getBotonEliminar().addActionListener(this);
        sellerView.getBotonSalir().addActionListener(this);
        sellerView.getBotonSwitch().addActionListener(this);
        sellerView.getBotonListo().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == sellerView.getBotonBuscar())
            searchProduct();
        else if (source == sellerView.getBotonAgregar())
            addProduct();
        else if (source == sellerView.getBotonEliminar())
            deleteProduct();
        else if (source == sellerView.getBotonListo())
            closeSale();
        else if (source == sellerView.getBotonSalir())
            Logout();
        else if (source == sellerView.getBotonSwitch())
            changeToGeneralManagerWindow();
    }

    private void searchProduct() {
        int code = Integer.parseInt(sellerView.getFieldCodigo().getText());
        String name = sellerView.getFieldNombre().getText();

        if (name.isEmpty()) {
            showMessage("Campo de nombre vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Product product = new Product(code, name);
            ArrayList<Product> productList = ProductDAO.select(product);

            if (!productList.isEmpty()) {
                productFinded(productList.get(0));
            } else {
                showMessage("Producto no encontrado", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException exception) {
            showMessage("Entrada inválida", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException exception) {
            handleError(exception);
        }
    }

    private void addProduct() {
        try {

            int code = Integer.parseInt(sellerView.getFieldCodigo().getText().trim());
            String name = sellerView.getFieldNombre().getText().trim();
            int quantity = Integer.parseInt(sellerView.getFieldCantidad().getText().trim());

            if (name.isEmpty()) {
                showMessage("Campos vacíos", JOptionPane.WARNING_MESSAGE);
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
                showMessage("Producto no encontrado", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException exception) {
            showMessage("Entrada inválida", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException exception) {
            handleError(exception);
        }
    }

    private void deleteProduct() {

        int index = sellerView.getTablaVentas().getSelectedRow();

        if (index == -1) {
            showMessage("Debe seleccionar un producto", JOptionPane.WARNING_MESSAGE);
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
        if (confirmAction("¿Está seguro de efectuar la venta?")) {
            TicketView ticketView = new TicketView();
            new TicketController(ticketView, saleTable, sellerView.getLabelTotal().getText());
            ticketView.setVisible(true);
            cleanMenuData();
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

        sellerView.getLabelTotal().setText(String.valueOf(totalCost));
    }

    private void cleanMenuData() {
        sellerView.getFieldCodigo().setText("");
        sellerView.getFieldNombre().setText("");
        sellerView.getFieldCantidad().setText("");
        saleTable.setRowCount(0);
        updateTotalCost();
    }

    private void productFinded(Product product) {
        showMessage(
            "Nombre: " + product.getName() + " " +
            "Precio unitario: " + product.getCostOfSale() + " "+
            "Cantidad disponible: " + product.getQuantity(),
        JOptionPane.INFORMATION_MESSAGE);
        sellerView.getFieldCodigo().setText(String.valueOf(product.getCode()));
        sellerView.getFieldNombre().setText(product.getName());
    }

    private boolean confirmAction(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Confirmación",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void showMessage(String message, int type) {
        JOptionPane.showMessageDialog(null, message, "Alerta", type);
    }

    private void handleError(Exception exception) {
        showMessage(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
        exception.printStackTrace();
    }

}