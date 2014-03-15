package testhola;

import static org.junit.Assert.*;

import org.junit.Test;

import holapais.Division;

public class HolaPaisTest {
  
    @Test  
    public void testDivision() {  
        Division division = new Division(10,5);  
        int resultado = division.division();  
        assertTrue(resultado == 2);  
    }  

}
