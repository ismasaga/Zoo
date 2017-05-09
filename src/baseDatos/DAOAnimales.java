package baseDatos;

import aplicacion.Animal;
import aplicacion.Area;
import aplicacion.Xaula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAnimales extends DAOAbstracto {

    public DAOAnimales(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    //
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
                animales.add(new Animal(rsAnimales.getInt("id"), rsAnimales.getString("nome"), rsAnimales.getString("especie"), Integer.valueOf(rsAnimales.getString("edad")), Integer.valueOf(rsAnimales.getString("peso")), rsAnimales.getString("sexo"), rsAnimales.getInt("idarea"), rsAnimales.getInt("idxaula"), rsAnimales.getString("idcoidador")));
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

    // Devolve as xaulas dos animais do coidador actual
    public ObservableList buscarXaulasAnimaisCoidador() {
        ObservableList xaulas = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmXaulas = null;
        ResultSet rsXaulas;
        con = this.getConexion();
        try {
            stmXaulas = con.prepareStatement("select * from xaulas where id in(select idXaula from animais where idCoidador = ?);");
            stmXaulas.setString(1, fa.getUsuarioActual().getDni());
            rsXaulas = stmXaulas.executeQuery();
            while (rsXaulas.next()) {
                xaulas.add(new Xaula(rsXaulas.getInt("id"), rsXaulas.getInt("idArea")));
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
        return xaulas;
    }

    // Devolve as areas dos animais do coidador actual
    public ObservableList buscarAreasAnimaisCoidador() {
        ObservableList areas = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmXaulas = null;
        ResultSet rsXaulas;
        con = this.getConexion();
        try {
            stmXaulas = con.prepareStatement("select * from areas where id in (select idArea from xaulas where id in (select idXaula from animais where idCoidador = ?));");
            stmXaulas.setString(1, fa.getUsuarioActual().getDni());
            rsXaulas = stmXaulas.executeQuery();
            while (rsXaulas.next()) {
                areas.add(new Area(rsXaulas.getInt("id"), rsXaulas.getString("climatizacion")));
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


    public void novoAnimal(Animal animal) {
        Connection con;
        PreparedStatement stmAnimales = null;
        con = this.getConexion();
        try {
            stmAnimales = con.prepareStatement("insert into animais values (?,?,?,?,?,?,?,?,?);");
            stmAnimales.setInt(1, animal.getId());
            stmAnimales.setString(2, animal.getNombre());
            stmAnimales.setString(3, animal.getEspecie());
            stmAnimales.setInt(4, animal.getEdad());
            stmAnimales.setInt(5, animal.getPeso());
            stmAnimales.setString(6, animal.getSexo());
            stmAnimales.setInt(7, animal.getXaula());
            stmAnimales.setInt(8, animal.getArea());
            stmAnimales.setString(9, animal.getIdCoidador());

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
            stmAnimales = con.prepareStatement("update animais set nome = ?, especie = ?, edad = ?, peso = ?, sexo = ?, idxaula = ?, idarea = ? idcoidador = ? where id = ?;");
            stmAnimales.setInt(9, animal.getId());
            stmAnimales.setString(1, animal.getNombre());
            stmAnimales.setString(2, animal.getEspecie());
            stmAnimales.setInt(3, animal.getEdad());
            stmAnimales.setInt(4, animal.getPeso());
            stmAnimales.setString(5, animal.getSexo());
            stmAnimales.setInt(6, animal.getXaula());
            stmAnimales.setInt(7, animal.getArea());
            stmAnimales.setString(8, animal.getSexo());

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

    public Integer contarAnimais(Integer idXaula, Integer idArea) {
        Connection con;
        Integer integer = 0;
        PreparedStatement stmAnimales = null;
        ResultSet rsAnimales = null;
        con = this.getConexion();
        try {
            stmAnimales = con.prepareStatement("select count(*) from animais where idxaula = ? and idarea = ?;");
            stmAnimales.setInt(1, idXaula);
            stmAnimales.setInt(2, idArea);

            rsAnimales = stmAnimales.executeQuery();
            rsAnimales.next();
            integer = rsAnimales.getInt("count");
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
        return integer;
    }

    public Integer contarEspecies(int idXaula, int idArea) {
        Connection con;
        Integer integer = 0;
        PreparedStatement stmAnimales = null;
        ResultSet rsAnimales = null;
        con = this.getConexion();
        try {
            stmAnimales = con.prepareStatement("select count(distinct especie) from animais where idxaula = ? and idarea = ?;");
            stmAnimales.setInt(1, idXaula);
            stmAnimales.setInt(2, idArea);

            rsAnimales = stmAnimales.executeQuery();
            rsAnimales.next();
            integer = rsAnimales.getInt("count");
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
        return integer;
    }

    public void transferirAnimais(Integer areaActual, Integer areaDestino, Integer xaulaActual, Integer xaulaDestino) {
        Connection con;
        PreparedStatement stmAnimales = null;
        con = this.getConexion();
        try {
            stmAnimales = con.prepareStatement("update animais set idarea = ? , idxaula = ? where idarea = ? and idxaula = ?;");
            stmAnimales.setInt(1, areaDestino);
            stmAnimales.setInt(2, xaulaDestino);
            stmAnimales.setInt(3, areaActual);
            stmAnimales.setInt(4, xaulaActual);

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
