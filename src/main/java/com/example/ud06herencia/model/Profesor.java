package com.example.ud06herencia.model;

/**
 * Clase Profesor que hereda de clase Persona
 */
public class Profesor extends Persona{
    private int sueldo;

    //constructor
    public Profesor(String dni, String nombre, int edad, int sueldo) {
        super(dni, nombre, edad);
        if(sueldo >= 0) {
            this.sueldo = sueldo;
        }else{
            this.sueldo = 0;
        }
    }

    //getter
    public int getSueldo() {
        return this.sueldo;
    }

    //setter
    public void setSueldo(int sueldo) {
        if(sueldo >= 0) {
            this.sueldo = sueldo;
        }else{
            this.sueldo = 0;
        }
    }
}
