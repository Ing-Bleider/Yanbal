
package modelo;

import java.math.BigDecimal;
import java.text.DecimalFormat;



public class Productos {
    public DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
    private int id;
    private int codigo;
    private String nombre;
    private BigDecimal precio;

    public Productos() {
    }

    public Productos(int id, int codigo, String nombre, BigDecimal precio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    
}
