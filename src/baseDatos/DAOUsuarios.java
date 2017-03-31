package baseDatos;

import java.sql.*;

/**
 * Created by miguel on 31/03/17.
 */
public class DAOUsuarios extends DAOAbstracto{
    public DAOUsuarios (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
}
