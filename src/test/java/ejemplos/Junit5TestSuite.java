package ejemplos;

import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ CalculadoraParamTest.class, DiaTest.class, Dinero1Tests.class })
@ExcludeTags("LeAsignoUnTag")
@IncludeTags("Correcto")	//SOLO va a correr los tests que macheen con el tag descrpito
public class Junit5TestSuite {
	
}
