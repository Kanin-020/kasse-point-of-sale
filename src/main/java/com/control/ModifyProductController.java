package com.control;

import static com.utils.Constants.*;

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

        this.modifyProductView.getModifyButton().addActionListener(this);
        this.modifyProductView.getCancelButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == modifyProductView.getModifyButton()) {
            modifyProduct();
        } else if (source == modifyProductView.getCancelButton()) {
            cancel();
        }

    }

    private void modifyProduct() {

        try {

            String name = modifyProductView.getNameField().getText().trim();
            String category = modifyProductView.getCategoryComboBox().getSelectedItem().toString();
            int quantity = Integer.parseInt(modifyProductView.getQuantityField().getText().trim());
            double supplierCost = Double.parseDouble(modifyProductView.getSupplierCostField().getText().trim());
            double costOfSale = Double.parseDouble(modifyProductView.getCostOfSaleField().getText().trim());

            Product modifiedProduct = new Product(name, category, quantity, supplierCost, costOfSale);

            ProductDAO.modify(product, modifiedProduct);

            modifyProductView.dispose();

            updateTable(product, modifiedProduct);

        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Entrada invalida", "Alerta", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    private void cancel() {
        modifyProductView.dispose();
    }

    private void fillForm() {
        modifyProductView.getNameField().setText(product.getName());
        modifyProductView.getCategoryComboBox().setSelectedItem(product.getCategory());
        modifyProductView.getQuantityField().setText(String.valueOf(product.getQuantity()));
        modifyProductView.getSupplierCostField().setText(String.valueOf(product.getSupplierCost()));
        modifyProductView.getCostOfSaleField().setText(String.valueOf(product.getCostOfSale()));
    }

    private void updateTable(Product product, Product modifiedProduct) {

        int rowCount = productTableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {

            if (productTableModel.getValueAt(i, INVENTORY_CODE_INDEX).equals(product.getCode())) {

                productTableModel.setValueAt(modifiedProduct.getName(), i, INVENTORY_NAME_INDEX);
                productTableModel.setValueAt(modifiedProduct.getCategory(), i, INVENTORY_CATEGORY_INDEX);
                productTableModel.setValueAt(modifiedProduct.getQuantity(), i, INVENTORY_QUANTITY_INDEX);
                productTableModel.setValueAt(modifiedProduct.getSupplierCost(), i, INVENTORY_SUPPLIER_COST_INDEX);
                productTableModel.setValueAt(modifiedProduct.getCostOfSale(), i, INVENTORY_COST_OF_SALE_INDEX);

                break;
            }
        }
    }

}
