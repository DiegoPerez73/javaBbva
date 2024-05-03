package cliente.cuenta;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CuentaTest {
	private double saldo;
	private Cuenta cuenta;

	@BeforeEach
	void inicializar() throws ExcepcionMontoInvalido {
		this.saldo = 20.0;
		cuenta = new Cuenta(saldo);

	}

	@Test
	void constructorCuentaPositivo() throws ExcepcionMontoInvalido {
		Cuenta c = new Cuenta(100.0);
		assertEquals(100, c.saldo());
	}

	@Test
	void constructorCuentaNegativo() throws ExcepcionMontoInvalido {
		assertThrows(ExcepcionMontoInvalido.class, () -> new Cuenta(-100.0));
	}

	@Test
	void constructorCuentaValor0() throws ExcepcionMontoInvalido {
		assertThrows(ExcepcionMontoInvalido.class, () -> new Cuenta(0));
	}

	@Test
	void devuelveSaldo() throws ExcepcionMontoInvalido {
		assertEquals(cuenta.saldo, cuenta.saldo());
	}

	@Test
	void disponible() throws ExcepcionMontoInvalido {
		assertEquals(cuenta.saldo, cuenta.disponible());
	}

	@Test
	void comparteTo() throws ExcepcionMontoInvalido {
		Cuenta cuentaMenorSaldo = new Cuenta(10.0);
		Cuenta cuentaMayorSaldo = new Cuenta(30.0);

		assertEquals(1.0, cuenta.compareTo(cuentaMenorSaldo));
		assertEquals(-1.0, cuenta.compareTo(cuentaMayorSaldo));
	}

	@Test
	void arrojaExcepcionMontoInvalido() throws ExcepcionMontoInvalido {
		assertThrows(ExcepcionMontoInvalido.class, () -> cuenta.validar(-10.0));
	}

	@Test
	void depositarExitoso() throws ExcepcionMontoInvalido {
		double saldoInicial = cuenta.saldo();
		double montoDepositado = 20.0;
		cuenta.depositar(montoDepositado);

		assertEquals(saldoInicial + montoDepositado, cuenta.saldo());
		assertDoesNotThrow(() -> cuenta.depositar(montoDepositado));
	}

	@Test
	void depositarError() throws ExcepcionMontoInvalido {
		double montoNegativo = -10.0;
		assertThrows(ExcepcionMontoInvalido.class, () -> cuenta.depositar(montoNegativo));
	}

	@Test
	void extraerExitoso() throws ExcepcionMontoInvalido, ExcepcionSaldoInsuficiente {
		double saldoInicial = cuenta.saldo();
		double montoPositivo = 10.0;
		cuenta.extraer(montoPositivo);
		assertEquals(saldoInicial - montoPositivo, cuenta.saldo());
		assertDoesNotThrow(() -> cuenta.depositar(montoPositivo));
	}

	@Test
	void extraerErrorMonto() throws ExcepcionMontoInvalido {
		double montoNegativo = -10.0;
		assertThrows(ExcepcionMontoInvalido.class, () -> cuenta.extraer(montoNegativo));
	}

	@Test
	void extraerErrorSaldoInsuf() throws ExcepcionSaldoInsuficiente {
		double montoPositivo = 30.0;
		assertThrows(ExcepcionSaldoInsuficiente.class, () -> cuenta.extraer(montoPositivo));
	}

	@Test
	void transferirExitoso() throws ExcepcionMontoInvalido, ExcepcionSaldoInsuficiente {
		// Cuenta destino
		Cuenta cuentaDestino = new Cuenta(5.0);
		double saldoCuentaDestino = cuentaDestino.saldo();
		// Cuenta origen
		double saldoOrigen = cuenta.saldo();
		// Monto transferido
		double montoTransferidoPositivo = 10.0;

		cuenta.transferir(montoTransferidoPositivo, cuentaDestino);

		assertEquals(saldoOrigen - montoTransferidoPositivo, cuenta.saldo());
		assertEquals(saldoCuentaDestino + montoTransferidoPositivo, cuentaDestino.saldo());
	}

	@Test
	void transferirErrorMonto() throws ExcepcionMontoInvalido, ExcepcionSaldoInsuficiente {
		Cuenta cuentaDestino = new Cuenta(5.0);

		double MontoMenorA0 = -10.0;

		double montoMayorASaldo = 25.0;

		assertThrows(ExcepcionMontoInvalido.class, () -> cuenta.transferir(MontoMenorA0, cuentaDestino));
		assertThrows(ExcepcionSaldoInsuficiente.class, () -> cuenta.transferir(montoMayorASaldo, cuentaDestino));
	}

	@Test
	void toStringExitoso() throws ExcepcionMontoInvalido {
		assertEquals("Cuenta [saldo=" + cuenta.saldo() + ", fechaApertura =" + cuenta.getFechaApertura() + "]",
				cuenta.toString());
	}

	@Test
	void compareToMayorA0() throws ExcepcionMontoInvalido {
		Cuenta cuentaA = new Cuenta(300.0);
		Cuenta cuentaB = new Cuenta(100.0);
		assertTrue(cuentaA.compareTo(cuentaB) > 0);
		assertTrue(cuentaB.compareTo(cuentaA) < 0);
	}

	@Test
	void compareToMenorA0() throws ExcepcionMontoInvalido {
		Cuenta cuentaA = new Cuenta(300.0);
		Cuenta cuentaB = new Cuenta(400.0);
		assertTrue(cuentaA.compareTo(cuentaB) < 0);
	}

	@Test
	void compareToIgualA0() throws ExcepcionMontoInvalido {
		Cuenta cuentaA = new Cuenta(300.0);
		Cuenta cuentaB = new Cuenta(300.0);
		assertTrue(cuentaA.compareTo(cuentaB) == 0);
	}
	
	@Test
	void CuentaAMayor() throws ExcepcionMontoInvalido {
		Cuenta cuentaA = new Cuenta(300.0);
		Cuenta cuentaB = new Cuenta(200.0);
		assertEquals(cuentaA, Cuenta.mayor(cuentaA, cuentaB));
	}
	
	@Test
	void CuentaBMayor() throws ExcepcionMontoInvalido {
		Cuenta cuentaA = new Cuenta(300.0);
		Cuenta cuentaB = new Cuenta(400.0);
		assertEquals(cuentaB, Cuenta.mayor(cuentaA, cuentaB));
	}
	
}
