package Inmuebles;

public class Casa extends InmuebleVivienda{
    protected int numPisos;

    public Casa(int identificador, int area, String direccion, int numHabitaciones, int numBanyos, int numPisos) {
        super(identificador, area, direccion, numHabitaciones, numBanyos);
        this.numPisos = numPisos;
    }

    @Override
    public String toString() {
        return "Casa{" +
                "numPisos=" + numPisos +
                ", identificador=" + identificador +
                ", area=" + area +
                ", direccion='" + direccion + '\'' +
                ", precioVenta=" + precioVenta +
                ", numHabitaciones=" + numHabitaciones +
                ", numBanyos=" + numBanyos +
                '}';
    }


}
