package modelo.tarifa;

import modelo.llamadas.Llamada;

/**
 * Created by al364498 on 20/02/18.
 */
public interface Tarifa  {
    String toString();
    
    double getPrecioMin();
    
    Double calcularPrecioLlamada(Llamada llamada);
}
