package cliente.cuenta;

public class Cliente {
	private int dni;
	private String nombre;
	private String apellido;
	private Cuenta cuenta;

	public Cliente(String nombre, String apellido, int dni, Cuenta cuenta)
			throws ExcepcionNombreNulo, ExcepcionApellidoNulo, ExcepcionDniNulo, ExcepcionCuentaNula {
		if (nombre == null)
			throw new ExcepcionNombreNulo();
		this.nombre = nombre;
		if (apellido == null)
			throw new ExcepcionApellidoNulo();
		this.apellido = apellido;
		if (dni == 0)
			throw new ExcepcionDniNulo();
		this.dni = dni;
		if (cuenta == null)
			throw new ExcepcionCuentaNula();
		this.cuenta = cuenta;
	}

	public String nombre() {
		return this.nombre;
	}

	public String apellido() {
		return this.apellido;
	}

	public int dni() {
		return this.dni;
	}

	public Cuenta cuenta() {
		return cuenta;
	}

	public void pagarConTransferencia(double monto, Cuenta cuentaDestino)
			throws ExcepcionSaldoInsuficiente, ExcepcionMontoInvalido, ExcepcionCuentaNula {
		if (cuentaDestino == null) {
			throw new ExcepcionCuentaNula();
		} else {
			cuenta.transferir(monto, cuentaDestino);
		}
	}

	public double saldo() {
		return cuenta.saldo();
	}

}
