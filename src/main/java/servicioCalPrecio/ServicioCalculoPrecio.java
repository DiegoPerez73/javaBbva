package servicioCalPrecio;

public class ServicioCalculoPrecio {

	private ServicioDescuentos descuentos;
	private CatalogoProductos catalogo;
	private ServicioClientes clientes;

	public ServicioCalculoPrecio(ServicioDescuentos descuentos, ServicioClientes clientes, CatalogoProductos catalogo) {
		this.descuentos = descuentos;
		this.clientes = clientes;
		this.catalogo = catalogo;
	}

	public double calcularPrecio(int codigoProducto, int idCliente)
			throws ProductoInexistenteException, ClienteInexistenteException {
		Producto producto = catalogo.getProducto(codigoProducto);

		// obtiene datos de usuarios
		double descuento;
		try {
			Cliente cliente = clientes.getCliente(idCliente);
			descuento = cliente.getPorcentajeDescuento();
		} catch (ClienteInexistenteException e) {
			descuento = 0;
		}
		// obtiene precio base producto
		double precioBase = producto.getPrecio();

		// al precioBase le aplica un descuento por producto
		double porcentajeDescuento = descuentos.getDescuentoAplicable(producto) + descuento;

		return Math.max(0, precioBase * (1 - porcentajeDescuento / 100));

	}

}
