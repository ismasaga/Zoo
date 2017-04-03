package aplicacion;

import baseDatos.FachadaBaseDatos;

public class XestionUsuarios {
    FachadaBaseDatos fbd;
    FachadaAplicacion fa;


    public XestionUsuarios(FachadaAplicacion fa) {
        this.fa = fa;
    }

    public Usuario comprobarAutentificacion(String dni, String pass) {
        return fa.fbd.comprobarAutentificacion(dni, pass);
    }

    public void logout() {
        fa.setUsuarioActual(null);
        fa.fgui.mostrarVentanaLogin();
    }
}
