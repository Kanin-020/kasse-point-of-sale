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
public class Vendedor {

    private Producto producto;

    public Vendedor(Producto producto) {
        this.producto = producto;
    }

    public void agregar() {

    }

    public void buscar() {

    }

    public void modificarCantidad(Producto productoaux) {
        Producto auxiliar = new Producto(producto.getCodigo(), producto.getNombre(),
                producto.getCategoria(), producto.getCantidad(), producto.getCosto(), producto.getPrecio_venta());
        String auxiliarString = ("codigo = '" + productoaux.getCodigo() + "' AND nombre = '" + productoaux.getNombre()
                + "' AND categoria = '" + productoaux.getCategoria() + "'");
        DAOProducto daoProducto = new DAOProducto();
        try {
            daoProducto.modificarCantidad(auxiliar, auxiliarString);
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
