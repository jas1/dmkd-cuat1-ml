package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.old;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.statistics.plugin.StatsException;
import com.ibm.statistics.plugin.StatsUtil;

/**
 *
 */
public class AutomatizarCorridasArbolFernando {


	
	private Double FACTOR_USO;
	private Integer PLIEGUES;
	private String CAMINO;
	private String ARCHIVO;
	private String IDENTIFICADOR;
	private String PREDICTOR_DESDE;
	private String PREDICTOR_HASTA;
	private String OBJETIVO;
	
	private String ALGORITMO;
	
	private Double ALFASPLIT;
	private Double ALFAMERGE;
	private String RESPLIT;
	private String CHI_CUADRADO;
	private Double CONVERGENCIA;
	private Integer INTERVALOS;
	private Integer PROFUNDIDAD;
	private Integer CANTIDAD_CASOS;
	
	public AutomatizarCorridasArbolFernando() {
		FACTOR_USO = 0.666;
		PLIEGUES = 3;
		CAMINO = "D:/SPSS/Privado/Maestria/Laboratorio/Clase_20150905/Procesos/";
		ARCHIVO = "Customer_dbase";
		IDENTIFICADOR = "custid";
		PREDICTOR_DESDE = "region";
		PREDICTOR_HASTA = "news";
		OBJETIVO = "default";
		ALGORITMO = "CHAID";
	}
	

	/**
	 * esto es lo que era main
Sub Main
	PRUEBA = 0
	Call CARGAR("")
	Call SET_SEED()
	Call COMPUTE_SELECTOR()
	Call FILTRAR_RESERVA()
	Call LOOP_PRINCIPAL()
End Sub
	 */
	public void ejecutar(){

//		PRUEBA = 0
//		Call CARGAR("")
		cargar("id archivo , donde ta");
//		Call SET_SEED()
		setearSeed();
//		Call COMPUTE_SELECTOR()
		computeSelector();
//		Call FILTRAR_RESERVA()
		filtrarReserva();
//		Call LOOP_PRINCIPAL()
		loopPrincipal();
	}
	
	/**


Dim PRUEBAS() As String
Dim PRUEBAS_C() As String
Dim PRUEBA	As Integer

'	PARAMETROS CHAID
Dim ALFASPLIT As Double
Dim ALFAMERGE As Double
Dim RESPLIT As String
Dim CHI_CUADRADO As String
Dim CONVERGENCIA As Double
Dim INTERVALOS As Integer
Dim PROFUNDIDAD As Integer
Dim CANTIDAD_CASOS As Integer


Sub Main
	PRUEBA = 0
	Call CARGAR("")
	Call SET_SEED()
	Call COMPUTE_SELECTOR()
	Call FILTRAR_RESERVA()
	Call LOOP_PRINCIPAL()
End Sub

*/

private void grabar(String idArchivo) {
	/*
Sub GRABAR(ID_ARCHIVO)

	Dim strCommand As String
	strCommand = strCommand & "SAVE OUTFILE="
	strCommand = strCommand & "'" & CAMINO
	strCommand = strCommand & ARCHIVO
	strCommand = strCommand & ID_ARCHIVO & ".sav'"
	strCommand = strCommand & "/COMPRESSED."
	strCommand = strCommand & vbCrLf
	objSpssApp.ExecuteCommands strCommand, True

End Sub
*/
}

private void grabarDrop(String idArchivo,String varDrop) {

/*
Sub GRABAR_DROP(ID_ARCHIVO,VAR_DROP)

	Dim strCommand As String
	strCommand = strCommand & "SAVE OUTFILE="
	strCommand = strCommand & "'" & CAMINO
	strCommand = strCommand & ARCHIVO
	strCommand = strCommand & ID_ARCHIVO & ".sav'"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "/DROP " & VAR_DROP
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "/COMPRESSED."
	strCommand = strCommand & vbCrLf
	objSpssApp.ExecuteCommands strCommand, True

End Sub
*/
}
private void cargar(String idArchivo) {

/*
Sub CARGAR(ID_ARCHIVO)

	Dim strCommand As String
	strCommand = strCommand & "GET "
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   FILE = " & "'" & CAMINO
	strCommand = strCommand & ARCHIVO
	strCommand = strCommand & ID_ARCHIVO & ".sav'"
	strCommand = strCommand & "."
	strCommand = strCommand & vbCrLf
	objSpssApp.ExecuteCommands strCommand, True
	strCommand = "CACHE."
	strCommand = strCommand & vbCrLf
	objSpssApp.ExecuteCommands strCommand, True

End Sub
*/
}

private void setearSeed() {

/*
Sub SET_SEED()

	Dim strCommand As String
	strCommand = strCommand & "SET SEED 2000000."
	strCommand = strCommand & vbCrLf
	objSpssApp.ExecuteCommands strCommand, True

End Sub
*/
}

private void computeSelector() {
	/*
Sub COMPUTE_SELECTOR()

	Dim strCommand As String
	strCommand = strCommand & "COMPUTE SELECTOR = UNIFORM(1)."
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "EXECUTE."
	strCommand = strCommand & vbCrLf
	objSpssApp.ExecuteCommands strCommand, True
	Call GRABAR("")

End Sub
*/
}
private void filtrarReserva() {

	/*
Sub FILTRAR_RESERVA()

	Dim strCommand As String
	strCommand = strCommand & "FILTER OFF."
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "USE ALL."
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "SELECT IF (SELECTOR < " & Str(FACTOR_USO) & ")."
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "EXECUTE."
	strCommand = strCommand & vbCrLf
	objSpssApp.ExecuteCommands strCommand, True
	Call GRABAR("_tr")

End Sub
*/
}

private void loopPrincipal(){
	/*
	Sub LOOP_PRINCIPAL()
		ALFASPLIT = 0.05
		ALFAMERGE = 0.05
		RESPLIT = "NO"
		CONVERGENCIA = 0.05
		INTERVALOS = 10
		PROFUNDIDAD = 10
		CANTIDAD_CASOS = 100
		For INTERVALOS = 3 To 15 Step 3
			For CANTIDAD_CASOS = 100 To 1000 Step 100
				CHI_CUADRADO = "LR"
				NP = NOMBRE_PRUEBA()
				Call CROSS_VALIDATION(NP)
				Call GRABAR_DROP("_" & ALGORITMO & "_" & NP, PREDICTOR_DESDE & " TO " & PREDICTOR_HASTA & ", ENTRENAMIENTO")
				Call CARGAR("_tr")
				CHI_CUADRADO = "PEARSON"
				NP = NOMBRE_PRUEBA()
				Call CROSS_VALIDATION(NP)
				Call GRABAR_DROP("_" & ALGORITMO & "_" & NP, PREDICTOR_DESDE & " TO " & PREDICTOR_HASTA & ", ENTRENAMIENTO")
				Call CARGAR("_tr")
			Next
		Next

	End Sub
	*/	
}
private void crossValidation(String nP){
/*
Sub CROSS_VALIDATION(NP)
	For I = 1 To PLIEGUES
		Call FILTRO_ENTRENAMIENTO(I)
		Call ARBOL(I,NP)
'        Call KS_ROC(I,NP)
		PRUEBA = PRUEBA + 1
		ReDim Preserve PRUEBAS(PRUEBA)
		PRUEBAS(PRUEBA) = NP & "_" & Trim(Str(I))
		ReDim Preserve PRUEBAS_C(PRUEBA)
		PRUEBAS_C(PRUEBA) = NP
	Next
End Sub
*/
}

private void filtroEntrenamiento(Integer pliegue){
	/*
Sub FILTRO_ENTRENAMIENTO(PLIEGUE)
	Dim strCommand As String
	strCommand = strCommand & "COMPUTE ENTRENAMIENTO = 0."
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "IF (SELECTOR < "
	strCommand = strCommand & Str(FACTOR_USO*(PLIEGUE - 1)/PLIEGUES) & ")"
	strCommand = strCommand & " | (SELECTOR >= "
	strCommand = strCommand & Str(FACTOR_USO*PLIEGUE/PLIEGUES) & ")"
	strCommand = strCommand & " ENTRENAMIENTO = 1."
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "EXECUTE."
	strCommand = strCommand & vbCrLf
	objSpssApp.ExecuteCommands strCommand, True
End Sub
*/
}
private void nombrePrueba(Integer pliegue){
/*
Function NOMBRE_PRUEBA()
'	CHAID
	NP = Trim(Str(ALFASPLIT)) &"_"& Trim(Str(ALFAMERGE)) &"_"& RESPLIT &"_"& CHI_CUADRADO &"_"& Trim(Str(CONVERGENCIA)) &"_"& Trim(Str(INTERVALOS)) &"_"& Trim(Str(PROFUNDIDAD)) &"_"& Trim(Str(CANTIDAD_CASOS))
	NOMBRE_PRUEBA = Replace(NP,".","d")
End Function
*/
}

private void arbol(Integer pliegue, Integer np){
/*
Sub ARBOL(PLIEGUE,NP)

'	CHAID
	Dim strCommand As String
	strCommand = strCommand & "* Classification Tree."
	strCommand = strCommand & vbCrLf
	strCommand = strCommand + "TREE " & OBJETIVO & " BY " & PREDICTOR_DESDE & " TO " & PREDICTOR_HASTA
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & " /TREE"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   DISPLAY=TOPDOWN"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   NODES=STATISTICS"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   BRANCHSTATISTICS=YES"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   NODEDEFS=YES"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   SCALE=AUTO"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & " /DEPCATEGORIES"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   USEVALUES=[0 1]"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & " /PRINT NONE"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & " /RULES"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   NODES=TERMINAL"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   SYNTAX=SPSS"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   TYPE=SCORING"
    strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   OUTFILE='"& CAMINO & ARCHIVO & "_Reglas_" & ALGORITMO & NP & "_" & Trim(Str(PLIEGUE)) & ".sps'"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & " /SAVE"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   PREDPROB(Score_" & NP & "_" & Trim(Str(PLIEGUE)) & ")"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   ASSIGNMENT(Set_" & NP & "_" & Trim(Str(PLIEGUE)) & ")"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & " /METHOD"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   TYPE=CHAID"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & " /GROWTHLIMIT"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   MAXDEPTH= " & Str(PROFUNDIDAD)
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   MINPARENTSIZE= " & Str(CANTIDAD_CASOS)
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   MINCHILDSIZE= " & Str(Round(CANTIDAD_CASOS / 2))
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & " /VALIDATION"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   TYPE=SPLITSAMPLE(ENTRENAMIENTO)"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   OUTPUT=BOTHSAMPLES"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & " /CHAID"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   ALPHASPLIT= " & Str(ALFASPLIT)
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   ALPHAMERGE= " & Str(ALFAMERGE)
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   SPLITMERGED= " & RESPLIT
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   CHISQUARE=" & CHI_CUADRADO
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   CONVERGE= " & Str(CONVERGENCIA)
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   MAXITERATIONS=100"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   ADJUST=BONFERRONI"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   INTERVALS= " & Str(INTERVALOS)
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & " /COSTS EQUAL"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & " /MISSING"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "   NOMINALMISSING=MISSING."
	strCommand = strCommand & vbCrLf
	objSpssApp.ExecuteCommands strCommand, True

End Sub
*/
}
private void ksROC(Integer pliegue, Integer np){
	/*
Sub KS_ROC(PLIEGUE,NP)
	Dim strCommand As String

	strCommand = "SORT CASES  BY Entrenamiento."
	strCommand = strCommand & vbCrLf
  	strCommand = strCommand & "EXECUTE."
	strCommand = strCommand & vbCrLf'
	objSpssApp.ExecuteCommands strCommand, True

	strCommand = "Split FILE SEPARATE BY Entrenamiento."
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "EXECUTE."
	strCommand = strCommand & vbCrLf
	objSpssApp.ExecuteCommands strCommand, True

    strCommand = "ROC Score_" & NP & "_" & Trim(Str(PLIEGUE)) & "_2 BY " & OBJETIVO & " (1)"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "/PLOT=CURVE(REFERENCE)"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "/CRITERIA=CUTOFF(INCLUDE) TESTPOS(LARGE) DISTRIBUTION(FREE) CI(95)"
	strCommand = strCommand & vbCrLf
	strCommand = strCommand & "/MISSING=EXCLUDE."
	objSpssApp.ExecuteCommands strCommand, True

	strCommand = "NPAR TESTS"
	strCommand = strCommand & vbCrLf
   	strCommand = strCommand & "/K-S= Score_" & NP & "_" & Trim(Str(PLIEGUE)) & "_1 BY " & OBJETIVO & "(1 0)"
	strCommand = strCommand & vbCrLf
   	strCommand = strCommand & "/MISSING ANALYSIS."
	strCommand = strCommand & vbCrLf
    strCommand = strCommand & "EXECUTE."
	objSpssApp.ExecuteCommands strCommand, True

  	strCommand = "Split FILE Off."
	strCommand = strCommand & vbCrLf
    strCommand = strCommand & "EXECUTE."
	strCommand = strCommand & vbCrLf
	objSpssApp.ExecuteCommands strCommand, True
End Sub
	 */
}
	
	
	/**
	 * arbol version 2 es seteando las labels, seteando limites de crecimiento , padres hijos separacion 70/30 y seteando los costes y variable objetivo
	 * @param origenDatosSav
	 * @param outputFolder
	 * @return
	 * @throws Exception 
	 */
	public static String[] comandoArbolVersion2(String origenDatosSav, String outputFolder ){
		
		
		
		String seed= "12345";
		String timeStamp = getTimeStamp(null,null);
		
		String carpetaOutput = outputFolder+timeStamp;
		File carpetaOut = new File(carpetaOutput);
		if (carpetaOut.mkdir()) {
			String fileXmlTest = carpetaOutput+"/"+"_test_model.xml";
			String fileXmlTrain = carpetaOutput+"/"+"_train_model.xml";
			String fileSql = carpetaOutput+"/"+"_reglas.sql";
			String outFileName = carpetaOutput+"/"+"output.html";
			
			String[] result = {
					"OMS",
	                "/DESTINATION FORMAT=HTML OUTFILE='"+outFileName+"'.",
					"GET",
				"  FILE='"+origenDatosSav+"'.",
				"DATASET NAME DataSet1 WINDOW=FRONT.",
				"* Define Variable Properties.",
				"*clase.",
				"VALUE LABELS clase",
				"  'BAJA+1                  ' 'NEGATIVO'",
				"  'BAJA+2                  ' 'POSITIVO'",
				"  'CONTINUA                ' 'NEGATIVO'.",
				"EXECUTE.",
				"SET SEED="+seed+".",
				"* Decision Tree.",
				"TREE clase [n] BY numero_de_cliente [n] foto_mes [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [n] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete_premium [n] tpaquete2 [n] tpaquete3 [n] tpaquete4 [n] tpaquete5 [n] tpaquete6 [n] tpaquete7 [n] tpaquete8 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s]",
				"mcuenta_corriente_Paquete [s] mcuenta_corriente_dolares [n] tcaja_ahorro [n] mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [n] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n]",
				"mprestamos_prendarios [s] cprestamos_hipotecarios [n] mprestamos_hipotecarios [s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] mbonos_gobierno [n] mmonedas_extranjeras [n] minversiones_otras [n] tplan_sueldo [n] mplan_sueldo [s]",
				"mplan_sueldo_manual [s] cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n] mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [n] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s]",
				"mtarjeta_master_descuentos [s] ccuenta_descuentos [n] mcuenta_descuentos [n] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n] ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s]",
				"mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados [s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s]",
				"ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [s] Master_mfinanciacion_limite [s] Master_Fvencimiento [s] Master_Finiciomora [s] Master_msaldototal [s] Master_msaldopesos [s] Master_msaldodolares [s] Master_mconsumospesos [s] Master_mconsumosdolares [s] Master_mlimitecompra [s] Master_madelantopesos [s] Master_madelantodolares [n] Master_fultimo_cierre [s] Master_mpagado [s] Master_mpagospesos [s] Master_mpagosdolares [s]",
				"Master_fechaalta [s] Master_mconsumototal [s] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [s] Visa_marca_atraso [n] Visa_cuenta_estado [s] Visa_mfinanciacion_limite [s] Visa_Fvencimiento [s] Visa_Finiciomora [s] Visa_msaldototal [s] Visa_msaldopesos [s] Visa_msaldodolares [s] Visa_mconsumospesos [s] Visa_mconsumosdolares [s] Visa_mlimitecompra [s] Visa_madelantopesos [s] Visa_madelantodolares [s] Visa_fultimo_cierre [s] Visa_mpagado [s] Visa_mpagospesos [s] Visa_mpagosdolares [s]",
				"Visa_fechaalta [s] Visa_mconsumototal [s] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [s] participa [n]",
				"  /TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
				"  /DEPCATEGORIES USEVALUES=['BAJA+1' 'BAJA+2' 'CONTINUA'] TARGET=['BAJA+2']",
				"  /PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE",
				"  /GAIN SUMMARYTABLE=YES CATEGORYTABLE=YES TYPE=[NODE] SORT=DESCENDING CUMULATIVE=NO",
				"  /RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+fileSql+"'",
				"  /SAVE NODEID PREDVAL PREDPROB ASSIGNMENT",
				"  /METHOD TYPE=CHAID",
				"  /GROWTHLIMIT MAXDEPTH=6 MINPARENTSIZE=350 MINCHILDSIZE=245",
				"  /VALIDATION TYPE=SPLITSAMPLE(70.00) OUTPUT=BOTHSAMPLES",
				"  /CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10",
				"  /COSTS CUSTOM= 'BAJA+1' 'BAJA+1' [0] 'BAJA+1' 'BAJA+2' [200] 'BAJA+1' 'CONTINUA' [0]  'BAJA+2' 'BAJA+1' [7800] 'BAJA+2' 'BAJA+2' [0] 'BAJA+2' 'CONTINUA' [7800]  'CONTINUA' 'BAJA+1' [0] 'CONTINUA' 'BAJA+2' [200] 'CONTINUA' 'CONTINUA' [0]",
				"  /PROFITS CUSTOM='BAJA+1' [0 200] 'BAJA+2' [8000 200] 'CONTINUA' [0 200]",
				"  /OUTFILE TRAININGMODEL='"+fileXmlTrain+"' TESTMODEL='"+fileXmlTest+"'",
				"  /MISSING NOMINALMISSING=MISSING.",
				
				"OMSEND."
			};
			return result;
		}
		throw new RuntimeException("no sepudo crear la carpeta: "+carpetaOutput);
	}
	
	public static void pruebaEjecucionSpssArbolVersion2(){
		
		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/";
		
        String[] commands= comandoArbolVersion2("C:/Users/julio/Desktop/dmf_wd/nulos/DataSetAbril2015.sav",outFolder);
		
		try {
			StatsUtil.start();
			StatsUtil.submit(commands);
			StatsUtil.stop();
		} catch (StatsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * para poder ejecutar comandos spss desde java es necesario poner en el
	 * classpath la carpeta donde esta el java plugin. generalmente: 
	 * C:\Program Files\IBM\SPSS\Statistics\21
	 */
	public static void pruebaEjecucionSpss(){
		
		String command = "SET SEED 12345.";
		String commandInit = "SET SEED 12345.";
//		String outFolder = "/output/demo.html";
		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/demo.html";
//		String outFolder = "C:/Users/Public/dev/demo.html";
		
        String[] commands={"OMS",
                "/DESTINATION FORMAT=HTML OUTFILE='"+outFolder+"'.",
                "DATA LIST FREE /salary (F).",
                "BEGIN DATA",
                "21450",
                "30000",
                "57000",
                "END DATA.",
                "DESCRIPTIVES salary.",
                "OMSEND."};
		
		try {
			StatsUtil.start();
			StatsUtil.submit(commands);
			StatsUtil.submit(command);
			StatsUtil.stop();
		} catch (StatsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * paso 0 del proceso: 
	 * la idea aca es tener el proceso automatizado en todas sus pasos
	 */
	private void levantarDatos() {
		
	}
	
	/**
	 * paso 1 del proceso: 
	 * la idea aca es tener el proceso automatizado en todas sus pasos
	 */
	private void limpiarDatos() {
		
	}
	
	
	public static String getTimeStamp(Date date, String format) {
		Date defaultDate = (date != null) ? date : new Date();
		String defaulFormat = (format != null) ? format : "yyyyMMdd_HHmmss";

		SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
		return lSDF.format(defaultDate);
	}	
	
}
