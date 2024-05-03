	package proxy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TestProxy {
	private Autorizador autorizador;
	private Servicio servicio;
	private Object objeto; 
	private User usuario;
	private Proxy proxy;

	@BeforeEach
	void inicializar(@Mock Autorizador autorizador, @Mock Servicio servicio, 
			@Mock Object objeto, @Mock User usuario) {
		this.autorizador = autorizador;
		this.servicio = servicio;
		proxy = new Proxy(autorizador, servicio);
		this.objeto = objeto;
		this.usuario = usuario;
	}

	@Test
	void siUsuarioTienePermisos_entoncesActualiza() {
		//Primero "mockeo" la fx estaAutorizado, para que siempre retorne true
		when(autorizador.estaAutorizado(usuario)).thenReturn(true);

		//Act
		//Le ejecuto la fx actualizar a proxy
		proxy.actualizar(objeto, usuario);
		
		//Assert
		//Verifico si efectivamente servicio fue actualizado
		verify(servicio).actualizar(objeto);
	}

	@Test
	void siUsuarioNoTienePermisos_entoncesNoActualiza() {
		when(autorizador.estaAutorizado(usuario)).thenReturn(false);
		//Act
		proxy.actualizar(objeto, usuario);
		//Assert
		verify(servicio,never()).actualizar(objeto);
		verify(servicio,never()).actualizar(any());
	}
	
	//Para testear funciones de tipo void, las ejecutamos a algo y verificamos si se llamo, si existio, etc.
	//En este caso, vamos a probar el metodo void get e indexOf (No los hice yo, pero para entender a que va)
	@Test
	void ejemploVerify(@Mock java.util.List<Integer> lista) {
	lista.get(10);
	lista.indexOf(5);
	verify(lista).get(10); //pasa
	verify(lista).indexOf(5);//pasa
	verify(lista).indexOf(5);//pasa
	// verify(lista).get(0); //falla
	}
	
	@Test
	void ejemploVerifyConOcurrencias(@Mock List<Integer> lista){
	lista.add(1);
	lista.add(2);
	lista.add(2);
	lista.add(3);
	lista.add(3);
	lista.add(3);
	//número exacto de invocaciones
	 verify(lista, times(1)).add(1);
	 verify(lista, times(2)).add(2);
	 verify(lista, times(3)).add(3);
	 //times(1) es el valor por defecto
	 verify(lista).add(1);
	 //never() es un alias de times(0)
	 verify(lista, times(0)).add(10);
	 verify(lista, never()).add(10);
	 //verificación usando atLeast()/atMost()
	 verify(lista, atMostOnce()).add(1);
	 verify(lista, atLeastOnce()).add(2);
	 verify(lista, atLeast(2)).add(3);
	 verify(lista, atMost(5)).add(2);
	}
	
	
	
}
