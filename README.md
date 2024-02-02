### Ejercicio 1
a) si, hereda ambas

b) no, los constructores no se heredan

c) super

d) si, siempre

e) se invoca solo porque se llama por defecto al constructor sin parametros de la clase padre

### Ejercicio 2
a) La clase A es una superclase de la clase C

b) La clase B es una no es nada de la clase C

c) La clase E es una subclase de la clase A

d) La clase D es una subclase de la clase B

e) La clase A es superclase de la clase D

### Ejercicio 3
Sobrecarga es cuando tenemos varios metodos con el mismo nombre pero con diferentes parámetros, sobreescritura es cuando hacemos override y sobreescribimos una clase hija en base a una clase padre.

### Ejercicio 4

// Clase padre Video

public class Video {
    protected String titulo;
    protected int minutos;

    // Constructor de la clase Video
    public Video(String titulo, int minutos) {
        this.titulo = titulo;
        this.minutos = minutos;
    }

    // Método toString() sobrescrito solo en la clase Video
    @Override
    public String toString() {
        return "Video [titulo=" + titulo + ", minutos=" + minutos + "]";
    }
}

// Clase hija Pelicula que hereda de Video

public class Pelicula extends Video {
    private String director;
    private double valoracion;

    // Constructor de la clase Pelicula que invoca al constructor de la clase padre usando super()
    public Pelicula(String titulo, int minutos, String director, double valoracion) {
        super(titulo, minutos);
        this.director = director;
        this.valoracion = valoracion;
    }

}
