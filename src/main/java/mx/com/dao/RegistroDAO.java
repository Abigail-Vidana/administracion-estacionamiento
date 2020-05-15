
package mx.com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.model.Registro;

/**
 *
 * @author abigail
 */

//clase para los query del registro de boletos que hereda de DAO, donde se crea la conexión
public class RegistroDAO extends DAO{

    public RegistroDAO() {
        super();
    }
    
    //Metodo para guardar un nuevo registro o alta de un auto
    public int registro(Registro registro) {

        int result = 0;
        
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO boleto (marca,modelo,placas,color,propietario,"
                + "entrada,id_usuario_entrada,id_cajon) VALUES (?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)
                ){
            ps.setString(1, registro.getMarca());
            ps.setString(2, registro.getModelo());
            ps.setString(3, registro.getPlacas());
            ps.setString(4, registro.getColor());
            ps.setString(5, registro.getPropietario());
            ps.setTimestamp(6, registro.getEntrada());
            ps.setInt(7, registro.getUsuarioEntrada());
            ps.setInt(8, registro.getCajon());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                result = rs.getInt(1);
            }
            else{
                result = -1;
                System.out.println("Error al registrar boleto");
            }
        } catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    //Metodo para actualizar un registro al dar una baja, se agregan los campos hora de salida y su total
    public int actualizar(Registro registro) {
        int filasModificadas = 0;
        try (PreparedStatement ps = conn.prepareStatement("UPDATE boleto SET salida=?,total=?, id_usuario_salida=? where id = ?")
                ){
            ps.setTimestamp(1, registro.getSalida());
            ps.setFloat(2, registro.getTotal());
            ps.setInt(3, registro.getUsuarioSalida());
            ps.setInt(4, registro.getId());
            
            filasModificadas = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return filasModificadas;
    }
    
    //metodo para obtener todos los boletos y mostrar su estado actual
    public List<Integer> todosLosBoletos(){
        getConexion();
        List <Integer> boletos = new ArrayList<>();
        
        try (PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT id FROM boleto")    
            ){
            ResultSet rst = ps.executeQuery();
                while (rst.next()) {
                    boletos.add(rst.getInt(1));
                }
            
        }catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return boletos;
    }
    
    //metodo para obtener los registros
     public Registro traerRegistro(int id) {

        getConexion();
        Registro registro = null;
        
        try (PreparedStatement ps = conn.prepareStatement("SELECT marca,modelo,placas,color,propietario,entrada,salida,"
                + "total,id_usuario_salida,id_usuario_entrada,id_cajon FROM boleto WHERE id = ?")){
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                registro = new Registro();
                registro.setId(id);
                registro.setMarca(rst.getString(1));
                registro.setModelo(rst.getString(2));
                registro.setPlacas(rst.getString(3));
                registro.setColor(rst.getString(4));
                registro.setPropietario(rst.getString(5));
                registro.setEntrada(rst.getTimestamp(6));
                registro.setSalida(rst.getTimestamp(7));
                registro.setTotal(rst.getFloat(8));
                registro.setUsuarioSalida(rst.getInt(9));
                registro.setUsuarioEntrada(rst.getInt(10));
                registro.setCajon(rst.getInt(11));
            }
        } catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return registro;
    }
}
