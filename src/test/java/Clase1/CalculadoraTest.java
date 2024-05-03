package Clase1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculadoraTest {

	Calculadora c = new Calculadora();
	
	@Test
	void suma() {
		assertEquals(9, c.suma(5, 4), "Fue correcto");
	}
	
	
	//varios parámetros, lista cuyos elementos separados por comas "...."

	//Se "variabilizan" por parametro para usarlo en el test
	
	 @ParameterizedTest
	 @CsvSource({"1, 2, 3", "4, 5, 9", "5, 7, 12"}) 	 //@CsvSource (Puede pasar parametros separados por ",")
														//Son combinaciones de los parámetros en el orden en que aparecen
	 
	 	void sumaParametrizada(int op1, int op2, int esperado) {
		  assertEquals(esperado, c.suma(op1,op2),
				  "suma(" + op1 + ", "+ op2 +")");
		  };

	 @ParameterizedTest
	 @ValueSource(ints = {0, 3, 5, 15, 60}) // @ValueSource (solo pasa 1 parametro)
	 	public void ceroNeutro(int op) {
		 	assertEquals(op, c.suma(0,op));
	 	};
	 	
	 	@ParameterizedTest
	 	@NullSource	//Para poder agregar un null en el parametro;
	 	void esNula(String input) {
	 		assertEquals(null,input);
	 	}
	 	
	 	@ParameterizedTest
	 	@EmptySource	//Para agregar un empty en el parámetro.
	 	void esVacía(String input) {
	 		assertEquals("",input);
	 	}
	 	
}
