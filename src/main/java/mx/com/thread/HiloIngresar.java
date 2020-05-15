package mx.com.thread;

import mx.com.object.IngresoFrame;

/**
 *
 * @author abigail
 */
//clase para especificar el Hilo para abrir la ventana del formulario de alta
public class HiloIngresar implements Runnable {

    //metodo sobreeescrito que se implementa de Runnable para correr el Hilo
    @Override
    public void run() {
        try{
            //se abre la ventana del formulario para la alta de auto
            IngresoFrame registroFrame = new IngresoFrame();
            registroFrame.setVisible(true);
            Thread.sleep(400);
        }catch(InterruptedException e){
            System.out.println("Hilo Interrumpido.");
        }
    }
    
}
