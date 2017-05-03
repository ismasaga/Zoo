/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import gui.FachadaGUI;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class FachadaAplicacion extends Application {

    FachadaGUI fgui;
    baseDatos.FachadaBaseDatos fbd;
    XestionUsuarios xu;
    Usuario usuarioActual = null;

    public FachadaAplicacion() {
        fgui = new FachadaGUI(this);
        fbd = new baseDatos.FachadaBaseDatos(this);
        xu = new XestionUsuarios(this);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fgui.mostrarVentanaLogin();
    }


    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void muestraExcepcion(String e) {
        fgui.muestraExcepcion(e);
    }

    public Usuario comprobarAutentificacion(String dni, String pass) {
        return xu.comprobarAutentificacion(dni, pass);
    }

    public void logout() {
        xu.logout();
    }

    public void sair() {
        exit(1);
    }

    public ObservableList buscarAnimal(String animal) {
        return fbd.buscarAnimal(animal);
    }

    public void updateAnimal(Animal a) {
        fbd.updateAnimal(a);
    }

    public void borrarAnimal(Integer integer) {
        fbd.borrarAnimal(integer);
    }

    public ObservableList updateAreas() {
        return fbd.updateAreas();
    }

    public ObservableList updateXaulas(Integer area) {
        return fbd.updateXaulas(area);
    }

    public boolean logeado(String dni, String pass) {
        return xu.logeado(dni, pass);
    }

    public ObservableList buscarAvisos() {
        return fbd.buscarAvisos();
    }

    public void resolverAviso(Aviso aviso) {
        fbd.resolverAviso(aviso);
    }

    public void reabrirAviso(Aviso aviso) {
        fbd.reabrirAviso(aviso);
    }

    public void borrarAviso(Aviso aviso) {
        fbd.borrarAviso(aviso);
    }

}
