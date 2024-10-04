/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAO.DAOProducto;
import java.sql.*;
import modelo.Producto;
import vista.VistaModificarProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Inventarista;
import vista.VistaVentanaInventarista;

/**
 *
 * @author ed_le
 */
public class ControlModificarProducto implements ActionListener {

    private VistaModificarProducto vistaModificarProducto;
    private VistaVentanaInventarista vistaVentanaInventarista;
    private Producto producto;

    public ControlModificarProducto(VistaModificarProducto vistaModificarProducto, VistaVentanaInventarista vistaVentanaInventarista, Producto producto) {
        this.vistaModificarProducto = vistaModificarProducto;
        this.vistaVentanaInventarista = vistaVentanaInventarista;
        this.producto = producto;

        this.vistaModificarProducto.getBotonModificar().addActionListener(this);
        this.vistaModificarProducto.getBotonCancelar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {
        if (vistaModificarProducto.getBotonModificar() == evento.getSource()) {
            try {
                Producto productoaux = new Producto(vistaModificarProducto.getFieldNombre().getText(),
                        vistaModificarProducto.getComboBoxCategoria().getSelectedItem().toString(),
                        0, Double.parseDouble(vistaModificarProducto.getFieldCosto().getText()),
                        Double.parseDouble(vistaModificarProducto.getFieldPrecioVenta().getText()));
                if (productoaux.getNombre().length() == 0 || productoaux.getCategoria() == "None" || productoaux.getCantidad() < 0 || productoaux.getCosto() < 0 || productoaux.getPrecio_venta() < 0) {
                    JOptionPane.showMessageDialog(null, "Campos vacios", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    Inventarista inventarista = new Inventarista(productoaux);
                    inventarista.modificar(producto); //modificar a modificar
                    ConexionTabla();

                    vistaModificarProducto.dispose();

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida", "Alerta", JOptionPane.WARNING_MESSAGE);
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

            DAOProducto enlace = new DAOProducto();
            Connection con = enlace.getConeccion();

            String orden = "SELECT codigo, nombre, categoria, cantidad, costo, precio_venta FROM productos";
            ps = con.prepareStatement(orden);
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
