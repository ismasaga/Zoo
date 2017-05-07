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
    ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    ObservableList<Area> areas = FXCollections.observableArrayList();

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
    private TextField textFieldArea;
    @FXML
    private TextField textFieldXaula;
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
    private Button buttonNovaArea;
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

    private TableColumn<Xaula, Integer> xaulaFirst = new TableColumn<Xaula, Integer>("ID");

    private TableColumn<Area, Integer> areaFirst = new TableColumn<Area, Integer>("ID");
    private TableColumn<Area, String> areaSecond = new TableColumn<Area, String>("Climatizacion");

    private TableColumn<Animal, Integer> animalFirst = new TableColumn<Animal, Integer>("ID");
    private TableColumn<Animal, String> animalSecond = new TableColumn<Animal, String>("Nombre");
    private TableColumn<Animal, String> animalThird = new TableColumn<Animal, String>("Especie");
    private TableColumn<Usuario, String> columnaNomeUsuario = new TableColumn<Usuario, String>("Nome");
    private TableColumn<Usuario, String> columnaDniUsuario = new TableColumn<Usuario, String>("DNI");

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

        areaFirst.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        areaSecond.setCellValueFactory(cellData -> cellData.getValue().climatizacionProperty());
        tablaAreas.getColumns().add(areaFirst);
        tablaAreas.getColumns().add(areaSecond);

        xaulaFirst.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
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
                    choiceBoxXaula.setItems(updateXaulas(fa, animal.getArea()));
                    choiceBoxXaula.getSelectionModel().select(choiceBoxXaula.getItems().indexOf(String.valueOf(animal.getXaula())));
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
                    choiceBoxXaula.setItems(updateXaulas(fa, Integer.valueOf(newValue.toString())));
                    choiceBoxXaula.getSelectionModel().clearSelection();
                }
            }
        });

        buttonGuardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (comprobarCampos(fa)) {
                    if (textFieldID.getText().matches("^\\d+$")) {
                        Animal animal = new Animal(Integer.valueOf(textFieldID.getText()), textFieldNombre.getText(), textFieldEspecie.getText(), Integer.valueOf(textFieldEdad.getText()), Integer.valueOf(textFieldPeso.getText()), choiceBoxSexo.getSelectionModel().getSelectedItem().toString(), Integer.valueOf(choiceBoxArea.getSelectionModel().getSelectedItem().toString()), Integer.valueOf(choiceBoxXaula.getSelectionModel().getSelectedItem().toString()));
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
                    
                    Usuario usr = new Usuario(textFieldDniUsuario.getText(), choiceBoxTipoUsuario.getSelectionModel().getSelectedItem().toString().equals("Coidador")? TipoUsuario.Coidador : TipoUsuario.Contable); 
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
        ObservableList e = updateAreas(fa);
        ObservableList z = FXCollections.observableArrayList();
        for (int i = 0; i < e.size(); i++) {
            z.add(((Area) e.get(i)).getId());
        }
        choiceBoxArea.setItems(z);
        buscarButton.fire();
        buttonBuscarUsuario.fire();
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
