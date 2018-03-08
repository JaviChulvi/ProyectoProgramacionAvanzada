package tarifa;

/**
 * Created by al364498 on 20/02/18.
 */
public class Tarifa {
    private double precioSec;

    public Tarifa(){
        this.precioSec = 0.1;
    }

    public Tarifa(double precioSec) {
        this.precioSec = precioSec;
    }

    public void setTarifa(double precioSec) {
        this.precioSec = precioSec;
    }

    public String toString(){
        return precioSec + " €/min";
    }
    public double getEurosPorSegundo() {
        return precioSec;
    }
}
