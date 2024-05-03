package cliente.cuenta;

public class appMain {

	public static void main(String[] args) throws ExcepcionMontoInvalido {

		Cuenta c1 = new Cuenta(20.0);
		Cuenta c2 = new Cuenta(10.0);

		System.out.println(Double.valueOf(c1.saldo));

		System.out.println(c1.compareTo(c2));

	}

}
