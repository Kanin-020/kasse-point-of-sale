/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.*;
import java.sql.*;
import vista.*;
import DAO.DAOUsuario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Administrador;

/**
 *
 * @author l2001
 */
public class ControlVentanaAdministrador implements ActionListener {

    private VistaVentanaAdministrador vistaVentanaAdministrador;

    public ControlVentanaAdministrador(VistaVentanaAdministrador vistaVentanaAdministrador) {

        this.vistaVentanaAdministrador = vistaVentanaAdministrador;
        ConexionTabla();

        this.vistaVentanaAdministrador.getBotonAgregar().addActionListener(this);
        this.vistaVentanaAdministrador.getBotonEliminar().addActionListener(this);
        this.vistaVentanaAdministrador.getBotonInventario().addActionListener(this);
        this.vistaVentanaAdministrador.getBotonVentas().addActionListener(this);
        this.vistaVentanaAdministrador.getBotonSalir().addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {

        if (vistaVentanaAdministrador.getBotonAgregar() == evento.getSource()) {

            VistaCrearUsuario vistaCrearUsuario = new VistaCrearUsuario();

            ControlCrearUsuario controlCrearUsuario = new ControlCrearUsuario(vistaCrearUsuario, vistaVentanaAdministrador);
            vistaCrearUsuario.setVisible(true);

        }

        if (vistaVentanaAdministrador.getBotonEliminar() == evento.getSource()) {
            int indice;
            int respuesta;
            indice = vistaVentanaAdministrador.getTablaUsuarios().getSelectedRow();
            try {
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el usuario?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        Administrador administrador = new Administrador(vistaVentanaAdministrador.getTablaUsuarios().getValueAt(indice, 0).toString(),
                                vistaVentanaAdministrador.getTablaUsuarios().getValueAt(indice, 1).toString(),
                                vistaVentanaAdministrador.getTablaUsuarios().getValueAt(indice, 2).toString());
                        administrador.eliminar();
                        ConexionTabla();
                    }
                }
            } catch (Exception e) {

            }

        }

        if (vistaVentanaAdministrador.getBotonInventario() == evento.getSource()) {
            int respuesta;
            respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de cambiar a modo Inventarista?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                vistaVentanaAdministrador.setVisible(false);
                vistaVentanaAdministrador.dispose();
                VistaVentanaInventarista ventanaInventarista = new VistaVentanaInventarista();
                ControlVentanaInventarista controlInventarista = new ControlVentanaInventarista(ventanaInventarista);
                ventanaInventarista.setVisible(true);
            } else {
                //Se cierra xd
            }

        }

        if (vistaVentanaAdministrador.getBotonVentas() == evento.getSource()) {
            int respuesta;
            respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de cambiar a modo Ventas?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                vistaVentanaAdministrador.setVisible(false);
                vistaVentanaAdministrador.dispose();
                VistaVentanaVendedor ventanaVendedor = new VistaVentanaVendedor();
                ControlVentanaVendedor controlVendedor = new ControlVentanaVendedor(ventanaVendedor);
                ventanaVendedor.setVisible(true);
            } else {
                //Se cierra xd
            }

        }

        if (vistaVentanaAdministrador.getBotonSalir() == evento.getSource()) {

            int respuesta;
            respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de cerrar sesión?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                vistaVentanaAdministrador.setVisible(false);
                vistaVentanaAdministrador.dispose();
                VistaLogin login = new VistaLogin();
                ControlLogin controllogin = new ControlLogin(login);
                login.setVisible(true);
            } else {
                //Se cierra xd
            }

        }

    }

    public void ConexionTabla() {

        try {

            DefaultTableModel modelo = new DefaultTableModel();

            modelo = (DefaultTableModel) this.vistaVentanaAdministrador.getTablaUsuarios().getModel();

            int no_filas;
            no_filas = modelo.getRowCount();
            for (int i = 1; i <= no_filas; i++) {
                modelo.removeRow(0);
            }

            PreparedStatement ps = null;
            ResultSet rs = null;

            DAOUsuario enlace = new DAOUsuario();
            Connection con = enlace.getConeccion();

            String orden = "SELECT nombre, contraseña, cargo FROM usuario";
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
