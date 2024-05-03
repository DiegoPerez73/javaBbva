package ejemplos;

import org.junit.jupiter.api.function.Executable;

public class ExecutableMonedaNula implements Executable {
	@Override
	public void execute() throws ExcepcionMonedaNula {
		new Dinero(10, null);
	}
}
