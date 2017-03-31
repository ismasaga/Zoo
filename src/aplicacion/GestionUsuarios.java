package aplicacion;

import baseDatos.FachadaBaseDatos;

/**
 * Created by miguel on 31/03/17.
 */
public class GestionUsuarios {
    FachadaBaseDatos fbd;


    public GestionUsuarios(FachadaBaseDatos fbd) {
        this.fbd = fbd;
    }

    public Usuario comprobarAutentificacion(String dni, String pass) {
        return fbd.comprobarAutentificacion(dni, pass);
    }
}
