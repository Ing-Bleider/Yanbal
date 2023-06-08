
package modelo;

import java.sql.Date;


public class MisVendedores {
    
    private int id;
    private int codigoVendedor;
    private String nombreVendedor;
    private Date fecha;
    private double total;

    public MisVendedores() {
    }

    public MisVendedores(int id, int codigoVendedor, String nombreVendedor, Date fecha, double total) {
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

    public void setFecha(java.util.Date fecha) { // Cambio realizado aquí
        this.fecha = new java.sql.Date(fecha.getTime());
    }

    public java.sql.Date getFecha() { // Cambio realizado aquí
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
