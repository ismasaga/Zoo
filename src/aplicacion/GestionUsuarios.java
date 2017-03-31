package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;

/**
 * Created by miguel on 31/03/17.
 */
public class GestionUsuarios {
    FachadaGui fgui;
    FachadaBaseDatos fbd;


    public GestionUsuarios(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public Usuario comprobarAutentificacion(String dni, String pass) {
        return fbd.comprobarAutentificacion(dni, pass);
    }
}
