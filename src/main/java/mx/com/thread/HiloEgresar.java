package mx.com.thread;

import mx.com.object.EgresoFrame;

/**
 *
 * @author abigail
 */
//clase para especificar el Hilo para abrir la ventana del formulario de baja
public class HiloEgresar implements Runnable{

    //metodo sobreeescrito que se implementa de Runnable para correr el Hilo
    @Override
    public void run() {
        try{
            //se abre la ventana del formulario para la baja de auto
            EgresoFrame registroFrame = new EgresoFrame();
            registroFrame.setVisible(true);
            Thread.sleep(400);
        }catch(InterruptedException e){
            System.out.println("Hilo Interrumpido.");
        }
    }
    
}
