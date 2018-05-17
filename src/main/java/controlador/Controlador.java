package controlador;

import modelo.clientes.Direccion;
import modelo.empresaTelefonica.EmpresaTelefonia;
import vista.InterfazGrafica;

import java.util.ArrayList;

public class Controlador {

    private InterfazGrafica interfazGrafica;
    private EmpresaTelefonia empresaTelefonia;

    public  Controlador(InterfazGrafica interfazGrafica, EmpresaTelefonia empresaTelefonia) {
        this.interfazGrafica = interfazGrafica;
        this.empresaTelefonia = empresaTelefonia;
    }

    public void addCliente(){
        if (interfazGrafica.tipoClienteSeleccionado().equals("empresa")) {
            addClienteEmpresa();
        } else {
            addClienteParticular();
        }
    }

    public void addClienteParticular(){
        ArrayList<String> informacionCliente = interfazGrafica.getInformacionAddCliente();
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
            interfazGrafica.mostrarInformacionCLientesVerde("El usuario (particular) se ha añadido al sistema");
        }
    }

    public void addClienteEmpresa(){
        ArrayList<String> informacionCliente = interfazGrafica.getInformacionAddCliente();
        if (!(informacionCliente == null)) {
            String nif = informacionCliente.get(0);
            String nombre = informacionCliente.get(1);
            String codigoPostal = informacionCliente.get(2);
            int cPostal = Integer.parseInt(codigoPostal);
            String provincia = informacionCliente.get(3);
            String poblacion = informacionCliente.get(4);
            Direccion direccion = new Direccion(cPostal, provincia, poblacion);
            String email = informacionCliente.get(5);
            empresaTelefonia.crearClienteEmpresa(nombre, nif, direccion,email );
            interfazGrafica.mostrarInformacionCLientesVerde("El usuario (Empresa) se ha añadido al sistema");
        }
    }

    public void borrarCliente(){
        String nif = interfazGrafica.getNifClientes();
        if(!nif.isEmpty()){
            if(empresaTelefonia.containsCliente(nif)){
                empresaTelefonia.borrarCliente(nif);
                interfazGrafica.mostrarInformacionCLientesVerde("Operacion reslizada satisfactoriamente");
            } else {
                interfazGrafica.mostrarInformacionCLientesRojo("El sistema no contiene un usuario \n con el nif especificado.");
            }
        } else {
            interfazGrafica.mostrarInformacionCLientesRojo("Introduce un NIF para poder realizar la operación.");
        }


    }
    public void hacerLlamada(){
        ArrayList<String> informacionLlamada = interfazGrafica.getInformacionAddLlamada();
        String nif = informacionLlamada.get(0);
        String numeroD = informacionLlamada.get(1);
        int numeroDestino = Integer.parseInt(numeroD);
        String duracionLl = informacionLlamada.get(2);
        int duracionLlamada = Integer.parseInt(duracionLl);
        if(empresaTelefonia.hacerLlamada(nif, numeroDestino, duracionLlamada)){
            interfazGrafica.mostrarInformacionCLientesVerde("Se ha realizado la llamada.");
        } else {
            interfazGrafica.mostrarInformacionCLientesRojo("El cliente (NIF) no existe en el sistema.");
        }
    }

}
