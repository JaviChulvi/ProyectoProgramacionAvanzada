package tarifa;

import llamadas.Llamada;

import java.io.Serializable;

/**
 * Created by al364498 on 20/02/18.
 */
public interface Tarifa  {
    String toString();
    //double getPrecioMin();
    Double calcularPrecioLlamada(Llamada llamada);
}
