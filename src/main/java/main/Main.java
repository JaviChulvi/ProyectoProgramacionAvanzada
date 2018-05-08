package main;

import modelo.empresaTelefonica.EmpresaTelefonia;
import vista.InterfazGrafica;

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
        InterfazGrafica window = new InterfazGrafica();
        window.ejecutar();
    }
}