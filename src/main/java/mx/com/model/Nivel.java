package mx.com.model;

/**
 *
 * @author abigail
 */
//Clase objeto Nivel
public class Nivel {
    
    //propiedades del Nivel
    private int id;
    private String nivel;

    //metodos get y set de las propiedades anteriores 
    //para acceder a ellas, tanto para darle como obtener valores
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    
}
