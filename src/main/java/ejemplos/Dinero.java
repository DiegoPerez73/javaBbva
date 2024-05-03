package ejemplos;

public class Dinero {
	//private static final long serialVersionUID = 1L;
	private final double suma;
	private final String moneda;

	public Dinero(int suma, String moneda) throws ExcepcionMonedaNula {
		if(moneda == null)
			throw new ExcepcionMonedaNula();
		this.suma = suma;
		this.moneda = moneda; 
	}

	public double monto() { 
		return suma;
	}
	public String moneda() {
		return moneda; 
	}

	@Override
	public boolean equals(Object unObjeto) {
		if (unObjeto instanceof Dinero) {
			Dinero dinero = (Dinero) unObjeto;
			return dinero.moneda().equals(moneda())
					&& monto() == dinero.monto();
		}
		return false;
	}

	@Override
	public String toString() {
		return "Monto:"+suma+"-----"+"MOneda:"+moneda;
	}
}