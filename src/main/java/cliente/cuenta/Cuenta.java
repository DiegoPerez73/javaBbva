package cliente.cuenta;

import java.time.LocalDate;

public class Cuenta implements Comparable<Cuenta> {
	protected double saldo;
	private LocalDate fechaApertura;

	public Cuenta(double saldoInicial) throws ExcepcionMontoInvalido {
		validar(saldoInicial);
		saldo = saldoInicial;
		fechaApertura = LocalDate.now();
	}

	public int compareTo(Cuenta o) {
		return Double.valueOf(saldo).compareTo(o.saldo());
	}

	public static <S extends Comparable<S>> 
	S mayor(S a, S b){
		if (a.compareTo(b) > 0) return a;
		else
			return b;
	}

	public double saldo(){	
		return saldo;
	}

	public void  validar(double monto) throws ExcepcionMontoInvalido {
		if (monto <= 0)
			throw new ExcepcionMontoInvalido();		
	}

	public void depositar(double monto) throws ExcepcionMontoInvalido{
		validar(monto);
		saldo+=monto;
	}

	public void extraer(double monto) throws ExcepcionSaldoInsuficiente, ExcepcionMontoInvalido{
		validar(monto);
		if(disponible() < monto)
			throw new ExcepcionSaldoInsuficiente();

		saldo-=monto;
	}

	protected double disponible() {
		return saldo();
	}


	public void transferir(double monto, Cuenta destino) throws ExcepcionSaldoInsuficiente, ExcepcionMontoInvalido {
		extraer(monto); 
		destino.depositar(monto);
	}

	public String toString() {
		return "Cuenta [saldo=" + saldo + ", fechaApertura =" + fechaApertura +"]";
	}
	
	public LocalDate getFechaApertura() {
		return this.fechaApertura;
	}

}


