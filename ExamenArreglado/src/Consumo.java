public class Consumo {
    private Electrodomestico electrodomestico;
    private int cantidad;
    private double tiempo;

    public Consumo(Electrodomestico electrodomestico, int cantidad, double tiempo) {
        this.electrodomestico = electrodomestico;
        this.cantidad = cantidad;
        this.tiempo = tiempo;
    }

    public double calcularEnergia() {
        return cantidad * electrodomestico.getConsumoEnWatts() * tiempo;
    }

    public double calcularPrecio() {
        return calcularEnergia() * 0.092;
    }
    public Electrodomestico getElectrodomestico() {
        return electrodomestico;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTiempo() {
        return tiempo;
    }

    @Override
    public String toString() {
        return "Consumo{" +
                "electrodomestico=" + electrodomestico +
                ", cantidad=" + cantidad +
                ", tiempo=" + tiempo +
                '}';
    }
}