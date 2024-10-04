/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author l2001
 */
public class Producto {

   private String nombre;
   private long codigo;
   private String categoria;
   private int cantidad;
   private double costo;
   private double precio_venta;

    public Producto(String nombre, String categoria, int cantidad, double costo, double precio_venta) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.costo = costo;
        this.precio_venta = precio_venta;
    }

    public Producto(long codigo, String nombre, String categoria, int cantidad, double costo, double precio_venta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.costo = costo;
        this.precio_venta = precio_venta;
    }
//Agregado

    public Producto(long codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Producto(long codigo, String nombre, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Producto() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

}
