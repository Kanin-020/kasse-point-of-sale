/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.*;
import java.util.ArrayList;
import vista.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Vendedor;
import modelo.Producto;

/**
 *
 * @author l2001
 */
public class ControlVentanaVendedor implements ActionListener {

    private VistaVentanaVendedor vistaVentanaVendedor;
    private int contador;
    private double total;

    public ControlVentanaVendedor(VistaVentanaVendedor vistaVentanaVendedor) {
        this.vistaVentanaVendedor = vistaVentanaVendedor;
        this.contador = 0;
        this.total = 0;

        this.vistaVentanaVendedor.getBotonBuscar().addActionListener(this);
        this.vistaVentanaVendedor.getBotonAgregar().addActionListener(this);
        this.vistaVentanaVendedor.getBotonEliminar().addActionListener(this);
        this.vistaVentanaVendedor.getBotonSalir().addActionListener(this);
        this.vistaVentanaVendedor.getBotonSwitch().addActionListener(this);
        this.vistaVentanaVendedor.getBotonListo().addActionListener(this);

    }

    public void actionPerformed(ActionEvent evento) {

        if (vistaVentanaVendedor.getBotonBuscar() == evento.getSource()) {

            ArrayList<Producto> lista = new ArrayList<Producto>();
            try {
                if (vistaVentanaVendedor.getFieldCodigo().getText().length() == 0 && vistaVentanaVendedor.getFieldNombre().getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Campo de cantidad vacio", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    long aux;
                    if (vistaVentanaVendedor.getFieldCodigo().getText().length() == 0) {
                        aux = 0;
                    } else {
                        aux = Long.parseLong(vistaVentanaVendedor.getFieldCodigo().getText());
                    }
                    Producto producto = new Producto(aux, vistaVentanaVendedor.getFieldNombre().getText());
                    Vendedor vendedor = new Vendedor(producto);
                    lista = vendedor.consultar();
                    if (lista.isEmpty() == false) {
                        JOptionPane.showMessageDialog(null, "Producto encontrado", "Proceso exitoso", JOptionPane.INFORMATION_MESSAGE);
                        vistaVentanaVendedor.getFieldCodigo().setText(String.valueOf(lista.get(0).getCodigo()));
                        vistaVentanaVendedor.getFieldNombre().setText(lista.get(0).getNombre());
                        vistaVentanaVendedor.getFieldCodigo().setEnabled(false);
                        vistaVentanaVendedor.getFieldNombre().setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (vistaVentanaVendedor.getBotonAgregar() == evento.getSource()) {
            ArrayList<Producto> lista = new ArrayList<Producto>();
            try {
                if ((vistaVentanaVendedor.getFieldCodigo().getText().length() == 0
                        && vistaVentanaVendedor.getFieldNombre().getText().length() == 0)
                        || vistaVentanaVendedor.getFieldCantidad().getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Campos vacios", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    long aux;
                    int cantidad, auxiliar;
                    if (vistaVentanaVendedor.getFieldCodigo().getText().length() == 0) {
                        aux = 0;
                    } else {
                        aux = Long.parseLong(vistaVentanaVendedor.getFieldCodigo().getText());
                    }
                    Producto producto = new Producto(aux, vistaVentanaVendedor.getFieldNombre().getText());
                    Vendedor vendedor = new Vendedor(producto);

                    cantidad = Integer.parseInt(vistaVentanaVendedor.getFieldCantidad().getText());
                    lista = vendedor.consultar();
                    if (lista.isEmpty() == false) {
                        JOptionPane.showMessageDialog(null, "Producto encontrado", "Proceso exitoso", JOptionPane.INFORMATION_MESSAGE);
                        producto = lista.get(0);

                        auxiliar = producto.getCantidad();
                        producto.setCantidad(auxiliar - cantidad);
                        vendedor.setProducto(producto);
                        vendedor.modificarCantidad(lista.get(0));
                        lista.get(0).setCantidad(auxiliar);
                        contador++;
                        agregarFila(lista, cantidad);
                        vistaVentanaVendedor.getFieldCodigo().setEnabled(true);
                        vistaVentanaVendedor.getFieldNombre().setEnabled(true);
                        vistaVentanaVendedor.getFieldCodigo().setText(null);
                        vistaVentanaVendedor.getFieldNombre().setText(null);
                        vistaVentanaVendedor.getFieldCantidad().setText(null);
                        modificarTotal(-1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida", "Alerta", JOptionPane.WARNING_MESSAGE);
            }

        }

        if (vistaVentanaVendedor.getBotonEliminar() == evento.getSource()) {
            ArrayList<Producto> lista = new ArrayList<Producto>();
            int indice;
            int respuesta;
            int auxiliar;
            int cantidad;
            indice = vistaVentanaVendedor.getTablaVentas().getSelectedRow();
            try {
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un producto", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el producto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        Producto producto = new Producto(Long.parseLong(vistaVentanaVendedor.getTablaVentas().getValueAt(indice, 1).toString()),
                                vistaVentanaVendedor.getTablaVentas().getValueAt(indice, 2).toString());
                        Vendedor vendedor = new Vendedor(producto);
                        lista = vendedor.consultar();
                        producto = lista.get(0);
                        cantidad = (int) vistaVentanaVendedor.getTablaVentas().getValueAt(indice, 3);
                        auxiliar = producto.getCantidad();
                        producto.setCantidad(auxiliar + cantidad);
                        vendedor.setProducto(producto);
                        vendedor.modificarCantidad(lista.get(0));
                        modificarTotal(indice);
                        eliminarFila(indice);
                    }
                }
            } catch (Exception e) {

            }
        }
        if (vistaVentanaVendedor.getBotonListo() == evento.getSource()) {
            int respuesta;
            respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de afectuar la venta?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                VistaTicketKasse ventanaTicket = new VistaTicketKasse();
                ControlTicket controlTicket = new ControlTicket(vistaVentanaVendedor, ventanaTicket);
                ventanaTicket.setVisible(true);
                vistaVentanaVendedor.getLabelTotal().setText(String.valueOf(0));
                vistaVentanaVendedor.getFieldCodigo().setText(null);
                vistaVentanaVendedor.getFieldNombre().setText(null);
                vistaVentanaVendedor.getFieldCantidad().setText(null);
                limpiarTabla();
            } else {
                //Se cierra xd
            }
        }

        if (vistaVentanaVendedor.getBotonSalir() == evento.getSource()) {
            int respuesta;
            respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de cerrar sesión?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                vistaVentanaVendedor.setVisible(false);
                vistaVentanaVendedor.dispose();
                VistaLogin login = new VistaLogin();
                ControlLogin controllogin = new ControlLogin(login);
                login.setVisible(true);
            } else {
                //Se cierra xd
            }

        }
        if (vistaVentanaVendedor.getBotonSwitch() == evento.getSource()) {
            int respuesta;
            respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de regresar a la ventana Administrador?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                vistaVentanaVendedor.setVisible(false);
                vistaVentanaVendedor.dispose();
                VistaVentanaAdministrador ventanaAdministrador = new VistaVentanaAdministrador();
                ControlVentanaAdministrador controlAdministrador = new ControlVentanaAdministrador(ventanaAdministrador);
                ventanaAdministrador.setVisible(true);
            } else {
                //Se cierra xd
            }
        }
    }

    public void agregarFila(ArrayList<Producto> lista, int cantidad) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) this.vistaVentanaVendedor.getTablaVentas().getModel();
        Object[] filas = new Object[6];

        filas[0] = contador;
        filas[1] = lista.get(0).getCodigo();
        filas[2] = lista.get(0).getNombre();
        filas[3] = cantidad;
        filas[4] = lista.get(0).getPrecio_venta();
        filas[5] = (double) cantidad * lista.get(0).getPrecio_venta();

        modelo.addRow(filas);

    }

    public void limpiarTabla() {
        int fila;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) this.vistaVentanaVendedor.getTablaVentas().getModel();
        fila = modelo.getRowCount();
        for (int i = fila - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    public void eliminarFila(int aux) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) this.vistaVentanaVendedor.getTablaVentas().getModel();
        if (aux >= 0) {
            modelo.removeRow(aux);
        }

    }

    public void modificarTotal(int aux) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) this.vistaVentanaVendedor.getTablaVentas().getModel();

        if (aux == -1) {
            total = total + (double) modelo.getValueAt(modelo.getRowCount() - 1, 5);
            vistaVentanaVendedor.getLabelTotal().setText(String.valueOf(total));
        } else {
            total = total - (double) modelo.getValueAt(aux, 5);
            vistaVentanaVendedor.getLabelTotal().setText(String.valueOf(total));
        }
    }

}
