package modelo.llamadas;



import modelo.gestionTelefonia.ObjetosConFecha;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Llamada implements ObjetosConFecha , Serializable {


	private static final long serialVersionUID = 2591627091244637271L;
	private int tlfDestino;
    private LocalDate fecha;
    private LocalTime hora;
    private int duracion;

    public Llamada(){}
    
    public Llamada(int tlfDestino, LocalDate fecha, int duracion) {
        this.tlfDestino = tlfDestino;
        this.fecha = fecha;
        Date input = new Date();
        this.hora = input.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        this.duracion = duracion;
    }
    public Llamada(int tlfDestino, LocalDate fecha, int duracion, LocalTime hora) {
        this.tlfDestino = tlfDestino;
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
    }

    @Override
    public LocalDate getFecha() {
        return this.fecha;
    }
    public LocalTime getHora(){
        return this.hora;
    }

    public int getDuracion(){
        return duracion;
    }
    public String toString(){
        return "Llamada de " + duracion + " segundos al número " + tlfDestino + " (" + fecha.toString() + ")";
    }
}
