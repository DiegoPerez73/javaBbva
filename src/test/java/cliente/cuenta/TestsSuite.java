package cliente.cuenta;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ ClienteTest.class, CuentaTest.class })
public class TestsSuite {

}
