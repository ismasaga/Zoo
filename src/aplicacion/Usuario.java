package aplicacion;

public class Usuario {
    private String dni;
    private String nombre;
    private TipoUsuario tipo;
    private String pass;

    public Usuario(String dni, String nombre, TipoUsuario tipo, String pass) {
        this.dni = dni;
        this.nombre = nombre;
        this.tipo = tipo;
        this.pass = pass;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
