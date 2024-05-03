package ejemplos;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ANY;

import java.util.EnumSet;


class DiaTest {	
	//@EnumSource(value = Dia.class, names = ".+er.+", mode = EnumSource.Mode.MATCH_ANY)
	//@EnumSource(value = Dia.class, names = "M.+", mode = EnumSource.Mode.MATCH_ANY)
	//@EnumSource(value = Dia.class, names = "M.+", mode = MATCH_ANY)
	//@EnumSource(value = Dia.class, names = {"M.+", ".+ves"}, mode = MATCH_ANY)
	//@EnumSource(value=Dia.class, names={"Lunes"}, mode = EnumSource.Mode.EXCLUDE)

	@ParameterizedTest
	@EnumSource(value = Dia.class, names = {"M.+", ".+ves"}, mode = MATCH_ANY)
	void testDeUnaFuncionDeDia(Dia dia) {	
		EnumSet<Dia> dias =
				EnumSet.of(Dia.Miercoles, Dia.Jueves, Dia.Viernes);
		assertTrue(dias.contains(dia));
	}
}
