
package mx.com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.model.Usuario;

/**
 *
 * @author abigail
 */
//clase para los query de los usuarios que hereda de DAO, donde se crea la conexión
public class UsuarioDAO extends DAO{
    
    public UsuarioDAO(){
        super();
    }
    
    //Metodo para guardar un nuevo usuario
    public boolean registro(Usuario usuario) {

        boolean exito = false;
        
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO usuario (id,nombre,apellido,cuenta,"
                + "contrasena,puesto,sueldo) VALUES (?,?,?,?,?,?,?)")
                ){
            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getCuenta());
            ps.setString(5, usuario.getContrasena());
            ps.setString(6, usuario.getPuesto());
            ps.setFloat(7, usuario.getSueldo());
            ps.executeUpdate();

            exito = true;
        } catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return exito;
    }
    
    //Metodo para verificar que una cuenta de usuario existe al momento de iniciar sesión
    public Usuario usuarioExiste(String cuenta, String password) {
        getConexion();
        Usuario usuario = null;
        
        try (PreparedStatement ps = conn.prepareStatement("SELECT id,nombre,apellido,cuenta,puesto FROM usuario where cuenta = ? and contrasena = ?")){
            ps.setString(1, cuenta);
            ps.setString(2, password);
            
            ResultSet rst = ps.executeQuery();
            
            while (rst.next()) {
                usuario = new Usuario();
                usuario.setId(rst.getInt(1));
                usuario.setNombre(rst.getString(2));
                usuario.setApellido(rst.getString(3));
                usuario.setCuenta(rst.getString(4));
                usuario.setPuesto(rst.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return usuario;
    }
    
    /*Metodo para traer los registros: nombre, cantidad de servicios y sueldo de trabajadores
    se agrega un 10% extra al sueldo de los 5 que mas trabajaron en el periodo de tiempo que se recibe*/
    public List<Map<String, String>> registroUsuarioCantidadServicioSueldo(Date inicio, Date termino) {

        getConexion();
        List<Map<String, String>> resultado = new ArrayList<>();
        
        try (PreparedStatement ps = conn.prepareStatement(" select a.nombre, a.sueldo, sum(a.cantidad) " +
                " from (SELECT u.nombre, u.sueldo, count(e.entrada) cantidad " +
                " from usuario u " +
                " inner join boleto e on u.id = e.id_usuario_entrada " +
                " where e.entrada between ? and ? " +
                " group by u.nombre, u.sueldo " +
                " union all " +
                " SELECT u.nombre, u.sueldo, count(e.salida) cantidad " +
                " from usuario u " +
                " inner join boleto e on u.id = e.id_usuario_salida " +
                " where e.salida between ? and ? " +
                " group by u.nombre, u.sueldo) as a " +
                " group by a.nombre, a.sueldo " +
                " order by sum(a.cantidad) desc ")
            ){
                ps.setTimestamp(1, new Timestamp(inicio.getTime()));
                ps.setTimestamp(2, new Timestamp(termino.getTime()));
                ps.setTimestamp(3, new Timestamp(inicio.getTime()));
                ps.setTimestamp(4, new Timestamp(termino.getTime()));
               
                ResultSet rst = ps.executeQuery();
                int count=0;
                while (rst.next()) {
                    double sueldo= rst.getDouble(2);
                    if(count++ < 5){
                        sueldo += sueldo * .10;
                    }
                    Map<String, String> fila = new LinkedHashMap<>();
                    fila.put("Nombre trabajador", rst.getString(1));
                    fila.put("Cantidad de servicios", rst.getString(3));
                    fila.put("Sueldo neto", String.format("%.2f", sueldo));
                    resultado.add(fila);
                }
        } catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return resultado;
    }
    
    //metodo para obtener los usuarios
     public Usuario traerUsuarios(int id) {

        getConexion();
        Usuario usuario = null;
        
        try (PreparedStatement ps = conn.prepareStatement("SELECT nombre,apellido,cuenta,contrasena,"
                + " puesto,sueldo FROM usuario WHERE id = ?")){
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                usuario = new Usuario();
                usuario.setId(id);
                usuario.setNombre(rst.getString(1));
                usuario.setApellido(rst.getString(2));
                usuario.setCuenta(rst.getString(3));
                usuario.setContrasena(rst.getString(4));
                usuario.setPuesto(rst.getString(5));
                usuario.setSueldo(rst.getFloat(6));
            }
        } catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return usuario;
    }
    
    //Metodo para obtener las comisiones por usuario
    public Float obtenerTotalComisiones(int id, Date inicio, Date termino){
         
        getConexion();
        Usuario usuario = null;
        float comision = 0;
       /*
        Como tenemos 2 usuarios registrados en boleto, uno que da la alta y uno que da la baja,
        se divide el total entre dos para que el total del boleto sea la mitad de comision
        para el que da la alta y la otra para el que da la baja
        */
        try (PreparedStatement ps = conn.prepareStatement(" select sum(total)*.1 from ( "
                    + " select b.total/2 as total from boleto b inner join usuario u "
                    + " on u.id = b.id_usuario_entrada "
                    + " where u.id = ? and b.total is not null "
                    + " and entrada between ? and ? "
                    + " union all "
                    + " select b.total/2 as total from boleto b inner join usuario u "
                    + " on u.id = b.id_usuario_salida "
                    + " where u.id = ? and b.total is not null "
                    + " and salida between ? and ?) totales" )){
            ps.setInt(1, id); 
            ps.setTimestamp(2, new Timestamp(inicio.getTime()));
            ps.setTimestamp(3, new Timestamp(termino.getTime()));
            ps.setInt(4, id); 
            ps.setTimestamp(5, new Timestamp(inicio.getTime()));
            ps.setTimestamp(6, new Timestamp(termino.getTime()));
            
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                comision= rst.getFloat(1);
                usuario = new Usuario();
                usuario.setId(id); 
            }
        } catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } 
        return comision;
    }
    
    //metodo para obtener el saldo de un usuario
     public Float traerSueldoDeUsuario(int id) {

        getConexion();
        Usuario usuario = null;
        float sueldo = 0;
        
        try (PreparedStatement ps = conn.prepareStatement("SELECT sueldo FROM usuario WHERE id = ?")){
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                usuario = new Usuario();
                usuario.setId(id);
                sueldo = rst.getFloat(1);
            }
        } catch (SQLException e) {
            System.out.println("Error en sql: ");
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return sueldo;
    }
}