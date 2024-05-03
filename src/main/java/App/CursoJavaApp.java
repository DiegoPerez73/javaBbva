package App;


import Clase1.Calculadora;

public class CursoJavaApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calculadora calc = new Calculadora();
		
		int res = calc.suma(10,5);
		System.out.println(res);
	
		
		 String[]  miArray = {
				 "hola","mundo"
		 };
		 
		 int[] numeros = {
				 2,3
		 };

		 
		 int[] num = new int[17];
		 
		 for (int i = 0; i <= 16; i++) {
			num[i] = i;
		};
		 
		for (int n : num) {
			n = n + 1;
			System.out.println(n);
		}
	
		System.out.println(miArray[1]);
		 System.out.println(numeros[1]);
	
		 
		 class Empleado  {
			 protected double salarioBasico = 0;
			 void setSalarioBasico(double salarioBasico) {
			 this.salarioBasico = salarioBasico;
			 };
			 
			 public double getSalarioFinal() {
			 return this.salarioBasico * 1.7;
			 };
			 };
			 
			 class Jefe extends Empleado {
			 @Override
			 public double getSalarioFinal() {
			 return this.salarioBasico * 10.0;
			 }
			 }
			 
			 Empleado tito = new Empleado();
			 tito.setSalarioBasico(20.0);
			 System.out.println(tito.getSalarioFinal());
			 
			 Empleado diego = new Jefe();
			 
			 diego.setSalarioBasico(20);
			 System.out.println(diego.getSalarioFinal());
		 
	}
}
