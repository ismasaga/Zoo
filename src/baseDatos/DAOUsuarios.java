package baseDatos;

import aplicacion.TipoUsuario;
import aplicacion.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOUsuarios extends DAOAbstracto {

    public DAOUsuarios(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    // Non se usa, igual a podemos borrar pero vouna deixar aquí por si acaso
    public String isTipo(String usuario) {
        Connection con = this.getConexion();
        PreparedStatement stmUsuario = null;
        ResultSet rs = null;
        String tipo = null;

        try {
            stmUsuario = con.prepareStatement("select count(*) from coidadores where dni = ?;");
            stmUsuario.setString(1, usuario);
            rs = stmUsuario.executeQuery();

            rs.next();

            if (rs.getInt(1) > 0) {
                tipo = new String("coidador");
            } else {
                tipo = new String("contable");
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

        return tipo;
    }

    public Usuario comprobarAutentificacion(String dni, String pass) {
        Usuario resultado = null;
        Connection con;
        PreparedStatement stmUsuario = null, stmCoidador = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select * from coidadores "
                    + "where dni = ? and pass = ?;");

            stmUsuario.setString(1, dni);
            stmUsuario.setString(2, pass);

            rsUsuario = stmUsuario.executeQuery();

            if (!rsUsuario.isBeforeFirst()) { // Non está en coidadores
                stmUsuario = con.prepareStatement("select * from contables"
                        + "where dni = ? and pass = ?;");

                stmUsuario.setString(1, dni);
                stmUsuario.setString(2, pass);

                rsUsuario = stmUsuario.executeQuery();

                if (rsUsuario.next()) {
                    resultado = new Usuario(rsUsuario.getString("dni"),
                            rsUsuario.getString("nome"), TipoUsuario.Contable,
                            rsUsuario.getString("pass"), rsUsuario.getString("telefono"), rsUsuario.getString("email"));
                }

            } else if (rsUsuario.next()) { // Si está en coidadores
                resultado = new Usuario(rsUsuario.getString("dni"),
                        rsUsuario.getString("nome"), TipoUsuario.Coidador,
                        rsUsuario.getString("pass"), rsUsuario.getString("telefono"), rsUsuario.getString("email"));
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

    public ObservableList buscarUsuario(String usuario) {
        ObservableList usuarios = FXCollections.observableArrayList();
        Usuario usr = null;

        Connection con = this.getConexion();
        PreparedStatement stmCoidadores = null, stmContables = null;
        ResultSet rsCoidadores = null, rsContables = null;

        try {
            if (usuario.equals("")) {
                stmCoidadores = con.prepareStatement("select * from coidadores");
                stmContables = con.prepareStatement("select * from contables");
            } else {
                stmCoidadores = con.prepareStatement("select * from coidadores where dni like %?% or nome like %?% or email like %?%;");
                stmContables = con.prepareStatement("select * from contables where dni like %?% or nome like %?% or email like %?%;");

                stmCoidadores.setString(1, usuario);
                stmCoidadores.setString(2, usuario);
                stmCoidadores.setString(3, usuario);

                stmContables.setString(1, usuario);
                stmContables.setString(2, usuario);
                stmContables.setString(3, usuario);

                rsCoidadores = stmCoidadores.executeQuery();
                rsContables = stmContables.executeQuery();
            }

            while (rsCoidadores.next()) {
                usuarios.add(new Usuario(rsCoidadores.getString("dni"),
                        rsCoidadores.getString("pass"),
                        TipoUsuario.Coidador,
                        rsCoidadores.getString("nome"),
                        rsCoidadores.getString("telefono"),
                        rsCoidadores.getString("email")));
            }

            while (rsContables.next()) {
                usuarios.add(new Usuario(rsContables.getString("dni"),
                        rsContables.getString("pass"),
                        TipoUsuario.Contable,
                        rsContables.getString("nome"),
                        rsContables.getString("telefono"),
                        rsContables.getString("email")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());

        } finally {
            try {
                stmCoidadores.close();
                stmContables.close();

            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return usuarios;
    }

    public void novoUsuario(Usuario usuario) {
        Connection con;
        PreparedStatement stmUsuarios = null;
        con = this.getConexion();

        try {
            if (usuario.getTipo() == TipoUsuario.Coidador) {
                stmUsuarios = con.prepareStatement("insert into coidadores values (?, ?, ?, ?, ?);");
            } else {
                stmUsuarios = con.prepareStatement("insert into contables values (?, ?, ?, ?, ?)");
            }

            stmUsuarios.setString(1, usuario.getDni());
            stmUsuarios.setString(2, usuario.getPass());
            stmUsuarios.setString(3, usuario.getNombre());
            stmUsuarios.setString(4, usuario.getTelefono());
            stmUsuarios.setString(5, usuario.getEmail());

            stmUsuarios.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());

        } finally {
            try {
                stmUsuarios.close();

            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }

    public void updateUsuario(Usuario usuario) {
        Connection con;
        PreparedStatement stmUsuarios = null;
        con = this.getConexion();

        try {
            if (usuario.getTipo() == TipoUsuario.Coidador) {
                stmUsuarios = con.prepareStatement("update coidadores set pass = ?, nome = ?, telefono = ?, email = ? where dni = ?;");
            } else {
                stmUsuarios = con.prepareStatement("update contables set pass = ?, nome = ?, telefono = ?, email = ? where dni = ?;");
            }

            stmUsuarios.setString(5, usuario.getDni());
            stmUsuarios.setString(1, usuario.getPass());
            stmUsuarios.setString(2, usuario.getNombre());
            stmUsuarios.setString(3, usuario.getTelefono());
            stmUsuarios.setString(4, usuario.getEmail());

            stmUsuarios.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());

        } finally {
            try {
                stmUsuarios.close();

            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }

    public void borrarUsuario(Usuario usuario) {
        Connection con;
        PreparedStatement stmUsuarios = null;
        con = this.getConexion();

        try {
            if (usuario.getTipo() == TipoUsuario.Coidador) {
                stmUsuarios = con.prepareStatement("delete from coidadores where dni = ?");
            } else {
                stmUsuarios = con.prepareStatement("delete from contables where dni = ?;");
            }

            stmUsuarios.setString(1, usuario.getDni());

            stmUsuarios.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());

        } finally {
            try {
                stmUsuarios.close();

            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }
}
