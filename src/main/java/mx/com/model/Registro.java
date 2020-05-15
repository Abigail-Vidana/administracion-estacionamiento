
package mx.com.model;

import java.sql.Timestamp;
/**
 *
 * @author abigail
 */
//Clase objeto Registro
public class Registro {
    
    //propiedades de Registro
    private int id;
    private String marca;
    private String modelo;
    private String placas;
    private String color;
    private String propietario;
    private Timestamp entrada;
    private Timestamp salida;
    private float total;
    private int usuario_entrada;
    private int usuario_salida;
    private int cajon;

    //metodos get y set de las propiedades anteriores 
    //para acceder a ellas, tanto para darle como obtener valores
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public Timestamp getEntrada() {
        return entrada;
    }

    public void setEntrada(Timestamp entrada) {
        this.entrada = entrada;
    }

    public Timestamp getSalida() {
        return salida;
    }

    public void setSalida(Timestamp salida) {
        this.salida = salida;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getUsuarioEntrada() {
        return usuario_entrada;
    }

    public void setUsuarioEntrada(int usuario_entrada) {
        this.usuario_entrada = usuario_entrada;
    }
    
     public int getUsuarioSalida() {
        return usuario_salida;
    }

    public void setUsuarioSalida(int usuario_salida) {
        this.usuario_salida = usuario_salida;
    }

    public int getCajon() {
        return cajon;
    }

    public void setCajon(int cajon) {
        this.cajon = cajon;
    }
    
}
