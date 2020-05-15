package mx.com.util;

import mx.com.model.Usuario;

/**
 *
 * @author abigail
 */

/*esta es una clase Singleton para crear un unico usuario en sesion 
(patron de diseño Singelton, asegura que una clase puede ser instanciada una sola vez, 
y provee de un acceso global a dicha instancia*/
public class UtilidadSession {
     
    private Usuario usuario;//objeto usuario
    private static UtilidadSession instancia; //instancia para el acceso global
  
    private UtilidadSession() { } 
  
    public static synchronized UtilidadSession getInstance() //un unico objeto se almacena en una variable estatica
    { 
        if (instancia == null) { 
            instancia = new UtilidadSession(); 
        } 
        return instancia; 
    } 

    //metodos get y set para tener acceso al objeto
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
   
}
