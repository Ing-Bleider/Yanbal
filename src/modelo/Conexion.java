
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection con;
    
    public Connection getConecction(){
        try {
            String myBD = "jdbc:mysql://localhost:3306/yanbal?serverTimezone=UTC";
           con = DriverManager.getConnection(myBD, "root", "123456");//123456
            //con = DriverManager.getConnection(myBD, "root", "");// sin contrasenia 
            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }
}
