package main;

import es.uji.belfern.generador.GeneradorDatosINE;

/**
 * Created by al364498 on 20/02/18.
 */
public class Main {
    public static void main(String[] args){
        GeneradorDatosINE generador = new GeneradorDatosINE();

        System.out.println(generador.getNombre());
        System.out.println(generador.getApellido());
        System.out.println(generador.getEdad());
        System.out.println(generador.getNIF());
    }
}
