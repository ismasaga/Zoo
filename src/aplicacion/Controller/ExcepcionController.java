package aplicacion.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by miguel on 29/04/17.
 */
public class ExcepcionController implements Initializable {
    @FXML
    private javafx.scene.control.Label excepcionLabel;

    @FXML
    private Button buttonOK;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initExcepcion(String error, Stage stage) {
        excepcionLabel.setText(error);
        this.stage = stage;

        buttonOK.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
    }


}
