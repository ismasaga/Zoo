package baseDatos;

import aplicacion.Animal;
import aplicacion.Aviso;
import aplicacion.Comida;
import aplicacion.Usuario;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FachadaBaseDatos {

    public aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOUsuarios daoUsuarios;
    private DAOAnimales daoAnimales;
    private DAOAreasXaulas daoAreasXaulas;
    private DAOAvisos daoAvisos;
    private DAOComidas daoComidas;

    public FachadaBaseDatos(aplicacion.FachadaAplicacion fa) {
        Properties configuracion = new Properties();
        this.fa = fa;
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();

            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion = java.sql.DriverManager.getConnection("jdbc:" + gestor + "://"
                    + configuracion.getProperty("servidor") + ":"
                    + configuracion.getProperty("puerto") + "/"
                    + configuracion.getProperty("baseDatos"),
                    usuario);

            daoUsuarios = new DAOUsuarios(conexion, fa);
            daoAnimales = new DAOAnimales(conexion, fa);
            daoAreasXaulas = new DAOAreasXaulas(conexion, fa);
            daoAvisos = new DAOAvisos(conexion, fa);
            daoComidas = new DAOComidas(conexion, fa);

        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage());
        } catch (IOException i) {
            System.out.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage());
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
        }
    }

    public Usuario comprobarAutentificacion(String dni, String pass) {
        return daoUsuarios.comprobarAutentificacion(dni, pass);
    }

    public ObservableList buscarAnimal(String animal) {
        return daoAnimales.buscarAnimal(animal);
    }

    public ObservableList buscarAnimaisCoidador() {
        return daoAnimales.buscarAnimaisCoidador();
    }
    
    public ObservableList buscarComidasAnimal(Animal animal) {
        return daoAnimales.buscarComidasAnimal(animal);
    }

    public ObservableList buscarXaulasAnimaisCoidador() {
        return daoAnimales.buscarXaulasAnimaisCoidador();
    }

    public ObservableList buscarAreasAnimaisCoidador() {
        return daoAnimales.buscarAreasAnimaisCoidador();
    }

    public void updateAnimal(Animal a) {
        if (buscarAnimal(String.valueOf(a.getId())).size() == 1) {
            daoAnimales.updateAnimal(a);
        } else {
            daoAnimales.novoAnimal(a);
        }
    }

    public void borrarAnimal(Integer integer) {
        daoAnimales.borrarAnimal(integer);
    }

    public ObservableList updateAreas() {
        return daoAreasXaulas.updateAreas();
    }

    public ObservableList updateXaulas(Integer area) {
        return daoAreasXaulas.updateXaulas(area);
    }

    public ObservableList updateUsuarios(String usuario) {
        return daoUsuarios.buscarUsuario(usuario);
    }

    public void gardarUsuario(Usuario usuario) {
        daoUsuarios.gardarUsuario(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        daoUsuarios.borrarUsuario(usuario);
    }

    public ObservableList updateComidas() {
        return daoComidas.buscarComidas();
    }

    public ObservableList updateAnimaisComida(Comida comida) {
        return daoComidas.buscarAnimaisComida(comida);
    }

    public ObservableList updateOutrosAnimaisComida(Comida comida) {
        return daoComidas.buscarAnimaisNonComida(comida);
    }

    public int recuperarCantidade(Comida comida, Animal animal) {
        return daoComidas.recuperarCantidade(comida, animal);
    }

    public void gardarComida(Comida c) {
        daoComidas.gardarComida(c);
    }

    public void eliminarComida(Comida c) {
        daoComidas.borrarComida(c);
    }

    public void engadirAnimalComida(Comida c, Animal a, Integer cantidade) {
        daoComidas.addAnimal(c, a, cantidade);
    }

    public void quitarAnimalComida(Comida c, Animal a) {
        daoComidas.removeAnimal(c, a);
    }

    public void cambiarCantidadeComida(Comida c, Animal a, Integer cantidade) {
        daoComidas.cambiarCantidade(c, a, cantidade);
    }

    public ObservableList buscarAvisos() {
        return daoAvisos.buscarAvisos();
    }

    public ObservableList buscarAvisosPropios() {
        return daoAvisos.buscarAvisosPropios();
    }

    public ObservableList buscarAvisosAnimais() {
        return daoAvisos.buscarAvisosAnimais();
    }

    public ObservableList buscarAvisosXaulas() {
        return daoAvisos.buscarAvisosXaulas();
    }

    public ObservableList buscarAvisosAreas() {
        return daoAvisos.buscarAvisosAreas();
    }

    public void novoAviso(Aviso aviso) {
        daoAvisos.novoAviso(aviso);
    }

    public void resolverAviso(Aviso aviso) {
        daoAvisos.resolverAviso(aviso);
    }

    public void reabrirAviso(Aviso aviso) {
        daoAvisos.reabrirAviso(aviso);
    }

    public void borrarAviso(Aviso aviso) {
        daoAvisos.borrarAviso(aviso);
    }

    public void actualizarAviso(Aviso aviso) {
        daoAvisos.actualizarAviso(aviso);
    }

    public void borrarArea(Integer id) {
        daoAreasXaulas.borrarArea(id);
    }

    public void borrarXaula(Integer id, Integer idArea) {
        daoAreasXaulas.borrarXaula(id, idArea);
    }

    public void novaXaula(Integer id, int idArea) {
        daoAreasXaulas.novaXaula(id, idArea);
    }

    public void novaArea(Integer id, String climatizacion) {
        daoAreasXaulas.novaArea(id, climatizacion);
    }

    public Integer contarAnimais(int idXaula, int idArea) {
        return daoAnimales.contarAnimais(idXaula, idArea);
    }

    public Integer contarEspecies(int idXaula, int idArea) {
        return daoAnimales.contarEspecies(idXaula, idArea);
    }

    public void transferirAnimais(Integer areaActual, Integer areaDestino, Integer xaulaActual, Integer xaulaDestino) {
        daoAnimales.transferirAnimais(areaActual, areaDestino, xaulaActual, xaulaDestino);
    }

    public Integer cargaTraballo(String usuario) {
        return daoUsuarios.cargaTraballo(usuario);
    }
}
