import java.util.ArrayList;

public class Vivienda {
    private String sector;
    private String callePrincipal;
    private String calleSecundaria;
    private String codigoPostal;
    private ArrayList<Consumo> consumos = new ArrayList<>();

    public Vivienda(String sector, String callePrincipal, String calleSecundaria, String codigoPostal) {
        this.sector = sector;
        this.callePrincipal = callePrincipal;
        this.calleSecundaria = calleSecundaria;
        this.codigoPostal = codigoPostal;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

    public void setCalleSecundaria(String calleSecundaria) {
        this.calleSecundaria = calleSecundaria;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setConsumos(ArrayList<Consumo> consumos) {
        this.consumos = consumos;
    }

    public String getSector() {
        return sector;
    }

    public String getCallePrincipal() {
        return callePrincipal;
    }

    public String getCalleSecundaria() {
        return calleSecundaria;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public ArrayList<Consumo> getConsumos() {
        return consumos;
    }

    public void agregarConsumo(Consumo consumo) {
        consumos.add(consumo);
    }


    @Override
    public String toString() {
        return "Vivienda{" +
                "sector='" + sector + '\'' +
                ", callePrincipal='" + callePrincipal + '\'' +
                ", calleSecundaria='" + calleSecundaria + '\'' +
                ", codigoPostal='" + codigoPostal + '\''+
                '}';
    }
}
