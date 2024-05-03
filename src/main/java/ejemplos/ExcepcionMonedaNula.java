package ejemplos;

@SuppressWarnings("serial")
public class ExcepcionMonedaNula extends Exception {
	ExcepcionMonedaNula (){
		super("Moneda Nula");
	}
}
