
package modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MisVentasDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int idMisVentas(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM mis_ventas";
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
    
    public int RegistrarMisVentas(MisVentas mv){
        String sql = "INSERT INTO mis_ventas (codigo_cliente, nombre_cliente, debe, abonado, total) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, mv.getCodigo_cliente());
            ps.setString(2, mv.getNombreCliente());
            ps.setDouble(3, mv.getDebe());
            ps.setDouble(4, mv.getAbonado());
            ps.setDouble(5, mv.getTotal());
            ps.execute();
            return r;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return r;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public int RegistrarDetalleMisVentas(DetalleMisVentas dmv){
        String sql = "INSERT INTO detalle_mis_ventas (codigo_producto, cantidad, precio_factura, precio_vendido, total, id_mis_ventas) VALUES (?,?,?,?,?,?)";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dmv.getCodigoProducto());
            ps.setInt(2, dmv.getCantidad());
            ps.setDouble(3, dmv.getPrecioFactura());
            ps.setDouble(4, dmv.getPrecioVendido());
            ps.setDouble(5, dmv.getTotal());
            ps.setInt(6, dmv.getIdVenta());
            ps.execute();
            return r;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return r;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public List ListarMisVentas(){
        List<MisVentas> ListaMv = new ArrayList();
        String sql = "SELECT * FROM mis_ventas";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MisVentas mv = new MisVentas();
                mv.setId(rs.getInt("id"));
                mv.setCodigo_cliente(rs.getInt("codigo_cliente"));
                mv.setNombreCliente(rs.getString("nombre_cliente"));
                mv.setDebe(rs.getDouble("debe"));
                mv.setAbonado(rs.getDouble("abonado"));
                mv.setTotal(rs.getDouble("total"));
                ListaMv.add(mv);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaMv;
    }
    
    public boolean ActualizarMisVentas(double debe, double abonar, int id){
        String sql = "UPDATE mis_ventas SET debe=?, abonado=? WHERE id=?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, debe);
            ps.setDouble(2, abonar);
            ps.setInt(3, id);
            
            ps.executeUpdate(); 
            //con.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean EliminarMisVentas(int id){
        String sql = "DELETE FROM mis_ventas WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } /*finally{
            try {
               // con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }*/
    }
    
    
    public boolean EliminarDetalleMisVentas(int id){
        String sql = "DELETE FROM detalle_mis_ventas WHERE id_mis_ventas=?";
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
    
    public void EliminarTodasMisVentas(){
        String sqlElimminar = "DELETE FROM mis_ventas";
        String sqlRestablecerId = "ALTER TABLE mis_ventas AUTO_INCREMENT=1";
        try {
            ps = con.prepareStatement(sqlElimminar);
            ps.execute();
            ps = con.prepareStatement(sqlRestablecerId);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } /*finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }   */            
    }
    
    public void EliminarTodoDetalleMisVentas(){
        String sqlElimminar = "DELETE FROM detalle_mis_ventas";
        String sqlRestablecerId = "ALTER TABLE detalle_mis_ventas AUTO_INCREMENT=1";
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
    
    public boolean RegistrarMisCuentas(MisCuentas mc) {
        String sql = "INSERT INTO mis_cuentas (monto_venta, pago_campania, recaudado, ganancia_total) VALUES (?,?,?,?)";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, mc.getMontoVenta());
            ps.setDouble(2, mc.getPagoCampania());
            ps.setDouble(3, mc.getRecaudado());
            ps.setDouble(4, mc.getTotalGanancia());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    
    public double MontoVentaMisCuentas(){
        double monto = 0;
        String sql = "SELECT total FROM mis_ventas";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                double total = rs.getDouble("total");
                monto += total;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return monto;
    }
    
    
    public double RecaudoMisCuentas(){
        double abonado = 0;
        String sql = "SELECT abonado FROM mis_ventas";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                double total = rs.getDouble("abonado");
                abonado += total;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return abonado;
    }
    
    public boolean ActualizarMisCuentas(MisCuentas mc) {
        String sql = "UPDATE mis_cuentas SET monto_venta=?, pago_campania=?, recaudado=?, ganancia_total=? WHERE id=?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, mc.getMontoVenta());
            ps.setDouble(2, mc.getPagoCampania());
            ps.setDouble(3, mc.getRecaudado());
            ps.setDouble(4, mc.getTotalGanancia());
            ps.setInt(5, mc.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public int idMisCuentas(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM mis_cuentas";
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
    
    public double PagoCampanialMisCuentas(){
        double pagCamp = 0;
        String sql = "SELECT MAX(pago_campania) FROM mis_cuentas";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                pagCamp = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pagCamp;
    }
    
    public DetalleMisVentas BuscarIdDetalleMisVentas(int idVenta){
        DetalleMisVentas dmVentas = new DetalleMisVentas();
        String sql = "SELECT * FROM detalle_mis_ventas WHERE id_mis_ventas =?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            rs = ps.executeQuery();
            if (rs.next()) {
              //  dmVentas.setId(rs.getInt("id"));
                dmVentas.setCodigoProducto(rs.getInt("codigo_producto"));
                dmVentas.setCantidad(rs.getInt("cantidad"));
                dmVentas.setPrecioFactura(rs.getDouble("precio_factura"));
                dmVentas.setPrecioVendido(rs.getDouble("precio_vendido"));
                dmVentas.setTotal(rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return dmVentas;
    }
    
    public MisVentas BuscarIdRegistroMisVentas(int idVenta){
        MisVentas dmVentas = new MisVentas();
        String sql = "SELECT * FROM mis_ventas WHERE id =?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            rs = ps.executeQuery();
            if (rs.next()) {
                dmVentas.setCodigo_cliente(rs.getInt("codigo_cliente"));
                dmVentas.setDebe(rs.getDouble("debe"));
                dmVentas.setAbonado(rs.getDouble("abonado"));
                dmVentas.setTotal(rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return dmVentas;
    }
}
