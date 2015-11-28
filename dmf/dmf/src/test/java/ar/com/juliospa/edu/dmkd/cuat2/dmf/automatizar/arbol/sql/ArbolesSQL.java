package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.sql;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbol;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioResultado;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.ParseNodosType;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.old.AutomatizarCorridasArbolJulio;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.old.AutomatizarCorridasArbolJulioExplorandoParametrosBase;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.sql.AcumuladorComandosSpss;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.sql.ArbolesSQLEjecutador;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.sql.CamposSQLHelper;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.ArbolFileSources;


/**
 * la idea de este es correr los arboles para la matriz
 * 1: correr todos los arboles para tener el modelo
 * 2: una vez que tengo todos los modelos probar 1 modelo desde spss para ver la sintaxis
 * 3: una vez que tengo la sintaxis, ejecutar la sintaxis de aplicacion de 1 modelo para los diferentes arboles y ver el output a ver si puedo obtener el resultado del tree
 * 4: puedo correrlo sobre todo el dataset no hace falta 70-30
 * @author julio
 *
 */
public class ArbolesSQL {
	@Test
	public void pruebaParseResultadoDefaultConDatasetInicial() {
		
		Long ganancia = 8000L;
		Long costo = 200L;
		double normalizador = 1;
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/sql/default";
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
			
			AutomatizarCorridasArbol.ejecucionArbol(configArbol,AcumuladorComandosSpss.comandoArbolDefaultLevantadoSQL(configArbol,"201504"),ParseNodosType.DEFAULT);
			
			AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.parseResultadoDefault(configArbol);
			System.out.println(result.persistime());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void probandoParametrosDevuelta() {
		
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/sql/historia/";
		try {
			
//			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
//			Integer[] maximaProfundidadList = { 6,7 };
//			Integer[] minParentSizeList = {300, 400, 500 };
//			// cuenta que va a ser el porcentaje variable del current padre
//			Integer[] minChildSizeList = { 65,70,90 };
			
//			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
//			Integer[] maximaProfundidadList = { 6,7 };
//			Integer[] minParentSizeList = {300, 400 };
//			// cuenta que va a ser el porcentaje variable del current padre
//			Integer[] minChildSizeList = { 65,70 };
			
			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
			Integer[] maximaProfundidadList = { 7};
			Integer[] minParentSizeList = {350 };
			// cuenta que va a ser el porcentaje variable del current padre
			Integer[] minChildSizeList = { 70 };
			
			ArbolesSQLEjecutador.ejecucionSpssIterativo(outFolder, maximaProfundidadList, minParentSizeList, minChildSizeList, seed);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void probandoReducto() {
		
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/sql/historia_redux/";
		try {
			
//			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
//			Integer[] maximaProfundidadList = { 6,7 };
//			Integer[] minParentSizeList = {300, 400, 500 };
//			// cuenta que va a ser el porcentaje variable del current padre
//			Integer[] minChildSizeList = { 65,70,90 };
			
//			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
//			Integer[] maximaProfundidadList = { 6,7 };
//			Integer[] minParentSizeList = {300, 400 };
//			// cuenta que va a ser el porcentaje variable del current padre
//			Integer[] minChildSizeList = { 65,70 };
			
			String[] seed = { "101723", "208403", "663552", "826668", "980641" };
			Integer[] maximaProfundidadList = { 7};
			Integer[] minParentSizeList = {350 };
			// cuenta que va a ser el porcentaje variable del current padre
			Integer[] minChildSizeList = { 70 };
			
			ArbolesSQLEjecutador.ejecucionSpssIterativoReducto(outFolder, maximaProfundidadList, minParentSizeList, minChildSizeList, seed);
			AutomatizarCorridasArbolJulioExplorandoParametrosBase.analizarResultadosDeMultiSeed(outFolder);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void analizarNuevosValores() {
		String outFolder = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/sql/historia/";
		AutomatizarCorridasArbolJulioExplorandoParametrosBase.analizarResultadosDeMultiSeed(outFolder);
	} 
	
	@Test
	public void calculaCOmpl() {
		String[] seed = { "101723", "208403", "663552", "826668", "980641" };
		Integer[] maximaProfundidadList = { 6,7 };
		Integer[] minParentSizeList = {350 };
		// cuenta que va a ser el porcentaje variable del current padre
		Integer[] minChildSizeList = { 70 };
		
		String origenDatos1 = ArbolFileSources.userFolder+"/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/TP_DMF/datasts_matriz_histo/"+ArbolFileSources.DM_FINANZAS_201411_TARJETAS_HISTO_AVG;

		String[] files = { origenDatos1};
		int tiempoEstimado = 5;
		ArbolFileSources.complexCalcConTiempo(maximaProfundidadList, minParentSizeList, minChildSizeList, seed,files,tiempoEstimado);
		
	}
	
	@Test
	public void testRandomCols() {
//		Map<String, String> tmp = CamposSQLHelper.getCamposAUsarRandom(50,CamposSQLHelper.getCampos());
//		System.out.println(CamposSQLHelper.transformToSpssSql(tmp.keySet()));
//		System.out.println();
//		System.out.println();
//		System.out.println();
////		System.out.println(Arrays.asList(CamposSQLHelper.transformToSpssTreeFields(tmp.values())));
		int maximaProfundidad = 7;
		int porcMinChildSize = 70;
		int minParentSize = 400;
		int periodoACorrer = 201504;
		
		Integer minChildSize = Math.round(400 * porcMinChildSize / 100);
		String experimentoNro = "expName_pr" + maximaProfundidad + "_par" + minParentSize + "_child" + minChildSize;

		AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
		configArbol.setSeed("12345");
		// configArbol.setOrigenDatosSav(origenDatos);
		configArbol.setTipoArbol("CHAID");
		configArbol.setOutputFolder( experimentoNro + "/");
		configArbol.setMaximaProfundidad(maximaProfundidad);
		configArbol.setMinChildSize(minChildSize);
		configArbol.setMinParentSize(minParentSize);
		configArbol.setCosto(200L);
		configArbol.setGanancia(8000L);
		configArbol.setNormalizador(0.3);
		final String sql = "SELECT numero_de_cliente,foto_mes,marketing_activo_ultimos90dias,cliente_vip,cliente_sucursal,cliente_edad,cliente_antiguedad,mrentabilidad,mrentabilidad_annual,mcomisiones,mactivos_margen,mpasivos_margen,marketing_coss_selling,tpaquete1,tpaquete2,tpaquete3,tpaquete4,tpaquete5,tpaquete6,tpaquete7,tpaquete8,tpaquete9,tcuentas,tcuenta_corriente,mcuenta_corriente_Nopaquete,mcuenta_corriente_Paquete,mcuenta_corriente_dolares,tcaja_ahorro,mcaja_ahorro_Paquete,mcaja_ahorro_Nopaquete,mcaja_ahorro_dolares,mdescubierto_preacordado,mcuentas_saldo,ttarjeta_debito,ctarjeta_debito_transacciones,mautoservicio,ttarjeta_visa,ctarjeta_visa_transacciones,mtarjeta_visa_consumo,ttarjeta_master,ctarjeta_master_transacciones,mtarjeta_master_consumo,cprestamos_personales,mprestamos_personales,cprestamos_prendarios,mprestamos_prendarios,cprestamos_hipotecarios,mprestamos_hipotecarios,tplazo_fijo,mplazo_fijo_dolares,mplazo_fijo_pesos,tfondos_comunes_inversion,mfondos_comunes_inversion_pesos,mfondos_comunes_inversion_dolares,ttitulos,mtitulos,tseguro_vida_mercado_abierto,tseguro_auto,tseguro_vivienda,tseguro_accidentes_personales,tcaja_seguridad,mbonos_gobierno,mmonedas_extranjeras,minversiones_otras,tplan_sueldo,mplan_sueldo,mplan_sueldo_manual,cplan_sueldo_transaccion,tcuenta_debitos_automaticos,mcuenta_debitos_automaticos,ttarjeta_visa_debitos_automaticos,mttarjeta_visa_debitos_automaticos,ttarjeta_master_debitos_automaticos,mttarjeta_master_debitos_automaticos,tpagodeservicios,mpagodeservicios,tpagomiscuentas,mpagomiscuentas,ccajeros_propios_descuentos,mcajeros_propios_descuentos,ctarjeta_visa_descuentos,mtarjeta_visa_descuentos,ctarjeta_master_descuentos,mtarjeta_master_descuentos,ccuenta_descuentos,mcuenta_descuentos,ccomisiones_mantenimiento,mcomisiones_mantenimiento,ccomisiones_otras,mcomisiones_otras,tcambio_monedas,ccambio_monedas_compra,mcambio_monedas_compra,ccambio_monedas_venta,mcambio_monedas_venta,ctransferencias_recibidas,mtransferencias_recibidas,ctransferencias_emitidas,mtransferencias_emitidas,cextraccion_autoservicio,mextraccion_autoservicio,ccheques_depositados,mcheques_depositados,ccheques_emitidos,mcheques_emitidos,ccheques_depositados_rechazados,mcheques_depositados_rechazados,ccheques_emitidos_rechazados,mcheques_emitidos_rechazados,tcallcenter,ccallcenter_transacciones,thomebanking,chomebanking_transacciones,tautoservicio,cautoservicio_transacciones,tcajas,tcajas_consultas,tcajas_depositos,tcajas_extracciones,tcajas_otras,ccajeros_propio_transacciones,mcajeros_propio,ccajeros_ajenos_transacciones,mcajeros_ajenos,tmovimientos_ultimos90dias,Master_marca_atraso,Master_cuenta_estado,Master_mfinanciacion_limite,Master_Fvencimiento,Master_Finiciomora,Master_msaldototal,Master_msaldopesos,Master_msaldodolares,Master_mconsumospesos,Master_mconsumosdolares,Master_mlimitecompra,Master_madelantopesos,Master_madelantodolares,Master_fultimo_cierre,Master_mpagado,Master_mpagospesos,Master_mpagosdolares,Master_fechaalta,Master_mconsumototal,Master_tconsumos,Master_tadelantosefectivo,Master_mpagominimo,Visa_marca_atraso,Visa_cuenta_estado,Visa_mfinanciacion_limite,Visa_Fvencimiento,Visa_Finiciomora,Visa_msaldototal,Visa_msaldopesos,Visa_msaldodolares,Visa_mconsumospesos,Visa_mconsumosdolares,Visa_mlimitecompra,Visa_madelantopesos,Visa_madelantodolares,Visa_fultimo_cierre,Visa_mpagado,Visa_mpagospesos,Visa_mpagosdolares,Visa_fechaalta,Visa_mconsumototal,Visa_tconsumos,Visa_tadelantosefectivo,Visa_mpagominimo,participa,clase,jestado_seguros,jestado_debitos,jestado_contacto,jestado_visa_dias_mora_relativa_periodo,jestado_master_dias_mora_relativa_periodo,jestado_visa_ratio_consumo_limite,jestado_master_ratio_consumo_limite,jestado_visa_ratio_minimo_consumo_limite,jestado_master_ratio_minimo_consumo_limite,tarjeta_marca_atraso,tarjeta_cuenta_estado,tarjeta_mfinanciacion_limite,tarjeta_Fvencimiento,tarjeta_Finiciomora,jestado_tarjeta_dias_mora_relativa_periodo,jestado_tarjeta_ratio_consumo_limite,jestado_tarjeta_ratio_minimo_consumo_limite,tarjeta_msaldototal,tarjeta_msaldopesos,tarjeta_msaldodolares,tarjeta_mconsumospesos,tarjeta_mconsumosdolares,tarjeta_mlimitecompra,tarjeta_madelantopesos,tarjeta_madelantodolares,tarjeta_fultimo_cierre,tarjeta_mpagado,tarjeta_mpagospesos,tarjeta_mpagosdolares,tarjeta_fechaalta,tarjeta_mconsumototal,tarjeta_tconsumos,tarjeta_tadelantosefectivo,tarjeta_mpagominimo,mrentabilidad_AVG,mcomisiones_AVG,mactivos_margen_AVG,mpasivos_margen_AVG,mcuenta_corriente_Nopaquete_AVG,mcuenta_corriente_Paquete_AVG,mcaja_ahorro_Paquete_AVG,mcaja_ahorro_Nopaquete_AVG,mcaja_ahorro_dolares_AVG,mdescubierto_preacordado_AVG,mcuentas_saldo_AVG,mautoservicio_AVG,mtarjeta_visa_consumo_AVG,mtarjeta_master_consumo_AVG,mprestamos_personales_AVG,mprestamos_prendarios_AVG,mprestamos_hipotecarios_AVG,mplazo_fijo_dolares_AVG,mplazo_fijo_pesos_AVG,mfondos_comunes_inversion_pesos_AVG,mfondos_comunes_inversion_dolares_AVG,mtitulos_AVG,mplan_sueldo_AVG,mplan_sueldo_manual_AVG,mcuenta_debitos_automaticos_AVG,mttarjeta_visa_debitos_automaticos_AVG,mttarjeta_master_debitos_automaticos_AVG,mpagodeservicios_AVG,mpagomiscuentas_AVG,mcajeros_propios_descuentos_AVG,mtarjeta_visa_descuentos_AVG,mtarjeta_master_descuentos_AVG,mcomisiones_mantenimiento_AVG,mcomisiones_otras_AVG,mcambio_monedas_compra_AVG,mcambio_monedas_venta_AVG,mtransferencias_recibidas_AVG,mtransferencias_emitidas_AVG,mextraccion_autoservicio_AVG,mcheques_depositados_AVG,mcheques_emitidos_AVG,mcheques_depositados_rechazados_AVG,mcheques_emitidos_rechazados_AVG,mcajeros_propio_AVG,mcajeros_ajenos_AVG,Master_mfinanciacion_limite_AVG,Master_msaldototal_AVG,Master_msaldopesos_AVG,Master_msaldodolares_AVG,Master_mconsumospesos_AVG,Master_mconsumosdolares_AVG,Master_mlimitecompra_AVG,Master_madelantopesos_AVG,Master_madelantodolares_AVG,Master_mpagado_AVG,Master_mpagospesos_AVG,Master_mpagosdolares_AVG,Master_mconsumototal_AVG,Master_mpagominimo_AVG,Visa_mfinanciacion_limite_AVG,Visa_msaldototal_AVG,Visa_msaldopesos_AVG,Visa_msaldodolares_AVG,Visa_mconsumospesos_AVG,Visa_mconsumosdolares_AVG,Visa_mlimitecompra_AVG,Visa_madelantopesos_AVG,Visa_madelantodolares_AVG,Visa_mpagado_AVG,Visa_mpagospesos_AVG,Visa_mpagosdolares_AVG,Visa_mconsumototal_AVG,Visa_mpagominimo_AVG,jestado_visa_ratio_consumo_limite_AVG,jestado_master_ratio_consumo_limite_AVG,jestado_visa_ratio_minimo_consumo_limite_AVG,jestado_master_ratio_minimo_consumo_limite_AVG,tarjeta_mfinanciacion_limite_AVG,jestado_tarjeta_ratio_consumo_limite_AVG,jestado_tarjeta_ratio_minimo_consumo_limite_AVG,tarjeta_msaldototal_AVG,tarjeta_msaldopesos_AVG,tarjeta_msaldodolares_AVG,tarjeta_mconsumospesos_AVG,tarjeta_mconsumosdolares_AVG,tarjeta_mlimitecompra_AVG,tarjeta_madelantopesos_AVG,tarjeta_madelantodolares_AVG,tarjeta_mpagado_AVG,tarjeta_mpagospesos_AVG,tarjeta_mpagosdolares_AVG,tarjeta_mconsumototal_AVG,tarjeta_mpagominimo_AVG,marketing_activo_ultimos90dias_AVG,cliente_vip_AVG,cliente_sucursal_AVG,cliente_edad_AVG,cliente_antiguedad_AVG,mrentabilidad_annual_AVG,marketing_coss_selling_AVG,mcuenta_corriente_dolares_AVG,ctarjeta_debito_transacciones_AVG,ctarjeta_visa_transacciones_AVG,ctarjeta_master_transacciones_AVG,cprestamos_personales_AVG,cprestamos_prendarios_AVG,cprestamos_hipotecarios_AVG,mbonos_gobierno_AVG,mmonedas_extranjeras_AVG,minversiones_otras_AVG,cplan_sueldo_transaccion_AVG,ccajeros_propios_descuentos_AVG,ctarjeta_visa_descuentos_AVG,ctarjeta_master_descuentos_AVG,ccuenta_descuentos_AVG,mcuenta_descuentos_AVG,ccomisiones_mantenimiento_AVG,ccomisiones_otras_AVG,ccambio_monedas_compra_AVG,ccambio_monedas_venta_AVG,ctransferencias_recibidas_AVG,ctransferencias_emitidas_AVG,cextraccion_autoservicio_AVG,ccheques_depositados_AVG,ccheques_emitidos_AVG,ccheques_depositados_rechazados_AVG,ccheques_emitidos_rechazados_AVG,ccallcenter_transacciones_AVG,chomebanking_transacciones_AVG,cautoservicio_transacciones_AVG,ccajeros_propio_transacciones_AVG,ccajeros_ajenos_transacciones_AVG,Master_marca_atraso_AVG,Master_cuenta_estado_AVG,Master_Fvencimiento_AVG,Master_Finiciomora_AVG,Master_fultimo_cierre_AVG,Master_fechaalta_AVG,Visa_marca_atraso_AVG,Visa_cuenta_estado_AVG,Visa_Fvencimiento_AVG,Visa_Finiciomora_AVG,Visa_fultimo_cierre_AVG,Visa_fechaalta_AVG,jestado_seguros_AVG,jestado_debitos_AVG,jestado_contacto_AVG,jestado_visa_dias_mora_relativa_periodo_AVG,jestado_master_dias_mora_relativa_periodo_AVG,tarjeta_marca_atraso_AVG,tarjeta_cuenta_estado_AVG,jestado_tarjeta_dias_mora_relativa_periodo_AVG,tarjeta_tconsumos_AVG,tarjeta_tadelantosefectivo_AVG,mrentabilidad_MAX,mcomisiones_MAX,mactivos_margen_MAX,mpasivos_margen_MAX,mcuenta_corriente_Nopaquete_MAX,mcuenta_corriente_Paquete_MAX,mcaja_ahorro_Paquete_MAX,mcaja_ahorro_Nopaquete_MAX,mcaja_ahorro_dolares_MAX,mdescubierto_preacordado_MAX,mcuentas_saldo_MAX,mautoservicio_MAX,mtarjeta_visa_consumo_MAX,mtarjeta_master_consumo_MAX,mprestamos_personales_MAX,mprestamos_prendarios_MAX,mprestamos_hipotecarios_MAX,mplazo_fijo_dolares_MAX,mplazo_fijo_pesos_MAX,mfondos_comunes_inversion_pesos_MAX,mfondos_comunes_inversion_dolares_MAX,mtitulos_MAX,mplan_sueldo_MAX,mplan_sueldo_manual_MAX,mcuenta_debitos_automaticos_MAX,mttarjeta_visa_debitos_automaticos_MAX,mttarjeta_master_debitos_automaticos_MAX,mpagodeservicios_MAX,mpagomiscuentas_MAX,mcajeros_propios_descuentos_MAX,mtarjeta_visa_descuentos_MAX,mtarjeta_master_descuentos_MAX,mcomisiones_mantenimiento_MAX,mcomisiones_otras_MAX,mcambio_monedas_compra_MAX,mcambio_monedas_venta_MAX,mtransferencias_recibidas_MAX,mtransferencias_emitidas_MAX,mextraccion_autoservicio_MAX,mcheques_depositados_MAX,mcheques_emitidos_MAX,mcheques_depositados_rechazados_MAX,mcheques_emitidos_rechazados_MAX,mcajeros_propio_MAX,mcajeros_ajenos_MAX,Master_mfinanciacion_limite_MAX,Master_msaldototal_MAX,Master_msaldopesos_MAX,Master_msaldodolares_MAX,Master_mconsumospesos_MAX,Master_mconsumosdolares_MAX,Master_mlimitecompra_MAX,Master_madelantopesos_MAX,Master_madelantodolares_MAX,Master_mpagado_MAX,Master_mpagospesos_MAX,Master_mpagosdolares_MAX,Master_mconsumototal_MAX,Master_mpagominimo_MAX,Visa_mfinanciacion_limite_MAX,Visa_msaldototal_MAX,Visa_msaldopesos_MAX,Visa_msaldodolares_MAX,Visa_mconsumospesos_MAX,Visa_mconsumosdolares_MAX,Visa_mlimitecompra_MAX,Visa_madelantopesos_MAX,Visa_madelantodolares_MAX,Visa_mpagado_MAX,Visa_mpagospesos_MAX,Visa_mpagosdolares_MAX,Visa_mconsumototal_MAX,Visa_mpagominimo_MAX,jestado_visa_ratio_consumo_limite_MAX,jestado_master_ratio_consumo_limite_MAX,jestado_visa_ratio_minimo_consumo_limite_MAX,jestado_master_ratio_minimo_consumo_limite_MAX,tarjeta_mfinanciacion_limite_MAX,jestado_tarjeta_ratio_consumo_limite_MAX,jestado_tarjeta_ratio_minimo_consumo_limite_MAX,tarjeta_msaldototal_MAX,tarjeta_msaldopesos_MAX,tarjeta_msaldodolares_MAX,tarjeta_mconsumospesos_MAX,tarjeta_mconsumosdolares_MAX,tarjeta_mlimitecompra_MAX,tarjeta_madelantopesos_MAX,tarjeta_madelantodolares_MAX,tarjeta_mpagado_MAX,tarjeta_mpagospesos_MAX,tarjeta_mpagosdolares_MAX,tarjeta_mconsumototal_MAX,tarjeta_mpagominimo_MAX,marketing_activo_ultimos90dias_MAX,cliente_vip_MAX,cliente_sucursal_MAX,cliente_edad_MAX,cliente_antiguedad_MAX,mrentabilidad_annual_MAX,marketing_coss_selling_MAX,mcuenta_corriente_dolares_MAX,ctarjeta_debito_transacciones_MAX,ctarjeta_visa_transacciones_MAX,ctarjeta_master_transacciones_MAX,cprestamos_personales_MAX,cprestamos_prendarios_MAX,cprestamos_hipotecarios_MAX,mbonos_gobierno_MAX,mmonedas_extranjeras_MAX,minversiones_otras_MAX,cplan_sueldo_transaccion_MAX,ccajeros_propios_descuentos_MAX,ctarjeta_visa_descuentos_MAX,ctarjeta_master_descuentos_MAX,ccuenta_descuentos_MAX,mcuenta_descuentos_MAX,ccomisiones_mantenimiento_MAX,ccomisiones_otras_MAX,ccambio_monedas_compra_MAX,ccambio_monedas_venta_MAX,ctransferencias_recibidas_MAX,ctransferencias_emitidas_MAX,cextraccion_autoservicio_MAX,ccheques_depositados_MAX,ccheques_emitidos_MAX,ccheques_depositados_rechazados_MAX,ccheques_emitidos_rechazados_MAX,ccallcenter_transacciones_MAX,chomebanking_transacciones_MAX,cautoservicio_transacciones_MAX,ccajeros_propio_transacciones_MAX,ccajeros_ajenos_transacciones_MAX,Master_marca_atraso_MAX,Master_cuenta_estado_MAX,Master_Fvencimiento_MAX,Master_Finiciomora_MAX,Master_fultimo_cierre_MAX,Master_fechaalta_MAX,Visa_marca_atraso_MAX,Visa_cuenta_estado_MAX,Visa_Fvencimiento_MAX,Visa_Finiciomora_MAX,Visa_fultimo_cierre_MAX,Visa_fechaalta_MAX,jestado_seguros_MAX,jestado_debitos_MAX,jestado_contacto_MAX,jestado_visa_dias_mora_relativa_periodo_MAX,jestado_master_dias_mora_relativa_periodo_MAX,tarjeta_marca_atraso_MAX,tarjeta_cuenta_estado_MAX,jestado_tarjeta_dias_mora_relativa_periodo_MAX,tarjeta_tconsumos_MAX,tarjeta_tadelantosefectivo_MAX,mrentabilidad_MIN,mcomisiones_MIN,mactivos_margen_MIN,mpasivos_margen_MIN,mcuenta_corriente_Nopaquete_MIN,mcuenta_corriente_Paquete_MIN,mcaja_ahorro_Paquete_MIN,mcaja_ahorro_Nopaquete_MIN,mcaja_ahorro_dolares_MIN,mdescubierto_preacordado_MIN,mcuentas_saldo_MIN,mautoservicio_MIN,mtarjeta_visa_consumo_MIN,mtarjeta_master_consumo_MIN,mprestamos_personales_MIN,mprestamos_prendarios_MIN,mprestamos_hipotecarios_MIN,mplazo_fijo_dolares_MIN,mplazo_fijo_pesos_MIN,mfondos_comunes_inversion_pesos_MIN,mfondos_comunes_inversion_dolares_MIN,mtitulos_MIN,mplan_sueldo_MIN,mplan_sueldo_manual_MIN,mcuenta_debitos_automaticos_MIN,mttarjeta_visa_debitos_automaticos_MIN,mttarjeta_master_debitos_automaticos_MIN,mpagodeservicios_MIN,mpagomiscuentas_MIN,mcajeros_propios_descuentos_MIN,mtarjeta_visa_descuentos_MIN,mtarjeta_master_descuentos_MIN,mcomisiones_mantenimiento_MIN,mcomisiones_otras_MIN,mcambio_monedas_compra_MIN,mcambio_monedas_venta_MIN,mtransferencias_recibidas_MIN,mtransferencias_emitidas_MIN,mextraccion_autoservicio_MIN,mcheques_depositados_MIN,mcheques_emitidos_MIN,mcheques_depositados_rechazados_MIN,mcheques_emitidos_rechazados_MIN,mcajeros_propio_MIN,mcajeros_ajenos_MIN,Master_mfinanciacion_limite_MIN,Master_msaldototal_MIN,Master_msaldopesos_MIN,Master_msaldodolares_MIN,Master_mconsumospesos_MIN,Master_mconsumosdolares_MIN,Master_mlimitecompra_MIN,Master_madelantopesos_MIN,Master_madelantodolares_MIN,Master_mpagado_MIN,Master_mpagospesos_MIN,Master_mpagosdolares_MIN,Master_mconsumototal_MIN,Master_mpagominimo_MIN,Visa_mfinanciacion_limite_MIN,Visa_msaldototal_MIN,Visa_msaldopesos_MIN,Visa_msaldodolares_MIN,Visa_mconsumospesos_MIN,Visa_mconsumosdolares_MIN,Visa_mlimitecompra_MIN,Visa_madelantopesos_MIN,Visa_madelantodolares_MIN,Visa_mpagado_MIN,Visa_mpagospesos_MIN,Visa_mpagosdolares_MIN,Visa_mconsumototal_MIN,Visa_mpagominimo_MIN,jestado_visa_ratio_consumo_limite_MIN,jestado_master_ratio_consumo_limite_MIN,jestado_visa_ratio_minimo_consumo_limite_MIN,jestado_master_ratio_minimo_consumo_limite_MIN,tarjeta_mfinanciacion_limite_MIN,jestado_tarjeta_ratio_consumo_limite_MIN,jestado_tarjeta_ratio_minimo_consumo_limite_MIN,tarjeta_msaldototal_MIN,tarjeta_msaldopesos_MIN,tarjeta_msaldodolares_MIN,tarjeta_mconsumospesos_MIN,tarjeta_mconsumosdolares_MIN,tarjeta_mlimitecompra_MIN,tarjeta_madelantopesos_MIN,tarjeta_madelantodolares_MIN,tarjeta_mpagado_MIN,tarjeta_mpagospesos_MIN,tarjeta_mpagosdolares_MIN,tarjeta_mconsumototal_MIN,tarjeta_mpagominimo_MIN,marketing_activo_ultimos90dias_MIN,cliente_vip_MIN,cliente_sucursal_MIN,cliente_edad_MIN,cliente_antiguedad_MIN,mrentabilidad_annual_MIN,marketing_coss_selling_MIN,mcuenta_corriente_dolares_MIN,ctarjeta_debito_transacciones_MIN,ctarjeta_visa_transacciones_MIN,ctarjeta_master_transacciones_MIN,cprestamos_personales_MIN,cprestamos_prendarios_MIN,cprestamos_hipotecarios_MIN,mbonos_gobierno_MIN,mmonedas_extranjeras_MIN,minversiones_otras_MIN,cplan_sueldo_transaccion_MIN,ccajeros_propios_descuentos_MIN,ctarjeta_visa_descuentos_MIN,ctarjeta_master_descuentos_MIN,ccuenta_descuentos_MIN,mcuenta_descuentos_MIN,ccomisiones_mantenimiento_MIN,ccomisiones_otras_MIN,ccambio_monedas_compra_MIN,ccambio_monedas_venta_MIN,ctransferencias_recibidas_MIN,ctransferencias_emitidas_MIN,cextraccion_autoservicio_MIN,ccheques_depositados_MIN,ccheques_emitidos_MIN,ccheques_depositados_rechazados_MIN,ccheques_emitidos_rechazados_MIN,ccallcenter_transacciones_MIN,chomebanking_transacciones_MIN,cautoservicio_transacciones_MIN,ccajeros_propio_transacciones_MIN,ccajeros_ajenos_transacciones_MIN,Master_marca_atraso_MIN,Master_cuenta_estado_MIN,Master_Fvencimiento_MIN,Master_Finiciomora_MIN,Master_fultimo_cierre_MIN,Master_fechaalta_MIN,Visa_marca_atraso_MIN,Visa_cuenta_estado_MIN,Visa_Fvencimiento_MIN,Visa_Finiciomora_MIN,Visa_fultimo_cierre_MIN,Visa_fechaalta_MIN,jestado_seguros_MIN,jestado_debitos_MIN,jestado_contacto_MIN,jestado_visa_dias_mora_relativa_periodo_MIN,jestado_master_dias_mora_relativa_periodo_MIN,tarjeta_marca_atraso_MIN,tarjeta_cuenta_estado_MIN,jestado_tarjeta_dias_mora_relativa_periodo_MIN,tarjeta_tconsumos_MIN,tarjeta_tadelantosefectivo_MIN FROM historia_rework_with_cols_histo_4_may_jun WHERE foto_mes="+periodoACorrer;
		String comandoSQLSPSS= CamposSQLHelper.transformSqltoSpss(sql);
		String columnasArbol = CamposSQLHelper.transformToSpssTreeFields(sql);
		
		String[] tmp = AcumuladorComandosSpss.comandoArbolSQL7030FromDB(configArbol, comandoSQLSPSS, columnasArbol);
		for (String tm : tmp) {
			System.out.println(tm);
		}
		
	}
	
}
