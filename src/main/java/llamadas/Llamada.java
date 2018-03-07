package llamadas;

import java.util.Date;

public class Llamada {
    public Integer tlfDestino;
    public Date fechaYhora;
    public Integer duración;

    public Llamada(Integer tlfDestino, Date fechaYhora, Integer duración) {
        this.tlfDestino = tlfDestino;
        this.fechaYhora = fechaYhora;
        this.duración = duración;
    }
}
