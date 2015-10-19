package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbol;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulio;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;

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
		
//		configuracion corrida
		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/";
		String origenDatos = "C:/Users/julio/Desktop/dmf_wd/nulos/DataSetAbril2015.sav";
		String seed= "12345";
		String tipoArbol = "CHAID";
		Integer maximaProfundidad = 6;
		Integer minParentSize = 350;
		Integer minChildSize = 245;

		
		AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
		configArbol.setSeed(seed);
		configArbol.setOrigenDatosSav(origenDatos);
		configArbol.setTipoArbol(tipoArbol);
		configArbol.setOutputFolder(outFolder);
		configArbol.setMaximaProfundidad(maximaProfundidad);
		configArbol.setMinChildSize(minChildSize);
		configArbol.setMinParentSize(minParentSize);
		
		AutomatizarCorridasArbolJulio.ejecucionArbolVersionParametrizadoArbolNoChaidNoCampos(configArbol);
		
	}

}
