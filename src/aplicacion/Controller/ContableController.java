package aplicacion.Controller;

import aplicacion.Manager.LoginManager;
import aplicacion.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by miguel on 31/03/17.
 */
public class ContableController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void initUser(final LoginManager loginManager, Usuario usuario) {
        /*sessionLabel.setText(sessionID);
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loginManager.logout();
            }
        });
    */}
}
