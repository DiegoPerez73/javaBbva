package cliente.cuenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//Le doy un orden para correr los tests
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

	@Order(1)
	@Test
	void constructorClienteErrores() {
		assertThrows(ExcepcionNombreNulo.class, () -> new Cliente(null, "testApellido", 123, new Cuenta(20.0)));
		assertThrows(ExcepcionApellidoNulo.class, () -> new Cliente("nombre", null, 123, new Cuenta(20.0)));
		assertThrows(ExcepcionDniNulo.class, () -> new Cliente("nombre", "testApellido", 0, new Cuenta(20.0)));
		assertThrows(ExcepcionCuentaNula.class, () -> new Cliente("nombre", "testApellido", 123, null));
		assertThrows(ExcepcionMontoInvalido.class, () -> new Cliente("nombre", "testApellido", 123, new Cuenta(-10)));
	}

	@Order(2)
	@Test
	void constructorClienteExitoso() throws ExcepcionNombreNulo, ExcepcionApellidoNulo, ExcepcionDniNulo,
			ExcepcionCuentaNula, ExcepcionMontoInvalido {
		Cuenta cu = new Cuenta(10.0);
		Cliente c = new Cliente("nombre", "testApellido", 123, cu);
		assertEquals("nombre", c.nombre());
		assertEquals("testApellido", c.apellido());
		assertEquals(123, c.dni());
		assertEquals(cu, c.cuenta());
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
	
	@Order(3)
	@Test
	void transferirConVerifyPorSerVoid(@Mock Cuenta ctaDestino) throws ExcepcionSaldoInsuficiente,
			ExcepcionMontoInvalido, ExcepcionCuentaNula, ExcepcionNombreNulo, ExcepcionApellidoNulo, ExcepcionDniNulo {
		Cliente c = new Cliente(nombre, apellido, dni, cuenta);
		double monto = 500.0;
		// Ejecuto la transferencia en cliente c
		c.pagarConTransferencia(monto, ctaDestino);
		// Verifico si la cuenta ejecuto una transferencia.
		verify(cuenta).transferir(monto, ctaDestino);
	}

	@Order(4)
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
		assertThrows(ExcepcionCuentaNula.class, () -> clienteOrigen.pagarConTransferencia(transferenciaValida, null));
		assertThrows(ExcepcionSaldoInsuficiente.class,
				() -> clienteOrigen.pagarConTransferencia(transferenciaSaldoInsuf, cuentaDestino));
	}

}
