package Inmuebles;

public class Local extends Inmueble{
    protected tipoLocal tipoLocal;

    public Local(int identificador, int area, String direccion, Inmuebles.tipoLocal tipoLocal) {
        super(identificador, area, direccion);
        this.tipoLocal = tipoLocal;
    }

    @Override
    public String toString() {
        return "Local{" +
                "identificador=" + identificador +
                ", area=" + area +
                ", direccion='" + direccion + '\'' +
                ", precioVenta=" + precioVenta +
                ", tipoLocal=" + tipoLocal +
                '}';
    }
}
