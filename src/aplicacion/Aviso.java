package aplicacion;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by miguel on 1/05/17.
 */
public class Aviso {
    private StringProperty asunto;
    private String tipo;
    private StringProperty suxeito;
    private StringProperty descripcion;
    private StringProperty coidador;
    private StringProperty contable;
    private IntegerProperty area;
    private IntegerProperty xaula;
    private IntegerProperty animal;
    private StringProperty tratamento;
    private StringProperty dataInicio;
    private StringProperty dataFin;

    public Aviso(String asunto, String descripcion, String coidador, String contable, Integer area, Integer xaula, String dataInicio, String dataFin, String tratamento, String tipo) {
        this.suxeito = new SimpleStringProperty("Xaula " + xaula + " da area " + area);
        this.asunto = new SimpleStringProperty(asunto);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.coidador = new SimpleStringProperty(coidador);
        this.contable = new SimpleStringProperty(contable);
        this.area = new SimpleIntegerProperty(area);
        this.xaula = new SimpleIntegerProperty(xaula);
        this.dataInicio = new SimpleStringProperty(dataInicio);
        this.dataFin = new SimpleStringProperty(dataFin);
        this.tratamento = new SimpleStringProperty(tratamento);
        this.tipo = tipo;
    }

    public Aviso(String asunto, String descripcion, String coidador, String contable, Integer area, String dataInicio, String dataFin, String tratamento, String tipo) {
        this.suxeito = new SimpleStringProperty("Area: " + area);
        this.asunto = new SimpleStringProperty(asunto);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.coidador = new SimpleStringProperty(coidador);
        this.contable = new SimpleStringProperty(contable);
        this.area = new SimpleIntegerProperty(area);
        this.xaula = null;
        this.dataInicio = new SimpleStringProperty(dataInicio);
        this.dataFin = new SimpleStringProperty(dataFin);
        this.tratamento = new SimpleStringProperty(tratamento);
        this.tipo = tipo;
    }

    public Aviso(Integer animal, String suxeito, String asunto, String descripcion, String coidador, String contable, String dataInicio, String dataFin, String tratamento, String tipo) {
        this.suxeito = new SimpleStringProperty("Animal: " + animal + ", " + suxeito);
        this.asunto = new SimpleStringProperty(asunto);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.coidador = new SimpleStringProperty(coidador);
        this.contable = new SimpleStringProperty(contable);
        this.animal = new SimpleIntegerProperty(animal);
        this.dataInicio = new SimpleStringProperty(dataInicio);
        this.dataFin = new SimpleStringProperty(dataFin);
        this.tratamento = new SimpleStringProperty(tratamento);
        this.tipo = tipo;
    }

    public int getAnimal() {
        return animal.get();
    }

    public void setAnimal(int animal) {
        this.animal.set(animal);
    }

    public IntegerProperty animalProperty() {
        return animal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSuxeito() {
        return suxeito.get();
    }

    public void setSuxeito(String suxeito) {
        this.suxeito.set(suxeito);
    }

    public StringProperty suxeitoProperty() {
        return suxeito;
    }

    public String getAsunto() {
        return asunto.get();
    }

    public void setAsunto(String asunto) {
        this.asunto.set(asunto);
    }

    public StringProperty asuntoProperty() {
        return asunto;
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public String getCoidador() {
        return coidador.get();
    }

    public void setCoidador(String coidador) {
        this.coidador.set(coidador);
    }

    public StringProperty coidadorProperty() {
        return coidador;
    }

    public String getContable() {
        return contable.get();
    }

    public void setContable(String contable) {
        this.contable.set(contable);
    }

    public StringProperty contableProperty() {
        return contable;
    }

    public Integer getArea() {
        return area.get();
    }

    public void setArea(int area) {
        this.area.set(area);
    }

    public IntegerProperty areaProperty() {
        return area;
    }

    public Integer getXaula() {
        return xaula.get();
    }

    public void setXaula(int xaula) {
        this.xaula.set(xaula);
    }

    public IntegerProperty xaulaProperty() {
        return xaula;
    }

    public String getDataInicio() {
        return dataInicio.get();
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio.set(dataInicio);
    }

    public StringProperty dataInicioProperty() {
        return dataInicio;
    }

    public String getDataFin() {
        return dataFin.get();
    }

    public void setDataFin(String dataFin) {
        this.dataFin.set(dataFin);
    }

    public StringProperty dataFinProperty() {
        return dataFin;
    }

    public String getTratamento() {
        return tratamento.get();
    }

    public void setTratamento(String tratamento) {
        this.tratamento.set(tratamento);
    }

    public StringProperty tratamentoProperty() {
        return tratamento;
    }
}