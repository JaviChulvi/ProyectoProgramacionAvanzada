package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import empresaTelefonica.EmpresaTelefonia;
import metodosGestionDatos.consola.MenuConsola;

/**
 * Created by al364498 on 20/02/18.
 */

public class Main {
    private static EmpresaTelefonia telefonica;
    
    public static void main(String[] args) {
    	
    	// Cargar datos desde fichero (o crearlo si no existe)
    	File archivo = new File("informacion");
        try {
	        if(archivo.isFile()){
	        	FileInputStream fis = new FileInputStream("informacion");
	        	ObjectInputStream ois = new ObjectInputStream(fis);
	            telefonica = (EmpresaTelefonia) ois.readObject();
	            ois.close();
	            fis.close();
	        } else {
	        	archivo.createNewFile();
	        	telefonica = new EmpresaTelefonia();
	        } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    	// Crear sistema de input y output
    	MenuConsola interfaz = new MenuConsola(telefonica);
    	
    	// Iniciar el sistema
        interfaz.iniciarMenu();
        
        // Guardar datos en fichero
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("informacion");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(telefonica);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}