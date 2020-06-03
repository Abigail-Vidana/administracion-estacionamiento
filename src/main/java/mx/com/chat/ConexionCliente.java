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
public class ConexionCliente extends Thread{//clase que crea la conexion del lado de cliente
    
    private Socket socket; //variable el socket
    private InputStreamReader entradaSocket;//variable para la entrada
    private DataOutputStream salida;//variable para la salida
    private BufferedReader entrada;//variable para leer los datos de salida
    final int puerto = 8000;//se especifica el puerto de enlace
    //public String text = "";
    
    public ConexionCliente(){}
    
    //constructor para la alta de objetos
    public ConexionCliente(String ip){
        try{
            this.socket = new Socket(ip, puerto);//se especifica la ip y el puerto de enlace para conectarse al servidor
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
    
    public void run(){//lo que va a correr el hilo
        boolean conectado = true;//verifica la conexion 
        while(conectado){//mientras este conectado el cliente
            try{
                String text;//variable para almacenar los mensajes
                text = this.entrada.readLine();//se almacenan los mensajes
                //System.out.println(""+this.socket.isInputShutdown());
                
                //si no hay mensajes y el socket esta cerrado o si no hay mensjaes y se cerro la sesion
                if (text == null && this.socket.isClosed() || text != null && text.equals(Constantes.CERRAR_SESION)){
                    conectado = false;//colocamos la conexion como falsa
                    //se le informa al cliente que el servidor se cerro
                    ClienteFrame.txtVerMensaje.setText(String.format("%s%n%s",ClienteFrame.txtVerMensaje.getText(), 
                            "El Servidor se cerro"));
                   
                    this.socket.close();//se cierra el socket
                }else{
                    //si no, enviamos el mensaje a todos los clientes
                    ClienteFrame.txtVerMensaje.setText(String.format("%s%n%s",ClienteFrame.txtVerMensaje.getText(), text));
                }
                
            }catch(IOException e){
                 ClienteFrame.txtVerMensaje.setText(String.format("%s%n%s",ClienteFrame.txtVerMensaje.getText(), 
                            "El Servidor se cerro"));
                e.printStackTrace();
            };
        }
    }
    
    //metodo que realiza el envio de mensajes
    public void enviarMensaje(String mensaje){//recibe el mensaje por enviar
        try{
            if(!mensaje.equals(Constantes.CERRAR_SESION)){//si no esta cerrada la sesion
                salida = new DataOutputStream(socket.getOutputStream());//se obtiene el socket de salida
                salida.writeChars(mensaje+"\n");//escribe el mensaje
            }else{//si no, entonces se recibio el mensaje de cerrar sesion
                this.socket.close();//y se cierra el socket
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
     //metodo para recibir mensajes
    public String leerMensaje(){
        try{
            return entrada.readLine();//se retonra el mensaje que llega
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    
    //metodo para cerrar la sesion desde el lado del cliente
    public void desconectarCliente(){
        try {
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());//se obtiene el socket de salida

            PrintWriter writer = new PrintWriter(salida, true);//va a enviar a la salida del cliente que cerro su ventana
            writer.println("Un usuario cerro sesion");            
            //writer.println(Constantes.CERRAR_SESION);//el mensaje de que cerro sesion
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