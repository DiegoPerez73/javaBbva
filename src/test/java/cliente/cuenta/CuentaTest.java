package cliente.cuenta;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
		double montoDepositado = -10.0;
		assertThrows(ExcepcionMontoInvalido.class, () -> cuenta.depositar(montoDepositado));
	}

	@Test
	void extraerExitoso() throws ExcepcionMontoInvalido, ExcepcionSaldoInsuficiente {
		double saldoInicial = cuenta.saldo();
		double montoExtraido = 10.0;
		cuenta.extraer(montoExtraido);
		assertEquals(saldoInicial - montoExtraido, cuenta.saldo());
		assertDoesNotThrow(() -> cuenta.depositar(montoExtraido));
	}

	@Test
	void extraerErrorMonto() throws ExcepcionMontoInvalido {
		double montoExtraido = -10.0;
		assertThrows(ExcepcionMontoInvalido.class, () -> cuenta.extraer(montoExtraido));
	}

	@Test
	void extraerErrorSaldoInsuf() throws ExcepcionSaldoInsuficiente {
		double montoExtraido = 30.0;
		assertThrows(ExcepcionSaldoInsuficiente.class, () -> cuenta.extraer(montoExtraido));
	}

	@Test
	void transferirExitoso() throws ExcepcionMontoInvalido, ExcepcionSaldoInsuficiente {
		// Cuenta destino
		Cuenta cuentaDestino = new Cuenta(5.0);
		double saldoCuentaDestino = cuentaDestino.saldo();
		// Cuenta origen
		double saldoOrigen = cuenta.saldo();
		// Monto transferido
		double montoTransferido = 10.0;

		cuenta.transferir(montoTransferido, cuentaDestino);

		assertEquals(saldoOrigen - montoTransferido, cuenta.saldo());
		assertEquals(saldoCuentaDestino + montoTransferido, cuentaDestino.saldo());
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
		assertEquals("Cuenta [saldo=20.0, fechaApertura =" + cuenta.getFechaApertura() + "]", cuenta.toString());
	}

}
