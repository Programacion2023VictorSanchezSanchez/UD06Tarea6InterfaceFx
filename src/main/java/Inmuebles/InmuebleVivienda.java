package Inmuebles;

public class InmuebleVivienda extends Inmueble{
    protected int numHabitaciones;
    protected int numBanyos;

    public InmuebleVivienda(int identificador, int area, String direccion, int numHabitaciones, int numBanyos) {
        super(identificador, area, direccion);
        this.numHabitaciones = numHabitaciones;
        this.numBanyos = numBanyos;
    }

    @Override
    public String toString() {
        return "InmuebleVivienda{" +
                "identificador=" + identificador +
                ", area=" + area +
                ", direccion='" + direccion + '\'' +
                ", numHabitaciones=" + numHabitaciones +
                ", numBanyos=" + numBanyos +
                '}';
    }
}
