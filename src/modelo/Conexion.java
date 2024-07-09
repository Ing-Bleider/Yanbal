
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection con;
    
    public Connection getConecction(){
        try {
            
//            Con xampp
            String myBD = "jdbc:mysql://localhost:3306/yanbal?serverTimezone=UTC";
            con = DriverManager.getConnection(myBD, "root", "");// sin contrasenia 
            
            
//            con = DriverManager.getConnection(myBD, "root", "123456");//123456
            
//           Con Workbench
//            String myBD = "jdbc:mysql://127.0.0.1:3306/yanbal?serverTimezone=UTC";
//            con = DriverManager.getConnection(myBD, "root", "root");
           
            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }
}
