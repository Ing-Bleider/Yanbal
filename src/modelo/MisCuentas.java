
package modelo;

import java.math.BigDecimal;



public class MisCuentas {
    
    private int id;
    private BigDecimal montoVenta;
    private BigDecimal pagoCampania;
    private BigDecimal recaudado;
    private BigDecimal totalGanancia;

    public MisCuentas() {
    }

    public MisCuentas(int id, BigDecimal montoVenta, BigDecimal pagoCampania, BigDecimal recaudado, BigDecimal totalGanancia) {
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

    public BigDecimal getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(BigDecimal montoVenta) {
        this.montoVenta = montoVenta;
    }

    public BigDecimal getPagoCampania() {
        return pagoCampania;
    }

    public void setPagoCampania(BigDecimal pagoCampania) {
        this.pagoCampania = pagoCampania;
    }

    public BigDecimal getRecaudado() {
        return recaudado;
    }

    public void setRecaudado(BigDecimal recaudado) {
        this.recaudado = recaudado;
    }

    public BigDecimal getTotalGanancia() {
        return totalGanancia;
    }

    public void setTotalGanancia(BigDecimal totalGanancia) {
        this.totalGanancia = totalGanancia;
    }

    
    
}
