/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import java.sql.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.dao.ProductDAO;
import com.data.Product;
import com.utils.DatabaseConnection;
import com.view.VistaModificarProducto;
import com.view.VistaVentanaInventarista;

/**
 *
 * @author ed_le
 */
public class ControlModificarProducto implements ActionListener {

    private VistaModificarProducto vistaModificarProducto;
    private VistaVentanaInventarista vistaVentanaInventarista;
    private Product producto;

    public ControlModificarProducto(VistaModificarProducto vistaModificarProducto,
            VistaVentanaInventarista vistaVentanaInventarista, Product producto) {
        this.vistaModificarProducto = vistaModificarProducto;
        this.vistaVentanaInventarista = vistaVentanaInventarista;
        this.producto = producto;

        this.vistaModificarProducto.getBotonModificar().addActionListener(this);
        this.vistaModificarProducto.getBotonCancelar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {
        if (vistaModificarProducto.getBotonModificar() == evento.getSource()) {
            try {
                Product productoaux = new Product(vistaModificarProducto.getFieldNombre().getText(),
                        vistaModificarProducto.getComboBoxCategoria().getSelectedItem().toString(),
                        0, Double.parseDouble(vistaModificarProducto.getFieldCosto().getText()),
                        Double.parseDouble(vistaModificarProducto.getFieldPrecioVenta().getText()));
                if (productoaux.getName().length() == 0 || productoaux.getCategory() == "None"
                        || productoaux.getQuantity() < 0 || productoaux.getSupplierCost() < 0
                        || productoaux.getCostOfSale() < 0) {
                    JOptionPane.showMessageDialog(null, "Campos vacios", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    
                    ProductDAO.modify(producto);

                    ConexionTabla();

                    vistaModificarProducto.dispose();

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida", "Alerta", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (vistaModificarProducto.getBotonCancelar() == evento.getSource()) {

            vistaModificarProducto.dispose();

        }

    }

    public void ConexionTabla() {

        try {

            DefaultTableModel modelo = new DefaultTableModel();

            modelo = (DefaultTableModel) this.vistaVentanaInventarista.getTablaProductos().getModel();

            int no_filas;
            no_filas = modelo.getRowCount();
            for (int i = 1; i <= no_filas; i++) {
                modelo.removeRow(0);
            }

            PreparedStatement ps = null;
            ResultSet rs = null;

            Connection connection = DatabaseConnection.getInstance().getConnection();

            String orden = "SELECT codigo, nombre, categoria, cantidad, costo, precio_venta FROM productos";
            ps = connection.prepareStatement(orden);
            rs = ps.executeQuery();

            ResultSetMetaData metadata = rs.getMetaData();

            int no_Columnas = metadata.getColumnCount();

            while (rs.next()) {

                Object[] filas = new Object[no_Columnas];

                for (int i = 0; i < no_Columnas; i++) {

                    filas[i] = rs.getObject(i + 1);

                }

                modelo.addRow(filas);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }
}
