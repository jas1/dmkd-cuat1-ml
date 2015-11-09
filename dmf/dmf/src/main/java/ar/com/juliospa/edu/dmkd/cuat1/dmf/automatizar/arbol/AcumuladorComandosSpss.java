package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol;

import java.io.File;


/**
 * la idea de este es tener todos los comandos e ir documentando de que se tratan cada uno
 * @author julio
 *
 */
public class AcumuladorComandosSpss {
	
	/**
	 * SI AGREGAS NUEVOS PONELE LO QUE HACE ! 
	
	 */
	public static String[] comandoGenerarArchivosTarjetaHistoriaAVG(String aOutPut, String infilePathAndName) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
		String outPut = aOutPut;
		if (!outPut.endsWith("/")) {
			outPut = outPut + "/";
		}
		
		String carpetaOutput = outPut+timeStamp;
		File carpetaOut = new File(carpetaOutput);
		if (carpetaOut.mkdirs()) {
			
			String outFileName = carpetaOutput+"/"+"output.html";
			String inFile = infilePathAndName;
			String tmpDataSet= infilePathAndName.substring(infilePathAndName.lastIndexOf("/")).replace(".txt", "")+"_dataset";
			String outFileNameForNewFile = infilePathAndName.substring(infilePathAndName.lastIndexOf("/")).replace(".txt", ".sav");
			String outFile = carpetaOutput+outFileNameForNewFile;

			String[] result = {
					"OMS",
					"/DESTINATION FORMAT=HTML OUTFILE='"+outFileName+"'.",
					"GET DATA  /TYPE=TXT",
					"  /FILE='"+inFile+"'",
					"  /ENCODING='Locale'",
					"  /DELCASE=LINE",
					"  /DELIMITERS='|'",
					"  /ARRANGEMENT=DELIMITED",
					"  /FIRSTCASE=2",
					"  /IMPORTCASE=ALL",
					"  /VARIABLES=",
					"  numero_de_cliente F7.0",
					"  foto_mes F6.0",
					"  marketing_activo_ultimos90dias F1.0",
					"  cliente_vip F1.0",
					"  cliente_sucursal F4.0",
					"  cliente_edad F2.0",
					"  cliente_antiguedad F3.0",
					"  mrentabilidad F1.0",
					"  mrentabilidad_annual F1.0",
					"  mcomisiones F1.0",
					"  mactivos_margen F1.0",
					"  mpasivos_margen F1.0",
					"  marketing_coss_selling F2.0",
					"  tpaquete2 F1.0",
					"  tpaquete4 F1.0",
					"  tpaquete6 F1.0",
					"  tpaquete7 F1.0",
					"  tpaquete9 F1.0",
					"  tcuentas F1.0",
					"  tcuenta_corriente F1.0",
					"  mcuenta_corriente_Nopaquete F1.0",
					"  mcuenta_corriente_Paquete F9.0",
					"  mcuenta_corriente_dolares F1.0",
					"  tcaja_ahorro F1.0",
					"  mcaja_ahorro_Paquete F9.0",
					"  mcaja_ahorro_Nopaquete F9.0",
					"  mcaja_ahorro_dolares F9.0",
					"  mdescubierto_preacordado F4.0",
					"  mcuentas_saldo F9.0",
					"  ttarjeta_debito F1.0",
					"  ctarjeta_debito_transacciones F2.0",
					"  mautoservicio F8.0",
					"  ttarjeta_visa F1.0",
					"  ctarjeta_visa_transacciones F2.0",
					"  mtarjeta_visa_consumo F8.0",
					"  ttarjeta_master F1.0",
					"  ctarjeta_master_transacciones F2.0",
					"  mtarjeta_master_consumo F8.0",
					"  cprestamos_personales F1.0",
					"  mprestamos_personales F9.0",
					"  cprestamos_prendarios F1.0",
					"  mprestamos_prendarios F9.0",
					"  cprestamos_hipotecarios F1.0",
					"  mprestamos_hipotecarios F9.0",
					"  tplazo_fijo F1.0",
					"  mplazo_fijo_dolares F9.0",
					"  mplazo_fijo_pesos F8.0",
					"  tfondos_comunes_inversion F1.0",
					"  mfondos_comunes_inversion_pesos F9.0",
					"  mfondos_comunes_inversion_dolares F6.0",
					"  ttitulos F1.0",
					"  mtitulos F9.0",
					"  tseguro_vida_mercado_abierto F1.0",
					"  tseguro_auto F1.0",
					"  tseguro_vivienda F1.0",
					"  tseguro_accidentes_personales F1.0",
					"  tcaja_seguridad F1.0",
					"  tplan_sueldo F1.0",
					"  mplan_sueldo F8.0",
					"  mplan_sueldo_manual F1.0",
					"  cplan_sueldo_transaccion F1.0",
					"  tcuenta_debitos_automaticos F1.0",
					"  mcuenta_debitos_automaticos F8.0",
					"  ttarjeta_visa_debitos_automaticos F1.0",
					"  mttarjeta_visa_debitos_automaticos F8.0",
					"  ttarjeta_master_debitos_automaticos F1.0",
					"  mttarjeta_master_debitos_automaticos F7.0",
					"  tpagodeservicios F1.0",
					"  mpagodeservicios F7.0",
					"  tpagomiscuentas F1.0",
					"  mpagomiscuentas F8.0",
					"  ccajeros_propios_descuentos F2.0",
					"  mcajeros_propios_descuentos F6.0",
					"  ctarjeta_visa_descuentos F2.0",
					"  mtarjeta_visa_descuentos F7.0",
					"  ctarjeta_master_descuentos F2.0",
					"  mtarjeta_master_descuentos F6.0",
					"  ccomisiones_mantenimiento F1.0",
					"  mcomisiones_mantenimiento F4.0",
					"  ccomisiones_otras F1.0",
					"  mcomisiones_otras F1.0",
					"  tcambio_monedas F1.0",
					"  ccambio_monedas_compra F1.0",
					"  mcambio_monedas_compra F1.0",
					"  ccambio_monedas_venta F1.0",
					"  mcambio_monedas_venta F7.0",
					"  ctransferencias_recibidas F1.0",
					"  mtransferencias_recibidas F4.0",
					"  ctransferencias_emitidas F2.0",
					"  mtransferencias_emitidas F8.0",
					"  cextraccion_autoservicio F2.0",
					"  mextraccion_autoservicio F7.0",
					"  ccheques_depositados F2.0",
					"  mcheques_depositados F9.0",
					"  ccheques_emitidos F2.0",
					"  mcheques_emitidos F9.0",
					"  ccheques_depositados_rechazados F1.0",
					"  mcheques_depositados_rechazados F7.0",
					"  ccheques_emitidos_rechazados F1.0",
					"  mcheques_emitidos_rechazados F6.0",
					"  tcallcenter F1.0",
					"  ccallcenter_transacciones F2.0",
					"  thomebanking F1.0",
					"  chomebanking_transacciones F3.0",
					"  tautoservicio F1.0",
					"  cautoservicio_transacciones F1.0",
					"  tcajas F1.0",
					"  tcajas_consultas F1.0",
					"  tcajas_depositos F1.0",
					"  tcajas_extracciones F1.0",
					"  tcajas_otras F1.0",
					"  ccajeros_propio_transacciones F2.0",
					"  mcajeros_propio F6.0",
					"  ccajeros_ajenos_transacciones F2.0",
					"  mcajeros_ajenos F6.0",
					"  tmovimientos_ultimos90dias F1.0",
					"  Master_marca_atraso A2",
					"  Master_cuenta_estado A2",
					"  Master_mfinanciacion_limite A9",
					"  Master_Fvencimiento A8",
					"  Master_Finiciomora A8",
					"  Master_msaldototal A9",
					"  Master_msaldopesos A9",
					"  Master_msaldodolares A8",
					"  Master_mconsumospesos A8",
					"  Master_mconsumosdolares A7",
					"  Master_mlimitecompra A9",
					"  Master_madelantopesos A7",
					"  Master_madelantodolares A7",
					"  Master_fultimo_cierre A8",
					"  Master_mpagado A8",
					"  Master_mpagospesos A9",
					"  Master_mpagosdolares A7",
					"  Master_fechaalta A8",
					"  Master_mconsumototal A8",
					"  Master_tconsumos F1.0",
					"  Master_tadelantosefectivo F1.0",
					"  Master_mpagominimo A7",
					"  Visa_marca_atraso A2",
					"  Visa_cuenta_estado A2",
					"  Visa_mfinanciacion_limite A9",
					"  Visa_Fvencimiento A8",
					"  Visa_Finiciomora A2",
					"  Visa_msaldototal A8",
					"  Visa_msaldopesos A8",
					"  Visa_msaldodolares A8",
					"  Visa_mconsumospesos A8",
					"  Visa_mconsumosdolares A7",
					"  Visa_mlimitecompra A9",
					"  Visa_madelantopesos A7",
					"  Visa_madelantodolares A6",
					"  Visa_fultimo_cierre A8",
					"  Visa_mpagado A8",
					"  Visa_mpagospesos A9",
					"  Visa_mpagosdolares A7",
					"  Visa_fechaalta A8",
					"  Visa_mconsumototal A8",
					"  Visa_tconsumos F1.0",
					"  Visa_tadelantosefectivo F1.0",
					"  Visa_mpagominimo A7",
					"  participa F1.0",
					"  clase A8",
					"  tarjeta_marca_atraso A3",
					"  tarjeta_cuenta_estado A5",
					"  tarjeta_mfinanciacion_limite A18",
					"  tarjeta_Fvencimiento A17",
					"  tarjeta_Finiciomora A2",
					"  tarjeta_msaldototal A18",
					"  tarjeta_msaldopesos A18",
					"  tarjeta_msaldodolares A9",
					"  tarjeta_mconsumospesos A18",
					"  tarjeta_mconsumosdolares A17",
					"  tarjeta_mlimitecompra A18",
					"  tarjeta_madelantopesos A7",
					"  tarjeta_madelantodolares A7",
					"  tarjeta_fultimo_cierre A17",
					"  tarjeta_mpagado A8",
					"  tarjeta_mpagospesos A19",
					"  tarjeta_mpagosdolares A18",
					"  tarjeta_fechaalta A17",
					"  tarjeta_mconsumototal A18",
					"  tarjeta_tconsumos A3",
					"  tarjeta_tadelantosefectivo A3",
					"  tarjeta_mpagominimo A7",
					"  AVG_mrentabilidad F7.0",
					"  AVG_mrentabilidad_annual F8.0",
					"  AVG_mcomisiones F7.0",
					"  AVG_mactivos_margen F7.0",
					"  AVG_mpasivos_margen F7.0",
					"  AVG_mcuenta_corriente_Nopaquete F4.0",
					"  AVG_mcuenta_corriente_Paquete F8.0",
					"  AVG_mcuenta_corriente_dolares F4.0",
					"  AVG_mcaja_ahorro_Paquete F9.0",
					"  AVG_mcaja_ahorro_Nopaquete F9.0",
					"  AVG_mcaja_ahorro_dolares F9.0",
					"  AVG_mdescubierto_preacordado F4.0",
					"  AVG_mcuentas_saldo F9.0",
					"  AVG_mautoservicio F8.0",
					"  AVG_mtarjeta_visa_consumo F8.0",
					"  AVG_mtarjeta_master_consumo F8.0",
					"  AVG_mprestamos_personales F9.0",
					"  AVG_mprestamos_prendarios F8.0",
					"  AVG_mprestamos_hipotecarios F9.0",
					"  AVG_mplazo_fijo_dolares F9.0",
					"  AVG_mplazo_fijo_pesos F8.0",
					"  AVG_mfondos_comunes_inversion_pesos F9.0",
					"  AVG_mfondos_comunes_inversion_dolares F6.0",
					"  AVG_mtitulos F9.0",
					"  AVG_mplan_sueldo F8.0",
					"  AVG_mplan_sueldo_manual F4.0",
					"  AVG_mcuenta_debitos_automaticos F8.0",
					"  AVG_mttarjeta_visa_debitos_automaticos F8.0",
					"  AVG_mttarjeta_master_debitos_automaticos F7.0",
					"  AVG_mpagodeservicios F7.0",
					"  AVG_mpagomiscuentas F8.0",
					"  AVG_mcajeros_propios_descuentos F6.0",
					"  AVG_mtarjeta_visa_descuentos F7.0",
					"  AVG_mtarjeta_master_descuentos F6.0",
					"  AVG_mcomisiones_mantenimiento F6.0",
					"  AVG_mcomisiones_otras F7.0",
					"  AVG_mcambio_monedas_compra F5.0",
					"  AVG_mcambio_monedas_venta F7.0",
					"  AVG_mtransferencias_recibidas F7.0",
					"  AVG_mtransferencias_emitidas F8.0",
					"  AVG_mextraccion_autoservicio F8.0",
					"  AVG_mcheques_depositados F9.0",
					"  AVG_mcheques_emitidos F9.0",
					"  AVG_mcheques_depositados_rechazados F7.0",
					"  AVG_mcheques_emitidos_rechazados F7.0",
					"  AVG_mcajeros_propio F8.0",
					"  AVG_mcajeros_ajenos F8.0",
					"  AVG_Master_mfinanciacion_limite A9",
					"  AVG_Master_msaldototal A8",
					"  AVG_Master_msaldopesos A8",
					"  AVG_Master_msaldodolares A8",
					"  AVG_Master_mconsumospesos A8",
					"  AVG_Master_mconsumosdolares A7",
					"  AVG_Master_mlimitecompra A9",
					"  AVG_Master_madelantopesos A7",
					"  AVG_Master_madelantodolares A6",
					"  AVG_Master_mpagado A8",
					"  AVG_Master_mpagospesos A9",
					"  AVG_Master_mpagosdolares A6",
					"  AVG_Master_mconsumototal A8",
					"  AVG_Master_mpagominimo A7",
					"  AVG_Visa_mfinanciacion_limite A9",
					"  AVG_Visa_msaldototal A8",
					"  AVG_Visa_msaldopesos A8",
					"  AVG_Visa_msaldodolares A8",
					"  AVG_Visa_mconsumospesos A8",
					"  AVG_Visa_mconsumosdolares A7",
					"  AVG_Visa_mlimitecompra A9",
					"  AVG_Visa_madelantopesos A7",
					"  AVG_Visa_madelantodolares A6",
					"  AVG_Visa_mpagado A8",
					"  AVG_Visa_mpagospesos A9",
					"  AVG_Visa_mpagosdolares A7",
					"  AVG_Visa_mconsumototal A8",
					"  AVG_Visa_mpagominimo A8",
					"  AVG_tarjeta_mfinanciacion_limite A9",
					"  AVG_tarjeta_msaldototal A9",
					"  AVG_tarjeta_msaldopesos A9",
					"  AVG_tarjeta_msaldodolares A8",
					"  AVG_tarjeta_mconsumospesos A8",
					"  AVG_tarjeta_mconsumosdolares A7",
					"  AVG_tarjeta_mlimitecompra A9",
					"  AVG_tarjeta_madelantopesos A7",
					"  AVG_tarjeta_madelantodolares A6",
					"  AVG_tarjeta_mpagado A8",
					"  AVG_tarjeta_mpagospesos A9",
					"  AVG_tarjeta_mpagosdolares A7",
					"  AVG_tarjeta_mconsumototal A8",
					"  AVG_tarjeta_mpagominimo A8.",
					"CACHE.",
					"EXECUTE.",
					"DATASET NAME "+tmpDataSet+" WINDOW=FRONT.",
					"",
					"SAVE OUTFILE='"+outFile+"'",
					"  /COMPRESSED.",
					"OMSEND."
					};
			return result;
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}
	}

	
	
	
	/**
	 * para correr el arbol nuevo de tarjetas con historia y average
	 * @param configArbol
	 * @return
	 */
	public static String[] comandoArbolTarjetasHistoriaAVG(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
		configArbol.setTimeStampFolder(timeStamp);
		
		String carpetaOutput = configArbol.getOutputFolder()+timeStamp;
		File carpetaOut = new File(carpetaOutput);
		if (carpetaOut.mkdirs()) {
			String fileXmlTest = carpetaOutput+"/"+"test_model.xml";
			String fileXmlTrain = carpetaOutput+"/"+"train_model.xml";
			String fileSql = carpetaOutput+"/"+"reglas.sql";
			String outFileName = carpetaOutput+"/"+"output.html";
			
			
			String columnas = 
					"numero_de_cliente [s] foto_mes [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [n] mrentabilidad_annual [n] mcomisiones [n] mactivos_margen [n] mpasivos_margen [n] marketing_coss_selling [n] tpaquete2 [n] tpaquete4 [n] tpaquete6 [n] tpaquete7 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete [s] mcuenta_corriente_dolares [n] tcaja_ahorro [n]"+
					"mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [n] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s] cprestamos_hipotecarios [n] mprestamos_hipotecarios"+
					"[s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s] cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n]"+
					"mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [s] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s] mtarjeta_master_descuentos [s] ccomisiones_mantenimiento [n] mcomisiones_mantenimiento [n] ccomisiones_otras [n] mcomisiones_otras [n] tcambio_monedas [n]"+
					"ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s] mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados"+
					"[s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s] ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [n] Master_mfinanciacion_limite [n] Master_Fvencimiento [n] Master_Finiciomora [n]"+
					"Master_msaldototal [n] Master_msaldopesos [n] Master_msaldodolares [n] Master_mconsumospesos [n] Master_mconsumosdolares [n] Master_mlimitecompra [n] Master_madelantopesos [n] Master_madelantodolares [n] Master_fultimo_cierre [n] Master_mpagado [n] Master_mpagospesos [n] Master_mpagosdolares [n] Master_fechaalta [n] Master_mconsumototal [n] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [n] Visa_marca_atraso [n] Visa_cuenta_estado [n] Visa_mfinanciacion_limite [n] Visa_Fvencimiento"+
					"[n] Visa_Finiciomora [n] Visa_msaldototal [n] Visa_msaldopesos [n] Visa_msaldodolares [n] Visa_mconsumospesos [n] Visa_mconsumosdolares [n] Visa_mlimitecompra [n] Visa_madelantopesos [n] Visa_madelantodolares [n] Visa_fultimo_cierre [n] Visa_mpagado [n] Visa_mpagospesos [n] Visa_mpagosdolares [n] Visa_fechaalta [n] Visa_mconsumototal [n] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [n] participa [n] tarjeta_marca_atraso [n] tarjeta_cuenta_estado [n] tarjeta_mfinanciacion_limite [n]"+
					"tarjeta_Fvencimiento [n] tarjeta_Finiciomora [n] tarjeta_msaldototal [n] tarjeta_msaldopesos [n] tarjeta_msaldodolares [n] tarjeta_mconsumospesos [n] tarjeta_mconsumosdolares [n] tarjeta_mlimitecompra [n] tarjeta_madelantopesos [n] tarjeta_madelantodolares [n] tarjeta_fultimo_cierre [n] tarjeta_mpagado [n] tarjeta_mpagospesos [n] tarjeta_mpagosdolares [n] tarjeta_fechaalta [n] tarjeta_mconsumototal [n] tarjeta_tconsumos [n] tarjeta_tadelantosefectivo [n] tarjeta_mpagominimo [n] AVG_mrentabilidad [n]"+
					"AVG_mrentabilidad_annual [n] AVG_mcomisiones [n] AVG_mactivos_margen [n] AVG_mpasivos_margen [n] AVG_mcuenta_corriente_Nopaquete [n] AVG_mcuenta_corriente_Paquete [n] AVG_mcuenta_corriente_dolares [n] AVG_mcaja_ahorro_Paquete [n] AVG_mcaja_ahorro_Nopaquete [n] AVG_mcaja_ahorro_dolares [n] AVG_mdescubierto_preacordado [n] AVG_mcuentas_saldo [n] AVG_mautoservicio [n] AVG_mtarjeta_visa_consumo [n] AVG_mtarjeta_master_consumo [n] AVG_mprestamos_personales [n] AVG_mprestamos_prendarios [n]"+
					"AVG_mprestamos_hipotecarios [n] AVG_mplazo_fijo_dolares [n] AVG_mplazo_fijo_pesos [n] AVG_mfondos_comunes_inversion_pesos [n] AVG_mfondos_comunes_inversion_dolares [n] AVG_mtitulos [n] AVG_mplan_sueldo [n] AVG_mplan_sueldo_manual [n] AVG_mcuenta_debitos_automaticos [n] AVG_mttarjeta_visa_debitos_automaticos [n] AVG_mttarjeta_master_debitos_automaticos [n] AVG_mpagodeservicios [n] AVG_mpagomiscuentas [n] AVG_mcajeros_propios_descuentos [n] AVG_mtarjeta_visa_descuentos [n] AVG_mtarjeta_master_descuentos [n]"+
					"AVG_mcomisiones_mantenimiento [n] AVG_mcomisiones_otras [n] AVG_mcambio_monedas_compra [n] AVG_mcambio_monedas_venta [n] AVG_mtransferencias_recibidas [n] AVG_mtransferencias_emitidas [n] AVG_mextraccion_autoservicio [n] AVG_mcheques_depositados [n] AVG_mcheques_emitidos [n] AVG_mcheques_depositados_rechazados [n] AVG_mcheques_emitidos_rechazados [n] AVG_mcajeros_propio [n] AVG_mcajeros_ajenos [n] AVG_Master_mfinanciacion_limite [n] AVG_Master_msaldototal [n] AVG_Master_msaldopesos [n]"+
					"AVG_Master_msaldodolares [n] AVG_Master_mconsumospesos [n] AVG_Master_mconsumosdolares [n] AVG_Master_mlimitecompra [n] AVG_Master_madelantopesos [n] AVG_Master_madelantodolares [n] AVG_Master_mpagado [n] AVG_Master_mpagospesos [n] AVG_Master_mpagosdolares [n] AVG_Master_mconsumototal [n] AVG_Master_mpagominimo [n] AVG_Visa_mfinanciacion_limite [n] AVG_Visa_msaldototal [n] AVG_Visa_msaldopesos [n] AVG_Visa_msaldodolares [n] AVG_Visa_mconsumospesos [n] AVG_Visa_mconsumosdolares [n] AVG_Visa_mlimitecompra"+
					"[n] AVG_Visa_madelantopesos [n] AVG_Visa_madelantodolares [n] AVG_Visa_mpagado [n] AVG_Visa_mpagospesos [n] AVG_Visa_mpagosdolares [n] AVG_Visa_mconsumototal [n] AVG_Visa_mpagominimo [n] AVG_tarjeta_mfinanciacion_limite [n] AVG_tarjeta_msaldototal [n] AVG_tarjeta_msaldopesos [n] AVG_tarjeta_msaldodolares [n] AVG_tarjeta_mconsumospesos [n] AVG_tarjeta_mconsumosdolares [n] AVG_tarjeta_mlimitecompra [n] AVG_tarjeta_madelantopesos [n] AVG_tarjeta_madelantodolares [n] AVG_tarjeta_mpagado [n]"+
					"AVG_tarjeta_mpagospesos [n] AVG_tarjeta_mpagosdolares [n] AVG_tarjeta_mconsumototal [n] AVG_tarjeta_mpagominimo [n]";
			
			String[] result = {
					"GET",
					"FILE='"+configArbol.getOrigenDatosSav()+"'.",
					"EXECUTE.",
					"OMS",
					"/DESTINATION FORMAT=HTML OUTFILE='"+outFileName+"'.",
					"DATASET NAME DataSet1 WINDOW=FRONT.",
					"SET SEED="+configArbol.getSeed()+".",
					"* Define Variable Properties.",
					"*clase.",
					"VALUE LABELS clase",
					"'BAJA+1  ' 'NEGATIVO'",
					"'BAJA+2  ' 'POSITIVO'",
					"'CONTINUA' 'NEGATIVO'.",
					"EXECUTE.",
					"* Decision Tree.",
					"TREE clase [n] BY ",
					columnas,
					"/TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
					"/DEPCATEGORIES USEVALUES=['BAJA+1' 'BAJA+2' 'CONTINUA'] TARGET=['BAJA+2']",
					"/PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE",
					"/RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+fileSql+"'",
					"/GAIN SUMMARYTABLE=YES CATEGORYTABLE=YES TYPE=[NODE] SORT=DESCENDING CUMULATIVE=NO",
					"/SAVE NODEID PREDVAL PREDPROB",
					"/METHOD TYPE=CHAID",
					"/GROWTHLIMIT MAXDEPTH="+configArbol.getMaximaProfundidad()+" MINPARENTSIZE="+configArbol.getMinParentSize()+" MINCHILDSIZE="+configArbol.getMinChildSize(),
					"/VALIDATION TYPE=SPLITSAMPLE(70.00) OUTPUT=BOTHSAMPLES",
					"/CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10",
					"/COSTS CUSTOM= 'BAJA+1' 'BAJA+1' [0] 'BAJA+1' 'BAJA+2' [200] 'BAJA+1' 'CONTINUA' [0]  'BAJA+2' 'BAJA+1' [7800] 'BAJA+2' 'BAJA+2' [0] 'BAJA+2' 'CONTINUA' [7800]  'CONTINUA' 'BAJA+1' [0] 'CONTINUA' 'BAJA+2' [200] 'CONTINUA' 'CONTINUA' [0]",
					"/PROFITS CUSTOM='BAJA+1' [0 200] 'BAJA+2' [8000 200] 'CONTINUA' [0 200]",
					"/OUTFILE TRAININGMODEL='"+fileXmlTrain+"' TESTMODEL='"+fileXmlTest+"'",
					"/MISSING NOMINALMISSING=MISSING.",
					"OMSEND."
					};
			return result;
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}
	}
	
	
	/**
	 * este arbol tiene las variables de tarjetas
	 * @param configArbol
	 * @return
	 */
	public static String[] comandoArbolTarjetas20151107(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
		configArbol.setTimeStampFolder(timeStamp);
		
		String carpetaOutput = configArbol.getOutputFolder()+timeStamp;
		File carpetaOut = new File(carpetaOutput);
		if (carpetaOut.mkdirs()) {
			String fileXmlTest = carpetaOutput+"/"+"test_model.xml";
			String fileXmlTrain = carpetaOutput+"/"+"train_model.xml";
			String fileSql = carpetaOutput+"/"+"reglas.sql";
			String outFileName = carpetaOutput+"/"+"output.html";
			
			
			String columnas = 
					"numero_de_cliente [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [n] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete2 [n] tpaquete4 [n] tpaquete6 [n] tpaquete7 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete [s] mcuenta_corriente_dolares [n] tcaja_ahorro [n]"+
					"mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [n] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s] cprestamos_hipotecarios [n] mprestamos_hipotecarios"+
					"[s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s] cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n]"+
					"mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [n] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s] mtarjeta_master_descuentos [s] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n]"+
					"ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s] mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados"+
					"[s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s] ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [n] Master_mfinanciacion_limite [n] Master_Fvencimiento [n] Master_Finiciomora [n]"+
					"Master_msaldototal [n] Master_msaldopesos [n] Master_msaldodolares [n] Master_mconsumospesos [n] Master_mconsumosdolares [n] Master_mlimitecompra [n] Master_madelantopesos [n] Master_madelantodolares [n] Master_fultimo_cierre [n] Master_mpagado [n] Master_mpagospesos [n] Master_mpagosdolares [n] Master_fechaalta [n] Master_mconsumototal [n] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [n] Visa_marca_atraso [n] Visa_cuenta_estado [n] Visa_mfinanciacion_limite [n] Visa_Fvencimiento"+
					"[n] Visa_Finiciomora [n] Visa_msaldototal [n] Visa_msaldopesos [n] Visa_msaldodolares [n] Visa_mconsumospesos [n] Visa_mconsumosdolares [n] Visa_mlimitecompra [n] Visa_madelantopesos [n] Visa_madelantodolares [n] Visa_fultimo_cierre [n] Visa_mpagado [n] Visa_mpagospesos [n] Visa_mpagosdolares [n] Visa_fechaalta [n] Visa_mconsumototal [n] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [n] participa [n] tarjeta_marca_atraso [n] tarjeta_cuenta_estado [n] tarjeta_mfinanciacion_limite [n]"+
					"tarjeta_Fvencimiento [n] tarjeta_Finiciomora [n] tarjeta_msaldototal [n] tarjeta_msaldopesos [n] tarjeta_msaldodolares [n] tarjeta_mconsumospesos [n] tarjeta_mconsumosdolares [n] tarjeta_mlimitecompra [n] tarjeta_madelantopesos [n] tarjeta_madelantodolares [n] tarjeta_fultimo_cierre [n] tarjeta_mpagado [n] tarjeta_mpagospesos [n] tarjeta_mpagosdolares [n] tarjeta_fechaalta [n] tarjeta_mconsumototal [n] tarjeta_tconsumos [n] tarjeta_tadelantosefectivo [n] tarjeta_mpagominimo [n]";
			
			String[] result = {
					"OMS",
					"/DESTINATION FORMAT=HTML OUTFILE='"+outFileName+"'.",
					"GET",
					"FILE='"+configArbol.getOrigenDatosSav()+"'.",
					"DATASET NAME DataSet1 WINDOW=FRONT.",
					"SET SEED="+configArbol.getSeed()+".",
					"* Define Variable Properties.",
					"*clase.",
					"VALUE LABELS clase",
					"'BAJA+1  ' 'NEGATIVO'",
					"'BAJA+2  ' 'POSITIVO'",
					"'CONTINUA' 'NEGATIVO'.",
					"EXECUTE.",
					"* Decision Tree.",
					"TREE clase [n] BY ",
					columnas,
					"/TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
					"/DEPCATEGORIES USEVALUES=['BAJA+1' 'BAJA+2' 'CONTINUA'] TARGET=['BAJA+2']",
					"/PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE",
					"/RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+fileSql+"'",
					"/GAIN SUMMARYTABLE=YES CATEGORYTABLE=YES TYPE=[NODE] SORT=DESCENDING CUMULATIVE=NO",
					"/SAVE NODEID PREDVAL PREDPROB",
					"/METHOD TYPE=CHAID",
					"/GROWTHLIMIT MAXDEPTH="+configArbol.getMaximaProfundidad()+" MINPARENTSIZE="+configArbol.getMinParentSize()+" MINCHILDSIZE="+configArbol.getMinChildSize(),
					"/VALIDATION TYPE=SPLITSAMPLE(70.00) OUTPUT=BOTHSAMPLES",
					"/CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10",
					"/COSTS CUSTOM= 'BAJA+1' 'BAJA+1' [0] 'BAJA+1' 'BAJA+2' [200] 'BAJA+1' 'CONTINUA' [0]  'BAJA+2' 'BAJA+1' [7800] 'BAJA+2' 'BAJA+2' [0] 'BAJA+2' 'CONTINUA' [7800]  'CONTINUA' 'BAJA+1' [0] 'CONTINUA' 'BAJA+2' [200] 'CONTINUA' 'CONTINUA' [0]",
					"/PROFITS CUSTOM='BAJA+1' [0 200] 'BAJA+2' [8000 200] 'CONTINUA' [0 200]",
					"/OUTFILE TRAININGMODEL='"+fileXmlTrain+"' TESTMODEL='"+fileXmlTest+"'",
					"/MISSING NOMINALMISSING=MISSING.",
					"OMSEND."
					};
			return result;
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}
	}
	
	/**
	 * train, test xml, divididon en 70/30
	 * agregada las label de positivo negativo
	 * columnas parametrizadas, 
	 * parte de ganancia
	 * @param configArbol
	 * @return
	 */
	public static String[] comandoArbolBase20151107(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
		configArbol.setTimeStampFolder(timeStamp);
		
		String carpetaOutput = configArbol.getOutputFolder()+timeStamp;
		File carpetaOut = new File(carpetaOutput);
		if (carpetaOut.mkdirs()) {
			String fileXmlTest = carpetaOutput+"/"+"test_model.xml";
			String fileXmlTrain = carpetaOutput+"/"+"train_model.xml";
			String fileSql = carpetaOutput+"/"+"reglas.sql";
			String outFileName = carpetaOutput+"/"+"output.html";
			
			
			String columnas = 
					"numero_de_cliente [n] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [s] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete2 [n] tpaquete4 [n] tpaquete6 [n] tpaquete7 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete [s] mcuenta_corriente_dolares [s] tcaja_ahorro [n]"+
					"mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [s] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [s] mprestamos_prendarios [s] cprestamos_hipotecarios [s] mprestamos_hipotecarios"+
					"[s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s] cplan_sueldo_transaccion [s] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n]"+
					"mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [s] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s] mtarjeta_master_descuentos [s] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n]"+
					"ccambio_monedas_compra [s] mcambio_monedas_compra [s] ccambio_monedas_venta [s] mcambio_monedas_venta [s] ctransferencias_recibidas [s] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s] mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [s] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados"+
					"[s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [s] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s] ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [n] Master_mfinanciacion_limite [n] Master_Fvencimiento [n] Master_Finiciomora [n]"+
					"Master_msaldototal [n] Master_msaldopesos [n] Master_msaldodolares [n] Master_mconsumospesos [n] Master_mconsumosdolares [n] Master_mlimitecompra [n] Master_madelantopesos [n] Master_madelantodolares [n] Master_fultimo_cierre [n] Master_mpagado [n] Master_mpagospesos [n] Master_mpagosdolares [n] Master_fechaalta [n] Master_mconsumototal [n] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [n] Visa_marca_atraso [n] Visa_cuenta_estado [n] Visa_mfinanciacion_limite [n] Visa_Fvencimiento"+
					"[n] Visa_Finiciomora [n] Visa_msaldototal [n] Visa_msaldopesos [n] Visa_msaldodolares [n] Visa_mconsumospesos [n] Visa_mconsumosdolares [n] Visa_mlimitecompra [n] Visa_madelantopesos [n] Visa_madelantodolares [n] Visa_fultimo_cierre [n] Visa_mpagado [n] Visa_mpagospesos [n] Visa_mpagosdolares [n] Visa_fechaalta [n] Visa_mconsumototal [n] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [n] participa [n]";
			
			String[] result = {
					"OMS",
					"/DESTINATION FORMAT=HTML OUTFILE='"+outFileName+"'.",
					"GET",
					"FILE='"+configArbol.getOrigenDatosSav()+"'.",
					"DATASET NAME DataSet1 WINDOW=FRONT.",
					"SET SEED="+configArbol.getSeed()+".",
					"* Define Variable Properties.",
					"*clase.",
					"VALUE LABELS clase",
					"'BAJA+1  ' 'NEGATIVO'",
					"'BAJA+2  ' 'POSITIVO'",
					"'CONTINUA' 'NEGATIVO'.",
					"EXECUTE.",
					"* Decision Tree.",
					"TREE clase [n] BY ",
					columnas,
					"/TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
					"/DEPCATEGORIES USEVALUES=['BAJA+1' 'BAJA+2' 'CONTINUA'] TARGET=['BAJA+2']",
					"/PRINT MODELSUMMARY CLASSIFICATION RISK CATEGORYSPECS TREETABLE",
					"/RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+fileSql+"'",
					"/GAIN SUMMARYTABLE=YES CATEGORYTABLE=YES TYPE=[NODE] SORT=DESCENDING CUMULATIVE=NO",
					"/SAVE NODEID PREDVAL PREDPROB",
					"/METHOD TYPE=CHAID",
					"/GROWTHLIMIT MAXDEPTH="+configArbol.getMaximaProfundidad()+" MINPARENTSIZE="+configArbol.getMinParentSize()+" MINCHILDSIZE="+configArbol.getMinChildSize(),
					"/VALIDATION TYPE=SPLITSAMPLE(70.00) OUTPUT=BOTHSAMPLES",
					"/CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10",
					"/COSTS CUSTOM= 'BAJA+1' 'BAJA+1' [0] 'BAJA+1' 'BAJA+2' [200] 'BAJA+1' 'CONTINUA' [0]  'BAJA+2' 'BAJA+1' [7800] 'BAJA+2' 'BAJA+2' [0] 'BAJA+2' 'CONTINUA' [7800]  'CONTINUA' 'BAJA+1' [0] 'CONTINUA' 'BAJA+2' [200] 'CONTINUA' 'CONTINUA' [0]",
					"/PROFITS CUSTOM='BAJA+1' [0 200] 'BAJA+2' [8000 200] 'CONTINUA' [0 200]",
					"/OUTFILE TRAININGMODEL='"+fileXmlTrain+"' TESTMODEL='"+fileXmlTest+"'",
					"/MISSING NOMINALMISSING=MISSING.",
					"OMSEND."
					};
			return result;
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}
	}
	
	
	public static String[] comandoArbolDefaultFixDelRawDeBaseFabiana(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
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
					"FILE='"+configArbol.getOrigenDatosSav()+"'.",
					"DATASET NAME DataSet1 WINDOW=FRONT.",
	                "* Decision Tree.",
	                "TREE clase [n] BY numero_de_cliente [s] foto_mes [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [n] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete1 [n] tpaquete2 [n] tpaquete3 [n] tpaquete4 [n] tpaquete5 [n] tpaquete6 [n] tpaquete7 [n] tpaquete8 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete",
	                "[s] mcuenta_corriente_dolares [n] tcaja_ahorro [n] mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [s] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s]",
	                "cprestamos_hipotecarios [n] mprestamos_hipotecarios [s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] mmonedas_extranjeras [n] minversiones_otras [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s]",
	                "cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n] mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [n] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s]",
	                "mtarjeta_master_descuentos [s] ccuenta_descuentos [n] mcuenta_descuentos [n] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n] ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s]",
	                "mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados [s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s]",
	                "ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [s] Master_mfinanciacion_limite [s] Master_Fvencimiento [s] Master_Finiciomora [s] Master_msaldototal [s] Master_msaldopesos [s] Master_msaldodolares [s] Master_mconsumospesos [s] Master_mconsumosdolares [s] Master_mlimitecompra [s] Master_madelantopesos [s] Master_madelantodolares [s] Master_fultimo_cierre [s] Master_mpagado [s] Master_mpagospesos [s] Master_mpagosdolares [s]",
	                "Master_fechaalta [s] Master_mconsumototal [s] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [s] Visa_marca_atraso [n] Visa_cuenta_estado [s] Visa_mfinanciacion_limite [s] Visa_Fvencimiento [s] Visa_Finiciomora [s] Visa_msaldototal [s] Visa_msaldopesos [s] Visa_msaldodolares [s] Visa_mconsumospesos [s] Visa_mconsumosdolares [s] Visa_mlimitecompra [s] Visa_madelantopesos [s] Visa_madelantodolares [s] Visa_fultimo_cierre [s] Visa_mpagado [s] Visa_mpagospesos [s] Visa_mpagosdolares [s]",
	                "Visa_fechaalta [s] Visa_mconsumototal [s] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [s] participa [n]",
	                "  /TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
	                "  /DEPCATEGORIES USEVALUES=[VALID]",
	                "  /PRINT MODELSUMMARY CLASSIFICATION RISK TREETABLE",
	                "  /METHOD TYPE=CHAID",
	                "  /GROWTHLIMIT MAXDEPTH=AUTO MINPARENTSIZE=100 MINCHILDSIZE=50",
	                "  /VALIDATION TYPE=NONE OUTPUT=BOTHSAMPLES",
	                "  /CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10",
	                "  /COSTS EQUAL",
	                "  /MISSING NOMINALMISSING=MISSING.",

					"OMSEND."};
	
			
			return result;
			
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}
	}
	
//	aca hay que hacer que se puedan elegir columnas
//    "TREE clase [n] BY numero_de_cliente [s] foto_mes [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [n] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete1 [n] tpaquete2 [n] tpaquete3 [n] tpaquete4 [n] tpaquete5 [n] tpaquete6 [n] tpaquete7 [n] tpaquete8 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete",
//    "[s] mcuenta_corriente_dolares [n] tcaja_ahorro [n] mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [n] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s]",
//    "cprestamos_hipotecarios [n] mprestamos_hipotecarios [s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] mbonos_gobierno [n] mmonedas_extranjeras [n] minversiones_otras [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s]",
//    "cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n] mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [n] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s]",
//    "mtarjeta_master_descuentos [s] ccuenta_descuentos [n] mcuenta_descuentos [n] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n] ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s]",
//    "mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados [s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s]",
//    "ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [n] Master_mfinanciacion_limite [n] Master_Fvencimiento [n] Master_Finiciomora [n] Master_msaldototal [n] Master_msaldopesos [n] Master_msaldodolares [n] Master_mconsumospesos [n] Master_mconsumosdolares [n] Master_mlimitecompra [n] Master_madelantopesos [n] Master_madelantodolares [n] Master_fultimo_cierre [n] Master_mpagado [n] Master_mpagospesos [n] Master_mpagosdolares [n]",
//    "Master_fechaalta [n] Master_mconsumototal [n] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [n] Visa_marca_atraso [n] Visa_cuenta_estado [n] Visa_mfinanciacion_limite [n] Visa_Fvencimiento [n] Visa_Finiciomora [n] Visa_msaldototal [n] Visa_msaldopesos [n] Visa_msaldodolares [n] Visa_mconsumospesos [n] Visa_mconsumosdolares [n] Visa_mlimitecompra [n] Visa_madelantopesos [n] Visa_madelantodolares [n] Visa_fultimo_cierre [n] Visa_mpagado [n] Visa_mpagospesos [n] Visa_mpagosdolares [n]",
//    "Visa_fechaalta [n] Visa_mconsumototal [n] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [n] participa [n]",
	
	public static String[] comandoArbolDefaultFixDelRawDeBase(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
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
	                "* Decision Tree.",
	                "TREE clase [n] BY numero_de_cliente [s] foto_mes [s] marketing_activo_ultimos90dias [n] cliente_vip [n] cliente_sucursal [s] cliente_edad [s] cliente_antiguedad [s] mrentabilidad [s] mrentabilidad_annual [n] mcomisiones [s] mactivos_margen [s] mpasivos_margen [s] marketing_coss_selling [n] tpaquete2 [n] tpaquete4 [n] tpaquete6 [n] tpaquete7 [n] tpaquete9 [n] tcuentas [n] tcuenta_corriente [n] mcuenta_corriente_Nopaquete [s] mcuenta_corriente_Paquete",
	                "[s] mcuenta_corriente_dolares [n] tcaja_ahorro [n] mcaja_ahorro_Paquete [s] mcaja_ahorro_Nopaquete [s] mcaja_ahorro_dolares [s] mdescubierto_preacordado [n] mcuentas_saldo [s] ttarjeta_debito [n] ctarjeta_debito_transacciones [s] mautoservicio [s] ttarjeta_visa [n] ctarjeta_visa_transacciones [s] mtarjeta_visa_consumo [s] ttarjeta_master [n] ctarjeta_master_transacciones [s] mtarjeta_master_consumo [s] cprestamos_personales [s] mprestamos_personales [s] cprestamos_prendarios [n] mprestamos_prendarios [s]",
	                "cprestamos_hipotecarios [n] mprestamos_hipotecarios [s] tplazo_fijo [n] mplazo_fijo_dolares [s] mplazo_fijo_pesos [s] tfondos_comunes_inversion [n] mfondos_comunes_inversion_pesos [s] mfondos_comunes_inversion_dolares [s] ttitulos [n] mtitulos [s] tseguro_vida_mercado_abierto [n] tseguro_auto [n] tseguro_vivienda [n] tseguro_accidentes_personales [n] tcaja_seguridad [n] tplan_sueldo [n] mplan_sueldo [s] mplan_sueldo_manual [s]",
	                "cplan_sueldo_transaccion [n] tcuenta_debitos_automaticos [n] mcuenta_debitos_automaticos [s] ttarjeta_visa_debitos_automaticos [n] mttarjeta_visa_debitos_automaticos [s] ttarjeta_master_debitos_automaticos [n] mttarjeta_master_debitos_automaticos [s] tpagodeservicios [n] mpagodeservicios [s] tpagomiscuentas [n] mpagomiscuentas [s] ccajeros_propios_descuentos [n] mcajeros_propios_descuentos [s] ctarjeta_visa_descuentos [s] mtarjeta_visa_descuentos [s] ctarjeta_master_descuentos [s]",
	                "mtarjeta_master_descuentos [s] ccomisiones_mantenimiento [s] mcomisiones_mantenimiento [s] ccomisiones_otras [s] mcomisiones_otras [s] tcambio_monedas [n] ccambio_monedas_compra [n] mcambio_monedas_compra [s] ccambio_monedas_venta [n] mcambio_monedas_venta [s] ctransferencias_recibidas [n] mtransferencias_recibidas [s] ctransferencias_emitidas [s] mtransferencias_emitidas [s] cextraccion_autoservicio [s] mextraccion_autoservicio [s] ccheques_depositados [s]",
	                "mcheques_depositados [s] ccheques_emitidos [s] mcheques_emitidos [s] ccheques_depositados_rechazados [n] mcheques_depositados_rechazados [s] ccheques_emitidos_rechazados [s] mcheques_emitidos_rechazados [s] tcallcenter [n] ccallcenter_transacciones [s] thomebanking [n] chomebanking_transacciones [s] tautoservicio [n] cautoservicio_transacciones [n] tcajas [n] tcajas_consultas [n] tcajas_depositos [n] tcajas_extracciones [n] tcajas_otras [n] ccajeros_propio_transacciones [s] mcajeros_propio [s]",
	                "ccajeros_ajenos_transacciones [s] mcajeros_ajenos [s] tmovimientos_ultimos90dias [n] Master_marca_atraso [n] Master_cuenta_estado [n] Master_mfinanciacion_limite [n] Master_Fvencimiento [n] Master_Finiciomora [n] Master_msaldototal [n] Master_msaldopesos [n] Master_msaldodolares [n] Master_mconsumospesos [n] Master_mconsumosdolares [n] Master_mlimitecompra [n] Master_madelantopesos [n] Master_madelantodolares [n] Master_fultimo_cierre [n] Master_mpagado [n] Master_mpagospesos [n] Master_mpagosdolares [n]",
	                "Master_fechaalta [n] Master_mconsumototal [n] Master_tconsumos [n] Master_tadelantosefectivo [n] Master_mpagominimo [n] Visa_marca_atraso [n] Visa_cuenta_estado [n] Visa_mfinanciacion_limite [n] Visa_Fvencimiento [n] Visa_Finiciomora [n] Visa_msaldototal [n] Visa_msaldopesos [n] Visa_msaldodolares [n] Visa_mconsumospesos [n] Visa_mconsumosdolares [n] Visa_mlimitecompra [n] Visa_madelantopesos [n] Visa_madelantodolares [n] Visa_fultimo_cierre [n] Visa_mpagado [n] Visa_mpagospesos [n] Visa_mpagosdolares [n]",
	                "Visa_fechaalta [n] Visa_mconsumototal [n] Visa_tconsumos [n] Visa_tadelantosefectivo [n] Visa_mpagominimo [n] participa [n]",
	                "  /TREE DISPLAY=TOPDOWN NODES=STATISTICS BRANCHSTATISTICS=YES NODEDEFS=YES SCALE=AUTO",
	                "  /DEPCATEGORIES USEVALUES=[VALID]",
	                "  /PRINT MODELSUMMARY CLASSIFICATION RISK TREETABLE",
	                " /RULES NODES=TERMINAL SYNTAX=SQL TYPE=SCORING OUTFILE='"+outputSQL+"'",
	                "  /SAVE PREDPROB",
	                "  /METHOD TYPE=CHAID",
	                "  /GROWTHLIMIT MAXDEPTH=AUTO MINPARENTSIZE=100 MINCHILDSIZE=50",
	                "  /VALIDATION TYPE=NONE OUTPUT=BOTHSAMPLES",
	                "  /CHAID ALPHASPLIT=0.05 ALPHAMERGE=0.05 SPLITMERGED=NO CHISQUARE=PEARSON CONVERGE=0.001 MAXITERATIONS=100 ADJUST=BONFERRONI INTERVALS=10",
	                "  /COSTS EQUAL",
	                "  /OUTFILE TRAININGMODEL='"+outputXML+"'",
	                "  /MISSING NOMINALMISSING=MISSING.",

					"OMSEND."};
	
			
			return result;
			
		}else{
			throw new RuntimeException("No se pudo crear la carpeta:"+ carpetaOut.getAbsolutePath());
		}
	}

	/**
	 * corrida base + setearle seed + export
	 * @param configArbol
	 * @return
	 */
	@Deprecated
	public static String[] comandoArbolDefault(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
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

	@Deprecated
	public static String[] comandoArbolDefault2(AutomatizarCorridasArbolJulioConfig configArbol) {
		String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
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
 * arbol version 2 es seteando las labels, seteando limites de crecimiento , padres hijos separacion 70/30 y seteando los costes y variable objetivo
 * @param origenDatosSav
 * @param outputFolder
 * @return
 * @throws Exception 
 */
public static String[] comandoArbolVersionParametrizadoArbolNoChaidNoCampos(AutomatizarCorridasArbolJulioConfig configArbol ){

	String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
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

	String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
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
public static String[] comandoArbolVersionParametrizadoArbolNoVarParaPaq1(AutomatizarCorridasArbolJulioConfig configArbol ){

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
public static String[] comandoArbolVersionParametrizadoArbolNoVarParaPaq1Mezclado(AutomatizarCorridasArbolJulioConfig configArbol ){

	String timeStamp = AutomatizarCorridasArbolJulio.getTimeStamp(null,null);
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
}
