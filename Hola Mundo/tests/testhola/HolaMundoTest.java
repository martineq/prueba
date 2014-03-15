package testhola;

import static org.junit.Assert.*;

import org.junit.Test;

import holamundo.Suma;
import holamundo.Resta;
import holamundo.Multiplicacion;

public class HolaMundoTest {

	public Suma suma;
	
	@Test
	public void testSuma() {
		suma = new Suma(42,42);
		int resultado = suma.suma();
		assertTrue(resultado == 84);
	}

	@Test
	public void testResta() {
		Resta resta;
		resta = new Resta(7,-3);
        int resultado = resta.resta();  
        assertTrue(resultado == 10);  
    }

	@Test
	public void testMultiplicacion() {
		Multiplicacion multiplicacion;
		multiplicacion = new Multiplicacion(8,4);
        int resultado = multiplicacion.multiplicacion();  
        assertTrue(resultado == 32);  
    }

}
