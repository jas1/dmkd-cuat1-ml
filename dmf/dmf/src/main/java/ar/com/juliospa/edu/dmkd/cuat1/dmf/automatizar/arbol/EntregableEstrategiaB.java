package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol;

import java.io.File;

public abstract class EntregableEstrategiaB {

	
	/**
	 * GUARRRDA esto guarda un nuevo .sav cada vez
	 * @param columnas
	 * @param origen
	 * @param carpetaOutput
	 * @param nameOutSubfix
	 * @return
	 */
	public static String[] ejecutarArboles(String columnas ,String origen , String carpetaOutput, String nameOutSubfix){
		
		String timestamp = UtilidadesGenerales.getTimeStamp(null, null);
		String fileName = timestamp+"_outputejecucion.html";
		paso0PreRequisitos(carpetaOutput);
		
		StringBuilder build = new StringBuilder(); 
		build.append("OMS").append( UtilidadesGenerales.separator);
		build.append( "/DESTINATION FORMAT=HTML ").append( UtilidadesGenerales.separator);
		build.append("OUTFILE='" + fileName + "'.").append( UtilidadesGenerales.separator);
		/* cargo datos*/
//		solo comentado para que trabaje en el mismo archivo
//		build.append(paso1GetSyntaxForDatasetLoadFromFile(origen)).append( UtilidadesGenerales.separator);
		/* genero modelos al generarlos le pongo nombre a los campos !*/
		build.append(paso2getMakeModels(columnas,carpetaOutput,nameOutSubfix)).append( UtilidadesGenerales.separator);
		/* luego de que tengo todos los modelos con las probabilidades generadas: calculo la ganancia */

		String resultName = origen.replace(".sav",nameOutSubfix+".sav");
		/* guardo todos los output en base en 1 sola tabla todas las corridas para no generar mugre. */
//		build.append("SAVE OUTFILE='").append(resultName).append("'").append( UtilidadesGenerales.separator); 
//		build.append("/COMPRESSED.").append( UtilidadesGenerales.separator); 
		build.append("OMSEND.").append(UtilidadesGenerales.separator);
		String[] result = { build.toString() };
		return result;
	}
	
	/**
	 * que exista la carpeta de output
	 * @param carpetaOutput
	 * si no cumple prerequisitos  
	 */
	public static void paso0PreRequisitos(String carpetaOutput){
//		que exista dataset donde se va a crear ?
		
//		que exista carpeta out
		File carpetaOut = new File(carpetaOutput);
		if (!carpetaOut.exists()) {
			if (!carpetaOut.mkdirs()) {
				throw new RuntimeException("No se pudo crear la carpeta:" + carpetaOut.getAbsolutePath());
			}
		}
	}
	
	// * levantar el dataset de abril
	/**
	 * levanto el dataset con todas las columnas
	 * 
	 * @return
	 */
	public static String paso1GetSyntaxForDatasetLoadFromFile(String origenDatosSav) {
		String load = "GET" + UtilidadesGenerales.separator;
		load += "  FILE='" + origenDatosSav + "'." + UtilidadesGenerales.separator;
		load += "DATASET NAME DataSet1 WINDOW=FRONT." + UtilidadesGenerales.separator+ UtilidadesGenerales.separator;
		return load;
	}

	// parametros 7 300 65
	/**
	 * determino syntax arbol para parametros y ciertas columnas
	 * @param nameOutSubfix 
	 * 
	 * @return
	 */
	public static String getSyntaxForAllTrees(String columnas, String carpetaOutput, String nameOutSubfix) {
		StringBuilder build = new StringBuilder();

//		a esta altura ya existen los directorios o revento
		
		String[] seeds = { "101723", "208403", "663552", "826668", "980641" };
		for (String seed : seeds) {
			build.append(getSyntaxForParams(seed, columnas, carpetaOutput,nameOutSubfix)).append(UtilidadesGenerales.separator);
		}
		return build.toString();
	}

	public static String getSyntaxForParams(String seed, String columnas, String carpetaOutput, String nameOutSubfix) {
		String tipo = "CHAID";
		int deep = 7;
		int parent = 300;
		int childPercent = 65;
		int child = childPercent * parent / 100;

		String nombreModel =generarNombreParams(tipo,deep,parent,childPercent,seed,nameOutSubfix);
		
		String fileXmlTest = carpetaOutput + "/" + nombreModel + "_test_model.xml";
		String fileXmlTrain = carpetaOutput + "/" + nombreModel + "_train_model.xml";

//		String nombreCampos= nombreModel
		
		StringBuilder build = new StringBuilder();
		// esto tiene que venir desde antes
		// build.append(
		// "* Define Variable Properties.").append(UtilidadesGenerales.separator);
		// build.append( "*clase.").append(UtilidadesGenerales.separator);
		// build.append(
		// "VALUE LABELS clase").append(UtilidadesGenerales.separator);
		// build.append(
		// "  'BAJA+1                  ' 'NEGATIVO'").append(UtilidadesGenerales.separator);
		// build.append(
		// "  'BAJA+2                  ' 'POSITIVO'").append(UtilidadesGenerales.separator);
		// build.append(
		// "  'CONTINUA                ' 'NEGATIVO'.").append(UtilidadesGenerales.separator);
		// build.append( "EXECUTE.").append(UtilidadesGenerales.separator);
		build.append("SET SEED=" + seed + ".").append(UtilidadesGenerales.separator);
		build.append("EXECUTE.").append(UtilidadesGenerales.separator);
		build.append("* Decision Tree.").append(UtilidadesGenerales.separator);
		build.append("TREE clase [n] BY ").append(UtilidadesGenerales.separator);
		build.append(columnas).append(UtilidadesGenerales.separator);
		build.append("  /TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO").append(UtilidadesGenerales.separator);
		build.append("  /DEPCATEGORIES USEVALUES=['BAJA+1' 'BAJA+2' 'CONTINUA'] TARGET=['BAJA+2']").append(UtilidadesGenerales.separator);
		build.append("  /PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE").append(UtilidadesGenerales.separator);
		build.append("  /GAIN SUMMARYTABLE=YES CATEGORYTABLE=YES TYPE=[NODE] SORT=DESCENDING CUMULATIVE=NO").append(UtilidadesGenerales.separator);
		build.append("  /SAVE NODEID PREDVAL PREDPROB ASSIGNMENT").append(UtilidadesGenerales.separator);
		build.append("  /METHOD TYPE= ").append(tipo);
		build.append("  /GROWTHLIMIT MAXDEPTH=").append(deep).append(" MINPARENTSIZE=").append(parent).append(" MINCHILDSIZE=").append(child).append(UtilidadesGenerales.separator);
		build.append("  /VALIDATION TYPE=SPLITSAMPLE(70.00) OUTPUT=BOTHSAMPLES").append(UtilidadesGenerales.separator);
		build.append("  /CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10").append(UtilidadesGenerales.separator);
		build.append("  /COSTS CUSTOM= 'BAJA+1' 'BAJA+1' [0] 'BAJA+1' 'BAJA+2' [200] 'BAJA+1' 'CONTINUA' [0]  'BAJA+2' 'BAJA+1' [7800] 'BAJA+2' 'BAJA+2' [0] 'BAJA+2' 'CONTINUA' [7800]  'CONTINUA' 'BAJA+1' [0] 'CONTINUA' 'BAJA+2' [200] 'CONTINUA' 'CONTINUA' [0]").append(UtilidadesGenerales.separator);
		build.append("  /PROFITS CUSTOM='BAJA+1' [0 200] 'BAJA+2' [8000 200] 'CONTINUA' [0 200]").append(UtilidadesGenerales.separator);
		build.append("  /OUTFILE ").append(UtilidadesGenerales.separator);
		build.append(" TRAININGMODEL='").append(fileXmlTrain + "' ").append(UtilidadesGenerales.separator);
		build.append(" TESTMODEL='").append(fileXmlTest + "'").append(UtilidadesGenerales.separator);
		build.append("  /MISSING NOMINALMISSING=MISSING.").append(UtilidadesGenerales.separator);
		build.append("EXECUTE.").append(UtilidadesGenerales.separator);
		/*  ESTO NO ESTA MAL ! es variables a renombrar = nuevos nombres  */
		build.append("RENAME VARIABLES (NodeID PredictedProbability_2 SampleAssignment = NodeID_").append(nombreModel).append(" probab2_").append(nombreModel).append(" esTrain_").append(nombreModel).append(" ).").append(UtilidadesGenerales.separator);
		build.append("DELETE VARIABLES PredictedValue PredictedProbability_1 PredictedProbability_3.").append(UtilidadesGenerales.separator);
		build.append("EXECUTE.").append(UtilidadesGenerales.separator);


		return build.toString();
	}

	private static String generarNombreParams(String tipo, int deep, int parent, int childPercent, String seed, String nameOutSubfix) {
		return tipo+"_"+deep+"_"+parent+"_"+childPercent+"_"+seed+"_"+nameOutSubfix;
	}

	// * generar los modelos chicos con diferenets semillas
	public static String paso2getMakeModels( String columnas, String carpetaOutput, String nameOutSubfix){
//		devuelve
		String models = getSyntaxForAllTrees( columnas, carpetaOutput,nameOutSubfix);
		
		return models;
	}

	// * evaluar los modelos chicos
//	public static String getAssessModels(String carpetaOutput){
//		
//	}

	// * aplicar ensamble de cada semilla
	public abstract String getEnsembleModels();

	// * aplicar ensemble de otros arboles
	public abstract String getEnsembleAllModels();

}
