package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscuchadorBotonesLlamadas implements ActionListener {

    public EscuchadorBotonesLlamadas(){
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
