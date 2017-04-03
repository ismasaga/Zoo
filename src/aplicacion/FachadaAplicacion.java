/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import gui.GUIManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class FachadaAplicacion extends Application {

    gui.GUIManager fgui;
    baseDatos.FachadaBaseDatos fbd;
    GestionUsuarios gu;
    Usuario usuarioActual = null;

    public FachadaAplicacion() {
        fgui = new gui.GUIManager(this);
        fbd = new baseDatos.FachadaBaseDatos(this);
        gu = new GestionUsuarios(this);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fgui.mostrarVentanaLogin();
    }

    public void muestraExcepcion(String e) {
        fgui.muestraExcepcion(e);
    }

    public Usuario comprobarAutentificacion(String dni, String pass) {
        return gu.comprobarAutentificacion(dni, pass);
    }

    public void logout(){
        gu.logout();
    }

}
