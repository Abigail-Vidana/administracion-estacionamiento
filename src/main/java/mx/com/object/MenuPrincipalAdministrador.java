package mx.com.object;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import mx.com.util.ImagenFondo;

/**
 *
 * @author abigail
 */
public class MenuPrincipalAdministrador extends javax.swing.JFrame {

    IngresoFrame ingresoFrame = new IngresoFrame();
    EgresoFrame egresoFrame = new EgresoFrame();
    EstadoFrame estadoFrame = new EstadoFrame();
    UsuarioFrame usuarioFrame = new UsuarioFrame();
    CajonesFrame cajonesFrame = new CajonesFrame();
    ReporteFrame reporteFrame = new ReporteFrame();
    ClienteFrame clienteFrame = new ClienteFrame();
    ServidorFrame servidorFrame = new ServidorFrame();
    List<JInternalFrame> frames = new ArrayList<>();
    /**
     * Creates new form MenuPrincipal2
     */
    public MenuPrincipalAdministrador() {
        initComponents();
        //caracteristicas de la ventana
        setTitle("Estacionamiento Buena Fe");
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/main/resources/cross.png"); //icono de la ventana
        Image image = icon.getImage();
        setIconImage(image); 
        jDesktopPane1.setBorder(new ImagenFondo());
        this.setExtendedState(MenuPrincipalAdministrador.MAXIMIZED_BOTH);
        this.jDesktopPane1.add(ingresoFrame);
        this.jDesktopPane1.add(egresoFrame);
        this.jDesktopPane1.add(estadoFrame);
        this.jDesktopPane1.add(usuarioFrame);
        this.jDesktopPane1.add(cajonesFrame);
        this.jDesktopPane1.add(reporteFrame);
        this.jDesktopPane1.add(clienteFrame);
        this.jDesktopPane1.add(servidorFrame);
        this.frames.add(ingresoFrame);
        this.frames.add(egresoFrame);
        this.frames.add(estadoFrame);
        this.frames.add(usuarioFrame);
        //this.frames.add(cajonesFrame);
        this.frames.add(reporteFrame);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuBoleto = new javax.swing.JMenu();
        itemAlta = new javax.swing.JMenuItem();
        itemBaja = new javax.swing.JMenuItem();
        itemConsulta = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuAuto = new javax.swing.JMenu();
        itemCajones = new javax.swing.JMenuItem();
        menuAcomodador = new javax.swing.JMenu();
        itemCliente = new javax.swing.JMenuItem();
        menuAdministracion = new javax.swing.JMenu();
        itemUsuario = new javax.swing.JMenuItem();
        itemReporte = new javax.swing.JMenuItem();
        itemServidor = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));
        jMenuBar1.setForeground(new java.awt.Color(204, 204, 204));

        menuBoleto.setForeground(new java.awt.Color(0, 102, 153));
        menuBoleto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/boleto.png"))); // NOI18N
        menuBoleto.setText("Boleto");
        menuBoleto.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 12)); // NOI18N

        itemAlta.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        itemAlta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guardar.png"))); // NOI18N
        itemAlta.setText("Alta");
        itemAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAlta(evt);
            }
        });
        menuBoleto.add(itemAlta);

        itemBaja.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        itemBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/baja.png"))); // NOI18N
        itemBaja.setText("Baja");
        itemBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBaja(evt);
            }
        });
        menuBoleto.add(itemBaja);

        itemConsulta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        itemConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/consultar.png"))); // NOI18N
        itemConsulta.setText("Consulta");
        itemConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConsulta(evt);
            }
        });
        menuBoleto.add(itemConsulta);

        jMenuItem1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salir.png"))); // NOI18N
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuBoleto.add(jMenuItem1);

        jMenuBar1.add(menuBoleto);

        menuAuto.setForeground(new java.awt.Color(0, 102, 153));
        menuAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/car.png"))); // NOI18N
        menuAuto.setText("Auto");
        menuAuto.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 12)); // NOI18N

        itemCajones.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        itemCajones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eye.png"))); // NOI18N
        itemCajones.setText("Ver cajones");
        itemCajones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCajonesActionPerformed(evt);
            }
        });
        menuAuto.add(itemCajones);

        jMenuBar1.add(menuAuto);

        menuAcomodador.setForeground(new java.awt.Color(0, 102, 153));
        menuAcomodador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuario.png"))); // NOI18N
        menuAcomodador.setText("Acomodador");
        menuAcomodador.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 12)); // NOI18N

        itemCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        itemCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat.png"))); // NOI18N
        itemCliente.setText("Chat");
        itemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemClienteActionPerformed(evt);
            }
        });
        menuAcomodador.add(itemCliente);

        jMenuBar1.add(menuAcomodador);

        menuAdministracion.setForeground(new java.awt.Color(0, 102, 153));
        menuAdministracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin.png"))); // NOI18N
        menuAdministracion.setText("Administracion");
        menuAdministracion.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 12)); // NOI18N

        itemUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        itemUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usercirlce.png"))); // NOI18N
        itemUsuario.setText("Agregar usuario");
        itemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUsuarioActionPerformed(evt);
            }
        });
        menuAdministracion.add(itemUsuario);

        itemReporte.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        itemReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivo.png"))); // NOI18N
        itemReporte.setText("Reporte semanal");
        itemReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReporteActionPerformed(evt);
            }
        });
        menuAdministracion.add(itemReporte);

        itemServidor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        itemServidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat.png"))); // NOI18N
        itemServidor.setText("Servidor de chat");
        itemServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemServidorActionPerformed(evt);
            }
        });
        menuAdministracion.add(itemServidor);

        jMenuBar1.add(menuAdministracion);

        setJMenuBar(jMenuBar1);

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

    private void itemAlta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAlta
        esconder();
        ingresoFrame.show();
    }//GEN-LAST:event_itemAlta

    private void itemBaja(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBaja
        esconder();
        egresoFrame.show();
    }//GEN-LAST:event_itemBaja

    private void itemConsulta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConsulta
        esconder();
        estadoFrame.show();
    }//GEN-LAST:event_itemConsulta

    private void itemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUsuarioActionPerformed
        esconder();
        usuarioFrame.show();
    }//GEN-LAST:event_itemUsuarioActionPerformed

    private void itemCajonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCajonesActionPerformed
        cajonesFrame.show();
    }//GEN-LAST:event_itemCajonesActionPerformed

    private void itemReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReporteActionPerformed
        esconder();
        reporteFrame.show();
    }//GEN-LAST:event_itemReporteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        InicioSesionFrame inicio = new InicioSesionFrame();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void itemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemClienteActionPerformed
        clienteFrame.show();
    }//GEN-LAST:event_itemClienteActionPerformed

    private void itemServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemServidorActionPerformed
        servidorFrame.show();
    }//GEN-LAST:event_itemServidorActionPerformed
    private void esconder(){
        for(JInternalFrame frame : frames){
            frame.hide();
        }
    } 
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
            java.util.logging.Logger.getLogger(MenuPrincipalAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipalAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemAlta;
    private javax.swing.JMenuItem itemBaja;
    private javax.swing.JMenuItem itemCajones;
    private javax.swing.JMenuItem itemCliente;
    private javax.swing.JMenuItem itemConsulta;
    private javax.swing.JMenuItem itemReporte;
    private javax.swing.JMenuItem itemServidor;
    private javax.swing.JMenuItem itemUsuario;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu menuAcomodador;
    private javax.swing.JMenu menuAdministracion;
    private javax.swing.JMenu menuAuto;
    private javax.swing.JMenu menuBoleto;
    // End of variables declaration//GEN-END:variables
}
