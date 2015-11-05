package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.model.NodoResultadoTablaNormalizada;

import com.ibm.statistics.plugin.StatsException;
import com.ibm.statistics.plugin.StatsUtil;

/**
 * la idea de esta clase es automatizar las corridas del arbol
 * la idea aca es tener el proceso automatizado en todas sus pasos
 * paso 0: levantar datos > ya viene limpio
 * paso 1: limpiar datos  > ya viene limpio
 * paso 2: agregar datos  >ya viene agregado
 * paso 3: configurar algoritmo > decirle de donde levantar datos y configs especificas > viende spss
 * paso 4: ejecutar algoritmo   > tomarle el tiempo
 * paso 5: guardar resultados de algoritmo > generar resultados de la corrida especifica
 * paso 6: evaulacion de fin de corridas de algoritmo > generar resultados masivos
 * paso 7a: si fin de corridas: hacer resumen de resultados totales
 * paso 7b: si no fin de corridas: configurar algo con nuevos parametros ( paso 3 )
 * paso -1: prueba de ejecucion de spss desde java  
 * @author julio
 *
 */
public class AutomatizarCorridasArbolJulio {

	private static final String TEST = "Test";
	private static final String TRAINING = "Training";


	public static AutomatizarCorridasArbolJulioResultado ejecucionArbolVersionParametrizadoArbolNoVarParaPaq1(AutomatizarCorridasArbolJulioConfig configArbol,String[] commands) {

		configArbol.setComandoSPSS(commands);
		
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		resultado.setTiempoInicioEjecucion(new Date());
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol;
		try {
			nodosArbol = getElementsForArbolTrainTest(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultado.setTiempoFin(new Date());
		return resultado ;
	}
	

	public static AutomatizarCorridasArbolJulioResultado ejecucionArbol(AutomatizarCorridasArbolJulioConfig configArbol,String[] commands,ParseNodosType tipo) {

		configArbol.setComandoSPSS(commands);
		
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		resultado.setTiempoInicioEjecucion(new Date());
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol = null;
		try {
			switch (tipo) {
				case DEFAULT: getElementsForArbolDefault(configArbol);break;
				case TRAINTEST7030: nodosArbol = getElementsForArbolTrainTest(configArbol);break;
	
				default: throw new Exception("No Implementado este tipo de Parseo de elementos");
			}
			
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultado.setTiempoFin(new Date());
		return resultado ;
	}
	
	public static AutomatizarCorridasArbolJulioResultado ejecucionArbolVersionParametrizadoArbolNoVarParaPaq1(AutomatizarCorridasArbolJulioConfig configArbol) {
//		String[] commands= comandoArbolVersionParametrizadoArbolNoVarParaPaq1(configArbol);
		String[] commands= comandoArbolVersionParametrizadoArbolNoVarParaPaq1Mezclado(configArbol);
		configArbol.setComandoSPSS(commands);
		
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		resultado.setTiempoInicioEjecucion(new Date());
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol;
		try {
			nodosArbol = getElementsForArbolTrainTest(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultado.setTiempoFin(new Date());
		return resultado ;
	}
	
	public static String[] comandoArbolVersionParametrizadoArbolNoVarParaPaq1Mezclado(AutomatizarCorridasArbolJulioConfig configArbol ){

		String timeStamp = getTimeStamp(null,null);
		configArbol.setTimeStampFolder(timeStamp);
		String carpetaOutput = configArbol.getOutputFolder()+timeStamp;
		File carpetaOut = new File(carpetaOutput);
		if (carpetaOut.mkdirs()) {
			String fileXmlTest = carpetaOutput+"/"+"_test_model.xml";
			String fileXmlTrain = carpetaOutput+"/"+"_train_model.xml";
			String fileSql = carpetaOutput+"/"+"_reglas.sql";
			String outFileName = carpetaOutput+"/"+"output.html";
			String inputFile = configArbol.getOrigenDatosSav();
			
			String[] result = {
				"OMS",
                "/DESTINATION FORMAT=HTML OUTFILE='"+outFileName+"'.",
				"GET",
				"FILE='"+inputFile+"'.",
				"DATASET NAME DataSet1 WINDOW=FRONT.",
				"SET SEED="+configArbol.getSeed()+".",
				"* Decision Tree.",
				"TREE clase [n] BY numero_de_cliente [n] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [s] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete2 [n] tpaquete4 [n] tpaquete6 [n] tpaquete7 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete [s] mcuenta_corriente_dolares [s] tcaja_ahorro [n]",
				"mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [s] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [s] mprestamos_prendarios [s] cprestamos_hipotecarios [s] mprestamos_hipotecarios ",
				"[s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s] cplan_sueldo_transaccion [s] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n]",
				"mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [s] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s] mtarjeta_master_descuentos [s] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n]",
				"ccambio_monedas_compra [s] mcambio_monedas_compra [s] ccambio_monedas_venta [s] mcambio_monedas_venta [s] ctransferencias_recibidas [s] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s] mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [s] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados",
				"[s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [s] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s] ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [n] Master_mfinanciacion_limite [n] Master_Fvencimiento [n] Master_Finiciomora [n]",
				"Master_msaldototal [n] Master_msaldopesos [n] Master_msaldodolares [n] Master_mconsumospesos [n] Master_mconsumosdolares [n] Master_mlimitecompra [n] Master_madelantopesos [n] Master_madelantodolares [n] Master_fultimo_cierre [n] Master_mpagado [n] Master_mpagospesos [n] Master_mpagosdolares [n] Master_fechaalta [n] Master_mconsumototal [n] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [n] Visa_marca_atraso [n] Visa_cuenta_estado [n] Visa_mfinanciacion_limite [n] Visa_Fvencimiento",
				"[n] Visa_Finiciomora [n] Visa_msaldototal [n] Visa_msaldopesos [n] Visa_msaldodolares [n] Visa_mconsumospesos [n] Visa_mconsumosdolares [n] Visa_mlimitecompra [n] Visa_madelantopesos [n] Visa_madelantodolares [n] Visa_fultimo_cierre [n] Visa_mpagado [n] Visa_mpagospesos [n] Visa_mpagosdolares [n] Visa_fechaalta [n] Visa_mconsumototal [n] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [n] participa [n]",
				"/TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
				"/DEPCATEGORIES USEVALUES=[VALID]",
				"/PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE",
				"/RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+fileSql+"'",
				"/SAVE NODEID PREDVAL PREDPROB",
				"/METHOD TYPE="+configArbol.getTipoArbol(),
				"/GROWTHLIMIT MAXDEPTH="+configArbol.getMaximaProfundidad()+" MINPARENTSIZE="+configArbol.getMinParentSize()+"  MINCHILDSIZE="+configArbol.getMinChildSize(),
				"/VALIDATION TYPE=NONE OUTPUT=BOTHSAMPLES",
				"/CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10",
				"/COSTS EQUAL",
				"/OUTFILE TRAININGMODEL='"+fileXmlTrain+"' TESTMODEL='"+fileXmlTest+"'",
				"/MISSING NOMINALMISSING=MISSING.",
				"OMSEND."
			};
			return result;
		}
		throw new RuntimeException("no sepudo crear la carpeta: "+carpetaOutput);
	}
	
	
	public static String[] comandoArbolVersionParametrizadoArbolNoVarParaPaq1(AutomatizarCorridasArbolJulioConfig configArbol ){

		String timeStamp = getTimeStamp(null,null);
		configArbol.setTimeStampFolder(timeStamp);
		String carpetaOutput = configArbol.getOutputFolder()+timeStamp;
		File carpetaOut = new File(carpetaOutput);
		if (carpetaOut.mkdirs()) {
			String fileXmlTest = carpetaOutput+"/"+"_test_model.xml";
			String fileXmlTrain = carpetaOutput+"/"+"_train_model.xml";
			String fileSql = carpetaOutput+"/"+"_reglas.sql";
			String outFileName = carpetaOutput+"/"+"output.html";

			String[] result = {
					"OMS",
	                "/DESTINATION FORMAT=HTML OUTFILE='"+outFileName+"'.",
					"GET",
				"  FILE='"+configArbol.getOrigenDatosSav()+"'.",
				"DATASET NAME DataSet1 WINDOW=FRONT.",
				"* Define Variable Properties.",
						"*clase.",
						"VARIABLE ROLE /TARGET  clase.",
						"VALUE LABELS clase",
						"  'BAJA+1  ' 'NEGATIVO'",
						"  'BAJA+2  ' 'POSITIVO'",
						"  'CONTINUA' 'NEGATIVO'.",
						"EXECUTE.",
						"COMPUTE AZAR_TRAIN=RV.UNIFORM(0,1).",
						"EXECUTE.",
						"IF  (AZAR_TRAIN  < 0.5) TRAIN=1.",
						"EXECUTE.",
						"SORT CASES  BY TRAIN.",
						"SPLIT FILE SEPARATE BY TRAIN.",
						"USE ALL.",
						"FILTER BY TRAIN.",
						"EXECUTE.",
						"* Decision Tree.",
						"TREE clase [n] BY numero_de_cliente [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [s] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete2 [n] tpaquete4 [n] tpaquete6 [n] tpaquete7 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete [s] mcuenta_corriente_dolares [s] tcaja_ahorro [n]",
						"mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [n] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s] cprestamos_hipotecarios [n] mprestamos_hipotecarios",
						"[s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s] cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n]",
						"mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [s] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s] mtarjeta_master_descuentos [s] ccomisiones_mantenimiento [n] mcomisiones_mantenimiento [n] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n]",
						"ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s] mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados",
						"[s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [s] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s] ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [n] Master_mfinanciacion_limite [n] Master_Fvencimiento [n] Master_Finiciomora [n]",
						"Master_msaldototal [n] Master_msaldopesos [n] Master_msaldodolares [n] Master_mconsumospesos [n] Master_mconsumosdolares [n] Master_mlimitecompra [n] Master_madelantopesos [n] Master_madelantodolares [n] Master_fultimo_cierre [n] Master_mpagado [n] Master_mpagospesos [n] Master_mpagosdolares [n] Master_fechaalta [n] Master_mconsumototal [n] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [n] Visa_marca_atraso [n] Visa_cuenta_estado [n] Visa_mfinanciacion_limite [n] Visa_Fvencimiento",
						"[n] Visa_Finiciomora [n] Visa_msaldototal [n] Visa_msaldopesos [n] Visa_msaldodolares [n] Visa_mconsumospesos [n] Visa_mconsumosdolares [n] Visa_mlimitecompra [n] Visa_madelantopesos [n] Visa_madelantodolares [n] Visa_fultimo_cierre [n] Visa_mpagado [n] Visa_mpagospesos [n] Visa_mpagosdolares [n] Visa_fechaalta [n] Visa_mconsumototal [n] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [n] participa [n]",
						"  /TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
						"  /DEPCATEGORIES USEVALUES=['BAJA+1' 'BAJA+2' 'CONTINUA']",
						"  /PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE",
						"  /GAIN SUMMARYTABLE=YES TYPE=[NODE] SORT=DESCENDING CUMULATIVE=NO",
						"  /RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+fileSql+"'",
						"  /SAVE PREDVAL PREDPROB ASSIGNMENT",
						"  /METHOD TYPE=CHAID",
						"  /GROWTHLIMIT MAXDEPTH=6 MINPARENTSIZE=350 MINCHILDSIZE=245",
						"  /VALIDATION TYPE=SPLITSAMPLE(70.00) OUTPUT=BOTHSAMPLES",
						"  /CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10",
						"  /COSTS CUSTOM= 'BAJA+1' 'BAJA+1' [0] 'BAJA+1' 'BAJA+2' [200] 'BAJA+1' 'CONTINUA' [1]  'BAJA+2' 'BAJA+1' [7800] 'BAJA+2' 'BAJA+2' [0] 'BAJA+2' 'CONTINUA' [7800]  'CONTINUA' 'BAJA+1' [0] 'CONTINUA' 'BAJA+2' [200] 'CONTINUA' 'CONTINUA' [0]",
						"  /PROFITS CUSTOM='BAJA+1' [0 200] 'BAJA+2' [8000 200] 'CONTINUA' [0 200]",
						"  /OUTFILE TRAININGMODEL='"+fileXmlTrain+"' TESTMODEL='"+fileXmlTest+"'",
						"  /MISSING NOMINALMISSING=MISSING.",
				
				"OMSEND."
			};
			return result;
		}
		throw new RuntimeException("no sepudo crear la carpeta: "+carpetaOutput);
	}
	
	/**
	 * par aparsear el resultado del arbol si ya se tiene una carpeta con corridas, configurarla en la configuracion.
	 * a diferencia si se ejecuta completo es que no va a tener cuanto duro.
	 * @param configArbol
	 */
	public static AutomatizarCorridasArbolJulioResultado parseResultadoDefault(AutomatizarCorridasArbolJulioConfig configArbol) {
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		try {
			List<NodoResultadoTablaNormalizada> nodosArbol = getElementsForArbolDefault(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return resultado;
	}
	
	public static AutomatizarCorridasArbolJulioResultado parseResultadoTrainTest(AutomatizarCorridasArbolJulioConfig configArbol) {
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		try {
			List<NodoResultadoTablaNormalizada> nodosArbol = getElementsForArbolTrainTest(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return resultado;
	}


	/**
	 * @param configArbol
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private static List<NodoResultadoTablaNormalizada> getElementsForArbolDefault(AutomatizarCorridasArbolJulioConfig configArbol) throws IOException, Exception {
		String outputhtml = configArbol.getOutputFolder()+configArbol.getTimeStampFolder()+"/"+"output.html";
		Document doc = Jsoup.parse(new File(outputhtml), "UTF-8");
		doc.getAllElements();
		
		List<NodoResultadoTablaNormalizada> nodosArbol = new ArrayList<NodoResultadoTablaNormalizada>();
		Set<Long> sonParental = new HashSet<Long>();
		
		List<Element>tablas = doc.getElementsByTag("table");
		for (Element element : tablas) {
			if (element.getElementsContainingText("Tree Table").size() > 0) {
				List<Element> tbodys = element.getElementsByTag("tbody");
				for (Element tbody : tbodys) {
					
					
					// si tiene mas de 1 registro
					if (tbody.getElementsByTag("tr").size() > 1) {
						List<Element> registros = tbody.getElementsByTag("tr");	
						
						//aca deberian llegar todos los TR
						// armar un string. 
						// muestra	nodo	BAJA+1N	BAJA+1Porcentaje	BAJA+2N	BAJA+2Porcentaje	CONTINUAN	CONTINUAPorcentaje	TotalN	TotalPorcentaje	Categoría pronosticada	Nodo parental	VIPVariable	VIPSig	VIPChi-cuadrado	VIPgl	VIPValores_de_segmentacion
						// en el caso default no hay muestra
						for (Element registro : registros) {
							String[] current = new String[17];
							int i = 0;
							current[i] = "";
							
							List<Element> campos = registro.children();
							for (Element campo : campos) {
								current[++i] = campo.childNodes().get(0).toString(); 
							}
//								System.out.println(Arrays.asList(current).toString());
							// junto los nodos
							NodoResultadoTablaNormalizada nodo = new NodoResultadoTablaNormalizada(current);
							
							// en el caso de lo que es default aca simplemente evaluo la proporcionde mayor a 2.5 % en baja +2
							if (nodo.getBaja2porc() > 2.5) {
								nodosArbol.add(nodo);
								sonParental.add(nodo.getNndoParental());
							}
							

//								nodo.getBaja2num();
						}
					}
				}
			}
		}
		
		
		
		// filtrar los que no sean nodos finales ! 
		// o sea que no aparezcan en la columna Nodo parental
		List<NodoResultadoTablaNormalizada> nodosArbolFinal = nodosArbol.stream().filter(nodo -> !sonParental.contains(nodo.getNodo())).collect(Collectors.toList());
		
		
		return nodosArbolFinal;
	}
	
	/**
	 * @param configArbol
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private static List<NodoResultadoTablaNormalizada> getElementsForArbolTrainTest(AutomatizarCorridasArbolJulioConfig configArbol) throws IOException, Exception {
		String outputhtml = configArbol.getOutputFolder()+configArbol.getTimeStampFolder()+"/"+"output.html";
		Document doc = Jsoup.parse(new File(outputhtml), "UTF-8");
		doc.getAllElements();
		
		List<NodoResultadoTablaNormalizada> nodosArbolTrain = new ArrayList<NodoResultadoTablaNormalizada>();
		
		List<NodoResultadoTablaNormalizada> nodosArbolTest = new ArrayList<NodoResultadoTablaNormalizada>();
		Set<Long> sonParental = new HashSet<Long>();
		List<Element>tablas = doc.getElementsByTag("table");
		for (Element element : tablas) {
			if (element.getElementsContainingText("Tree Table").size() > 0) {
				List<Element> tbodys = element.getElementsByTag("tbody");
				for (Element tbody : tbodys) {
					
					
					// si tiene mas de 1 registro
					if (tbody.getElementsByTag("tr").size() > 1) {
						List<Element> registros = tbody.getElementsByTag("tr");	
						
						//aca deberian llegar todos los TR
						// armar un string. 
						// muestra	nodo	BAJA+1N	BAJA+1Porcentaje	BAJA+2N	BAJA+2Porcentaje	CONTINUAN	CONTINUAPorcentaje	TotalN	TotalPorcentaje	Categoría pronosticada	Nodo parental	VIPVariable	VIPSig	VIPChi-cuadrado	VIPgl	VIPValores_de_segmentacion
						// en el caso default no hay muestra
						
						boolean currentTrain = true;
						
						for (Element registro : registros) {
							
							// por el TH de la tabla 
							if (registro.children().get(0).childNodes().get(0).toString().contains(TEST)) {
								currentTrain = false;
							}
							String[] current = new String[17];
							int i = 0;
//							current[i] = "";
							
							List<Element> campos = registro.children();
							for (Element campo : campos) {
								
								
								
								// si el texto NO tiene Test o Training
								if (!campo.childNodes().get(0).toString().contains(TEST) && !campo.childNodes().get(0).toString().contains(TRAINING)) {
									
									// si es el primer campo
									if (i==0) {
										if (currentTrain) {
											// train
											current[i] = TRAINING;
											// aumento 1 y sigo el valor que deberia tener
											i++;
											current[i] = campo.childNodes().get(0).toString();	
										}else{
											// test
											current[i] = TEST;	
											// aumento 1 y sigo el valor que deberia tener
											i++;
											current[i] = campo.childNodes().get(0).toString();	
										}
									}else{
										// sino el valor del campo
										current[i] = campo.childNodes().get(0).toString();	
									}
									
									
								}else{
									// si es el primer campo
									if (i==0) {
										if (currentTrain) {
											// train
											current[i] = TRAINING;	
										}else{
											// test
											current[i] = TEST;	
										}
									}else{
										// sino el valor del campo
										current[i] = campo.childNodes().get(0).toString();	
									}
									// si es train o test el valor del campo
									current[i] = campo.childNodes().get(0).toString();	
								}
								
								i++; 
							}
//								System.out.println(Arrays.asList(current).toString());
							// junto los nodos
							NodoResultadoTablaNormalizada nodo;
							try {
								nodo = new NodoResultadoTablaNormalizada(current);
							} catch (Exception e) {
								System.out.println(Arrays.toString(current));
								e.printStackTrace();
								throw new Exception(e);
								
							}
							
							// en el caso de lo que es default aca simplemente evaluo la proporcionde mayor a 2.5 % en baja +2
							// y ademas en este caso DEBE SER DE TRAINING
							if (nodo.getBaja2porc() > 2.5 && nodo.getMuestra().equals(TRAINING)) {
								nodosArbolTrain.add(nodo);
								sonParental.add(nodo.getNndoParental());
							}
							// si es de test la agrego igual , y luego voy a depurar los ids de nodos que esten en training
							if (nodo.getMuestra().equals(TEST)) {
								nodosArbolTest.add(nodo);
							}
							

//								nodo.getBaja2num();
						}
					}
				}
			}
		}
		
		// a esta altura levante todos los nodos que me interesan
		List<NodoResultadoTablaNormalizada> nodosFiltradosPorSerParental = nodosArbolTrain.stream().filter(nodo -> !sonParental.contains(nodo.getNodo())).collect(Collectors.toList());
		
		List<NodoResultadoTablaNormalizada> resultado = new ArrayList<NodoResultadoTablaNormalizada>();
//		aca debe haber un segundo filtro que lo que hace es de los nodos restantes de train los busca en testing
		for (NodoResultadoTablaNormalizada nodoTrainFiltrados : nodosFiltradosPorSerParental) {
			for (NodoResultadoTablaNormalizada nodoTest : nodosArbolTest) {
				if (nodoTest.getNodo().equals(nodoTrainFiltrados.getNodo())) {
					resultado.add(nodoTest);
				}
			}
		}
		
		return resultado;
	}
	/**
	 * para hacer lac orrida default
	 * @param configArbol solo toma las propiedades dec onfiguracio: output folder, datos origen sav.
	 * @return
	 */
	public static AutomatizarCorridasArbolJulioResultado ejecucionArbol7030historia(AutomatizarCorridasArbolJulioConfig configArbol){
		
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		String[] commands= comandoArbolVersion7030Historia(configArbol);
		
		resultado.setTiempoInicioEjecucion(new Date());
		
		for (String string : commands) {
			System.out.println(string);
		}
		
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		resultado.setTiempoFin(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol;
		try {
			nodosArbol = getElementsForArbolDefault(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado ;
	}
	
	/**
	 * para hacer lac orrida default
	 * @param configArbol solo toma las propiedades dec onfiguracio: output folder, datos origen sav.
	 * @return
	 */
	public static AutomatizarCorridasArbolJulioResultado ejecucionArbolDefault(AutomatizarCorridasArbolJulioConfig configArbol){
		
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		String[] commands= comandoArbolDefault(configArbol);
		
		resultado.setTiempoInicioEjecucion(new Date());
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		resultado.setTiempoFin(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol;
		try {
			nodosArbol = getElementsForArbolDefault(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado ;
	}
	/**
	 * corrida base + setearle seed + export
	 * @param configArbol
	 * @return
	 */
	public static String[] comandoArbolDefault(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = getTimeStamp(null,null);
		configArbol.setTimeStampFolder(timeStamp);
		String carpetaOutput = configArbol.getOutputFolder()+timeStamp;
		File carpetaOut = new File(carpetaOutput);
		if (carpetaOut.mkdirs()) {
			String outputXML = carpetaOutput+"/"+"model.xml";
			String outputSQL = carpetaOutput+"/"+"reglas.sql";
			String outFileName = carpetaOutput+"/"+"output.html";
			
			String inputFile = configArbol.getOrigenDatosSav(); 
			
			String[] result = {
					"OMS",
	                "/DESTINATION FORMAT=HTML OUTFILE='"+outFileName+"'.",
					"GET",
					"FILE='"+inputFile+"'.",
					"DATASET NAME DataSet1 WINDOW=FRONT.",
					"SET SEED="+configArbol.getSeed()+".",
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
					"/TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
					"/DEPCATEGORIES USEVALUES=[VALID]",
					"/PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE",
					"/RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+outputSQL+"'",
					"/SAVE NODEID PREDVAL PREDPROB",
					"/METHOD TYPE=CHAID",
					"/GROWTHLIMIT MAXDEPTH=AUTO MINPARENTSIZE=100 MINCHILDSIZE=50",
					"/VALIDATION TYPE=NONE OUTPUT=BOTHSAMPLES",
					"/CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10",
					"/COSTS EQUAL",
					"/OUTFILE TRAININGMODEL='"+outputXML+"'",
					"/MISSING NOMINALMISSING=MISSING.",
					"OMSEND."};

			
			return result;
			
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}

	}
	public static String[] comandoArbolDefault2(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = getTimeStamp(null,null);
		configArbol.setTimeStampFolder(timeStamp);
		String carpetaOutput = configArbol.getOutputFolder()+timeStamp;
		File carpetaOut = new File(carpetaOutput);
		if (carpetaOut.mkdirs()) {
			String outputXML = carpetaOutput+"/"+"model.xml";
			String outputSQL = carpetaOutput+"/"+"reglas.sql";
			String outFileName = carpetaOutput+"/"+"output.html";
			
			String inputFile = configArbol.getOrigenDatosSav(); 
			
			String[] result = {
					"OMS",
	                "/DESTINATION FORMAT=HTML OUTFILE='"+outFileName+"'.",
					"GET",
					"FILE='"+inputFile+"'.",
					"DATASET NAME DataSet1 WINDOW=FRONT.",
					"SET SEED="+configArbol.getSeed()+".",
					"* Decision Tree.",
					"TREE clase [n] BY numero_de_cliente [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [s] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete2 [n] tpaquete4 [n] tpaquete6 [n] tpaquete7 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete [s] mcuenta_corriente_dolares [s] tcaja_ahorro [n]",
					"mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [n] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s] cprestamos_hipotecarios [n] mprestamos_hipotecarios",
					"[s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s] cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n]",
					"mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [s] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s] mtarjeta_master_descuentos [s] ccomisiones_mantenimiento [n] mcomisiones_mantenimiento [n] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n]",
					"ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s] mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados",
					"[s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [s] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s] ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [n] Master_mfinanciacion_limite [n] Master_Fvencimiento [n] Master_Finiciomora [n]",
					"Master_msaldototal [n] Master_msaldopesos [n] Master_msaldodolares [n] Master_mconsumospesos [n] Master_mconsumosdolares [n] Master_mlimitecompra [n] Master_madelantopesos [n] Master_madelantodolares [n] Master_fultimo_cierre [n] Master_mpagado [n] Master_mpagospesos [n] Master_mpagosdolares [n] Master_fechaalta [n] Master_mconsumototal [n] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [n] Visa_marca_atraso [n] Visa_cuenta_estado [n] Visa_mfinanciacion_limite [n] Visa_Fvencimiento",
					"[n] Visa_Finiciomora [n] Visa_msaldototal [n] Visa_msaldopesos [n] Visa_msaldodolares [n] Visa_mconsumospesos [n] Visa_mconsumosdolares [n] Visa_mlimitecompra [n] Visa_madelantopesos [n] Visa_madelantodolares [n] Visa_fultimo_cierre [n] Visa_mpagado [n] Visa_mpagospesos [n] Visa_mpagosdolares [n] Visa_fechaalta [n] Visa_mconsumototal [n] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [n] participa [n]",
					"/TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
					"/DEPCATEGORIES USEVALUES=[VALID]",
					"/PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE",
					"/RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+outputSQL+"'",
					"/SAVE NODEID PREDVAL PREDPROB",
					"/METHOD TYPE=CHAID",
					"/GROWTHLIMIT MAXDEPTH=AUTO MINPARENTSIZE=100 MINCHILDSIZE=50",
					"/VALIDATION TYPE=NONE OUTPUT=BOTHSAMPLES",
					"/CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10",
					"/COSTS EQUAL",
					"/OUTFILE TRAININGMODEL='"+outputXML+"'",
					"/MISSING NOMINALMISSING=MISSING.",
					"OMSEND."};

			
			return result;
			
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}

	}
	
	/**
	 * @param configArbol
	 */
	public static AutomatizarCorridasArbolJulioResultado ejecucionArbolVersionParametrizadoArbolNoChaidNoCampos(AutomatizarCorridasArbolJulioConfig configArbol) {
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		String[] commands= comandoArbolVersionParametrizadoArbolNoChaidNoCampos(configArbol);
		
		resultado.setTiempoInicioEjecucion(new Date());
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol;
		try {
			nodosArbol = getElementsForArbolTrainTest(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultado.setTiempoFin(new Date());
		return resultado ;
	}

	/**
	 * @param commands
	 */
	private static void ejecutarComandosSpss(String[] commands) {
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
	 * arbol version 2 es seteando las labels, seteando limites de crecimiento , padres hijos separacion 70/30 y seteando los costes y variable objetivo
	 * @param origenDatosSav
	 * @param outputFolder
	 * @return
	 * @throws Exception 
	 */
	public static String[] comandoArbolVersionParametrizadoArbolNoChaidNoCampos(AutomatizarCorridasArbolJulioConfig configArbol ){

		String timeStamp = getTimeStamp(null,null);
		configArbol.setTimeStampFolder(timeStamp);
		String campos = "numero_de_cliente [n] foto_mes [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [n] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete_premium [n] tpaquete2 [n] tpaquete3 [n] tpaquete4 [n] tpaquete5 [n] tpaquete6 [n] tpaquete7 [n] tpaquete8 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s]"+
		" mcuenta_corriente_Paquete [s] mcuenta_corriente_dolares [n] tcaja_ahorro [n] mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [n] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n]"+
		" mprestamos_prendarios [s] cprestamos_hipotecarios [n] mprestamos_hipotecarios [s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] mbonos_gobierno [n] mmonedas_extranjeras [n] minversiones_otras [n] tplan_sueldo [n] mplan_sueldo [s]"+
		" mplan_sueldo_manual [s] cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n] mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [n] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s]"+
		" mtarjeta_master_descuentos [s] ccuenta_descuentos [n] mcuenta_descuentos [n] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n] ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s]"+
		" mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados [s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s]"+
		" ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [s] Master_mfinanciacion_limite [s] Master_Fvencimiento [s] Master_Finiciomora [s] Master_msaldototal [s] Master_msaldopesos [s] Master_msaldodolares [s] Master_mconsumospesos [s] Master_mconsumosdolares [s] Master_mlimitecompra [s] Master_madelantopesos [s] Master_madelantodolares [n] Master_fultimo_cierre [s] Master_mpagado [s] Master_mpagospesos [s] Master_mpagosdolares [s]"+
		" Master_fechaalta [s] Master_mconsumototal [s] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [s] Visa_marca_atraso [n] Visa_cuenta_estado [s] Visa_mfinanciacion_limite [s] Visa_Fvencimiento [s] Visa_Finiciomora [s] Visa_msaldototal [s] Visa_msaldopesos [s] Visa_msaldodolares [s] Visa_mconsumospesos [s] Visa_mconsumosdolares [s] Visa_mlimitecompra [s] Visa_madelantopesos [s] Visa_madelantodolares [s] Visa_fultimo_cierre [s] Visa_mpagado [s] Visa_mpagospesos [s] Visa_mpagosdolares [s]"+
		" Visa_fechaalta [s] Visa_mconsumototal [s] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [s] participa [n]";
		
		String carpetaOutput = configArbol.getOutputFolder()+timeStamp;
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
				"  FILE='"+configArbol.getOrigenDatosSav()+"'.",
				"DATASET NAME DataSet1 WINDOW=FRONT.",
				"* Define Variable Properties.",
				"*clase.",
				"VALUE LABELS clase",
				"  'BAJA+1                  ' 'NEGATIVO'",
				"  'BAJA+2                  ' 'POSITIVO'",
				"  'CONTINUA                ' 'NEGATIVO'.",
				"EXECUTE.",
				"SET SEED="+configArbol.getSeed()+".",
				"* Decision Tree.",
				"TREE clase [n] BY ",campos,

				"  /TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
				"  /DEPCATEGORIES USEVALUES=['BAJA+1' 'BAJA+2' 'CONTINUA'] TARGET=['BAJA+2']",
				"  /PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE",
				"  /GAIN SUMMARYTABLE=YES CATEGORYTABLE=YES TYPE=[NODE] SORT=DESCENDING CUMULATIVE=NO",
				"  /RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+fileSql+"'",
				"  /SAVE NODEID PREDVAL PREDPROB ASSIGNMENT",
				"  /METHOD TYPE="+configArbol.getTipoArbol(),
				"  /GROWTHLIMIT MAXDEPTH="+configArbol.getMaximaProfundidad()+" MINPARENTSIZE="+configArbol.getMinParentSize()+" MINCHILDSIZE="+configArbol.getMinChildSize(),
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

	public static String[] comandoArbolVersion7030Historia(AutomatizarCorridasArbolJulioConfig configArbol ){

		String timeStamp = getTimeStamp(null,null);
		configArbol.setTimeStampFolder(timeStamp);
		String campos1 =" numero_de_cliente [s] foto_mes [s] marketing_activo_ultimos90dias [s] cliente_vip [s] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [s] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete1 [n] tpaquete2 [n] tpaquete3 [n] tpaquete4 [n] tpaquete5 [n] tpaquete6 [n] tpaquete7 [n] tpaquete8 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete [s] ";
		String campos2 =" mcuenta_corriente_dolares [n] tcaja_ahorro [n] mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [n] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s]";
		String campos3 =" cprestamos_hipotecarios [n] mprestamos_hipotecarios [s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] mbonos_gobierno [n] mmonedas_extranjeras [n] minversiones_otras [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s]";
		String campos4 =" cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n] mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [n] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s]";
		String campos5 =" mtarjeta_master_descuentos [s] ccuenta_descuentos [n] mcuenta_descuentos [n] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n] ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s]";
		String campos6 ="	mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados [s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s]";
		String campos7 =" ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [n] Master_mfinanciacion_limite [n] Master_Fvencimiento [n] Master_Finiciomora [n] Master_msaldototal [n] Master_msaldopesos [n] Master_msaldodolares [n] Master_mconsumospesos [n] Master_mconsumosdolares [n] Master_mlimitecompra [n] Master_madelantopesos [n] Master_madelantodolares [n] Master_fultimo_cierre [n] Master_mpagado [n] Master_mpagospesos [n] Master_mpagosdolares [n]";
		String campos8 =" Master_fechaalta [n] Master_mconsumototal [n] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [n] Visa_marca_atraso [n] Visa_cuenta_estado [n] Visa_mfinanciacion_limite [n] Visa_Fvencimiento [n] Visa_Finiciomora [n] Visa_msaldototal [n] Visa_msaldopesos [n] Visa_msaldodolares [n] Visa_mconsumospesos [n] Visa_mconsumosdolares [n] Visa_mlimitecompra [n] Visa_madelantopesos [n] Visa_madelantodolares [n] Visa_fultimo_cierre [n] Visa_mpagado [n] Visa_mpagospesos [n] Visa_mpagosdolares [n]";
		String campos9 =" Visa_fechaalta [n] Visa_mconsumototal [n] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [n] participa [n] cliente_edad_MAX [s] cliente_edad_MIN [s] cliente_edad_AVG [n] Master_mfinanciacion_limite_MAX [n] Master_mfinanciacion_limite_MIN [n] Master_mfinanciacion_limite_AVG [n] Visa_mconsumototal_MAX [n] Visa_mconsumototal_MIN [n] Visa_mconsumototal_AVG [n] cplan_sueldo_transaccion_MAX [n] cplan_sueldo_transaccion_MIN [n] cplan_sueldo_transaccion_AVG [n] Master_fechaalta_MAX [n]";
		String campos10 =" Master_fechaalta_MIN [n] Master_fechaalta_AVG [n] ccajeros_propios_descuentos_MAX [s] ccajeros_propios_descuentos_MIN [n] ccajeros_propios_descuentos_AVG [n] Master_madelantodolares_MAX [n] Master_madelantodolares_MIN [n] Master_madelantodolares_AVG [n] tpaquete3_MAX [n] tpaquete3_MIN [n] tpaquete3_AVG [n] mmonedas_extranjeras_MAX [n] mmonedas_extranjeras_MIN [n] mmonedas_extranjeras_AVG [n] tpaquete4_MAX [n] tpaquete4_MIN [n] tpaquete4_AVG [n] tpaquete1_MAX [n] tpaquete1_MIN [n] tpaquete1_AVG [n]";
		String campos11 =" tpaquete2_MAX [n] tpaquete2_MIN [n] tpaquete2_AVG [n] ccomisiones_mantenimiento_MAX [s] ccomisiones_mantenimiento_MIN [n] ccomisiones_mantenimiento_AVG [n] ccallcenter_transacciones_MAX [s] ccallcenter_transacciones_MIN [n] ccallcenter_transacciones_AVG [n] ttarjeta_visa_MAX [n] ttarjeta_visa_MIN [n] ttarjeta_visa_AVG [n] tseguro_accidentes_personales_MAX [n] tseguro_accidentes_personales_MIN [n] tseguro_accidentes_personales_AVG [n] cliente_antiguedad_MAX [s] cliente_antiguedad_MIN [s]";
		String campos12 =" cliente_antiguedad_AVG [n] Master_msaldodolares_MAX [n] Master_msaldodolares_MIN [n] Master_msaldodolares_AVG [n] tpaquete9_MAX [n] tpaquete9_MIN [n] tpaquete9_AVG [n] tpaquete7_MAX [n] tpaquete7_MIN [n] tpaquete7_AVG [n] participa_MAX [n] participa_MIN [n] participa_AVG [n] tpaquete8_MAX [n] tpaquete8_MIN [n] tpaquete8_AVG [n] Visa_mpagosdolares_MAX [n] Visa_mpagosdolares_MIN [n] Visa_mpagosdolares_AVG [n] tpaquete5_MAX [n] tpaquete5_MIN [n] tpaquete5_AVG [n] tpaquete6_MAX [n] tpaquete6_MIN [n] ";
		String campos13 =" tpaquete6_AVG [n] cprestamos_hipotecarios_MAX [n] cprestamos_hipotecarios_MIN [n] cprestamos_hipotecarios_AVG [n] mcheques_depositados_MAX [s] mcheques_depositados_MIN [s] mcheques_depositados_AVG [n] mdescubierto_preacordado_MAX [n] mdescubierto_preacordado_MIN [n] mdescubierto_preacordado_AVG [n] ccheques_emitidos_MAX [s] ccheques_emitidos_MIN [n] ccheques_emitidos_AVG [n] mtransferencias_emitidas_MAX [s] mtransferencias_emitidas_MIN [s] mtransferencias_emitidas_AVG [n] mtarjeta_visa_descuentos_MAX [s] ";
		String campos14 =" mtarjeta_visa_descuentos_MIN [n] mtarjeta_visa_descuentos_AVG [n] Master_Fvencimiento_MAX [n] Master_Fvencimiento_MIN [n] Master_Fvencimiento_AVG [n] mcajeros_ajenos_MAX [s] mcajeros_ajenos_MIN [s] mcajeros_ajenos_AVG [n] Visa_msaldototal_MAX [n] Visa_msaldototal_MIN [n] Visa_msaldototal_AVG [n] tseguro_vivienda_MAX [n] tseguro_vivienda_MIN [n] tseguro_vivienda_AVG [n] mpagodeservicios_MAX [s] mpagodeservicios_MIN [s] mpagodeservicios_AVG [n] tcuenta_corriente_MAX [n] tcuenta_corriente_MIN [n] ";
		String campos15 =" tcuenta_corriente_AVG [n] Visa_madelantopesos_MAX [n] Visa_madelantopesos_MIN [n] Visa_madelantopesos_AVG [n] ctarjeta_master_descuentos_MAX [s] ctarjeta_master_descuentos_MIN [n] ctarjeta_master_descuentos_AVG [n] mactivos_margen_MAX [s] mactivos_margen_MIN [s] mactivos_margen_AVG [n] Visa_cuenta_estado_MAX [n] Visa_cuenta_estado_MIN [n] Visa_cuenta_estado_AVG [n] mcheques_depositados_rechazados_MAX [s] mcheques_depositados_rechazados_MIN [n] mcheques_depositados_rechazados_AVG [n] tcaja_ahorro_MAX [n] ";
		String campos16 =" tcaja_ahorro_MIN [n] tcaja_ahorro_AVG [n] mcaja_ahorro_Paquete_MAX [s] mcaja_ahorro_Paquete_MIN [s] mcaja_ahorro_Paquete_AVG [n] mtarjeta_visa_consumo_MAX [s] mtarjeta_visa_consumo_MIN [s] mtarjeta_visa_consumo_AVG [n] tplan_sueldo_MAX [n] tplan_sueldo_MIN [n] tplan_sueldo_AVG [n] mtitulos_MAX [s] mtitulos_MIN [s] mtitulos_AVG [n] Visa_mlimitecompra_MAX [n] Visa_mlimitecompra_MIN [n] Visa_mlimitecompra_AVG [n] mcuenta_descuentos_MAX [n] mcuenta_descuentos_MIN [n] mcuenta_descuentos_AVG [n] ";
		String campos17 =" tcambio_monedas_MAX [n] tcambio_monedas_MIN [n] tcambio_monedas_AVG [n] Visa_Finiciomora_MAX [n] Visa_Finiciomora_MIN [n] Visa_Finiciomora_AVG [n] Master_mpagado_MAX [n] Master_mpagado_MIN [n] Master_mpagado_AVG [n] tseguro_auto_MAX [n] tseguro_auto_MIN [n] tseguro_auto_AVG [n] Master_fultimo_cierre_MAX [n] Master_fultimo_cierre_MIN [n] Master_fultimo_cierre_AVG [n] mfondos_comunes_inversion_pesos_MAX [s] mfondos_comunes_inversion_pesos_MIN [s] mfondos_comunes_inversion_pesos_AVG [n] ";
		String campos18 =" ccheques_emitidos_rechazados_MAX [s] ccheques_emitidos_rechazados_MIN [n] ccheques_emitidos_rechazados_AVG [n] Master_marca_atraso_MAX [n] Master_marca_atraso_MIN [n] Master_marca_atraso_AVG [n] Visa_mconsumosdolares_MAX [n] Visa_mconsumosdolares_MIN [n] Visa_mconsumosdolares_AVG [n] ctarjeta_visa_descuentos_MAX [s] ctarjeta_visa_descuentos_MIN [n] ctarjeta_visa_descuentos_AVG [n] tcuentas_MAX [n] tcuentas_MIN [n] tcuentas_AVG [n] mcheques_emitidos_rechazados_MAX [s] mcheques_emitidos_rechazados_MIN [n] ";
		String campos19 =" mcheques_emitidos_rechazados_AVG [s] tcajas_extracciones_MAX [n] tcajas_extracciones_MIN [n] tcajas_extracciones_AVG [n] mplazo_fijo_pesos_MAX [s] mplazo_fijo_pesos_MIN [s] mplazo_fijo_pesos_AVG [n] Visa_fultimo_cierre_MAX [n] Visa_fultimo_cierre_MIN [n] Visa_fultimo_cierre_AVG [n] chomebanking_transacciones_MAX [s] chomebanking_transacciones_MIN [s] chomebanking_transacciones_AVG [n] Visa_msaldodolares_MAX [n] Visa_msaldodolares_MIN [n] Visa_msaldodolares_AVG [n] thomebanking_MAX [n] thomebanking_MIN [n] ";
		String campos20 =" thomebanking_AVG [n] tpagodeservicios_MAX [n] tpagodeservicios_MIN [n] tpagodeservicios_AVG [n] tmovimientos_ultimos90dias_MAX [n] tmovimientos_ultimos90dias_MIN [n] tmovimientos_ultimos90dias_AVG [n] mplan_sueldo_manual_MAX [s] mplan_sueldo_manual_MIN [s] mplan_sueldo_manual_AVG [s] mpagomiscuentas_MAX [s] mpagomiscuentas_MIN [s] mpagomiscuentas_AVG [n] tcaja_seguridad_MAX [n] tcaja_seguridad_MIN [n] tcaja_seguridad_AVG [n] tcajas_consultas_MAX [n] tcajas_consultas_MIN [n] tcajas_consultas_AVG [n]";
		String campos21 =" Master_Finiciomora_MAX [n] Master_Finiciomora_MIN [n] Master_Finiciomora_AVG [n] tseguro_vida_mercado_abierto_MAX [n] tseguro_vida_mercado_abierto_MIN [n] tseguro_vida_mercado_abierto_AVG [n] mttarjeta_master_debitos_automaticos_MAX [s] mttarjeta_master_debitos_automaticos_MIN [s] mttarjeta_master_debitos_automaticos_AVG [n] Master_mpagosdolares_MAX [n] Master_mpagosdolares_MIN [n] Master_mpagosdolares_AVG [n] mfondos_comunes_inversion_dolares_MAX [s] mfondos_comunes_inversion_dolares_MIN [s] ";
		String campos22 =" mfondos_comunes_inversion_dolares_AVG [s] Visa_fechaalta_MAX [n] Visa_fechaalta_MIN [n] Visa_fechaalta_AVG [n] Visa_tconsumos_MAX [n] Visa_tconsumos_MIN [n] Visa_tconsumos_AVG [n] cautoservicio_transacciones_MAX [n] cautoservicio_transacciones_MIN [n] cautoservicio_transacciones_AVG [n] mextraccion_autoservicio_MAX [s] mextraccion_autoservicio_MIN [s] mextraccion_autoservicio_AVG [n] Master_tadelantosefectivo_MAX [n] Master_tadelantosefectivo_MIN [n] Master_tadelantosefectivo_AVG [n] minversiones_otras_MAX [n]";
		String campos23 =" minversiones_otras_MIN [n] minversiones_otras_AVG [n] Visa_mpagominimo_MAX [n] Visa_mpagominimo_MIN [n] Visa_mpagominimo_AVG [n] tpagomiscuentas_MAX [n] tpagomiscuentas_MIN [n] tpagomiscuentas_AVG [n] tcallcenter_MAX [n] tcallcenter_MIN [n] tcallcenter_AVG [n] marketing_activo_ultimos90dias_MAX [n] marketing_activo_ultimos90dias_MIN [n] marketing_activo_ultimos90dias_AVG [n] mcuenta_corriente_Nopaquete_MAX [s] mcuenta_corriente_Nopaquete_MIN [s] mcuenta_corriente_Nopaquete_AVG [s] mpasivos_margen_MAX [s]";
		String campos24 =" mpasivos_margen_MIN [s] mpasivos_margen_AVG [n] ttarjeta_visa_debitos_automaticos_MAX [n] ttarjeta_visa_debitos_automaticos_MIN [n] ttarjeta_visa_debitos_automaticos_AVG [n] Visa_mfinanciacion_limite_MAX [n] Visa_mfinanciacion_limite_MIN [n] Visa_mfinanciacion_limite_AVG [n] Visa_tadelantosefectivo_MAX [n] Visa_tadelantosefectivo_MIN [n] Visa_tadelantosefectivo_AVG [n] Master_tconsumos_MAX [n] Master_tconsumos_MIN [n] Master_tconsumos_AVG [n] ccajeros_ajenos_transacciones_MAX [s]";
		String campos25 ="	ccajeros_ajenos_transacciones_MIN [s] ccajeros_ajenos_transacciones_AVG [n] mtarjeta_master_descuentos_MAX [s] mtarjeta_master_descuentos_MIN [n] mtarjeta_master_descuentos_AVG [n] tplazo_fijo_MAX [n] tplazo_fijo_MIN [n] tplazo_fijo_AVG [n] mplazo_fijo_dolares_MAX [s] mplazo_fijo_dolares_MIN [s] mplazo_fijo_dolares_AVG [n] ctarjeta_visa_transacciones_MAX [s] ctarjeta_visa_transacciones_MIN [s] ctarjeta_visa_transacciones_AVG [n] Visa_mpagado_MAX [n] Visa_mpagado_MIN [n] Visa_mpagado_AVG [n]";
		String campos26 ="	mbonos_gobierno_MAX [n] mbonos_gobierno_MIN [n] mbonos_gobierno_AVG [n] Master_mconsumosdolares_MAX [n] Master_mconsumosdolares_MIN [n] Master_mconsumosdolares_AVG [n] ccheques_depositados_rechazados_MAX [n] ccheques_depositados_rechazados_MIN [n] ccheques_depositados_rechazados_AVG [n] Visa_marca_atraso_MAX [n] Visa_marca_atraso_MIN [n] Visa_marca_atraso_AVG [n] mcheques_emitidos_MAX [s] mcheques_emitidos_MIN [n] mcheques_emitidos_AVG [n] ccheques_depositados_MAX [s] ccheques_depositados_MIN [n]";
		String campos27 =" ccheques_depositados_AVG [n] mcuenta_corriente_Paquete_MAX [s] mcuenta_corriente_Paquete_MIN [s] mcuenta_corriente_Paquete_AVG [n] cprestamos_prendarios_MAX [n] cprestamos_prendarios_MIN [n] cprestamos_prendarios_AVG [n] mcuenta_debitos_automaticos_MAX [s] mcuenta_debitos_automaticos_MIN [s] mcuenta_debitos_automaticos_AVG [n] ttarjeta_debito_MAX [n] ttarjeta_debito_MIN [n] ttarjeta_debito_AVG [n] tcajas_MAX [n] tcajas_MIN [n] tcajas_AVG [n] mprestamos_prendarios_MAX [s] mprestamos_prendarios_MIN [s]";
		String campos28 =" mprestamos_prendarios_AVG [s] ccomisiones_otras_MAX [s] ccomisiones_otras_MIN [n] ccomisiones_otras_AVG [n] mcaja_ahorro_dolares_MAX [s] mcaja_ahorro_dolares_MIN [s] mcaja_ahorro_dolares_AVG [n] mcomisiones_MAX [s] mcomisiones_MIN [s] mcomisiones_AVG [n] ccuenta_descuentos_MAX [n] ccuenta_descuentos_MIN [n] ccuenta_descuentos_AVG [n] tautoservicio_MAX [n] tautoservicio_MIN [n] tautoservicio_AVG [n] cliente_vip_MAX [n] cliente_vip_MIN [n] cliente_vip_AVG [n] ccambio_monedas_compra_MAX [n] ";
		String campos29 =" ccambio_monedas_compra_MIN [n] ccambio_monedas_compra_AVG [n] ccambio_monedas_venta_MAX [n] ccambio_monedas_venta_MIN [n] ccambio_monedas_venta_AVG [n] Master_mlimitecompra_MAX [n] Master_mlimitecompra_MIN [n] Master_mlimitecompra_AVG [n] Visa_mpagospesos_MAX [n] Visa_mpagospesos_MIN [n] Visa_mpagospesos_AVG [n] mcambio_monedas_venta_MAX [s] mcambio_monedas_venta_MIN [s] mcambio_monedas_venta_AVG [n] Master_madelantopesos_MAX [n] Master_madelantopesos_MIN [n] Master_madelantopesos_AVG [n] mautoservicio_MAX [s]";
		String campos30 =" mautoservicio_MIN [s] mautoservicio_AVG [n] mplan_sueldo_MAX [s] mplan_sueldo_MIN [s] mplan_sueldo_AVG [n] Visa_msaldopesos_MAX [n] Visa_msaldopesos_MIN [n] Visa_msaldopesos_AVG [n] mttarjeta_visa_debitos_automaticos_MAX [s] mttarjeta_visa_debitos_automaticos_MIN [s] mttarjeta_visa_debitos_automaticos_AVG [n] ctarjeta_debito_transacciones_MAX [s] ctarjeta_debito_transacciones_MIN [s] ctarjeta_debito_transacciones_AVG [n] ctransferencias_emitidas_MAX [s] ctransferencias_emitidas_MIN [s]";
		String campos31 =" ctransferencias_emitidas_AVG [n] mcuenta_corriente_dolares_MAX [n] mcuenta_corriente_dolares_MIN [n] mcuenta_corriente_dolares_AVG [n] tfondos_comunes_inversion_MAX [n] tfondos_comunes_inversion_MIN [n] tfondos_comunes_inversion_AVG [n] ttitulos_MAX [n] ttitulos_MIN [n] ttitulos_AVG [n] tcajas_depositos_MAX [n] tcajas_depositos_MIN [n] tcajas_depositos_AVG [n] Visa_madelantodolares_MAX [n] Visa_madelantodolares_MIN [n] Visa_madelantodolares_AVG [n] cliente_sucursal_MAX [s] cliente_sucursal_MIN [s]";
		String campos32 =" cliente_sucursal_AVG [n] cprestamos_personales_MAX [s] cprestamos_personales_MIN [s] cprestamos_personales_AVG [n] Master_mpagominimo_MAX [n] Master_mpagominimo_MIN [n] Master_mpagominimo_AVG [n] cextraccion_autoservicio_MAX [s] cextraccion_autoservicio_MIN [s] cextraccion_autoservicio_AVG [n] Visa_mconsumospesos_MAX [n] Visa_mconsumospesos_MIN [n] Visa_mconsumospesos_AVG [n] mcambio_monedas_compra_MAX [s] mcambio_monedas_compra_MIN [n] mcambio_monedas_compra_AVG [s] Master_mpagospesos_MAX [n]";
		String campos33 =" Master_mpagospesos_MIN [n] Master_mpagospesos_AVG [n] mprestamos_hipotecarios_MAX [s] mprestamos_hipotecarios_MIN [s] mprestamos_hipotecarios_AVG [n] Master_mconsumospesos_MAX [n] Master_mconsumospesos_MIN [n] Master_mconsumospesos_AVG [n] mtransferencias_recibidas_MAX [s] mtransferencias_recibidas_MIN [s] mtransferencias_recibidas_AVG [n] Master_msaldototal_MAX [n] Master_msaldototal_MIN [n] Master_msaldototal_AVG [n] Master_cuenta_estado_MAX [n] Master_cuenta_estado_MIN [n] Master_cuenta_estado_AVG [n]";
		String campos34 =" mrentabilidad_MAX [s] mrentabilidad_MIN [s] mrentabilidad_AVG [n] Master_msaldopesos_MAX [n] Master_msaldopesos_MIN [n] Master_msaldopesos_AVG [n] Master_mconsumototal_MAX [n] Master_mconsumototal_MIN [n] Master_mconsumototal_AVG [n] mcomisiones_otras_MAX [s] mcomisiones_otras_MIN [s] mcomisiones_otras_AVG [n] ttarjeta_master_MAX [n] ttarjeta_master_MIN [n] ttarjeta_master_AVG [n] ctransferencias_recibidas_MAX [n] ctransferencias_recibidas_MIN [n] ctransferencias_recibidas_AVG [n]";
		String campos35 =" mcaja_ahorro_Nopaquete_MAX [s] mcaja_ahorro_Nopaquete_MIN [s] mcaja_ahorro_Nopaquete_AVG [n] mrentabilidad_annual_MAX [n] mrentabilidad_annual_MIN [n] mrentabilidad_annual_AVG [n] marketing_coss_selling_MAX [n] marketing_coss_selling_MIN [n] marketing_coss_selling_AVG [n] ctarjeta_master_transacciones_MAX [s] ctarjeta_master_transacciones_MIN [s] ctarjeta_master_transacciones_AVG [n] mcajeros_propio_MAX [s] mcajeros_propio_MIN [s] mcajeros_propio_AVG [n] tcuenta_debitos_automaticos_MAX [n]";
		String campos36 =" tcuenta_debitos_automaticos_MIN [n] tcuenta_debitos_automaticos_AVG [n] Visa_Fvencimiento_MAX [n] Visa_Fvencimiento_MIN [n] Visa_Fvencimiento_AVG [n] mprestamos_personales_MAX [s] mprestamos_personales_MIN [s] mprestamos_personales_AVG [n] tcajas_otras_MAX [n] tcajas_otras_MIN [n] tcajas_otras_AVG [n] ttarjeta_master_debitos_automaticos_MAX [n] ttarjeta_master_debitos_automaticos_MIN [n] ttarjeta_master_debitos_automaticos_AVG [n] ccajeros_propio_transacciones_MAX [s] ccajeros_propio_transacciones_MIN [s]";
		String campos37 =" ccajeros_propio_transacciones_AVG [n] mtarjeta_master_consumo_MAX [s] mtarjeta_master_consumo_MIN [s] mtarjeta_master_consumo_AVG [n] mcuentas_saldo_MAX [s] mcuentas_saldo_MIN [s] mcuentas_saldo_AVG [n] mcomisiones_mantenimiento_MAX [s] mcomisiones_mantenimiento_MIN [s] mcomisiones_mantenimiento_AVG [n] mcajeros_propios_descuentos_MAX [s] mcajeros_propios_descuentos_MIN [s] mcajeros_propios_descuentos_AVG [n]";
		
		String carpetaOutput = configArbol.getOutputFolder()+timeStamp;
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
				"  FILE='"+configArbol.getOrigenDatosSav()+"'.",
				"DATASET NAME DataSet1 WINDOW=FRONT.",
				"* Define Variable Properties.",
				"*clase.",
				"VALUE LABELS clase",
				"  'BAJA+1                  ' 'NEGATIVO'",
				"  'BAJA+2                  ' 'POSITIVO'",
				"  'CONTINUA                ' 'NEGATIVO'.",
				"EXECUTE.",
				"SET SEED="+configArbol.getSeed()+".",
				"* Decision Tree.",
				"TREE clase [n] BY ",
				campos1, campos2, campos3, campos4, campos5, 
				campos6, campos7, campos8, campos9, campos10, 
				campos11, campos12, campos13, campos14, campos15, 
				campos16, campos17, campos18, campos19, campos20, 
				campos21, campos22, campos23, campos24, campos25, 
				campos26, campos27, campos28, campos29, campos30, 
				campos31, campos32, campos33, campos34, campos35, 
				campos36, campos37, 

				"  /TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
				"  /DEPCATEGORIES USEVALUES=['BAJA+1' 'BAJA+2' 'CONTINUA'] TARGET=['BAJA+2']",
				"  /PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE",
				"  /GAIN SUMMARYTABLE=YES CATEGORYTABLE=YES TYPE=[NODE] SORT=DESCENDING CUMULATIVE=NO",
				"  /RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+fileSql+"'",
				"  /SAVE NODEID PREDVAL PREDPROB ASSIGNMENT",
				"  /METHOD TYPE="+configArbol.getTipoArbol(),
				"  /GROWTHLIMIT MAXDEPTH="+configArbol.getMaximaProfundidad()+" MINPARENTSIZE="+configArbol.getMinParentSize()+" MINCHILDSIZE="+configArbol.getMinChildSize(),
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
	
	public static String getTimeStamp(Date date, String format) {
		Date defaultDate = (date != null) ? date : new Date();
		String defaulFormat = (format != null) ? format : "yyyyMMdd_HHmmss";

		SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
		return lSDF.format(defaultDate);
	}	
	
}
