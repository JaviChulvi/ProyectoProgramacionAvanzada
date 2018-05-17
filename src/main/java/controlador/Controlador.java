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
            interfazGrafica.mostrarInformacionClientesVerde("El usuario (particular) se ha añadido al sistema");
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
            interfazGrafica.mostrarInformacionClientesVerde("El usuario (Empresa) se ha añadido al sistema");
        }
    }

    public void borrarCliente(){
        String nif = interfazGrafica.getNifClientes();
        if(!nif.isEmpty()){
            if(empresaTelefonia.containsCliente(nif)){
                empresaTelefonia.borrarCliente(nif);
                interfazGrafica.mostrarInformacionClientesVerde("Operacion reslizada satisfactoriamente");
            } else {
                interfazGrafica.mostrarInformacionClientesRojo("El sistema no contiene un usuario con el nif especificado.");
            }
        } else {
            interfazGrafica.mostrarInformacionClientesRojo("Introduce un NIF para poder realizar la operación.");
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
            interfazGrafica.mostrarInformacionLlamadasVerde("Se ha realizado la llamada.");
        } else {
            interfazGrafica.mostrarInformacionLlamadasRojo("El cliente (NIF) no existe en el sistema.");
        }
    }

    public void hacerFactura(){
        String nif = interfazGrafica.getNifFacturas();
        if(!nif.isEmpty()){
            if(empresaTelefonia.containsCliente(nif)){
                empresaTelefonia.emitirFactura(nif);
                interfazGrafica.mostrarInformacionFacturasVerde("La operación se ha realizado correctamente.");
            } else {
                interfazGrafica.mostrarInformacionFacturasRojo("El sistema no contiene a este cliente");
            }
        } else {
            interfazGrafica.mostrarInformacionFacturasRojo("Rellena el campo NIF");
        }

    }

}
