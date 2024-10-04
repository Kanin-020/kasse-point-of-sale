/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import DAO.DAOUsuario;

/**
 *
 * @author l2001
 */
public class Administrador extends Usuario {

    public Administrador(String usuario, String contraseña, String cargo) {
        super(usuario, contraseña, cargo);
    }

    public void agregar() {
        Administrador auxiliar = new Administrador(usuario, contraseña, cargo);
        DAOUsuario daoUsuario = new DAOUsuario();
        try {
            daoUsuario.agregar(auxiliar);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void eliminar() {
        String auxiliar = ("nombre = '" + usuario + "' AND contraseña = '" + contraseña + "' AND cargo = '" + cargo + "'");

        DAOUsuario daoUsuario = new DAOUsuario();
        try {
            daoUsuario.eliminar(auxiliar);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void inventario() {

    }

    public void ventas() {

    }

    public void salir() {

    }
}
