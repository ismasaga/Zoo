package aplicacion.Controller;

import aplicacion.Usuario;
import gui.GUIManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class CoidadorController implements Initializable {
    @FXML
    Button sesionButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initUser(final GUIManager GUIManager, Usuario usuario) {
        //sessionLabel.setText(sessionID);
        sesionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                GUIManager.logout();
            }
        });
    }
}
