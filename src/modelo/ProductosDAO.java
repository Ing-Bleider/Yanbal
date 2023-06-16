
package modelo;

import com.sun.source.tree.TryTree;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProductosDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProducto(Productos pr){
        String sql = "INSERT INTO productos (codigo, nombre, precio) VALUES (?,?,?)";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getCodigo());
            ps.setString(2,pr.getNombre());
            ps.setBigDecimal(3, pr.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;          
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }        
    }
    
    public List ListarProductos(){
        List<Productos> ListaPr = new ArrayList();
        String sql = "SELECT * FROM productos";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos pr = new Productos();
                pr.setId(rs.getInt("id"));
                pr.setCodigo(rs.getInt("codigo"));
                pr.setNombre(rs.getString("nombre"));
                pr.setPrecio(rs.getBigDecimal("precio"));
                ListaPr.add(pr);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPr;
    }
    
    public boolean EliminarProducto(int id){
        String sql = "DELETE FROM productos WHERE id =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public boolean ActualizarProducto(Productos pr){
        String sql = "UPDATE productos SET codigo=?, nombre=?, precio=? WHERE id=?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getCodigo());
            ps.setString(2, pr.getNombre());
            ps.setBigDecimal(3, pr.getPrecio());
            ps.setInt(4, pr.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }    
    }   
    
    public Productos BuscarProductosXCodigo(int codigo){
        Productos producto = new Productos();
        String sql = "SELECT * FROM productos WHERE codigo =?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getBigDecimal("precio"));                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
    
    public Productos BuscarProductosXNombre(String nombre){
        Productos producto = new Productos();
        String sql = "SELECT * FROM productos WHERE nombre =?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setId(rs.getInt("id"));
                producto.setCodigo(rs.getInt("codigo"));
                producto.setPrecio(rs.getBigDecimal("precio"));                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
    
    
    
    
    
}
