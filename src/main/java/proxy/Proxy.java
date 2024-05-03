package proxy;

public class Proxy {
	private Autorizador autorizador;
	private Servicio servicio;

	public Proxy(Autorizador autorizador, Servicio servicio) {
		this.autorizador = autorizador;
		this.servicio = servicio;
	}

	public void actualizar(Object nuevo, User u) {
		if (autorizador.estaAutorizado(u))
			servicio.actualizar(nuevo);
	}
}