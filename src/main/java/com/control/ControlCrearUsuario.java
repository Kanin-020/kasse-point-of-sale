/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.dao.ProductDAO;
import com.dao.UserDAO;
import com.data.User;
import com.utils.DatabaseConnection;
import com.view.VistaCrearUsuario;
import com.view.VistaVentanaAdministrador;

/**
 *
 * @author l2001
 */
public class ControlCrearUsuario implements ActionListener {

    private VistaCrearUsuario vistaCrearUsuario;
    private VistaVentanaAdministrador vistaVentanaAdministrador;

    public ControlCrearUsuario(VistaCrearUsuario vistaCrearUsuario,
            VistaVentanaAdministrador vistaVentanaAdministrador) {
        this.vistaVentanaAdministrador = vistaVentanaAdministrador;
        this.vistaCrearUsuario = vistaCrearUsuario;

        this.vistaCrearUsuario.getBotonAgregar().addActionListener(this);
        this.vistaCrearUsuario.getBotonCancelar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {

        try {

            if (vistaCrearUsuario.getBotonAgregar() == evento.getSource()) {

                User user = new User(
                        vistaCrearUsuario.getFieldUsuario().getText(),
                        vistaCrearUsuario.getFieldContraseña().getText(),
                        vistaCrearUsuario.getComboBoxCargo().getSelectedItem().toString());

                if (user.getUsername().length() == 0 || user.getPassword().length() == 0
                        || user.getPosition() == "None") {
                    JOptionPane.showMessageDialog(null, "Campos vacios", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    UserDAO.add(user);
                    vistaCrearUsuario.dispose();

                    ConexionTabla();

                }
            }

            if (vistaCrearUsuario.getBotonCancelar() == evento.getSource()) {

                vistaCrearUsuario.dispose();

            }

        } catch (SQLException e) {
            e.printStackTrace();
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

            Connection connection = DatabaseConnection.getInstance().getConnection();

            String orden = "SELECT nombre, contraseña, cargo FROM usuario";
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

            JOptionPane.showMessageDialog(null, "Error", "Error al conectar con la tabla", JOptionPane.ERROR_MESSAGE);

        }

    }

}
