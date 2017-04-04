package aplicacion;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

/**
 * Created by miguel on 3/04/17.
 */
public class Animal {
    private StringProperty id;
    private StringProperty nombre;
    private StringProperty especie;
    private IntegerProperty edad;
    private ArrayList<Comida> comidas;

    public Animal(String id, String nombre, String especie, Integer edad, ArrayList<Comida> comidas) {
        this.id = new SimpleStringProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.especie = new SimpleStringProperty(especie);
        this.edad = new SimpleIntegerProperty(edad);
        this.comidas = comidas;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getEspecie() {
        return especie.get();
    }

    public void setEspecie(String especie) {
        this.especie.set(especie);
    }

    public StringProperty especieProperty() {
        return especie;
    }

    public int getEdad() {
        return edad.get();
    }

    public void setEdad(int edad) {
        this.edad.set(edad);
    }

    public IntegerProperty edadProperty() {
        return edad;
    }

    public ArrayList<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(ArrayList<Comida> comidas) {
        this.comidas = comidas;
    }
}
