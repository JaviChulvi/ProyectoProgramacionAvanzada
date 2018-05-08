package modelo.tarifa.fabrica;

import java.time.DayOfWeek;
import java.time.LocalTime;

import modelo.tarifa.Tarifa;
import modelo.tarifa.TarifaBasica;
import modelo.tarifa.TarifaDiaDeLaSemana;
import modelo.tarifa.TarifaIntervaloHorario;

public interface FabricaT {

	public TarifaBasica getTarifaBasica(double precio);
	
	public TarifaDiaDeLaSemana getTarifaDiaDeLaSemana(Tarifa tarifa, double precio, DayOfWeek diaDeLaSemana);
	
	public TarifaIntervaloHorario getTarifaIntervaloHorario(Tarifa tarifa, double precio, LocalTime horaIni, LocalTime horaFin);
	
}