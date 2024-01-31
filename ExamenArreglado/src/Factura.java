import java.util.ArrayList;

public class Factura {
    private Usuario usuario;
    private Vivienda vivienda;
    private double costoTotal;

    public Factura(Usuario usuario, Vivienda vivienda, double costoTotal) {
        this.usuario = usuario;
        this.vivienda = vivienda;
        this.costoTotal = costoTotal;
    }

    public void imprimirFactura(){
        System.out.println("Usuario: " + usuario.getNombre() + " " + usuario.getApellido());
        System.out.println("--Datos de la Vivienda--");
        System.out.println("Sector: " + vivienda.getSector());
        System.out.println("Calle Principal: " + vivienda.getCallePrincipal());
        System.out.println("Calle Secundaria: " + vivienda.getCalleSecundaria());
        System.out.println("Código Postal: " + vivienda.getCodigoPostal());
        System.out.println("--Electrodomésticos--");
        for (Consumo consumo : vivienda.getConsumos()) {
            System.out.println(" | Nombre: " + consumo.getElectrodomestico().getNombre() + " Cantidad: " + consumo.getCantidad() + " Consumo: " + consumo.getElectrodomestico().getConsumoEnWatts()  + " Tiempo: " + consumo.getTiempo()+ " Energia Total: " + consumo.calcularEnergia());
        }
        System.out.println("Costo Total: $" + costoTotal);
    }

    @Override
    public String toString() {
        return "Factura{" +
                "costoTotal=" + costoTotal +
                '}';
    }
}
