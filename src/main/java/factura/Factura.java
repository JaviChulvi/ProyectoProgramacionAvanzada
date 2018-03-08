package factura;

import llamadas.Llamada;
import tarifa.Tarifa;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by al364498 on 20/02/18.
 */
public class Factura {
    private static int codigoActual = 0;  // Contador para asignar a cada factura un codigo distinto
    private int codigo;  // Único, no lo puede poseer otra factura
    private Tarifa tarifaAplicada;
    private Date fechaEmision;
    private Calendar principioFacturacion;
    private Calendar finalFacturacion;
    private float importe;

    public Factura(Tarifa tarifaAplicada, Date fechaEmision, Calendar principioFacturacion, Calendar finalFacturacion) {
        this.codigo = codigoActual++;
        this.tarifaAplicada = tarifaAplicada;
        this.fechaEmision = fechaEmision;
        this.principioFacturacion = principioFacturacion;
        this.finalFacturacion = finalFacturacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public Date getFecha(){
        return this.fechaEmision;
    }
    /*public float importe(){
        return suma de minutos de llamadas * tarifa
    }*/

    public String toString(){
        return "Factura de código " + codigo + ":\nImporte: " + importe + "\nFecha de emisión: "
                + fechaEmision.toString() + "\nPeriodo de facturación: Desde " + principioFacturacion.getTime()
                + " hasta " + finalFacturacion.getTime();
    }
    public float calcularImporte(List<Llamada> llamadas){
        float importe = 0;
        for(Llamada l : llamadas){
            if(l.getFecha().before(finalFacturacion) && l.getFecha().after(principioFacturacion)){
                importe += l.getDuracion() * tarifaAplicada.getEurosPorSegundo();
            }
        }
        return importe;
    }

}
