package modelo.gestionTelefonia;

public class ImpossibleDateIntervalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImpossibleDateIntervalException() {}
	
	public ImpossibleDateIntervalException(String message) { 
		super(message); 
	}
}
