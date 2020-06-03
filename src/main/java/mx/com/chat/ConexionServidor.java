package mx.com.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.model.Usuario;
import mx.com.object.ServidorFrame;
import mx.com.util.Constantes;
import mx.com.util.UtilidadSession;

/**
 *
 * @author abigail
 */
public class ConexionServidor extends Thread{
    
    private ServerSocket serverSocket;
    final int puerto = 8000;
    private boolean conectado = true;
    List<Socket> sockets = new ArrayList<>();
    Usuario usuario = UtilidadSession.getInstance().getUsuario();//obtenemos el usuario que esta en sesion
    String nombreUsuario = String.valueOf(usuario.getNombre());
    
    public ConexionServidor(String nombre){
        super(nombre);
    }
    
    public void run(){
        
        try{    
            serverSocket = new ServerSocket(puerto);
            List<ServerThread> threads = new ArrayList<>();
            int connectionCount = 0;
                System.out.println("Servidor escuchando en puerto "+puerto);
            while(conectado){
                
                // La siguiente linea detiene la ejecucion del hilo hasta que
                // un cliente se conecta
                Socket socket = serverSocket.accept();
                // pone el mensaje de que alguien se conecto en el servidor
                ServidorFrame.txtVerMensaje.setText(ServidorFrame.txtVerMensaje.getText()+"\n"+"Nuevo Usuario Conectado\n"+ 
                        new Date(System.currentTimeMillis()));
                // incrementa contador de los hilos de servidor
                connectionCount++;
                
                sockets.add(socket);
                for(ServerThread serverThread : threads){
                    serverThread.updateSockets(sockets);
                }
                ServerThread serverThread = new ServerThread(socket,connectionCount);
                serverThread.setSockets(sockets);
                threads.add(serverThread);
                serverThread.start();
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //metodo para el envio de mensajes
    public void enviarMensaje(String mensaje){
        if(!mensaje.equals(Constantes.CERRAR_SESION)){
            for(Socket socket: sockets){
                try {
                    DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                    salida.writeChars(mensaje+"\n");
                }catch (IOException ex) {
                    Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } 
        }else{
            desconectarServidor();
            this.conectado = false;
        }
    }
    
    public void desconectarServidor(){
         for(Socket socket: sockets){
                try {
                    DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                    //salida.writeChars(Constantes.CERRAR_SESION+"\n");
                    
                    PrintWriter writer = new PrintWriter(salida, true);
                    writer.println(Constantes.CERRAR_SESION);
                } catch (IOException ex) {
                    Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
      
    }
    
    /**
     * Este hilo es responsable de manejar la conexion con cada cliente
     */
    class ServerThread extends Thread{
        private Socket socket;
        // contiene los sockets de los otros clientes para poder mandarles mensajes
        private List<Socket> sockets;
        private int id;
        //Mapa con las salidas de los otros sockets
        private Map<Integer,DataOutputStream> salidas = new HashMap<>();
        private DataOutputStream salida;
 
    public ServerThread(Socket socket,int id) {
        this.socket = socket;
        this.id = id;
    }
    
    /**
     * Este metodo se usa para agregar los sockets que ya existian
     * cuando este fue creado
     * @param sockets 
     */
    public void setSockets(List<Socket> sockets){
        this.sockets = sockets;
        /**
         * Se iteran todos los sockets para crear las salidas
         * que se van a usar para mandar mensajes a los otros clientes
         */
        for(int i = 0; i< sockets.size(); i++){
            Socket socket = sockets.get(i);
            DataOutputStream salida = null;
            try {
                salida = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
            } 
            salidas.put(i, salida);
        }
    }
    
    /**
     * Se utiliza para actualizar la lista de sockets cada que se crea una 
     * nueva conexion
     * @param sockets 
     */
    public void updateSockets(List<Socket> sockets){
        setSockets(sockets);
        if(this.salida != null){
            try{
                this.salida.writeChars("Alguien se unio \n");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Mande mensajes a todos los sockets
     * @param mensaje 
     */
    private void mandarMensaje(String mensaje){
         for(Integer key: salidas.keySet()){
            try{
                salidas.get(key).writeChars(mensaje+"\n");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            this.salida = new DataOutputStream(output);
 
 
            String texto;
 
            do {
                texto = entrada.readLine();
                if(texto != null){
                    mandarMensaje(texto);
                    ServidorFrame.txtVerMensaje.setText(ServidorFrame.txtVerMensaje.getText()+"\n"+texto);
                    Logger.getLogger(ConexionServidor.class.getName()).log(Level.INFO, texto);
                }
            } while (!texto.equals(Constantes.CERRAR_SESION) && !this.socket.isClosed());
            
            if(!this.socket.isClosed()){
                this.socket.close();
            }
        } catch (SocketException e){
            if(e.getMessage().equals("Socket closed")){
                Logger.getLogger(ConexionServidor.class.getName()).log(Level.INFO, "Se Cerro la conexion");
            }
            else{
                Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, e.getMessage(),e);
            }
        } 
        catch (IOException e) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, "Server exception: " + e.getMessage(),e);
        }  
    }
    }
}
