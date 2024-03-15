package com.example.ud06herencia.controllers;

import com.example.ud06herencia.model.Alumno;
import com.example.ud06herencia.model.Curso;
import com.example.ud06herencia.model.Persona;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

public class AlumnoController implements Initializable {

    private ObservableList<Alumno> listaAlumnos;

    @FXML
    private Button btBorrar;

    @FXML
    private Button btGuardar;

    @FXML
    private ComboBox<String> cbCurso;

    @FXML
    private Label lbCurso;

    @FXML
    private Label lbDni;

    @FXML
    private Label lbEdad;

    @FXML
    private Label lbNombre;

    @FXML
    private RadioButton rbCurso;

    @FXML
    private RadioButton rbEdad;

    @FXML
    private RadioButton rbNombre;

    @FXML
    private TableColumn<Alumno, Curso> tcCurso;

    @FXML
    private TableColumn<Alumno, String> tcDni;

    @FXML
    private TableColumn<Alumno, Integer> tcEdad;

    @FXML
    private TableColumn<Alumno, String> tcNombre;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfNombre;

    @FXML
    private ToggleGroup tgOrdenar;


    @FXML
    private TableView<Alumno> tvAlumnos;
    @FXML
    void onClickOrdenar(ActionEvent event) {
        if(rbNombre.isSelected()){
            Collections.sort(listaAlumnos);
        }else if(rbEdad.isSelected()){
            Collections.sort(listaAlumnos,Alumno.edadComparator);
        }else if(rbCurso.isSelected()){
            Collections.sort(listaAlumnos,Alumno.cursoComparator);
        }

    }

    /**
     * Comprobamos si existe el alumno y si existe lo borramos
     * @param event
     */
    @FXML
    void onClickBorrar(ActionEvent event) {
        String dni = tfDni.getText();
        Alumno alumno = new Alumno(dni, "", 0, Curso.DAM);
        int indice = listaAlumnos.indexOf(alumno);

        if (alumno != null) {
            if (listaAlumnos.contains(alumno)) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setHeaderText("Quieres modificar el alumno con DNI \n" + alumno.getDni());

                alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                alerta.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        listaAlumnos.remove(indice);
                    }
                });
            }
        }
    }

    /**
     * Creamos el alumno y lo guardamos al pulsar el boton comprobando si existe ya y si lo queremos modificar
     * @param event
     */
    @FXML
    void onClickGuardar(ActionEvent event) {
        Alumno alumno = creaAlumno();
        if(alumno!=null){
            if(!listaAlumnos.contains(alumno)) {
                //guardamos alumno
                listaAlumnos.add(alumno);
                //Limpiamos los datos de entrada
                limpiaDatos();
            }else {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setHeaderText("Quieres modificar el alumno con DNI \n" + alumno.getDni());

                alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                alerta.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        sustituyeAlumno(alumno);
                    }
                });
            }
        }

    }

    /**
     * Nos mostrará los parametros al cliclarlos en la tabla
     * @param event
     */
    @FXML
    void onClickTvAlumnos(MouseEvent event) {
        Alumno alumno = tvAlumnos.getSelectionModel().getSelectedItem();

        if(alumno!=null){
            tfDni.setText(alumno.getDni());
            tfNombre.setText(alumno.getNombre());
            tfEdad.setText(String.valueOf(alumno.getEdad()));
            cbCurso.setValue(alumno.getCurso().toString());
        }
    }

    /**
     * Iniciamos la aplicacion
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Inicializamos la aplicación
     * @param listaAlumnos
     */
    public void initialize(ObservableList<Alumno> listaAlumnos){
        //Iniciamos combobox
        iniciaCbCurso();
        //asignamos la lista
        this.listaAlumnos=listaAlumnos;
        //Iniciamos tableview
        iniciaTableView();
        //Iniciamos el radio button
        iniciaRadioButton();
    }

    /**
     * Inicializamos el radio button
     */
    void iniciaRadioButton(){
        tgOrdenar.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldButton, Toggle newButton) {
                if(rbNombre==newButton){
                    Collections.sort(listaAlumnos);
                }else if(rbEdad==newButton){
                    Collections.sort(listaAlumnos,Alumno.edadComparator);
                }else if(rbCurso==newButton){
                    Collections.sort(listaAlumnos,Alumno.cursoComparator);
                }
            }
        });
    }

    /**
     * Iniciamos el combobox
     */
    private void iniciaCbCurso(){
        Curso[] cursos = Curso.values();
        for(Curso curso : cursos){
            cbCurso.getItems().add(curso.toString());
        }
        cbCurso.setValue(cursos[0].toString());
    }

    /**
     * Mandamos mensaje de error
     * @param mensaje
     */
    private void iniciaAlertaError(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");

        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Creamos el alumno con los datos introducidos comprobando que cumple los requisitos
     * @return el alumno creado
     */
    private Alumno creaAlumno(){
        Alumno alumno = null;
        String nombre = tfNombre.getText();

        String dni = tfDni.getText();

        Curso curso = Curso.valueOf(cbCurso.getValue());

        int edad;

        if (!Persona.esCorrectoNIF(dni)) {

            iniciaAlertaError("El DNI es incorrecto");

            tfDni.requestFocus();
        }else if(nombre.isEmpty()){
            iniciaAlertaError("El alumno tiene que tener nombre");
            tfNombre.requestFocus();
        }else if(tfEdad.getText().isEmpty()){
            iniciaAlertaError("El alumno tiene que tener edad");
            tfEdad.requestFocus();
        }else{
            try{
                edad=Integer.parseInt(tfEdad.getText());

                alumno = new Alumno(dni,nombre,edad,curso);
            }catch (NumberFormatException e){
                iniciaAlertaError("La edad tiene que ser un numero entero");
                tfEdad.requestFocus();
            }
        }
        return alumno;
    }

    /**
     * Iniciamos el TableView
     */
    private void iniciaTableView(){


        tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tcDni.setCellValueFactory(new PropertyValueFactory("dni"));
        tcEdad.setCellValueFactory(new PropertyValueFactory("edad"));
        tcCurso.setCellValueFactory(new PropertyValueFactory("curso"));

        tvAlumnos.setItems(listaAlumnos);
    }

    /**
     * Limpiamos los datos de los textfields
     */
    private void limpiaDatos(){
        tfEdad.clear();
        tfNombre.clear();
        tfDni.clear();
    }

    /**
     * Sustituimos alumno
     * @param alumno que introduciremos
     */
    private void sustituyeAlumno(Alumno alumno){
        int indice=listaAlumnos.indexOf(alumno);

        if(indice!=-1){
            listaAlumnos.set(indice, alumno);
        }
    }

}

