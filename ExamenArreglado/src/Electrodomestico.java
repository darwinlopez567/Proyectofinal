public class Electrodomestico {
    private String nombre;
    private int consumoEnWatts;

    public Electrodomestico(String nombre, int consumoEnWatts) {
        this.nombre = nombre;
        this.consumoEnWatts = consumoEnWatts;
    }

    public String getNombre() {
        return nombre;
    }

    public int getConsumoEnWatts() {
        return consumoEnWatts;
    }
    public String obtenerInformacionCompleta() {
        return "Nombre: " + nombre + ", Consumo en Watts: " + consumoEnWatts;
    }
}
