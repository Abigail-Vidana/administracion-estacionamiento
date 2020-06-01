package mx.com.util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mx.com.dao.RegistroDAO;
import mx.com.model.Cajon;
import mx.com.model.Registro;

/**
 *
 * @author abigail
 */
//clase para el evento de accion de los botones (cada cajon)
public class OyenteAcciones implements ActionListener{
    
    private JPanel panel;//componente panel
    Registro registro;//objeto de Registro
    RegistroDAO registroDao = new RegistroDAO();//objeto para hacer uso de los query para los registros
    MontoPago pago; //objeto para obtener el total
    
    public OyenteAcciones(JPanel panel) {//constructor para almacenar el panel en esta clase
        this.panel = panel;
    }

    //evento de accion
    public void actionPerformed(ActionEvent e) {
           
        JButton boton = (JButton) e.getSource();// Se obtiene el recurso del boton pulsado
 
        //si el color del boton es rojo, es que esta ocupado
        if (boton.getBackground() == Color.red) {
            registro = registroDao.traerRegistroUsandoCajon(((Cajon)boton.getClientProperty("cajon")).getId());//traemos los registros de un auto de acuerdo con el id del cajon
            pago = new MontoPago(registro); //objeto de la clase que calcula el pago, se envia el objeto registro
            registro.setTotal(pago.obtenerTotal());//se obtiene el total actual
            //se muestra el monto total actual de pago del cajon 
            JOptionPane.showMessageDialog(panel, "CAJON OCUPADO\n Monto actual: "+registro.getTotal(),"Estado del cajon",
                JOptionPane.INFORMATION_MESSAGE);
        }else{
        //Si no, el color es verde y se informa que esta disponible
        JOptionPane.showMessageDialog(panel, "¡El cajon esta disponible!","Estado del cajon",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }   
}
