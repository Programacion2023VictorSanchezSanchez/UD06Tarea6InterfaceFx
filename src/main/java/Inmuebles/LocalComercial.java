package Inmuebles;

public class LocalComercial extends Local{
    protected double valorArea;
    protected String centroComercial;

    public LocalComercial(int identificador, int area, String direccion, Inmuebles.tipoLocal tipoLocal, double valorArea, String centroComercial) {
        super(identificador, area, direccion, tipoLocal);
        this.valorArea = valorArea;
        this.centroComercial = centroComercial;
    }

    @Override
    public String toString() {
        return "LocalComercial{" +
                "identificador=" + identificador +
                ", area=" + area +
                ", direccion='" + direccion + '\'' +
                ", precioVenta=" + precioVenta +
                ", tipoLocal=" + tipoLocal +
                ", valorArea=" + valorArea +
                ", centroComercial='" + centroComercial + '\'' +
                '}';
    }


}
