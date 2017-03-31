package baseDatos;

import aplicacion.TipoUsuario;
import aplicacion.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by miguel on 31/03/17.
 */
public class DAOUsuarios extends DAOAbstracto {

    public DAOUsuarios(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public Usuario comprobarAutentificacion(String dni, String pass) {
        Usuario resultado = null;
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select dni, nombre, tipo, pass " +
                    "from usuarios " +
                    "where dni = ? and pass = ?");
            stmUsuario.setString(1, dni);
            stmUsuario.setString(2, pass);
            rsUsuario = stmUsuario.executeQuery();
            if (rsUsuario.next()) {
                resultado = new Usuario(rsUsuario.getString("dni"),rsUsuario.getString("nombre"), TipoUsuario.valueOf(rsUsuario.getString("tipo")), rsUsuario.getString("pass"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }
}
