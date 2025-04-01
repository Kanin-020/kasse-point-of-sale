/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.dao.ProductDAO;
import com.data.Product;
import com.utils.DatabaseConnection;
import com.view.VistaAbastecerProducto;
import com.view.VistaVentanaInventarista;

/**
 *
 * @author ed_le
 */
public class ControlAbastecerProducto implements ActionListener {

    private VistaAbastecerProducto vistaAbastecerProducto;
    private VistaVentanaInventarista vistaVentanaInventarista;

    public ControlAbastecerProducto(VistaAbastecerProducto vistaAbastecerProducto,
            VistaVentanaInventarista vistaVentanaInventarista) {
        this.vistaAbastecerProducto = vistaAbastecerProducto;
        this.vistaVentanaInventarista = vistaVentanaInventarista;

        this.vistaAbastecerProducto.getBotonAbastecer().addActionListener(this);
        this.vistaAbastecerProducto.getBotonCancelar().addActionListener(this);
        this.vistaAbastecerProducto.getBotonComprobar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {

        if (vistaAbastecerProducto.getBotonAbastecer() == evento.getSource()) {
            try {
                if (vistaAbastecerProducto.getFieldCantidad().getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Campo de cantidad vacio", "Alerta",
                            JOptionPane.WARNING_MESSAGE);
                } else {

                    Product producto = new Product(
                            Integer.parseInt(vistaAbastecerProducto.getFieldCodigo().getText()),
                            vistaAbastecerProducto.getFieldNombre().getText(),
                            Integer.parseInt(vistaAbastecerProducto.getFieldCantidad().getText()));

                    //TODO abastecer
                   
                    ConexionTabla();
                    vistaAbastecerProducto.dispose();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (vistaAbastecerProducto.getBotonCancelar() == evento.getSource()) {
            vistaAbastecerProducto.dispose();
        }
        if (vistaAbastecerProducto.getBotonComprobar() == evento.getSource()) {
            ArrayList<Product> lista = new ArrayList<Product>();
            try {
                if ((vistaAbastecerProducto.getFieldCodigo().getText().length() == 0)
                        && (vistaAbastecerProducto.getFieldNombre().getText().length() == 0)) {
                    JOptionPane.showMessageDialog(null, "Campos vacios", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    int aux;
                    if (vistaAbastecerProducto.getFieldCodigo().getText().length() == 0) {
                        aux = 0;
                    } else {
                        aux = Integer.parseInt(vistaAbastecerProducto.getFieldCodigo().getText());
                    }
                    Product producto = new Product(aux, vistaAbastecerProducto.getFieldNombre().getText());
                    
                    lista = ProductDAO.select(producto);

                    if (lista.isEmpty() == false) {
                        JOptionPane.showMessageDialog(null, "Producto encontrado", "Proceso exitoso",
                                JOptionPane.INFORMATION_MESSAGE);
                        vistaAbastecerProducto.getFieldCodigo().setText(String.valueOf(lista.get(0).getCode()));
                        vistaAbastecerProducto.getFieldNombre().setText(lista.get(0).getName());
                        vistaAbastecerProducto.getFieldCodigo().setEnabled(false);
                        vistaAbastecerProducto.getFieldNombre().setEnabled(false);
                        vistaAbastecerProducto.getBotonAbastecer().setEnabled(true);
                        vistaAbastecerProducto.getBotonComprobar().setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado", "Alerta",
                                JOptionPane.WARNING_MESSAGE);
                        vistaAbastecerProducto.getBotonAbastecer().setEnabled(false);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida", "Alerta", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
