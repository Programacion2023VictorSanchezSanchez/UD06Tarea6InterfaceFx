package com.example.ud06herencia.controllers;

import com.example.ud06herencia.IESOchoaApplication;
import com.example.ud06herencia.model.Profesor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioController {

    @FXML
    private Button btAlumnos;

    @FXML
    private Button btProfesores;

    @FXML
    void onClickAlumnos(ActionEvent event) {
        abrirAlumnos();
    }

    @FXML
    void onClickProfesores(ActionEvent event) {
        abrirProfesores();
    }

    private void abrirAlumnos(){
        try{
            FXMLLoader loader = new FXMLLoader(IESOchoaApplication.class.getResource("alumno-view.fxml"));
            Parent root = loader.load();

            AlumnoController alumnoController=loader.getController();
            Scene scene= new Scene(root);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void abrirProfesores(){
        try{
            FXMLLoader loader = new FXMLLoader(IESOchoaApplication.class.getResource("profesor-view.fxml"));
            Parent root = loader.load();

            ProfesorController profesorController=loader.getController();
            Scene scene= new Scene(root);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

}

