package baseDatos;

import aplicacion.Animal;
import aplicacion.Aviso;
import aplicacion.AvisosContador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DAOAvisos extends DAOAbstracto {

    public DAOAvisos(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    // Novo aviso
    public void novoAviso(Aviso aviso) {
        Connection con;
        PreparedStatement stmAvisos = null;
        con = this.getConexion();
        try {            
            if (aviso.getTipo().equals("area")) {
                stmAvisos = con.prepareStatement("insert into avisosareas (area, nome, descripcion, dataInicio, dataFin, coidador, contable, "
                        + "tratamento) values (?,?,?,current_date,null,?,null,null);");
                stmAvisos.setInt(1, aviso.getArea());
                stmAvisos.setString(2, aviso.getAsunto());
                stmAvisos.setString(3, aviso.getDescripcion());
                stmAvisos.setString(4, fa.getUsuarioActual().getDni());
            }
            if (aviso.getTipo().equals("xaula")) {
                stmAvisos = con.prepareStatement("insert into avisosxaulas (xaula, area, nome, descripcion, dataInicio, dataFin, coidador, contable, "
                        + "tratamento) values (?,?,?,?,current_date,null,?,null,null);");
                stmAvisos.setInt(1, aviso.getXaula());
                stmAvisos.setInt(2, aviso.getArea());
                stmAvisos.setString(3, aviso.getAsunto());
                stmAvisos.setString(4, aviso.getDescripcion());
                stmAvisos.setString(5, fa.getUsuarioActual().getDni());
            }
            if (aviso.getTipo().equals("animal")) {
                stmAvisos = con.prepareStatement("insert into avisosanimais (animal, nome, descripcion, dataInicio, "
                        + "dataFin, coidador, contable, tratamento) values (?,?,?,current_date,null,?,null,null);");
                stmAvisos.setInt(1, aviso.getAnimal());
                stmAvisos.setString(2, aviso.getAsunto());
                stmAvisos.setString(3, aviso.getDescripcion());
                stmAvisos.setString(4, aviso.getCoidador());
            }
            stmAvisos.executeUpdate();
        } catch (SQLException e) {
            fa.muestraExcepcion(e.getMessage()+"\n"+e.getSQLState());
        } finally {
            try {
                stmAvisos.close();
            } catch (SQLException e) {
                fa.muestraExcepcion("Imposible cerrar cursores");
            }
        }
    }

    // Todos os avisos
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
            stmAvisos = con.prepareStatement("select * from avisosanimais;");
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                Animal a = (Animal) fa.buscarAnimal(rsAvisos.getString("animal")).get(0);
                avisos.add(new Aviso(rsAvisos.getInt("animal"), a.getNombre(), rsAvisos.getString("nome"), rsAvisos.getString("descripcion"), rsAvisos.getString("coidador") == null ? "" : rsAvisos.getString("coidador"), rsAvisos.getString("contable") == null ? "" : rsAvisos.getString("contable"), rsAvisos.getDate("datainicio").toString(), rsAvisos.getDate("datafin") == null ? "Non" : rsAvisos.getDate("datafin").toString(), rsAvisos.getString("tratamento") == null ? "" : rsAvisos.getString("tratamento").toString(), "animal"));
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
    
    // Todos os avisos que engadira o coidador actual
    public ObservableList buscarAvisosPropios() {
        ObservableList avisos = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAvisos = null;
        ResultSet rsAvisos;
        con = this.getConexion();
        try {
            stmAvisos = con.prepareStatement("select * from avisosxaulas where coidador = ?;");
            stmAvisos.setString(1, fa.getUsuarioActual().getDni());
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                avisos.add(new Aviso(rsAvisos.getString("nome"), rsAvisos.getString("descripcion"), rsAvisos.getString("coidador") == null ? "" : rsAvisos.getString("coidador"), rsAvisos.getString("contable") == null ? "" : rsAvisos.getString("contable"), rsAvisos.getInt("area"), rsAvisos.getInt("xaula"), rsAvisos.getDate("datainicio").toString(), rsAvisos.getDate("datafin") == null ? "Non" : rsAvisos.getDate("datafin").toString(), rsAvisos.getDate("tratamento") == null ? "" : rsAvisos.getDate("tratamento").toString(), "xaula"));
            }
            stmAvisos = con.prepareStatement("select * from avisosareas where coidador = ?;");
            stmAvisos.setString(1, fa.getUsuarioActual().getDni());
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                avisos.add(new Aviso(rsAvisos.getString("nome"), rsAvisos.getString("descripcion"), rsAvisos.getString("coidador") == null ? "" : rsAvisos.getString("coidador"), rsAvisos.getString("contable") == null ? "" : rsAvisos.getString("contable"), rsAvisos.getInt("area"), rsAvisos.getDate("datainicio").toString(), rsAvisos.getDate("datafin") == null ? "Non" : rsAvisos.getDate("datafin").toString(), rsAvisos.getString("tratamento") == null ? "" : rsAvisos.getString("tratamento").toString(), "area"));
            }
            stmAvisos = con.prepareStatement("select * from avisosanimais;");
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                Animal a = (Animal) fa.buscarAnimal(rsAvisos.getString("animal")).get(0);
                avisos.add(new Aviso(rsAvisos.getInt("animal"), a.getNombre(), rsAvisos.getString("nome"), rsAvisos.getString("descripcion"), rsAvisos.getString("coidador") == null ? "" : rsAvisos.getString("coidador"), rsAvisos.getString("contable") == null ? "" : rsAvisos.getString("contable"), rsAvisos.getDate("datainicio").toString(), rsAvisos.getDate("datafin") == null ? "Non" : rsAvisos.getDate("datafin").toString(), rsAvisos.getString("tratamento") == null ? "" : rsAvisos.getString("tratamento").toString(), "animal"));
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
    // Avisos de animais que coida o coidador actual
    public ObservableList buscarAvisosAnimais() {
        ObservableList avisos = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAvisos = null;
        ResultSet rsAvisos;
        con = this.getConexion();
        try {
            stmAvisos = con.prepareStatement("select * from avisosanimais where coidador = ?;");
            stmAvisos.setString(1, fa.getUsuarioActual().getDni());
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                Animal a = (Animal) fa.buscarAnimal(rsAvisos.getString("animal")).get(0);
                avisos.add(new Aviso(rsAvisos.getInt("animal"), a.getNombre(), rsAvisos.getString("nome"), rsAvisos.getString("descripcion"), 
                        rsAvisos.getString("coidador") == null ? "" : rsAvisos.getString("coidador"), 
                        rsAvisos.getString("contable") == null ? "" : rsAvisos.getString("contable"), rsAvisos.getDate("datainicio").toString(), 
                        rsAvisos.getDate("datafin") == null ? "Non" : rsAvisos.getDate("datafin").toString(), 
                        rsAvisos.getString("tratamento") == null ? "" : rsAvisos.getString("tratamento").toString(), "animal"));
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
    // Devolve cada animal xunto co numero de avisos aberto e pechado
    public ObservableList contarAvisosAnimais() {
        ObservableList avisos = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAvisos = null, stmAvisos2 = null;
        ResultSet rsAvisos, rsAvisos2;
        con = this.getConexion();
        try {
            stmAvisos = con.prepareStatement("select av.animal, count(av.animal) as abertas, an.nome from avisosAnimais as av inner join animais as an on (av.animal = an.id) and (av.dataFin is null) group by av.animal, an.nome;");
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                AvisosContador av = new AvisosContador(rsAvisos.getInt("animal")+":"+rsAvisos.getString("nome"), "", "", rsAvisos.getInt("abertas"), 0);
                stmAvisos2 = con.prepareStatement("select count(animal) as pechados from avisosAnimais where animal = ? and (not dataFin is null);");
                stmAvisos2.setInt(1, rsAvisos.getInt("animal"));
                rsAvisos2 = stmAvisos2.executeQuery();
                while (rsAvisos2.next()) {
                    av.setPechados(new SimpleIntegerProperty(rsAvisos2.getInt("pechados")));
                    avisos.add(av);
                }
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
    // Devolve cada area xunto co seu numero de avisos aberto e pechado
    public ObservableList contarAvisosAreas() {
        ObservableList avisos = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAvisos = null, stmAvisos2 = null;
        ResultSet rsAvisos, rsAvisos2;
        con = this.getConexion();
        try {
            stmAvisos = con.prepareStatement("select av.area, count(av.area) as abertas, ar.climatizacion from avisosAreas as av inner join areas as ar on (av.area = ar.id) and (av.dataFin is null) group by av.area, ar.climatizacion;");
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                AvisosContador av = new AvisosContador("", "", rsAvisos.getString("climatizacion")+":"+rsAvisos.getString("area"), rsAvisos.getInt("abertas"), 0);
                stmAvisos2 = con.prepareStatement("select count(area) as pechados from avisosAreas where area = ? and (not dataFin is null);");
                stmAvisos2.setInt(1, rsAvisos.getInt("area"));
                rsAvisos2 = stmAvisos2.executeQuery();
                while (rsAvisos2.next()) {
                    av.setPechados(new SimpleIntegerProperty(rsAvisos2.getInt("pechados")));
                    avisos.add(av);
                }
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
    // Devolve cada xaula xunto co seu numero de avisos aberto e pechado
    public ObservableList contarAvisosXaulas() {
        ObservableList avisos = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAvisos = null, stmAvisos2 = null;
        ResultSet rsAvisos, rsAvisos2;
        con = this.getConexion();
        try {
            stmAvisos = con.prepareStatement("select av.xaula, count(av.xaula) as abertas, xa.idArea from avisosXaulas as av inner join xaulas as xa on (av.xaula = xa.id) and (av.dataFin is null) group by av.xaula, xa.idArea;");
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                AvisosContador av = new AvisosContador("", rsAvisos.getString("xaula")+":Área "+rsAvisos.getString("idArea"), "", rsAvisos.getInt("abertas"), 0);
                stmAvisos2 = con.prepareStatement("select count(area) as pechados from avisosXaulas where xaula = ? and area = ? and (not dataFin is null);");
                stmAvisos2.setInt(1, rsAvisos.getInt("xaula"));
                stmAvisos2.setInt(2, rsAvisos.getInt("idArea"));
                rsAvisos2 = stmAvisos2.executeQuery();
                while (rsAvisos2.next()) {
                    av.setPechados(new SimpleIntegerProperty(rsAvisos2.getInt("pechados")));
                    avisos.add(av);
                }
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
    // Avisos de xaulas nas que estan animais que coida o coidador actual
    public ObservableList buscarAvisosXaulas() {
        ObservableList avisos = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAvisos = null;
        ResultSet rsAvisos;
        con = this.getConexion();
        try {
            stmAvisos = con.prepareStatement("select * from avisosxaulas where coidador = ?;");
            stmAvisos.setString(1, fa.getUsuarioActual().getDni());
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                avisos.add(new Aviso(rsAvisos.getString("nome"), rsAvisos.getString("descripcion"), rsAvisos.getString("coidador") == null ? "" : rsAvisos.getString("coidador"), 
                        rsAvisos.getString("contable") == null ? "" : rsAvisos.getString("contable"), rsAvisos.getInt("area"), rsAvisos.getInt("xaula"), 
                        rsAvisos.getDate("datainicio").toString(), rsAvisos.getDate("datafin") == null ? "Non" : rsAvisos.getDate("datafin").toString(), 
                        rsAvisos.getDate("tratamento") == null ? "" : rsAvisos.getDate("tratamento").toString(), "xaula"));
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
    
    // Avisos de areas nas que estan as xaulas onde estan os animais que coida o coidador actual
    public ObservableList buscarAvisosAreas() {
        ObservableList avisos = FXCollections.observableArrayList();
        Connection con;
        PreparedStatement stmAvisos = null;
        ResultSet rsAvisos;
        con = this.getConexion();
        try {
            stmAvisos = con.prepareStatement("select * from avisosareas where coidador = ?;");
            stmAvisos.setString(1, fa.getUsuarioActual().getDni());
            rsAvisos = stmAvisos.executeQuery();
            while (rsAvisos.next()) {
                avisos.add(new Aviso(rsAvisos.getString("nome"), rsAvisos.getString("descripcion"), rsAvisos.getString("coidador") == null ? "" : rsAvisos.getString("coidador"), 
                        rsAvisos.getString("contable") == null ? "" : rsAvisos.getString("contable"), rsAvisos.getInt("area"), rsAvisos.getDate("datainicio").toString(), 
                        rsAvisos.getDate("datafin") == null ? "Non" : rsAvisos.getDate("datafin").toString(), 
                        rsAvisos.getString("tratamento") == null ? "" : rsAvisos.getString("tratamento").toString(), "area"));
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

    public void resolverAviso(Aviso aviso) {
        Connection con;
        PreparedStatement stmAvisos = null;
        con = this.getConexion();
        try {
            if (aviso.getTipo().equals("area")) {
                stmAvisos = con.prepareStatement("update avisosareas set datafin = current_date , contable = ? , tratamento = ? where area = ? and nome = ? and datainicio = ?;");
                stmAvisos.setString(1, fa.getUsuarioActual().getDni());
                stmAvisos.setString(2, aviso.getTratamento());
                stmAvisos.setInt(3, aviso.getArea());
                stmAvisos.setString(4, aviso.getAsunto());
                stmAvisos.setDate(5, java.sql.Date.valueOf(aviso.getDataInicio()));
            }
            if (aviso.getTipo().equals("xaula")) {
                stmAvisos = con.prepareStatement("update avisosxaulas set datafin = current_date , contable = ? , tratamento = ? where idarea = ? and id = ? and nome = ? and datainicio = ?;");
                stmAvisos.setString(1, fa.getUsuarioActual().getDni());
                stmAvisos.setString(2, aviso.getTratamento());
                stmAvisos.setInt(3, aviso.getArea());
                stmAvisos.setInt(4, aviso.getXaula());
                stmAvisos.setString(5, aviso.getAsunto());
                stmAvisos.setDate(6, java.sql.Date.valueOf(aviso.getDataInicio()));
            }
            if (aviso.getTipo().equals("animal")) {
                stmAvisos = con.prepareStatement("update avisosanimais set datafin = current_date , contable = ? , tratamento = ? where animal = ? and nome = ? and datainicio = ?;");
                stmAvisos.setString(1, fa.getUsuarioActual().getDni());
                stmAvisos.setString(2, aviso.getTratamento());
                stmAvisos.setInt(3, aviso.getAnimal());
                stmAvisos.setString(4, aviso.getAsunto());
                stmAvisos.setDate(5, java.sql.Date.valueOf(aviso.getDataInicio()));
            }
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

    public void reabrirAviso(Aviso aviso) {
        Connection con;
        PreparedStatement stmAvisos = null;
        con = this.getConexion();
        try {
            if (aviso.getTipo().equals("area")) {
                stmAvisos = con.prepareStatement("update avisosareas set datafin = null , contable = null , tratamento = null where area = ? and nome = ? and datainicio = ?;");
                stmAvisos.setInt(1, Integer.valueOf(aviso.getArea()));
                stmAvisos.setString(2, aviso.getAsunto());
                stmAvisos.setDate(3, java.sql.Date.valueOf(aviso.getDataInicio()));

            }
            if (aviso.getTipo().equals("xaula")) {
                stmAvisos = con.prepareStatement("update avisosxaulas set datafin = null , contable = null , tratamento = null where idarea = ? and nome = ? and datainicio = ? and id = ?;");
                stmAvisos.setInt(1, Integer.valueOf(aviso.getArea()));
                stmAvisos.setString(2, aviso.getAsunto());
                stmAvisos.setDate(3, java.sql.Date.valueOf(aviso.getDataInicio()));
                stmAvisos.setInt(4, Integer.valueOf(aviso.getXaula()));

            }
            if (aviso.getTipo().equals("animal")) {
                stmAvisos = con.prepareStatement("update avisosanimais set datafin = null , contable = null , tratamento = null where animal = ? and nome = ? and datainicio = ?;");
                stmAvisos.setInt(1, Integer.valueOf(aviso.getAnimal()));
                stmAvisos.setString(2, aviso.getAsunto());
                stmAvisos.setDate(3, java.sql.Date.valueOf(aviso.getDataInicio()));
            }
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

    public void borrarAviso(Aviso aviso) {
        Connection con;
        PreparedStatement stmAvisos = null;
        con = this.getConexion();
        try {
            if (aviso.getTipo().equals("area")) {
                stmAvisos = con.prepareStatement("delete from avisosareas where area = ? and nome = ? and datainicio = ?;");
                stmAvisos.setInt(1, Integer.valueOf(aviso.getArea()));
                stmAvisos.setString(2, aviso.getAsunto());
                stmAvisos.setDate(3, java.sql.Date.valueOf(aviso.getDataInicio()));

            }
            if (aviso.getTipo().equals("xaula")) {
                stmAvisos = con.prepareStatement("delete from avisosxaulas where idarea = ? and nome = ? and datainicio = ? and id = ?;");
                stmAvisos.setInt(1, Integer.valueOf(aviso.getArea()));
                stmAvisos.setString(2, aviso.getAsunto());
                stmAvisos.setDate(3, java.sql.Date.valueOf(aviso.getDataInicio()));
                stmAvisos.setInt(4, Integer.valueOf(aviso.getXaula()));

            }
            if (aviso.getTipo().equals("animal")) {
                stmAvisos = con.prepareStatement("delete from avisosanimais where animal = ? and nome = ? and datainicio = ?;");
                stmAvisos.setInt(1, Integer.valueOf(aviso.getAnimal()));
                stmAvisos.setString(2, aviso.getAsunto());
                stmAvisos.setDate(3, java.sql.Date.valueOf(aviso.getDataInicio()));
            }
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
    
    public void actualizarAviso(Aviso aviso) {
        Connection con;
        PreparedStatement stmAvisos = null;
        con = this.getConexion();
        try {
            if (aviso.getTipo().equals("area")) {
                stmAvisos = con.prepareStatement("update avisosareas set nome = ? , descripcion = ? where coidador = ? and datainicio = ? and area = ?;");
                stmAvisos.setString(1, aviso.getAsunto());
                stmAvisos.setString(2, aviso.getDescripcion());
                stmAvisos.setString(3, aviso.getCoidador());
                stmAvisos.setDate(4, java.sql.Date.valueOf(aviso.getDataInicio()));
                stmAvisos.setInt(5, aviso.getArea());
            }
            if (aviso.getTipo().equals("xaula")) {
                stmAvisos = con.prepareStatement("update avisosxaulas set nome = ? , descripcion = ? where coidador = ? and datainicio = ? and xaula = ? and area = ?;");
                stmAvisos.setString(1, aviso.getAsunto());
                stmAvisos.setString(2, aviso.getDescripcion());
                stmAvisos.setString(3, aviso.getCoidador());
                stmAvisos.setDate(4, java.sql.Date.valueOf(aviso.getDataInicio()));
                stmAvisos.setInt(5, aviso.getXaula());
                stmAvisos.setInt(6, aviso.getArea());
            }
            if (aviso.getTipo().equals("animal")) {
                stmAvisos = con.prepareStatement("update avisosanimais set nome = ? , descripcion = ? where coidador = ? and datainicio = ? and animal = ?;");
                stmAvisos.setString(1, aviso.getAsunto());
                stmAvisos.setString(2, aviso.getDescripcion());
                stmAvisos.setString(3, aviso.getCoidador());
                stmAvisos.setDate(4, java.sql.Date.valueOf(aviso.getDataInicio()));
                stmAvisos.setInt(5, aviso.getAnimal());
            }
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
