
package modelo;

public class DetalleMisVentas {
    
    private int id;
    private int codigoProducto;
    private int cantidad;
    private double precioFactura;
    private double precioVendido;
    private double total;
    private int idVenta;

    public DetalleMisVentas() {
    }

    public DetalleMisVentas(int id, int codigoProducto, int cantidad, double precioFactura, double precioVendido, double total, int idVenta) {
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

    public double getPrecioFactura() {
        return precioFactura;
    }

    public void setPrecioFactura(double precioFactura) {
        this.precioFactura = precioFactura;
    }

    public double getPrecioVendido() {
        return precioVendido;
    }

    public void setPrecioVendido(double precioVendido) {
        this.precioVendido = precioVendido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    
    
    
    
}
