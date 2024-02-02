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

d) Muestra primero el primer objeto Video con sus 2 atributos y luego solo 2 de los 4 atributos de Pelicula porque no sobrecargamos toString en la clase Pelicula.

e)Sobrecargar el metodo toString en la clase Pelicula

f)

// Clase VideoMusical que hereda de la clase Video

public class VideoMusical extends Video {

    private String artista;
    private String categoria;

    // Constructor de la clase VideoMusical que invoca al constructor de la clase padre usando super()
    public VideoMusical(String titulo, int minutos, String artista, String categoria) {
        super(titulo, minutos);
        this.artista = artista;
        this.categoria = categoria;
    }

    // Método toString() sobrescrito para proporcionar una representación en cadena específica para VideoMusical
    @Override
    public String toString() {
        return "VideoMusical [titulo=" + titulo + ", minutos=" + minutos + ", artista=" + artista + ", categoria=" + categoria + "]";
    }
}

g)Tiene 4, los 2 suyos y los 2 heredados de la clase video

h) A la clase video para que lo hereden todas la demás

### Ejercicio 5

a)No, marca es privado y no es accesible desde la clase hija

b)Es correcto si esta en el mismo paquete o es una subclase es accesible al estar protegido

c)Si es correcto

d)Es correcto si esta en el mismo paquete o es una subclase es accesible al estar protegido

e)Es correcto si esta en el mismo paquete o es una subclase es accesible al estar protegido

f)La diferencia de estados entre los atributos y que no hayan getters y setters

### Ejercicio 8

Primero imprime Marca = Apple y luego imprime Marca = Acer





