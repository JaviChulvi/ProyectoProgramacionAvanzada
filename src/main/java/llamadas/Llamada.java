package llamadas;


import gestionTelefonia.ObjetosConFecha;

import java.time.LocalDate;
import java.util.Date;

public class Llamada implements ObjetosConFecha {

    private int tlfDestino;
    private LocalDate fechaYhora;
    private int duracion;

    public Llamada(){}
    
    public Llamada(int tlfDestino, LocalDate fechaYhora, int duración) {
        this.tlfDestino = tlfDestino;
        this.fechaYhora = fechaYhora;
        this.duracion = duración;
    }

    @Override
    public LocalDate getFecha() {
        return this.fechaYhora;
    }

    public int getDuracion(){
        return duracion;
    }
    public String toString(){
        return "Llamada de " + duracion + " segundos al número " + tlfDestino + " (" + fechaYhora.toString() + ")";
    }
}
