package aplicacion;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by miguel on 3/04/17.
 */
public class Animal {
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty especie;
    private IntegerProperty edad;
    private IntegerProperty peso;
    private StringProperty sexo;
    private IntegerProperty area;
    private IntegerProperty xaula;
    private StringProperty idCoidador;

    public Animal(Integer id, String nombre) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
    }

    public Animal(Integer id, String nombre, String especie, Integer edad, Integer peso, String sexo, Integer area, Integer xaula) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.especie = new SimpleStringProperty(especie);
        this.edad = new SimpleIntegerProperty(edad);
        this.peso = new SimpleIntegerProperty(peso);
        this.sexo = new SimpleStringProperty(sexo);
        this.area = new SimpleIntegerProperty(area);
        this.xaula = new SimpleIntegerProperty(xaula);
    }

    //
    public Animal(Integer id, String nombre, String especie, Integer edad, Integer peso, String sexo, Integer area, Integer xaula, String idCoidador) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.especie = new SimpleStringProperty(especie);
        this.edad = new SimpleIntegerProperty(edad);
        this.peso = new SimpleIntegerProperty(peso);
        this.sexo = new SimpleStringProperty(sexo);
        this.area = new SimpleIntegerProperty(area);
        this.xaula = new SimpleIntegerProperty(xaula);
        this.idCoidador = new SimpleStringProperty(idCoidador);
    }

    public int getPeso() {
        return peso.get();
    }

    public void setPeso(int peso) {
        this.peso.set(peso);
    }

    public IntegerProperty pesoProperty() {
        return peso;
    }

    public String getSexo() {
        return sexo.get();
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public int getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
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

    public int getArea() {
        return area.get();
    }

    public void setArea(Integer area) {
        this.area.set(area);
    }

    public IntegerProperty areaProperty() {
        return area;
    }

    public int getXaula() {
        return xaula.get();
    }

    public void setXaula(Integer xaula) {
        this.xaula.set(xaula);
    }

    public IntegerProperty xaulaProperty() {
        return xaula;
    }

    public String getIdCoidador() {
        return idCoidador.get();
    }

    public void setIdCoidador(String idCoidador) {
        this.idCoidador.set(idCoidador);
    }

    public StringProperty idCoidadorProperty() {
        return idCoidador;
    }
}