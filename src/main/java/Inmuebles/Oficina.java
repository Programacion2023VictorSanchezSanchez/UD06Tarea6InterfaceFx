package Inmuebles;

public class Oficina extends Local{
    protected double valorArea;
    protected boolean esGobierno;

    public Oficina(int identificador, int area, String direccion, Inmuebles.tipoLocal tipoLocal, double valorArea, boolean esGobierno) {
        super(identificador, area, direccion, tipoLocal);
        this.valorArea = valorArea;
        this.esGobierno = esGobierno;
    }

    @Override
    public String toString() {
        return "Oficina{" +
                "identificador=" + identificador +
                ", area=" + area +
                ", direccion='" + direccion + '\'' +
                ", precioVenta=" + precioVenta +
                ", tipoLocal=" + tipoLocal +
                ", valorArea=" + valorArea +
                ", esGobierno=" + esGobierno +
                '}';
    }
}
