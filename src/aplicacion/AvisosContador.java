package aplicacion;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AvisosContador {
    private StringProperty animalDescrito;
    private StringProperty xaulaDescrita;
    private StringProperty areaDescrita;
    private IntegerProperty abertos;
    private IntegerProperty pechados;

    public AvisosContador(String animalDescrito, String xaulaDescrita, String areaDescrita, Integer abertos, Integer pechados) {
        this.animalDescrito = new SimpleStringProperty(animalDescrito);
        this.xaulaDescrita = new SimpleStringProperty(xaulaDescrita);
        this.areaDescrita = new SimpleStringProperty(areaDescrita);
        this.abertos = new SimpleIntegerProperty(abertos);
        this.pechados = new SimpleIntegerProperty(pechados);
    }

    public StringProperty getNomeAnimal() {
        return animalDescrito;
    }

    public void setNomeAnimal(StringProperty animalDescrito) {
        this.animalDescrito = animalDescrito;
    }

    public StringProperty getXaulaDescrita() {
        return xaulaDescrita;
    }

    public void setXaulaDescrita(StringProperty xaulaDescrita) {
        this.xaulaDescrita = xaulaDescrita;
    }

    public StringProperty getAreaDescrita() {
        return areaDescrita;
    }

    public void setAreaDescrita(StringProperty areaDescrita) {
        this.areaDescrita = areaDescrita;
    }

    public IntegerProperty getAbertos() {
        return abertos;
    }

    public void setAbertos(IntegerProperty abertos) {
        this.abertos = abertos;
    }

    public IntegerProperty getPechados() {
        return pechados;
    }

    public void setPechados(IntegerProperty pechados) {
        this.pechados = pechados;
    }
}
