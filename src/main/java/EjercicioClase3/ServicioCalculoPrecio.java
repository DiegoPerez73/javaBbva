package EjercicioClase3;

public class ServicioCalculoPrecio {

	private CatalogoProductos catalogoProductos;

	private ServicioClientes servicioClientes;

	private ServicioDescuentos servicioDescuentos;

	public ServicioCalculoPrecio(CatalogoProductos catalogoProductos, ServicioClientes servicioClientes,
			ServicioDescuentos servicioDescuentos) {
		this.catalogoProductos = catalogoProductos;
		this.servicioClientes = servicioClientes;
		this.servicioDescuentos = servicioDescuentos;
	}

	public double calcularPrecio(long idProducto, long idCliente) throws Exception {

		if (idProducto == catalogoProductos.getIdProducto()) {
			double res = servicioClientes.getIdCliente() == idCliente ? catalogoProductos.getPrecioBase() - 
					(catalogoProductos.getPrecioBase() * (servicioDescuentos.getDescuento()
					+ servicioClientes.getDescuentoCliente())) 
					: 
					catalogoProductos.getPrecioBase() - 
					(catalogoProductos.getPrecioBase() * servicioClientes.getDescuentoCliente());
			
			return res;
		} else {
			throw new NullPointerException();
		}
	}

}
