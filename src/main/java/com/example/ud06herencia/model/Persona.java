package com.example.ud06herencia.model;

import java.util.Comparator;
import java.util.Objects;

/**
 * Clase Persona con información de personas
 * Autor: Víctor Sánchez
 */
public class Persona implements Comparable<Persona>{

    //variables
    private String dni;
    private String nombre;
    private int edad;

    //constructor
    public Persona(String dni, String nombre, int edad) {
        if(esCorrectoNIF(dni)) {
            this.dni = dni;
        }else{
            this.dni = "SinDNI";
        }
        this.nombre = nombre;
        this.edad = edad;
    }

    //getters
    public String getDni() {
        return this.dni;
    }
    public String getNombre() {
        return this.nombre;
    }
    public int getEdad() {
        return this.edad;
    }

    //setters
    public void setDni(String dni) {
        if(esCorrectoNIF(dni)) {
            this.dni = dni;
        }else{
            this.dni = "SinDNI";
        }
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Comprobamos si el nif es correcto
     *
     * @param nif que comprobaremos si es correcto
     * @return devolveremos un boolean de si es correcto o no el nif
     */
    public static boolean esCorrectoNIF(String nif){
        boolean esCorrectoNIF = true;

        // Comprobar el tamaño
        if (nif.length() != 9) {
            esCorrectoNIF = false;
        }

        // Comprobar que los primeros 8 caracteres son dígitos
        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(nif.charAt(i))) {
                esCorrectoNIF = false;
            }
        }

        // Comprobar que el último caracter es una letra
        char letra = Character.toUpperCase(nif.charAt(8));
        if (!Character.isLetter(letra)) {
            esCorrectoNIF = false;
        }


        return esCorrectoNIF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDni());
    }


    /**
     * Comparable nombre
     * @param otraPersona the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Persona otraPersona) {
        return this.nombre.compareTo(otraPersona.getNombre());
    }

    //Comparator edad
    public static Comparator<Persona> edadComparator = Comparator.comparingInt(Persona::getEdad);

    //Comparator curso alumno
    public static Comparator<Alumno> cursoComparator = Comparator.comparing(Alumno::getCurso);

    //Comparator sueldo profesor
    public static Comparator<Profesor> sueldoComparator = Comparator.comparing(Profesor::getSueldo);
}
