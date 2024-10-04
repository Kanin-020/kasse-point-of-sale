/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import DAO.DAOProducto;
import java.util.ArrayList;

/**
 *
 * @author ed_le
 */
public class Inventarista {

    private Producto producto;

    public Inventarista(Producto producto) {
        this.producto = producto;
    }

    public void agregar() {
        Producto auxiliar = new Producto(producto.getNombre(), producto.getCategoria(),
                producto.getCantidad(), producto.getCosto(), producto.getPrecio_venta());
        DAOProducto daoProducto = new DAOProducto();
        try {
            daoProducto.agregar(auxiliar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificar(Producto productoaux) {
        Producto auxiliar = new Producto(producto.getCodigo(), producto.getNombre(),
                producto.getCategoria(), producto.getCantidad(), producto.getCosto(), producto.getPrecio_venta());
        String auxiliarString = ("codigo = '" + productoaux.getCodigo() + "' AND nombre = '" + productoaux.getNombre()
                + "' AND categoria = '" + productoaux.getCategoria() + "'");
        System.out.println(auxiliarString);
        DAOProducto daoProducto = new DAOProducto();
        try {
            daoProducto.modificar(auxiliar, auxiliarString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificarCantidad(Producto productoaux) {
        Producto auxiliar = new Producto(producto.getCodigo(), producto.getNombre(),
                producto.getCategoria(), producto.getCantidad(), producto.getCosto(), producto.getPrecio_venta());
        String auxiliarString = ("codigo = '" + productoaux.getCodigo() + "' AND nombre = '" + productoaux.getNombre()
                + "' AND categoria = '" + productoaux.getCategoria() + "'");
        System.out.println(auxiliarString);
        DAOProducto daoProducto = new DAOProducto();
        try {
            daoProducto.modificarCantidad(auxiliar, auxiliarString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() {
        String auxiliar = ("nombre = '" + producto.getNombre() + "' AND categoria = '"
                + producto.getCategoria() + "' AND cantidad = '" + producto.getCantidad()
                + "' AND costo = '" + producto.getCosto() + "' AND precio_venta = '" + producto.getPrecio_venta() + "'");

        DAOProducto daoProducto = new DAOProducto();
        try {
            daoProducto.eliminar(auxiliar);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Producto> consultar() {
        String auxiliar = ("codigo = '" + producto.getCodigo() + "' OR nombre = '" + producto.getNombre() + "'");

        ArrayList<Producto> lista = new ArrayList<Producto>();
        DAOProducto daoProducto = new DAOProducto();
        try {
            lista = daoProducto.consultar(auxiliar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void abastecer() {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        Producto auxiliar = new Producto(producto.getCodigo(), producto.getNombre(), producto.getCantidad());
        Inventarista inventarista = new Inventarista(auxiliar);
        lista = inventarista.consultar();
        int suma;
        Producto auxiliar1 = lista.get(0);
        suma = auxiliar.getCantidad() + lista.get(0).getCantidad();
        lista.get(0).setCantidad(suma);
        inventarista.setProducto(lista.get(0));
        inventarista.modificarCantidad(auxiliar1);

    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
