package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbol;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulio;

public class AutomatizarCorridasArbolTest {

	@Test
	public void testPruebaEjecucionSpss() {
		AutomatizarCorridasArbol.pruebaEjecucionSpss();
		
	}
	@Test
	public void pruebaEjecucionSpssArbolVersion2() {
		AutomatizarCorridasArbol.pruebaEjecucionSpssArbolVersion2();
	}
	
	@Test
	public void pruebaEjecucionSpssArbolVersionJulio() {
		AutomatizarCorridasArbolJulio.pruebaEjecucionSpssArbolVersion2();
		
	}

}
