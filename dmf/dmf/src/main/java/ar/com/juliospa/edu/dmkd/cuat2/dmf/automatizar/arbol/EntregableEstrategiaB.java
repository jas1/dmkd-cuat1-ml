package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class EntregableEstrategiaB {

	
	
	
	private static final String AVG_PREFIX = "avg_";
	private static final String TODOS = "todos";

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
		build.append("OMS").append( UtilidadesGenerales.lineSeparator);
		build.append( "/DESTINATION FORMAT=HTML ").append( UtilidadesGenerales.lineSeparator);
		build.append("OUTFILE='" + fileName + "'.").append( UtilidadesGenerales.lineSeparator);
		/* cargo datos*/
//		solo comentado para que trabaje en el mismo archivo
//		build.append(paso1GetSyntaxForDatasetLoadFromFile(origen)).append( UtilidadesGenerales.separator);
		/* genero modelos al generarlos le pongo nombre a los campos !*/
		build.append(paso2getMakeModels(columnas,carpetaOutput,nameOutSubfix)).append( UtilidadesGenerales.lineSeparator);
		/* luego de que tengo todos los modelos con las probabilidades generadas: calculo la ganancia */

		String resultName = origen.replace(".sav",nameOutSubfix+".sav");
		/* guardo todos los output en base en 1 sola tabla todas las corridas para no generar mugre. */
//		build.append("SAVE OUTFILE='").append(resultName).append("'").append( UtilidadesGenerales.separator); 
//		build.append("/COMPRESSED.").append( UtilidadesGenerales.separator); 
		build.append("OMSEND.").append(UtilidadesGenerales.lineSeparator);
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
		String load = "GET" + UtilidadesGenerales.lineSeparator;
		load += "  FILE='" + origenDatosSav + "'." + UtilidadesGenerales.lineSeparator;
		load += "DATASET NAME DataSet1 WINDOW=FRONT." + UtilidadesGenerales.lineSeparator+ UtilidadesGenerales.lineSeparator;
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
			build.append(getSyntaxForParams(seed, columnas, carpetaOutput,nameOutSubfix)).append(UtilidadesGenerales.lineSeparator);
		}
		return build.toString();
	}

	public static String getSyntaxForParams(String seed, String columnas, String carpetaOutput, String nameOutSubfix) {
		String tipo = "CHAID";
		int deep = 6;
		int parent = 400;
		int childPercent = 70;
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
		build.append("SET SEED=" + seed + ".").append(UtilidadesGenerales.lineSeparator);
		build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
		build.append("* Decision Tree.").append(UtilidadesGenerales.lineSeparator);
		build.append("TREE clase [n] BY ").append(UtilidadesGenerales.lineSeparator);
		build.append(columnas).append(UtilidadesGenerales.lineSeparator);
		build.append("  /TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO").append(UtilidadesGenerales.lineSeparator);
		build.append("  /DEPCATEGORIES USEVALUES=['BAJA+1' 'BAJA+2' 'CONTINUA'] TARGET=['BAJA+2']").append(UtilidadesGenerales.lineSeparator);
		build.append("  /PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE").append(UtilidadesGenerales.lineSeparator);
		build.append("  /GAIN SUMMARYTABLE=YES CATEGORYTABLE=YES TYPE=[NODE] SORT=DESCENDING CUMULATIVE=NO").append(UtilidadesGenerales.lineSeparator);
		build.append("  /SAVE NODEID PREDVAL PREDPROB ASSIGNMENT").append(UtilidadesGenerales.lineSeparator);
		build.append("  /METHOD TYPE= ").append(tipo);
		build.append("  /GROWTHLIMIT MAXDEPTH=").append(deep).append(" MINPARENTSIZE=").append(parent).append(" MINCHILDSIZE=").append(child).append(UtilidadesGenerales.lineSeparator);
		build.append("  /VALIDATION TYPE=SPLITSAMPLE(70.00) OUTPUT=BOTHSAMPLES").append(UtilidadesGenerales.lineSeparator);
		build.append("  /CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10").append(UtilidadesGenerales.lineSeparator);
		build.append("  /COSTS CUSTOM= 'BAJA+1' 'BAJA+1' [0] 'BAJA+1' 'BAJA+2' [200] 'BAJA+1' 'CONTINUA' [0]  'BAJA+2' 'BAJA+1' [7800] 'BAJA+2' 'BAJA+2' [0] 'BAJA+2' 'CONTINUA' [7800]  'CONTINUA' 'BAJA+1' [0] 'CONTINUA' 'BAJA+2' [200] 'CONTINUA' 'CONTINUA' [0]").append(UtilidadesGenerales.lineSeparator);
		build.append("  /PROFITS CUSTOM='BAJA+1' [0 200] 'BAJA+2' [8000 200] 'CONTINUA' [0 200]").append(UtilidadesGenerales.lineSeparator);
		build.append("  /OUTFILE ").append(UtilidadesGenerales.lineSeparator);
		build.append(" TRAININGMODEL='").append(fileXmlTrain + "' ").append(UtilidadesGenerales.lineSeparator);
		build.append(" TESTMODEL='").append(fileXmlTest + "'").append(UtilidadesGenerales.lineSeparator);
		build.append("  /MISSING NOMINALMISSING=MISSING.").append(UtilidadesGenerales.lineSeparator);
		build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
		/*  ESTO NO ESTA MAL ! es variables a renombrar = nuevos nombres  */
		build.append("RENAME VARIABLES (NodeID PredictedProbability_2 SampleAssignment = NodeID_").append(nombreModel).append(" probab2_").append(nombreModel).append(" esTrain_").append(nombreModel).append(" ).").append(UtilidadesGenerales.lineSeparator);
		build.append("DELETE VARIABLES PredictedValue PredictedProbability_1 PredictedProbability_3.").append(UtilidadesGenerales.lineSeparator);
		build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);


		return build.toString();
	}
	
	public static String getSyntaxForParamsBKP(String seed, String columnas, String carpetaOutput, String nameOutSubfix) {
		String tipo = "CHAID";
		int deep = 7;
		int parent = 400;
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
		build.append("SET SEED=" + seed + ".").append(UtilidadesGenerales.lineSeparator);
		build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
		build.append("* Decision Tree.").append(UtilidadesGenerales.lineSeparator);
		build.append("TREE clase [n] BY ").append(UtilidadesGenerales.lineSeparator);
		build.append(columnas).append(UtilidadesGenerales.lineSeparator);
		build.append("  /TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO").append(UtilidadesGenerales.lineSeparator);
		build.append("  /DEPCATEGORIES USEVALUES=['BAJA+1' 'BAJA+2' 'CONTINUA'] TARGET=['BAJA+2']").append(UtilidadesGenerales.lineSeparator);
		build.append("  /PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE").append(UtilidadesGenerales.lineSeparator);
		build.append("  /GAIN SUMMARYTABLE=YES CATEGORYTABLE=YES TYPE=[NODE] SORT=DESCENDING CUMULATIVE=NO").append(UtilidadesGenerales.lineSeparator);
		build.append("  /SAVE NODEID PREDVAL PREDPROB ASSIGNMENT").append(UtilidadesGenerales.lineSeparator);
		build.append("  /METHOD TYPE= ").append(tipo);
		build.append("  /GROWTHLIMIT MAXDEPTH=").append(deep).append(" MINPARENTSIZE=").append(parent).append(" MINCHILDSIZE=").append(child).append(UtilidadesGenerales.lineSeparator);
		build.append("  /VALIDATION TYPE=SPLITSAMPLE(70.00) OUTPUT=BOTHSAMPLES").append(UtilidadesGenerales.lineSeparator);
		build.append("  /CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10").append(UtilidadesGenerales.lineSeparator);
		build.append("  /COSTS CUSTOM= 'BAJA+1' 'BAJA+1' [0] 'BAJA+1' 'BAJA+2' [200] 'BAJA+1' 'CONTINUA' [0]  'BAJA+2' 'BAJA+1' [7800] 'BAJA+2' 'BAJA+2' [0] 'BAJA+2' 'CONTINUA' [7800]  'CONTINUA' 'BAJA+1' [0] 'CONTINUA' 'BAJA+2' [200] 'CONTINUA' 'CONTINUA' [0]").append(UtilidadesGenerales.lineSeparator);
		build.append("  /PROFITS CUSTOM='BAJA+1' [0 200] 'BAJA+2' [8000 200] 'CONTINUA' [0 200]").append(UtilidadesGenerales.lineSeparator);
		build.append("  /OUTFILE ").append(UtilidadesGenerales.lineSeparator);
		build.append(" TRAININGMODEL='").append(fileXmlTrain + "' ").append(UtilidadesGenerales.lineSeparator);
		build.append(" TESTMODEL='").append(fileXmlTest + "'").append(UtilidadesGenerales.lineSeparator);
		build.append("  /MISSING NOMINALMISSING=MISSING.").append(UtilidadesGenerales.lineSeparator);
		build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
		/*  ESTO NO ESTA MAL ! es variables a renombrar = nuevos nombres  */
		build.append("RENAME VARIABLES (NodeID PredictedProbability_2 SampleAssignment = NodeID_").append(nombreModel).append(" probab2_").append(nombreModel).append(" esTrain_").append(nombreModel).append(" ).").append(UtilidadesGenerales.lineSeparator);
		build.append("DELETE VARIABLES PredictedValue PredictedProbability_1 PredictedProbability_3.").append(UtilidadesGenerales.lineSeparator);
		build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);


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
	
	/** prefixCols debe venir en orden : prefixColProba, prefixColGanancia, prefixColNodo, prefixColTrain */
	public static String generarGananciasModelosParaCasoPuntualFIXEDNOENSEMBLE(Map<String,Map<String,Map<String,String>>> armaNombres,String[] prefixCols,String probabilidadFiltro, int calculoGananciaGanancia, int calculoGananciaCosto) {
		StringBuilder build = new StringBuilder();
		
//		para cada tipo modelo
		for (Entry<String, Map<String,Map<String,String>>> tipoModelos : armaNombres.entrySet()) {
			
//			para cada modelo
			for (Entry<String, Map<String,String>> modelo : tipoModelos.getValue().entrySet()) {
				
				build.append("IF  (").append(modelo.getValue().get(prefixCols[0]) ).append( "   >= " );
				build.append(UtilidadesGenerales.lineSeparator).append( probabilidadFiltro ).append( " AND clase_int=2 ) " );
				build.append( modelo.getValue().get(prefixCols[1])).append( "=" ).append( (calculoGananciaGanancia - calculoGananciaCosto) ).append( ".").append(UtilidadesGenerales.lineSeparator);
				build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
				build.append("RECODE ").append(modelo.getValue().get(prefixCols[1])).append(" (SYSMIS=-").append(calculoGananciaCosto).append(").").append(UtilidadesGenerales.lineSeparator);
				build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
			}
		}
		return build.toString();
	}
	
	
	/**
	 * este tiene un error de concepto en la parte de la corrida de los arboles es con diferentes train entonces ... 
	 * cuando quieras hacer los ensembles directo al arbol te va a dar sarasa proque no van a coincidir train y test siempre :S
	 * la parte de ensambles aca SOBRA
	 * @param probabilidadFiltro
	 * @param calculoGananciaGanancia
	 * @param calculoGananciaCosto
	 * @return
	 */
	@Deprecated
	public static String generarGananciasModelosParaCasoPuntual(String probabilidadFiltro, int calculoGananciaGanancia, int calculoGananciaCosto) {
		// voy haciendo get de las variables con el postfijo acorde N, P o G

		
		String prefixColProba = "probab2";
		String prefixColGanancia = "ganancia";
		String prefixColTrain = "esTrain";
		String prefixColNodo= "NodeID";
		
		String[] subfixModelos = {"Solo","SoloAgre","NorAgre","NorTarj","SoloDoubNOHist","SoloEstadoNoHist","SoloIntsNoHist"};
		String[] paramsModelos = {"CHAID_7_300_65_101723","CHAID_7_300_65_208403","CHAID_7_300_65_663552","CHAID_7_300_65_826668","CHAID_7_300_65_980641"};
		String[] prefixCols = {prefixColProba,prefixColGanancia,prefixColNodo,prefixColTrain};
		
//		los modelos corridos son params modelos + subfix modelos
//		las columnas generadas son prefixCols + "_" + paramsModelos + "_" + subfixModelos
		
//		tiposmodelos > modelos > tipos variables > variables
		Map<String,Map<String,Map<String,String>>> armaNombres = armaNombres(subfixModelos,paramsModelos,prefixCols);
//		tengo que armar para modelos con sus ganancias individuales, luego ganancias por grupo , y luego ganancias generales
		
		StringBuilder build = new StringBuilder();
		
		String todosProba = AVG_PREFIX+prefixColProba+"_"+TODOS;
		String todosGanancia = AVG_PREFIX+prefixColGanancia+"_"+TODOS;
		StringBuilder todosBuilder = new StringBuilder() ;
		todosBuilder.append("COMPUTE ").append(todosProba).append("=(");
		int totalCounter = 0;
		
//		para cada tipo modelo
		for (Entry<String, Map<String,Map<String,String>>> tipoModelos : armaNombres.entrySet()) {
			
//			avg_probs=(A_0_A_prob_b2+A_1_A_prob_b2+A_2_A_prob_b2+A_3_A_prob_b2+A_4_A_prob_b2)/5. 
			StringBuilder nuevaProb = new StringBuilder() ;
//			avg_probs_SoloAgre=
			String currentTipoModeloProba = AVG_PREFIX+prefixColProba+"_"+tipoModelos.getKey();
			String currentTipoModeloGanancia = AVG_PREFIX+prefixColGanancia+"_"+tipoModelos.getKey();
			nuevaProb.append("COMPUTE ").append(currentTipoModeloProba).append("=(");
			
//			para cada modelo
			for (Entry<String, Map<String,String>> modelo : tipoModelos.getValue().entrySet()) {
				build.append("IF  (").append(modelo.getValue().get(prefixColProba) ).append( "   >= " ).append( probabilidadFiltro ).append( " AND clase_int=2 ) " );
				build.append( modelo.getValue().get(prefixColGanancia)).append( "=" ).append( (calculoGananciaGanancia - calculoGananciaCosto) ).append( ".").append(UtilidadesGenerales.lineSeparator);
				build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
				build.append("RECODE ").append(modelo.getValue().get(prefixColGanancia)).append(" (SYSMIS=-").append(calculoGananciaCosto).append(").").append(UtilidadesGenerales.lineSeparator);
				build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
				
				nuevaProb.append(modelo.getValue().get(prefixColProba)).append("+").append(UtilidadesGenerales.lineSeparator);
				totalCounter++;
				todosBuilder.append(modelo.getValue().get(prefixColProba)).append("+").append(UtilidadesGenerales.lineSeparator);
			}
//			saca el + que sobra
			nuevaProb.deleteCharAt(nuevaProb.lastIndexOf("+"));
//			)/5.
			nuevaProb.append(")/").append(tipoModelos.getValue().size()).append(".").append(UtilidadesGenerales.lineSeparator).append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
			build.append(nuevaProb);
// modelo avg
			build.append("IF  (").append(currentTipoModeloProba ).append( "   >= " ).append( probabilidadFiltro ).append( " AND clase_int=2 ) " );
			build.append( currentTipoModeloGanancia).append( "=" ).append( (calculoGananciaGanancia - calculoGananciaCosto) ).append( ".").append(UtilidadesGenerales.lineSeparator);
			build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
			build.append("RECODE ").append(currentTipoModeloGanancia).append(" (SYSMIS=-").append(calculoGananciaCosto).append(").").append(UtilidadesGenerales.lineSeparator);
			build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
		}
// avg de los avg ? o suma de todos y avg final ? el avg del avg me suena feo porque puedo arrastar error ( no esoty seguro del todo ) , asi que voy con el otro
		todosBuilder.deleteCharAt(todosBuilder.lastIndexOf("+"));
//		)/5.
		todosBuilder.append(")/").append(totalCounter).append(".").append(UtilidadesGenerales.lineSeparator).append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
		build.append(todosBuilder);
		
//		para los promedios de los grupos ( los de las seed )
		build.append("IF  (").append(todosProba ).append( "   >= " ).append( probabilidadFiltro ).append( " AND clase_int=2 ) " );
		build.append( todosGanancia).append( "=" ).append( (calculoGananciaGanancia - calculoGananciaCosto) ).append( ".\n");
		build.append("EXECUTE.\n");
		build.append("RECODE ").append(todosGanancia).append(" (SYSMIS=-").append(calculoGananciaCosto).append(").\n");
		build.append("EXECUTE.\n");
		
		return build.toString();
	}

	/** tipo modelo > modelo > tipo var > var
	 * @param prefixCols 
	 * @param paramsModelos 
	 * @param subfixModelos 
	 * @return
	 */
	public static Map<String, Map<String, Map<String,String>>> armaNombres(String[] subfixModelos, String[] paramsModelos, String[] prefixCols) {
//		tipo modelo modelos > tipos variables > variables
		Map<String, Map<String, Map<String,String>>> ret = new HashMap<String, Map<String,Map<String,String>>>();

// nuevo : tipo modelo > modelo > tipo var > var		
		
		for (String subfix : subfixModelos) {
			
			Map<String,Map<String,String>> modelos = new HashMap<String, Map<String,String>>();
//			armar modelos
			for (String paramsMod : paramsModelos) {
				String tmpModelName = paramsMod+"_"+subfix;
//				guardo las variables para el modelo
				Map<String,String> subVars = new HashMap<String, String>();
				
				for (String col : prefixCols) {
					String tmpVarName = col+"_"+tmpModelName;
					// cols tiene que ser mismo nombre
					subVars.put(col, tmpVarName);
				}
//				luego de generar todas las vars para los modelos, lo guardo para el modelo
				modelos.put(tmpModelName, subVars);
			}
			ret.put(subfix, modelos);
			
		}

		return ret;
	}


	public static String generarpersistenciaEjecucionModelos(Map<String, Map<String, Map<String,String>>> nombresVariables, String odbcName, String tabla) {
		// voy haciendo get de las variables con el postfijo acorde N, P o G
//		aca la cosa cambia porque lo que itera son las columnas no todo
		StringBuilder build = new StringBuilder();
		StringBuilder buildKeep = new StringBuilder();
		StringBuilder buildInsert = new StringBuilder();
		StringBuilder buildSelectVars= new StringBuilder();
		
		build.append("SAVE TRANSLATE /TYPE=ODBC").append(UtilidadesGenerales.lineSeparator).append("  /CONNECT='DSN=" + odbcName + ";'").append(UtilidadesGenerales.lineSeparator);
		build.append("  /ENCRYPTED").append(UtilidadesGenerales.lineSeparator).append("  /MISSING=RECODE").append(UtilidadesGenerales.lineSeparator);
		build.append("  /SQL='CREATE TABLE " ).append( tabla ).append("'+").append(UtilidadesGenerales.lineSeparator);		
		build.append( "' (numero_de_cliente double , foto_mes double, clase_int int,'+").append(UtilidadesGenerales.lineSeparator).append("'" );		
//		todas las columnas son double por lo tanto las puedo agregar sin drama, y no me calienta de que modelo sean , tampoco el orden
//		lo que si me calienta es el formato las ponga bien.
		
		buildKeep.append("  /REPLACE").append(UtilidadesGenerales.lineSeparator).append( "  /TABLE='SPSS_TEMP'").append(UtilidadesGenerales.lineSeparator).append("  /KEEP=numero_de_cliente, foto_mes, clase_int, " );
		buildInsert.append("  /SQL='INSERT INTO " ).append( tabla ).append(" ('+").append(UtilidadesGenerales.lineSeparator);
		buildSelectVars.append( "' numero_de_cliente, foto_mes,clase_int, " ).append("'+").append(UtilidadesGenerales.lineSeparator).append("'");
		
//		aca voy a guardar todas las variables de todo lo corrido me menefrega de odnde vengan
		for (Entry<String, Map<String, Map<String,String>>> tipoModel : nombresVariables.entrySet()) {
			
			for (Entry<String, Map<String, String>> model : tipoModel.getValue().entrySet()) {
				for (Entry<String, String> valorVar : model.getValue().entrySet()) {
					build.append(valorVar.getValue()).append( " double , '+").append(UtilidadesGenerales.lineSeparator).append("'");
					buildKeep.append( valorVar.getValue() ).append( ",").append(UtilidadesGenerales.lineSeparator);
					buildSelectVars.append( valorVar.getValue()).append( ", ").append("'+").append(UtilidadesGenerales.lineSeparator).append("'");
				}
			}
		}
		build.deleteCharAt(build.lastIndexOf(","));
		buildSelectVars.deleteCharAt(buildSelectVars.lastIndexOf(","));
//		closing
		build.append(")'").append(UtilidadesGenerales.lineSeparator);
		buildInsert.append(buildSelectVars);
		buildInsert.append(")'+").append(UtilidadesGenerales.lineSeparator).append(" 'SELECT " ).append("'+").append(UtilidadesGenerales.lineSeparator);
		buildInsert.append(buildSelectVars);
		buildInsert.append( " FROM SPSS_TEMP'").append(UtilidadesGenerales.lineSeparator);
		buildInsert.append("  /SQL='DROP TABLE SPSS_TEMP'.  ");
		
		
		buildKeep.deleteCharAt(buildKeep.lastIndexOf(","));
		
		
		build.append(buildKeep);
		build.append(buildInsert);
		
		return build.toString();
	}

	/**
	 * 
	 * @param nombresVariables lugar de donde vienen los nombres de las variables
	 * @param prefixCols  debe venir en orden : prefixCols[0]=prefixColProba, prefixCols[1]= prefixColGanancia, prefixCols[2] = prefixColNodo, prefixCols[3]=prefixColTrain 
	 * @param tabla : es la tabla a consultar
	 * @param baseDefault ejemplo : 3136800 ( es el default del mes que estas evaluando )
	 * @param porcentajeMuerte : ejemplo 1.3 ( para el 30% , porque seria el 100 + 30 )
	 * @param probLimiteGanancia : ejemplo 0.025 ( es la cota para decir cuales son buenos )
	 * @param normalizador : si no  hace falta usar 1, sino usar el correspondiente, Ej: test=30%  => normalizador=0.3
	 * @return
	 */
	public static String generarSQLParaGananciaArboles(Map<String, Map<String, Map<String,String>>> nombresVariables,String[] prefixCols, String tabla,String baseDefault,String porcentajeMuerte,String probLimiteGanancia, String normalizador) {

//		select count(*), sum(A_0_A_ganancia ), (sum(A_0_A_ganancia )- 3136800) as defa, (sum(A_0_A_ganancia )- 3136800*1.3) as muerte from ah201504pr7p300c195_20151123_003826  where A_0_A_prob_b2 >=0.025 AND A_0_A_train = 0;
		StringBuilder build = new StringBuilder();
		
		for (Entry<String, Map<String, Map<String,String>>> tipoModel : nombresVariables.entrySet()) {
			String currentSeparator  = " union ";
			
			/* para cada arbol evaluo la ganancia */
			for (Entry<String, Map<String, String>> model : tipoModel.getValue().entrySet()) {
				
				String ganancia = model.getValue().get(prefixCols[1]);
				String probab2 = model.getValue().get(prefixCols[0]);
				String esTrain = model.getValue().get(prefixCols[3]);
				
				build.append("select ").append(UtilidadesGenerales.lineSeparator);
				build.append("count(*) as afectados,").append(UtilidadesGenerales.lineSeparator);
				build.append(" sum(").append(ganancia).append("/").append(normalizador).append(") as ganancia,").append(UtilidadesGenerales.lineSeparator); 
				build.append(" (sum(").append(ganancia).append("/").append(normalizador).append(") - ").append(baseDefault).append(") as distanciaDefa,").append(UtilidadesGenerales.lineSeparator); 
				build.append(" (sum(").append(ganancia).append("/").append(normalizador).append(")- ").append(baseDefault).append("*").append(porcentajeMuerte).append(") as distanciaMuerte,").append(UtilidadesGenerales.lineSeparator);
				build.append(" ").append(baseDefault).append(" as defa , ").append(baseDefault).append("*").append(porcentajeMuerte).append(" as muerte ").append(UtilidadesGenerales.lineSeparator);
				build.append(" from ").append(tabla).append(" where ").append(UtilidadesGenerales.lineSeparator);
				build.append(" ").append(probab2).append(" >= 0.025 AND ").append(esTrain).append(" = 0 ").append(UtilidadesGenerales.lineSeparator);
				build.append(currentSeparator).append(UtilidadesGenerales.lineSeparator);
				
//				build.append("select count(*), sum(").append(ganancia);
//				build.append("), (sum(").append(ganancia).append("- ").append(baseDefault).append(") as defa, (sum(").append(baseDefault).append(")- ").append(baseDefault).append("*").append(porcentajeMuerte).append(") as muerte from ");
//				build.append(tabla).append(" where ").append(probab2).append(" >= ").append(probLimiteGanancia).append(" AND ").append(esTrain).append(" = 0").append(UtilidadesGenerales.separator);
				
			}
			int pos = build.lastIndexOf(currentSeparator);
			build.delete(pos,pos+currentSeparator.length());
			build.append(";");
			build.append(UtilidadesGenerales.lineSeparator);
		}
		return build.toString();
	}
	
	// * aplicar ensamble de cada semilla
	public static String getEnsembleModels(String aCarpeta,Map<String, Map<String, Map<String,String>>> nombresVariables,String[] prefixCols, String probabilidadFiltro, int calculoGananciaGanancia, int calculoGananciaCosto){
		
		String pathCarpeta = aCarpeta.endsWith(UtilidadesGenerales.fileSeparator)?aCarpeta:aCarpeta+UtilidadesGenerales.fileSeparator;
		StringBuilder build = new StringBuilder();
//		aplica todos los modelos en la carpeta
		final String train = "_train";
		try {
			Files.list(new File(pathCarpeta).toPath())
				 .filter(filePath -> filePath.toString().contains(train))
				 .forEach(filePath ->  
				 	build.append(
				 			generarCorrerModelos(filePath.toString(),nombresVariables,prefixCols,probabilidadFiltro,calculoGananciaGanancia,calculoGananciaCosto)));
			
			
			
			build.append(generarGananciasModelosParaCasoPuntualFIXEDNOENSEMBLE(nombresVariables, prefixCols, probabilidadFiltro, calculoGananciaGanancia, calculoGananciaCosto));
			
			
			String todosProba = AVG_PREFIX+prefixCols[0]+"_"+TODOS;
			String todosGanancia = AVG_PREFIX+prefixCols[1]+"_"+TODOS;
			StringBuilder todosBuilder = new StringBuilder() ;
			todosBuilder.append("COMPUTE ").append(todosProba).append("=(");
			int totalCounter = 0;
			

			for (Entry<String, Map<String,Map<String,String>>> tipoModelos : nombresVariables.entrySet()) {
				
//				avg_probs=(A_0_A_prob_b2+A_1_A_prob_b2+A_2_A_prob_b2+A_3_A_prob_b2+A_4_A_prob_b2)/5. 
				StringBuilder nuevaProb = new StringBuilder() ;
				//				avg_probs_SoloAgre=
				String currentTipoModeloProba = AVG_PREFIX+prefixCols[0]+"_"+tipoModelos.getKey();
				String currentTipoModeloGanancia = AVG_PREFIX+prefixCols[1]+"_"+tipoModelos.getKey();
				nuevaProb.append("COMPUTE ").append(currentTipoModeloProba).append("=(");
				
				for (Entry<String, Map<String, String>> modeloIt : tipoModelos.getValue().entrySet()) {
					nuevaProb.append(modeloIt.getValue().get(prefixCols[0])).append("+").append(UtilidadesGenerales.lineSeparator);
					totalCounter++;
					todosBuilder.append(modeloIt.getValue().get(prefixCols[0])).append("+").append(UtilidadesGenerales.lineSeparator);
				}
				nuevaProb.deleteCharAt(nuevaProb.lastIndexOf("+"));
				nuevaProb.append(")/").append(tipoModelos.getValue().size()).append(".").append(UtilidadesGenerales.lineSeparator).append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
				build.append(nuevaProb);
				// modelo avg
				build.append("IF  (").append(currentTipoModeloProba ).append( "   >= " ).append( probabilidadFiltro ).append( " AND clase_int=2 ) " );
				build.append( currentTipoModeloGanancia).append( "=" ).append( (calculoGananciaGanancia - calculoGananciaCosto) ).append( ".").append(UtilidadesGenerales.lineSeparator);
				build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
				build.append("RECODE ").append(currentTipoModeloGanancia).append(" (SYSMIS=-").append(calculoGananciaCosto).append(").").append(UtilidadesGenerales.lineSeparator);
				build.append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
			}
	// avg de los avg ? o suma de todos y avg final ? el avg del avg me suena feo porque puedo arrastar error ( no esoty seguro del todo ) , asi que voy con el otro
			todosBuilder.deleteCharAt(todosBuilder.lastIndexOf("+"));
//			)/5.
			todosBuilder.append(")/").append(totalCounter).append(".").append(UtilidadesGenerales.lineSeparator).append("EXECUTE.").append(UtilidadesGenerales.lineSeparator);
			build.append(todosBuilder);
			
//			para los promedios de los grupos ( los de las seed )
			build.append("IF  (").append(todosProba ).append( "   >= " ).append( probabilidadFiltro ).append( " AND clase_int=2 ) " );
			build.append( todosGanancia).append( "=" ).append( (calculoGananciaGanancia - calculoGananciaCosto) ).append( ".\n");
			build.append("EXECUTE.\n");
			build.append("RECODE ").append(todosGanancia).append(" (SYSMIS=-").append(calculoGananciaCosto).append(").\n");
			build.append("EXECUTE.\n");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		renombra las variables
//		hace el avg de los que terminan igual
//		renombra las variables
//		hace el avg general
//		renombra las variables
		return build.toString();
	}
	
	/**
	 * 
	 * @param modeloName el nombre del modelo en formato: CHAID_7_300_65_101723_NorTarj_train_model.xml DEBE ESTAR EN 5to Lugar el tipificador de corrida
	 * @param nombresVariables
	 * @param prefixCols debe venir en orden : prefixCols[0]=prefixColProba, prefixCols[1]= prefixColGanancia, prefixCols[2] = prefixColNodo, prefixCols[3]=prefixColTrain
	 * @param probabilidadFiltro 
	 * @param calculoGananciaGanancia 
	 * @param calculoGananciaCosto 
	 * @return
	 */
	private static String generarCorrerModelos(String modeloPath, Map<String, Map<String, Map<String,String>>> nombresVariables,String[] prefixCols, String probabilidadFiltro, int calculoGananciaGanancia, int calculoGananciaCosto) {
		
		StringBuilder build = new StringBuilder();
		try {
			String modeloName = modeloPath.substring(modeloPath.lastIndexOf(UtilidadesGenerales.fileSeparator)+UtilidadesGenerales.fileSeparator.length());
			
//		agarro tipo modelo
			String parseCurrentTipo = modeloName.split("_")[5];
//		CHAID_7_300_65_101723_NorTarj_train_model.xml
			final String train = "_train";
			String postfixVars= modeloName.substring(0,modeloName.indexOf(train)-parseCurrentTipo.length()-1);
			String modeloKey = modeloName.substring(0,modeloName.indexOf(parseCurrentTipo)+parseCurrentTipo.length());
//		agarro modelo
			Map<String, Map<String, String>> tipo = nombresVariables.get(parseCurrentTipo);
			Map<String,String> modelo = tipo.get(modeloKey);
			
			
			build.append("MODEL HANDLE NAME=model FILE='").append( modeloPath ).append( "'").append(UtilidadesGenerales.lineSeparator).append( "  /OPTIONS MISSING=SUBSTITUTE.").append(UtilidadesGenerales.lineSeparator);
			// son N nodos asociados a cada modelo
			build.append("COMPUTE " ).append( modelo.get(prefixCols[2])).append( "=APPLYMODEL(model, 'NODEID').").append(UtilidadesGenerales.lineSeparator);
			// son N probabilidadies asociados a cada modelo
			build.append("COMPUTE ").append(  modelo.get(prefixCols[0])).append( "=APPLYMODEL(model, 'PROBABILITY', 'BAJA+2').");
			build.append(UtilidadesGenerales.lineSeparator).append( "EXECUTE.").append(UtilidadesGenerales.lineSeparator).append( "MODEL CLOSE NAME=model.").append(UtilidadesGenerales.lineSeparator);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return build.toString();
		
		
//		for (String modelo : modelFileXml) {
//			build.append("MODEL HANDLE NAME=model FILE='").append( modelo ).append( "'\n").append( "  /OPTIONS MISSING=SUBSTITUTE.\n");
//			// son N nodos asociados a cada modelo
//			build.append("COMPUTE " ).append( nombresVariables.get(modelo+MODEL_SUFFIX_N) ).append( "=APPLYMODEL(model, 'NODEID').\n ");
//			// son N probabilidadies asociados a cada modelo
//			build.append("COMPUTE ").append( nombresVariables.get(modelo+MODEL_SUFFIX_P) ).append( "=APPLYMODEL(model, 'PROBABILITY', 'BAJA+2').\n").append( "EXECUTE.\n").append( "MODEL CLOSE NAME=model.\n");
//		}
		
	}
	/**
	 * 
	 * @param nombresVariables
	 * @param prefixCols debe venir en orden : prefixCols[0]=prefixColProba, prefixCols[1]= prefixColGanancia, prefixCols[2] = prefixColNodo, prefixCols[3]=prefixColTrain
	 * @param odbcName
	 * @param tabla
	 * @return
	 */
	public static String generarPersistenciaEjecucionEnsemble(Map<String, Map<String, Map<String,String>>> nombresVariables,String[] prefixCols, String odbcName, String tabla) {
		// voy haciendo get de las variables con el postfijo acorde N, P o G
//		aca la cosa cambia porque lo que itera son las columnas no todo
		StringBuilder build = new StringBuilder();
		StringBuilder buildKeep = new StringBuilder();
		StringBuilder buildInsert = new StringBuilder();
		StringBuilder buildSelectVars= new StringBuilder();
		
		build.append("SAVE TRANSLATE /TYPE=ODBC").append(UtilidadesGenerales.lineSeparator).append("  /CONNECT='DSN=" + odbcName + ";'").append(UtilidadesGenerales.lineSeparator);
		build.append("  /ENCRYPTED").append(UtilidadesGenerales.lineSeparator).append("  /MISSING=RECODE").append(UtilidadesGenerales.lineSeparator);
		build.append("  /SQL='CREATE TABLE " ).append( tabla ).append("'+").append(UtilidadesGenerales.lineSeparator);		
		build.append( "' (numero_de_cliente double , foto_mes double, clase_int int,'+").append(UtilidadesGenerales.lineSeparator).append("'" );		
//		todas las columnas son double por lo tanto las puedo agregar sin drama, y no me calienta de que modelo sean , tampoco el orden
//		lo que si me calienta es el formato las ponga bien.
		
		buildKeep.append("  /REPLACE").append(UtilidadesGenerales.lineSeparator).append( "  /TABLE='SPSS_TEMP'").append(UtilidadesGenerales.lineSeparator).append("  /KEEP=numero_de_cliente, foto_mes, clase_int, " );
		buildInsert.append("  /SQL='INSERT INTO " ).append( tabla ).append(" ('+").append(UtilidadesGenerales.lineSeparator);
		buildSelectVars.append( "' numero_de_cliente, foto_mes,clase_int, " ).append("'+").append(UtilidadesGenerales.lineSeparator).append("'");
		
//		aca voy a guardar todas las variables de todo lo corrido me menefrega de odnde vengan
		for (Entry<String, Map<String, Map<String,String>>> tipoModel : nombresVariables.entrySet()) {
			
			for (Entry<String, Map<String, String>> model : tipoModel.getValue().entrySet()) {
				for (Entry<String, String> valorVar : model.getValue().entrySet()) {
					build.append(valorVar.getValue()).append( " double , '+").append(UtilidadesGenerales.lineSeparator).append("'");
					buildKeep.append( valorVar.getValue() ).append( ",").append(UtilidadesGenerales.lineSeparator);
					buildSelectVars.append( valorVar.getValue()).append( ", ").append("'+").append(UtilidadesGenerales.lineSeparator).append("'");
				}
			}
//			lo que agrega es que por cada tipo agrega un par de variables, 
			String avgEnsembleLocal = AVG_PREFIX+prefixCols[0]+"_"+tipoModel.getKey();
			build.append(avgEnsembleLocal).append( " double , '+").append(UtilidadesGenerales.lineSeparator).append("'");
			buildKeep.append( avgEnsembleLocal) .append( ",").append(UtilidadesGenerales.lineSeparator);
			buildSelectVars.append( avgEnsembleLocal).append( ", ").append("'+").append(UtilidadesGenerales.lineSeparator).append("'");
			
			String avgEnsembleLocalGanancia = AVG_PREFIX+prefixCols[1]+"_"+tipoModel.getKey();
			build.append(avgEnsembleLocalGanancia).append( " double , '+").append(UtilidadesGenerales.lineSeparator).append("'");
			buildKeep.append( avgEnsembleLocalGanancia) .append( ",").append(UtilidadesGenerales.lineSeparator);
			buildSelectVars.append( avgEnsembleLocalGanancia).append( ", ").append("'+").append(UtilidadesGenerales.lineSeparator).append("'");
		}
//		lo que agrega es que al final agrega mas variables
		String avgEnsembleGlobal = AVG_PREFIX+prefixCols[0]+"_"+TODOS;
		build.append(avgEnsembleGlobal).append( " double , '+").append(UtilidadesGenerales.lineSeparator).append("'");
		buildKeep.append( avgEnsembleGlobal) .append( ",").append(UtilidadesGenerales.lineSeparator);
		buildSelectVars.append( avgEnsembleGlobal).append( ", ").append("'+").append(UtilidadesGenerales.lineSeparator).append("'");
		
		String avgEnsembleGanancia = AVG_PREFIX+prefixCols[1]+"_"+TODOS;
		build.append(avgEnsembleGanancia).append( " double , '+").append(UtilidadesGenerales.lineSeparator).append("'");
		buildKeep.append( avgEnsembleGanancia) .append( ",").append(UtilidadesGenerales.lineSeparator);
		buildSelectVars.append( avgEnsembleGanancia).append( ", ").append("'+").append(UtilidadesGenerales.lineSeparator).append("'");
		
		
		
		build.deleteCharAt(build.lastIndexOf(","));
		buildSelectVars.deleteCharAt(buildSelectVars.lastIndexOf(","));
//		closing
		build.append(")'").append(UtilidadesGenerales.lineSeparator);
		buildInsert.append(buildSelectVars);
		buildInsert.append(")'+").append(UtilidadesGenerales.lineSeparator).append(" 'SELECT " ).append("'+").append(UtilidadesGenerales.lineSeparator);
		buildInsert.append(buildSelectVars);
		buildInsert.append( " FROM SPSS_TEMP'").append(UtilidadesGenerales.lineSeparator);
		buildInsert.append("  /SQL='DROP TABLE SPSS_TEMP'.  ");
		
		
		buildKeep.deleteCharAt(buildKeep.lastIndexOf(","));
		
		
		build.append(buildKeep);
		build.append(buildInsert);
		
		return build.toString();
	}
	

	/**
	 * 
	 * @param nombresVariables lugar de donde vienen los nombres de las variables
	 * @param prefixCols  debe venir en orden : prefixCols[0]=prefixColProba, prefixCols[1]= prefixColGanancia, prefixCols[2] = prefixColNodo
	 * @param tabla : es la tabla a consultar
	 * @param baseDefault ejemplo : 3136800 ( es el default del mes que estas evaluando )
	 * @param porcentajeMuerte : ejemplo 1.3 ( para el 30% , porque seria el 100 + 30 )
	 * @param probLimiteGanancia : ejemplo 0.025 ( es la cota para decir cuales son buenos )
	 * @param normalizador : si no  hace falta usar 1, sino usar el correspondiente, Ej: test=30%  => normalizador=0.3
	 * @return
	 */
	public static String generarSQLParaGananciaEnsamble(Map<String, Map<String, Map<String,String>>> nombresVariables,String[] prefixCols, String tabla,String baseDefault,String porcentajeMuerte,String probLimiteGanancia, String normalizador) {

//		select count(*), sum(A_0_A_ganancia ), (sum(A_0_A_ganancia )- 3136800) as defa, (sum(A_0_A_ganancia )- 3136800*1.3) as muerte from ah201504pr7p300c195_20151123_003826  where A_0_A_prob_b2 >=0.025 AND A_0_A_train = 0;
		StringBuilder build = new StringBuilder();
		String currentSeparator  = " union ";
		for (Entry<String, Map<String, Map<String,String>>> tipoModel : nombresVariables.entrySet()) {
			String avgEnsembleLocal = AVG_PREFIX+prefixCols[0]+"_"+tipoModel.getKey();
			String avgEnsembleLocalGanancia = AVG_PREFIX+prefixCols[1]+"_"+tipoModel.getKey();
			String ganancia = avgEnsembleLocalGanancia;
			String probab2 = avgEnsembleLocal;
			
			build.append("select ").append(UtilidadesGenerales.lineSeparator);
			build.append("count(*) as afectados,").append(UtilidadesGenerales.lineSeparator);
			build.append(" sum(").append(ganancia).append("/").append(normalizador).append(") as ganancia,").append(UtilidadesGenerales.lineSeparator); 
			build.append(" (sum(").append(ganancia).append("/").append(normalizador).append(") - ").append(baseDefault).append(") as distanciaDefa,").append(UtilidadesGenerales.lineSeparator); 
			build.append(" (sum(").append(ganancia).append("/").append(normalizador).append(")- ").append(baseDefault).append("*").append(porcentajeMuerte).append(") as distanciaMuerte,").append(UtilidadesGenerales.lineSeparator);
			build.append(" ").append(baseDefault).append(" as defa , ").append(baseDefault).append("*").append(porcentajeMuerte).append(" as muerte ").append(UtilidadesGenerales.lineSeparator);
			build.append(" from ").append(tabla).append(" where ").append(UtilidadesGenerales.lineSeparator);
			build.append(" ").append(probab2).append(" >= 0.025 ").append(UtilidadesGenerales.lineSeparator);
			build.append(currentSeparator).append(UtilidadesGenerales.lineSeparator);

		}
		String avgEnsembleGanancia = AVG_PREFIX+prefixCols[1]+"_"+TODOS;
		String avgEnsembleGlobal = AVG_PREFIX+prefixCols[0]+"_"+TODOS;
		String ganancia = avgEnsembleGanancia;
		String probab2 = avgEnsembleGlobal;
		build.append("select ").append(UtilidadesGenerales.lineSeparator);
		build.append("count(*) as afectados,").append(UtilidadesGenerales.lineSeparator);
		build.append(" sum(").append(ganancia).append("/").append(normalizador).append(") as ganancia,").append(UtilidadesGenerales.lineSeparator); 
		build.append(" (sum(").append(ganancia).append("/").append(normalizador).append(") - ").append(baseDefault).append(") as distanciaDefa,").append(UtilidadesGenerales.lineSeparator); 
		build.append(" (sum(").append(ganancia).append("/").append(normalizador).append(")- ").append(baseDefault).append("*").append(porcentajeMuerte).append(") as distanciaMuerte,").append(UtilidadesGenerales.lineSeparator);
		build.append(" ").append(baseDefault).append(" as defa , ").append(baseDefault).append("*").append(porcentajeMuerte).append(" as muerte ").append(UtilidadesGenerales.lineSeparator);
		build.append(" from ").append(tabla).append(" where ").append(UtilidadesGenerales.lineSeparator);
		build.append(" ").append(probab2).append(" >= 0.025 ").append(UtilidadesGenerales.lineSeparator);
		build.append(currentSeparator).append(UtilidadesGenerales.lineSeparator);
		
		
		int pos = build.lastIndexOf(currentSeparator);
		build.delete(pos,pos+currentSeparator.length());
		build.append(";");
		build.append(UtilidadesGenerales.lineSeparator);
		return build.toString();
	}
	

	// * aplicar ensemble de otros arboles
	public abstract String getEnsembleAllModels();

	
	
	/**
	 * recorre carpeta devuelve el nombre de los mdoelos
CHAID_6_300_70_undefi_old_train_model.xml 
siendo: tipoarbol, profundidad, cantidad padres. proporcion hijos, seed, agrupador, segmento+model.xml
	 * @param paramsModelosFromCarpeta
	 * @param prefixCols String[] prefixCols = { [0]prefixColProba, [1]prefixColGanancia, [2]prefixColNodo, [3]prefixColTrain };
	 * @return
	 */
	public static Map<String, Map<String, Map<String, String>>> armaNombres(String[] paramsModelosFromCarpeta, String[] prefixCols) {
//		tipo modelo modelos > tipos variables > variables
		Map<String, Map<String, Map<String,String>>> ret = new HashMap<String, Map<String,Map<String,String>>>();

//		String subfixModelos = 
		
// nuevo : tipo modelo > modelo > tipo var > var		
		
		for (String modelo : paramsModelosFromCarpeta) {
			
			String subfix = modelo.substring(0,modelo.lastIndexOf("_train"));
			subfix = subfix.substring(subfix.lastIndexOf("_")+1);

			Map<String,Map<String,String>> modelos = ret.get(subfix);
			if (modelos == null) {
				 modelos = new HashMap<String, Map<String,String>>();
			}
			String tmpModelName = modelo.substring(0,modelo.lastIndexOf("_train"));

//			armar modelos
				
//			guardo las variables para el modelo
			Map<String,String> subVars = new HashMap<String, String>();
			
			for (String col : prefixCols) {
				String tmpVarName = col+"_"+tmpModelName;
				// cols tiene que ser mismo nombre
				subVars.put(col, tmpVarName);
			}
//			luego de generar todas las vars para los modelos, lo guardo para el modelo
			modelos.put(tmpModelName, subVars);

			ret.put(subfix, modelos);
			
		}

		return ret;
	}

}
