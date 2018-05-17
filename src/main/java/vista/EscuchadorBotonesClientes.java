package vista;

import controlador.Controlador;
import modelo.empresaTelefonica.EmpresaTelefonia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by al364498 on 8/05/18.
 */
public class EscuchadorBotonesClientes implements ActionListener{
    private Controlador controlador;
    public EscuchadorBotonesClientes(Controlador controlador) {
        this.controlador = controlador;
    }

    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "añadir-cliente":
                System.out.println("Pulsado añadir cliente");
                controlador.addCliente();
                break;
            case "borrar-cliente":
                System.out.println("Pulsado borrar cliente");
                //controlador.borrarCliente();
                break;
            case "buscar-cliente":
                System.out.println("Pulsado buscar cliente");

                break;
            case "mostrar-clientes":
                System.out.println("Pulsado mostrar cliente");
                break;
        }
    }
}
