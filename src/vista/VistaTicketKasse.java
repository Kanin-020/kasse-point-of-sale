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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

/**
 *
 * @author l2001
 */
public class VistaTicketKasse extends javax.swing.JFrame {

    /**
     * Creates new form VistaTicketKasse
     */
    public VistaTicketKasse() {
        initComponents();
        // this.setExtendedState(0);
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

        jPanel1 = new javax.swing.JPanel();
        LabelLocal = new javax.swing.JLabel();
        Icono = new javax.swing.JLabel();
        LabelRFC = new javax.swing.JLabel();
        LabelLugar = new javax.swing.JLabel();
        LabelMerida = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextPanelCampo = new javax.swing.JTextPane();
        Separador = new javax.swing.JSeparator();
        BotonGenerar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        LabelLocal.setText("Abarrotes Doña Yolis");

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Fuente.png"))); // NOI18N

        LabelRFC.setText("R.F.C           TK1821873-RMC-ASA ");

        LabelLugar.setText("Colonia Francisco de Montejo\t    CP 97203 \t");

        LabelMerida.setText("Mérida Yucatán");

        TextPanelCampo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TextPanelCampo.setAutoscrolls(false);
        TextPanelCampo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(TextPanelCampo);

        BotonGenerar.setText("Generar");
        BotonGenerar.setBorder(null);
        BotonGenerar.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Separador, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelRFC)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(LabelLugar)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(LabelMerida)
                                    .addGap(88, 88, 88))))
                        .addGap(145, 145, 145))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(BotonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelLocal)
                        .addGap(214, 214, 214))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelLocal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Icono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelRFC)
                .addGap(18, 18, 18)
                .addComponent(LabelLugar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LabelMerida)
                .addGap(18, 18, 18)
                .addComponent(Separador, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VistaTicketKasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaTicketKasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaTicketKasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaTicketKasse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaTicketKasse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BotonGenerar;
    private javax.swing.JLabel Icono;
    private javax.swing.JLabel LabelLocal;
    private javax.swing.JLabel LabelLugar;
    private javax.swing.JLabel LabelMerida;
    private javax.swing.JLabel LabelRFC;
    private javax.swing.JSeparator Separador;
    private javax.swing.JTextPane TextPanelCampo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    

    public JLabel getIcono() {
        return Icono;
    }

    public void setIcono(JLabel Icono) {
        this.Icono = Icono;
    }

    public JLabel getLabelLocal() {
        return LabelLocal;
    }

    public void setLabelLocal(JLabel LabelLocal) {
        this.LabelLocal = LabelLocal;
    }

    public JLabel getLabelLugar() {
        return LabelLugar;
    }

    public void setLabelLugar(JLabel LabelLugar) {
        this.LabelLugar = LabelLugar;
    }

    public JLabel getLabelMerida() {
        return LabelMerida;
    }

    public void setLabelMerida(JLabel LabelMerida) {
        this.LabelMerida = LabelMerida;
    }

    public JLabel getLabelRFC() {
        return LabelRFC;
    }

    public void setLabelRFC(JLabel LabelRFC) {
        this.LabelRFC = LabelRFC;
    }


    public JSeparator getSeparador() {
        return Separador;
    }

    public void setSeparador(JSeparator Separador) {
        this.Separador = Separador;
    }

    public JTextPane getTextPanelCampo() {
        return TextPanelCampo;
    }

    public void setTextPanelCampo(JTextPane TextPanelCampo) {
        this.TextPanelCampo = TextPanelCampo;
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

    public JToggleButton getBotonGenerar() {
        return BotonGenerar;
    }

    public void setBotonGenerar(JToggleButton BotonGenerar) {
        this.BotonGenerar = BotonGenerar;
    }

   

 
    
}
