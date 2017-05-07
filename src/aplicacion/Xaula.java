package aplicacion;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Xaula {
    private IntegerProperty id;
    private IntegerProperty idArea;

    public Xaula(int id, int idArea) {
        this.id = new SimpleIntegerProperty(id);
        this.idArea = new SimpleIntegerProperty(idArea);
    }

    public IntegerProperty getIdProperty() {
        return id;
    }
    
    public int getId() {
        return id.get();
    }

    public void setIdProperty(IntegerProperty id) {
        this.id = id;
    }
    
    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty getIdAreaProperty() {
        return idArea;
    }
    
    public int getIdArea() {
        return idArea.get();
    }

    public void setIdAreaProperty(IntegerProperty idArea) {
        this.idArea = idArea;
    }
    
    public void setIdArea(int idArea) {
        this.idArea.set(idArea);
    }
}
