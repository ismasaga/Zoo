package aplicacion;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

    private StringProperty dni;
    private StringProperty nombre;
    private TipoUsuario tipo;
    private StringProperty pass;
    private StringProperty telefono;
    private StringProperty email;

    public Usuario(String dni, String nombre, TipoUsuario tipo, String pass, String telefono, String email) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo = tipo;
        this.pass = new SimpleStringProperty(pass);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
    }

    public Usuario(String dni, TipoUsuario tipo) {
        this.dni = new SimpleStringProperty(dni);
        this.tipo = tipo;
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public TipoUsuario tipoProperty() {
        return tipo;
    }

    public StringProperty passProperty() {
        return pass;
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getDni() {
        return dni.get();
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String getPass() {
        return pass.get();
    }

    public void setPass(String pass) {
        this.pass.set(pass);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
