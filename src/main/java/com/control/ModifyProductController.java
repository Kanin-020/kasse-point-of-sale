package com.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.dao.ProductDAO;
import com.data.Product;
import com.view.ModifyProductView;

public class ModifyProductController implements ActionListener {

    private ModifyProductView modifyProductView;
    private DefaultTableModel productTableModel;
    private Product product;

    public ModifyProductController(
            ModifyProductView modifyProductController,
            DefaultTableModel productTableModel,
            Product product) {

        this.modifyProductView = modifyProductController;
        this.productTableModel = productTableModel;
        this.product = product;

        fillForm();

        this.modifyProductView.getBotonModificar().addActionListener(this);
        this.modifyProductView.getBotonCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == modifyProductView.getBotonModificar()) {
            modifyProduct();
        } else if (source == modifyProductView.getBotonCancelar()) {
            cancel();
        }

    }

    private void modifyProduct() {

        try {

            String name = modifyProductView.getFieldNombre().getText().trim();
            String category = modifyProductView.getComboBoxCategoria().getSelectedItem().toString();
            int quantity = Integer.parseInt(modifyProductView.getFieldCantidad().getText().trim());
            double supplierCost = Double.parseDouble(modifyProductView.getFieldCosto().getText().trim());
            double costOfSale = Double.parseDouble(modifyProductView.getFieldPrecioVenta().getText().trim());

            Product modifiedProduct = new Product(name, category, quantity, supplierCost, costOfSale);

            ProductDAO.modify(product, modifiedProduct);

            modifyProductView.dispose();

            updateTable(product, modifiedProduct);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada invalida", "Alerta", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void cancel() {
        modifyProductView.dispose();
    }


    private void fillForm(){
        modifyProductView.getFieldNombre().setText(product.getName());
        modifyProductView.getComboBoxCategoria().setSelectedItem(product.getCategory());
        modifyProductView.getFieldCantidad().setText(String.valueOf(product.getQuantity()));
        modifyProductView.getFieldCosto().setText(String.valueOf(product.getSupplierCost()));
        modifyProductView.getFieldPrecioVenta().setText(String.valueOf(product.getCostOfSale()));
    }

    private void updateTable(Product product, Product modifiedProduct) {
   
        int rowCount = productTableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            
            if (productTableModel.getValueAt(i, 0).equals(product.getCode())) { 

                productTableModel.setValueAt(modifiedProduct.getName(), i, 1);
                productTableModel.setValueAt(modifiedProduct.getCategory(), i, 2);
                productTableModel.setValueAt(modifiedProduct.getQuantity(), i, 3);
                productTableModel.setValueAt(modifiedProduct.getSupplierCost(), i, 4);
                productTableModel.setValueAt(modifiedProduct.getCostOfSale(), i, 5);
                break;
            }
        }
    }

}
