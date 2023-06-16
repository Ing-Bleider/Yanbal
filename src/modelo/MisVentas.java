
package modelo;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class MisVentas {
    
    private int id;
    private int codigo_cliente;
    private String nombreCliente;
    private BigDecimal debe;
    private BigDecimal abonado;
    private BigDecimal total;
    public DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");


    public MisVentas() {
    }

    public MisVentas(int id, int codigo_cliente, String nombreCliente, BigDecimal debe, BigDecimal abonado, BigDecimal total) {
        this.id = id;
        this.codigo_cliente = codigo_cliente;
        this.nombreCliente = nombreCliente;
        this.debe = debe;
        this.abonado = abonado;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getAbonado() {
        return abonado;
    }

    public void setAbonado(BigDecimal abonado) {
        this.abonado = abonado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

   
    
}



