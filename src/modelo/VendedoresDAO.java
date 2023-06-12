
package modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class VendedoresDAO {
    
    Conexion cn = new Conexion();
    Connection con; 
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarVendedor(Vendedores ve){
        String sql = "INSERT INTO vendedores (codigo, nombre, direccion, telefono) VALUES (?,?,?,?)";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ve.getCodigo());
            ps.setString(2, ve.getNombre());
            ps.setString(3, ve.getDireccion());
            ps.setString(4, ve.getTelefono());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    
    public List ListarVendedores(){
        List<Vendedores> ListaVe = new ArrayList();
        String sql = "SELECT * FROM vendedores";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vendedores ve = new Vendedores();
                ve.setId(rs.getInt("id"));
                ve.setCodigo(rs.getInt("codigo"));
                ve.setNombre(rs.getString("nombre"));
                ve.setDireccion(rs.getString("direccion"));
                ve.setTelefono(rs.getString("telefono"));
                ListaVe.add(ve);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaVe;
    }
    
    public boolean EliminarVendedor(int id){
        String sql = "DELETE FROM   vendedores WHERE id=?";
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
    
    public boolean ActualizarVendedor(Vendedores ve){
        String sql = "UPDATE vendedores SET codigo=?, nombre=?, direccion=?, telefono=? WHERE id =?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ve.getCodigo());
            ps.setString(2, ve.getNombre());
            ps.setString(3,ve.getDireccion());
            ps.setString(4,ve.getTelefono());
            ps.setInt(5,ve.getId());
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
    
    public Vendedores BuscarVendedores(int codigo){
        Vendedores vendedor = new Vendedores();
        String sql = "SELECT * FROM vendedores WHERE codigo =?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                vendedor.setId(rs.getInt("id"));
                vendedor.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return vendedor;
    }
    
    
    
}
