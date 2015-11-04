package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbol;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulio;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioResultado;
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
	public void testPruebaEjecucionSpss() {
		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_p1/";
		String origenDatos = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/dm_finanzas_201504_con_booleans_depurado-var-paquete1.sav";	
		String experimentoNro = "expDefault";
		Integer maximaProfundidad = 6;
		Integer minParentSize = 350;
		Integer minChildSize = 245;
		tirada(experimentoNro,origenDatos,outFolder+"default/",maximaProfundidad,minParentSize,minChildSize);
		
		experimentoNro = "exp0";
		maximaProfundidad = 10;
		minParentSize = 1000;
		minChildSize = 900;
		tirada(experimentoNro,origenDatos,outFolder+"misparams0/",maximaProfundidad,minParentSize,minChildSize);
		
//		15	500	325

		experimentoNro = "exp1";
		maximaProfundidad = 15;
		minParentSize = 500;
		minChildSize = 325;
		tirada(experimentoNro,origenDatos,outFolder+"misparams1/",maximaProfundidad,minParentSize,minChildSize);
		
//		15	500	325

		experimentoNro = "exp2";
		maximaProfundidad = 5;
		minParentSize = 1000;
		minChildSize = 900;
		tirada(experimentoNro,origenDatos,outFolder+"misparams2/",maximaProfundidad,minParentSize,minChildSize);
		
		experimentoNro = "exp3";
		maximaProfundidad = 6;
		minParentSize = 1000;
		minChildSize = 900;
		tirada(experimentoNro,origenDatos,outFolder+"misparams3/",maximaProfundidad,minParentSize,minChildSize);
		
		experimentoNro = "exp4";
		maximaProfundidad = 6;
		minParentSize = 500;
		minChildSize = 325;
		tirada(experimentoNro,origenDatos,outFolder+"misparams4/",maximaProfundidad,minParentSize,minChildSize);
		
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
	
	private void tirada(String experimentoNro,String origenDatos,String outFolder ,Integer maximaProfundidad,Integer minParentSize,Integer minChildSize ) {
		
//		configuracion corrida
//		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_p1/";
//		String origenDatos = "C:/Users/julio/Desktop/dmf_wd/nulos/DataSetAbril2015.sav";
		
//		5 seeds de primos de 6 digitos 
//		101723
//		208403
//		391661
//		663552 
//		826668 
//		980641 
//		String seed= "12345";
		String[] seed= {"101723",
				"208403",
				"391661",
				"663552", 
				"826668", 
				"980641"} ;
		String tipoArbol = "CHAID";
//		String experimentoNro = "exp1";
//		Integer maximaProfundidad = 6;
//		Integer minParentSize = 350;
//		Integer minChildSize = 245;

//		Experimento1
		for (String currentSeed : seed) {
			AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
			configArbol.setSeed(currentSeed);
			configArbol.setOrigenDatosSav(origenDatos);
			configArbol.setTipoArbol(tipoArbol);
			configArbol.setOutputFolder(outFolder+experimentoNro+"/");
			configArbol.setMaximaProfundidad(maximaProfundidad);
			configArbol.setMinChildSize(minChildSize);
			configArbol.setMinParentSize(minParentSize);
			AutomatizarCorridasArbolJulio.ejecucionArbolVersionParametrizadoArbolNoVarParaPaq1(configArbol);
		}
	}


}
