
package modelo;

import java.math.BigDecimal;
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
    
    public int idMaxMisVentas(){
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
    
    public int idMaxDetalleMisVentas(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM detalle_mis_ventas";
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
            ps.setBigDecimal(3, mv.getDebe());
            ps.setBigDecimal(4, mv.getAbonado());
            ps.setBigDecimal(5, mv.getTotal());
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
            ps.setBigDecimal(3, dmv.getPrecioFactura());
            ps.setBigDecimal(4, dmv.getPrecioVendido());
            ps.setBigDecimal(5, dmv.getTotal());
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
                mv.setDebe(rs.getBigDecimal("debe"));
                mv.setAbonado(rs.getBigDecimal("abonado"));
                mv.setTotal(rs.getBigDecimal("total"));
                ListaMv.add(mv);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaMv;
    }
    
    public boolean ActualizarMisVentas(BigDecimal debe, BigDecimal abonar, int id){
        String sql = "UPDATE mis_ventas SET debe=?, abonado=? WHERE id=?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setBigDecimal(1, debe);
            ps.setBigDecimal(2, abonar);
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
            ps.setBigDecimal(1, mc.getMontoVenta());
            ps.setBigDecimal(2, mc.getPagoCampania());
            ps.setBigDecimal(3, mc.getRecaudado());
            ps.setBigDecimal(4, mc.getTotalGanancia());
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
    
    
    
    
    public BigDecimal MontoVentaMisCuentas(){
        BigDecimal monto = BigDecimal.ZERO; // monto = 0.00
        String sql = "SELECT total FROM mis_ventas";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                BigDecimal total = rs.getBigDecimal("total");
                monto = monto.add(total); // monto += total
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return monto;
    }
    
    
    public BigDecimal RecaudoMisCuentas(){
        BigDecimal abonado = BigDecimal.ZERO;
        String sql = "SELECT abonado FROM mis_ventas";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                
                BigDecimal total = rs.getBigDecimal("abonado");
                abonado  = abonado.add(total);
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
            ps.setBigDecimal(1, mc.getMontoVenta());
            ps.setBigDecimal(2, mc.getPagoCampania());
            ps.setBigDecimal(3, mc.getRecaudado());
            ps.setBigDecimal(4, mc.getTotalGanancia());
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
    
    public BigDecimal PagoCampanialMisCuentas(){
        BigDecimal pagCamp = BigDecimal.ZERO;
        String sql = "SELECT pago_campania FROM mis_cuentas ORDER BY pago_campania DESC LIMIT 1";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                pagCamp = rs.getBigDecimal(1);
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
                dmVentas.setPrecioFactura(rs.getBigDecimal("precio_factura"));
                dmVentas.setPrecioVendido(rs.getBigDecimal("precio_vendido"));
                dmVentas.setTotal(rs.getBigDecimal("total"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return dmVentas;
    }
    
    public DetalleMisVentas BuscarIdMisVentas(int idVenta){
        DetalleMisVentas dmVentas = new DetalleMisVentas();
        String sql = "SELECT * FROM detalle_mis_ventas WHERE id =?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            rs = ps.executeQuery();
            if (rs.next()) {
              //  dmVentas.setId(rs.getInt("id"));
                dmVentas.setCodigoProducto(rs.getInt("codigo_producto"));
                dmVentas.setCantidad(rs.getInt("cantidad"));
                dmVentas.setPrecioFactura(rs.getBigDecimal("precio_factura"));
                dmVentas.setPrecioVendido(rs.getBigDecimal("precio_vendido"));
                dmVentas.setTotal(rs.getBigDecimal("total"));
                dmVentas.setIdVenta(rs.getInt("id_mis_ventas"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return dmVentas;
    }
    
    public MisVentas BuscarIdRegistroMisVentas(int idVenta){
        MisVentas rmVentas = new MisVentas();
        String sql = "SELECT * FROM mis_ventas WHERE id =?";
        try {
            con = cn.getConecction();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            rs = ps.executeQuery();
            if (rs.next()) {
                rmVentas.setCodigo_cliente(rs.getInt("codigo_cliente"));
                rmVentas.setDebe(rs.getBigDecimal("debe"));
                rmVentas.setAbonado(rs.getBigDecimal("abonado"));
                rmVentas.setTotal(rs.getBigDecimal("total"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return rmVentas;
    }
}
