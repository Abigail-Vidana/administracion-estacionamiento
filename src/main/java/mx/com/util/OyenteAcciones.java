package mx.com.util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author abiga
 */
//clase para el evento de accion de los botones (cada cajon)
public class OyenteAcciones implements ActionListener{
    private JPanel panel;//componente panel

    public OyenteAcciones(JPanel panel) {//constructor para almacenar el panel en esta clase
        this.panel = panel;
    }

    //evento de accion
    public void actionPerformed(ActionEvent e) {
        // Se obtiene el color del boton pulsado
        JButton colorBoton = (JButton) e.getSource();
        String estado = "Disponible";//si el color es verde, es que esta disponible
        //si el color es rojo, es que esta ocupado
        if (colorBoton.getBackground() == Color.red) {
            estado = "Ocupado";
        }
        //al pulsar el boton (cajon) nos dirá si esta ocupado o disponible
        JOptionPane.showMessageDialog(panel, "El cajon se encuentra: " + estado,"Estado del cajon",
                JOptionPane.INFORMATION_MESSAGE);
    }   
}
