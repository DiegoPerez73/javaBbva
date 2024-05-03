
package ejemplos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)	//Le das un orden definido a la ejecucion de tests
@ExtendWith(AntesDeCadaTests.class)	//Extendes de una clase para hacer lo que quieras (antes, despues etc.)
class Dinero1Tests {
	
	@Order(2)
	@Test
	void alConstruirConMonedaNulaExcepcion() {
		assertThrows(ExcepcionMonedaNula.class, () -> new Dinero(1, null));
	}

	@Test
	void alConstruirConValoresCorrectos() throws ExcepcionMonedaNula {
		Dinero dinero = new Dinero(100, "Pesos");
		assertEquals(100, dinero.monto(), () -> "valida monto");
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 3, 5, -3, 15 })
	void varios(int x) throws ExcepcionMonedaNula {
		Dinero dinero = new Dinero(100, "Pesos");
		assertEquals(100 + x, dinero.monto() + x);
	}

	/*
	 * @Test void excepcionMonedaNula() { ExcepcionMonedaNula monNula =
	 * assertThrows(ExcepcionMonedaNula.class, new ExcepcionMonedaNula());
	 * assertEquals("Moneda Nula",monNula.getMessage()); }
	 */

	@Tag("Correcto")
	@Test
	void excepcionMonedaNula2() {
		assertThrows(ExcepcionMonedaNula.class, new ExecutableMonedaNula());
	}
	
	@Tag("LeAsignoUnTag")	//Sirve para desde la testSuite excluirlo, incluirlo, etc (Es como darle un ID)
	@Order(1)
	@Test
	void excepcionMonedaNulaClaseAnonima() {
		Executable accion = new Executable() {
			// @Override
			public void execute() throws ExcepcionMonedaNula {
				new Dinero(10, null);
			}
		};
		assertThrows(ExcepcionMonedaNula.class, accion);
	}

	@Test
	void excepcionMonedaNulaClaseLambda1() {
		Executable accion = () -> new Dinero(10, null);
		ExcepcionMonedaNula monNula = assertThrows(ExcepcionMonedaNula.class, accion);
		System.out.println("Lambda:" + monNula.getMessage());

	}

}
