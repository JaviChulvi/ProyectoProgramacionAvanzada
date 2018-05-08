package modelo.clientes.fabrica;

import java.time.LocalDate;

import modelo.clientes.Direccion;
import modelo.clientes.Empresa;
import modelo.clientes.Particular;

public interface FabricaC {

	public Empresa getClienteEmpresa(String nombre, String NIF, Direccion direccion, String email, LocalDate fechaDeAlta, double precioMin);
	
	public Particular getClienteParticular(String nombre, String NIF, Direccion direccion, String email, LocalDate fechaDeAlta, double precioMin, String apellido1, String apellido2);
	
}
