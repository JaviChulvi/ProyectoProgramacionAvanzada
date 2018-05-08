package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modelo.empresaTelefonica.EmpresaTelefonia;

public class DatosEnFichero {

	public DatosEnFichero(){}
	
	public EmpresaTelefonia leerDesdeFichero(String nombre) {
		EmpresaTelefonia telefonica = new EmpresaTelefonia();
		
		File archivo = new File(nombre);
        try {
	        if(archivo.isFile()){
	        	FileInputStream fis = new FileInputStream(nombre);
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
        
        return telefonica;
	}
	
	public void guardarEnFichero(String nombre, EmpresaTelefonia telefonica){
		FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(nombre);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(telefonica);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
