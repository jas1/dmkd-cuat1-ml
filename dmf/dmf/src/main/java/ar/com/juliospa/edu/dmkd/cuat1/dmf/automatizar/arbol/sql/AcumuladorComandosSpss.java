package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.sql;

import java.io.File;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.old.AutomatizarCorridasArbolJulio;


/**
 * la idea de este es tener todos los comandos e ir documentando de que se tratan cada uno
 * @author julio
 *
 */
public class AcumuladorComandosSpss {
	
	/**
	 * SI AGREGAS NUEVOS PONELE LO QUE HACE ! 
	
	 */
	
	/**
	 * toma de la conexion odbc dmkd-dmf
	 * tira la query.
	 * @param configArbol
	 * @return
	 */
	public static String[] comandoArbolDefaultLevantadoSQL(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
		configArbol.setTimeStampFolder(timeStamp);
		String carpetaOutput = configArbol.getOutputFolder()+timeStamp;
		File carpetaOut = new File(carpetaOutput);
		if (carpetaOut.mkdirs()) {
			String outputXML = carpetaOutput+"/"+"model.xml";
			String outputSQL = carpetaOutput+"/"+"reglas.sql";
			String outFileName = carpetaOutput+"/"+"output.html";
		
			String[] result = {
					"OMS",
	                "/DESTINATION FORMAT=HTML OUTFILE='"+outFileName+"'.",
	                "GET DATA",
	                "  /TYPE=ODBC",
	                "  /CONNECT='DSN=dmkd-dmf;'",
	                "  /SQL='SELECT numero_de_cliente, foto_mes, marketing_activo_ultimos90dias, cliente_vip, '+",
	                "    'cliente_sucursal, cliente_edad, cliente_antiguedad, mrentabilidad, mrentabilidad_annual, '+",
	                "    'mcomisiones, mactivos_margen, mpasivos_margen, marketing_coss_selling, tpaquete1, tpaquete2, '+",
	                "    'tpaquete3, tpaquete4, tpaquete5, tpaquete6, tpaquete7, tpaquete8, tpaquete9, tcuentas, '+",
	                "    'tcuenta_corriente, mcuenta_corriente_Nopaquete, mcuenta_corriente_Paquete, '+",
	                "    'mcuenta_corriente_dolares, tcaja_ahorro, mcaja_ahorro_Paquete, mcaja_ahorro_Nopaquete, '+",
	                "    'mcaja_ahorro_dolares, mdescubierto_preacordado, mcuentas_saldo, ttarjeta_debito, '+",
	                "    'ctarjeta_debito_transacciones, mautoservicio, ttarjeta_visa, ctarjeta_visa_transacciones, '+",
	                "    'mtarjeta_visa_consumo, ttarjeta_master, ctarjeta_master_transacciones, '+",
	                "    'mtarjeta_master_consumo, cprestamos_personales, mprestamos_personales, cprestamos_prendarios, '+",
	                "    'mprestamos_prendarios, cprestamos_hipotecarios, mprestamos_hipotecarios, tplazo_fijo, '+",
	                "    'mplazo_fijo_dolares, mplazo_fijo_pesos, tfondos_comunes_inversion, '+",
	                "    'mfondos_comunes_inversion_pesos, mfondos_comunes_inversion_dolares, ttitulos, mtitulos, '+",
	                "    'tseguro_vida_mercado_abierto, tseguro_auto, tseguro_vivienda, tseguro_accidentes_personales, '+",
	                "    'tcaja_seguridad, mbonos_gobierno, mmonedas_extranjeras, minversiones_otras, tplan_sueldo, '+",
	                "    'mplan_sueldo, mplan_sueldo_manual, cplan_sueldo_transaccion, tcuenta_debitos_automaticos, '+",
	                "    'mcuenta_debitos_automaticos, ttarjeta_visa_debitos_automaticos, '+",
	                "    'mttarjeta_visa_debitos_automaticos, ttarjeta_master_debitos_automaticos, '+",
	                "    'mttarjeta_master_debitos_automaticos, tpagodeservicios, mpagodeservicios, tpagomiscuentas, '+",
	                "    'mpagomiscuentas, ccajeros_propios_descuentos, mcajeros_propios_descuentos, '+",
	                "    'ctarjeta_visa_descuentos, mtarjeta_visa_descuentos, ctarjeta_master_descuentos, '+",
	                "    'mtarjeta_master_descuentos, ccuenta_descuentos, mcuenta_descuentos, '+",
	                "    'ccomisiones_mantenimiento, mcomisiones_mantenimiento, ccomisiones_otras, mcomisiones_otras, '+",
	                "    'tcambio_monedas, ccambio_monedas_compra, mcambio_monedas_compra, ccambio_monedas_venta, '+",
	                "    'mcambio_monedas_venta, ctransferencias_recibidas, mtransferencias_recibidas, '+",
	                "    'ctransferencias_emitidas, mtransferencias_emitidas, cextraccion_autoservicio, '+",
	                "    'mextraccion_autoservicio, ccheques_depositados, mcheques_depositados, ccheques_emitidos, '+",
	                "    'mcheques_emitidos, ccheques_depositados_rechazados, mcheques_depositados_rechazados, '+",
	                "    'ccheques_emitidos_rechazados, mcheques_emitidos_rechazados, tcallcenter, '+",
	                "    'ccallcenter_transacciones, thomebanking, chomebanking_transacciones, tautoservicio, '+",
	                "    'cautoservicio_transacciones, tcajas, tcajas_consultas, tcajas_depositos, tcajas_extracciones, '+",
	                "    'tcajas_otras, ccajeros_propio_transacciones, mcajeros_propio, ccajeros_ajenos_transacciones, '+",
	                "    'mcajeros_ajenos, tmovimientos_ultimos90dias, Master_marca_atraso, Master_cuenta_estado, '+",
	                "    'Master_mfinanciacion_limite, Master_Fvencimiento, Master_Finiciomora, Master_msaldototal, '+",
	                "    'Master_msaldopesos, Master_msaldodolares, Master_mconsumospesos, Master_mconsumosdolares, '+",
	                "    'Master_mlimitecompra, Master_madelantopesos, Master_madelantodolares, Master_fultimo_cierre, '+",
	                "    'Master_mpagado, Master_mpagospesos, Master_mpagosdolares, Master_fechaalta, '+",
	                "    'Master_mconsumototal, Master_tconsumos, Master_tadelantosefectivo, Master_mpagominimo, '+",
	                "    'Visa_marca_atraso, Visa_cuenta_estado, Visa_mfinanciacion_limite, Visa_Fvencimiento, '+",
	                "    'Visa_Finiciomora, Visa_msaldototal, Visa_msaldopesos, Visa_msaldodolares, '+",
	                "    'Visa_mconsumospesos, Visa_mconsumosdolares, Visa_mlimitecompra, Visa_madelantopesos, '+",
	                "    'Visa_madelantodolares, Visa_fultimo_cierre, Visa_mpagado, Visa_mpagospesos, '+",
	                "    'Visa_mpagosdolares, Visa_fechaalta, Visa_mconsumototal, Visa_tconsumos, '+",
	                "    'Visa_tadelantosefectivo, Visa_mpagominimo, participa, clase FROM dmf.historia_rework '+",
	                "    'WHERE foto_mes=201504'+",
	                "  /ASSUMEDSTRWIDTH=255.",
	                "",
	                "CACHE.",
	                "EXECUTE.",
	                "DATASET NAME DataSet1 WINDOW=FRONT.",
	                "* Decision Tree.",
	                "TREE clase [n] BY numero_de_cliente [s] foto_mes [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [n] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete1 [n] tpaquete2 [n] tpaquete3 [n] tpaquete4 [n] tpaquete5 [n] tpaquete6 [n] tpaquete7 [n] tpaquete8 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete",
	                "[s] mcuenta_corriente_dolares [s] tcaja_ahorro [n] mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [s] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s]",
	                "cprestamos_hipotecarios [n] mprestamos_hipotecarios [s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] mbonos_gobierno [n] mmonedas_extranjeras [n] minversiones_otras [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s]",
	                "cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n] mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [n] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s]",
	                "mtarjeta_master_descuentos [s] ccuenta_descuentos [n] mcuenta_descuentos [n] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n] ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s]",
	                "mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados [s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s]",
	                "ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [s] Master_mfinanciacion_limite [s] Master_Fvencimiento [s] Master_Finiciomora [s] Master_msaldototal [s] Master_msaldopesos [s] Master_msaldodolares [s] Master_mconsumospesos [s] Master_mconsumosdolares [s] Master_mlimitecompra [s] Master_madelantopesos [s] Master_madelantodolares [s] Master_fultimo_cierre [s] Master_mpagado [s] Master_mpagospesos [s] Master_mpagosdolares [s]",
	                "Master_fechaalta [s] Master_mconsumototal [s] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [s] Visa_marca_atraso [n] Visa_cuenta_estado [s] Visa_mfinanciacion_limite [s] Visa_Fvencimiento [s] Visa_Finiciomora [s] Visa_msaldototal [s] Visa_msaldopesos [s] Visa_msaldodolares [s] Visa_mconsumospesos [s] Visa_mconsumosdolares [s] Visa_mlimitecompra [s] Visa_madelantopesos [s] Visa_madelantodolares [s] Visa_fultimo_cierre [s] Visa_mpagado [s] Visa_mpagospesos [s] Visa_mpagosdolares [s]",
	                "Visa_fechaalta [s] Visa_mconsumototal [s] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [s] participa [n]",
	                "  /TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
	                "  /DEPCATEGORIES USEVALUES=[VALID]",
	                "  /PRINT MODELSUMMARY CLASSIFICATION RISK TREETABLE",
	                "  /RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+outputSQL+"'",
	                "  /SAVE PREDPROB",
	                "  /METHOD TYPE=CHAID",
	                "  /GROWTHLIMIT MAXDEPTH=AUTO MINPARENTSIZE=100 MINCHILDSIZE=50",
	                "  /VALIDATION TYPE=NONE OUTPUT=BOTHSAMPLES",
	                "  /CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10",
	                "  /COSTS EQUAL",
	                "  /OUTFILE TRAININGMODEL='"+outputXML+"'",
	                "  /MISSING NOMINALMISSING=MISSING.",
	                "EXECUTE.",
					"OMSEND."};
	
			
			return result;
			
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}
	}

	public static String[] comandoArbolDefaultLevantadoSQL7030(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
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
	                "GET DATA",
	                "  /TYPE=ODBC",
	                "  /CONNECT='DSN=dmkd-dmf;'",
	                "  /SQL='SELECT numero_de_cliente, foto_mes, marketing_activo_ultimos90dias, cliente_vip, '+",
	                "    'cliente_sucursal, cliente_edad, cliente_antiguedad, mrentabilidad, mrentabilidad_annual, '+",
	                "    'mcomisiones, mactivos_margen, mpasivos_margen, marketing_coss_selling, tpaquete1, tpaquete2, '+",
	                "    'tpaquete3, tpaquete4, tpaquete5, tpaquete6, tpaquete7, tpaquete8, tpaquete9, tcuentas, '+",
	                "    'tcuenta_corriente, mcuenta_corriente_Nopaquete, mcuenta_corriente_Paquete, '+",
	                "    'mcuenta_corriente_dolares, tcaja_ahorro, mcaja_ahorro_Paquete, mcaja_ahorro_Nopaquete, '+",
	                "    'mcaja_ahorro_dolares, mdescubierto_preacordado, mcuentas_saldo, ttarjeta_debito, '+",
	                "    'ctarjeta_debito_transacciones, mautoservicio, ttarjeta_visa, ctarjeta_visa_transacciones, '+",
	                "    'mtarjeta_visa_consumo, ttarjeta_master, ctarjeta_master_transacciones, '+",
	                "    'mtarjeta_master_consumo, cprestamos_personales, mprestamos_personales, cprestamos_prendarios, '+",
	                "    'mprestamos_prendarios, cprestamos_hipotecarios, mprestamos_hipotecarios, tplazo_fijo, '+",
	                "    'mplazo_fijo_dolares, mplazo_fijo_pesos, tfondos_comunes_inversion, '+",
	                "    'mfondos_comunes_inversion_pesos, mfondos_comunes_inversion_dolares, ttitulos, mtitulos, '+",
	                "    'tseguro_vida_mercado_abierto, tseguro_auto, tseguro_vivienda, tseguro_accidentes_personales, '+",
	                "    'tcaja_seguridad, mbonos_gobierno, mmonedas_extranjeras, minversiones_otras, tplan_sueldo, '+",
	                "    'mplan_sueldo, mplan_sueldo_manual, cplan_sueldo_transaccion, tcuenta_debitos_automaticos, '+",
	                "    'mcuenta_debitos_automaticos, ttarjeta_visa_debitos_automaticos, '+",
	                "    'mttarjeta_visa_debitos_automaticos, ttarjeta_master_debitos_automaticos, '+",
	                "    'mttarjeta_master_debitos_automaticos, tpagodeservicios, mpagodeservicios, tpagomiscuentas, '+",
	                "    'mpagomiscuentas, ccajeros_propios_descuentos, mcajeros_propios_descuentos, '+",
	                "    'ctarjeta_visa_descuentos, mtarjeta_visa_descuentos, ctarjeta_master_descuentos, '+",
	                "    'mtarjeta_master_descuentos, ccuenta_descuentos, mcuenta_descuentos, '+",
	                "    'ccomisiones_mantenimiento, mcomisiones_mantenimiento, ccomisiones_otras, mcomisiones_otras, '+",
	                "    'tcambio_monedas, ccambio_monedas_compra, mcambio_monedas_compra, ccambio_monedas_venta, '+",
	                "    'mcambio_monedas_venta, ctransferencias_recibidas, mtransferencias_recibidas, '+",
	                "    'ctransferencias_emitidas, mtransferencias_emitidas, cextraccion_autoservicio, '+",
	                "    'mextraccion_autoservicio, ccheques_depositados, mcheques_depositados, ccheques_emitidos, '+",
	                "    'mcheques_emitidos, ccheques_depositados_rechazados, mcheques_depositados_rechazados, '+",
	                "    'ccheques_emitidos_rechazados, mcheques_emitidos_rechazados, tcallcenter, '+",
	                "    'ccallcenter_transacciones, thomebanking, chomebanking_transacciones, tautoservicio, '+",
	                "    'cautoservicio_transacciones, tcajas, tcajas_consultas, tcajas_depositos, tcajas_extracciones, '+",
	                "    'tcajas_otras, ccajeros_propio_transacciones, mcajeros_propio, ccajeros_ajenos_transacciones, '+",
	                "    'mcajeros_ajenos, tmovimientos_ultimos90dias, Master_marca_atraso, Master_cuenta_estado, '+",
	                "    'Master_mfinanciacion_limite, Master_Fvencimiento, Master_Finiciomora, Master_msaldototal, '+",
	                "    'Master_msaldopesos, Master_msaldodolares, Master_mconsumospesos, Master_mconsumosdolares, '+",
	                "    'Master_mlimitecompra, Master_madelantopesos, Master_madelantodolares, Master_fultimo_cierre, '+",
	                "    'Master_mpagado, Master_mpagospesos, Master_mpagosdolares, Master_fechaalta, '+",
	                "    'Master_mconsumototal, Master_tconsumos, Master_tadelantosefectivo, Master_mpagominimo, '+",
	                "    'Visa_marca_atraso, Visa_cuenta_estado, Visa_mfinanciacion_limite, Visa_Fvencimiento, '+",
	                "    'Visa_Finiciomora, Visa_msaldototal, Visa_msaldopesos, Visa_msaldodolares, '+",
	                "    'Visa_mconsumospesos, Visa_mconsumosdolares, Visa_mlimitecompra, Visa_madelantopesos, '+",
	                "    'Visa_madelantodolares, Visa_fultimo_cierre, Visa_mpagado, Visa_mpagospesos, '+",
	                "    'Visa_mpagosdolares, Visa_fechaalta, Visa_mconsumototal, Visa_tconsumos, '+",
	                "    'Visa_tadelantosefectivo, Visa_mpagominimo, participa, clase FROM dmf.historia_rework '+",
	                "    'WHERE foto_mes=201504'+",
	                "  /ASSUMEDSTRWIDTH=255.",
	                "",
	                "CACHE.",
	                "EXECUTE.",
	                "DATASET NAME DataSet1 WINDOW=FRONT.",
	                
	    			"* Define Variable Properties.",
	    			"*clase.",
	    			"VALUE LABELS clase",
	    			"  'BAJA+1                  ' 'NEGATIVO'",
	    			"  'BAJA+2                  ' 'POSITIVO'",
	    			"  'CONTINUA                ' 'NEGATIVO'.",
	    			"EXECUTE.",
	    			"SET SEED="+configArbol.getSeed()+".",
	                "EXECUTE.",
	                "* Decision Tree.",
	                "TREE clase [n] BY numero_de_cliente [s] foto_mes [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [n] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete1 [n] tpaquete2 [n] tpaquete3 [n] tpaquete4 [n] tpaquete5 [n] tpaquete6 [n] tpaquete7 [n] tpaquete8 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete",
	                "[s] mcuenta_corriente_dolares [s] tcaja_ahorro [n] mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [s] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s]",
	                "cprestamos_hipotecarios [n] mprestamos_hipotecarios [s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] mbonos_gobierno [n] mmonedas_extranjeras [n] minversiones_otras [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s]",
	                "cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n] mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [n] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s]",
	                "mtarjeta_master_descuentos [s] ccuenta_descuentos [n] mcuenta_descuentos [n] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n] ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s]",
	                "mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados [s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s]",
	                "ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [s] Master_mfinanciacion_limite [s] Master_Fvencimiento [s] Master_Finiciomora [s] Master_msaldototal [s] Master_msaldopesos [s] Master_msaldodolares [s] Master_mconsumospesos [s] Master_mconsumosdolares [s] Master_mlimitecompra [s] Master_madelantopesos [s] Master_madelantodolares [s] Master_fultimo_cierre [s] Master_mpagado [s] Master_mpagospesos [s] Master_mpagosdolares [s]",
	                "Master_fechaalta [s] Master_mconsumototal [s] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [s] Visa_marca_atraso [n] Visa_cuenta_estado [s] Visa_mfinanciacion_limite [s] Visa_Fvencimiento [s] Visa_Finiciomora [s] Visa_msaldototal [s] Visa_msaldopesos [s] Visa_msaldodolares [s] Visa_mconsumospesos [s] Visa_mconsumosdolares [s] Visa_mlimitecompra [s] Visa_madelantopesos [s] Visa_madelantodolares [s] Visa_fultimo_cierre [s] Visa_mpagado [s] Visa_mpagospesos [s] Visa_mpagosdolares [s]",
	                "Visa_fechaalta [s] Visa_mconsumototal [s] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [s] participa [n]",
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
	                "EXECUTE.",
					"OMSEND."};
	
			
			return result;
			
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}
	}
	
	
	public static String[] comandoArbolSQL7030ConVarsHistoria(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
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
	                "GET DATA",
	                "  /TYPE=ODBC",
	                "  /CONNECT='DSN=dmkd-dmf;'",
	                "  /SQL='SELECT numero_de_cliente, foto_mes, marketing_activo_ultimos90dias, cliente_vip, '+",
	                "    'cliente_sucursal, cliente_edad, cliente_antiguedad, mrentabilidad, mrentabilidad_annual, '+",
	                "    'mcomisiones, mactivos_margen, mpasivos_margen, marketing_coss_selling, tpaquete1, tpaquete2, '+",
	                "    'tpaquete3, tpaquete4, tpaquete5, tpaquete6, tpaquete7, tpaquete8, tpaquete9, tcuentas, '+",
	                "    'tcuenta_corriente, mcuenta_corriente_Nopaquete, mcuenta_corriente_Paquete, '+",
	                "    'mcuenta_corriente_dolares, tcaja_ahorro, mcaja_ahorro_Paquete, mcaja_ahorro_Nopaquete, '+",
	                "    'mcaja_ahorro_dolares, mdescubierto_preacordado, mcuentas_saldo, ttarjeta_debito, '+",
	                "    'ctarjeta_debito_transacciones, mautoservicio, ttarjeta_visa, ctarjeta_visa_transacciones, '+",
	                "    'mtarjeta_visa_consumo, ttarjeta_master, ctarjeta_master_transacciones, '+",
	                "    'mtarjeta_master_consumo, cprestamos_personales, mprestamos_personales, cprestamos_prendarios, '+",
	                "    'mprestamos_prendarios, cprestamos_hipotecarios, mprestamos_hipotecarios, tplazo_fijo, '+",
	                "    'mplazo_fijo_dolares, mplazo_fijo_pesos, tfondos_comunes_inversion, '+",
	                "    'mfondos_comunes_inversion_pesos, mfondos_comunes_inversion_dolares, ttitulos, mtitulos, '+",
	                "    'tseguro_vida_mercado_abierto, tseguro_auto, tseguro_vivienda, tseguro_accidentes_personales, '+",
	                "    'tcaja_seguridad, mbonos_gobierno, mmonedas_extranjeras, minversiones_otras, tplan_sueldo, '+",
	                "    'mplan_sueldo, mplan_sueldo_manual, cplan_sueldo_transaccion, tcuenta_debitos_automaticos, '+",
	                "    'mcuenta_debitos_automaticos, ttarjeta_visa_debitos_automaticos, '+",
	                "    'mttarjeta_visa_debitos_automaticos, ttarjeta_master_debitos_automaticos, '+",
	                "    'mttarjeta_master_debitos_automaticos, tpagodeservicios, mpagodeservicios, tpagomiscuentas, '+",
	                "    'mpagomiscuentas, ccajeros_propios_descuentos, mcajeros_propios_descuentos, '+",
	                "    'ctarjeta_visa_descuentos, mtarjeta_visa_descuentos, ctarjeta_master_descuentos, '+",
	                "    'mtarjeta_master_descuentos, ccuenta_descuentos, mcuenta_descuentos, '+",
	                "    'ccomisiones_mantenimiento, mcomisiones_mantenimiento, ccomisiones_otras, mcomisiones_otras, '+",
	                "    'tcambio_monedas, ccambio_monedas_compra, mcambio_monedas_compra, ccambio_monedas_venta, '+",
	                "    'mcambio_monedas_venta, ctransferencias_recibidas, mtransferencias_recibidas, '+",
	                "    'ctransferencias_emitidas, mtransferencias_emitidas, cextraccion_autoservicio, '+",
	                "    'mextraccion_autoservicio, ccheques_depositados, mcheques_depositados, ccheques_emitidos, '+",
	                "    'mcheques_emitidos, ccheques_depositados_rechazados, mcheques_depositados_rechazados, '+",
	                "    'ccheques_emitidos_rechazados, mcheques_emitidos_rechazados, tcallcenter, '+",
	                "    'ccallcenter_transacciones, thomebanking, chomebanking_transacciones, tautoservicio, '+",
	                "    'cautoservicio_transacciones, tcajas, tcajas_consultas, tcajas_depositos, tcajas_extracciones, '+",
	                "    'tcajas_otras, ccajeros_propio_transacciones, mcajeros_propio, ccajeros_ajenos_transacciones, '+",
	                "    'mcajeros_ajenos, tmovimientos_ultimos90dias, Master_marca_atraso, Master_cuenta_estado, '+",
	                "    'Master_mfinanciacion_limite, Master_Fvencimiento, Master_Finiciomora, Master_msaldototal, '+",
	                "    'Master_msaldopesos, Master_msaldodolares, Master_mconsumospesos, Master_mconsumosdolares, '+",
	                "    'Master_mlimitecompra, Master_madelantopesos, Master_madelantodolares, Master_fultimo_cierre, '+",
	                "    'Master_mpagado, Master_mpagospesos, Master_mpagosdolares, Master_fechaalta, '+",
	                "    'Master_mconsumototal, Master_tconsumos, Master_tadelantosefectivo, Master_mpagominimo, '+",
	                "    'Visa_marca_atraso, Visa_cuenta_estado, Visa_mfinanciacion_limite, Visa_Fvencimiento, '+",
	                "    'Visa_Finiciomora, Visa_msaldototal, Visa_msaldopesos, Visa_msaldodolares, '+",
	                "    'Visa_mconsumospesos, Visa_mconsumosdolares, Visa_mlimitecompra, Visa_madelantopesos, '+",
	                "    'Visa_madelantodolares, Visa_fultimo_cierre, Visa_mpagado, Visa_mpagospesos, '+",
	                "    'Visa_mpagosdolares, Visa_fechaalta, Visa_mconsumototal, Visa_tconsumos, '+",
	                "    'Visa_tadelantosefectivo, Visa_mpagominimo, participa, clase, jestado_seguros, '+",
	                "    'jestado_debitos, jestado_contacto, jestado_visa_dias_mora_relativa_periodo, '+",
	                "    'jestado_master_dias_mora_relativa_periodo, jestado_visa_ratio_consumo_limite, '+",
	                "    'jestado_master_ratio_consumo_limite, jestado_visa_ratio_minimo_consumo_limite, '+",
	                "    'jestado_master_ratio_minimo_consumo_limite, tarjeta_marca_atraso, tarjeta_cuenta_estado, '+",
	                "    'tarjeta_mfinanciacion_limite, tarjeta_Fvencimiento, tarjeta_Finiciomora, '+",
	                "    'jestado_tarjeta_dias_mora_relativa_periodo, jestado_tarjeta_ratio_consumo_limite, '+",
	                "    'jestado_tarjeta_ratio_minimo_consumo_limite, tarjeta_msaldototal, tarjeta_msaldopesos, '+",
	                "    'tarjeta_msaldodolares, tarjeta_mconsumospesos, tarjeta_mconsumosdolares, '+",
	                "    'tarjeta_mlimitecompra, tarjeta_madelantopesos, tarjeta_madelantodolares, '+",
	                "    'tarjeta_fultimo_cierre, tarjeta_mpagado, tarjeta_mpagospesos, tarjeta_mpagosdolares, '+",
	                "    'tarjeta_fechaalta, tarjeta_mconsumototal, tarjeta_tconsumos, tarjeta_tadelantosefectivo, '+",
	                "    'tarjeta_mpagominimo, mrentabilidad_AVG, mcomisiones_AVG, Master_mfinanciacion_limite_AVG, '+",
	                "    'Master_msaldodolares_AVG, Master_mconsumospesos_AVG, Master_mconsumosdolares_AVG, '+",
	                "    'Master_mlimitecompra_AVG, Master_madelantodolares_AVG, Master_mpagado_AVG, '+",
	                "    'Master_mpagosdolares_AVG, Master_mconsumototal_AVG, Visa_mfinanciacion_limite_AVG, '+",
	                "    'Visa_mlimitecompra_AVG, Visa_madelantodolares_AVG, Visa_mpagado_AVG, '+",
	                "    'jestado_visa_ratio_consumo_limite_AVG, tarjeta_mfinanciacion_limite_AVG, '+",
	                "    'tarjeta_msaldodolares_AVG, tarjeta_mconsumospesos_AVG, tarjeta_mconsumosdolares_AVG, '+",
	                "    'tarjeta_mlimitecompra_AVG, tarjeta_madelantodolares_AVG, tarjeta_mpagosdolares_AVG, '+",
	                "    'tarjeta_mconsumototal_AVG FROM dmf.historia_rework_with_cols_histo_2 where foto_mes=201504'",
	                "  /ASSUMEDSTRWIDTH=255.",
	                "",
	                "CACHE.",
	                "EXECUTE.",
	                "DATASET NAME DataSet1 WINDOW=FRONT.",
	                
	    			"* Define Variable Properties.",
	    			"*clase.",
	    			"VALUE LABELS clase",
	    			"  'BAJA+1                  ' 'NEGATIVO'",
	    			"  'BAJA+2                  ' 'POSITIVO'",
	    			"  'CONTINUA                ' 'NEGATIVO'.",
	    			"EXECUTE.",
	    			"SET SEED="+configArbol.getSeed()+".",
	                "EXECUTE.",
	                "* Decision Tree.",
	                "TREE clase [n] BY numero_de_cliente [s] foto_mes [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [n] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete1 [n] tpaquete2 [n] tpaquete3 [n] tpaquete4 [n] tpaquete5 [n] tpaquete6 [n] tpaquete7 [n] tpaquete8 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete",
	                "[s] mcuenta_corriente_dolares [n] tcaja_ahorro [n] mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [s] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s]",
	                "cprestamos_hipotecarios [n] mprestamos_hipotecarios [s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] mbonos_gobierno [n] mmonedas_extranjeras [n] minversiones_otras [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s]",
	                "cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n] mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [n] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s]",
	                "mtarjeta_master_descuentos [s] ccuenta_descuentos [n] mcuenta_descuentos [n] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n] ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s]",
	                "mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados [s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s]",
	                "ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [s] Master_mfinanciacion_limite [s] Master_Fvencimiento [s] Master_Finiciomora [s] Master_msaldototal [s] Master_msaldopesos [s] Master_msaldodolares [s] Master_mconsumospesos [s] Master_mconsumosdolares [s] Master_mlimitecompra [s] Master_madelantopesos [s] Master_madelantodolares [s] Master_fultimo_cierre [s] Master_mpagado [s] Master_mpagospesos [s] Master_mpagosdolares [s]",
	                "Master_fechaalta [s] Master_mconsumototal [s] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [s] Visa_marca_atraso [n] Visa_cuenta_estado [s] Visa_mfinanciacion_limite [s] Visa_Fvencimiento [s] Visa_Finiciomora [s] Visa_msaldototal [s] Visa_msaldopesos [s] Visa_msaldodolares [s] Visa_mconsumospesos [s] Visa_mconsumosdolares [s] Visa_mlimitecompra [s] Visa_madelantopesos [s] Visa_madelantodolares [s] Visa_fultimo_cierre [s] Visa_mpagado [s] Visa_mpagospesos [s] Visa_mpagosdolares [s]",
	                "Visa_fechaalta [s] Visa_mconsumototal [s] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [s] participa [n] jestado_seguros [n] jestado_debitos [n] jestado_contacto [n] jestado_visa_dias_mora_relativa_periodo [s] jestado_master_dias_mora_relativa_periodo [s] jestado_visa_ratio_consumo_limite [s] jestado_master_ratio_consumo_limite [s] jestado_visa_ratio_minimo_consumo_limite [s] jestado_master_ratio_minimo_consumo_limite [s] tarjeta_marca_atraso [n] tarjeta_cuenta_estado [s]",
	                "tarjeta_mfinanciacion_limite [s] tarjeta_Fvencimiento [n] tarjeta_Finiciomora [n] jestado_tarjeta_dias_mora_relativa_periodo [s] jestado_tarjeta_ratio_consumo_limite [s] jestado_tarjeta_ratio_minimo_consumo_limite [s] tarjeta_msaldototal [s] tarjeta_msaldopesos [s] tarjeta_msaldodolares [s] tarjeta_mconsumospesos [s] tarjeta_mconsumosdolares [s] tarjeta_mlimitecompra [s] tarjeta_madelantopesos [s] tarjeta_madelantodolares [s] tarjeta_fultimo_cierre [n] tarjeta_mpagado [s] tarjeta_mpagospesos [s]",
	                "tarjeta_mpagosdolares [s] tarjeta_fechaalta [n] tarjeta_mconsumototal [s] tarjeta_tconsumos [n] tarjeta_tadelantosefectivo [n] tarjeta_mpagominimo [s] mrentabilidad_AVG [s] mcomisiones_AVG [s] Master_mfinanciacion_limite_AVG [s] Master_msaldodolares_AVG [s] Master_mconsumospesos_AVG [s] Master_mconsumosdolares_AVG [s] Master_mlimitecompra_AVG [s] Master_madelantodolares_AVG [s] Master_mpagado_AVG [s] Master_mpagosdolares_AVG [s] Master_mconsumototal_AVG [s] Visa_mfinanciacion_limite_AVG [s]",
	                "Visa_mlimitecompra_AVG [s] Visa_madelantodolares_AVG [s] Visa_mpagado_AVG [s] jestado_visa_ratio_consumo_limite_AVG [s] tarjeta_mfinanciacion_limite_AVG [s] tarjeta_msaldodolares_AVG [s] tarjeta_mconsumospesos_AVG [s] tarjeta_mconsumosdolares_AVG [s] tarjeta_mlimitecompra_AVG [s] tarjeta_madelantodolares_AVG [s] tarjeta_mpagosdolares_AVG [s] tarjeta_mconsumototal_AVG [s]",
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
	                "EXECUTE.",
					"OMSEND."};
	
			
			return result;
			
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}
	}
	
	public static String[] comandoArbolSQL7030ConVarsHistoriaReducto2(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
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
	                "GET DATA",
	                "  /TYPE=ODBC",
	                "  /CONNECT='DSN=dmkd-dmf;'",
	                "  /SQL='SELECT numero_de_cliente, foto_mes, marketing_activo_ultimos90dias, cliente_vip, '+",
	                "    'cliente_sucursal, cliente_edad, cliente_antiguedad, mrentabilidad, mrentabilidad_annual, '+",
	                "    'mcomisiones, mactivos_margen, mpasivos_margen, marketing_coss_selling, tpaquete1, tpaquete2, '+",
	                "    'tpaquete3, tpaquete4, tpaquete5, tpaquete6, tpaquete7, tpaquete8, tpaquete9, tcuentas, '+",
	                "    'tcuenta_corriente, mcuenta_corriente_Nopaquete, mcuenta_corriente_Paquete, '+",
	                "    'mcuenta_corriente_dolares, tcaja_ahorro, mcaja_ahorro_Paquete, mcaja_ahorro_Nopaquete, '+",
	                "    'mcaja_ahorro_dolares, mdescubierto_preacordado, mcuentas_saldo, ttarjeta_debito, '+",
	                "    'ctarjeta_debito_transacciones, mautoservicio, ttarjeta_visa, ctarjeta_visa_transacciones, '+",
	                "    'mtarjeta_visa_consumo, ttarjeta_master, ctarjeta_master_transacciones, '+",
	                "    'mtarjeta_master_consumo, cprestamos_personales, mprestamos_personales, cprestamos_prendarios, '+",
	                "    'mprestamos_prendarios, cprestamos_hipotecarios, mprestamos_hipotecarios, tplazo_fijo, '+",
	                "    'mplazo_fijo_dolares, mplazo_fijo_pesos, tfondos_comunes_inversion, '+",
	                "    'mfondos_comunes_inversion_pesos, mfondos_comunes_inversion_dolares, ttitulos, mtitulos, '+",

	                "    'participa, clase, jestado_seguros, '+",
	                "    'jestado_debitos, jestado_contacto, jestado_visa_dias_mora_relativa_periodo, '+",
	                "    'jestado_master_dias_mora_relativa_periodo, jestado_visa_ratio_consumo_limite, '+",
	                "    'jestado_master_ratio_consumo_limite, jestado_visa_ratio_minimo_consumo_limite, '+",
	                "    'jestado_master_ratio_minimo_consumo_limite, tarjeta_marca_atraso, tarjeta_cuenta_estado, '+",
	                "    'tarjeta_mfinanciacion_limite, tarjeta_Fvencimiento, tarjeta_Finiciomora, '+",
	                "    'jestado_tarjeta_dias_mora_relativa_periodo, jestado_tarjeta_ratio_consumo_limite, '+",
	                "    'jestado_tarjeta_ratio_minimo_consumo_limite, tarjeta_msaldototal, tarjeta_msaldopesos, '+",
	                "    'tarjeta_msaldodolares, tarjeta_mconsumospesos, tarjeta_mconsumosdolares, '+",
	                "    'tarjeta_mlimitecompra, tarjeta_madelantopesos, tarjeta_madelantodolares, '+",
	                "    'tarjeta_fultimo_cierre, tarjeta_mpagado, tarjeta_mpagospesos, tarjeta_mpagosdolares, '+",
	                "    'tarjeta_fechaalta, tarjeta_mconsumototal, tarjeta_tconsumos, tarjeta_tadelantosefectivo, '+",
	                "    'tarjeta_mpagominimo, mrentabilidad_AVG, mcomisiones_AVG, Master_mfinanciacion_limite_AVG, '+",
	                "    'Master_msaldodolares_AVG, Master_mconsumospesos_AVG, Master_mconsumosdolares_AVG, '+",
	                "    'Master_mlimitecompra_AVG, Master_madelantodolares_AVG, Master_mpagado_AVG, '+",
	                "    'Master_mpagosdolares_AVG, Master_mconsumototal_AVG, Visa_mfinanciacion_limite_AVG, '+",
	                "    'Visa_mlimitecompra_AVG, Visa_madelantodolares_AVG, Visa_mpagado_AVG, '+",
	                "    'jestado_visa_ratio_consumo_limite_AVG, tarjeta_mfinanciacion_limite_AVG, '+",
	                "    'tarjeta_msaldodolares_AVG, tarjeta_mconsumospesos_AVG, tarjeta_mconsumosdolares_AVG, '+",
	                "    'tarjeta_mlimitecompra_AVG, tarjeta_madelantodolares_AVG, tarjeta_mpagosdolares_AVG, '+",
	                "    'tarjeta_mconsumototal_AVG FROM dmf.historia_rework_with_cols_histo_2 where foto_mes=201504'",
	                "  /ASSUMEDSTRWIDTH=255.",
	                "",
	                "CACHE.",
	                "EXECUTE.",
	                "DATASET NAME DataSet1 WINDOW=FRONT.",
	                
	    			"* Define Variable Properties.",
	    			"*clase.",
	    			"VALUE LABELS clase",
	    			"  'BAJA+1                  ' 'NEGATIVO'",
	    			"  'BAJA+2                  ' 'POSITIVO'",
	    			"  'CONTINUA                ' 'NEGATIVO'.",
	    			"EXECUTE.",
	    			"SET SEED="+configArbol.getSeed()+".",
	                "EXECUTE.",
	                "* Decision Tree.",
	                "TREE clase [n] BY numero_de_cliente [s] foto_mes [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [n] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete1 [n] tpaquete2 [n] tpaquete3 [n] tpaquete4 [n] tpaquete5 [n] tpaquete6 [n] tpaquete7 [n] tpaquete8 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete",
	                "[s] mcuenta_corriente_dolares [n] tcaja_ahorro [n] mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [s] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s]",
	                "cprestamos_hipotecarios [n] mprestamos_hipotecarios [s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] ",
	                "participa [n] jestado_seguros [n] jestado_debitos [n] jestado_contacto [n] jestado_visa_dias_mora_relativa_periodo [s] jestado_master_dias_mora_relativa_periodo [s] jestado_visa_ratio_consumo_limite [s] jestado_master_ratio_consumo_limite [s] jestado_visa_ratio_minimo_consumo_limite [s] jestado_master_ratio_minimo_consumo_limite [s] tarjeta_marca_atraso [n] tarjeta_cuenta_estado [s]",
	                "tarjeta_mfinanciacion_limite [s] tarjeta_Fvencimiento [n] tarjeta_Finiciomora [n] jestado_tarjeta_dias_mora_relativa_periodo [s] jestado_tarjeta_ratio_consumo_limite [s] jestado_tarjeta_ratio_minimo_consumo_limite [s] tarjeta_msaldototal [s] tarjeta_msaldopesos [s] tarjeta_msaldodolares [s] tarjeta_mconsumospesos [s] tarjeta_mconsumosdolares [s] tarjeta_mlimitecompra [s] tarjeta_madelantopesos [s] tarjeta_madelantodolares [s] tarjeta_fultimo_cierre [n] tarjeta_mpagado [s] tarjeta_mpagospesos [s]",
	                "tarjeta_mpagosdolares [s] tarjeta_fechaalta [n] tarjeta_mconsumototal [s] tarjeta_tconsumos [n] tarjeta_tadelantosefectivo [n] tarjeta_mpagominimo [s] mrentabilidad_AVG [s] mcomisiones_AVG [s] Master_mfinanciacion_limite_AVG [s] Master_msaldodolares_AVG [s] Master_mconsumospesos_AVG [s] Master_mconsumosdolares_AVG [s] Master_mlimitecompra_AVG [s] Master_madelantodolares_AVG [s] Master_mpagado_AVG [s] Master_mpagosdolares_AVG [s] Master_mconsumototal_AVG [s] Visa_mfinanciacion_limite_AVG [s]",
	                "Visa_mlimitecompra_AVG [s] Visa_madelantodolares_AVG [s] Visa_mpagado_AVG [s] jestado_visa_ratio_consumo_limite_AVG [s] tarjeta_mfinanciacion_limite_AVG [s] tarjeta_msaldodolares_AVG [s] tarjeta_mconsumospesos_AVG [s] tarjeta_mconsumosdolares_AVG [s] tarjeta_mlimitecompra_AVG [s] tarjeta_madelantodolares_AVG [s] tarjeta_mpagosdolares_AVG [s] tarjeta_mconsumototal_AVG [s]",
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
	                "EXECUTE.",
					"OMSEND."};
	
			
			return result;
			
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}
	}
	
	
	public static String[] comandoArbolSQL7030ConVarsHistoriaReducto1(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
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
	                "GET DATA",
	                "  /TYPE=ODBC",
	                "  /CONNECT='DSN=dmkd-dmf;'",
	                "  /SQL='SELECT numero_de_cliente, foto_mes, marketing_activo_ultimos90dias, cliente_vip, '+",
	                "    'ccheques_emitidos_rechazados, mcheques_emitidos_rechazados, tcallcenter, '+",
	                "    'ccallcenter_transacciones, thomebanking, chomebanking_transacciones, tautoservicio, '+",
	                "    'cautoservicio_transacciones, tcajas, tcajas_consultas, tcajas_depositos, tcajas_extracciones, '+",
	                "    'tcajas_otras, ccajeros_propio_transacciones, mcajeros_propio, ccajeros_ajenos_transacciones, '+",
	                "    'mcajeros_ajenos, tmovimientos_ultimos90dias, Master_marca_atraso, Master_cuenta_estado, '+",
	                "    'Master_mfinanciacion_limite, Master_Fvencimiento, Master_Finiciomora, Master_msaldototal, '+",
	                "    'Master_msaldopesos, Master_msaldodolares, Master_mconsumospesos, Master_mconsumosdolares, '+",
	                "    'Master_mlimitecompra, Master_madelantopesos, Master_madelantodolares, Master_fultimo_cierre, '+",
	                "    'Master_mpagado, Master_mpagospesos, Master_mpagosdolares, Master_fechaalta, '+",
	                "    'Master_mconsumototal, Master_tconsumos, Master_tadelantosefectivo, Master_mpagominimo, '+",
	                "    'Visa_marca_atraso, Visa_cuenta_estado, Visa_mfinanciacion_limite, Visa_Fvencimiento, '+",
	                "    'Visa_Finiciomora, Visa_msaldototal, Visa_msaldopesos, Visa_msaldodolares, '+",
	                "    'Visa_mconsumospesos, Visa_mconsumosdolares, Visa_mlimitecompra, Visa_madelantopesos, '+",
	                "    'Visa_madelantodolares, Visa_fultimo_cierre, Visa_mpagado, Visa_mpagospesos, '+",
	                "    'Visa_mpagosdolares, Visa_fechaalta, Visa_mconsumototal, Visa_tconsumos, '+",
	                "    'Visa_tadelantosefectivo, Visa_mpagominimo, participa, clase, jestado_seguros, '+",
	                "    'jestado_debitos, jestado_contacto, jestado_visa_dias_mora_relativa_periodo, '+",
	                "    'jestado_master_dias_mora_relativa_periodo, jestado_visa_ratio_consumo_limite, '+",
	                "    'jestado_master_ratio_consumo_limite, jestado_visa_ratio_minimo_consumo_limite, '+",
	                "    'jestado_master_ratio_minimo_consumo_limite, tarjeta_marca_atraso, tarjeta_cuenta_estado, '+",
	                "    'tarjeta_mfinanciacion_limite, tarjeta_Fvencimiento, tarjeta_Finiciomora, '+",
	                "    'jestado_tarjeta_dias_mora_relativa_periodo, jestado_tarjeta_ratio_consumo_limite, '+",
	                "    'jestado_tarjeta_ratio_minimo_consumo_limite, tarjeta_msaldototal, tarjeta_msaldopesos, '+",
	                "    'tarjeta_msaldodolares, tarjeta_mconsumospesos, tarjeta_mconsumosdolares, '+",
	                "    'tarjeta_mlimitecompra, tarjeta_madelantopesos, tarjeta_madelantodolares, '+",
	                "    'tarjeta_fultimo_cierre, tarjeta_mpagado, tarjeta_mpagospesos, tarjeta_mpagosdolares, '+",
	                "    'tarjeta_fechaalta, tarjeta_mconsumototal, tarjeta_tconsumos, tarjeta_tadelantosefectivo, '+",
	                "    'tarjeta_mpagominimo, mrentabilidad_AVG, mcomisiones_AVG, Master_mfinanciacion_limite_AVG, '+",
	                "    'Master_msaldodolares_AVG, Master_mconsumospesos_AVG, Master_mconsumosdolares_AVG, '+",
	                "    'Master_mlimitecompra_AVG, Master_madelantodolares_AVG, Master_mpagado_AVG, '+",
	                "    'Master_mpagosdolares_AVG, Master_mconsumototal_AVG, Visa_mfinanciacion_limite_AVG, '+",
	                "    'Visa_mlimitecompra_AVG, Visa_madelantodolares_AVG, Visa_mpagado_AVG, '+",
	                "    'jestado_visa_ratio_consumo_limite_AVG, tarjeta_mfinanciacion_limite_AVG, '+",
	                "    'tarjeta_msaldodolares_AVG, tarjeta_mconsumospesos_AVG, tarjeta_mconsumosdolares_AVG, '+",
	                "    'tarjeta_mlimitecompra_AVG, tarjeta_madelantodolares_AVG, tarjeta_mpagosdolares_AVG, '+",
	                "    'tarjeta_mconsumototal_AVG FROM dmf.historia_rework_with_cols_histo_2 where foto_mes=201504'",
	                "  /ASSUMEDSTRWIDTH=255.",
	                "",
	                "CACHE.",
	                "EXECUTE.",
	                "DATASET NAME DataSet1 WINDOW=FRONT.",
	                
	    			"* Define Variable Properties.",
	    			"*clase.",
	    			"VALUE LABELS clase",
	    			"  'BAJA+1                  ' 'NEGATIVO'",
	    			"  'BAJA+2                  ' 'POSITIVO'",
	    			"  'CONTINUA                ' 'NEGATIVO'.",
	    			"EXECUTE.",
	    			"SET SEED="+configArbol.getSeed()+".",
	                "EXECUTE.",
	                "* Decision Tree.",
	                "TREE clase [n] BY numero_de_cliente [s] foto_mes [s] marketing_activo_ultimos90dias [n] cliente_vip [n] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados [s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s]",
	                "ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [s] Master_mfinanciacion_limite [s] Master_Fvencimiento [s] Master_Finiciomora [s] Master_msaldototal [s] Master_msaldopesos [s] Master_msaldodolares [s] Master_mconsumospesos [s] Master_mconsumosdolares [s] Master_mlimitecompra [s] Master_madelantopesos [s] Master_madelantodolares [s] Master_fultimo_cierre [s] Master_mpagado [s] Master_mpagospesos [s] Master_mpagosdolares [s]",
	                "Master_fechaalta [s] Master_mconsumototal [s] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [s] Visa_marca_atraso [n] Visa_cuenta_estado [s] Visa_mfinanciacion_limite [s] Visa_Fvencimiento [s] Visa_Finiciomora [s] Visa_msaldototal [s] Visa_msaldopesos [s] Visa_msaldodolares [s] Visa_mconsumospesos [s] Visa_mconsumosdolares [s] Visa_mlimitecompra [s] Visa_madelantopesos [s] Visa_madelantodolares [s] Visa_fultimo_cierre [s] Visa_mpagado [s] Visa_mpagospesos [s] Visa_mpagosdolares [s]",
	                "Visa_fechaalta [s] Visa_mconsumototal [s] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [s] participa [n] jestado_seguros [n] jestado_debitos [n] jestado_contacto [n] jestado_visa_dias_mora_relativa_periodo [s] jestado_master_dias_mora_relativa_periodo [s] jestado_visa_ratio_consumo_limite [s] jestado_master_ratio_consumo_limite [s] jestado_visa_ratio_minimo_consumo_limite [s] jestado_master_ratio_minimo_consumo_limite [s] tarjeta_marca_atraso [n] tarjeta_cuenta_estado [s]",
	                "tarjeta_mfinanciacion_limite [s] tarjeta_Fvencimiento [n] tarjeta_Finiciomora [n] jestado_tarjeta_dias_mora_relativa_periodo [s] jestado_tarjeta_ratio_consumo_limite [s] jestado_tarjeta_ratio_minimo_consumo_limite [s] tarjeta_msaldototal [s] tarjeta_msaldopesos [s] tarjeta_msaldodolares [s] tarjeta_mconsumospesos [s] tarjeta_mconsumosdolares [s] tarjeta_mlimitecompra [s] tarjeta_madelantopesos [s] tarjeta_madelantodolares [s] tarjeta_fultimo_cierre [n] tarjeta_mpagado [s] tarjeta_mpagospesos [s]",
	                "tarjeta_mpagosdolares [s] tarjeta_fechaalta [n] tarjeta_mconsumototal [s] tarjeta_tconsumos [n] tarjeta_tadelantosefectivo [n] tarjeta_mpagominimo [s] mrentabilidad_AVG [s] mcomisiones_AVG [s] Master_mfinanciacion_limite_AVG [s] Master_msaldodolares_AVG [s] Master_mconsumospesos_AVG [s] Master_mconsumosdolares_AVG [s] Master_mlimitecompra_AVG [s] Master_madelantodolares_AVG [s] Master_mpagado_AVG [s] Master_mpagosdolares_AVG [s] Master_mconsumototal_AVG [s] Visa_mfinanciacion_limite_AVG [s]",
	                "Visa_mlimitecompra_AVG [s] Visa_madelantodolares_AVG [s] Visa_mpagado_AVG [s] jestado_visa_ratio_consumo_limite_AVG [s] tarjeta_mfinanciacion_limite_AVG [s] tarjeta_msaldodolares_AVG [s] tarjeta_mconsumospesos_AVG [s] tarjeta_mconsumosdolares_AVG [s] tarjeta_mlimitecompra_AVG [s] tarjeta_madelantodolares_AVG [s] tarjeta_mpagosdolares_AVG [s] tarjeta_mconsumototal_AVG [s]",
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
	                "EXECUTE.",
					"OMSEND."};
	
			
			return result;
			
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}
	}
	
}