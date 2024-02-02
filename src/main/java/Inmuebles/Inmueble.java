package Inmuebles;

public class Inmueble {
    protected int identificador;
    protected int area;
    protected String direccion;
    protected double precioVenta;

    public Inmueble(int identificador, int area, String direccion) {
        this.identificador = identificador;
        this.area = area;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "identificador=" + identificador +
                ", area=" + area +
                ", direccion='" + direccion + '\'' +
                ", precioVenta=" + precioVenta +
                '}';
    }
}
