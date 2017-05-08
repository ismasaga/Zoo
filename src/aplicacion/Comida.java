package aplicacion;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by miguel on 3/04/17.
 */
public class Comida {

    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty uds;
    private IntegerProperty stock;
    
    public Comida(Integer id) {
       this.id = new SimpleIntegerProperty(id); 
    }

    public Comida(Integer id, String nombre, String uds, Integer stock) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.uds = new SimpleStringProperty(uds);
        this.stock = new SimpleIntegerProperty(stock);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty udsProperty() {
        return uds;
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public Integer getStock() {
        return stock.get();
    }

    public void setStock(Integer stock) {
        this.stock.set(stock);
    }

    public String getUds() {
        return uds.get();
    }

    public void setUds(String uds) {
        this.uds.set(uds);
    }

}
