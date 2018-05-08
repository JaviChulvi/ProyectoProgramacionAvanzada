package modelo.tarifa;

import java.io.Serializable;
import java.time.LocalTime;

import modelo.llamadas.Llamada;

/**
 * Created by al364498 on 17/04/18.
 */
public class TarifaIntervaloHorario extends Oferta implements Serializable {
	private static final long serialVersionUID = -3050831188184987288L;
	private LocalTime horaIni;
	private LocalTime horaFin;
	
    public TarifaIntervaloHorario(Tarifa tarifa, double precio, LocalTime horaIni, LocalTime horaFin){
        super(tarifa, precio);
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    public Double calcularPrecioLlamada(Llamada llamada){
        Double anterior = tarifa.calcularPrecioLlamada(llamada);
        Double actual = llamada.getDuracion() * precio;
        
        if(llamada.getHora().isAfter(horaIni) && llamada.getHora().isBefore(horaFin)) {
            if (actual < anterior) {
                return actual;
            }
        }
        
        return anterior;
    }

    @Override
    public String toString() {
        return super.toString() + " para las modelo.llamadas hechas por la tarde";
    }
}
