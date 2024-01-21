import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String apellido;
    private String cedula;
    private ArrayList<Vivienda> viviendas = new ArrayList<>();

    public Usuario(String nombre, String apellido, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public ArrayList<Vivienda> getViviendas() {
        return viviendas;
    }

    public void agregarVivienda(Vivienda vivienda) {
        viviendas.add(vivienda);
    }
    public Factura generarFactura() {
        double costoTotal = 0.0;
        Vivienda ultimaVivienda = viviendas.get(viviendas.size() - 1);
        for (Consumo consumo : ultimaVivienda.getConsumos()) {
            costoTotal += consumo.calcularPrecio();
        }
        ArrayList<Electrodomestico> electrodomesticos = new ArrayList<>();
        for (Consumo consumo : ultimaVivienda.getConsumos()) {
            electrodomesticos.add(consumo.getElectrodomestico());
        }
        return new Factura(this, ultimaVivienda, electrodomesticos, costoTotal);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                '}';
    }
}
