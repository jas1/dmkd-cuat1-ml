package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AcumuladorComandosSpss;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulio;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioExplorandoParametrosBase;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioResultado;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolSinVarParaPaq1;

/**
 * corrida arbol base
 * corrida arbol base sin parametros sobrantes
 * corrida arbol base 
 * 
 * 
 * @author julio
 *
 */
public class PruebasArbol20151107 {

	@Test
	public void testPruebaBase() {
		
//		String userFolder = "C:/Users/jspairani";
		
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/prueba/";
		String origenDatos = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/"+ArbolFileSources.DM_FINANZAS_201504_CON_BOOLEANS_DEPURADO_VAR_PAQUETE_SAV;
		String seed= "101723";
		String tipoArbol = "CHAID";
		Integer maximaProfundidad = 6;
		Integer minParentSize = 500;
		Integer minChildSize = 325;
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
		// probar si funciona y si funciona probar agregando parametrizacion de columnas
		String[] commands= AcumuladorComandosSpss.comandoArbolBase20151107(configArbol);
		
		AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.ejecucionArbolVersionParametrizadoArbol(configArbol,commands);
		
		System.out.println(result.persistime());
	}
	@Test
	public void testPruebaBaseSinEjecucion() {
		
//		String userFolder = "C:/Users/jspairani";

		
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/prueba/";
		String origenDatos = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/"+ArbolFileSources.DM_FINANZAS_201504_CON_BOOLEANS_DEPURADO_VAR_PAQUETE_SAV;
		String seed= "101723";
		String tipoArbol = "CHAID";
		Integer maximaProfundidad = 6;
		Integer minParentSize = 500;
		Integer minChildSize = 325;
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
		// probar si funciona y si funciona probar agregando parametrizacion de columnas
		String[] commands= AcumuladorComandosSpss.comandoArbolBase20151107(configArbol);
		
		configArbol.setTimeStampFolder("20151107_180728-rev");

		AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.parseResultadoTrainTest(configArbol);
		
		System.out.println(result.persistime());
		
	}
	

	
	@Test
	public void testPruebaTarjetas() {
		
//		String userFolder = "C:/Users/jspairani";
		
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/prueba/";
		String origenDatos = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/"+ArbolFileSources.DM_FINANZAS_201504_TARJETAS;
		String seed= "101723";
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
		// probar si funciona y si funciona probar agregando parametrizacion de columnas
		String[] commands= AcumuladorComandosSpss.comandoArbolTarjetas20151107(configArbol);
		
		AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.ejecucionArbolVersionParametrizadoArbol(configArbol,commands);
		
		System.out.println(result.persistime());
	}
	
	@Test
	public void testPruebaEjecucionSpss() {
		
//		String outFolder = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_p1_v3/";
//		String origenDatos = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/"+ArbolFileSources.DM_FINANZAS_201504_CON_BOOLEANS_DEPURADO_VAR_PAQUETE_SAV;
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_p1_v6/";
		String origenDatos = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/"+ArbolFileSources.DM_FINANZAS_201504_CON_BOOLEANS_DEPURADO_VAR_PAQUETE_SAV;

//		estaria bueno que este metodo tire un resumen de todo
//		por ahora solo genera todas las cosas, despues creo un metodo para parsear eso.
		AutomatizarCorridasArbolSinVarParaPaq1.ejecucionSpss(outFolder, origenDatos);
		
		AutomatizarCorridasArbolJulioExplorandoParametrosBase.analizarResultadosDeMultiSeed(outFolder);
	}
	
	@Test
	public void testPruebaEjecucionSpssTarjetas() {
		
//		String outFolder = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_p1_v3/";
//		String origenDatos = "C:/Users/jspairani/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/"+ArbolFileSources.DM_FINANZAS_201504_CON_BOOLEANS_DEPURADO_VAR_PAQUETE_SAV;
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_p1_v8/";
		String origenDatos = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/"+ArbolFileSources.DM_FINANZAS_201504_TARJETAS;

//		RECORDAR COMPLEJIDAD! ! ! 

//		Integer[] maximaProfundidadList = { 6, 7, 8 };
//		Integer[] minParentSizeList = { 350, 400, 450, 500 };
//		Integer[] minChildSizeList = { 68, 70, 72 };
		
		Integer[] maximaProfundidadList = { 5,6,7 };
		Integer[] minParentSizeList = { 350,500 };
		Integer[] minChildSizeList = {70,90 };
		
		String[] seed = { "101723", "208403", "391661", "663552", "826668", "980641" };
		
		ArbolFileSources.complexCalc(maximaProfundidadList, minParentSizeList, minChildSizeList, seed);
		
//		estaria bueno que este metodo tire un resumen de todo
//		por ahora solo genera todas las cosas, despues creo un metodo para parsear eso.
		AutomatizarCorridasArbolSinVarParaPaq1.ejecucionSpssIterativo(outFolder, origenDatos,maximaProfundidadList,minParentSizeList,minChildSizeList,seed);
		
		AutomatizarCorridasArbolJulioExplorandoParametrosBase.analizarResultadosDeMultiSeed(outFolder);
		
		
	}
	@Test
	public void analizarTarjetasTotal() {
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_p1_v8/";
		System.out.println(AutomatizarCorridasArbolJulioExplorandoParametrosBase.analizarResultadosDeMultiSeedAgrupados(outFolder));
	} 

	@Test
	public void cuentaComplex() {
		Integer[] maximaProfundidadList = { 6 };
		Integer[] minParentSizeList = { 350, 375 };
		Integer[] minChildSizeList = { 68, 70, 72 };
		
		String[] seed = { "101723", "208403", "391661", "663552", "826668", "980641" };
		
		ArbolFileSources.complexCalc(maximaProfundidadList, minParentSizeList, minChildSizeList, seed);
	}
	@Test
	public void testPruebaTarjetasHistoAVG() {
		
//		String userFolder = "C:/Users/jspairani";
		
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/prueba/";
		String origenDatos = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/"+ArbolFileSources.DM_FINANZAS_201411_TARJETAS_HISTO_AVG;
		String seed= "101723";
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
		// probar si funciona y si funciona probar agregando parametrizacion de columnas
		String[] commands= AcumuladorComandosSpss.comandoArbolTarjetas20151107(configArbol);
		
		AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.ejecucionArbolVersionParametrizadoArbol(configArbol,commands);
		
		System.out.println(result.persistime());
	}
	
	@Test
	public void testGenerarArchivosTarjetasHistoAVG() {
		
//		String userFolder = "C:/Users/jspairani";
		
		
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/";
		String origenDatos = "C:/compartido/dm_finanzas_REMP_con_avg_histo_tarj.txt";
		
		String[] datosValues = new String[]{"201411","201412","201501","201502","201503","201504"};
		for (String currentRempl : datosValues) {
			String[] commands= AcumuladorComandosSpss.comandoGenerarArchivosTarjetaHistoriaAVG(outFolder, origenDatos.replace("REMP", currentRempl));
			AutomatizarCorridasArbolJulio.ejecutarComandosSpss(commands);	
		}
		
		System.out.println(outFolder);

	}

//	C:\compartido\dm_finanzas_201412_con_avg_histo_tarj.sav
//	C:\compartido\dm_finanzas_201412_con_avg_histo_tarj.txt
	
}
