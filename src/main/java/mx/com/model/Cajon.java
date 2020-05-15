package mx.com.model;

/**
 *
 * @author abigail
 */
//Clase objeto Cajon
public class Cajon {
    
    //propiedades del Cajon
    private int id;
    private String cajon;
    private boolean estado;
    private int id_nivel;

    //metodos get y set de las propiedades anteriores 
    //para acceder a ellas, tanto para darle como obtener valores
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCajon() {
        return cajon;
    }

    public void setCajon(String cajon) {
        this.cajon = cajon;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }
    
    @Override
    public String toString(){
        return cajon;
    }
}
