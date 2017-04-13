package baseDatos;

import aplicacion.TipoUsuario;
import aplicacion.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUsuarios extends DAOAbstracto {

    public DAOUsuarios(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public Usuario comprobarAutentificacion(String dni, String pass) {
        Usuario resultado = null;
        Connection con;
        PreparedStatement stmUsuario = null, stmCoidador = null;
        ResultSet rsUsuario, rsCoidador;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select dni, nome, pass, telefono, email " +
                    "from usuarios " +
                    "where dni = ? and pass = ?");
            stmUsuario.setString(1, dni);
            stmUsuario.setString(2, pass);
            rsUsuario = stmUsuario.executeQuery();
            stmCoidador = con.prepareStatement("select * from coidadores where dni = ?");
            if (rsUsuario.next()) {
                stmCoidador.setString(1, rsUsuario.getString("dni"));
                rsCoidador = stmCoidador.executeQuery();
                if (rsCoidador.next()) {
                    resultado = new Usuario(rsUsuario.getString("dni"),
                            rsUsuario.getString("nome"), TipoUsuario.Coidador,
                            rsUsuario.getString("pass"), rsUsuario.getString("telefono"), rsUsuario.getString("email"));
                } else {
                    resultado = new Usuario(rsUsuario.getString("dni"),
                            rsUsuario.getString("nome"), TipoUsuario.Contable,
                            rsUsuario.getString("pass"), rsUsuario.getString("telefono"), rsUsuario.getString("email"));
                }
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
