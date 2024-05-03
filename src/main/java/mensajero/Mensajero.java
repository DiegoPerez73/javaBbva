package mensajero;

public class Mensajero {
	private Formateador formateador;
	private MailServer mailServer;
	/**
	 * Permite enviar emails a traves de mailServer formateados 
	 * por formateador
	 * @param mailServer el servidor de emails a utilizar
	 * @param formateador usado para dar formato al mensaje
	 */
	public Mensajero(MailServer mailServer, Formateador formateador) {
		if (mailServer == null || formateador == null)
			throw new IllegalArgumentException();
		this.mailServer = mailServer;
		this.formateador = formateador;
	}
	/**
	 * Envía un email a la dirección email del cliente con 
	 * contenido formateado según el template
	 * @param cliente de donde obtener la dirección de email
	 * @param template del mensaje a enviar
	 */
	public void sendMessage(Cliente cliente, Template template) {
		String mensaje =
				formateador.prepararMensaje(template, cliente);
		mailServer.send("email: " + cliente.getEmail(), mensaje);
	}

	public Formateador getFormateador() {
		return formateador;
	}

	public MailServer getMailServer() {
		return mailServer;
	}
}
