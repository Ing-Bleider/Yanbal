
package modelo;

import java.math.BigDecimal;

public class DetalleMisVentas {
    
    private int id;
    private int codigoProducto;
    private int cantidad;
    private BigDecimal precioFactura;
    private BigDecimal precioVendido;
    private BigDecimal total;
    private int idVenta;

    public DetalleMisVentas() {
    }

    public DetalleMisVentas(int id, int codigoProducto, int cantidad, BigDecimal precioFactura, BigDecimal precioVendido, BigDecimal total, int idVenta) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.precioFactura = precioFactura;
        this.precioVendido = precioVendido;
        this.total = total;
        this.idVenta = idVenta;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioFactura() {
        return precioFactura;
    }

    public void setPrecioFactura(BigDecimal precioFactura) {
        this.precioFactura = precioFactura;
    }

    public BigDecimal getPrecioVendido() {
        return precioVendido;
    }

    public void setPrecioVendido(BigDecimal precioVendido) {
        this.precioVendido = precioVendido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

  
    
    
}
