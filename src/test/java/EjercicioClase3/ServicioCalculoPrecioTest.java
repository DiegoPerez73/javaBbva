package EjercicioClase3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import proxy.User;


@ExtendWith(MockitoExtension.class)
public class ServicioCalculoPrecioTest {
	private CatalogoProductos catalogoProductos;
	private ServicioDescuentos servicioDescuentos;
	private ServicioClientes servicioClientes;
	private ServicioCalculoPrecio servicioCalculoPrecio;
	
	@BeforeEach
	void inicializar(@Mock CatalogoProductos catalogoProductos, @Mock ServicioDescuentos servicioDescuentos, 
			@Mock ServicioClientes servicioClientes, @Mock User usuario) {
		this.catalogoProductos = catalogoProductos;
		this.servicioDescuentos = servicioDescuentos;
		this.servicioClientes = servicioClientes;
		servicioCalculoPrecio = new ServicioCalculoPrecio(catalogoProductos, servicioClientes, servicioDescuentos);
	}
	
	// Escenario exitoso 
	@Test
	void aplicaDescuento() throws Exception {
		//CatalogoProductos
		when(catalogoProductos.getIdProducto()).thenReturn(1L);
		when(catalogoProductos.getPrecioBase()).thenReturn(10D);
		//ServicioClientes
		when(servicioClientes.getIdCliente()).thenReturn(1L);
		when(servicioClientes.getDescuentoCliente()).thenReturn(0.1D);
		//ServocopDescuentos
		when(servicioDescuentos.getDescuento()).thenReturn(0.2D);
		//Assert
		assertEquals(7D, servicioCalculoPrecio.calcularPrecio(1L, 1L));
	}
	
	//Escenario arroja error.
	@Test
	void arrojaErrorPorNoMachearIdProducto() throws Exception {
		when(catalogoProductos.getIdProducto()).thenThrow(NullPointerException.class);
		
		assertThrows(NullPointerException.class, ()-> servicioCalculoPrecio.calcularPrecio(1L, 1L));
	}
	
	//Escenario idCliente inexistente, realiza el descuento igual pero solo el descuentoServicio	
	@Test
	void aplicaDescuentoSinDescuentoCliente() throws Exception {
		//CatalogoProductos
		when(catalogoProductos.getIdProducto()).thenReturn(1L);
		when(catalogoProductos.getPrecioBase()).thenReturn(10D);
		//ServicioClientes
		when(servicioClientes.getIdCliente()).thenReturn(1L);
		when(servicioClientes.getDescuentoCliente()).thenReturn(0.1D);
		//Assert
		assertEquals(9D, servicioCalculoPrecio.calcularPrecio(1L, 2L));
	}
	
}
