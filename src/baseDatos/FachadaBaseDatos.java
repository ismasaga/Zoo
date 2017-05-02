package baseDatos;

import aplicacion.Animal;
import aplicacion.Aviso;
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
            this.conexion = java.sql.DriverManager.getConnection("jdbc:" + gestor + "://" +
                            configuracion.getProperty("servidor") + ":" +
                            configuracion.getProperty("puerto") + "/" +
                            configuracion.getProperty("baseDatos"),
                    usuario);

            daoUsuarios = new DAOUsuarios(conexion, fa);
            daoAnimales = new DAOAnimales(conexion, fa);
            daoAreasXaulas = new DAOAreasXaulas(conexion, fa);
            daoAvisos = new DAOAvisos(conexion, fa);


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

    public ObservableList buscarAvisos() {
        return daoAvisos.buscarAvisos();
    }

    public void resolverAvisoArea(Aviso aviso) {
        daoAvisos.resolverAvisoArea(aviso);
    }
}
