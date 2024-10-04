/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author lbojor
 */
public class DAOUsuario extends DAOSQL<Usuario> {

    public DAOUsuario() {
    }

    public int agregar(Usuario e) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "INSERT INTO usuario(nombre, contraseña, cargo)"
                + "VALUES ('" + e.getUsuario() + "','" + e.getContraseña() + "','" + e.getCargo() + "')";

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    public int eliminar(String condicion) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "DELETE FROM usuario WHERE " + condicion;

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    public ArrayList<Usuario> consultar(String condicion) throws SQLException {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        Usuario e;
        Connection con = getConeccion();
        String orden = "SELECT * FROM usuario "
                + (condicion == null || condicion.length() == 0 ? "" : "WHERE " + condicion);
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery(orden);
        while (rs.next()) {
            e = new Usuario(rs.getString("nombre"), rs.getString("contraseña"), rs.getString("cargo"));
            lista.add(e);
        }
        sentencia.close();
        cerrarConeccion(con);
        return lista;
    }

    @Override
    public int modificar(Usuario entidad, String condicion) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
