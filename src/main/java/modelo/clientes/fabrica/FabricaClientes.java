package modelo.clientes.fabrica;

import java.io.Serializable;
import java.time.LocalDate;

import modelo.clientes.Direccion;
import modelo.clientes.Empresa;
import modelo.clientes.Particular;
import modelo.tarifa.TarifaBasica;

public class FabricaClientes implements FabricaC, Serializable {
	private static final long serialVersionUID = 2053012348275677428L;

	public FabricaClientes(){}
	
	@Override
	public Empresa getClienteEmpresa(String nombre, String NIF, Direccion direccion, String email, LocalDate fechaDeAlta, double precioMin) {
		return new Empresa(nombre, NIF, direccion, email, fechaDeAlta, new TarifaBasica(precioMin));
	}

	@Override
	public Particular getClienteParticular(String nombre, String NIF, Direccion direccion, String email, LocalDate fechaDeAlta, double precioMin, String apellido1, String apellido2) {
		return new Particular(nombre, NIF, direccion, email, fechaDeAlta, new TarifaBasica(precioMin), apellido1, apellido2);
	}

}
