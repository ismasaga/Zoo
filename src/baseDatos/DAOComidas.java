/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import aplicacion.ComidaAnimal;
import aplicacion.Animal;
import aplicacion.Comida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rosa
 */
public class DAOComidas extends DAOAbstracto {

    public DAOComidas(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ObservableList buscarComidas() {
        ObservableList comidas = FXCollections.observableArrayList();

        Connection con = this.getConexion();
        PreparedStatement stmComer = null, stmAnimales = null, stmComidas = null;
        ResultSet rsComer = null, rsComidas = null, rsAnimales = null;
        Comida c;
        Animal a;

        try {
            stmComer = con.prepareStatement("select * from comer; ");
            stmComidas = con.prepareStatement("select * from comidas where id = ?;");
            stmAnimales = con.prepareStatement("select * from animales where id = ?;");

            rsComer = stmComer.executeQuery();

            while (rsComer.next()) {
                stmComidas.setInt(1, rsComer.getInt("comida"));
                rsComidas = stmComidas.executeQuery();

                stmAnimales.setInt(1, rsComer.getInt("animal"));
                rsAnimales = stmAnimales.executeQuery();

                c = new Comida(rsComidas.getInt("id"), rsComidas.getString("nome"), rsComidas.getString("unidades"), rsComidas.getInt("stock"));
                a = new Animal(rsAnimales.getInt("id"), rsAnimales.getString("nome"), rsAnimales.getString("especie"), rsAnimales.getInt("edad"),
                        rsAnimales.getInt("peso"), rsAnimales.getString("sexo"), rsAnimales.getInt("area"), rsAnimales.getInt("xaula"), rsAnimales.getString("idcoidador"));

                comidas.add(new ComidaAnimal(a, c, rsComer.getInt("cantidad")));
            }

        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmComidas.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }

        return comidas;
    }

    public void insertarComida(Comida comida) {
        Connection con = this.getConexion();
        PreparedStatement stmComidas = null;

        try {
            stmComidas = con.prepareStatement("insert into comidas values(?, ?, ?, ?);");
            stmComidas.setInt(1, comida.getId());
            stmComidas.setString(2, comida.getNombre());
            stmComidas.setInt(3, comida.getStock());
            stmComidas.setString(4, comida.getUds());

            stmComidas.executeUpdate();

        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmComidas.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }

    public void actualizarComida(Comida comida) {
        Connection con = this.getConexion();
        PreparedStatement stmComidas = null;

        try {
            stmComidas = con.prepareStatement("update comidas set nome = ?, stock = ?, uds = ? where id = ?");
            stmComidas.setInt(4, comida.getId());
            stmComidas.setString(1, comida.getNombre());
            stmComidas.setInt(2, comida.getStock());
            stmComidas.setString(3, comida.getUds());

            stmComidas.executeUpdate();

        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmComidas.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }
    
    public void gardarComida(Comida comdia) {
        // Eu estaba aquí
    }
}
