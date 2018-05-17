package main;

import controlador.Controlador;
import modelo.empresaTelefonica.EmpresaTelefonia;
import vista.InterfazGrafica;

import javax.swing.*;

import static java.awt.SystemColor.window;

/**
 * Created by al364498 on 20/02/18.
 */

public class Main {
    private static EmpresaTelefonia telefonica;
    
    public static void main(String[] args) {
    	
    	/*
    	// Cargar datos desde fichero (o crearlo si no existe)
    	DatosEnFichero fichero = new DatosEnFichero();
    	telefonica = fichero.leerDesdeFichero("informacion");
        
    	// Crear sistema de input y output
    	MenuConsola interfaz = new MenuConsola(telefonica);
    	
    	// Iniciar el sistema
        interfaz.iniciarMenu();
        
        // Guardar datos en fichero
        fichero.guardarEnFichero("informacion", telefonica);
        */
    	EmpresaTelefonia telefonica = new EmpresaTelefonia();
        InterfazGrafica window = new InterfazGrafica();
        window.setTelefonia(telefonica);
        Controlador controlador = new Controlador(window, telefonica);
        window.setConntrolador(controlador);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                window.ejecutar();
            }
        });
    }
}