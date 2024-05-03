package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MonitorTest {

	Monitor monitor;
	Vehiculo v;

	@BeforeEach
	void inicializar(@Mock Vehiculo v) {
		this.v = v;
		monitor = new Monitor(v);
	}

	@Test
	void mockFunciona() {
		// Pruebo que funcione el mock
		assertTrue(v instanceof Vehiculo);
	}

	@Test
	void vehiculoSinCombustible_NecesitaAyuda() {
		// Al usar el beforeEach con un Mock de Vehiculo v, ya puedo utilizarlo en todos
		// los tests.
		monitor.setVehiculo(v);
		boolean resultado = monitor.necesitaAyuda();
		assertTrue(resultado);
	}

	@Test
	void vehiculoConCombustible_NecesitaAyuda() {
		// Devuevle valores por default del mock (Java da por default :
		// - numéricos -> 0,
		// - booleans -> False,
		// - colecciones -> vacias )
		assertEquals(false, v.tieneCombustible());
		assertEquals(0, v.velocidad(), 0);
	}

	@Test
	void expectativasMock() {
	// Le asignas comportamientos con el when, then return. Para probar las distintas posibilidades que podrian darse.
	when(v.tieneCombustible()).thenReturn(true);
	assertEquals(true,v.tieneCombustible());
	}

	//Jugando con los when - thenReturn para testear los escenarios.
	
	@Test
	 void siVehiculoNoTieneCombustible_NecesitaAyuda() {
	 when(v.tieneCombustible()).thenReturn(false);
	 boolean obtenido = monitor.necesitaAyuda();
	 assertTrue(obtenido);
	 }

	@Test
	 void siVehiculoTieneCombustible_NoNecesitaAyuda() {
	 when(v.tieneCombustible()).thenReturn(true);
	 boolean obtenido = monitor.necesitaAyuda();
	 assertFalse(obtenido);
	}
	
	

}