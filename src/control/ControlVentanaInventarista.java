/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.*;
import java.sql.*;
import vista.*;
import javax.swing.JOptionPane;
import modelo.Inventarista;
import modelo.Producto;
import javax.swing.table.DefaultTableModel;
import DAO.DAOProducto;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author l2001
 */
public class ControlVentanaInventarista implements ActionListener {

    private VistaVentanaInventarista vistaVentanaInventarista;

    public ControlVentanaInventarista(VistaVentanaInventarista vistaVentanaInventarista) {
        this.vistaVentanaInventarista = vistaVentanaInventarista;

        ConexionTabla();

        this.vistaVentanaInventarista.getBotonAgregar().addActionListener(this);
        this.vistaVentanaInventarista.getBotonAbastecer().addActionListener(this);
        this.vistaVentanaInventarista.getBotonEliminar().addActionListener(this);
        this.vistaVentanaInventarista.getBotonExcel().addActionListener(this);
        this.vistaVentanaInventarista.getBotonModificar().addActionListener(this);
        this.vistaVentanaInventarista.getBotonSalir().addActionListener(this);
        this.vistaVentanaInventarista.getBotonSwitch().addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {

        if (vistaVentanaInventarista.getBotonAgregar() == evento.getSource()) {
            VistaCrearProducto vistaCrearProducto = new VistaCrearProducto();

            ControlCrearProducto controlCrearProducto = new ControlCrearProducto(vistaCrearProducto, vistaVentanaInventarista);
            vistaCrearProducto.setVisible(true);
        }

        if (vistaVentanaInventarista.getBotonModificar() == evento.getSource()) {
            int indice;
            int respuesta;
            indice = vistaVentanaInventarista.getTablaProductos().getSelectedRow();

            try {
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un producto", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de modificar el producto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION) {

                        Producto producto = new Producto(Long.parseLong(vistaVentanaInventarista.getTablaProductos().getValueAt(indice, 0).toString()),
                                vistaVentanaInventarista.getTablaProductos().getValueAt(indice, 1).toString(),
                                vistaVentanaInventarista.getTablaProductos().getValueAt(indice, 2).toString(),
                                Integer.parseInt(vistaVentanaInventarista.getTablaProductos().getValueAt(indice, 3).toString()),
                                Double.parseDouble(vistaVentanaInventarista.getTablaProductos().getValueAt(indice, 4).toString()),
                                Double.parseDouble(vistaVentanaInventarista.getTablaProductos().getValueAt(indice, 5).toString()));
                        VistaModificarProducto vistaModificarProducto = new VistaModificarProducto();
                        ControlModificarProducto controlModificarProducto = new ControlModificarProducto(vistaModificarProducto, vistaVentanaInventarista, producto);
                        vistaModificarProducto.setVisible(true);
                        vistaModificarProducto.getFieldNombre().setText(producto.getNombre());
                        vistaModificarProducto.getComboBoxCategoria().setToolTipText(producto.getCategoria()); //escribir bien en combobox
                        indice = -1;
                        for (int i = 0; i < 8; i++) {
                            String auxiliar;
                            vistaModificarProducto.getComboBoxCategoria().setSelectedIndex(i);
                            auxiliar = vistaModificarProducto.getComboBoxCategoria().getSelectedItem().toString();
                            if (producto.getCategoria().equals(auxiliar) == true) {
                                indice = i;
                            }
                        }
                        vistaModificarProducto.getComboBoxCategoria().setSelectedIndex(indice);
                        vistaModificarProducto.getFieldCosto().setText(String.valueOf(producto.getCosto()));
                        vistaModificarProducto.getFieldPrecioVenta().setText(String.valueOf(producto.getPrecio_venta()));
                    }
                }
            } catch (Exception e) {

            }
        }

        if (vistaVentanaInventarista.getBotonEliminar() == evento.getSource()) {
            int indice;
            int respuesta;
            indice = vistaVentanaInventarista.getTablaProductos().getSelectedRow();
            try {
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un producto", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el producto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        Producto producto = new Producto(vistaVentanaInventarista.getTablaProductos().getValueAt(indice, 1).toString(),
                                vistaVentanaInventarista.getTablaProductos().getValueAt(indice, 2).toString(),
                                Integer.parseInt(vistaVentanaInventarista.getTablaProductos().getValueAt(indice, 3).toString()),
                                Double.parseDouble(vistaVentanaInventarista.getTablaProductos().getValueAt(indice, 4).toString()),
                                Double.parseDouble(vistaVentanaInventarista.getTablaProductos().getValueAt(indice, 5).toString()));
                        Inventarista inventarista = new Inventarista(producto);
                        inventarista.eliminar();
                        ConexionTabla();
                    }
                }
            } catch (Exception e) {

            }

        }

        if (vistaVentanaInventarista.getBotonAbastecer() == evento.getSource()) {
            VistaAbastecerProducto vistaAbastecerProducto = new VistaAbastecerProducto();
            ControlAbastecerProducto controlAbastecerProducto = new ControlAbastecerProducto(vistaAbastecerProducto, vistaVentanaInventarista);
            vistaAbastecerProducto.setVisible(true);
            vistaAbastecerProducto.getBotonAbastecer().setEnabled(false);
        }

        if (vistaVentanaInventarista.getBotonExcel() == evento.getSource()) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Quiere generar un documento de Excel?", "Generar Excel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {

                crearExcel();
                //SQLExcel();
                JOptionPane.showMessageDialog(null, "Documento generado", "Documento generado", JOptionPane.INFORMATION_MESSAGE);

            } else {
                //Se cierra xd
            }

        }

        if (vistaVentanaInventarista.getBotonSalir() == evento.getSource()) {
            int respuesta;
            respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de cerrar sesión?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                vistaVentanaInventarista.setVisible(false);
                vistaVentanaInventarista.dispose();
                VistaLogin login = new VistaLogin();
                ControlLogin controllogin = new ControlLogin(login);
                login.setVisible(true);
            } else {
                //Se cierra xd
            }

        }

        if (vistaVentanaInventarista.getBotonSwitch() == evento.getSource()) {
            int respuesta;
            respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de regresar a la ventana Administrador?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                vistaVentanaInventarista.setVisible(false);
                vistaVentanaInventarista.dispose();
                VistaVentanaAdministrador ventanaAdministrador = new VistaVentanaAdministrador();
                ControlVentanaAdministrador controlAdministrador = new ControlVentanaAdministrador(ventanaAdministrador);
                ventanaAdministrador.setVisible(true);
            } else {
                //Se cierra xd
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

    public void crearExcel() {

        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Ventana 1");

        XSSFRow fila = sheet.createRow(0);
        fila.createCell(0).setCellValue("Codigo");
        fila.createCell(1).setCellValue("Nombre");
        fila.createCell(2).setCellValue("Categoria");
        fila.createCell(3).setCellValue("Cantidad");
        fila.createCell(4).setCellValue("Costo");
        fila.createCell(5).setCellValue("Precio/Venta");

        int noFilas = vistaVentanaInventarista.getTablaProductos().getRowCount();

        for (int i = 0; i < noFilas; i++) {

            // vistaVentanaInventarista.setRowSelectionInterval(i+1,noFilas);
            fila = sheet.createRow(i + 1);

            fila.createCell(0).setCellValue(Long.parseLong(vistaVentanaInventarista.getTablaProductos().getValueAt(i, 0).toString()));
            fila.createCell(1).setCellValue(vistaVentanaInventarista.getTablaProductos().getValueAt(i, 1).toString());
            fila.createCell(2).setCellValue(vistaVentanaInventarista.getTablaProductos().getValueAt(i, 2).toString());
            fila.createCell(3).setCellValue(Integer.parseInt(vistaVentanaInventarista.getTablaProductos().getValueAt(i, 3).toString()));
            fila.createCell(4).setCellValue(Double.parseDouble(vistaVentanaInventarista.getTablaProductos().getValueAt(i, 4).toString()));
            fila.createCell(5).setCellValue(Double.parseDouble(vistaVentanaInventarista.getTablaProductos().getValueAt(i, 5).toString()));
        }

        try {

            FileOutputStream fileout = new FileOutputStream("ReporteDeVenta.xlsx");
            book.write(fileout);
            fileout.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado", "Alerta", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(ControlVentanaInventarista.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Proceso cancelado", "Alerta", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(ControlVentanaInventarista.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
