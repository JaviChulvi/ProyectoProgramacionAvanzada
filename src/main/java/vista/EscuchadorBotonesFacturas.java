package vista;

import controlador.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscuchadorBotonesFacturas implements ActionListener {

    private Controlador controlador;

    public EscuchadorBotonesFacturas( Controlador controlador){
        this.controlador = controlador;
    }

    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "datos-factura":
                System.out.println("Pulsado datos factura");
                break;
            case "emitir-factura":
                System.out.println("Pulsado emitir factura");
                controlador.hacerFactura();
                break;
            case "listar-facturas":
                System.out.println("Pulsado listar facturas");
                break;
        }
    }
}
