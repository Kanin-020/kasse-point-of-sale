package com.control;

import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.dao.ProductDAO;
import com.data.Product;
import com.view.AddProductView;

public class AddProductController implements ActionListener {

    private AddProductView addProductView;
    private DefaultTableModel productTable;

    public AddProductController(AddProductView addProductView, DefaultTableModel productTable) {
        this.addProductView = addProductView;
        this.productTable = productTable;

        this.addProductView.getAddButton().addActionListener(this);
        this.addProductView.getCancelButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == addProductView.getAddButton()) {
            addProduct();
        } else if (source == addProductView.getCancelButton()) {
            cancel();
        }

    }

    private void addProduct() {

        try {

            Random random = new Random();

            int code = 100000 + random.nextInt(900000);
            String name = addProductView.getNameField().getText().trim();
            String category = addProductView.getCategoryComboBox().getSelectedItem().toString();
            int quantity = Integer.parseInt(addProductView.getQuantityField().getText().trim());
            double supplierCost = Double.parseDouble(addProductView.getSupplierCostField().getText().trim());
            double costOfSale = Double.parseDouble(addProductView.getCostOfSaleField().getText().trim());

            if (name.isEmpty() || category.equals("None")) {

                JOptionPane.showMessageDialog(
                        addProductView,
                        "Los campos no pueden estar vacíos",
                        "Alerta",
                        JOptionPane.WARNING_MESSAGE);

                return;

            }

            Product product = new Product(code, name, category, quantity, supplierCost, costOfSale);

            ProductDAO.add(product);

            productTable.addRow(new Object[] {
                    product.getCode(),
                    product.getName(),
                    product.getCategory(),
                    product.getQuantity(),
                    product.getSupplierCost(),
                    product.getCostOfSale() });

            addProductView.dispose();

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(
                    addProductView,
                    "Error al agregar producto: " + exception.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cancel() {
        addProductView.dispose();
    }

}
