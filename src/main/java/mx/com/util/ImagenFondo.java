package mx.com.util;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author abigail
 */
public class ImagenFondo implements Border{

    public Image fondo;
    
    public ImagenFondo(){
        try{
            fondo = new ImageIcon("src/main/resources/fondo.png").getImage(); //icono de la ventana
        }catch(Exception e){
            
        }
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
         g.drawImage(fondo,0,0,ancho,alto, null);
 
    }

    @Override
    public Insets getBorderInsets(Component arg0) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}