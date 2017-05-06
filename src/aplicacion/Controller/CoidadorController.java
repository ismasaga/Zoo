package aplicacion.Controller;

import aplicacion.Animal;
import aplicacion.Aviso;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class CoidadorController implements Initializable {

    // Lista de animais
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
    private TableColumn<Animal, Integer> first = new TableColumn<Animal, Integer>("ID");
    private TableColumn<Animal, String> second = new TableColumn<Animal, String>("Nombre");
    private TableColumn<Animal, String> third = new TableColumn<Animal, String>("Especie");
    private TableColumn<Animal, Integer> fourth = new TableColumn<Animal, Integer>("Edad");
    // Lista de incidencias
    ObservableList<Aviso> incidencias = FXCollections.observableArrayList();
    // Taboa superior(grande) da pestanha Incidencias
    @FXML
    private TableView tablaIncidencias;
    private TableColumn<Aviso, String> incidenciaColUn = new TableColumn<>("Suxeito");
    private TableColumn<Aviso, String> incidenciaColDous = new TableColumn<>("Asunto");
    private TableColumn<Aviso, String> incidenciaColTres = new TableColumn<>("Data");
    @FXML
    private TextField textAsuntoIncidencia;
    @FXML
    private TextArea textDescripIncidencia;
    @FXML
    private Button buttonNovo;
    @FXML
    private Button buttonEliminar;
    @FXML
    private Label labelTaboaElementos;
    @FXML
    private ToggleGroup grupoRadioButtons;
    // Usoo para saber cal esta pulsado, non esta ligado ca interfaz
    private RadioButton selectedRadioButton;
    // Lista de incidencias
    ObservableList<Aviso> elementos = FXCollections.observableArrayList();
    // Taboa pequena de incidencias
    @FXML
    private TableView taboaElementos;
    private TableColumn<Animal, Integer> elemUnAnimal = new TableColumn<>("Identificador");
    private TableColumn<Animal, String> elemDousAnimal = new TableColumn<>("Nome");
    //private TableColumn<Aviso, String> elemUnXaula;
    //private TableColumn<Aviso, String> elemDousXaula;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initUser(final FachadaAplicacion fa, Usuario usuario) throws IOException {
        // Inicializamos a taboa de incidencias(pestanha incidencias)
        incidenciaColUn.setCellValueFactory(cellData -> cellData.getValue().suxeitoProperty());
        incidenciaColDous.setCellValueFactory(cellData -> cellData.getValue().asuntoProperty());
        incidenciaColTres.setCellValueFactory(cellData -> cellData.getValue().dataInicioProperty());
        tablaIncidencias.getColumns().add(incidenciaColUn);
        tablaIncidencias.getColumns().add(incidenciaColDous);
        tablaIncidencias.getColumns().add(incidenciaColTres);
        // Inicializamos a taboa de elementos(pestanha incidencias)
        elemUnAnimal.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        elemDousAnimal.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        // Pestanha pechar sesion
        sesionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fa.logout();
            }
        });
        // Pestanha pechar sesion
        sairButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fa.sair();
            }
        });
        // Pestanha animais
        buscarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    tabla = (FXMLLoader.load(getClass().getResource("/gui/FXML/TaboaAnimais.fxml")));
                    panelAnimaisTabla.getChildren().add(tabla);
                    String animal = buscarTextField.getText();
                    animales = fa.buscarAnimal(animal);
                    tabla.setItems(animales);
                    first.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
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
        // Pestanha animais
        buscarTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    buscarButton.fire();
                }
            }
        });
        // Pestanha animais
        todosButton.setOnAction(event -> {
            buscarTextField.setText("");
            buscarButton.fire();
        });
        // Pestanha Incidencias
        tablaIncidencias.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Se consigo obter a taboa
                if (tablaIncidencias.getSelectionModel().getSelectedItem() != null) {
                    // Obtenho o aviso pulsado
                    Aviso aviso = (Aviso) tablaIncidencias.getSelectionModel().getSelectedItem();
                    // Meto o texto de descripcion e asunto onde corresponde
                    textDescripIncidencia.setText(aviso.getDescripcion());
                    textAsuntoIncidencia.setText(aviso.getAsunto());
                } else {
                    fa.muestraExcepcion("Hay problemas coa táboa superior.");
                }
            }
        });
        // Pestanha Incidencias
        taboaElementos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Se consigo obter a taboa
                /*if (taboaElementos.getSelectionModel().getSelectedItem() != null) {
                    // Obtenho o aviso pulsado
                    Aviso aviso = (Aviso) taboaElementos.getSelectionModel().getSelectedItem();
                    // Meto o texto de descripcion e asunto onde corresponde
                    textDescripIncidencia.setText(aviso.getDescripcion());
                    textAsuntoIncidencia.setText(aviso.getAsunto());
                }*/
            }
        });
        // Pestanha incidencias
        buttonNovo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Segun o radiobutton que este pulsado
                switch (selectedRadioButton.getText()) {
                    case "Animais":
                        // Se consigo obter a taboa
                        if (taboaElementos.getSelectionModel().getSelectedItem() != null) {
                            // Meto novo aviso mandandolle o animal
                            //fa.novoAvisoAnimal(new Aviso()(Animal) tablaIncidencias.getSelectionModel().getSelectedItem());
                        } else {
                            fa.muestraExcepcion("Debes seleccionar un animal na táboa");
                        }
                        break;
                    case "Xaulas":
                        break;
                    case "Areas":
                        break;
                    case "Todos":
                        fa.muestraExcepcion("Debe seleccionar o tipo de incidencia antes de insertala pulsando un dos botóns superiores(Animais, Xaulas ou áreas).");
                        break;
                }
            }
        });
        // Pestanha incidencias
        buttonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Se consigo obter a taboa
                if (tablaIncidencias.getSelectionModel().getSelectedItem() != null) {
                    // Obtenho o aviso pulsado
                    Aviso aviso = (Aviso) tablaIncidencias.getSelectionModel().getSelectedItem();
                    // Borro o aviso en cuestión
                    fa.borrarAviso(aviso);
                } else {
                    fa.muestraExcepcion("Debe seleccionar un elemento na táboa superior para poder eliminalo.");
                }
            }
        });
        // Pestanha Incidencias
        grupoRadioButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                // Obtemos o radioButton que se pulsou
                selectedRadioButton = (RadioButton) grupoRadioButtons.getSelectedToggle();
                // Actualizamos a taboa superior cos datos pertinentes
                updateTaboas(fa);
            }
        });
        // Executa o manexador de dito boton(Pestanha animais)
        buscarButton.fire();
        // Obtemos o radioButton que se ven pulsado por defecto
        selectedRadioButton = (RadioButton) grupoRadioButtons.getSelectedToggle();
        // Na primeira execucion recheo a taboa coas incidencias pertinentes
        updateTaboas(fa);
        // Como a taboa de elementos vai estar valeira..(pestanha incidencias)
        labelTaboaElementos.setText("Valeiro :");
    }

    // Funcion para actualizar as taboas da pestanha incidencias
    private void updateTaboas(FachadaAplicacion fa) {
        // Borro os datos obsoletos
        incidencias.removeAll();
        elementos.removeAll();
        // Elimino as columnas da taboa de elementos
        taboaElementos.getColumns().clear();
        // Segun o radiobutton que este pulsado
        switch (selectedRadioButton.getText()) {
            case "Animais":
                // Cambio o label da taboa
                labelTaboaElementos.setText("Animais :");
                // Obtenho as incidencias de animais que engadiu o coidador actual
                incidencias = fa.buscarAvisosAnimais();
                // Obtenmos os animais que coida o coidador actual
                elementos = fa.buscarAnimaisCoidador();
                // Metemos na taboa as columnas
                taboaElementos.getColumns().add(elemUnAnimal);
                taboaElementos.getColumns().add(elemDousAnimal);
                break;
            case "Xaulas":
                // Cambio o label da taboa
                labelTaboaElementos.setText("Xaulas :");
                // Obtenho as incidencias de xaulas que engadiu o coidador actual
                incidencias = fa.buscarAvisosXaulas();
                break;
            case "Areas":
                // Cambio o label da taboa
                labelTaboaElementos.setText("Areas :");
                // Obtenho as incidencias de areas que engadiu o coidador actual
                incidencias = fa.buscarAvisosAreas();
                break;
            case "Todos":
                // Cambio o label da taboa
                labelTaboaElementos.setText("Animais :");
                // Obtenho todas as incidencias que engadiu o coidador logeado
                incidencias = fa.buscarAvisosPropios();
                break;
        }
        // Meto o novo contido nas taboas
        tablaIncidencias.setItems(incidencias);
        taboaElementos.setItems(elementos);
    }
}
