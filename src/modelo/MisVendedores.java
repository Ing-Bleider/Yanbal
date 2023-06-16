
package modelo;

import java.math.BigDecimal;
import java.sql.Date;


public class MisVendedores {
    
    private int id;
    private int codigoVendedor;
    private String nombreVendedor;
    private Date fecha;
    private BigDecimal total;

    public MisVendedores() {
    }

    public MisVendedores(int id, int codigoVendedor, String nombreVendedor, Date fecha, BigDecimal total) {
        this.id = id;
        this.codigoVendedor = codigoVendedor;
        this.nombreVendedor = nombreVendedor;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    
}
