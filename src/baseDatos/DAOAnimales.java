package baseDatos;

import aplicacion.Animal;
import aplicacion.Comida;
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
                stmAnimales = con.prepareStatement("select * from animais where id = ? or nome like ? or especie like ?;");
                if (animal.matches("^\\d+$")) {
                    stmAnimales.setInt(1, Integer.valueOf(animal));
                } else {
                    stmAnimales.setInt(1, 0);
                }
                stmAnimales.setString(2, "%" + animal + "%");
                stmAnimales.setString(3, "%" + animal + "%");
            }
            rsAnimales = stmAnimales.executeQuery();
            while (rsAnimales.next()) {
                animales.add(new Animal(rsAnimales.getInt("id"), rsAnimales.getString("nome"), rsAnimales.getString("especie"), Integer.valueOf(rsAnimales.getString("edad")), Integer.valueOf(rsAnimales.getString("peso")), rsAnimales.getString("sexo"), rsAnimales.getInt("idarea"), rsAnimales.getInt("idxaula")));
            }
        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmAnimales.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
        return animales;
    }

    // Devolve os animais do coidador actual
    public ObservableList buscarAnimaisCoidador() {
        ObservableList animales = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAnimales = null;
        ResultSet rsAnimales;
        con = this.getConexion();
        try {
            stmAnimales = con.prepareStatement("select * from animais where idCoidador = ?;");
            stmAnimales.setString(1, fa.getUsuarioActual().getDni());
            rsAnimales = stmAnimales.executeQuery();
            while (rsAnimales.next()) {
                animales.add(new Animal(rsAnimales.getInt("id"), rsAnimales.getString("nome"), rsAnimales.getString("especie"),
                        Integer.valueOf(rsAnimales.getString("edad")), Integer.valueOf(rsAnimales.getString("peso")),
                        rsAnimales.getString("sexo"), rsAnimales.getInt("idarea"), rsAnimales.getInt("idxaula"),
                        rsAnimales.getString("idCoidador")));
            }
        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmAnimales.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
        return animales;
    }


    public void novoAnimal(Animal animal) {
        Connection con;
        PreparedStatement stmAnimales = null;
        con = this.getConexion();
        try {
            stmAnimales = con.prepareStatement("insert into animais values (?,?,?,?,?,?,?,?);");
            stmAnimales.setInt(1, animal.getId());
            stmAnimales.setString(2, animal.getNombre());
            stmAnimales.setString(3, animal.getEspecie());
            stmAnimales.setInt(4, animal.getEdad());
            stmAnimales.setInt(5, animal.getPeso());
            stmAnimales.setString(6, animal.getSexo());
            stmAnimales.setInt(7, animal.getXaula());
            stmAnimales.setInt(8, animal.getArea());

            stmAnimales.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmAnimales.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }

    public void updateAnimal(Animal animal) {
        Connection con;
        PreparedStatement stmAnimales = null;
        con = this.getConexion();
        try {
            stmAnimales = con.prepareStatement("update animais set nome = ?, especie = ?, edad = ?, peso = ?, sexo = ?, idxaula = ?, idarea = ? where id = ?;");
            stmAnimales.setInt(8, animal.getId());
            stmAnimales.setString(1, animal.getNombre());
            stmAnimales.setString(2, animal.getEspecie());
            stmAnimales.setInt(3, animal.getEdad());
            stmAnimales.setInt(4, animal.getPeso());
            stmAnimales.setString(5, animal.getSexo());
            stmAnimales.setInt(6, animal.getXaula());
            stmAnimales.setInt(7, animal.getArea());

            stmAnimales.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmAnimales.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }

    public void borrarAnimal(Integer animal) {
        Connection con;
        PreparedStatement stmAnimales = null;
        con = this.getConexion();
        try {
            stmAnimales = con.prepareStatement("delete from animais where id = ?;");
            stmAnimales.setInt(1, animal);

            stmAnimales.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmAnimales.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }
}
