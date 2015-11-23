package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.sql;


public class AutomatizarCorridasModeloJulioConfig {
	
	// tiene que tener todas las columnas como el formato de spss /SQL sql partido creo que cada 4 campos o max width
	private String comandoSQLSPSS;
	// nombre del archivo xml a aplicar
	private String modelFileXml;
	// nombre de la columna resultante con el id de nodo del modelo
	private String modelNodeIdVar;
	// nombre de la columna resultante con la probabilidad de baja2
	private String modelProbBaja2Var;
	// nombre de la columna resultante con la ganancia
	private String modelGananciaVar;
	// nombre de la conexion odbc a la db
	private String odbcName;
	// nombre de la talba que se va a crear / insertar los resultados
	private String tableInsertResultado;

	// probabilidad filtro par acalculo ganancia deberia ser 0.025 o simil
	private String probabilidadFiltro;
	// ganancia del calculo de ganancia poner los 8000  la cuenta ganancia - costo se hace adentro.
	private int calculoGananciaGanancia;
	// costo del calculo de ganancia
	private int calculoGananciaCosto;
	
	// carpeta timestamp
	private String timeStampFolder;
	// carpeta output
	private String outputFolder;
	// comando spss
	private String[] comandoSPSS;
	
	
	/**
	 * la idea es ar amodelos con multiples seed se puedea  correr las 5 a la vez
	 * @param comandoSQLSPSS
	 * @param modelFileXml
	 * @param modelNodeIdVar
	 * @param modelProbBaja2Var
	 * @param modelGananciaVar
	 * @param odbcName
	 * @param tableInsertResultado
	 * @param probabilidadFiltro
	 * @param calculoGananciaGanancia
	 * @param calculoGananciaCosto
	 * @param timeStampFolder
	 * @param outputFolder
	 * @param ensembleGanancia 
	 * @param ensembleProbs 
	 */
	public AutomatizarCorridasModeloJulioConfig(String comandoSQLSPSS, String[] modelFileXml, String modelNodeIdVar, String modelProbBaja2Var, String modelGananciaVar, String odbcName, String tableInsertResultado, String probabilidadFiltro, int calculoGananciaGanancia, int calculoGananciaCosto, String timeStampFolder, String outputFolder, String ensembleProbs, String ensembleGanancia) {
		this.comandoSQLSPSS = comandoSQLSPSS;
		this.modelNodeIdVar = modelNodeIdVar;
		this.modelProbBaja2Var = modelProbBaja2Var;
		this.modelGananciaVar = modelGananciaVar;
		this.odbcName = odbcName;
		this.tableInsertResultado = tableInsertResultado;
		this.probabilidadFiltro = probabilidadFiltro;
		this.calculoGananciaGanancia = calculoGananciaGanancia;
		this.calculoGananciaCosto = calculoGananciaCosto;
		this.timeStampFolder = timeStampFolder;
		this.outputFolder = outputFolder;
		
		comandoSPSS = AcumuladorComandosSpss.comandoModeloToDBWithSeed(outputFolder, timeStampFolder, comandoSQLSPSS, modelFileXml, modelNodeIdVar, modelProbBaja2Var, modelGananciaVar, odbcName, tableInsertResultado, probabilidadFiltro, calculoGananciaGanancia, calculoGananciaCosto, ensembleProbs,  ensembleGanancia);
		
	}
	
	
	public AutomatizarCorridasModeloJulioConfig(String comandoSQLSPSS, String modelFileXml, String modelNodeIdVar, String modelProbBaja2Var, String modelGananciaVar, String odbcName, String tableInsertResultado, String probabilidadFiltro, int calculoGananciaGanancia, int calculoGananciaCosto, String timeStampFolder, String outputFolder) {
		this.comandoSQLSPSS = comandoSQLSPSS;
		this.modelFileXml = modelFileXml;
		this.modelNodeIdVar = modelNodeIdVar;
		this.modelProbBaja2Var = modelProbBaja2Var;
		this.modelGananciaVar = modelGananciaVar;
		this.odbcName = odbcName;
		this.tableInsertResultado = tableInsertResultado;
		this.probabilidadFiltro = probabilidadFiltro;
		this.calculoGananciaGanancia = calculoGananciaGanancia;
		this.calculoGananciaCosto = calculoGananciaCosto;
		this.timeStampFolder = timeStampFolder;
		this.outputFolder = outputFolder;
		
		comandoSPSS = AcumuladorComandosSpss.comandoModeloToDB(outputFolder, timeStampFolder, comandoSQLSPSS, modelFileXml, modelNodeIdVar, modelProbBaja2Var, modelGananciaVar, odbcName, tableInsertResultado, probabilidadFiltro, calculoGananciaGanancia, calculoGananciaCosto);
		
	}
	public String getComandoSQLSPSS() {
		return comandoSQLSPSS;
	}
	public void setComandoSQLSPSS(String comandoSQLSPSS) {
		this.comandoSQLSPSS = comandoSQLSPSS;
	}
	public String getModelFileXml() {
		return modelFileXml;
	}
	public void setModelFileXml(String modelFileXml) {
		this.modelFileXml = modelFileXml;
	}
	public String getModelNodeIdVar() {
		return modelNodeIdVar;
	}
	public void setModelNodeIdVar(String modelNodeIdVar) {
		this.modelNodeIdVar = modelNodeIdVar;
	}
	public String getModelProbBaja2Var() {
		return modelProbBaja2Var;
	}
	public void setModelProbBaja2Var(String modelProbBaja2Var) {
		this.modelProbBaja2Var = modelProbBaja2Var;
	}
	public String getModelGananciaVar() {
		return modelGananciaVar;
	}
	public void setModelGananciaVar(String modelGananciaVar) {
		this.modelGananciaVar = modelGananciaVar;
	}
	public String getOdbcName() {
		return odbcName;
	}
	public void setOdbcName(String odbcName) {
		this.odbcName = odbcName;
	}
	public String getTableInsertResultado() {
		return tableInsertResultado;
	}
	public void setTableInsertResultado(String tableInsertResultado) {
		this.tableInsertResultado = tableInsertResultado;
	}
	public String getProbabilidadFiltro() {
		return probabilidadFiltro;
	}
	public void setProbabilidadFiltro(String probabilidadFiltro) {
		this.probabilidadFiltro = probabilidadFiltro;
	}
	public int getCalculoGananciaGanancia() {
		return calculoGananciaGanancia;
	}
	public void setCalculoGananciaGanancia(int calculoGananciaGanancia) {
		this.calculoGananciaGanancia = calculoGananciaGanancia;
	}
	public int getCalculoGananciaCosto() {
		return calculoGananciaCosto;
	}
	public void setCalculoGananciaCosto(int calculoGananciaCosto) {
		this.calculoGananciaCosto = calculoGananciaCosto;
	}
	public String getTimeStampFolder() {
		return timeStampFolder;
	}
	public void setTimeStampFolder(String timeStampFolder) {
		this.timeStampFolder = timeStampFolder;
	}
	public String getOutputFolder() {
		return outputFolder;
	}
	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}
	public String[] getComandoSPSS() {
		return comandoSPSS;
	}
	public void setComandoSPSS(String[] comandoSPSS) {
		this.comandoSPSS = comandoSPSS;
	}
}
