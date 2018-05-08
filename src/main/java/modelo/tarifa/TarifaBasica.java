package modelo.tarifa;

import modelo.llamadas.Llamada;

import java.io.Serializable;

/**
 * Created by al364498 on 17/04/18.
 */
public class TarifaBasica implements Tarifa , Serializable{
	private static final long serialVersionUID = 6548538062323766063L;
	public double precioMin;
    
	public TarifaBasica(double precioMin) {
        this.precioMin = precioMin;
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

