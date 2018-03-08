package clientes;

/**
 * Created by al364498 on 20/02/18.
 */
public class Direccion {
    private Integer codigoPostal;
    private String provincia;
    private String poblacion;

    public Direccion(Integer codigoPostal, String provincia, String poblacion) {
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public String toString(){
        return poblacion + ", " + provincia + " (" + codigoPostal + ")";
    }
}
