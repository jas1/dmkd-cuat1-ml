package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.sql;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbol;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioResultado;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.ParseNodosType;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.old.AutomatizarCorridasArbolJulio;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.old.AutomatizarCorridasArbolJulioExplorandoParametrosBase;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.sql.AcumuladorComandosSpss;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.sql.ArbolesSQLEjecutador;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.sql.CamposSQLHelper;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.ArbolFileSources;


/**
 * la idea de este es correr los arboles para la matriz
 * 1: correr todos los arboles para tener el modelo
 * 2: una vez que tengo todos los modelos probar 1 modelo desde spss para ver la sintaxis
 * 3: una vez que tengo la sintaxis, ejecutar la sintaxis de aplicacion de 1 modelo para los diferentes arboles y ver el output a ver si puedo obtener el resultado del tree
 * 4: puedo correrlo sobre todo el dataset no hace falta 70-30
 * @author julio
 *
 */
public class ArbolesSQL {
	@Test
	public void pruebaParseResultadoDefaultConDatasetInicial() {
		
		Long ganancia = 8000L;
		Long costo = 200L;
		double normalizador = 1;
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/sql/default";
		try {
			AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
//				se levanta por jdbc
//				configArbol.setOrigenDatosSav(origenDatos);
			configArbol.setOutputFolder(outFolder);
			// para probar lo de los valores le seteo la nueva seed
//			configArbol.setSeed("12345");
			configArbol.setGanancia(ganancia);
			configArbol.setCosto(costo);
			configArbol.setNormalizador(normalizador);
			
			configArbol.setMinParentSize(100);
			configArbol.setMinChildSize(50);
			
			AutomatizarCorridasArbol.ejecucionArbol(configArbol,AcumuladorComandosSpss.comandoArbolDefaultLevantadoSQL(configArbol),ParseNodosType.DEFAULT);
			
			AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.parseResultadoDefault(configArbol);
			System.out.println(result.persistime());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void probandoParametrosDevuelta() {
		
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/sql/historia/";
		try {
			
//			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
//			Integer[] maximaProfundidadList = { 6,7 };
//			Integer[] minParentSizeList = {300, 400, 500 };
//			// cuenta que va a ser el porcentaje variable del current padre
//			Integer[] minChildSizeList = { 65,70,90 };
			
//			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
//			Integer[] maximaProfundidadList = { 6,7 };
//			Integer[] minParentSizeList = {300, 400 };
//			// cuenta que va a ser el porcentaje variable del current padre
//			Integer[] minChildSizeList = { 65,70 };
			
			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
			Integer[] maximaProfundidadList = { 7};
			Integer[] minParentSizeList = {350 };
			// cuenta que va a ser el porcentaje variable del current padre
			Integer[] minChildSizeList = { 70 };
			
			ArbolesSQLEjecutador.ejecucionSpssIterativo(outFolder, maximaProfundidadList, minParentSizeList, minChildSizeList, seed);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void probandoReducto() {
		
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/sql/historia_redux/";
		try {
			
//			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
//			Integer[] maximaProfundidadList = { 6,7 };
//			Integer[] minParentSizeList = {300, 400, 500 };
//			// cuenta que va a ser el porcentaje variable del current padre
//			Integer[] minChildSizeList = { 65,70,90 };
			
//			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
//			Integer[] maximaProfundidadList = { 6,7 };
//			Integer[] minParentSizeList = {300, 400 };
//			// cuenta que va a ser el porcentaje variable del current padre
//			Integer[] minChildSizeList = { 65,70 };
			
			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
			Integer[] maximaProfundidadList = { 7};
			Integer[] minParentSizeList = {350 };
			// cuenta que va a ser el porcentaje variable del current padre
			Integer[] minChildSizeList = { 70 };
			
			ArbolesSQLEjecutador.ejecucionSpssIterativoReducto(outFolder, maximaProfundidadList, minParentSizeList, minChildSizeList, seed);
			AutomatizarCorridasArbolJulioExplorandoParametrosBase.analizarResultadosDeMultiSeed(outFolder);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void analizarNuevosValores() {
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/sql/historia/";
		AutomatizarCorridasArbolJulioExplorandoParametrosBase.analizarResultadosDeMultiSeed(outFolder);
	} 
	
	@Test
	public void calculaCOmpl() {
		String[] seed = { "101723", "208403", "663552", "826668", "980641" };
		Integer[] maximaProfundidadList = { 6,7 };
		Integer[] minParentSizeList = {350 };
		// cuenta que va a ser el porcentaje variable del current padre
		Integer[] minChildSizeList = { 70 };
		
		String origenDatos1 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201411_TARJETAS_HISTO_AVG;

		String[] files = { origenDatos1};
		int tiempoEstimado = 5;
		ArbolFileSources.complexCalcConTiempo(maximaProfundidadList, minParentSizeList, minChildSizeList, seed,files,tiempoEstimado);
		
	}
	
	@Test
	public void testRandomCols() {
		Map<String, String> tmp = CamposSQLHelper.getCamposAUsarRandom(50);
		System.out.println(CamposSQLHelper.transformToSpssSql(tmp.keySet()));
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(Arrays.asList(CamposSQLHelper.transformToSpssTreeFields(tmp.values())));
		
	}
	
}
