package aplicacion;

import java.util.ArrayList;

/**
 * Created by miguel on 3/04/17.
 */

public class Comida {
    private int id; 
    private String nombre;
    private String uds; 
    private Integer stock;

    public Comida(int id, String nombre, String uds, Integer stock) {
        this.id = id; 
        this.nombre = nombre;
        this.uds = uds; 
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getUds() {
        return uds;
    }

    public void setUds(String uds) {
        this.uds = uds;
    }
}
