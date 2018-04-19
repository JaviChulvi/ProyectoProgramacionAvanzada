package tarifa;

import llamadas.Llamada;

/**
 * Created by al364498 on 17/04/18.
 */
public class TarifaBasica implements Tarifa {

    public double precioMin = 0.15;
    public TarifaBasica() {
        super();
    }

    public double getPrecioMin() {
        return precioMin;
    }

    public String toString() {
        return precioMin + "Euros/Minuto";
    }

    public Double calcularPrecioLlamada(Llamada llamada){
        return llamada.getDuracion() * precioMin;
    }
}

