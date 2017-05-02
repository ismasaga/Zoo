package aplicacion.Controller;

import aplicacion.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContableController implements Initializable {
    ObservableList<Animal> animales = FXCollections.observableArrayList();
    ObservableList<Aviso> avisos = FXCollections.observableArrayList();
    @FXML
    private Button sesionButton;
    @FXML
    private Button sairButton;
    @FXML
    private Button buscarButton;
    @FXML
    private Button buttonEliminar;
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonResolver;
    @FXML
    private Button buttonReabrir;
    @FXML
    private Button buttonEliminarAviso;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldID;
    @FXML
    private TextField textFieldEdad;
    @FXML
    private TextField textFieldEspecie;
    @FXML
    private TextArea textAreaDescripcion;
    @FXML
    private TextArea textAreaTratamento;
    @FXML
    private ChoiceBox choiceBoxArea;
    @FXML
    private ChoiceBox choiceBoxXaula;
    @FXML
    private TextField buscarTextField;
    @FXML
    private Button todosButton;
    @FXML
    private Pane panelAnimaisTabla;
    @FXML
    private ChoiceBox choiceBoxSexo;
    @FXML
    private TextField textFieldPeso;
    @FXML
    private TableView tablaAnimais;

    private TableColumn<Animal, Integer> animalFirst = new TableColumn<Animal, Integer>("ID");
    private TableColumn<Animal, String> animalSecond = new TableColumn<Animal, String>("Nombre");
    private TableColumn<Animal, String> animalThird = new TableColumn<Animal, String>("Especie");

    @FXML
    private TableView tablaAvisos;

    private TableColumn<Aviso, String> avisoFirst = new TableColumn<Aviso, String>("Suxeito");
    private TableColumn<Aviso, String> avisoSecond = new TableColumn<Aviso, String>("Asunto");
    private TableColumn<Aviso, String> avisoThird = new TableColumn<Aviso, String>("Data");
    private TableColumn<Aviso, String> avisoFourth = new TableColumn<Aviso, String>("Resolto");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxSexo.setItems(FXCollections.observableArrayList("Macho", "Femia"));
    }

    public void initUser(final FachadaAplicacion fa, Usuario usuario) throws IOException {
        //sessionLabel.setText(sessionID);
        tablaAnimais = (FXMLLoader.load(getClass().getResource("/gui/FXML/TaboaAnimais.fxml")));
        panelAnimaisTabla.getChildren().add(tablaAnimais);
        animalFirst.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        animalSecond.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        animalThird.setCellValueFactory(cellData -> cellData.getValue().especieProperty());
        tablaAnimais.getColumns().add(animalFirst);
        tablaAnimais.getColumns().add(animalSecond);
        tablaAnimais.getColumns().add(animalThird);

        avisoFirst.setCellValueFactory(cellData -> cellData.getValue().suxeitoProperty());
        avisoSecond.setCellValueFactory(cellData -> cellData.getValue().asuntoProperty());
        avisoThird.setCellValueFactory(cellData -> cellData.getValue().dataInicioProperty());
        avisoFourth.setCellValueFactory(cellData -> cellData.getValue().dataFinProperty());
        tablaAvisos.getColumns().add(avisoFirst);
        tablaAvisos.getColumns().add(avisoSecond);
        tablaAvisos.getColumns().add(avisoThird);
        tablaAvisos.getColumns().add(avisoFourth);


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
                animales.removeAll();
                String animal = buscarTextField.getText();
                animales = fa.buscarAnimal(animal);
                tablaAnimais.setItems(animales);
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


        tablaAnimais.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (tablaAnimais.getSelectionModel().getSelectedItem() != null) {
                    Animal animal = (Animal) tablaAnimais.getSelectionModel().getSelectedItem();
                    textFieldNombre.setText(animal.getNombre());
                    textFieldID.setText(String.valueOf(animal.getId()));
                    textFieldEspecie.setText(animal.getEspecie());
                    textFieldEdad.setText(String.valueOf(animal.getEdad()));
                    choiceBoxArea.setItems(updateAreas(fa));
                    choiceBoxArea.getSelectionModel().select(choiceBoxArea.getItems().indexOf(String.valueOf(animal.getArea())));
                    textFieldPeso.setText(String.valueOf(animal.getPeso()));
                    choiceBoxXaula.setItems(updateXaulas(fa, animal.getArea()));
                    choiceBoxXaula.getSelectionModel().select(choiceBoxXaula.getItems().indexOf(String.valueOf(animal.getXaula())));
                    if (animal.getSexo().equals("Macho"))
                        choiceBoxSexo.getSelectionModel().select(0);
                    else
                        choiceBoxSexo.getSelectionModel().select(1);
                }
            }
        });

        tablaAvisos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (tablaAvisos.getSelectionModel().getSelectedItem() != null) {
                    Aviso aviso = (Aviso) tablaAvisos.getSelectionModel().getSelectedItem();
                    textAreaDescripcion.setText(aviso.getDescripcion());
                }
            }
        });

        buttonResolver.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fa.resolverAviso((Aviso) tablaAvisos.getSelectionModel().getSelectedItem());
            }
        });

        choiceBoxArea.valueProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    choiceBoxXaula.setItems(updateXaulas(fa, Integer.valueOf(newValue.toString())));
                    choiceBoxXaula.getSelectionModel().clearSelection();
                }
            }
        });


        buttonGuardar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (comprobarCampos(fa)) {
                    if (textFieldID.getText().matches("^\\d+$")) {
                        Animal animal = new Animal(Integer.valueOf(textFieldID.getText()), textFieldNombre.getText(), textFieldEspecie.getText(), Integer.valueOf(textFieldEdad.getText()), Integer.valueOf(textFieldPeso.getText()), choiceBoxSexo.getSelectionModel().getSelectedItem().toString(), Integer.valueOf(choiceBoxArea.getSelectionModel().getSelectedItem().toString()), Integer.valueOf(choiceBoxXaula.getSelectionModel().getSelectedItem().toString()), new ArrayList<Comida>());
                        fa.updateAnimal(animal);
                        buscarButton.fire();
                    } else fa.muestraExcepcion("El ID debe ser numérico");
                }
            }
        });

        buttonEliminar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (tablaAnimais.getSelectionModel().getSelectedItem() != null) {
                    fa.borrarAnimal(((Animal) tablaAnimais.getItems().get(tablaAnimais.getSelectionModel().getSelectedIndex())).getId());
                    buscarButton.fire();
                    textFieldNombre.setText("");
                    textFieldID.setText("");
                    textFieldEspecie.setText("");
                    textFieldEdad.setText("");
                    choiceBoxArea.getSelectionModel().clearSelection();
                    textFieldPeso.setText("");
                    choiceBoxXaula.getSelectionModel().clearSelection();
                } else fa.muestraExcepcion("Selecciona un animal en la tabla para borrarlo");
            }
        });


        buscarButton.fire();
        updateAvisos(fa);
    }

    private boolean comprobarCampos(FachadaAplicacion fa) {
        String texto = "";
        if (textFieldID.getText().equals("")) {
            texto = texto + "ID, ";
        }
        if (textFieldNombre.getText().equals("")) {
            texto = texto + "NOMBRE, ";
        }
        if (choiceBoxArea.getSelectionModel().getSelectedItem().toString().equals("")) {
            texto = texto + "AREA, ";
        }
        if (textFieldEdad.getText().equals("")) {
            texto = texto + "EDAD, ";
        }
        if (textFieldPeso.getText().equals("")) {
            texto = texto + "PESO, ";
        }
        if (textFieldEspecie.getText().equals("")) {
            texto = texto + "ESPECIE, ";
        }
        if (choiceBoxXaula.getSelectionModel().getSelectedItem().toString().equals("")) {
            texto = texto + "XAULA, ";
        }
        if (!texto.isEmpty()) {
            texto = "Os campos: " + texto + " están vacíos";
            fa.muestraExcepcion(texto);
        }
        return texto.isEmpty();
    }

    private ObservableList updateAreas(FachadaAplicacion fa) {
        return fa.updateAreas();
    }

    private ObservableList updateXaulas(FachadaAplicacion fa, Integer area) {
        return fa.updateXaulas(area);
    }

    private void updateAvisos(FachadaAplicacion fa) {
        avisos.removeAll();
        avisos = fa.buscarAvisos();
        tablaAvisos.setItems(avisos);
    }
}