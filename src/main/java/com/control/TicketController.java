package com.control;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.table.DefaultTableModel;
import com.view.TicketView;

public class TicketController implements ActionListener, Printable {

    private TicketView ticketView;
    private DefaultTableModel saleTable;
    private String totalCost;

    public TicketController(TicketView ticketView, DefaultTableModel saleTable, String totalCost) {
        this.ticketView = ticketView;
        this.saleTable = saleTable;
        this.totalCost = totalCost;

        getSaleData();

        addActionListeners();

    }

    private void addActionListeners() {
        ticketView.getGenerateButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();

        if (source == ticketView.getGenerateButton()) {
            printTicket();
        }

    }

    private void printTicket() {
        try {

            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPrintable(this);

            if (printerJob.printDialog()) {
                printerJob.print();
            }
        } catch (PrinterException exception) {
            System.err.println("Error al imprimir: " + exception.getMessage());
        }
    }

    public void getSaleData() {

        StringBuilder stringBuilder = new StringBuilder("\tIndice\tCodigo\tNombre\tCantidad\tPrecio\tTotal\n\t");

        for (int i = 0; i < saleTable.getRowCount(); i++) {
            for (int j = 0; j < saleTable.getColumnCount(); j++) {
                stringBuilder.append(saleTable.getValueAt(i, j).toString()).append("\t");
            }
            stringBuilder.append("\n\t");
        }

        stringBuilder.append("\n\t\t\t\t\t\tTotal: ").append(this.totalCost);
        ticketView.getListContent().setText(stringBuilder.toString());

    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        graphics2D.scale(1, 1);
        ticketView.getBasePanel().printAll(graphics);

        return PAGE_EXISTS;
    }
}
