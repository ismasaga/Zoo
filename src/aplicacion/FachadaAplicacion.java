/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author miguel
 */
public class FachadaAplicacion extends Application{
    gui.FachadaGui fgui;
    baseDatos.FachadaBaseDatos fbd;
    GestionUsuarios gu;

    public FachadaAplicacion(){
        fgui=new gui.FachadaGui(this);
        fbd= new baseDatos.FachadaBaseDatos(this);
        gu= new GestionUsuarios(fgui, fbd);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new StackPane());

        LoginManager loginManager = new LoginManager(scene);
        loginManager.mostrarVentanaLogin();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void muestraExcepcion(String e){
        fgui.muestraExcepcion(e);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
