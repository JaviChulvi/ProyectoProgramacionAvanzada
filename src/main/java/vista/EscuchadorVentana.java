package vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by al364498 on 24/04/18.
 */
public class EscuchadorVentana extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}