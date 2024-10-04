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
public class ControlCrearUsuario implements ActionListener {

    private VistaCrearUsuario vistaCrearUsuario;
    private VistaVentanaAdministrador vistaVentanaAdministrador;

    public ControlCrearUsuario(VistaCrearUsuario vistaCrearUsuario, VistaVentanaAdministrador vistaVentanaAdministrador) {
        this.vistaVentanaAdministrador = vistaVentanaAdministrador;
        this.vistaCrearUsuario = vistaCrearUsuario;

        this.vistaCrearUsuario.getBotonAgregar().addActionListener(this);
        this.vistaCrearUsuario.getBotonCancelar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {

        if (vistaCrearUsuario.getBotonAgregar() == evento.getSource()) {

            Administrador administrador = new Administrador(vistaCrearUsuario.getFieldUsuario().getText(),
                    vistaCrearUsuario.getFieldContraseña().getText(), vistaCrearUsuario.getComboBoxCargo().getSelectedItem().toString());

            if (administrador.getUsuario().length() == 0 || administrador.getContraseña().length() == 0 || administrador.getCargo() == "None") {
                JOptionPane.showMessageDialog(null, "Campos vacios", "Alerta", JOptionPane.WARNING_MESSAGE);
            } else {
                administrador.agregar();

                vistaCrearUsuario.dispose();

                ConexionTabla();

            }
        }

        if (vistaCrearUsuario.getBotonCancelar() == evento.getSource()) {

            vistaCrearUsuario.dispose();

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
