package servicioCalPrecio;


public interface ServicioClientes {

	public Cliente getCliente(int idCliente) throws ClienteInexistenteException;

}