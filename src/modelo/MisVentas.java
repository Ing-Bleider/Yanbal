
package modelo;


public class MisVentas {
    
    private int id;
    private int codigo_cliente;
    private String nombreCliente;
    private double debe;
    private double abonado;
    private double total;

    public MisVentas() {
    }

    public MisVentas(int id, int codigo_cliente, String nombreCliente, double debe, double abonado, double total) {
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

    public double getDebe() {
        return debe;
    }

    public void setDebe(double debe) {
        this.debe = debe;
    }

    public double getAbonado() {
        return abonado;
    }

    public void setAbonado(double abonado) {
        this.abonado = abonado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    
}



