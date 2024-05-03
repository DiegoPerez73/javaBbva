package ejemplos;

import static org.junit.jupiter.api.Assertions.*;


import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


class CalculadoraParamTest {
	static Calculadora calc;
	@BeforeAll
	static void ini() {
		calc = new Calculadora();
	}

	@Test
	void suma() {
		assertEquals(9, calc.suma(4, 5), "suma(4,5)");
	}
	@ParameterizedTest
	@ValueSource(ints = {0, 3, 5, 15, 60}) //un sólo parámetro
	void ceroNeutro(int op) {
		assertEquals(op, calc.suma(0,op),
				"suma(0," + op +")");
	}
	@ParameterizedTest
	@CsvSource({"1, 2, 3", "4, 5, 9", "5, 7, 12"}) //varios parámetros, lista cuyos elementos separados por comas "...." 
	//son combinaciones de los parámetros en el orden en que aparecen

	void sumaParametrizada(int op1, int op2, int esperado) {
		assertEquals(esperado, calc.suma(op1,op2),
				"suma(" + op1 + ", "+ op2 +")");

	}

	@ParameterizedTest
	@MethodSource("numerosParaSuma")
	void sumaParametrizadaMetodo(int op1, int op2, int esperado) {
		assertEquals(esperado, calc.suma(op1,op2),
				"suma(" + op1 + ", "+ op2 +")");
	}
	private static Stream<Arguments> numerosParaSuma() { return Stream.of(
			Arguments.of(1, 3, 4),
			Arguments.of(5, 7, 12)
			);
	}
	
//	@ParameterizedTest
//	@CsvFileSource(resources = "numerosParaSuma.csv", numLinesToSkip = 1)
//	void sumaParametrizadaArchivo(int op1, int op2, int esperado) {
//		assertEquals(esperado, calc.suma(op1,op2),
//				"suma(" + op1 + ", "+ op2 +")");
//	}	Lo comento porque necesitaria un archivo para importar !
}


