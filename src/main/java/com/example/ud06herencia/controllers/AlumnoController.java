package com.example.ud06herencia.controllers;

import com.example.ud06herencia.model.Alumno;
import com.example.ud06herencia.model.Curso;
import com.example.ud06herencia.model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AlumnoController implements Initializable {

    private ObservableList<Alumno> listaAlumnos;

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
    private TableView<Alumno> tvAlumnos;

    /**
     * Creamos el alumno y lo guardamos al pulsar el boton
     * @param event
     */
    @FXML
    void onClickGuardar(ActionEvent event) {
        Alumno alumno = creaAlumno();
        if(alumno!=null){
            listaAlumnos.add(alumno);
        }
        //Limpiamos los datos de entrada
        limpiaDatos();
    }

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
    public void initialize(ObservableList<Alumno> listaAlumnos){
        //Iniciamos combobox
        iniciaCbCurso();
        //asignamos la lista
        this.listaAlumnos=listaAlumnos;
        //Iniciamos tableview
        iniciaTableView();
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

}

