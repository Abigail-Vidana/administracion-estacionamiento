package mx.com.object;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import java.awt.Image;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import mx.com.dao.UsuarioDAO;
import mx.com.util.ReporteTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author abigail
 */
public class ReporteFrame extends javax.swing.JInternalFrame {

     List<Map<String, String>> usuario = null; 
     UsuarioDAO usuarioDao = new UsuarioDAO();
     ReporteTable modeloTabla =  new ReporteTable(); //objeto modelo de la clase donde declaramos la tabla
     /**
     * Creates new form ReporteFrame
     */
    public ReporteFrame() {    
        initComponents();
 
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
        lblReporte = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblInicio = new javax.swing.JLabel();
        lblTermino = new javax.swing.JLabel();
        jDateInicio = new com.toedter.calendar.JDateChooser();
        jDateTermino = new com.toedter.calendar.JDateChooser();
        btnVer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        btnReporte = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte Semanal");

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        lblReporte.setBackground(new java.awt.Color(204, 255, 204));
        lblReporte.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblReporte.setForeground(new java.awt.Color(204, 255, 204));
        lblReporte.setText("Reporte Semanal");

        lblDescripcion.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcion.setText("Inserte el rango de fechas para obtener a los 5 mejores trabajadores de la semana");

        lblInicio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblInicio.setText("Inicio de semana:");

        lblTermino.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTermino.setForeground(new java.awt.Color(255, 255, 255));
        lblTermino.setText("Termino de semana:");

        btnVer.setText("Ver resultados");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVer(evt);
            }
        });

        tablaUsuarios.setModel(modeloTabla);
        jScrollPane1.setViewportView(tablaUsuarios);

        btnReporte.setText("crear PDF");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPDF(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescripcion)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblInicio)
                                    .addComponent(lblTermino))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(jDateTermino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnVer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(lblReporte)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblReporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDescripcion)
                        .addGap(25, 25, 25)
                        .addComponent(lblInicio))
                    .addComponent(jDateInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTermino)
                    .addComponent(jDateTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporte))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
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

    private void btnVer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVer
        Date dateTermino = jDateTermino.getDate();
        Date dateInicio = jDateInicio.getDate();
        usuario = usuarioDao.registroUsuarioCantidadServicioSueldo(dateInicio, dateTermino);
        modeloTabla = new ReporteTable(usuario);
        tablaUsuarios.setModel(modeloTabla);
    }//GEN-LAST:event_btnVer

    private void btnCrearPDF(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPDF
        if(usuario.size()>0){ //si hay resgitros encontrados
            //le damos a elegir donde guardar el archivo
            jFileChooser1.showSaveDialog(null);
            File archivo =jFileChooser1.getSelectedFile(); //aqui se almacena el nombre del archivo
            if(archivo !=null){ //si se solicitó guardar un archivo
                String suffix = ".pdf"; //variable para el sufijo
                //en caso de que no se asignó automáticamente el .pdf
                if(!jFileChooser1.getSelectedFile().getAbsolutePath().endsWith(suffix)){
                    archivo = new File(jFileChooser1.getSelectedFile() + suffix); //se asigna a nuestro nombre el .pdf
                }
                Document reportePdf = new Document(); //variable de tipo Document para el reporte
                try {
                    //con esta variable escribimos sobre el archivo
                    PdfWriter pdfWriter = PdfWriter.getInstance(reportePdf, new FileOutputStream(archivo));
                    reportePdf.open(); //se abre
                    //al titulo le ponemos un tipo de fuente y tamaño
                    reportePdf.add(getParrafo(18f,24f,"Reporte semanal de los trabajadores",FontFactory.HELVETICA_BOLDOBLIQUE));
                    reportePdf.add(crearTabla()); //se crea el reporte
                    reportePdf.close(); //cerramos el reporte
                    pdfWriter.close(); //le indica que no escribimos mas
                    JOptionPane.showMessageDialog(null, "Reporte Creado!"); //mensaje que informa al usuario de reporte creado
                } catch (FileNotFoundException | DocumentException ex) { //excepcion que puede ocurrir
                    Logger.getLogger(ReporteFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "No hay Información!");//si no hay registros, se le informa al usuario
        }
    }//GEN-LAST:event_btnCrearPDF

    //con este metodo le damos al texto del documento, un tamaño a la letra, espacios y tipo de letra al texto que recibe
    private Paragraph getParrafo(float fntSize, float lineSpacing, String text, String letra){
        return new Paragraph(new Phrase(lineSpacing,text,
                               FontFactory.getFont(FontFactory.COURIER_BOLD, fntSize)));
    }
    
    //metodo para obtener los valores que se van a guardar en la tabla de reporte
    private PdfPTable crearTabla(){
        float[] anchoColumnas = {4f,4f,4f}; //espacio de las columnas
        //se crea la tabla y se indica el ancho que lleva sus columnas, y al mismo tiempo le decimos que son 7 columnas
        PdfPTable tabla = new PdfPTable(anchoColumnas); 
        tabla.setWidthPercentage(110); //ancho
        tabla.setSpacingAfter(5f); //espacio despues
        tabla.setSpacingBefore(5f); //espacio antes
        
        //aqui obtenemos la primera fila (los titulos)
        usuario.get(0).keySet().forEach( col -> { //se itera cada titulo o columna
            PdfPCell celda = new PdfPCell(getParrafo(10f,10f,col,FontFactory.COURIER_BOLD)); //tamaño, espacio, texto y tipo de letra de cada titulo
            celda.setBackgroundColor(BaseColor.CYAN); //un color para los titulos
            tabla.addCell( celda); //añadimos todos estos titulos a la tabla
        });
        
        //aqui obtenemos todos los valores de la tabla
        usuario.forEach(fila ->{ //se itera cada fila
            fila.values().forEach(col ->{ //para cada fila le damos un valor a las 3 columnas (0,1 - 0,2 - 0,3)
                //tamaño, espacio, texto y tipo de letra de cada fila
                tabla.addCell( new PdfPCell(getParrafo(8f,10f,col,FontFactory.COURIER))); //se añade todos estos valores a la tabla
            });
        });
        return tabla; //se retorna la tabla
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
            java.util.logging.Logger.getLogger(ReporteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteFrame().setVisible(true);
            }
        });
    }

    private JFileChooser jFileChooser1 = new JFileChooser();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnVer;
    private com.toedter.calendar.JDateChooser jDateInicio;
    private com.toedter.calendar.JDateChooser jDateTermino;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblReporte;
    private javax.swing.JLabel lblTermino;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}
