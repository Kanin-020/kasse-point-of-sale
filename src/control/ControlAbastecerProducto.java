/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAO.DAOProducto;
import java.sql.*;
import modelo.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Inventarista;
import vista.VistaAbastecerProducto;
import vista.VistaVentanaInventarista;

/**
 *
 * @author ed_le
 */
public class ControlAbastecerProducto implements ActionListener {

    private VistaAbastecerProducto vistaAbastecerProducto;
    private VistaVentanaInventarista vistaVentanaInventarista;

    public ControlAbastecerProducto(VistaAbastecerProducto vistaAbastecerProducto, VistaVentanaInventarista vistaVentanaInventarista) {
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
                    JOptionPane.showMessageDialog(null, "Campo de cantidad vacio", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {

                    Producto producto = new Producto(Long.parseLong(vistaAbastecerProducto.getFieldCodigo().getText()),
                            vistaAbastecerProducto.getFieldNombre().getText(), Integer.parseInt(vistaAbastecerProducto.getFieldCantidad().getText()));
                    Inventarista inventarista = new Inventarista(producto);
                    inventarista.abastecer();
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
            ArrayList<Producto> lista = new ArrayList<Producto>();
            try {
                if ((vistaAbastecerProducto.getFieldCodigo().getText().length() == 0) && (vistaAbastecerProducto.getFieldNombre().getText().length() == 0)) {
                    JOptionPane.showMessageDialog(null, "Campos vacios", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    long aux;
                    if (vistaAbastecerProducto.getFieldCodigo().getText().length() == 0) {
                        aux = 0;
                    } else {
                        aux = Long.parseLong(vistaAbastecerProducto.getFieldCodigo().getText());
                    }
                    Producto producto = new Producto(aux, vistaAbastecerProducto.getFieldNombre().getText());
                    Inventarista inventarista = new Inventarista(producto);
                    lista = inventarista.consultar();
                    if (lista.isEmpty() == false) {
                        JOptionPane.showMessageDialog(null, "Producto encontrado", "Proceso exitoso", JOptionPane.INFORMATION_MESSAGE);
                        vistaAbastecerProducto.getFieldCodigo().setText(String.valueOf(lista.get(0).getCodigo()));
                        vistaAbastecerProducto.getFieldNombre().setText(lista.get(0).getNombre());
                        vistaAbastecerProducto.getFieldCodigo().setEnabled(false);
                        vistaAbastecerProducto.getFieldNombre().setEnabled(false);
                        vistaAbastecerProducto.getBotonAbastecer().setEnabled(true);
                        vistaAbastecerProducto.getBotonComprobar().setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado", "Alerta", JOptionPane.WARNING_MESSAGE);
                        vistaAbastecerProducto.getBotonAbastecer().setEnabled(false);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida", "Alerta", JOptionPane.WARNING_MESSAGE);
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
