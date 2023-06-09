
package modelo;

import java.math.BigDecimal;

public class DetalleMisVendedores {
    
    private int id;
    private int codigoProducto;
    private String nombreProducto;
    private int cantidad;
    private BigDecimal precio;
    private BigDecimal total;
    private int idMisVendedores;

    public DetalleMisVendedores() {
    }

    public DetalleMisVendedores(int id, int codigoProducto, String nombreProducto, int cantidad, BigDecimal precio, BigDecimal total, int idMisVendedores) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.idMisVendedores = idMisVendedores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getIdMisVendedores() {
        return idMisVendedores;
    }

    public void setIdMisVendedores(int idMisVendedores) {
        this.idMisVendedores = idMisVendedores;
    }

    
    
}
