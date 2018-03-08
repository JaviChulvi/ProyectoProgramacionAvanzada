package llamadas;

import java.util.Calendar;

public class Llamada {
    private Integer tlfDestino;
    private Calendar fechaYhora;
    private int duracion;

    public Llamada(Integer tlfDestino, Calendar fechaYhora, int duración) {
        this.tlfDestino = tlfDestino;
        this.fechaYhora = fechaYhora;
        this.duracion = duración;
    }

    public Calendar getFecha() {
        return this.fechaYhora;
    }
    public int getDuracion(){
        return duracion;
    }
    public String toString(){
        return "Llamada de " + duracion + " segundos al número " + tlfDestino + " (" + fechaYhora.getTime() + ")";
    }
}
