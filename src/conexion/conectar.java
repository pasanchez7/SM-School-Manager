
package conexion;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class conectar {
    
     public static Statement st=null;
     public static ResultSet rt=null;
     Connection conectar=null;

public Connection conexion (){

    try {
         Class.forName("com.mysql.jdbc.Driver");
         conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Error al crear coneccion: "+e.getMessage());
    }
return conectar;
}
}
