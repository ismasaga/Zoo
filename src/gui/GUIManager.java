package gui;

import aplicacion.Controller.CoidadorController;
import aplicacion.Controller.ContableController;
import aplicacion.Controller.LoginController;
import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by miguel on 31/03/17.
 */
public class GUIManager {
    private Stage stage;
    private FachadaAplicacion fa;

    public GUIManager(FachadaAplicacion fa) {
        this.fa = fa;
    }

    public void mostrarVentanaLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("FXML/Login.fxml")
            );
            if (stage != null)
                stage.close();
            Scene scene = new Scene(loader.load(), 310, 188);
            this.stage = new Stage();
            LoginController controller = loader.<LoginController>getController();
            controller.initManager(this);
            stage.setTitle("Login");
            stage.setScene(scene);
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
            this.stage.close();
            Scene scene = new Scene(loader.load(), 600, 400);
            stage = new Stage();
            ContableController controller =
                    loader.<ContableController>getController();
            controller.initUser(this, usuario);
            stage.setTitle("Ventana Contable");
            stage.setScene(scene);
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
            this.stage.close();
            Scene scene = new Scene(loader.load(), 600, 400);
            stage = new Stage();
            CoidadorController controller =
                    loader.<CoidadorController>getController();
            controller.initUser(this, usuario);
            stage.setTitle("Ventana Coidador");
            stage.setScene(scene);
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
        } else {
            return false;
        }
    }

}
