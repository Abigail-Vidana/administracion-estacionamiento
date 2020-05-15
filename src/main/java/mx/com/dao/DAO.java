package mx.com.dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abigail
 */

//Data Access Object
public class DAO {
    //constructor de la clase, para llamar a la conexion
    public DAO(){
        getConexion();
    }
  
	Connection conn  = null;
        /**
         * Metodo usado para conectarse a la base de datos
         */
	public void getConexion() { 
            try {
                //cadena de conexion
                String url = "jdbc:mysql://localhost:3306/estacionamiento?useUnicode=true"
                        + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false"
                        + "&serverTimezone=America/Chihuahua";
                String user = "estacionamiento"; //usuario
                String password = "3staci0nami3nt0"; //contraseña
                Class.forName("com.mysql.cj.jdbc.Driver"); //registra el driver de la base de datos
                conn  = DriverManager.getConnection(url, user, password); //conexion a la BD
            } catch (SQLException ex) { //excepcion de sql, puede que ocurra algun problema con la base de datos
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) { //excepcion en caso de que el driver no exista
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

}
