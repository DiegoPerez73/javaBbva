package servicioCalPrecio;

public interface CatalogoProductos {

	public Producto getProducto(int codigoProducto) throws ProductoInexistenteException;
	

}
