package main;

import es.uji.belfern.generador.GeneradorDatosINE;

import java.util.Scanner;

/**
 * Created by al364498 on 20/02/18.
 */
public class Main {
    public static void main(String[] args){
        /*GeneradorDatosINE generador = new GeneradorDatosINE();

        System.out.println(generador.getNombre());
        System.out.println(generador.getApellido());
        System.out.println(generador.getEdad());
        System.out.println(generador.getNIF());*/



        System.out.println(Menu.getMenu());
        Scanner scanner = new Scanner(System.in);
        System.out.print("Elije una opción:");
        byte opcion = scanner.nextByte();
        Menu opcionMenu = Menu.getOpcion(opcion);
        switch(opcionMenu) {
            case CONSULTA_SALDO:
                System.out.println("Opción 1.");
                break;
            case REALIZAR_TRANSFERENCIA:
                System.out.println("Opción 2.");
                break;
        }
    }
}
