/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import DAO.DAOUsuario;

/**
 *
 * @author l2001
 */
public class Usuario {

   protected String cargo;
   protected String usuario;
   protected String contraseña;

    public Usuario(String usuario, String contraseña, String cargo) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.cargo = cargo;
    }

    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.cargo = null;
    }

    public Usuario() {

    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Usuario iniciarSesion() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        Usuario usuarios = new Usuario();
        String condicion = ("nombre = '" + usuario + "' AND contraseña = '" + contraseña + "'");
        DAOUsuario daoUsuario = new DAOUsuario();
        try {
            lista = daoUsuario.consultar(condicion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (lista.size() == 0) {
            usuarios.setUsuario(null);
            usuarios.setContraseña(null);
            usuarios.setCargo(null);
        } else {
            usuarios = lista.get(0);
        }
        //System.out.println(lista.get(0).getCargo());
        return usuarios;
    }

}
