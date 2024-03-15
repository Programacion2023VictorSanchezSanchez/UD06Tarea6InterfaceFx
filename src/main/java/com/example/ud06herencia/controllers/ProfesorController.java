package com.example.ud06herencia.controllers;

import com.example.ud06herencia.model.Alumno;
import com.example.ud06herencia.model.Curso;
import com.example.ud06herencia.model.Persona;
import com.example.ud06herencia.model.Profesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfesorController implements Initializable {
    private ObservableList<Profesor> listaProfesor;

    @FXML
    private Button btGuardar;

    @FXML
    private Label lbSueldo;

    @FXML
    private Label lbDni;

    @FXML
    private Label lbEdad;

    @FXML
    private Label lbNombre;

    @FXML
    private TableColumn<Profesor, Integer> tcSueldo;

    @FXML
    private TableColumn<Profesor, String> tcDni;

    @FXML
    private TableColumn<Profesor, Integer> tcEdad;

    @FXML
    private TableColumn<Profesor, String> tcNombre;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfSueldo;

    @FXML
    private TableView<Profesor> tvProfesores;

    /**
     * Creamos el profesor y lo guardamos al pulsar el boton
     * @param event
     */
    @FXML
    void onClickGuardar(ActionEvent event) {
        Profesor profesor = creaProfesor();
        if(profesor!=null){
            listaProfesor.add(profesor);
        }
        //Limpiamos los datos de entrada
        limpiaDatos();
    }

    /**
     * Iniciamos la aplicacion
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void initialize(ObservableList<Profesor> listaProfesor){
        //asignamos la lista
        this.listaProfesor=listaProfesor
        //Iniciamos tableview
        iniciaTableView();
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
    private Profesor creaProfesor(){
        Profesor profesor = null;
        String nombre = tfNombre.getText();

        String dni = tfDni.getText();

        int sueldo = Integer.parseInt(tfSueldo.getText());

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
        } else{
            try{
                edad=Integer.parseInt(tfEdad.getText());

                profesor = new Profesor(dni,nombre,edad,sueldo);
            }catch (NumberFormatException e){
                iniciaAlertaError("La edad tiene que ser un numero entero");
                tfEdad.requestFocus();
            }
        }
        return profesor;
    }

    /**
     * Iniciamos el TableView
     */
    private void iniciaTableView(){


        tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tcDni.setCellValueFactory(new PropertyValueFactory("dni"));
        tcEdad.setCellValueFactory(new PropertyValueFactory("edad"));
        tcSueldo.setCellValueFactory(new PropertyValueFactory("sueldo"));

        tvProfesores.setItems(listaProfesor);
    }

    /**
     * Limpiamos los datos de los textfields
     */
    private void limpiaDatos(){
        tfEdad.clear();
        tfNombre.clear();
        tfDni.clear();
        tfSueldo.clear();
    }
}
