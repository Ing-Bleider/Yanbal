
package modelo;



public class MisCuentas {
    
    private int id;
    private double montoVenta;
    private double pagoCampania;
    private double recaudado;
    private double totalGanancia;

    public MisCuentas() {
    }

    public MisCuentas(int id, double montoVenta, double pagoCampania, double recaudado, double totalGanancia) {
        this.id = id;
        this.montoVenta = montoVenta;
        this.pagoCampania = pagoCampania;
        this.recaudado = recaudado;
        this.totalGanancia = totalGanancia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(double montoVenta) {
        this.montoVenta = montoVenta;
    }

    public double getPagoCampania() {
        return pagoCampania;
    }

    public void setPagoCampania(double pagoCampania) {
        this.pagoCampania = pagoCampania;
    }

    public double getRecaudado() {
        return recaudado;
    }

    public void setRecaudado(double recaudado) {
        this.recaudado = recaudado;
    }

    public double getTotalGanancia() {
        return totalGanancia;
    }

    public void setTotalGanancia(double totalGanancia) {
        this.totalGanancia = totalGanancia;
    }
    
    
    
}
