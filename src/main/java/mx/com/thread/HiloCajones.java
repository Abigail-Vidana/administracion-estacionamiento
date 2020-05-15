package mx.com.thread;

import java.util.List;
import mx.com.dao.CajonDAO;
import mx.com.model.Cajon;
import mx.com.object.CajonesFrame;

/**
 *
 * @author abigail
 */

//clase para especificar el Hilo para mostrar los cajones
public class HiloCajones implements Runnable{

    //metodo sobreeescrito que se implementa de Runnable para correr el Hilo
    @Override
    public void run() {
        try{
            CajonDAO cajonDao = new CajonDAO();//objeto para usar los query de cajon
            List<Cajon> cajones = cajonDao.getCajones();//se listan los cajones
            CajonesFrame cajonFrame = new CajonesFrame(cajones);//se abre la ventana que muestra los cajones
            cajonFrame.setVisible(true);
            Thread.sleep(400);
        }catch(InterruptedException e){
            System.out.println("Hilo Interrumpido.");
        }
    }
    
}
