package aplicacion;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by miguel on 6/05/17.
 */
public class Area {
    private IntegerProperty id;
    private StringProperty climatizacion;

    public Area(Integer id, String climatizacion) {
        this.id = new SimpleIntegerProperty(id);
        this.climatizacion = new SimpleStringProperty(climatizacion);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getClimatizacion() {
        return climatizacion.get();
    }

    public void setClimatizacion(String climatizacion) {
        this.climatizacion.set(climatizacion);
    }

    public StringProperty climatizacionProperty() {
        return climatizacion;
    }
}
