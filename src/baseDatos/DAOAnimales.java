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
                stmAnimales = con.prepareStatement("select * from animais;");
            } else {
                animal = animal.substring(0, 1).toUpperCase() + animal.substring(1);
                stmAnimales = con.prepareStatement("select * from animais where id = ? or nome = ? or especie = ?;");
                stmAnimales.setString(1, animal);
                stmAnimales.setString(2, animal);
                stmAnimales.setString(3, animal);
            }
            rsAnimales = stmAnimales.executeQuery();
            while (rsAnimales.next()) {
                animales.add(new Animal(rsAnimales.getString("id"), rsAnimales.getString("nome"), rsAnimales.getString("especie"), Integer.valueOf(rsAnimales.getString("edad")), new ArrayList<>(), rsAnimales.getString("idarea"), rsAnimales.getString("idxaula")));
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