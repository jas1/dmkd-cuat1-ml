package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	/**
	 * @param configArbol
	 */
	public static AutomatizarCorridasArbolJulioResultado ejecucionArbolVersionParametrizadoArbolNoChaidNoCampos(AutomatizarCorridasArbolJulioConfig configArbol) {
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		String[] commands= comandoArbolVersionParametrizadoArbolNoChaidNoCampos(configArbol);
		
		resultado.setTiempoInicioEjecucion(new Date());
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
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

	
	
	public static String getTimeStamp(Date date, String format) {
		Date defaultDate = (date != null) ? date : new Date();
		String defaulFormat = (format != null) ? format : "yyyyMMdd_HHmmss";

		SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
		return lSDF.format(defaultDate);
	}	
	
}
