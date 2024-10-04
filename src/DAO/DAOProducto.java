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
import modelo.Producto;

/**
 *
 * @author l2001
 */
public class DAOProducto extends DAOSQL<Producto> {

    public DAOProducto() {
    }

    public int agregar(Producto e) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "INSERT INTO productos(nombre, categoria, cantidad,costo, precio_venta)"
                + "VALUES ('" + e.getNombre() + "','" + e.getCategoria() + "','" + e.getCantidad() + "','" + e.getCosto() + "','" + e.getPrecio_venta() + "')";

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    public int eliminar(String condicion) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "DELETE FROM productos WHERE " + condicion;

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    public int modificar(Producto e, String condicion) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "UPDATE productos SET "
                + "nombre = '" + e.getNombre() + "', "
                + "categoria = '" + e.getCategoria() + "',"
                + "costo = '" + e.getCosto() + "',"
                + " precio_venta = '" + e.getPrecio_venta()
                + "' WHERE " + condicion;
        
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    public int modificarCantidad(Producto e, String condicion) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "UPDATE productos SET "
                + "nombre = '" + e.getNombre() + "', "
                + "categoria = '" + e.getCategoria() + "',"
                + "cantidad = '" + e.getCantidad() + "',"
                + "costo = '" + e.getCosto() + "',"
                + " precio_venta = '" + e.getPrecio_venta()
                + "' WHERE " + condicion;
        
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    public ArrayList<Producto> consultar(String condicion) throws SQLException {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        Producto e;
        Connection con = getConeccion();
        String orden = "SELECT * FROM productos "
                + (condicion == null || condicion.length() == 0 ? "" : "WHERE " + condicion);
        Statement sentencia = con.createStatement();
        
        ResultSet rs = sentencia.executeQuery(orden);
        while (rs.next()) {
            e = new Producto(rs.getLong("codigo"), rs.getString("nombre"), rs.getString("categoria"), rs.getInt("cantidad"), rs.getDouble("costo"), rs.getDouble("precio_venta"));
            lista.add(e);
        }
        sentencia.close();
        cerrarConeccion(con);
        return lista;
    }

}
