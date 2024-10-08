/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author l2001
 */
public class VistaVentanaVendedor extends javax.swing.JFrame {

    /**
     * Creates new form vistaPrincipal
     */
    public VistaVentanaVendedor() {
        initComponents();
        this.setExtendedState(6);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/Fuente.png")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelBase = new javax.swing.JPanel();
        PanelLateralIzquierdo = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        LabelTitulo = new javax.swing.JLabel();
        LabelIcono = new javax.swing.JLabel();
        LabelCodigo = new javax.swing.JLabel();
        LabelCantidad = new javax.swing.JLabel();
        LabelNombre = new javax.swing.JLabel();
        FieldNombre = new javax.swing.JTextField();
        FieldCantidad = new javax.swing.JTextField();
        FieldCodigo = new javax.swing.JTextField();
        BotonAgregar = new javax.swing.JButton();
        BotonSalir = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();
        BotonBuscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        LabelEtiqueta = new javax.swing.JLabel();
        LabelTotal = new javax.swing.JLabel();
        BotonListo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVentas = new javax.swing.JTable();
        PanelSuperior = new javax.swing.JPanel();
        BotonSwitch = new javax.swing.JButton();
        BotonBienvenida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kasse");
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(1374, 861));

        PanelBase.setBackground(new java.awt.Color(255, 255, 255));
        PanelBase.setPreferredSize(new java.awt.Dimension(1050, 575));

        PanelLateralIzquierdo.setBackground(new java.awt.Color(255, 102, 102));
        PanelLateralIzquierdo.setMaximumSize(new java.awt.Dimension(200, 32767));
        PanelLateralIzquierdo.setMinimumSize(new java.awt.Dimension(200, 100));
        PanelLateralIzquierdo.setPreferredSize(new java.awt.Dimension(200, 575));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        LabelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        LabelTitulo.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        LabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LabelTitulo.setText("Kasse");
        LabelTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LabelIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/IconoPanelVendedor.png"))); // NOI18N

        LabelCodigo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LabelCodigo.setForeground(new java.awt.Color(255, 255, 255));
        LabelCodigo.setText("Codigo:");

        LabelCantidad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LabelCantidad.setForeground(new java.awt.Color(255, 255, 255));
        LabelCantidad.setText("Cantidad:");

        LabelNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LabelNombre.setForeground(new java.awt.Color(255, 255, 255));
        LabelNombre.setText("Nombre:");

        FieldNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        FieldCantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        FieldCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        BotonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonAgregarRB.png"))); // NOI18N
        BotonAgregar.setBorder(null);
        BotonAgregar.setBorderPainted(false);
        BotonAgregar.setMaximumSize(new java.awt.Dimension(200, 66));
        BotonAgregar.setMinimumSize(new java.awt.Dimension(200, 66));
        BotonAgregar.setPreferredSize(new java.awt.Dimension(200, 66));
        BotonAgregar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonAgregarRS.png"))); // NOI18N
        BotonAgregar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonAgregarRB.png"))); // NOI18N

        BotonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonSalirRB.png"))); // NOI18N
        BotonSalir.setBorder(null);
        BotonSalir.setBorderPainted(false);
        BotonSalir.setMaximumSize(new java.awt.Dimension(200, 66));
        BotonSalir.setMinimumSize(new java.awt.Dimension(200, 66));
        BotonSalir.setPreferredSize(new java.awt.Dimension(200, 66));
        BotonSalir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonSalirRS.png"))); // NOI18N
        BotonSalir.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonSalirRB.png"))); // NOI18N
        BotonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSalirActionPerformed(evt);
            }
        });

        BotonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonEliminarRB.png"))); // NOI18N
        BotonEliminar.setBorder(null);
        BotonEliminar.setBorderPainted(false);
        BotonEliminar.setMaximumSize(new java.awt.Dimension(200, 66));
        BotonEliminar.setMinimumSize(new java.awt.Dimension(200, 66));
        BotonEliminar.setPreferredSize(new java.awt.Dimension(200, 66));
        BotonEliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonEliminarRS.png"))); // NOI18N
        BotonEliminar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonEliminarRB.png"))); // NOI18N

        BotonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonBuscarB.png"))); // NOI18N
        BotonBuscar.setBorder(null);
        BotonBuscar.setBorderPainted(false);
        BotonBuscar.setMaximumSize(new java.awt.Dimension(200, 66));
        BotonBuscar.setMinimumSize(new java.awt.Dimension(200, 66));
        BotonBuscar.setPreferredSize(new java.awt.Dimension(200, 66));
        BotonBuscar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonBuscarS.png"))); // NOI18N
        BotonBuscar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonBuscarB.png"))); // NOI18N

        javax.swing.GroupLayout PanelLateralIzquierdoLayout = new javax.swing.GroupLayout(PanelLateralIzquierdo);
        PanelLateralIzquierdo.setLayout(PanelLateralIzquierdoLayout);
        PanelLateralIzquierdoLayout.setHorizontalGroup(
            PanelLateralIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLateralIzquierdoLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(LabelIcono)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelLateralIzquierdoLayout.createSequentialGroup()
                .addGroup(PanelLateralIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLateralIzquierdoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(PanelLateralIzquierdoLayout.createSequentialGroup()
                        .addGroup(PanelLateralIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelLateralIzquierdoLayout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addComponent(LabelTitulo))
                            .addGroup(PanelLateralIzquierdoLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(PanelLateralIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelCodigo)
                                    .addComponent(LabelCantidad)
                                    .addComponent(LabelNombre)
                                    .addComponent(FieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 25, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(BotonAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotonBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelLateralIzquierdoLayout.setVerticalGroup(
            PanelLateralIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLateralIzquierdoLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(LabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(LabelCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LabelNombre)
                .addGap(18, 18, 18)
                .addComponent(FieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LabelCantidad)
                .addGap(18, 18, 18)
                .addComponent(FieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(BotonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(BotonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelIcono)
                .addGap(37, 37, 37))
        );

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        LabelEtiqueta.setBackground(new java.awt.Color(255, 255, 255));
        LabelEtiqueta.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        LabelEtiqueta.setForeground(new java.awt.Color(255, 255, 255));
        LabelEtiqueta.setText("Total: ");

        LabelTotal.setBackground(new java.awt.Color(255, 255, 255));
        LabelTotal.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        LabelTotal.setForeground(new java.awt.Color(255, 255, 255));
        LabelTotal.setText("0");

        BotonListo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonListoB.png"))); // NOI18N
        BotonListo.setBorder(null);
        BotonListo.setBorderPainted(false);
        BotonListo.setMaximumSize(new java.awt.Dimension(200, 66));
        BotonListo.setMinimumSize(new java.awt.Dimension(200, 66));
        BotonListo.setPreferredSize(new java.awt.Dimension(200, 66));
        BotonListo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonListoS.png"))); // NOI18N
        BotonListo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonListoB.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(658, Short.MAX_VALUE)
                .addComponent(LabelEtiqueta)
                .addGap(28, 28, 28)
                .addComponent(LabelTotal)
                .addGap(30, 30, 30)
                .addComponent(BotonListo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BotonListo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEtiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jScrollPane1.setBorder(null);

        TablaVentas.setAutoCreateRowSorter(true);
        TablaVentas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TablaVentas.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        TablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Índice", "Código", "Nombre", "Cantidad", "Precio", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Short.class, java.lang.String.class, java.lang.String.class, java.lang.Short.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaVentas.setToolTipText("");
        TablaVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TablaVentas.setGridColor(new java.awt.Color(255, 255, 255));
        TablaVentas.setRowHeight(30);
        TablaVentas.setSelectionBackground(new java.awt.Color(255, 153, 153));
        TablaVentas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TablaVentas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaVentas);
        if (TablaVentas.getColumnModel().getColumnCount() > 0) {
            TablaVentas.getColumnModel().getColumn(0).setResizable(false);
            TablaVentas.getColumnModel().getColumn(0).setPreferredWidth(10);
            TablaVentas.getColumnModel().getColumn(1).setResizable(false);
            TablaVentas.getColumnModel().getColumn(1).setPreferredWidth(1);
            TablaVentas.getColumnModel().getColumn(2).setResizable(false);
            TablaVentas.getColumnModel().getColumn(3).setResizable(false);
            TablaVentas.getColumnModel().getColumn(4).setResizable(false);
            TablaVentas.getColumnModel().getColumn(5).setResizable(false);
        }

        PanelSuperior.setBackground(new java.awt.Color(232, 80, 80));

        BotonSwitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonSwitchRB.png"))); // NOI18N
        BotonSwitch.setBorder(null);
        BotonSwitch.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonSwitchRDisable.png"))); // NOI18N
        BotonSwitch.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonSwitchRS.png"))); // NOI18N
        BotonSwitch.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BotonSwitchRB.png"))); // NOI18N

        javax.swing.GroupLayout PanelSuperiorLayout = new javax.swing.GroupLayout(PanelSuperior);
        PanelSuperior.setLayout(PanelSuperiorLayout);
        PanelSuperiorLayout.setHorizontalGroup(
            PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSuperiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BotonSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        PanelSuperiorLayout.setVerticalGroup(
            PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BotonSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 93, Short.MAX_VALUE)
        );

        BotonBienvenida.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        BotonBienvenida.setText("¡Bienvenido compañero Vendedor!");

        javax.swing.GroupLayout PanelBaseLayout = new javax.swing.GroupLayout(PanelBase);
        PanelBase.setLayout(PanelBaseLayout);
        PanelBaseLayout.setHorizontalGroup(
            PanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBaseLayout.createSequentialGroup()
                .addComponent(PanelLateralIzquierdo, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelBaseLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(BotonBienvenida)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBaseLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        PanelBaseLayout.setVerticalGroup(
            PanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BotonBienvenida)
                .addGap(12, 12, 12)
                .addComponent(PanelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(PanelLateralIzquierdo, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelBase, javax.swing.GroupLayout.DEFAULT_SIZE, 1374, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelBase, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaVentanaVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaVentanaVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaVentanaVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaVentanaVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaVentanaVendedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregar;
    private javax.swing.JLabel BotonBienvenida;
    private javax.swing.JButton BotonBuscar;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonListo;
    private javax.swing.JButton BotonSalir;
    private javax.swing.JButton BotonSwitch;
    private javax.swing.JTextField FieldCantidad;
    private javax.swing.JTextField FieldCodigo;
    private javax.swing.JTextField FieldNombre;
    private javax.swing.JLabel LabelCantidad;
    private javax.swing.JLabel LabelCodigo;
    private javax.swing.JLabel LabelEtiqueta;
    private javax.swing.JLabel LabelIcono;
    private javax.swing.JLabel LabelNombre;
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JPanel PanelBase;
    private javax.swing.JPanel PanelLateralIzquierdo;
    private javax.swing.JPanel PanelSuperior;
    private javax.swing.JTable TablaVentas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    public JButton getBotonAgregar() {
        return BotonAgregar;
    }

    public void setBotonAgregar(JButton BotonAgregar) {
        this.BotonAgregar = BotonAgregar;
    }

    public JLabel getBotonBienvenida() {
        return BotonBienvenida;
    }

    public void setBotonBienvenida(JLabel BotonBienvenida) {
        this.BotonBienvenida = BotonBienvenida;
    }

    public JButton getBotonEliminar() {
        return BotonEliminar;
    }

    public void setBotonEliminar(JButton BotonEliminar) {
        this.BotonEliminar = BotonEliminar;
    }

    public JButton getBotonListo() {
        return BotonListo;
    }

    public void setBotonListo(JButton BotonListo) {
        this.BotonListo = BotonListo;
    }

    public JButton getBotonSalir() {
        return BotonSalir;
    }

    public void setBotonSalir(JButton BotonSalir) {
        this.BotonSalir = BotonSalir;
    }

    public JButton getBotonSwitch() {
        return BotonSwitch;
    }

    public void setBotonSwitch(JButton BotonSwitch) {
        this.BotonSwitch = BotonSwitch;
    }

    public JTextField getFieldCantidad() {
        return FieldCantidad;
    }

    public void setFieldCantidad(JTextField FieldCantidad) {
        this.FieldCantidad = FieldCantidad;
    }

    public JTextField getFieldCodigo() {
        return FieldCodigo;
    }

    public void setFieldCodigo(JTextField FieldCodigo) {
        this.FieldCodigo = FieldCodigo;
    }

    public JTextField getFieldNombre() {
        return FieldNombre;
    }

    public void setFieldNombre(JTextField FieldNombre) {
        this.FieldNombre = FieldNombre;
    }

    public JLabel getLabelCantidad() {
        return LabelCantidad;
    }

    public void setLabelCantidad(JLabel LabelCantidad) {
        this.LabelCantidad = LabelCantidad;
    }

    public JLabel getLabelEtiqueta() {
        return LabelEtiqueta;
    }

    public void setLabelEtiqueta(JLabel LabelEtiqueta) {
        this.LabelEtiqueta = LabelEtiqueta;
    }

    public JLabel getLabelCodigo() {
        return LabelCodigo;
    }

    public void setLabelCodigo(JLabel LabelCodigo) {
        this.LabelCodigo = LabelCodigo;
    }

    public JLabel getLabelIcono() {
        return LabelIcono;
    }

    public void setLabelIcono(JLabel LabelIcono) {
        this.LabelIcono = LabelIcono;
    }

    public JLabel getLabelNombre() {
        return LabelNombre;
    }

    public void setLabelNombre(JLabel LabelNombre) {
        this.LabelNombre = LabelNombre;
    }

    public JLabel getLabelTitulo() {
        return LabelTitulo;
    }

    public void setLabelTitulo(JLabel LabelTitulo) {
        this.LabelTitulo = LabelTitulo;
    }

    public JLabel getLabelTotal() {
        return LabelTotal;
    }

    public void setLabelTotal(JLabel LabelTotal) {
        this.LabelTotal = LabelTotal;
    }

    public JPanel getPanelBase() {
        return PanelBase;
    }

    public void setPanelBase(JPanel PanelBase) {
        this.PanelBase = PanelBase;
    }

    public JPanel getPanelLateralIzquierdo() {
        return PanelLateralIzquierdo;
    }

    public void setPanelLateralIzquierdo(JPanel PanelLateralIzquierdo) {
        this.PanelLateralIzquierdo = PanelLateralIzquierdo;
    }

    public JPanel getPanelSuperior() {
        return PanelSuperior;
    }

    public void setPanelSuperior(JPanel PanelSuperior) {
        this.PanelSuperior = PanelSuperior;
    }

    public JTable getTablaVentas() {
        return TablaVentas;
    }

    public void setTablaVentas(JTable TablaVentas) {
        this.TablaVentas = TablaVentas;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JSeparator getjSeparator1() {
        return jSeparator1;
    }

    public void setjSeparator1(JSeparator jSeparator1) {
        this.jSeparator1 = jSeparator1;
    }

    public JButton getBotonBuscar() {
        return BotonBuscar;
    }

    public void setBotonBuscar(JButton BotonBuscar) {
        this.BotonBuscar = BotonBuscar;
    }

    

}
