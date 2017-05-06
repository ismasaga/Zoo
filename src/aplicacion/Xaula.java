package aplicacion;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by miguel on 6/05/17.
 */
public class Xaula {
    private IntegerProperty id;

    public Xaula(Integer id, String climatizacion) {
        this.id = new SimpleIntegerProperty(id);
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
}
