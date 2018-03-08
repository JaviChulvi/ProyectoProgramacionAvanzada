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
    public Date fechaEmision;
    public Date[] periodoFacturacion;

    public Factura(Tarifa tarifaAplicada, Date fechaEmision, Date[] periodoFacturacion) {
        this.codigo = codigo++;
        this.tarifaAplicada = tarifaAplicada;
        this.fechaEmision = fechaEmision;
        this.periodoFacturacion = periodoFacturacion;
    }
    public Date getFecha(){
        return this.fechaEmision;
    }
    /*public float importe(){
        return suma de minutos de llamadas * tarifa
    }*/
}
