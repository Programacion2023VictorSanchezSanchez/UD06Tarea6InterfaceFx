package Inmuebles;

public class Apartamento extends InmuebleVivienda{
    public Apartamento(int identificador, int area, String direccion, int numHabitaciones, int numBanyos) {
        super(identificador, area, direccion, numHabitaciones, numBanyos);
    }

    @Override
    public String toString() {
        return "Apartamento{" +
                "identificador=" + identificador +
                ", area=" + area +
                ", direccion='" + direccion + '\'' +
                ", precioVenta=" + precioVenta +
                ", numHabitaciones=" + numHabitaciones +
                ", numBanyos=" + numBanyos +
                '}';
    }


}
