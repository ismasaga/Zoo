package aplicacion.Controller;

import aplicacion.Animal;
import aplicacion.TipoUsuario;
import aplicacion.Usuario;
import gui.GUIManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CoidadorController implements Initializable {
    @FXML
    private Button sesionButton;
    @FXML
    private Button sairButton;
    @FXML
    private Button buscarButton;
    @FXML
    private Button todosButton;
    @FXML
    private VBox tarefasVBox;
    @FXML
    private Pane panelAnimaisTabla;
    @FXML
    private TableView tabla;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initUser(final GUIManager GUIManager, Usuario usuario) throws IOException {
        //sessionLabel.setText(sessionID);
        sesionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                GUIManager.logout();

            }
        });
        sairButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                GUIManager.sair();

            }
        });

        buscarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    tabla = (FXMLLoader.load(getClass().getResource("../../gui/FXML/TaboaAnimais.fxml")));
                    panelAnimaisTabla.getChildren().add(tabla);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



    }
}
