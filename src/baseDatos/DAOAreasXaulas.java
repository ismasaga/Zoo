package baseDatos;

import aplicacion.Area;
import aplicacion.Xaula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by miguel on 1/05/17.
 */
public class DAOAreasXaulas extends DAOAbstracto {

    public DAOAreasXaulas(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ObservableList updateAreas() {
        ObservableList areas = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAreas = null;
        ResultSet rsAreas;
        con = this.getConexion();
        try {

            stmAreas = con.prepareStatement("select * from areas;");
            rsAreas = stmAreas.executeQuery();

            while (rsAreas.next()) {
                areas.add(new Area(rsAreas.getInt("id"), rsAreas.getString("climatizacion")));
            }
        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmAreas.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
        return areas;
    }

    public ObservableList updateXaulas(Integer area) {
        ObservableList areas = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmXaulas = null;
        ResultSet rsXaulas;
        con = this.getConexion();
        try {

            stmXaulas = con.prepareStatement("select * from xaulas where idarea = ?;");
            stmXaulas.setInt(1, area);
            rsXaulas = stmXaulas.executeQuery();

            while (rsXaulas.next()) {
                areas.add(new Xaula(rsXaulas.getInt("id"), rsXaulas.getInt("idarea")));
            }
        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmXaulas.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
        return areas;
    }


    public void borrarArea(Integer id) {
        ObservableList areas = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAreas = null;
        con = this.getConexion();
        try {

            stmAreas = con.prepareStatement("remove from areas where id = ?;");
            stmAreas.setInt(1, id);
            stmAreas.executeUpdate();

        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmAreas.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }

    public void borrarXaula(Integer id, Integer idArea) {
        ObservableList areas = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmXaulas = null;
        con = this.getConexion();
        try {

            stmXaulas = con.prepareStatement("remove from xaulas where id = ?, idarea = ?;");
            stmXaulas.setInt(1, id);
            stmXaulas.setInt(2, idArea);
            stmXaulas.executeUpdate();

        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmXaulas.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }

    public void novaXaula(Integer id, int idArea) {
        ObservableList areas = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmXaulas = null;
        con = this.getConexion();
        try {

            stmXaulas = con.prepareStatement("insert into xaulas values (?,?);");
            stmXaulas.setInt(1, id);
            stmXaulas.setInt(2, idArea);
            stmXaulas.executeUpdate();

        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmXaulas.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }

    public void novaArea(Integer id, String climatizacion) {
        ObservableList areas = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmXaulas = null;
        con = this.getConexion();
        try {

            stmXaulas = con.prepareStatement("insert into areas values (?,?);");
            stmXaulas.setInt(1, id);
            stmXaulas.setString(2, climatizacion);
            stmXaulas.executeUpdate();

        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmXaulas.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }
}
