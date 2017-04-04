package baseDatos;

import aplicacion.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOAnimales extends DAOAbstracto {

    public DAOAnimales(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ObservableList buscarAnimal(String animal) {
        ObservableList animales = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAnimales = null;
        ResultSet rsAnimales;

        con = this.getConexion();
        try {
            if (animal.equals("")) {
                stmAnimales = con.prepareStatement("select * from animales;");
            } else {
                stmAnimales = con.prepareStatement("select * from animales where id = ? or nombre = ? or especie = ?;");
                stmAnimales.setString(1, animal);
                stmAnimales.setString(2, animal);
                stmAnimales.setString(3, animal);
            }
            System.out.println(stmAnimales);
            rsAnimales = stmAnimales.executeQuery();
            while (rsAnimales.next()) {
                animales.add(new Animal(rsAnimales.getString("id"), rsAnimales.getString("nombre"), rsAnimales.getString("especie"), Integer.valueOf(rsAnimales.getString("edad")), new ArrayList<>()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmAnimales.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return animales;
    }
}
