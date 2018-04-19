package facturas;

import java.time.LocalDate;
import java.util.List;

import gestionTelefonia.ImpossibleDateIntervalException;
import gestionTelefonia.ObjetosConFecha;
import llamadas.Llamada;
import tarifa.Tarifa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by al364498 on 20/02/18.
 */
public class Factura implements ObjetosConFecha , Serializable {
    private static int codigoActual = 0;  // Contador para asignar a cada factura un codigo distinto
    private int codigo;  // Único, no lo puede poseer otra factura
    private Tarifa tarifaAplicada;
    private LocalDate fechaEmision;
    private LocalDate principioFacturacion;
    private LocalDate finalFacturacion;
    private float importe;

    public Factura(){}
    public Factura(Tarifa tarifaAplicada, LocalDate fechaEmision, LocalDate principioFacturacion, LocalDate finalFacturacion) {
    	if(principioFacturacion.isAfter(finalFacturacion))
    		throw new ImpossibleDateIntervalException("La fecha del principio de la facturación debe ser menor a la de final de la facturación.");
    	
        this.codigo = codigoActual++;
        this.tarifaAplicada = tarifaAplicada;
        this.fechaEmision = fechaEmision;
        this.principioFacturacion = principioFacturacion;
        this.finalFacturacion = finalFacturacion;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public LocalDate getFecha(){
        return this.fechaEmision;
    }

    public float getImporte(){
    	return importe;
    }

    public String toString(){
        return "Factura de código " + codigo + ":\nImporte: " + importe + "\nFecha de emisión: "
                + fechaEmision.toString() + "\nPeriodo de facturación: Desde " + principioFacturacion.toString()
                + " hasta " + finalFacturacion.toString();
    }
   /* public void calcularImporte(List<Llamada> llamadas){
        float importe = 0;
        for(Llamada l : llamadas){
            if(l.getFecha().isBefore(finalFacturacion) && l.getFecha().isAfter(principioFacturacion)){
                importe += l.getDuracion() * tarifaAplicada.;
            }
        }
        this.importe = importe;
    }*/

}
