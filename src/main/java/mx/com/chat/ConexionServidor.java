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
public class ConexionServidor extends Thread{//clase que crea la conexion del lado de servidor
    
    private ServerSocket serverSocket; //variable serversocket
    final int puerto = 8000;//puerto de enlace
    private boolean conectado = true; //variable para verificar la conexion
    List<Socket> sockets = new ArrayList<>();//lista de la clase para almacenar las conexiones 
    Usuario usuario = UtilidadSession.getInstance().getUsuario();//obtenemos el usuario que esta en sesion
    String nombreUsuario = String.valueOf(usuario.getNombre());//nombre del usuario en sesion
    
    public ConexionServidor(String nombre){
        super(nombre);
    }
    
    public void run(){//lo que va a correr el hilo
        
        try{    
            serverSocket = new ServerSocket(puerto);//se inicializa el server y se le manda el puerto
            List<ServerThread> threads = new ArrayList<>();//lista para almacenar los hilos de conexion de clientes
            int connectionCount = 0;//contador de numero de clientes
                //System.out.println("Servidor escuchando en puerto "+puerto);
            while(conectado){
                
                // La siguiente linea detiene la ejecucion del hilo hasta que
                // un cliente se conecta
                Socket socket = serverSocket.accept();
                // pone el mensaje de que alguien se conecto en el servidor
                ServidorFrame.txtVerMensaje.setText(ServidorFrame.txtVerMensaje.getText()+"\n"+"Nuevo Usuario Conectado\n"+ 
                        new Date(System.currentTimeMillis()));//se asigna la hora y fecha actual a la conexion
                // incrementa contador de los hilos de servidor
                connectionCount++;//aumenta uno porque hay un nuevo cliente
                
                sockets.add(socket);//se añade a la lista cada una de las nuevas conexiones
                for(ServerThread serverThread : threads){//se recorre la lista de conexiones
                    serverThread.updateSockets(sockets);//se manda actualizar el socket
                }
                ServerThread serverThread = new ServerThread(socket,connectionCount);//objeto de la clase serverThread
                serverThread.setSockets(sockets);//se establece la lista de sockets en un nuevo hilo cada uno
                threads.add(serverThread);//a la lista local le pasamos dichos hilos
                serverThread.start();//se inician los hilos para iniciar la conexion
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //metodo para el envio de mensajes
    public void enviarMensaje(String mensaje){
        if(!mensaje.equals(Constantes.CERRAR_SESION)){//se verifica que no este cerrada la sesion
            for(Socket socket: sockets){//se recorren los clientes (para mandarles a todos el mismo mensaje)
                try {
                    DataOutputStream salida = new DataOutputStream(socket.getOutputStream());//se obtiene el socket de salida
                    salida.writeChars(mensaje+"\n");//se envian los mensajes
                }catch (IOException ex) {
                    Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } 
        }else{//si se recibe un mensaje de cerrar sesion
            desconectarServidor();//se desconecta el servidor
            this.conectado = false;//colocamos como falsa la conexion para que no se corra en el hilo
        }
    }
    
    //metodo para desconectar la sesion desde el lado del servidor
    public void desconectarServidor(){
         for(Socket socket: sockets){//se recorren los clientes
                try {
                    DataOutputStream salida = new DataOutputStream(socket.getOutputStream());//se obtiene el socket de salida
                    
                    PrintWriter writer = new PrintWriter(salida, true);//va a mandar a todas las salidas
                    writer.println(Constantes.CERRAR_SESION);//el mensaje de cerrar sesion (y se deconecta el servidor)
                } catch (IOException ex) {
                    Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
      
    }
    
    /**
     * Este clase crea el hilo responsable de manejar la conexion con cada cliente
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
                this.salida.writeChars("¡Alguien se unio a la conversacion! \n");
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
    
    public void run() {//metodo que se corre al inicar el hilo
        try {
            InputStream input = socket.getInputStream();//variable que obtiene el socket de entrada 
            BufferedReader entrada = new BufferedReader(new InputStreamReader(input));//lee los datos de entrada
 
            OutputStream output = socket.getOutputStream();//variable que obtiene el socket de salida
            this.salida = new DataOutputStream(output);//envia los datos de salida
 
 
            String texto;//variable para almacenar los mensajes
 
            do {
                texto = entrada.readLine();//se alamacenan los mensajes
                if(texto != null){//si tenemos algo
                    mandarMensaje(texto);//se envia el texto
                    //en la ventana del servidor concatenamos los mensajes que ya tenemos mas este nuevo
                    ServidorFrame.txtVerMensaje.setText(ServidorFrame.txtVerMensaje.getText()+"\n"+texto);
                    Logger.getLogger(ConexionServidor.class.getName()).log(Level.INFO, texto);
                }
            } while (!texto.equals(Constantes.CERRAR_SESION) && !this.socket.isClosed());//se recoore mientras no se cierre la sesion
            
            //si algun cliente cierra su ventana (el evento cerrará el socket)
            if(!this.socket.isClosed()){
                this.socket.close();//se cierra su socket de conexion
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
