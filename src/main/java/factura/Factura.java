package factura;

import tarifa.Tarifa;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by al364498 on 20/02/18.
 */
public class Factura {
    public static Integer codigo = 0;
    public Tarifa tarifaAplicada;
    public Calendar fechaEmision;
    public Calendar[] periodoFacturacion;

    public Factura(Integer codigo, Tarifa tarifaAplicada, Calendar fechaEmision, Calendar[] periodoFacturacion) {
        this.codigo = codigo++;
        this.tarifaAplicada = tarifaAplicada;
        this.fechaEmision = fechaEmision;
        this.periodoFacturacion = periodoFacturacion;
    }

    /*public float importe(){
        return suma de minutos de llamadas * tarifa
    }*/
}
