package mx.com.object;

import mx.com.util.OyenteAcciones;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import mx.com.model.Cajon;
import mx.com.dao.CajonDAO;

/**
 *
 * @author abigail
 */
//Ventana para mostrar los cajones de forma gráfica
public class CajonesFrame extends javax.swing.JInternalFrame {

    DefaultComboBoxModel modeloNiveles = new DefaultComboBoxModel();//combo para seleccionar el nivel (1-4)
    List<Cajon> cajones;//lista para almacenar los cajones
    List<Integer> niveles;// lista para almacenar los niveles
    CajonDAO cajonDao = new CajonDAO();
      
    //metodo para establece los cajones por nivel
    private void mostrarCajonesUI(){
        /*como estamos actualizando los cajones disponibles
        cada 5 segundos necesitamos usar invokeLater para actualizar el UI
        */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
      // Here, we can safely update the GUI
      // because we'll be called from the
      // event dispatch thread
        int nivel = (Integer) modeloNiveles.getSelectedItem();//obtenemos el nivel que seleccione el usuario
        OyenteAcciones oyenteAcciones = new OyenteAcciones(panelCajones);//clase para efectuar una accion a cada cajon
        // se obtiene una lista con cajones del nivel seleccionado
        List<Cajon> cajonesNivel =  cajones.stream() // se convierte la lista a stream
                .filter(cajon -> cajon.getId_nivel() == nivel) //se filtran los cajones del nivel seleccionado
                .collect(Collectors.toList()); // se colectan en una lista
        //se obtiene el alto y ancho para un formato cercano a un cuadrado
        int alto = getAlto(cajonesNivel.size());
        int largo = getLargo(cajonesNivel.size(), alto);
        JButton[][] botones = new JButton[largo][alto];//se inicializa un arreglo de botones para los cajones
        // Se limpia el panel cada que cambiamos el nivel
        panelCajones.removeAll();
        panelCajones.revalidate();
        panelCajones.repaint();
       
        
        int counter = 0;
        //recorremos el arreglo de botones con un for anidado
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                //condicionar para no sobrepasaron de los niveles existentes (4)
                if(counter==cajonesNivel.size()){
                    break;
                }
                Cajon cajon = cajonesNivel.get(counter++);//objeto cajon, se le asigna un nivel
                botones[i][j] = new JButton();//se crean nuevos botones para dicho nivel
                botones[i][j].setPreferredSize(new Dimension(50, 50));//se les da un tamaño preferido
                // si el cajon esta libre se pone verde
                if(cajon.isEstado()){
                    botones[i][j].setBackground(Color.red);
                }
                else{
                    botones[i][j].setBackground(Color.green);
                }
                botones[i][j].setText(cajon.getCajon());//a cada cajon se pone en texto su nombre
                botones[i][j].addActionListener(oyenteAcciones);//a cada cajon se le asigna una accion
                panelCajones.add(botones[i][j]);//añadimos los botones al panel
            }
        }
        panelCajones.setLayout(new GridLayout(largo, alto));//diseño del panel
        }
    });
    }
    
    //metodo para obtener el alto de botones 
    private int getAlto(int length){
        //para darle un estilo cuadrado
        //se obtiene la raiz cuadrada del largo y se redondea hacia arriba
        int ancho = (int) Math.ceil(Math.sqrt(length));
        return ancho;
    }
    
    //metodo para obtener el largo de botones
    private int getLargo(int length, int alto){
        //para darle un estilo cuadrado
        int largo = alto-1;//el largo sera el alto menos 1
        
        //validamos si el largo por alto es menor que la cantidad de cajones
        //si es menor significa que nos falta añadir mas botones
        if(largo*alto < length ){
            largo = alto;//entonces dejamos el alto tal cual lo recibimos
        }
        return largo;
    }
    
    public CajonesFrame() {
        init();
    }
    
    private void init(){
        initComponents();
        
        /* Iniciamos el hilo que va estar revisando cada 5 segundos los cambios 
         de estado en los cajones para actualizar el UI */
        Thread queryThread = new Thread() {
            public void run() {
             do{   
              mostrarCajones();
                 try {
                     Thread.sleep(5000);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(CajonesFrame.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }while(true);
            }
          };
          queryThread.start();
    }
    /**
     * Este metodo se trae los datos de los cajones en la base de datos
     * y muestra los datos en la UI 
     */
    private void mostrarCajones(){
        this.cajones = cajonDao.getCajones();
        // solo debe correr la primera vez, cuando niveles es nulo
        // esto para no estar llenando la lista en cada llamada
        if(niveles == null){
            niveles = this.cajones.stream().// se convierte la lista a stream
                map(val -> val.getId_nivel()).distinct().//se filtran los valores de los cajones
                collect(Collectors.toList());//se colectan en una lista
            modeloNiveles.addAll(niveles);//se guardan los niveles en el modelo
            modeloNiveles.setSelectedItem(niveles.get(0));//se establece el modelo de acuerdo con el nivel elegido
        }
        mostrarCajonesUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        comboNiveles = new javax.swing.JComboBox<>();
        lblNivel = new javax.swing.JLabel();
        panelCajones = new javax.swing.JPanel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setMaximizable(true);
        setTitle("Ubicación de cajones");

        panel1.setBackground(new java.awt.Color(0, 102, 153));

        comboNiveles.setModel(modeloNiveles);
        comboNiveles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNivelesActionPerformed(evt);
            }
        });

        lblNivel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNivel.setForeground(new java.awt.Color(255, 255, 255));
        lblNivel.setText("Nivel:");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblNivel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboNiveles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(339, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboNiveles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNivel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCajones.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout panelCajonesLayout = new javax.swing.GroupLayout(panelCajones);
        panelCajones.setLayout(panelCajonesLayout);
        panelCajonesLayout.setHorizontalGroup(
            panelCajonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelCajonesLayout.setVerticalGroup(
            panelCajonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCajones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCajones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //evento de accion para mostrar los cajones al elegir un nivel del combo
    private void comboNivelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNivelesActionPerformed
        mostrarCajonesUI();
    }//GEN-LAST:event_comboNivelesActionPerformed
      
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CajonesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Integer> comboNiveles;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panelCajones;
    // End of variables declaration//GEN-END:variables
}
