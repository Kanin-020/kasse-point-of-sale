/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import vista.VistaTicketKasse;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import vista.VistaVentanaVendedor;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author l2001
 */
public class ControlTicket extends javax.swing.JFrame implements ActionListener, Printable {

    private VistaTicketKasse vistaTicket;
    private VistaVentanaVendedor vistaVentanaVendedor;

    public ControlTicket(VistaVentanaVendedor vistaVentanaVendedor, VistaTicketKasse vistaTicket) {
        this.vistaTicket = vistaTicket;
        this.vistaVentanaVendedor = vistaVentanaVendedor;
        obtenerDatos();

        this.vistaTicket.getBotonGenerar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {
        if (vistaTicket.getBotonGenerar() == evento.getSource()) {
            try {
                vistaTicket.getBotonGenerar().setVisible(false);
                PrinterJob imprimir = PrinterJob.getPrinterJob();
                imprimir.setPrintable(this);
                boolean comprobar = imprimir.printDialog();

                if (comprobar) {

                    imprimir.print();
                }

            } catch (PrinterException e) {

            }
        }

    }

    public void obtenerDatos() {
        DefaultTableModel tabla = new DefaultTableModel();

        tabla = (DefaultTableModel) this.vistaVentanaVendedor.getTablaVentas().getModel();

        String auxiliar = "\tIndice\t Codigo\t Nombre\t Cantidad\t Precio\t Total\n\t";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            for (int j = 0; j < tabla.getColumnCount(); j++) {

                String celda = tabla.getValueAt(i, j).toString();

                auxiliar = auxiliar + celda + "\t";

            }
            auxiliar = auxiliar + "\n\t";

        }
        auxiliar = auxiliar + "\n\t\t\t\t\t\tTotal:   " + vistaVentanaVendedor.getLabelTotal().getText();
        vistaTicket.getTextPanelCampo().setText(auxiliar);
    }

    @Override
    public int print(Graphics graf, PageFormat pagefor, int i) throws PrinterException {
        if (i > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D ar = (Graphics2D) graf;
        ar.translate(pagefor.getImageableX() * 50, pagefor.getImageableY() * 50);
        ar.scale(1, 1);
        vistaTicket.getjPanel1().printAll(graf);
        return PAGE_EXISTS;

    }

}
