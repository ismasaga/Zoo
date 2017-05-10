package aplicacion.Controller;

import aplicacion.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.util.ResourceBundle;

public class ContableController implements Initializable {

    ObservableList<Animal> animales = FXCollections.observableArrayList();
    ObservableList<Aviso> avisos = FXCollections.observableArrayList();
    ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    ObservableList<Area> areas = FXCollections.observableArrayList();
    Boolean novaXaula = true;
    Boolean cancelar = false;
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
    @FXML
    private TextField textFieldBuscarUsuario;
    @FXML
    private Button buttonBuscarUsuario;
    @FXML
    private Button buttonVerTodosUsuarios;
    @FXML
    private TextField textFieldNomeUsuario;
    @FXML
    private TextField textFieldDniUsuario;
    @FXML
    private TextField textFieldPassUsuario;
    @FXML
    private TextField textFieldEmailUsuario;
    @FXML
    private TextField textFieldTlfUsuario;
    @FXML
    private TextField textFieldIDArea;
    @FXML
    private TextField textFieldIDXaula;
    @FXML
    private TextField textFieldClimatizacion;
    @FXML
    private Label labelAnimaisDentro;
    @FXML
    private Label labelEspeciesDentro;
    @FXML
    private ChoiceBox choiceBoxAreaDestino;
    @FXML
    private ChoiceBox choiceBoxXaulaDestino;
    @FXML
    private ChoiceBox choiceBoxAnimal;
    @FXML
    private Button buttonGardarArea;
    @FXML
    private Button buttonNovaXaula;
    @FXML
    private Button buttonBorrarArea;
    @FXML
    private Button buttonBorrarXaula;
    @FXML
    private Button buttonTransferir;
    @FXML
    private ChoiceBox choiceBoxTipoUsuario;
    @FXML
    private Button buttonNovoUsuario;
    @FXML
    private Button buttonGardarUsuario;
    @FXML
    private Button buttonEliminarUsuario;
    @FXML
    private TableView taboaUsuarios;
    @FXML
    private TableView tablaAreas;
    @FXML
    private TableView tablaXaulas;
    @FXML
    private ChoiceBox choiceBoxCoidador;
    @FXML
    private TableView taboaComidas;
    @FXML
    private TableView taboaAnimaisComida;
    @FXML
    private TableView taboaOutrosAnimaisComida;
    @FXML
    private Label labelUds;
    @FXML
    private Button buttonNovaComida;
    @FXML
    private Button buttonGardarComida;
    @FXML
    private Button buttonEliminarComida;
    @FXML
    private TextField textFieldIdComida;
    @FXML
    private TextField textFieldNomeComida;
    @FXML
    private Tab pestanhaAnimais;
    @FXML
    private TextField textFieldStockComida;
    @FXML
    private TextField textFieldUnidadesComida;
    @FXML
    private Button buttonCambiarCantidadeComida;
    @FXML
    private Button buttonEngadirAnimalComida;
    @FXML
    private Button buttonQuitarAnimalComida;
    @FXML
    private TextField textFieldCantidadeComida;

    private TableColumn<Xaula, Integer> xaulaFirst = new TableColumn<Xaula, Integer>("ID");

    private TableColumn<Area, Integer> areaFirst = new TableColumn<Area, Integer>("ID");
    private TableColumn<Area, String> areaSecond = new TableColumn<Area, String>("Climatizacion");

    private TableColumn<Animal, Integer> animalFirst = new TableColumn<Animal, Integer>("ID");
    private TableColumn<Animal, String> animalSecond = new TableColumn<Animal, String>("Nombre");
    private TableColumn<Animal, String> animalThird = new TableColumn<Animal, String>("Especie");
    private TableColumn<Usuario, String> columnaNomeUsuario = new TableColumn<Usuario, String>("Nome");
    private TableColumn<Usuario, String> columnaDniUsuario = new TableColumn<Usuario, String>("DNI");

    private TableColumn<Comida, String> columnaNomeComida = new TableColumn<Comida, String>("Nome");
    private TableColumn<Comida, Integer> columnaStockComida = new TableColumn<Comida, Integer>("Stock");

    private TableColumn<Animal, String> columnaAnimaisComida = new TableColumn<Animal, String>("Animais que comen");

    private TableColumn<Animal, String> columnaOutrosAnimais = new TableColumn<Animal, String>("Outros animais");

    @FXML
    private TableView tablaAvisos;

    private TableColumn<Aviso, String> avisoFirst = new TableColumn<Aviso, String>("Suxeito");
    private TableColumn<Aviso, String> avisoSecond = new TableColumn<Aviso, String>("Asunto");
    private TableColumn<Aviso, String> avisoThird = new TableColumn<Aviso, String>("Data");
    private TableColumn<Aviso, String> avisoFourth = new TableColumn<Aviso, String>("Resolto");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxSexo.setItems(FXCollections.observableArrayList("Macho", "Femia"));
        choiceBoxTipoUsuario.setItems(FXCollections.observableArrayList("Coidador", "Contable"));
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

        columnaNomeUsuario.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaDniUsuario.setCellValueFactory(cellData -> cellData.getValue().dniProperty());
        taboaUsuarios.getColumns().add(columnaNomeUsuario);
        taboaUsuarios.getColumns().add(columnaDniUsuario);

        columnaNomeComida.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaStockComida.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        taboaComidas.getColumns().add(columnaNomeComida);
        taboaComidas.getColumns().add(columnaStockComida);

        columnaAnimaisComida.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        taboaAnimaisComida.getColumns().add(columnaAnimaisComida);

        columnaOutrosAnimais.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        taboaOutrosAnimaisComida.getColumns().add(columnaOutrosAnimais);

        areaFirst.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        areaSecond.setCellValueFactory(cellData -> cellData.getValue().climatizacionProperty());
        tablaAreas.getColumns().add(areaFirst);
        tablaAreas.getColumns().add(areaSecond);

        xaulaFirst.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        tablaXaulas.getColumns().add(xaulaFirst);

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

        pestanhaAnimais.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
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
                    ObservableList e = updateAreas(fa);
                    ObservableList z = FXCollections.observableArrayList();
                    for (int i = 0; i < e.size(); i++) {
                        z.add(String.valueOf(((Area) e.get(i)).getId()));
                    }
                    choiceBoxArea.setItems(z);
                    choiceBoxArea.getSelectionModel().select(choiceBoxArea.getItems().indexOf(String.valueOf(animal.getArea())));
                    textFieldPeso.setText(String.valueOf(animal.getPeso()));
                    e = updateXaulas(fa, animal.getArea());
                    ObservableList x = FXCollections.observableArrayList();
                    for (int i = 0; i < e.size(); i++) {
                        x.add(((Xaula) e.get(i)).getId());
                    }
                    choiceBoxXaula.setItems(x);
                    choiceBoxXaula.getSelectionModel().select(choiceBoxXaula.getItems().indexOf(animal.getXaula()));
                    choiceBoxCoidador.getSelectionModel().select(choiceBoxCoidador.getItems().indexOf(animal.getIdCoidador()));

                    if (animal.getSexo().equals("Macho")) {
                        choiceBoxSexo.getSelectionModel().select(0);
                    } else {
                        choiceBoxSexo.getSelectionModel().select(1);
                    }
                }
            }
        });

        tablaAvisos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (tablaAvisos.getSelectionModel().getSelectedItem() != null) {
                    Aviso aviso = (Aviso) tablaAvisos.getSelectionModel().getSelectedItem();
                    textAreaDescripcion.setText(aviso.getDescripcion());
                    textAreaTratamento.setText(aviso.getTratamento());

                    if (aviso.getDataFin().equals("Non")) {
                        buttonReabrir.setDisable(true);
                        buttonResolver.setDisable(false);
                    } else {
                        buttonReabrir.setDisable(false);
                        buttonResolver.setDisable(true);
                    }
                }
            }
        });

        tablaAreas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Integer integer = ((Area) tablaAreas.getSelectionModel().getSelectedItem()).getId();
                tablaXaulas.setItems(updateXaulas(fa, integer));
                buttonBorrarXaula.setDisable(true);
                buttonNovaXaula.setDisable(false);
                buttonBorrarArea.setDisable(false);
                buttonGardarArea.setDisable(false);
                choiceBoxAreaDestino.setDisable(true);
                choiceBoxXaulaDestino.setDisable(true);
                buttonTransferir.setDisable(true);
                textFieldIDXaula.setText("");
                labelAnimaisDentro.setText("-");
                labelEspeciesDentro.setText("-");
                textFieldIDArea.setDisable(false);
                textFieldClimatizacion.setDisable(false);
                textFieldIDArea.setText(String.valueOf(((Area) tablaAreas.getSelectionModel().getSelectedItem()).getId()));
                textFieldClimatizacion.setText(((Area) tablaAreas.getSelectionModel().getSelectedItem()).getClimatizacion());
            }
        });

        buttonNovaXaula.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (novaXaula) {
                    textFieldIDArea.setDisable(true);
                    textFieldClimatizacion.setDisable(true);
                    textFieldIDXaula.setDisable(false);
                    buttonNovaXaula.setText("Guardar");
                    buttonBorrarXaula.setText("Cancelar");
                    buttonBorrarXaula.setDisable(false);
                    buttonGardarArea.setDisable(true);
                    buttonBorrarArea.setDisable(true);
                    tablaAreas.setDisable(true);
                    tablaXaulas.setDisable(true);
                    choiceBoxAreaDestino.setDisable(true);
                    choiceBoxXaulaDestino.setDisable(true);
                    buttonTransferir.setDisable(true);
                    labelAnimaisDentro.setText("-");
                    labelEspeciesDentro.setText("-");
                    novaXaula = false;
                    cancelar = true;
                } else {
                    novaXaula = true;
                    cancelar = false;
                    textFieldIDArea.setDisable(false);
                    textFieldClimatizacion.setDisable(false);
                    textFieldIDXaula.setDisable(true);
                    buttonNovaXaula.setText("Nova Xaula");
                    buttonBorrarXaula.setText("Borrar Xaula");
                    buttonBorrarXaula.setDisable(true);
                    buttonGardarArea.setDisable(false);
                    buttonBorrarArea.setDisable(false);
                    labelAnimaisDentro.setText("-");
                    labelEspeciesDentro.setText("-");
                    tablaAreas.setDisable(false);
                    tablaXaulas.setDisable(false);
                    fa.novaXaula(Integer.valueOf(textFieldIDXaula.getText()), ((Area) tablaAreas.getSelectionModel().getSelectedItem()).getId());
                    Integer integer = ((Area) tablaAreas.getSelectionModel().getSelectedItem()).getId();
                    tablaXaulas.setItems(updateXaulas(fa, integer));
                    textFieldIDXaula.setText("");
                }
            }
        });

        buttonBorrarXaula.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cancelar) {
                    novaXaula = true;
                    cancelar = false;
                    textFieldIDXaula.setDisable(true);
                    buttonNovaXaula.setText("Nova Xaula");
                    buttonBorrarXaula.setText("Borrar Xaula");
                } else {
                    fa.borrarXaula(((Xaula) tablaXaulas.getSelectionModel().getSelectedItem()).getId(), ((Xaula) tablaXaulas.getSelectionModel().getSelectedItem()).getIdArea());
                    Integer integer = ((Area) tablaAreas.getSelectionModel().getSelectedItem()).getId();
                    tablaXaulas.setItems(updateXaulas(fa, integer));
                }
                textFieldIDArea.setDisable(false);
                textFieldClimatizacion.setDisable(false);
                buttonBorrarXaula.setDisable(true);
                buttonGardarArea.setDisable(false);
                buttonBorrarArea.setDisable(false);
                buttonNovaXaula.setDisable(false);
                tablaAreas.setDisable(false);
                tablaXaulas.setDisable(false);
                textFieldIDXaula.setText("");
                labelAnimaisDentro.setText("-");
                labelEspeciesDentro.setText("-");
            }
        });

        buttonGardarArea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fa.novaArea(Integer.valueOf(textFieldIDArea.getText()), textFieldClimatizacion.getText());
                tablaAreas.setItems(updateAreas(fa));
                buttonNovaXaula.setDisable(true);
                buttonBorrarArea.setDisable(true);
            }
        });

        buttonBorrarArea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fa.borrarArea(Integer.valueOf(textFieldIDArea.getText()));
                tablaAreas.setItems(updateAreas(fa));
                buttonNovaXaula.setDisable(true);
                buttonBorrarArea.setDisable(true);
            }
        });

        tablaXaulas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                buttonBorrarArea.setDisable(true);
                buttonNovaXaula.setDisable(true);
                buttonBorrarXaula.setDisable(false);
                buttonGardarArea.setDisable(true);
                textFieldIDArea.setDisable(true);
                textFieldClimatizacion.setDisable(true);
                buttonTransferir.setDisable(false);
                choiceBoxAreaDestino.setDisable(false);
                choiceBoxXaulaDestino.setDisable(false);
                labelAnimaisDentro.setText(String.valueOf(fa.contarAnimais(((Xaula) tablaXaulas.getSelectionModel().getSelectedItem()).getId(), ((Area) tablaAreas.getSelectionModel().getSelectedItem()).getId())));
                labelEspeciesDentro.setText(String.valueOf(fa.contarEspecies(((Xaula) tablaXaulas.getSelectionModel().getSelectedItem()).getId(), ((Area) tablaAreas.getSelectionModel().getSelectedItem()).getId())));
                textFieldIDXaula.setText(String.valueOf(((Xaula) tablaXaulas.getSelectionModel().getSelectedItem()).getId()));
                ObservableList a = fa.updateAreas();
                ObservableList v = FXCollections.observableArrayList();
                for (int i = 0; i < a.size(); i++) {
                    v.add(((Area) a.get(i)).getId());
                }
                choiceBoxAreaDestino.setItems(v);
            }
        });

        buttonResolver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tablaAvisos.getSelectionModel().getSelectedItem() != null) {
                    Aviso aviso = (Aviso) tablaAvisos.getSelectionModel().getSelectedItem();
                    aviso.setTratamento(textAreaTratamento.getText());
                    fa.resolverAviso(aviso);
                    updateAvisos(fa);
                }
            }
        });

        buttonReabrir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tablaAvisos.getSelectionModel().getSelectedItem() != null) {
                    Aviso aviso = (Aviso) tablaAvisos.getSelectionModel().getSelectedItem();
                    fa.reabrirAviso(aviso);
                    updateAvisos(fa);
                    textAreaDescripcion.setText("");
                    textAreaTratamento.setText("");
                }
            }
        });

        buttonEliminarAviso.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tablaAvisos.getSelectionModel().getSelectedItem() != null) {
                    Aviso aviso = (Aviso) tablaAvisos.getSelectionModel().getSelectedItem();
                    fa.borrarAviso(aviso);
                    updateAvisos(fa);
                    textAreaDescripcion.setText("");
                    textAreaTratamento.setText("");
                }
            }
        });

        choiceBoxArea.valueProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    ObservableList e = updateXaulas(fa, Integer.valueOf(newValue.toString()));
                    ObservableList z = FXCollections.observableArrayList();
                    for (int i = 0; i < e.size(); i++) {
                        z.add(String.valueOf(((Xaula) e.get(i)).getId()));
                    }
                    choiceBoxXaula.setItems(z);
                    choiceBoxXaula.getSelectionModel().clearSelection();
                }
            }
        });

        choiceBoxAreaDestino.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    ObservableList e = updateXaulas(fa, Integer.valueOf(newValue.toString()));
                    ObservableList z = FXCollections.observableArrayList();
                    for (int i = 0; i < e.size(); i++) {
                        System.out.println(((Xaula) e.get(i)).getId() + ((Xaula) e.get(i)).getIdArea());
                        System.out.println(((Xaula) tablaXaulas.getSelectionModel().getSelectedItem()).getId() + ((Xaula) tablaXaulas.getSelectionModel().getSelectedItem()).getIdArea());
                        System.out.println();
                        if (((Xaula) e.get(i)).getId() != ((Xaula) tablaXaulas.getSelectionModel().getSelectedItem()).getId() || ((Xaula) e.get(i)).getIdArea() != ((Xaula) tablaXaulas.getSelectionModel().getSelectedItem()).getIdArea())
                            z.add(String.valueOf(((Xaula) e.get(i)).getId()));
                    }
                    choiceBoxXaulaDestino.setItems(z);
                    choiceBoxXaulaDestino.getSelectionModel().clearSelection();
                }

                buttonTransferir.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (!choiceBoxAreaDestino.getSelectionModel().isEmpty() && !choiceBoxXaulaDestino.getSelectionModel().isEmpty()) {
                            fa.transferirAnimais(((Area) tablaAreas.getSelectionModel().getSelectedItem()).getId(), Integer.valueOf(choiceBoxAreaDestino.getSelectionModel().getSelectedItem().toString()), ((Xaula) tablaXaulas.getSelectionModel().getSelectedItem()).getId(), Integer.valueOf(choiceBoxXaulaDestino.getSelectionModel().getSelectedItem().toString()));
                            tablaXaulas.getSelectionModel().clearSelection();
                        } else fa.muestraExcepcion("Marca os campos de area de destino e xaula de destino");
                    }
                });
            }
        });

        buttonGuardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (comprobarCampos(fa)) {
                    if (textFieldID.getText().matches("^\\d+$")) {
                        Animal animal = new Animal(Integer.valueOf(textFieldID.getText()), textFieldNombre.getText(), textFieldEspecie.getText(), Integer.valueOf(textFieldEdad.getText()), Integer.valueOf(textFieldPeso.getText()), choiceBoxSexo.getSelectionModel().getSelectedItem().toString(), Integer.valueOf(choiceBoxArea.getSelectionModel().getSelectedItem().toString()), Integer.valueOf(choiceBoxXaula.getSelectionModel().getSelectedItem().toString()), choiceBoxCoidador.getSelectionModel().getSelectedItem().toString());
                        fa.updateAnimal(animal);
                        buscarButton.fire();
                    } else {
                        fa.muestraExcepcion("El ID debe ser numérico");
                    }
                }
            }
        });

        buttonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
                    choiceBoxCoidador.getSelectionModel().clearSelection();
                    choiceBoxSexo.getSelectionModel().clearSelection();
                    choiceBoxXaula.setItems(FXCollections.observableArrayList());
                } else {
                    fa.muestraExcepcion("Selecciona un animal en la tabla para borrarlo");
                }
            }
        });

        buttonBuscarUsuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                usuarios.removeAll();
                String usuario = textFieldBuscarUsuario.getText();
                usuarios = updateUsuarios(fa, usuario);
                taboaUsuarios.setItems(usuarios);
            }
        });

        buttonNovoUsuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textFieldNomeUsuario.setText("");
                textFieldDniUsuario.setText("");
                textFieldPassUsuario.setText("");
                textFieldEmailUsuario.setText("");
                textFieldTlfUsuario.setText("");
                choiceBoxTipoUsuario.setDisable(false);
                choiceBoxTipoUsuario.getSelectionModel().clearSelection();

                buttonEliminarUsuario.setDisable(true);

                taboaUsuarios.getSelectionModel().clearSelection();
                buttonBuscarUsuario.fire();
            }
        });

        buttonGardarUsuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Comprobamos que todos os campos obrigatorios estean cubertos
                if (textFieldDniUsuario.getText() != null && !textFieldDniUsuario.getText().equals("")
                        && textFieldNomeUsuario.getText() != null && !textFieldNomeUsuario.getText().equals("")
                        && textFieldPassUsuario.getText() != null && !textFieldPassUsuario.getText().equals("")
                        && (choiceBoxTipoUsuario.getSelectionModel().getSelectedIndex() == 0
                        || choiceBoxTipoUsuario.getSelectionModel().getSelectedIndex() == 1)) {

                    Usuario usr = new Usuario(textFieldDniUsuario.getText(), textFieldNomeUsuario.getText(),
                            choiceBoxTipoUsuario.getSelectionModel().getSelectedIndex() == 0 ? TipoUsuario.Coidador : TipoUsuario.Contable,
                            textFieldPassUsuario.getText(), textFieldTlfUsuario.getText(), textFieldEmailUsuario.getText());

                    gardarUsuario(fa, usr);
                    buttonBuscarUsuario.fire();
                    buttonNovoUsuario.fire();

                } else {
                    fa.muestraExcepcion("Non se pode gardar o usuario; non se insertaron todos os campos obrigatorios.");
                }
            }
        });

        buttonEliminarUsuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Comprobamos que o DNI e o tipo de usuario non sexan null
                if (textFieldDniUsuario.getText() != null && !textFieldDniUsuario.getText().equals("")
                        && (choiceBoxTipoUsuario.getSelectionModel().getSelectedIndex() == 0
                        || choiceBoxTipoUsuario.getSelectionModel().getSelectedIndex() == 1)) {

                    Usuario usr = new Usuario(textFieldDniUsuario.getText(), choiceBoxTipoUsuario.getSelectionModel().getSelectedItem().toString().equals("Coidador") ? TipoUsuario.Coidador : TipoUsuario.Contable);
                    eliminarUsuario(fa, usr);

                    buttonBuscarUsuario.fire();
                    buttonNovoUsuario.fire();
                }
            }
        });

        taboaUsuarios.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (taboaUsuarios.getSelectionModel().getSelectedItem() != null) {
                    Usuario usr = (Usuario) taboaUsuarios.getSelectionModel().getSelectedItem();

                    textFieldNomeUsuario.setText(usr.getNombre());
                    textFieldDniUsuario.setText(usr.getDni());
                    textFieldEmailUsuario.setText(usr.getEmail());
                    textFieldPassUsuario.setText(usr.getPass());
                    textFieldTlfUsuario.setText(usr.getTelefono());
                    choiceBoxTipoUsuario.setDisable(true);

                    buttonEliminarUsuario.setDisable(false);

                    if (usr.getTipo().toString().equals("Coidador")) {
                        choiceBoxTipoUsuario.getSelectionModel().select(0);
                    } else {
                        choiceBoxTipoUsuario.getSelectionModel().select(1);

                    }
                }
            }
        });

        buttonVerTodosUsuarios.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                usuarios.removeAll();
                usuarios = updateUsuarios(fa, "");
                taboaUsuarios.setItems(usuarios);
            }
        });

        taboaComidas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (taboaComidas.getSelectionModel().getSelectedItem() != null) {
                    Comida c = (Comida) taboaComidas.getSelectionModel().getSelectedItem();

                    textFieldIdComida.setText(String.valueOf(c.getId()));
                    textFieldNomeComida.setText(c.getNombre());
                    textFieldStockComida.setText(String.valueOf(c.getStock()));
                    textFieldUnidadesComida.setText(c.getUds());

                    buttonNovaComida.setDisable(false);
                    buttonGardarComida.setDisable(false);
                    buttonEliminarComida.setDisable(false);

                    taboaAnimaisComida.setItems(updateAnimaisComida(fa, c));
                    taboaOutrosAnimaisComida.setItems(updateOutrosAnimaisComida(fa, c));

                    buttonEngadirAnimalComida.setDisable(true);
                    buttonCambiarCantidadeComida.setDisable(true);
                    buttonQuitarAnimalComida.setDisable(true);

                    textFieldCantidadeComida.setText("");

                    labelUds.setText(c.getUds());
                }
            }
        });

        taboaAnimaisComida.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (taboaAnimaisComida.getSelectionModel().getSelectedItem() != null) {
                    Comida c = (Comida) taboaComidas.getSelectionModel().getSelectedItem();
                    Animal a = (Animal) taboaAnimaisComida.getSelectionModel().getSelectedItem();

                    textFieldCantidadeComida.setText(String.valueOf(recuperarCantidade(fa, c, a)));

                    buttonEngadirAnimalComida.setDisable(true);
                    buttonCambiarCantidadeComida.setDisable(false);
                    buttonQuitarAnimalComida.setDisable(false);
                }
            }
        });

        taboaOutrosAnimaisComida.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (taboaOutrosAnimaisComida.getSelectionModel().getSelectedItem() != null) {
                    Comida c = (Comida) taboaComidas.getSelectionModel().getSelectedItem();

                    textFieldCantidadeComida.setText("");

                    buttonEngadirAnimalComida.setDisable(false);
                    buttonCambiarCantidadeComida.setDisable(true);
                    buttonQuitarAnimalComida.setDisable(true);
                }
            }
        });

        buttonNovaComida.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textFieldIdComida.setText("");
                textFieldNomeComida.setText("");
                textFieldStockComida.setText("");
                textFieldUnidadesComida.setText("");
                textFieldTlfUsuario.setText("");

                buttonGardarComida.setDisable(false);
                buttonEliminarComida.setDisable(true);

                textFieldCantidadeComida.setText("");
                labelUds.setText("");

                taboaComidas.getSelectionModel().clearSelection();
                taboaAnimaisComida.getSelectionModel().clearSelection();
                taboaOutrosAnimaisComida.getSelectionModel().clearSelection();

                taboaAnimaisComida.setItems(null);
                taboaOutrosAnimaisComida.setItems(null);
            }
        });

        buttonGardarComida.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (textFieldIdComida.getText() != null && !textFieldIdComida.getText().equals("")
                        && textFieldNomeComida.getText() != null && !textFieldNomeComida.getText().equals("")
                        && textFieldStockComida.getText() != null && !textFieldStockComida.getText().equals("")
                        && textFieldUnidadesComida.getText() != null && !textFieldUnidadesComida.getText().equals("")) {

                    Comida c = new Comida(Integer.parseInt(textFieldIdComida.getText()), textFieldNomeComida.getText(),
                            textFieldUnidadesComida.getText(), Integer.parseInt(textFieldStockComida.getText()));

                    gardarComida(fa, c);
                    buttonNovaComida.fire();
                    taboaComidas.setItems(updateComidas(fa));
                }
            }

        });

        buttonEliminarComida.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (textFieldIdComida.getText() != null && !textFieldIdComida.getText().equals("")) {

                    Comida c = new Comida(Integer.parseInt(textFieldIdComida.getText()));
                    eliminarComida(fa, c);

                    taboaComidas.setItems(updateComidas(fa));
                    buttonNovaComida.fire();
                }
            }
        });

        buttonEngadirAnimalComida.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { // Meter aviso para que si non se puxo nada en cantidade diga algo
                if (taboaOutrosAnimaisComida.getSelectionModel().getSelectedItem() != null
                        && taboaComidas.getSelectionModel().getSelectedItem() != null
                        && textFieldCantidadeComida.getText() != null
                        && !textFieldCantidadeComida.getText().equals("")) {

                    Animal a = (Animal) taboaOutrosAnimaisComida.getSelectionModel().getSelectedItem();
                    Comida c = (Comida) taboaComidas.getSelectionModel().getSelectedItem();

                    engadirAnimalComida(fa, c, a, Integer.parseInt(textFieldCantidadeComida.getText()));
                    taboaAnimaisComida.setItems(updateAnimaisComida(fa, c));
                    taboaOutrosAnimaisComida.setItems(updateOutrosAnimaisComida(fa, c));

                } else if (textFieldCantidadeComida.getText() == null
                        || textFieldCantidadeComida.getText().equals("")){
                    fa.muestraExcepcion("¡Debe indicarse a cantidade de comida!");
                }
            }
        });

        buttonQuitarAnimalComida.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (taboaAnimaisComida.getSelectionModel().getSelectedItem() != null
                        && taboaComidas.getSelectionModel().getSelectedItem() != null) {

                    Animal a = (Animal) taboaAnimaisComida.getSelectionModel().getSelectedItem();
                    Comida c = (Comida) taboaComidas.getSelectionModel().getSelectedItem();

                    quitarAnimalComida(fa, c, a);
                    taboaAnimaisComida.setItems(updateAnimaisComida(fa, c));
                    taboaOutrosAnimaisComida.setItems(updateOutrosAnimaisComida(fa, c));
                }
            }
        });

        buttonCambiarCantidadeComida.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { // xq colle comida como null?
                if (taboaAnimaisComida.getSelectionModel().getSelectedItem() != null
                        && taboaComidas.getSelectionModel().getSelectedItem() != null
                        && textFieldCantidadeComida.getText() != null
                        && !textFieldCantidadeComida.getText().equals("")) {

                    Animal a = (Animal) taboaAnimaisComida.getSelectionModel().getSelectedItem();
                    Comida c = (Comida) taboaComidas.getSelectionModel().getSelectedItem();

                    cambiarCantidadeComida(fa, c, a, Integer.parseInt(textFieldCantidadeComida.getText()));
                }
            }
        }
        );

        ObservableList e = updateAreas(fa);
        ObservableList z = FXCollections.observableArrayList();
        for (int i = 0; i < e.size(); i++) {
            z.add(((Area) e.get(i)).getId());
        }
        choiceBoxArea.setItems(z);
        tablaAreas.setItems(updateAreas(fa));
        buscarButton.fire();
        buttonBuscarUsuario.fire();
        e = fa.updateUsuarios("");
        ObservableList r = FXCollections.observableArrayList();
        for (int i = 0; i < e.size(); i++) {
            if (((Usuario) e.get(i)).getTipo().equals(TipoUsuario.Coidador)) {
                r.add(((Usuario) e.get(i)).getDni());
            }
        }
        choiceBoxCoidador.setItems(r);
        updateAvisos(fa);
        textFieldIDXaula.setDisable(true);
        buttonNovaXaula.setDisable(true);
        buttonBorrarArea.setDisable(true);
        buttonBorrarXaula.setDisable(true);
        choiceBoxAreaDestino.setDisable(true);
        choiceBoxXaulaDestino.setDisable(true);
        buttonTransferir.setDisable(true);
        taboaComidas.setItems(updateComidas(fa));

    }

    private boolean comprobarCampos(FachadaAplicacion fa) {
        String texto = "";
        if (textFieldID.getText().equals("")) {
            texto = texto + "ID, ";
        }
        if (textFieldNombre.getText().equals("")) {
            texto = texto + "NOMBRE, ";
        }
        if (choiceBoxArea.getSelectionModel().getSelectedItem() == null || choiceBoxArea.getSelectionModel().getSelectedItem().toString().equals("")) {
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
        if (choiceBoxSexo.getSelectionModel().getSelectedItem() == null || choiceBoxSexo.getSelectionModel().getSelectedItem().toString().equals("")) {
            texto = texto + "SEXO, ";

        }
        if (choiceBoxXaula.getSelectionModel().getSelectedItem() == null || choiceBoxXaula.getSelectionModel().getSelectedItem().toString().equals("")) {
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

    private ObservableList updateComidas(FachadaAplicacion fa) {
        return fa.updateComidas();
    }

    private ObservableList updateAnimaisComida(FachadaAplicacion fa, Comida comida) {
        return fa.updateAnimaisComida(comida);
    }

    private ObservableList updateOutrosAnimaisComida(FachadaAplicacion fa, Comida comida) {
        return fa.updateOutrosAnimaisComida(comida);
    }

    private int recuperarCantidade(FachadaAplicacion fa, Comida c, Animal a) {
        return fa.recuperarCantidade(c, a);
    }

    private void gardarComida(FachadaAplicacion fa, Comida c) {
        fa.gardarComida(c);
    }

    private void eliminarComida(FachadaAplicacion fa, Comida c) {
        fa.eliminarComida(c);
    }

    private void engadirAnimalComida(FachadaAplicacion fa, Comida c, Animal a, Integer cantidade) {
        fa.engadirAnimalComida(c, a, cantidade);
    }

    private void quitarAnimalComida(FachadaAplicacion fa, Comida c, Animal a) {
        fa.quitarAnimalComida(c, a);
    }

    private void cambiarCantidadeComida(FachadaAplicacion fa, Comida c, Animal a, Integer cantidade) {
        fa.cambiarCantidadeComida(c, a, cantidade);
    }

    private ObservableList updateUsuarios(FachadaAplicacion fa, String usuario) {
        return fa.updateUsuarios(usuario);
    }

    private void gardarUsuario(FachadaAplicacion fa, Usuario usuario) {
        fa.gardarUsuario(usuario);
    }

    private void eliminarUsuario(FachadaAplicacion fa, Usuario usuario) {
        fa.eliminarUsuario(usuario);
    }
}
