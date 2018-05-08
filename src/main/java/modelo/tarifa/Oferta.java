package modelo.tarifa;

import modelo.llamadas.Llamada;

import java.io.Serializable;

/**
 * Created by al364498 on 17/04/18.
 */
public abstract class Oferta implements Tarifa, Serializable {
	private static final long serialVersionUID = -3742051646639739485L;
	protected Tarifa tarifa;
	protected double precio;

    public Oferta(Tarifa tarifa, double precio) {
        this.tarifa = tarifa;
        this.precio = precio;
    }

    protected Tarifa getTarifa() {
        return tarifa;
    }
    
    public double getPrecioMin(){
    	return precio;
    }

    public Double calcularPrecioLlamada(Llamada llamada){
        return llamada.getDuracion() * precio;
    }

    public String toString() {
        return tarifa.toString() + "con ofertas: ";
    }
}
