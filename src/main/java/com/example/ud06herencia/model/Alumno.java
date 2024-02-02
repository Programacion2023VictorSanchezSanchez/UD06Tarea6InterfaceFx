package com.example.ud06herencia.model;

/**
 * Clase Alumno que hereda de clase Persona
 */
public class Alumno extends Persona{
    private Curso curso;

    //constructor
    public Alumno(String dni, String nombre, int edad, Curso curso) {
        super(dni, nombre, edad);
        this.curso = curso;
    }

    //getter
    public Curso getCurso() {
        return this.curso;
    }

    //setter
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
