package vista;

import controlador.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscuchadorBotonesLlamadas implements ActionListener {

    private Controlador controlador;

    public EscuchadorBotonesLlamadas(Controlador controlador){
        this.controlador = controlador;
    }
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "hacer-llamada":
                System.out.println("Pulsado hacer llamada");
                break;
            case "listar-llamadas":
                System.out.println("Pulsado listar llamadas");
                break;
        }
    }

}
