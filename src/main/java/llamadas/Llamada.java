package llamadas;

import java.util.Calendar;

public class Llamada {
<<<<<<< 28ddab7a9cffe52a95b48729b8203c6a692a5f6a
    private Integer tlfDestino;
    private Calendar fechaYhora;
    private int duracion;

    public Llamada(Integer tlfDestino, Calendar fechaYhora, int duración) {
=======
    private int tlfDestino;
    private Calendar fechaYhora;
    private int duracion;

    public Llamada(){}
    
    public Llamada(int tlfDestino, Calendar fechaYhora, int duración) {
>>>>>>> Desde clase ANTES PRACTICAA
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
