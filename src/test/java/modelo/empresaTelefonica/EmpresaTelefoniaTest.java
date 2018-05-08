package modelo.empresaTelefonica;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import modelo.clientes.Cliente;
import modelo.clientes.Empresa;
import modelo.facturas.Factura;
import modelo.gestionTelefonia.ImpossibleDateIntervalException;
import modelo.llamadas.Llamada;
import modelo.tarifa.Tarifa;
import modelo.tarifa.TarifaBasica;
import modelo.tarifa.TarifaDiaDeLaSemana;
import modelo.tarifa.TarifaIntervaloHorario;

public class EmpresaTelefoniaTest {

	@Test
	public void testEmitirFacturaConLlamadas() {
		System.out.println("\n## TEST DE EMISIÓN DE UNA FACTURA CON VARIAS LLAMADAS ##");
		float ratio = 0.7f; // €/min
		System.out.println("Coste en €/min: " + ratio);

		int[] minutosDeLasLlamadas = {97, 35, 22, 196, 17, 15};
		float precioEsperado = 0;

		System.out.print("Duracion de las modelo.llamadas: ");
		for(int i = 0; i < minutosDeLasLlamadas.length; i++){
			precioEsperado += ratio * minutosDeLasLlamadas[i];
			System.out.print(minutosDeLasLlamadas[i] + " ");
		}

		LocalDate fechaActual = LocalDate.now();
		LocalDate principioFacturacion = LocalDate.now().minusMonths(1);
		LocalDate finalFacturacion = LocalDate.now().plusMonths(1);

		Tarifa tarifa = new TarifaBasica(ratio);
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

		LocalDate fechaActual = LocalDate.now();
		LocalDate principioFacturacion = LocalDate.now().minusMonths(1);
		LocalDate finalFacturacion = LocalDate.now().plusMonths(1);

		Tarifa tarifa = new TarifaBasica(0.15);
		Factura facturaSinLlamadas = new Factura(tarifa, fechaActual, principioFacturacion, finalFacturacion);
		facturaSinLlamadas.calcularImporte(new LinkedList<Llamada>());

		System.out.println("Importe esperado: 0.0\nImporte calculado: " + facturaSinLlamadas.getImporte());
		assertEquals(0, facturaSinLlamadas.getImporte(), 0.001);
	}

/*	@Test
	public void testGestionClientes(){
		System.out.println("\n## TEST DE GESTION DE CLIENTES ##");
		EmpresaTelefonia tel = new EmpresaTelefonia();
		GeneradorDatosINE generador = new GeneradorDatosINE();

		// Crear modelo.clientes (metodos crearClienteParticular y crearClienteEmpresa)
		System.out.println("·Creación de modelo.clientes:");
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

		// Devolver lista con los modelo.clientes (metodo listarClientes)
		tel.crearClienteParticular("Peter", "Dougan", "Capaldi", "53219723W", new Direccion(), "", 0.0);
		assertThat(tel.listarClientes().size(), is(9));
		System.out.println("Creado cliente (particular) de nombre Peter Dougan Capaldi y NIF 53219723W");

		tel.crearClienteEmpresa("BBC", "12591462L", new Direccion(), "", 0.0);
		assertThat(tel.listarClientes().size(), is(10));
		System.out.println("Creado cliente (empresa) de nombre BBC y NIF 12591462L");

		// Comprobar que el metodo datosCliente muestra información correcta (ver consola)
		System.out.println("\n·Obtención de datos de dos modelo.clientes aleatorios:");
		System.out.println(tel.datosCliente("2N"));
		System.out.println(tel.datosCliente("5N"));

		// Eliminar modelo.clientes (metodo borrarCliente)
		System.out.println("\n·Borrado de modelo.clientes:");
		tel.borrarCliente("53219723W");
		tel.borrarCliente("12591462L");

		if(tel.borrarCliente("NoExiste") == true)
			fail();
		assertThat(tel.listarClientes().size(), is(8));
		System.out.println("Eliminados modelo.clientes de NIF 53219723W y 12591462L");

		// Comprobar que el metodo datosCliente devuelve null cuando no existe el cliente
		if(tel.datosCliente("53219723W") != null || tel.datosCliente("12591462L") != null)
			fail();
	}
*/
	
	@Test(expected=ImpossibleDateIntervalException.class)
	public void testExcepcionFechas(){
		System.out.println("\n## TEST DE IMPOSSIBLE_DATE_INTERVAL_EXCEPTION ##");
		Factura factura = new Factura(new TarifaBasica(0.15), LocalDate.now(), LocalDate.now(), LocalDate.now().minusMonths(1));
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
		clientes.add(new Empresa(null, "123", null, null, hoy, null));
		clientes.add(new Empresa(null, "456", null, null, hoy, null));
		clientes.add(new Empresa(null, "abc", null, null, haceUnMes, null));
		clientes.add(new Empresa(null, "789", null, null, hoy, null));
		clientes.add(new Empresa(null, "def", null, null, haceUnMes, null));

		ArrayList<Cliente> resultado = (ArrayList<Cliente>) new EmpresaTelefonia().filtrarPorFecha(clientes, haceMedioMes, dentroDeMedioMes);

		assertThat(resultado.size(), is(3));
	}

	@Test
	public void testTarifa() {
		System.out.println("\n## TEST DE DECORADOR ##");
		LocalDate fecha1 = LocalDate.of(2018, 4, 22);		// Día de la llamada 1: un domingo cualquiera
		LocalDate fecha2 = LocalDate.of(2018, 4, 19);		// Día de la llamada 2: un jueves cualquiera
		LocalTime hora1 = LocalTime.of(3, 0); 				// Hora de realización de la llamada 1: 3pm o 3:00
        LocalTime hora2 = LocalTime.of(18, 0); 				// Hora de realización de la llamada 2: 6pm o 18:00
        
        LocalTime horaInicioOferta = LocalTime.of(16, 0); 	// Hora de inicio de la oferta: 4pm o 16:00
        LocalTime horaFinOferta = LocalTime.of(20, 0); 		// Hora de final de la oferta: 8pm o 20:00
        
		Llamada llamada1 = new Llamada(420, fecha1, 7, hora1); 	// Llamada de 7 minutos un domingo a las 3:00
		Llamada llamada2 = new Llamada(666, fecha2, 5, hora2); 	// Llamada de 5 minutos un jueves a las 18:00
		
		Tarifa tarifa = new TarifaBasica(0.15);
		assertThat(tarifa.calcularPrecioLlamada(llamada1), is(1.05)); 	// 0.15 €/min * 7 min = 1.05 €
		assertThat(tarifa.calcularPrecioLlamada(llamada2), is(0.75));	// 0.15 €/min * 5 min = 0.75 €
		
		tarifa = new TarifaIntervaloHorario(tarifa, 0.10, horaInicioOferta, horaFinOferta);
		assertThat(tarifa.calcularPrecioLlamada(llamada1), is(1.05));	// NO se aplica oferta de tarde: 1.05 €
		assertThat(tarifa.calcularPrecioLlamada(llamada2), is(0.50));	// Se aplica la oferta de tarde: 0.10 €/min * 5 min = 0.50 €
		
		tarifa = new TarifaDiaDeLaSemana(tarifa, 0, DayOfWeek.SUNDAY);
		assertThat(tarifa.calcularPrecioLlamada(llamada1), is(0.0));	// Se aplica la modelo.tarifa de domingos: 0 €
		assertThat(tarifa.calcularPrecioLlamada(llamada2), is(0.50));	// NO se aplica la modelo.tarifa de domingos, pero si la anterior: 0.50 €
	}
}










