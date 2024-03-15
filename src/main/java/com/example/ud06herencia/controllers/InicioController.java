package com.example.ud06herencia.controllers;

import com.example.ud06herencia.IESOchoaApplication;
import com.example.ud06herencia.model.Alumno;
import com.example.ud06herencia.model.Profesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Initializable {

    private ObservableList<Profesor> listaProfesores;
    private ObservableList<Alumno> listaAlumnos;

    @FXML
    private Button btAlumnos;

    @FXML
    private Button btProfesores;

    //Abrimos ventanas al clicar los botones
    @FXML
    void onClickAlumnos(ActionEvent event) {
        abrirAlumnos();
    }

    @FXML
    void onClickProfesores(ActionEvent event) {
        abrirProfesores();
    }

    /**
     * Ventana alumnos
     */
    private void abrirAlumnos(){
        try{
            FXMLLoader loader = new FXMLLoader(IESOchoaApplication.class.getResource("alumno-view.fxml"));
            Parent root = loader.load();

            AlumnoController alumnoController=loader.getController();
            alumnoController.initialize(listaAlumnos);
            Scene scene= new Scene(root);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Ventana profesores
     */
    private void abrirProfesores(){
        try{
            FXMLLoader loader = new FXMLLoader(IESOchoaApplication.class.getResource("profesor-view.fxml"));
            Parent root = loader.load();

            ProfesorController profesorController=loader.getController();
            profesorController.initialize(listaProfesores);
            Scene scene= new Scene(root);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Inicializamos listas
        listaAlumnos = FXCollections.observableArrayList();
        listaProfesores = FXCollections.observableArrayList();
    }
}

