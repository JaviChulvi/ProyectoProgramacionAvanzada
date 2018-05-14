package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by al364498 on 8/05/18.
 */
public class EscuchadorBotonesClientes implements ActionListener{

    public EscuchadorBotonesClientes(){
    }

    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "añadir-cliente":
                System.out.println("Pulsado añadir cliente");
                break;
            case "borrar-cliente":
                System.out.println("Pulsado borrar cliente");
                break;
            case "buscar-cliente":
                System.out.println("Pulsado buscar cliente");
                break;
            case "mostrar-cliente":
                System.out.println("Pulsado mostrar cliente");
                break;
        }
    }
}
