package aplicacion.Controller;

import aplicacion.Animal;
import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CoidadorController implements Initializable {
    ObservableList<Animal> animales = FXCollections.observableArrayList();
    @FXML
    private Button sesionButton;
    @FXML
    private Button sairButton;
    @FXML
    private Button buscarButton;
    @FXML
    private TextField buscarTextField;
    @FXML
    private Button todosButton;
    @FXML
    private VBox tarefasVBox;
    @FXML
    private Pane panelAnimaisTabla;
    @FXML
    private TableView tabla;
    private TableColumn<Animal, String> first = new TableColumn<Animal, String>("ID");
    private TableColumn<Animal, String> second = new TableColumn<Animal, String>("Nombre");
    private TableColumn<Animal, String> third = new TableColumn<Animal, String>("Especie");
    private TableColumn<Animal, Integer> fourth = new TableColumn<Animal, Integer>("Edad");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initUser(final FachadaAplicacion fa, Usuario usuario) throws IOException {
        //sessionLabel.setText(sessionID);
        sesionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fa.logout();

            }
        });
        sairButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fa.sair();

            }
        });

        buscarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    tabla = (FXMLLoader.load(getClass().getResource("/gui/FXML/TaboaAnimais.fxml")));
                    panelAnimaisTabla.getChildren().add(tabla);
                    String animal = buscarTextField.getText();
                    animales = fa.buscarAnimal(animal);
                    tabla.setItems(animales);
                    first.setCellValueFactory(cellData -> cellData.getValue().idProperty());
                    second.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
                    third.setCellValueFactory(cellData -> cellData.getValue().especieProperty());
                    tabla.getColumns().add(first);
                    tabla.getColumns().add(second);
                    tabla.getColumns().add(third);
                    tabla.getSelectionModel().select(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        buscarTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    buscarButton.fire();
                }
            }
        });

        todosButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buscarTextField.setText("");
                buscarButton.fire();
            }
        });

        buscarButton.fire();


    }
}


/******************************************************************************/

/*package aplicacion.Controller;

import aplicacion.Animal;
import aplicacion.Usuario;
import gui.FachadaGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CoidadorController {

    //ObservableList<Animal> animales = FXCollections.observableArrayList();
    @FXML
    private Button sesionButton;
    @FXML
    private Button sairButton;
    @FXML
    private Button buscarButton;
    @FXML
    private TextField buscarTextField;
    @FXML
    private Button todosButton;
    @FXML
    private VBox tarefasVBox;
    @FXML
    private Pane panelAnimaisTabla;
    @FXML
    private TableView<Animal> tabla;
    @FXML
    private TableColumn<Animal, String> colId;
    @FXML
    private TableColumn<Animal, String> colName;
    //@FXML
    //private TableColumn<Animal, String> third = new TableColumn<Animal, String>("Especie");
    //@FXML
    //private TableColumn<Animal, Integer> fourth = new TableColumn<Animal, Integer>("Edad");
    private FachadaGUI FachadaGUI;

    @FXML
    private void initialize() {*/
        /* Dende aqui non podo facer nada porque non tenho maneira de facer
         * consultas nin de chamar a ningen
         */
    /*}

    public void initUser(final FachadaGUI FachadaGUI, Usuario usuario) throws IOException {
        // Gardo a referencia o FachadaGUI para poder chamalo dende calquer metodo
        this.FachadaGUI = FachadaGUI;
        // Fago a primeira busca para ter os datos cargados por defecto
        tabla.setItems(FachadaGUI.buscarAnimal(""));
        //Referenciamos as columnas da vista
        this.colId.setCellValueFactory(animal -> animal.getValue().idProperty());
        this.colName.setCellValueFactory(animal -> animal.getValue().nombreProperty());
        //sessionLabel.setText(sessionID);
        sesionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FachadaGUI.logout();

            }
        });
        sairButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FachadaGUI.sair();

            }
        });

        buscarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    tabla = (FXMLLoader.load(getClass().getResource("/gui/FXML/TaboaAnimais.fxml")));
                    panelAnimaisTabla.getChildren().add(tabla);
                    String animal = buscarTextField.getText();
                    animales = FachadaGUI.buscarAnimal(animal);
                    tabla.setItems(animales);
                    first.setCellValueFactory(cellData -> cellData.getValue().idProperty());
                    second.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
                    third.setCellValueFactory(cellData -> cellData.getValue().especieProperty());
                    tabla.getColumns().add(first);
                    tabla.getColumns().add(second);
                    tabla.getColumns().add(third);
                    tabla.getSelectionModel().select(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        buscarTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    buscarButton.fire();
                }
            }
        });

        todosButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buscarTextField.setText("");
                buscarButton.fire();
            }
        });
        buscarButton.fire();
    }

    @FXML
    private void buscarAnimais() {
        String animal = buscarTextField.getText();
        tabla.setItems(FachadaGUI.buscarAnimal(animal));
        try {
            tabla = (FXMLLoader.load(getClass().getResource("/gui/FXML/TaboaAnimais.fxml")));
            panelAnimaisTabla.getChildren().add(tabla);
            String animal = buscarTextField.getText();
            animales = FachadaGUI.buscarAnimal(animal);
            tabla.setItems(animales);
            first.setCellValueFactory(cellData -> cellData.getValue().idProperty());
            second.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
            third.setCellValueFactory(cellData -> cellData.getValue().especieProperty());
            tabla.getColumns().add(first);
            tabla.getColumns().add(second);
            tabla.getColumns().add(third);
            tabla.getSelectionModel().select(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
