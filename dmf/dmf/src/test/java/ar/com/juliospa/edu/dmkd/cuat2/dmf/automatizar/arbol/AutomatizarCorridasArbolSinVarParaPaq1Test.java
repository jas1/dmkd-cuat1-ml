package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulio;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioResultado;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolSinVarParaPaq1;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.ParseNodosType;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.model.NodoResultadoTablaNormalizada;

/**
 * la idea es probar con el nuevo dataset
 * la idea es probar ciertos valores
 * la idea es repetir 5 veces cad auno con 5 nros primos cada arbol
 * la idea es guardar los valores de las 5 corridas para despues hacer el promedio
 * 
 * 
 * @author julio
 *
 */
public class AutomatizarCorridasArbolSinVarParaPaq1Test {

	@Test
	public void pruebaParseResultadoDefaultConDatasetInicial() {

		String outFolder = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/default/";
		String origenDatos = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/DataSetAbril2015.sav";
		
		Long ganancia = 8000L;
		Long costo = 200L;
		double normalizador = 1;

		// la corrida default no tiene mucha configuracion
		AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
		configArbol.setOrigenDatosSav(origenDatos);
		configArbol.setOutputFolder(outFolder);
		// para probar lo de los valores le seteo la nueva seed
		configArbol.setSeed("12345");
		configArbol.setGanancia(ganancia);
		configArbol.setCosto(costo);
		configArbol.setNormalizador(normalizador);
		
		AutomatizarCorridasArbolJulio.ejecucionArbol(configArbol,AutomatizarCorridasArbolJulio.comandoArbolDefault(configArbol),ParseNodosType.DEFAULT);
		
		AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.parseResultadoDefault(configArbol);
		System.out.println(result.persistime());

	}
	

	@Test
	public void pruebaParseResultadoDefaultConNuevoDataset() {

		String outFolder = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/default/";
		String origenDatos = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/dm_finanzas_201504_con_booleans_depurado-var-paquete.sav";
		
		Long ganancia = 8000L;
		Long costo = 200L;
		double normalizador = 1;

		// la corrida default no tiene mucha configuracion
		AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
		configArbol.setOrigenDatosSav(origenDatos);
		configArbol.setOutputFolder(outFolder);
		// para probar lo de los valores le seteo la nueva seed
		configArbol.setSeed("12345");
		configArbol.setGanancia(ganancia);
		configArbol.setCosto(costo);
		configArbol.setNormalizador(normalizador);
		
		AutomatizarCorridasArbolJulio.ejecucionArbol(configArbol,AutomatizarCorridasArbolJulio.comandoArbolDefault2(configArbol),ParseNodosType.DEFAULT);
		
		AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.parseResultadoDefault(configArbol);
		System.out.println(result.persistime());

	}
	
	@Test
	public void testPruebaEjecucionSpssSarasa() {
		
		
		String outFolder = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/prueba/";
		String origenDatos = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/dm_finanzas_201504_con_booleans_depurado-var-paquete1.sav";
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
		String[] commands= AutomatizarCorridasArbolJulio.comandoArbolVersionParametrizadoArbolNoVarParaPaq1(configArbol);
		
		AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.ejecucionArbolVersionParametrizadoArbolNoVarParaPaq1(configArbol,commands);
		
		System.out.println(result.persistime());
		
	}
	
	
	@Test
	public void testPruebaEjecucionSpss() {
		String outFolder = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_p1_v3/";
		String origenDatos = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/dm_finanzas_201504_con_booleans_depurado-var-paquete.sav";

//		estaria bueno que este metodo tire un resumen de todo
//		por ahora solo genera todas las cosas, despues creo un metodo para parsear eso.
		AutomatizarCorridasArbolSinVarParaPaq1.ejecucionSpss(outFolder, origenDatos);
		
		
		
	}

	

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



}
