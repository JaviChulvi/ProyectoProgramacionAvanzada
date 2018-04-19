package tarifa;

import llamadas.Llamada;

/**
 * Created by al364498 on 17/04/18.
 */
public class TarifaDomingos extends Oferta {

    private static final double precioMinDomingos = 0;

    public TarifaDomingos(Tarifa tarifa){
        super(tarifa);
    }
    @Override
    public String toString() {
        return super.toString() + " para las llamadas hechas los Domingos";
    }

    @Override
    public Double calcularPrecioLlamada(Llamada llamada){
        Double anterior = super.calcularPrecioLlamada(llamada);
        Double actual = llamada.getDuracion() * precioMinDomingos;
        if(llamada.getFecha().getDayOfWeek().getValue()==7){
            return  actual;
        } else {
            return anterior;
        }
    }
}
