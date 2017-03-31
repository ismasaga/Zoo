package aplicacion.Manager;

import aplicacion.Controller.LoginController;
import aplicacion.FachadaAplicacion;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by miguel on 31/03/17.
 */
public class LoginManager {
    private Scene scene;
    private FachadaAplicacion fa;

    public LoginManager(Scene scene, FachadaAplicacion fa) {
        this.scene = scene;
        this.fa = fa;
    }

    public void mostrarVentanaLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../FXML/Login.fxml")
            );
            scene.setRoot((Parent) loader.load());
            LoginController controller = loader.<LoginController>getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logeado(String name, String pass) {
        System.out.println(fa.comprobarAutentificacion(name, pass).getId());


    }
}
