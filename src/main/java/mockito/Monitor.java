package mockito;

public class Monitor {
	Vehiculo vehiculo;

	public Monitor(Vehiculo v) {
		vehiculo = v;
	}

	public void setVehiculo(Vehiculo v) {
		vehiculo = v;
	}

	public boolean necesitaAyuda() {
		boolean necesitaAyuda; 
		try {
			necesitaAyuda = !vehiculo.tieneCombustible();						
		} catch (Exception e) {
			necesitaAyuda = true;
		}
		return necesitaAyuda;
	}

	public void acelerar() {
		vehiculo.acelerar();
	}

}
