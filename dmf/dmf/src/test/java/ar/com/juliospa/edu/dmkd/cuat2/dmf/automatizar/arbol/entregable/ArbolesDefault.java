package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.entregable;

import org.junit.Assert;
import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.ArbolFileSources;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.AutomatizarCorridasArbol;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioResultado;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.ParseNodosType;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.UtilidadesGenerales;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.old.AutomatizarCorridasArbolJulio;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.sql.AcumuladorComandosSpss;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.sql.AutomatizarCorridasModeloJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.sql.CamposSQLHelper;

/**
 * esta clase es solo para correr lso arboles default y guardar dichos valores.
 * @author julio
 *
 */
public class ArbolesDefault {

	/**
	 * correr los arboles default basicos
	 */
	@Test
	public void pruebaParseResultadoDefaultConDatasetInicial() {
//		voy a correr con estos periodos porque son los que la variabilidad se parece con el de junio que es para donde tenemos que clasificar
		// el 201506 no cuenta porque no tengo las clases
		final String[] periodos = {"201504","201503","201501"};
		for (String periodo : periodos) {
			arbolDefaultPeriodo(periodo);	
		}
	}

	/** correr arbol default en periodo
	 * @param periodo
	 */
	private void arbolDefaultPeriodo(final String periodo) {
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/defaults/"+periodo+"/";
		Long ganancia = 8000L;
		Long costo = 200L;
		double normalizador = 1;
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
			
			
			AutomatizarCorridasArbol.ejecucionArbol(configArbol,AcumuladorComandosSpss.comandoArbolDefaultLevantadoSQL(configArbol,periodo),ParseNodosType.DEFAULT);
			
			AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.parseResultadoDefault(configArbol);
			System.out.println(result.persistime());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void cruzarDefaultConOtrosMeses() {
		String srcFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/defaults/";
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/defaults/cruce/";
		
//		corro los modelos
		final String[] periodos = {"201504","201503","201501"};
//		modelos correspondientes
		final String[] modelos = {srcFolder+"201504/20151127_021601/model.xml",srcFolder+"201503/20151127_022037/model.xml",srcFolder+"201501/20151127_022446/model.xml"};
		
	}
	
	@Test
	public void cruzarDefaultConOtrosMeses201504() {
		String srcFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/defaults/";
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/defaults/cruce/";
//		corro los modelos
		String periodo = "201504";
//		modelos correspondientes, todos menos el 201504
		final String[] modelos = {srcFolder+"201503/20151127_022037/model.xml",srcFolder+"201501/20151127_022446/model.xml"};
		correrModelosEnPeriodo(periodo,modelos,outFolder+periodo+"/");
	}
	
	@Test
	public void cruzarDefaultConOtrosMeses201503() {
		String srcFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/defaults/";
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/defaults/cruce/";
//		corro los modelos
		String periodo = "201503";
//		modelos correspondientes, todos menos el 201503
		final String[] modelos = {srcFolder+"201504/20151127_021601/model.xml",srcFolder+"201501/20151127_022446/model.xml"};
		correrModelosEnPeriodo(periodo,modelos,outFolder+periodo+"/");
	}
	
	@Test
	public void cruzarDefaultConOtrosMeses201501() {
		String srcFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/defaults/";
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/defaults/cruce/";
//		corro los modelos
		String periodo = "201501";
//		modelos correspondientes, todos menos el 201501
		final String[] modelos = {srcFolder+"201504/20151127_021601/model.xml",srcFolder+"201503/20151127_022037/model.xml"};
		correrModelosEnPeriodo(periodo,modelos,outFolder+periodo+"/");
	}
	
	private void correrModelosEnPeriodo(String periodo,String[] modelFileXml,String outputFolder ) {
		
//		parametros_baseexpName_pr7_par300_child195	20151115_145540

		
		// TODO: editar modelos

		String modelNodeIdVar = "A_node_id";
		String modelProbBaja2Var="A_prob_b2";
		String modelGananciaVar="A_ganancia";
		String odbcName="dmkd-dmf";
		final String ensembleProbs = "avg_probs";
		final String ensembleGanancia = "avg_ganancia";
		
		String tableInsertResultado="cruceDefault"+periodo;
		String probabilidadFiltro="0.025";
		int calculoGananciaGanancia = 8000;
		int calculoGananciaCosto=200;
		String timeStampFolder = UtilidadesGenerales.getTimeStamp(null, null);

//		aca usar el generador de spss code a partir de la query
		final String sql = "SELECT numero_de_cliente,foto_mes,marketing_activo_ultimos90dias,cliente_vip,cliente_sucursal,cliente_edad,cliente_antiguedad,mrentabilidad,mrentabilidad_annual,mcomisiones,mactivos_margen,mpasivos_margen,marketing_coss_selling,tpaquete1,tpaquete2,tpaquete3,tpaquete4,tpaquete5,tpaquete6,tpaquete7,tpaquete8,tpaquete9,tcuentas,tcuenta_corriente,mcuenta_corriente_Nopaquete,mcuenta_corriente_Paquete,mcuenta_corriente_dolares,tcaja_ahorro,mcaja_ahorro_Paquete,mcaja_ahorro_Nopaquete,mcaja_ahorro_dolares,mdescubierto_preacordado,mcuentas_saldo,ttarjeta_debito,ctarjeta_debito_transacciones,mautoservicio,ttarjeta_visa,ctarjeta_visa_transacciones,mtarjeta_visa_consumo,ttarjeta_master,ctarjeta_master_transacciones,mtarjeta_master_consumo,cprestamos_personales,mprestamos_personales,cprestamos_prendarios,mprestamos_prendarios,cprestamos_hipotecarios,mprestamos_hipotecarios,tplazo_fijo,mplazo_fijo_dolares,mplazo_fijo_pesos,tfondos_comunes_inversion,mfondos_comunes_inversion_pesos,mfondos_comunes_inversion_dolares,ttitulos,mtitulos,tseguro_vida_mercado_abierto,tseguro_auto,tseguro_vivienda,tseguro_accidentes_personales,tcaja_seguridad,mbonos_gobierno,mmonedas_extranjeras,minversiones_otras,tplan_sueldo,mplan_sueldo,mplan_sueldo_manual,cplan_sueldo_transaccion,tcuenta_debitos_automaticos,mcuenta_debitos_automaticos,ttarjeta_visa_debitos_automaticos,mttarjeta_visa_debitos_automaticos,ttarjeta_master_debitos_automaticos,mttarjeta_master_debitos_automaticos,tpagodeservicios,mpagodeservicios,tpagomiscuentas,mpagomiscuentas,ccajeros_propios_descuentos,mcajeros_propios_descuentos,ctarjeta_visa_descuentos,mtarjeta_visa_descuentos,ctarjeta_master_descuentos,mtarjeta_master_descuentos,ccuenta_descuentos,mcuenta_descuentos,ccomisiones_mantenimiento,mcomisiones_mantenimiento,ccomisiones_otras,mcomisiones_otras,tcambio_monedas,ccambio_monedas_compra,mcambio_monedas_compra,ccambio_monedas_venta,mcambio_monedas_venta,ctransferencias_recibidas,mtransferencias_recibidas,ctransferencias_emitidas,mtransferencias_emitidas,cextraccion_autoservicio,mextraccion_autoservicio,ccheques_depositados,mcheques_depositados,ccheques_emitidos,mcheques_emitidos,ccheques_depositados_rechazados,mcheques_depositados_rechazados,ccheques_emitidos_rechazados,mcheques_emitidos_rechazados,tcallcenter,ccallcenter_transacciones,thomebanking,chomebanking_transacciones,tautoservicio,cautoservicio_transacciones,tcajas,tcajas_consultas,tcajas_depositos,tcajas_extracciones,tcajas_otras,ccajeros_propio_transacciones,mcajeros_propio,ccajeros_ajenos_transacciones,mcajeros_ajenos,tmovimientos_ultimos90dias,Master_marca_atraso,Master_cuenta_estado,Master_mfinanciacion_limite,Master_Fvencimiento,Master_Finiciomora,Master_msaldototal,Master_msaldopesos,Master_msaldodolares,Master_mconsumospesos,Master_mconsumosdolares,Master_mlimitecompra,Master_madelantopesos,Master_madelantodolares,Master_fultimo_cierre,Master_mpagado,Master_mpagospesos,Master_mpagosdolares,Master_fechaalta,Master_mconsumototal,Master_tconsumos,Master_tadelantosefectivo,Master_mpagominimo,Visa_marca_atraso,Visa_cuenta_estado,Visa_mfinanciacion_limite,Visa_Fvencimiento,Visa_Finiciomora,Visa_msaldototal,Visa_msaldopesos,Visa_msaldodolares,Visa_mconsumospesos,Visa_mconsumosdolares,Visa_mlimitecompra,Visa_madelantopesos,Visa_madelantodolares,Visa_fultimo_cierre,Visa_mpagado,Visa_mpagospesos,Visa_mpagosdolares,Visa_fechaalta,Visa_mconsumototal,Visa_tconsumos,Visa_tadelantosefectivo,Visa_mpagominimo,participa,clase FROM dmf.historia_rework WHERE foto_mes="+periodo;
		
//		System.out.println(sql.length() + " | " +sql2.length());
		
		String comandoSQLSPSS= CamposSQLHelper.transformSqltoSpss(sql);
		
		AutomatizarCorridasModeloJulioConfig config = new AutomatizarCorridasModeloJulioConfig(comandoSQLSPSS, modelFileXml, modelNodeIdVar, modelProbBaja2Var, modelGananciaVar, odbcName, tableInsertResultado, probabilidadFiltro, calculoGananciaGanancia, calculoGananciaCosto, timeStampFolder, outputFolder,ensembleProbs,ensembleGanancia);
		
		String[] tmp = config.getComandoSPSS();
		
		for (String comando: tmp) {
			System.out.println(comando);
		}
	}
	
}
