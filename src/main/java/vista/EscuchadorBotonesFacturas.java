package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscuchadorBotonesFacturas implements ActionListener {

    public EscuchadorBotonesFacturas(){
    }

    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "datos-factura":
                System.out.println("Pulsado datos factura");
                break;
            case "emitir-factura":
                System.out.println("Pulsado emitir factura");
                break;
            case "listar-facturas":
                System.out.println("Pulsado listar facturas");
                break;
        }
    }
}
