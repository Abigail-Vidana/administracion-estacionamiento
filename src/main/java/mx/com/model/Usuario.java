
package mx.com.model;

/**
 *
 * @author abigail
 */
//Clase objeto Usuario
public class Usuario {
    
    //propiedades de Usuario
    private int id;
    private String nombre;
    private String apellido;
    private String cuenta;
    private String contrasena;
    private String puesto;

    //metodos get y set de las propiedades anteriores 
    //para acceder a ellas, tanto para darle como obtener valores

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
}
