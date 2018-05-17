package controlador;

import modelo.clientes.Direccion;
import modelo.empresaTelefonica.EmpresaTelefonia;
import vista.InterfazGrafica;

import javax.swing.*;
import java.util.ArrayList;

public class Controlador {

    public InterfazGrafica interfazGrafica;
    public EmpresaTelefonia empresaTelefonia;

    public  Controlador(InterfazGrafica interfazGrafica, EmpresaTelefonia empresaTelefonia) {
        this.interfazGrafica = interfazGrafica;
        this.empresaTelefonia = empresaTelefonia;
    }

    public void addCliente(){
        ArrayList<String> informacionCliente = interfazGrafica.recogerInformacionAddCliente();

        if (!(informacionCliente == null)) {
            String nif = informacionCliente.get(0);
            String nombre = informacionCliente.get(1);
            String apellido1 = informacionCliente.get(2);
            String apellido2 = informacionCliente.get(3);
            String codigoPostal = informacionCliente.get(4);
            int cPostal = Integer.parseInt(codigoPostal);
            String provincia = informacionCliente.get(5);
            String poblacion = informacionCliente.get(6);
            Direccion direccion = new Direccion(cPostal, provincia, poblacion);
            String email = informacionCliente.get(7);
            empresaTelefonia.crearClienteParticular(nombre,apellido1, apellido2, nif, direccion,email );
        }


    }

    /*public void borrarCliente(){
        String nif = interfazGrafica.getNifClientes();
        empresaTelefonia.borrarCliente(nif);
    }*/

}
