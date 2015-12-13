package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.entregable;

import org.junit.Assert;
import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.ArbolFileSources;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.UtilidadesGenerales;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.sql.ArbolesSQLEjecutador;

/**
 * la idea de esto es correr los parametros conocidos con los arboles con todos
 * los chiches luego de esto ver que variables salen para esos arboles
 * 
 * paso 1 levantar data set, paso 2 correr arboles
 * 
 * @author julio
 *
 */
public class ArbolesHistoria {

	/**
	 * no me corrio desde java, tuve que cambiarlo para que tire syntax y luego
	 * correrlo a manopla desde spss cuando lo deje corriendo no corrieron todos
	 * sino solo un cacho de todos los quetire a correr
	 */
	@Test
	public void probandoParametrosDevuelta() {

		String outFolder = ArbolFileSources.userFolder + "/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/historia/";
		try {

			// String[] seed = { "101723", "208403", "663552", "826668",
			// "980641" };
			// Integer[] maximaProfundidadList = { 6,7 };
			// Integer[] minParentSizeList = {300, 400, 500 };
			// // cuenta que va a ser el porcentaje variable del current padre
			// Integer[] minChildSizeList = { 65,70,90 };

			// String[] seed = { "101723", "208403", "663552", "826668",
			// "980641" };
			// Integer[] maximaProfundidadList = { 6,7 };
			// Integer[] minParentSizeList = {300, 400 };
			// // cuenta que va a ser el porcentaje variable del current padre
			// Integer[] minChildSizeList = { 65,70 };

			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
			Integer[] maximaProfundidadList = { 7 };
			Integer[] minParentSizeList = { 300, 350, 400 };
			// cuenta que va a ser el porcentaje variable del current padre
			Integer[] minChildSizeList = { 65, 70 };

			// para cada semilla correr los arboles

			String resultSyntax = ArbolesSQLEjecutador.generaSyntaxSpssIterativoVarsYHisto(outFolder, maximaProfundidadList, minParentSizeList, minChildSizeList, seed);
			String timeStampFolder = UtilidadesGenerales.getTimeStamp(null, null);
			UtilidadesGenerales.writeToFile(resultSyntax, "UTF-8", outFolder + timeStampFolder + "_syntax.txt");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * de los que tire a correr tengo las probabilidades pero no las ganancias,
	 * tengo que averiguar las ganancias.
	 */
	@Test
	public void armarGananciasDeLosResultantes() {
		/*
		 * me salieron 24 arboles, que me interesan los campos 
		 * NodeID_24 : para saber que hoja del arbol fue 
		 * PredictedProbability_2_24 : para saber que probabilidad de baja mas 2 fue 
		 * SampleAssignment_24 : para saber si fue tomado para test o train tengoq ue correr el script siguient para persistirlo en la DB
		 * 
		 * los nombres de las variables van como anteriorlmente , el inicial no
		 * tiene subindice, pero se lo cambi oy le pongo 0 cosa de que arranque
		 * en 0
IF  (A_default_prob   >= 0.025 AND clase_int=2) A_default_ganancia=7800. 
EXECUTE.  
RECODE A_default_ganancia (SYSMIS=-200). 
EXECUTE.

SAVE TRANSLATE /TYPE=ODBC 
  /CONNECT='DSN=dmkd-dmf;' 
  /ENCRYPTED 
  /MISSING=RECODE 
  /SQL='CREATE TABLE historico_201503_default_tree_result (numero_de_cliente double , foto_mes double , A_default_node double , A_default_prob double, A_default_ganancia double )' 
  /REPLACE 
  /TABLE='SPSS_TEMP' 
  /KEEP=numero_de_cliente, foto_mes, A_default_node, A_default_prob ,A_default_ganancia
  /SQL='INSERT INTO historico_201503_default_tree_result (numero_de_cliente, foto_mes, A_default_node, A_default_prob,A_default_ganancia) SELECT numero_de_cliente, foto_mes, A_default_node, A_default_prob,A_default_ganancia FROM SPSS_TEMP' 
  /SQL='DROP TABLE SPSS_TEMP'.  
EXECUTE.
DATASET CLOSE.
EXECUTE.


PredictedProbability_2_24
		 * 
		 */
		// de 0 a 24
		String separator = System.getProperty("line.separator");
		String outFolder = ArbolFileSources.userFolder + "/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/historia/";
		String timeStampFolder = UtilidadesGenerales.getTimeStamp(null, null);
		StringBuilder build = new StringBuilder();
		for (int i = 0; i < 25; i++) {
			
			String nombreProba = "PredictedProbability_2_"+i;
			String nombreGanancia= "ganancia_"+i;
			String nombreNodo= "NodeID_"+i;
			String nombreTrain= "SampleAssignment_"+i;
			String nombreTabla = "corridaCiega_"+i;
			
			/* recode de ganancia */
			build.append("IF  (PredictedProbability_2_").append(i).append("   >= 0.025 AND clase_int=2 ) ganancia_").append(i).append("=7800.").append(separator);
			build.append("EXECUTE.").append(separator);
			build.append("RECODE ganancia_").append(i).append("(SYSMIS=-200).").append(separator);
			build.append("EXECUTE.").append(separator);
			
			 
			/* save sql */
			build.append("SAVE TRANSLATE /TYPE=ODBC").append(separator);
			build.append("/CONNECT='DSN=dmkd-dmf;'").append(separator);
			build.append("/ENCRYPTED ").append(separator);
			build.append("/MISSING=RECODE ").append(separator);
			build.append("/SQL='CREATE TABLE ").append(nombreTabla).append(" (numero_de_cliente double , foto_mes double , '+").append(separator);
			
			build.append("' ").append(nombreProba).append(" double , '+").append(separator);
			build.append("' ").append(nombreGanancia).append(" double , '+").append(separator);
			build.append("' ").append(nombreNodo).append(" double , '+").append(separator);
			build.append("' ").append(nombreTrain).append(" double ) '").append(separator);
			
 
			build.append("/REPLACE ").append(separator);
			build.append("/TABLE='SPSS_TEMP' ").append(separator);
			build.append("/KEEP=numero_de_cliente, foto_mes, ").append(separator);
			build.append(nombreProba).append(" , ").append(separator);
			build.append(nombreGanancia).append(" , ").append(separator);
			build.append(nombreNodo).append(" , ").append(separator);
			build.append(nombreTrain).append(" ").append(separator);
			
			build.append("/SQL='INSERT INTO ").append(nombreTabla).append(" (numero_de_cliente, foto_mes, '+").append(separator);
			build.append("' ").append(nombreProba).append(" , '+").append(separator);
			build.append("' ").append(nombreGanancia).append(" , '+").append(separator);
			build.append("' ").append(nombreNodo).append(" , '+").append(separator);
			build.append("' ").append(nombreTrain).append(" '+").append(separator);
			build.append("' ) SELECT numero_de_cliente, foto_mes, '+").append(separator);
			build.append("' ").append(nombreProba).append(" , '+").append(separator);
			build.append("' ").append(nombreGanancia).append(" , '+").append(separator);
			build.append("' ").append(nombreNodo).append(" , '+").append(separator);
			build.append("' ").append(nombreTrain).append(" '+").append(separator);
			build.append("' FROM SPSS_TEMP'").append(separator);
			
			build.append("/SQL='DROP TABLE SPSS_TEMP'.").append(separator);  
			build.append("EXECUTE.").append(separator).append(separator);
			
			
		}
		UtilidadesGenerales.writeToFile(build.toString(), "UTF-8", outFolder + timeStampFolder + "_syntax_corridaCiega_.txt");
	}
	
	@Test
	public void armarSqlDeGananciasParaTabla() {
		
		for (int i = 1; i < 25; i++) {
			paraLasCorridasCiegas(i);	
		}
		
		
	}

	/**
	 * @param indiceCorrida
	 */
	private void paraLasCorridasCiegas(int indiceCorrida) {
		String varLineaDefault = "3136800";
		String varGanancia = "ganancia_"+indiceCorrida;
		String varTabla = "corridaCiega_"+indiceCorrida;
		String varProba="PredictedProbability_2_"+indiceCorrida;
		String varTrain="SampleAssignment_"+indiceCorrida;
		armarSqlGananciaTablasTrain(varLineaDefault, varGanancia, varTabla, varProba, varTrain,indiceCorrida);
	}

	/**
	 * @param varLineaDefault
	 * @param varGanancia
	 * @param varTabla
	 * @param varProba
	 * @param varTrain
	 */
	private void armarSqlGananciaTablas(String varLineaDefault, String varGanancia, String varTabla, String varProba, String varTrain) {
		String separator = System.getProperty("line.separator");
		StringBuilder build = new StringBuilder();		
		build.append("/*  sin train */").append(separator);
		build.append("select count(*), sum(").append(varGanancia).append(" ), (sum(").append(varGanancia).append(" )- ").append(varLineaDefault).append(") as defa, (sum(").append(varGanancia).append(" )- ").append(varLineaDefault).append("*1.3) as muerte from ");
		build.append(varTabla).append("  where ").append(varProba).append(" >=0.025;").append(separator).append(separator);
		build.append("/*  con train */").append(separator);
		build.append("select count(*), sum(").append(varGanancia).append(" ), sum(").append(varGanancia).append(" )/0.3 as normalizado, (sum(").append(varGanancia).append(" )- ").append(varLineaDefault).append(") as defa, (sum(").append(varGanancia).append(" )- ").append(varLineaDefault).append("*1.3) as muerte from ");
		build.append(varTabla).append("  where ").append(varProba).append(" >=0.025 AND ").append(varTrain).append("=0;").append(separator);
//		build.append("select count(*), sum(avg_ganancia ), (sum(avg_ganancia )- 3136800) as defa, (sum(avg_ganancia )- 3136800*1.3) as muerte from ah201504pr7p300c195_20151127_003455  where avg_probs >=0.025;").append(separator);
		System.out.println(build.toString());
	}
	
	private void armarSqlGananciaTablasTrain(String varLineaDefault, String varGanancia, String varTabla, String varProba, String varTrain,int i) {
		String separator = System.getProperty("line.separator");
		StringBuilder build = new StringBuilder();		
		build.append("/*  con train */").append(separator);
		build.append("select 'ciega").append(i).append("' as corrida,count(*), sum(").append(varGanancia).append(" ), sum(").append(varGanancia).append(" )/0.3 as normalizado, (sum(").append(varGanancia).append(" )- ").append(varLineaDefault).append(") as defa, (sum(").append(varGanancia).append(" )- ").append(varLineaDefault).append("*1.3) as muerte from ");
		build.append(varTabla).append("  where ").append(varProba).append(" >=0.025 AND ").append(varTrain).append("=0;").append(separator);
//		build.append("select count(*), sum(avg_ganancia ), (sum(avg_ganancia )- 3136800) as defa, (sum(avg_ganancia )- 3136800*1.3) as muerte from ah201504pr7p300c195_20151127_003455  where avg_probs >=0.025;").append(separator);
		System.out.println(build.toString());
	}
	
}
