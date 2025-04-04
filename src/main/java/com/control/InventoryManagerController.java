package com.control;

import static com.utils.Constants.*;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dao.ProductDAO;
import com.data.Product;
import com.view.AddProductView;
import com.view.LoginView;
import com.view.ModifyProductView;
import com.view.GeneralManagerView;
import com.view.InventoryManagerView;

public class InventoryManagerController implements ActionListener {

    private InventoryManagerView inventoryManagerView;
    private DefaultTableModel productTable;

    public InventoryManagerController(InventoryManagerView inventoryManagerView) {
        this.inventoryManagerView = inventoryManagerView;
        this.productTable = (DefaultTableModel) inventoryManagerView.getTablaProductos().getModel();

        updateTable();

        addActionListeners();
    }

    private void addActionListeners() {
        inventoryManagerView.getBotonAgregar().addActionListener(this);
        inventoryManagerView.getBotonEliminar().addActionListener(this);
        inventoryManagerView.getBotonExcel().addActionListener(this);
        inventoryManagerView.getBotonModificar().addActionListener(this);
        inventoryManagerView.getBotonSalir().addActionListener(this);
        inventoryManagerView.getBotonSwitch().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == inventoryManagerView.getBotonAgregar()) {
            openAddProductView();
        } else if (source == inventoryManagerView.getBotonModificar()) {
            modifyProduct();
        } else if (source == inventoryManagerView.getBotonEliminar()) {
            deleteProduct();
        } else if (source == inventoryManagerView.getBotonExcel()) {
            handleExportExcel();
        } else if (source == inventoryManagerView.getBotonSalir()) {
            logout();
        } else if (source == inventoryManagerView.getBotonSwitch()) {
            changeToGeneralManagerWindow();
        }
    }

    private void openAddProductView() {
        AddProductView addProductView = new AddProductView();
        new AddProductController(addProductView, productTable);
        addProductView.setVisible(true);
    }

    private void modifyProduct() {

        int index = inventoryManagerView.getTablaProductos().getSelectedRow();

        if (index == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Debe seleccionar un producto",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int answer = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de modificar el producto?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION) {
            try {

                Product product = createProductFromTable(index);
                ModifyProductView modifyProductView = new ModifyProductView();
                new ModifyProductController(modifyProductView, productTable, product);
                modifyProductView.setVisible(true);

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error al modificar el producto: " + exception.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void deleteProduct() {
        int index = inventoryManagerView.getTablaProductos().getSelectedRow();
        if (index == -1) {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int answer = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el producto?", "Confirmación",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            Product product = createProductFromTable(index);
            try {
                ProductDAO.delete(product);
                updateTable();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void handleExportExcel() {
        int answer = JOptionPane.showConfirmDialog(null, "¿Quiere generar un documento de Excel?", "Generar Excel",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            exportExcel();
            JOptionPane.showMessageDialog(null, "Documento generado", "Documento generado",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void logout() {

        int answer = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de cerrar sesión?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
            inventoryManagerView.dispose();
            LoginView login = new LoginView();
            new LoginController(login);
            login.setVisible(true);
        }
    }

    private void changeToGeneralManagerWindow() {

        int answer = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de regresar a la ventana Administrador?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION) {
            inventoryManagerView.dispose();
            GeneralManagerView generalManager = new GeneralManagerView();
            new GeneralManagerController(generalManager);
            generalManager.setVisible(true);
        }

    }

    private Product createProductFromTable(int index) {
        return new Product(
                Integer.parseInt(productTable.getValueAt(index, INVENTORY_CODE_INDEX).toString()),
                productTable.getValueAt(index, INVENTORY_NAME_INDEX).toString(),
                productTable.getValueAt(index, INVENTORY_CATEGORY_INDEX).toString(),
                Integer.parseInt(productTable.getValueAt(index, INVENTORY_QUANTITY_INDEX).toString()),
                Double.parseDouble(productTable.getValueAt(index, INVENTORY_SUPPLIER_COST_INDEX).toString()),
                Double.parseDouble(productTable.getValueAt(index, INVENTORY_COST_OF_SALE_INDEX).toString()));
    }

    private void exportExcel() {

        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Ventana 1");

        String[] headers = {
                PRODUCT_CODE_HEADER,
                PRODUCT_NAME_HEADER,
                PRODUCT_CATEGORY_HEADER,
                PRODUCT_QUANTITY_HEADER,
                PRODUCT_SUPPLIER_COST_HEADER,
                PRODUCT_COST_OF_SALE_HEADER
        };

        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        int rowCount = inventoryManagerView.getTablaProductos().getRowCount();

        for (int i = 0; i < rowCount; i++) {
            XSSFRow row = sheet.createRow(i + 1);

            for (int j = 0; j < headers.length; j++) {
                Object cellValue = inventoryManagerView.getTablaProductos().getValueAt(i, j);
                if (cellValue != null) {
                    if (cellValue instanceof Long) {
                        row.createCell(j).setCellValue((Long) cellValue);
                    } else if (cellValue instanceof Integer) {
                        row.createCell(j).setCellValue((Integer) cellValue);
                    } else if (cellValue instanceof Double) {
                        row.createCell(j).setCellValue((Double) cellValue);
                    } else {
                        row.createCell(j).setCellValue(cellValue.toString());
                    }
                }
            }
        }

        try (FileOutputStream fileout = new FileOutputStream("ReporteDeVenta.xlsx")) {
            book.write(fileout);
        } catch (IOException exception) {
            String message = (exception instanceof FileNotFoundException) ? "Archivo no encontrado"
                    : "Proceso cancelado";
            JOptionPane.showMessageDialog(null, message, "Alerta", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(InventoryManagerController.class.getName()).log(Level.SEVERE, null, exception);
        } finally {
            try {
                book.close();
            } catch (IOException exception) {
                Logger.getLogger(InventoryManagerController.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
    }

    private void updateTable() {

        if (productTable == null) {
            JOptionPane.showMessageDialog(null, "Error: La tabla no está inicializada.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingWorker<Void, Void> worker = new SwingWorker<>() {

            @Override
            protected Void doInBackground() {
                try {

                    productTable.setRowCount(0);

                    ArrayList<Product> productList = ProductDAO.selectAll();
                    for (Product product : productList) {
                        Object[] rows = {
                                product.getCode(), product.getName(),
                                product.getCategory(), product.getQuantity(),
                                product.getSupplierCost(), product.getCostOfSale()
                        };
                        productTable.addRow(rows);
                    }

                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error al cargar la tabla de productos: " + exception.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        };

        worker.execute();

    }

}
