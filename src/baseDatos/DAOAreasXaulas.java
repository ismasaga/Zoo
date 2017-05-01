package baseDatos;

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
                areas.add(rsAreas.getString("id"));
            }
        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
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
                areas.add(rsXaulas.getString("id"));
            }
        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmXaulas.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
        return areas;
    }


}
