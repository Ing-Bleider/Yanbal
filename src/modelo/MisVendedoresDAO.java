
package modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MisVendedoresDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    
    public int idMisVendedores(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM mis_vendedores";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }
    
    public int RegistrarDetalleMisVendedores(DetalleMisVendedores dmvr){
        String sql = "INSERT INTO detalle_mis_vendedores (codigo_producto, cantidad, precio, total, id_mis_vendedores) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dmvr.getCodigoProducto());
            ps.setInt(2, dmvr.getCantidad());
            ps.setDouble(3, dmvr.getPrecio());
            ps.setDouble(4, dmvr.getTotal());
            ps.setInt(5, dmvr.getIdMisVendedores());
            ps.execute();
            return r;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return r;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public int RegistrarVentaMisVendedores(MisVendedores mvr){
        String sql = "INSERT INTO mis_vendedores (codigo_vendedor, nombre_vendedor, fecha_limite, total) VALUES (?,?,?,?)";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, mvr.getCodigoVendedor());
            ps.setString(2, mvr.getNombreVendedor());
            ps.setDate(3, (Date) mvr.getFecha());
            ps.setDouble(4, mvr.getTotal());
            ps.execute();
            return r;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return r;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarVentaMisVendedores(){
        List <MisVendedores> ListaMvr = new ArrayList();
        String sql = "SELECT * FROM mis_vendedores";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MisVendedores mvr = new MisVendedores();
                mvr.setId(rs.getInt("id"));
                mvr.setNombreVendedor(rs.getString("nombre_vendedor"));
                mvr.setFecha(rs.getDate("fecha_limite"));
                mvr.setTotal(rs.getDouble("total"));
                ListaMvr.add(mvr);            
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return  ListaMvr;
        
    }
    
    public boolean EliminarVentasMisVendedores(int id){
        String sql = "DELETE FROM mis_vendedores WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public void EliminarTodasVentasMisVendedores(){
        String sqlElimminar = "DELETE FROM mis_vendedores";
        String sqlRestablecerId = "ALTER TABLE mis_vendedores AUTO_INCREMENT=1";
        try {
            ps = con.prepareStatement(sqlElimminar);
            ps.execute();
            ps = con.prepareStatement(sqlRestablecerId);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
               
    }
    
}
