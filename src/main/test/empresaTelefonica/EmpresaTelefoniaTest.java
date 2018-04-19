package empresaTelefonica;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import clientes.Cliente;
import clientes.Direccion;
import clientes.Empresa;
import es.uji.www.GeneradorDatosINE;
import facturas.Factura;
import gestionTelefonia.ImpossibleDateIntervalException;
import llamadas.Llamada;
import tarifa.Tarifa;

public class EmpresaTelefoniaTest {

	@Test
	public void testEmitirFacturaConLlamadas() {
		System.out.println("\n## TEST DE EMISIÓN DE UNA FACTURA CON VARIAS LLAMADAS ##");
		float ratio = 0.7f; // €/min
		System.out.println("Coste en €/min: " + ratio);
		
		int[] minutosDeLasLlamadas = {97, 35, 22, 196, 17, 15};
		float precioEsperado = 0;
		
		System.out.print("Duracion de las llamadas: ");
		for(int i = 0; i < minutosDeLasLlamadas.length; i++){
			precioEsperado += ratio * minutosDeLasLlamadas[i];
			System.out.print(minutosDeLasLlamadas[i] + " ");
		}
	
		LocalDate fechaActual = LocalDate.now();
		LocalDate principioFacturacion = LocalDate.now().minusMonths(1);
		LocalDate finalFacturacion = LocalDate.now().plusMonths(1);
		
		Tarifa tarifa = new Tarifa(ratio);
		List<Llamada> llamadas = new LinkedList<Llamada>();
		for(int i = 0; i < minutosDeLasLlamadas.length; i++)
			llamadas.add(new Llamada(636542197, LocalDate.now(), minutosDeLasLlamadas[i]));
		
		Factura facturaNormal = new Factura(tarifa, fechaActual, principioFacturacion, finalFacturacion);
		facturaNormal.calcularImporte(llamadas);
		
		System.out.println("\nImporte esperado: " + precioEsperado + "\nImporte calculado: " + facturaNormal.getImporte());
		assertEquals(precioEsperado, facturaNormal.getImporte(), 0.001);
	}
	
	@Test
	public void testEmitirFacturaSinLlamadas(){
		System.out.println("\n## TEST DE EMISIÓN DE UNA FACTURA SIN LLAMADAS ##");
		float ratio = 0.7f; // €/min
		
		LocalDate fechaActual = LocalDate.now();
		LocalDate principioFacturacion = LocalDate.now().minusMonths(1);
		LocalDate finalFacturacion = LocalDate.now().plusMonths(1);
		
		Tarifa tarifa = new Tarifa(ratio);
		Factura facturaSinLlamadas = new Factura(tarifa, fechaActual, principioFacturacion, finalFacturacion);
		facturaSinLlamadas.calcularImporte(new LinkedList<Llamada>());
		
		System.out.println("Importe esperado: 0.0\nImporte calculado: " + facturaSinLlamadas.getImporte());
		assertEquals(0, facturaSinLlamadas.getImporte(), 0.001);
	}
	
	@Test
	public void testGestionClientes(){
		System.out.println("\n## TEST DE GESTION DE CLIENTES ##");
		EmpresaTelefonia tel = new EmpresaTelefonia();
		GeneradorDatosINE generador = new GeneradorDatosINE();
		
		// Crear clientes (metodos crearClienteParticular y crearClienteEmpresa)
		System.out.println("·Creación de clientes:");
		for(int i = 0; i < 8; i++){
			String nombre = generador.getNombre();
			String apellido1 = generador.getApellido();
			String apellido2 = generador.getApellido();
			String nif = i + "N";
			String provincia = generador.getProvincia();
			String poblacion = generador.getPoblacion(provincia);
			int codigoPostal = (int)(Math.random()*50000);
			tel.crearClienteParticular(nombre, apellido1, apellido2, nif, new Direccion(codigoPostal, provincia, poblacion), nombre + "@mail.es", Math.random());
			System.out.println("Creado cliente (particular) de nombre " + nombre + " " + apellido1 + " " + apellido2 + " y NIF " + nif);
		}

		// Devolver lista con los clientes (metodo listarClientes)
		tel.crearClienteParticular("Peter", "Dougan", "Capaldi", "53219723W", new Direccion(), "", 0.0);
		assertThat(tel.listarClientes().size(), is(9));
		System.out.println("Creado cliente (particular) de nombre Peter Dougan Capaldi y NIF 53219723W");
		
		tel.crearClienteEmpresa("BBC", "12591462L", new Direccion(), "", 0.0);
		assertThat(tel.listarClientes().size(), is(10));
		System.out.println("Creado cliente (empresa) de nombre BBC y NIF 12591462L");
		
		// Comprobar que el metodo datosCliente muestra información correcta (ver consola)
		System.out.println("\n·Obtención de datos de dos clientes aleatorios:");
		System.out.println(tel.datosCliente("2N"));
		System.out.println(tel.datosCliente("5N"));
				
		// Eliminar clientes (metodo borrarCliente)
		System.out.println("\n·Borrado de clientes:");
		tel.borrarCliente("53219723W");
		tel.borrarCliente("12591462L");	
		
		if(tel.borrarCliente("NoExiste") == true)
			fail();
		assertThat(tel.listarClientes().size(), is(8));
		System.out.println("Eliminados clientes de NIF 53219723W y 12591462L");
		
		// Comprobar que el metodo datosCliente devuelve null cuando no existe el cliente
		if(tel.datosCliente("53219723W") != null || tel.datosCliente("12591462L") != null)
			fail();
	}
	
	@Test(expected=ImpossibleDateIntervalException.class)
	public void testExcepcionFechas(){
		System.out.println("\n## TEST DE IMPOSSIBLE_DATE_INTERVAL_EXCEPTION ##");
		Factura factura = new Factura(new Tarifa(), LocalDate.now(), LocalDate.now(), LocalDate.now().minusMonths(1));
		System.out.println("ERROR: Se esperaba que se lanzase la excepción ImpossibleDateIntervalException().");
	}
	
	@Test
	public void testFiltrarPorFecha(){
		System.out.println("\n## TEST DE FILTRAR_POR_FECHA ##");
		LocalDate dentroDeMedioMes = LocalDate.now().plusDays(15);
		LocalDate hoy = LocalDate.now();
		LocalDate haceUnMes = LocalDate.now().minusMonths(1);
		LocalDate haceMedioMes = LocalDate.now().minusDays(15);
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Empresa(null, "123", null, null, hoy, null, null, null));
		clientes.add(new Empresa(null, "456", null, null, hoy, null, null, null));
		clientes.add(new Empresa(null, "abc", null, null, haceUnMes, null, null, null));
		clientes.add(new Empresa(null, "789", null, null, hoy, null, null, null));
		clientes.add(new Empresa(null, "def", null, null, haceUnMes, null, null, null));
		
		ArrayList<Cliente> resultado = (ArrayList<Cliente>) new EmpresaTelefonia().filtrarPorFecha(clientes, haceMedioMes, dentroDeMedioMes);
		
		assertThat(resultado.size(), is(3));
	}
}




