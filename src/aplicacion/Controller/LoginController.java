/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.Controller;

import aplicacion.Manager.LoginManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sun.util.resources.be.LocaleNames_be;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author miguel
 */
public class LoginController implements Initializable {

    @FXML
    private TextField dni;
    @FXML
    private Label error;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initManager(final LoginManager loginManager) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!loginManager.logeado(dni.getText(), passwordField.getText())){
                    error.setText("Usuario o clave incorrectos");
                }
                else{
                    error.setText("");
                }
            }
        });
    }

}
