/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import com.DAO.DAOUsuario;

/**
 *
 * @author l2001
 */
public class Manager extends Usuario {

    public Manager(String usuario, String contraseña, String cargo) {
        super(usuario, contraseña, cargo);
    }

    public void agregar() {
        Manager auxiliar = new Manager(username, password, position);
        DAOUsuario daoUsuario = new DAOUsuario();
        try {
            daoUsuario.agregar(auxiliar);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void eliminar() {
        String auxiliar = ("nombre = '" + username + "' AND contraseña = '" + password + "' AND cargo = '" + position + "'");

        DAOUsuario daoUsuario = new DAOUsuario();
        try {
            daoUsuario.eliminar(auxiliar);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
