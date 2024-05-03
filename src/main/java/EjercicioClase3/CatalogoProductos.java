package EjercicioClase3;

public class CatalogoProductos {
	
	private long idProducto;
	private double precioBase;
	

	public CatalogoProductos (long idProducto, int precioBase) {
		this.idProducto = idProducto;
		this.precioBase = precioBase;
	}
	
	public long getIdProducto () {
		return this.idProducto;
	}
	
	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}
	
	public double getPrecioBase () {
		return this.precioBase;
	}
	

}
