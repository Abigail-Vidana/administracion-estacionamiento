
package mx.com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.model.Cajon;

/**
 *
 * @author abigail
 */
//clase para los query de los cajones que hereda de DAO, donde se crea la conexión
public class CajonDAO extends DAO{
    
    public CajonDAO(){
        super();
    }
    
    //Metodo para obtener los cajones disponibles, se condiciona los que tienen el estado en false(disponible)
    public List<Cajon> getCajonesDisponibles(){
        getConexion();
        List <Cajon> cajones = new ArrayList<>();
        
        try (PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT id, descripcion,estado, id_nivel FROM cajon WHERE estado= false")    
            ){
            ResultSet rst = ps.executeQuery();
                while (rst.next()) {
                    Cajon cajon = new Cajon();
                    cajon.setId(rst.getInt(1));
                    cajon.setCajon(rst.getString(2));
                    cajon.setEstado(rst.getBoolean(3));
                    cajon.setId_nivel(rst.getInt(4));
                    cajones.add(cajon);
                }
            
        }catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(CajonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return cajones;
    }
    
    //metodo para obtener los cajones e identificar su nivel, nombre de cajon y numero
     public List<Cajon> getCajones(){
        getConexion();
        List <Cajon> cajones = new ArrayList<>();
        
        try (PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT id, descripcion,estado, id_nivel FROM cajon")    
            ){
            ResultSet rst = ps.executeQuery();
                while (rst.next()) {
                    Cajon cajon = new Cajon();
                    cajon.setId(rst.getInt(1));
                    cajon.setCajon(rst.getString(2));
                    cajon.setEstado(rst.getBoolean(3));
                    cajon.setId_nivel(rst.getInt(4));
                    cajones.add(cajon);
                }
            
        }catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(CajonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return cajones;
    }
    
     //metodo para actualizar el cajon al dar una baja o alta, su estado cambia a true o false segun corresponda
    public int actualizarCajon(Cajon cajon) {
        int filasModificadas = 0;
        try (PreparedStatement ps = conn.prepareStatement("UPDATE cajon SET estado=? WHERE id=? ")
                ){
            ps.setBoolean(1, cajon.isEstado());
            ps.setInt(2, cajon.getId());
            filasModificadas = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(CajonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return filasModificadas;
    }
}
