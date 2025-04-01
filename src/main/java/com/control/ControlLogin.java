/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import java.awt.event.*;
import javax.swing.JOptionPane;

import com.modelo.Usuario;
import com.vista.VistaLogin;
import com.vista.VistaVentanaAdministrador;
import com.vista.VistaVentanaInventarista;
import com.vista.VistaVentanaVendedor;


/**
 *
 * @author l2001
 */
public class ControlLogin implements ActionListener {

    private VistaLogin vistaLogin;

    public ControlLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;

        this.vistaLogin.getBotonIniciarSesion().addActionListener(this);

    }

    public void actionPerformed(ActionEvent evento) {

        if (vistaLogin.getBotonIniciarSesion() == evento.getSource()) {
            Usuario usuario = new Usuario(vistaLogin.getFieldUsuario().getText(), vistaLogin.getFieldContraseña().getText());
            usuario = usuario.login();

            if (usuario.getPosition() == null) {

                JOptionPane.showMessageDialog(null, "Usuario/Contraseña invalida", "Alerta", JOptionPane.WARNING_MESSAGE);

            } else {
                vistaLogin.setVisible(false);
                vistaLogin.dispose();

                switch (usuario.getPosition()) {
                    case "Administrador":

                        VistaVentanaAdministrador ventanaAdministrador = new VistaVentanaAdministrador();
                        ControlVentanaAdministrador controlAdministrador = new ControlVentanaAdministrador(ventanaAdministrador);
                        ventanaAdministrador.setVisible(true);

                        break;
                    case "Inventarista":

                        VistaVentanaInventarista ventanaInventarista = new VistaVentanaInventarista();
                        ControlVentanaInventarista controlInventarista = new ControlVentanaInventarista(ventanaInventarista);
                        ventanaInventarista.setVisible(true);
                        ventanaInventarista.getBotonSwitch().setEnabled(false);

                        break;
                    case "Vendedor":
                        VistaVentanaVendedor ventanaVendedor = new VistaVentanaVendedor();
                        ControlVentanaVendedor controlVendedor = new ControlVentanaVendedor(ventanaVendedor);
                        ventanaVendedor.setVisible(true);
                        ventanaVendedor.getBotonSwitch().setEnabled(false);
                        break;

                }

            }

        }

    }

}
