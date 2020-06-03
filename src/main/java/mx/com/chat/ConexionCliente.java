package mx.com.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.object.ClienteFrame;
import mx.com.util.Constantes;

/**
 *
 * @author abigail
 */
public class ConexionCliente extends Thread{
    
    private Socket socket;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto = 8000;
    public String text = "";
    
    public ConexionCliente(){}
    
    public ConexionCliente(String ip){
        try{
            this.socket = new Socket(ip, puerto);
            System.out.println("Cliente conetcado a "+ip+":"+puerto);
            //creacion de entrada de datos para la lectura de mensajes
            this.entradaSocket = new InputStreamReader(socket.getInputStream());
            this.entrada = new BufferedReader(entradaSocket);    
            //creacion de salida de datos para el envio de mensajes
            this.salida = new DataOutputStream(socket.getOutputStream());
        }catch(Exception e){
            e.printStackTrace();
        };
    }
    
    public void run(){
        boolean conectado = true;
        while(conectado){
            try{
                
                text = this.entrada.readLine();
                //System.out.println(""+this.socket.isInputShutdown());
                if (text == null && this.socket.isClosed() || text != null && text.equals(Constantes.CERRAR_SESION)){
                    conectado = false;
                    ClienteFrame.txtVerMensaje.setText(String.format("%s%n%s",ClienteFrame.txtVerMensaje.getText(), 
                            "El Servidor se cerro"));
                   
                    this.socket.close();
                }
                else{
                    ClienteFrame.txtVerMensaje.setText(String.format("%s%n%s",ClienteFrame.txtVerMensaje.getText(), text));
                }
                
            }catch(IOException e){
                 ClienteFrame.txtVerMensaje.setText(String.format("%s%n%s",ClienteFrame.txtVerMensaje.getText(), 
                            "El Servidor se cerro"));
                e.printStackTrace();
            };
        }
    }
    
    public void enviarMensaje(String mensaje){
        try{
            if(!mensaje.equals(Constantes.CERRAR_SESION)){
                salida = new DataOutputStream(socket.getOutputStream());
                salida.writeChars(mensaje+"\n");
            }else{
                this.socket.close();
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
     //metodo para recibir mensajes
    public String leerMensaje(){
        try{
            return entrada.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void desconectarCliente(){
        try {
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            //salida.writeChars(Constantes.CERRAR_SESION+"\n");

            PrintWriter writer = new PrintWriter(salida, true);
            writer.println(Constantes.CERRAR_SESION);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}