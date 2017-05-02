package baseDatos;

import aplicacion.Aviso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by miguel on 1/05/17.
 */
public class DAOAvisos extends DAOAbstracto {

    public DAOAvisos(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ObservableList buscarAvisos() {
        ObservableList avisos = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAvisos = null;
        ResultSet rsAvisos;
        con = this.getConexion();
        try {
            stmAvisos = con.prepareStatement("select * from avisosxaulas;");
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                avisos.add(new Aviso(rsAvisos.getString("nome"), rsAvisos.getString("descripcion"), rsAvisos.getString("coidador") == null ? "" : rsAvisos.getString("coidador"), rsAvisos.getString("contable") == null ? "" : rsAvisos.getString("contable"), rsAvisos.getInt("area"), rsAvisos.getInt("xaula"), rsAvisos.getDate("datainicio").toString(), rsAvisos.getDate("datafin") == null ? "Non" : rsAvisos.getDate("datafin").toString(), rsAvisos.getDate("tratamento") == null ? "" : rsAvisos.getDate("tratamento").toString(), "xaula"));
            }
            stmAvisos = con.prepareStatement("select * from avisosareas;");
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                avisos.add(new Aviso(rsAvisos.getString("nome"), rsAvisos.getString("descripcion"), rsAvisos.getString("coidador") == null ? "" : rsAvisos.getString("coidador"), rsAvisos.getString("contable") == null ? "" : rsAvisos.getString("contable"), rsAvisos.getInt("area"), rsAvisos.getDate("datainicio").toString(), rsAvisos.getDate("datafin") == null ? "Non" : rsAvisos.getDate("datafin").toString(), rsAvisos.getString("tratamento") == null ? "" : rsAvisos.getString("tratamento").toString(), "area"));
            }
        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmAvisos.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
        return avisos;
    }

    public void resolverAvisoArea(Aviso aviso) {
        Connection con;
        PreparedStatement stmAvisos = null;
        con = this.getConexion();
        try {
            stmAvisos = con.prepareStatement("update avisosareas set datafin = current_date , contable = ? , tratamento = ? where area = ? and nome = ? and datainicio = ?;");
            stmAvisos.setString(1, fa.getUsuarioActual().getDni());
            stmAvisos.setString(2, aviso.getTratamento());
            stmAvisos.setInt(3, Integer.valueOf(aviso.getArea()));
            stmAvisos.setString(4, aviso.getAsunto());
            stmAvisos.setDate(5, java.sql.Date.valueOf(aviso.getDataInicio()));
            stmAvisos.executeUpdate();
        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmAvisos.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }
}
