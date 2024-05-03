package Clase1;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AntesDeCadaTests implements BeforeEachCallback {
	
	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		System.out.println("Ejecutado método: " + context.getTestMethod());
	}
}
