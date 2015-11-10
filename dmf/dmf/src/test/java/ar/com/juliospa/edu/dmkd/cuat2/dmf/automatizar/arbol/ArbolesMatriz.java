package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import org.junit.Assert;
import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AcumuladorComandosSpss;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulio;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioExplorandoParametrosBase;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioResultado;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolSinVarParaPaq1;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.ParseNodosType;

/**
 * la idea de este es correr los arboles para la matriz
 * 1: correr todos los arboles para tener el modelo
 * 2: una vez que tengo todos los modelos probar 1 modelo desde spss para ver la sintaxis
 * 3: una vez que tengo la sintaxis, ejecutar la sintaxis de aplicacion de 1 modelo para los diferentes arboles y ver el output a ver si puedo obtener el resultado del tree
 * 4: puedo correrlo sobre todo el dataset no hace falta 70-30
 * @author julio
 *
 */
public class ArbolesMatriz {

	
	/**
	 * DEFAULT ! de meses
	 */
	@Test
	public void pruebaParseResultadoDefaultConDatasetInicial() {
		
		Long ganancia = 8000L;
		Long costo = 200L;
		double normalizador = 1;
		
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_tarj_histo_default/";
		String origenDatos1 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201411_TARJETAS_HISTO_AVG;
		String origenDatos2 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201412_TARJETAS_HISTO_AVG;
		String origenDatos3 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201501_TARJETAS_HISTO_AVG;
		String origenDatos4 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201502_TARJETAS_HISTO_AVG;
		String origenDatos5 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201503_TARJETAS_HISTO_AVG;
		String origenDatos6 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201504_TARJETAS_HISTO_AVG;

		String[] files = { origenDatos1, origenDatos2, origenDatos3, origenDatos4, origenDatos5, origenDatos6 };
		for (String origenDatos : files) {
			try {
				AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
				configArbol.setOrigenDatosSav(origenDatos);
				configArbol.setOutputFolder(outFolder);
				// para probar lo de los valores le seteo la nueva seed
//			configArbol.setSeed("12345");
				configArbol.setGanancia(ganancia);
				configArbol.setCosto(costo);
				configArbol.setNormalizador(normalizador);
				
				configArbol.setMinParentSize(100);
				configArbol.setMinChildSize(50);
				
				AutomatizarCorridasArbolJulio.ejecucionArbol(configArbol,AcumuladorComandosSpss.comandoArbolDefaultFixDelRawDeBase(configArbol),ParseNodosType.DEFAULT);
				
				AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.parseResultadoDefault(configArbol);
				System.out.println(result.persistime());
			} catch (Exception e) {
				Assert.fail(e.getMessage());
				e.printStackTrace();
			}
		}
		

		// la corrida default no tiene mucha configuracion


	}
	
	@Test
	public void correrArbolesDiagonalPrincipal() {
		
//		Integer[] maximaProfundidadList = { 6 };
//		Integer[] minParentSizeList = { 500 };
//		Integer[] minChildSizeList = { 90 };
		
		Integer[] maximaProfundidadList = { 7 };
		Integer[] minParentSizeList = { 350 };
		Integer[] minChildSizeList = { 70 };
		
		String[] seed = { "101723", "208403", "391661", "663552", "826668", "980641" };
		
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_tarj_histo_v4/";
		String origenDatos1 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201411_TARJETAS_HISTO_AVG;
		String origenDatos2 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201412_TARJETAS_HISTO_AVG;
		String origenDatos3 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201501_TARJETAS_HISTO_AVG;
		String origenDatos4 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201502_TARJETAS_HISTO_AVG;
		String origenDatos5 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201503_TARJETAS_HISTO_AVG;
		String origenDatos6 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201504_TARJETAS_HISTO_AVG;
//		 origenDatos1, origenDatos2, origenDatos3, origenDatos4, origenDatos5,origenDatos6
		String[] files = { origenDatos1, origenDatos2, origenDatos3,origenDatos4, origenDatos5,origenDatos6 };
		ArbolFileSources.complexCalc(maximaProfundidadList, minParentSizeList, minChildSizeList, seed,files);
		AutomatizarCorridasArbolSinVarParaPaq1.ejecucionSpssIterativoTarjetaHistoAVG(outFolder, maximaProfundidadList,minParentSizeList,minChildSizeList,seed,files);
		
	}
	
	@Test
	public void normalizarSalidaDiagonal() {
//		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_tarj_histo_v3/expName_pr6_par500_child450-6-periodos";
//		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_tarj_histo_default";
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/corrida_var_tarj_histo_v4/expName_pr7_par350_child245/";
		AutomatizarCorridasArbolJulioExplorandoParametrosBase.analizarResultadosDeMultiSeed(outFolder);
	}
	
	
	
	@Test
	public void calculaCOmpl() {
		Integer[] maximaProfundidadList = { 6 };
		Integer[] minParentSizeList = { 500 };
		Integer[] minChildSizeList = { 90 };
		
		String[] seed = { "101723", "208403", "391661", "663552", "826668", "980641" };
		
		String origenDatos1 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201411_TARJETAS_HISTO_AVG;
		String origenDatos2 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201412_TARJETAS_HISTO_AVG;
		String origenDatos3 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201501_TARJETAS_HISTO_AVG;
		String origenDatos4 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201502_TARJETAS_HISTO_AVG;
		String origenDatos5 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201503_TARJETAS_HISTO_AVG;
		String origenDatos6 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201504_TARJETAS_HISTO_AVG;

		String[] files = { origenDatos1, origenDatos2, origenDatos3, origenDatos4, origenDatos5, origenDatos6 };
		ArbolFileSources.complexCalc(maximaProfundidadList, minParentSizeList, minChildSizeList, seed,files);
	}
	
	
}
