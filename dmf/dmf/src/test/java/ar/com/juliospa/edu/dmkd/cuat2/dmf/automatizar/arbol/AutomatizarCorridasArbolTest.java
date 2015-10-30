package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbol;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulio;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioResultado;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.model.NodoResultadoTablaNormalizada;

public class AutomatizarCorridasArbolTest {

	@Test
	public void testPruebaEjecucionSpss() {
		AutomatizarCorridasArbol.pruebaEjecucionSpss();
		
	}
	
	/**
	 * ACA CONFIGURAR EL TIMESTAMP ! SINO REVIENTA
	 */
	@Test
	public void pruebaParseResultadoDefaultSinEjecucion() {
		final String timeStampFolder = "20151028_103415";
		
		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/";
		String origenDatos = "C:/Users/julio/Desktop/dmf_wd/nulos/DataSetAbril2015.sav";

		Long ganancia = 8000L;
		Long costo = 200L;
		double normalizador = 1;
		
		// la corrida default no tiene mucha configuracion
		AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
		configArbol.setOrigenDatosSav(origenDatos);
		configArbol.setOutputFolder(outFolder);
		configArbol.setGanancia(ganancia);
		configArbol.setCosto(costo);
		configArbol.setNormalizador(normalizador);
		
		// para probar lo de los valores le seteo la nueva seed
		
//		como ya fue ejecutadlo lo levanto de ahi
		
		configArbol.setTimeStampFolder(timeStampFolder);
	
		AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.parseResultadoDefault(configArbol);
		
		System.out.println(result.persistime());

	}
	
	@Test
	public void pruebaParseResultadoDefaultConEjecucion() {

		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/";
		String origenDatos = "C:/Users/julio/Desktop/dmf_wd/nulos/DataSetAbril2015.sav";

		// la corrida default no tiene mucha configuracion
		AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
		configArbol.setOrigenDatosSav(origenDatos);
		configArbol.setOutputFolder(outFolder);
		// para probar lo de los valores le seteo la nueva seed
		configArbol.setSeed("12345");
		
		AutomatizarCorridasArbolJulio.ejecucionArbolDefault(configArbol);
		
		
		
//		como ya fue ejecutadlo lo levanto de ahi
//		configArbol.setTimeStampFolder("20151028_013006");
		System.out.println(NodoResultadoTablaNormalizada.nodoCsvNombreColumnas());;		
		AutomatizarCorridasArbolJulio.parseResultadoDefault(configArbol);
		

		

	}
//	
//	@Test
//	public void pruebaEjecucionSpssArbolVersion2() {
//		AutomatizarCorridasArbol.pruebaEjecucionSpssArbolVersion2();
//	}
	
	@Test
	public void pruebaEjecucionSpssArbolVersionJulioSinEjecucionTrainTest() {
		
//		configuracion corrida
		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/";
		String origenDatos = "C:/Users/julio/Desktop/dmf_wd/nulos/DataSetAbril2015.sav";
		String seed= "12345";
		String tipoArbol = "CHAID";
		Integer maximaProfundidad = 6;
		Integer minParentSize = 350;
		Integer minChildSize = 245;
		Long ganancia = 8000L;
		Long costo = 200L;
		// por el 30%
		double normalizador = 0.3;
		
		AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
		configArbol.setSeed(seed);
		configArbol.setOrigenDatosSav(origenDatos);
		configArbol.setTipoArbol(tipoArbol);
		configArbol.setOutputFolder(outFolder);
		configArbol.setMaximaProfundidad(maximaProfundidad);
		configArbol.setMinChildSize(minChildSize);
		configArbol.setMinParentSize(minParentSize);
		configArbol.setGanancia(ganancia);
		configArbol.setCosto(costo);
		configArbol.setNormalizador(normalizador);
		
		configArbol.setTimeStampFolder("20151017_125755");
		
		AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.parseResultadoTrainTest(configArbol);
		
		System.out.println(result.persistime());
		
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
