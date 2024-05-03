package cliente.cuenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {
	private int dni;
	private String nombre;
	private String apellido;
	private Cuenta cuenta;
	private Cliente cliente;

	@BeforeEach
	void inicializar(@Mock Cuenta cuenta) throws ExcepcionNombreNulo, ExcepcionApellidoNulo, ExcepcionDniNulo,
			ExcepcionCuentaNula, ExcepcionMontoInvalido {
		this.dni = 1;
		this.nombre = "TestNombre";
		this.apellido = "TestApellido";
		this.cuenta = cuenta;
		cliente = new Cliente(nombre, apellido, dni, cuenta);
	}

	@Test
	void devuelveDni() {
		assertEquals(1, cliente.dni());
	}

	@Test
	void devuelveNombre() {
		assertEquals("TestNombre", cliente.nombre());
	}

	@Test
	void devuelveApellido() {
		assertEquals("TestApellido", cliente.apellido());
	}

	@Test
	void devuelveCuenta() {
		assertEquals(cuenta, cliente.cuenta());
	}

	@Test
	void devuelveSaldo() {
		assertEquals(cuenta.saldo(), cliente.saldo());
	}

	@Test
	void transferirExitoso() throws ExcepcionSaldoInsuficiente, ExcepcionMontoInvalido, ExcepcionCuentaNula,
			ExcepcionNombreNulo, ExcepcionApellidoNulo, ExcepcionDniNulo {
		// Cuenta destino
		double saldoCuentaDestino = 20.0;
		Cuenta cuentaDestino = new Cuenta(saldoCuentaDestino);

		// Cuenta origen
		double saldoCuentaOrigen = 30.0;
		Cuenta cuentaOrigen = new Cuenta(saldoCuentaOrigen);
		Cliente clienteOrigen = new Cliente("test", "test", 123, cuentaOrigen);

		// Saldo a transferir
		double transferencia = 10.0;

		clienteOrigen.pagarConTransferencia(transferencia, cuentaDestino);

		assertEquals(saldoCuentaOrigen - transferencia, cuentaOrigen.saldo());
	}

	@Test
	void transferirErrors() throws ExcepcionMontoInvalido, ExcepcionSaldoInsuficiente, ExcepcionCuentaNula,
			ExcepcionNombreNulo, ExcepcionApellidoNulo, ExcepcionDniNulo {
		// Cuenta destino
		double saldoCuentaDestino = 20.0;
		Cuenta cuentaDestino = new Cuenta(saldoCuentaDestino);

		// Cuenta origen válida
		double saldoCuentaOrigen = 30.0;
		Cuenta cuentaOrigen = new Cuenta(saldoCuentaOrigen);
		Cliente clienteOrigen = new Cliente("test", "test", 123, cuentaOrigen);

		// Saldo a transferir
		double transferenciaInvalida = -10.0;
		double transferenciaValida = 10.0;
		double transferenciaSaldoInsuf = 50.0;

		assertThrows(ExcepcionMontoInvalido.class,
				() -> clienteOrigen.pagarConTransferencia(transferenciaInvalida, cuentaDestino));
		assertThrows(ExcepcionCuentaNula.class, 
				() -> clienteOrigen.pagarConTransferencia(transferenciaValida, null));
		assertThrows(ExcepcionSaldoInsuficiente.class,
				() -> clienteOrigen.pagarConTransferencia(transferenciaSaldoInsuf, cuentaDestino));

	}

}
