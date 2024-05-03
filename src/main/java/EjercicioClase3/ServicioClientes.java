package EjercicioClase3;

public class ServicioClientes {
	private long idCliente;
	private double descuentoCliente;
	
	public ServicioClientes (long idCliente, double descuentoCliente) {
		this.idCliente = idCliente;
		this.descuentoCliente = descuentoCliente;
	}
	
	public double getDescuentoCliente () {
		return this.descuentoCliente;
	}

	public long getIdCliente() {
		return idCliente;
	}

}
