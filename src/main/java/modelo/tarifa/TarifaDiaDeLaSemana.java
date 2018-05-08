package modelo.tarifa;

import java.io.Serializable;
import java.time.DayOfWeek;

import modelo.llamadas.Llamada;

/**
 * Created by al364498 on 17/04/18.
 */
public class TarifaDiaDeLaSemana extends Oferta implements Serializable{
	private static final long serialVersionUID = -4018182193778582785L;
	private DayOfWeek diaDeLaSemana;

    public TarifaDiaDeLaSemana(Tarifa tarifa, double precio, DayOfWeek diaDeLaSemana){
        super(tarifa, precio);
        this.diaDeLaSemana = diaDeLaSemana;
    }
    
    @Override
    public String toString() {
        return super.toString() + " para las modelo.llamadas hechas los Domingos";
    }

    @Override
    public Double calcularPrecioLlamada(Llamada llamada){
        Double anterior = tarifa.calcularPrecioLlamada(llamada);
        Double actual = llamada.getDuracion() * precio;
        
        if(llamada.getFecha().getDayOfWeek().compareTo(diaDeLaSemana) == 0) {
        	if(actual < anterior) {
        		return actual;
        	}
        }
            
        return anterior;
    }
}
