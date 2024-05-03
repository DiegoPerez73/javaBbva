package mensajero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestEnviarEmail {
	private static String EMAIL_CLIENTE = "cliente@bbva.com.ar";
	private static String MENSAJE = "Estimado cliente";

	@Mock
	private MailServer mailserver;
	private Mensajero mensajero;
	@Mock
	private Formateador formateador;

	@Test
	void alConstruir_SeFijanMailServeryFormateador() {
		mensajero = new Mensajero(mailserver, formateador);
		assertEquals(mailserver, mensajero.getMailServer());
		assertEquals(formateador, mensajero.getFormateador());
	}

	@Test
	void alConstruirConMailserverNull_IllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new Mensajero(null, formateador));
	}

	@Test
	void alConstruirConFormateadorNull_IllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new Mensajero(mailserver, null));
	}

	@Test
	void debeEnviarEmail(@Mock Cliente cliente, @Mock Template template) {
		mensajero = new Mensajero(mailserver, formateador);
		when(cliente.getEmail()).thenReturn(EMAIL_CLIENTE);
		when(formateador.prepararMensaje(template, cliente)).thenReturn(MENSAJE);

		// Act
		mensajero.sendMessage(cliente, template);
		// Verify
		verify(mailserver).send("email: " + EMAIL_CLIENTE, MENSAJE);
	}

	@ParameterizedTest
	@CsvSource("cliente@bbva.com.ar, Estimado cliente")	//Agrego 2 parametros string para utilizar
	void debeEnviarEmailParametrico(String cuenta, String texto, @Mock Cliente cliente, @Mock Template template) {
		// System.out.println(cuenta); -> cuenta="cliente@bbva.com.ar";
		// System.out.println(texto); -> texto= "Estimado cliente";
		mensajero = new Mensajero(mailserver, formateador);
		when(cliente.getEmail()).thenReturn(cuenta);
		when(formateador.prepararMensaje(template, cliente)).thenReturn(texto);

		// Act
		mensajero.sendMessage(cliente, template);
		// Verify
		verify(mailserver).send("email: " + cuenta, texto);
	}
	
	

}
