package aplicacion;

import baseDatos.FachadaBaseDatos;

public class GestionUsuarios {
    FachadaBaseDatos fbd;
    FachadaAplicacion fa;


    public GestionUsuarios(FachadaAplicacion fa) {
        this.fa = fa;
    }

    public Usuario comprobarAutentificacion(String dni, String pass) {
        return fa.fbd.comprobarAutentificacion(dni, pass);
    }

    public void logout(){
        fa.setUsuarioActual(null);
        fa.fgui.mostrarVentanaLogin();
    }
}
