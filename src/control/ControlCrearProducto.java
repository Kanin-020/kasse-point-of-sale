/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAO.DAOProducto;
import java.sql.*;
import modelo.Producto;
import vista.VistaCrearProducto;
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
 * @author l2001
 */
public class ControlCrearProducto implements ActionListener {

    private VistaCrearProducto vistaCrearProducto;
    private VistaVentanaInventarista vistaVentanaInventarista;

    public ControlCrearProducto(VistaCrearProducto vistaCrearProducto, VistaVentanaInventarista vistaVentanaInventarista) {
        this.vistaCrearProducto = vistaCrearProducto;
        this.vistaVentanaInventarista = vistaVentanaInventarista;

        this.vistaCrearProducto.getBotonAgregar().addActionListener(this);
        this.vistaCrearProducto.getBotonCancelar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {

        if (vistaCrearProducto.getBotonAgregar() == evento.getSource()) {
            try {
                Producto producto = new Producto(vistaCrearProducto.getFieldNombre().getText(),
                        vistaCrearProducto.getComboBoxCategoria().getSelectedItem().toString(),
                        0, Double.parseDouble(vistaCrearProducto.getFieldCosto().getText()),
                        Double.parseDouble(vistaCrearProducto.getFieldPrecioVenta().getText()));
                Inventarista inventarista = new Inventarista(producto);

                if (producto.getNombre().length() == 0 || producto.getCategoria() == "None" || producto.getCantidad() < 0 || producto.getCosto() < 0 || producto.getPrecio_venta() < 0) {
                    JOptionPane.showMessageDialog(null, "Campos vacios", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {

                    inventarista.agregar();
                    ConexionTabla();

                    vistaCrearProducto.dispose();

                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida", "Alerta", JOptionPane.WARNING_MESSAGE);

            }
        }
        if (vistaCrearProducto.getBotonCancelar() == evento.getSource()) {

            vistaCrearProducto.dispose();

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
