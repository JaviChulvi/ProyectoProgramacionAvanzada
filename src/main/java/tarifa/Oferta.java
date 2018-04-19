package tarifa;

import llamadas.Llamada;

import java.io.Serializable;

/**
 * Created by al364498 on 17/04/18.
 */
public abstract class Oferta implements Tarifa, Serializable {
    private Tarifa tarifa;

    public Oferta(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    protected Tarifa getTarifa() {
        return tarifa;
    }

    public Double calcularPrecioLlamada(Llamada llamada){
        return llamada.getDuracion() * 0.15;
    }

    public String toString() {
        return tarifa.toString() + "con ofertas: ";
    }
}
