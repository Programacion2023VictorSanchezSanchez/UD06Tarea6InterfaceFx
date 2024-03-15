package com.example.ud06herencia.controllers;

import com.example.ud06herencia.model.Alumno;
import com.example.ud06herencia.model.Curso;
import com.example.ud06herencia.model.Persona;
import com.example.ud06herencia.model.Profesor;
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
import java.util.Collections;
import java.util.ResourceBundle;

public class ProfesorController implements Initializable {
    private ObservableList<Profesor> listaProfesor;

    @FXML
    private Button btBorrar;

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
    private RadioButton rbSueldo;

    @FXML
    private RadioButton rbEdad;

    @FXML
    private RadioButton rbNombre;

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
    private ToggleGroup tgOrdenar;

    @FXML
    private TableView<Profesor> tvProfesores;

    @FXML
    void onClickOrdenar(ActionEvent event) {
        if(rbNombre.isSelected()){
            Collections.sort(listaProfesor);
        }else if(rbEdad.isSelected()){
            Collections.sort(listaProfesor,Profesor.edadComparator);
        }else if(rbSueldo.isSelected()){
            Collections.sort(listaProfesor,Profesor.sueldoComparator);
        }

    }

    /**
     * Comprobamos si existe el profesor y si existe lo borramos
     * @param event
     */
    @FXML
    void onClickBorrar(ActionEvent event) {
        String dni = tfDni.getText();
        Profesor profesor = new Profesor(dni, "", 0, 0);
        int indice = listaProfesor.indexOf(profesor);

        if (profesor != null) {
            if (listaProfesor.contains(profesor)) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setHeaderText("Quieres modificar el alumno con DNI \n" + profesor.getDni());

                alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                alerta.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        listaProfesor.remove(indice);
                    }
                });
            }
        }
    }

    /**
     * Creamos el profesor y lo guardamos al pulsar el boton comprobando si existe ya y si lo queremos modificar
     * @param event
     */
    @FXML
    void onClickGuardar(ActionEvent event) {
        Profesor profesor = creaProfesor();
        if(profesor!=null){
            if(!listaProfesor.contains(profesor)){
                //Añadimos el profesor
                listaProfesor.add(profesor);

                //Limpiamos los datos de entrada
                limpiaDatos();
            }else {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setHeaderText("Quieres modificar el profesor con DNI \n" + profesor.getDni());

                alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                alerta.showAndWait().ifPresent(response ->{
                    if(response == ButtonType.YES){
                        sustituyeProfesor(profesor);
                    }
                });
            }


        }

    }

    /**
     * Mostramos los parametros de un profesor al cliclar en la tabla
     * @param event
     */
    @FXML
    void onClickTvProfesores(MouseEvent event) {
        Profesor profesor = tvProfesores.getSelectionModel().getSelectedItem();

        if(profesor!=null){
            tfDni.setText(profesor.getDni());
            tfNombre.setText(profesor.getNombre());
            tfEdad.setText(String.valueOf(profesor.getEdad()));
            tfSueldo.setText(String.valueOf(profesor.getSueldo()));
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
     * @param listaProfesor
     */
    public void initialize(ObservableList<Profesor> listaProfesor){
        //asignamos la lista
        this.listaProfesor=listaProfesor;
        //Iniciamos tableview
        iniciaTableView();
        //inicializamos radio button
        iniciaRadioButton();
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
     * Inicializamos el radio button
     */
    void iniciaRadioButton(){
        tgOrdenar.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldButton, Toggle newButton) {
                if(rbNombre==newButton){
                    Collections.sort(listaProfesor);
                }else if(rbEdad==newButton){
                    Collections.sort(listaProfesor,Profesor.edadComparator);
                }else if(rbSueldo==newButton){
                    Collections.sort(listaProfesor,Profesor.sueldoComparator);
                }
            }
        });
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

    /**
     * Sustituimos un profesore
     * @param profesor que vamos a introducir
     */
    private void sustituyeProfesor(Profesor profesor){
        int indice=listaProfesor.indexOf(profesor);

        if(indice!=-1){
            listaProfesor.set(indice, profesor);
        }
    }
}
