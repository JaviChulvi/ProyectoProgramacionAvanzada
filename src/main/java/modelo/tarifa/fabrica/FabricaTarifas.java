package modelo.tarifa.fabrica;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

import modelo.tarifa.Tarifa;
import modelo.tarifa.TarifaBasica;
import modelo.tarifa.TarifaDiaDeLaSemana;
import modelo.tarifa.TarifaIntervaloHorario;

public class FabricaTarifas implements FabricaT, Serializable {
	private static final long serialVersionUID = 6979669984723076003L;

	public FabricaTarifas(){}

	@Override
	public TarifaBasica getTarifaBasica(double precio) {
		return new TarifaBasica(precio);
	}

	@Override
	public TarifaDiaDeLaSemana getTarifaDiaDeLaSemana(Tarifa tarifa, double precio, DayOfWeek diaDeLaSemana) {
		return new TarifaDiaDeLaSemana(tarifa, precio, diaDeLaSemana);
	}

	@Override
	public TarifaIntervaloHorario getTarifaIntervaloHorario(Tarifa tarifa, double precio, LocalTime horaIni, LocalTime horaFin) {
		return new TarifaIntervaloHorario(tarifa, precio, horaIni, horaFin);
	}

}
