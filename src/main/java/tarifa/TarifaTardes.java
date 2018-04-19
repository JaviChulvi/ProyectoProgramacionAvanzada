package tarifa;

import llamadas.Llamada;

/**
 * Created by al364498 on 17/04/18.
 */
public class TarifaTardes extends Oferta {
    private static final double precioMinTardes = 0.5;
    public TarifaTardes(Tarifa tarifa){
        super(tarifa);
    }



    public Double calcularPrecioLlamada(Llamada llamada){
        Double anterior = super.calcularPrecioLlamada(llamada);
        Double actual = llamada.getDuracion() * precioMinTardes;
        if(llamada.getHora().getHour() >= 17 && llamada.getHora().getHour() < 21){
            return  actual;
        } else {
            return anterior;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " para las llamadas hechas por la tarde";
    }
}