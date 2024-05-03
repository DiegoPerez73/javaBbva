package EjercicioClase3;

public class MainApp {

	public static void main(String[] args) {
		
		CatalogoProductos catalogo = new CatalogoProductos(1, 10);
		ServicioClientes servCliente = new ServicioClientes(1, 0.1);
		ServicioDescuentos servDescto = new ServicioDescuentos(0.2);
		ServicioCalculoPrecio calcPrecio = new ServicioCalculoPrecio(catalogo, servCliente, servDescto);
		
		try {
			System.out.println(calcPrecio.calcularPrecio(1, 1));			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
