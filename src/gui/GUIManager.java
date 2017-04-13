package gui;

import aplicacion.Controller.CoidadorController;
import aplicacion.Controller.ContableController;
import aplicacion.Controller.LoginController;
import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUIManager {
    private Stage stage = new Stage();
    private FachadaAplicacion fa;

    public GUIManager(FachadaAplicacion fa) {
        this.fa = fa;
    }

    public void mostrarVentanaLogin() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Login.fxml"));
        try {
            Scene scene = new Scene(loader.load(), 310, 188);
            LoginController controller = loader.getController();
            controller.initManager(this);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(GUIManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarVentanaContable(Usuario usuario) {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("FXML/Contable.fxml")
        );

        try {
            Scene scene = new Scene(loader.load(), 800, 600);
            ContableController controller = loader.getController();
            controller.initUser(this, usuario);
            stage.setTitle("Ventana Contable: " + usuario.getNombre());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarVentanaCoidador(Usuario usuario) {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("FXML/Coidador.fxml")
        );

        try {
            Scene scene = new Scene(loader.load(), 800, 600);
            CoidadorController controller = loader.getController();
            controller.initUser(this, usuario);
            stage.setTitle("Ventana Coidador: " + usuario.getNombre());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean logeado(String dni, String pass) {
        Usuario u = fa.comprobarAutentificacion(dni, pass);
        if (u != null) {
            fa.setUsuarioActual(u);
            if (u.getTipo().toString().equals("Contable")) {
                this.mostrarVentanaContable(u);
            } else {
                this.mostrarVentanaCoidador(u);
            }
            return true;
        } else return false;
    }

    public void logout() {
        fa.logout();
    }

    public void sair() {
        fa.sair();
    }

    public ObservableList buscarAnimal(String animal) {
        return fa.buscarAnimal(animal);
    }

    public void muestraExcepcion(String txtExcepcion) {

    }

}
