package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol;

public class AutomatizarCorridasArbolSinVarParaPaq1 {

	
	public static void ejecucionSpss(String outFolder,String origenDatos) {
//		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_p1/";
//		String origenDatos = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/dm_finanzas_201504_con_booleans_depurado-var-paquete1.sav";	
		String experimentoNro = "expDefault";
		Integer maximaProfundidad = 6;
		Integer minParentSize = 350;
		Integer minChildSize = 245;
		tirada(experimentoNro,origenDatos,outFolder,maximaProfundidad,minParentSize,minChildSize);
		
		experimentoNro = "exp0";
		maximaProfundidad = 10;
		minParentSize = 1000;
		minChildSize = 900;
		tirada(experimentoNro,origenDatos,outFolder,maximaProfundidad,minParentSize,minChildSize);
		
//		15	500	325

		experimentoNro = "exp1";
		maximaProfundidad = 15;
		minParentSize = 500;
		minChildSize = 325;
		tirada(experimentoNro,origenDatos,outFolder,maximaProfundidad,minParentSize,minChildSize);
		
//		15	500	325

		experimentoNro = "exp2";
		maximaProfundidad = 5;
		minParentSize = 1000;
		minChildSize = 900;
		tirada(experimentoNro,origenDatos,outFolder,maximaProfundidad,minParentSize,minChildSize);
		
		experimentoNro = "exp3";
		maximaProfundidad = 6;
		minParentSize = 1000;
		minChildSize = 900;
		tirada(experimentoNro,origenDatos,outFolder,maximaProfundidad,minParentSize,minChildSize);
		
		experimentoNro = "exp4";
		maximaProfundidad = 6;
		minParentSize = 500;
		minChildSize = 325;
		tirada(experimentoNro,origenDatos,outFolder,maximaProfundidad,minParentSize,minChildSize);
		
	}
	
	private static void tirada(String experimentoNro,String origenDatos,String outFolder ,Integer maximaProfundidad,Integer minParentSize,Integer minChildSize ) {
		
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
			configArbol.setCosto(200L);
			configArbol.setGanancia(8000L);
			configArbol.setNormalizador(0.3);
			AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.ejecucionArbolVersionParametrizadoArbolNoVarParaPaq1(configArbol);
			System.out.println(result.persistime());
		}
	}
	
}

