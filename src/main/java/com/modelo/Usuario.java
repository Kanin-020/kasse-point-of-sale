/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.util.ArrayList;

import com.DAO.DAOUsuario;

/**
 *
 * @author l2001
 */
public class Usuario {

    private String username;
    private String password;
    private String position;

    public Usuario(String username, String password, String position) {
        this.username = username;
        this.password = password;
        this.position = position;
    }

    public Usuario(String usuario, String contraseña) {
        this.username = usuario;
        this.password = contraseña;
        this.position = null;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String cargo) {
        this.position = cargo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usuario) {
        this.username = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contraseña) {
        this.password = contraseña;
    }

    public Usuario login() {
        Usuario user = null;
        DAOUsuario daoUsuario = new DAOUsuario();
    
        try {

            String query = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";
            ArrayList<Usuario> userList = daoUsuario.consultar(query);
            
            if (!userList.isEmpty()) {
                user = userList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return user;
    }
    

}
