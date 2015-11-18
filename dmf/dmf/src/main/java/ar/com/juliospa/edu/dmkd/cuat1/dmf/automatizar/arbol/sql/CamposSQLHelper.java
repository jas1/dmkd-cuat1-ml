package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

public abstract class CamposSQLHelper {

	
	public static List<String> getCampos() {
		List<String> campos = new ArrayList<String>();
		campos.add("clase [n] ");
		campos.add("numero_de_cliente [s] ");
		campos.add("foto_mes [s] ");
		campos.add("marketing_activo_ultimos90dias [n] ");
		campos.add("cliente_vip [n] ");
		campos.add("cliente_sucursal [s] ");
		campos.add("cliente_edad [s] ");
		campos.add("cliente_antiguedad [s] ");
		campos.add("mrentabilidad [s] ");
		campos.add("mrentabilidad_annual [n] ");
		campos.add("mcomisiones [s] ");
		campos.add("mactivos_margen [s] ");
		campos.add("mpasivos_margen [s] ");
		campos.add("marketing_coss_selling [n] ");
		campos.add("tpaquete1 [n] ");
		campos.add("tpaquete2 [n] ");
		campos.add("tpaquete3 [n] ");
		campos.add("tpaquete4 [n] ");
		campos.add("tpaquete5 [n] ");
		campos.add("tpaquete6 [n] ");
		campos.add("tpaquete7 [n] ");
		campos.add("tpaquete8 [n] ");
		campos.add("tpaquete9 [n] ");
		campos.add("tcuentas [n] ");
		campos.add("tcuenta_corriente [n] ");
		campos.add("mcuenta_corriente_Nopaquete [s] ");
		campos.add("mcuenta_corriente_Paquete [s] ");
		campos.add("mcuenta_corriente_dolares [n] ");
		campos.add("tcaja_ahorro [n] ");
		campos.add("mcaja_ahorro_Paquete [s] ");
		campos.add("mcaja_ahorro_Nopaquete [s] ");
		campos.add("mcaja_ahorro_dolares [s] ");
		campos.add("mdescubierto_preacordado [s] ");
		campos.add("mcuentas_saldo [s] ");
		campos.add("ttarjeta_debito [n] ");
		campos.add("ctarjeta_debito_transacciones [s] ");
		campos.add("mautoservicio [s] ");
		campos.add("ttarjeta_visa [n] ");
		campos.add("ctarjeta_visa_transacciones [s] ");
		campos.add("mtarjeta_visa_consumo [s] ");
		campos.add("ttarjeta_master [n] ");
		campos.add("ctarjeta_master_transacciones [s] ");
		campos.add("mtarjeta_master_consumo [s] ");
		campos.add("cprestamos_personales [s] ");
		campos.add("mprestamos_personales [s] ");
		campos.add("cprestamos_prendarios [n] ");
		campos.add("mprestamos_prendarios [s] ");
		campos.add("cprestamos_hipotecarios [n] ");
		campos.add("mprestamos_hipotecarios [s] ");
		campos.add("tplazo_fijo [n] ");
		campos.add("mplazo_fijo_dolares [s] ");
		campos.add("mplazo_fijo_pesos [s] ");
		campos.add("tfondos_comunes_inversion [n] ");
		campos.add("mfondos_comunes_inversion_pesos [s] ");
		campos.add("mfondos_comunes_inversion_dolares [s] ");
		campos.add("ttitulos [n] ");
		campos.add("mtitulos [s] ");
		campos.add("tseguro_vida_mercado_abierto [n] ");
		campos.add("tseguro_auto [n] ");
		campos.add("tseguro_vivienda [n] ");
		campos.add("tseguro_accidentes_personales [n] ");
		campos.add("tcaja_seguridad [n] ");
		campos.add("mbonos_gobierno [n] ");
		campos.add("mmonedas_extranjeras [n] ");
		campos.add("minversiones_otras [n] ");
		campos.add("tplan_sueldo [n] ");
		campos.add("mplan_sueldo [s] ");
		campos.add("mplan_sueldo_manual [s] ");
		campos.add("cplan_sueldo_transaccion [n] ");
		campos.add("tcuenta_debitos_automaticos [n] ");
		campos.add("mcuenta_debitos_automaticos [s] ");
		campos.add("ttarjeta_visa_debitos_automaticos [n] ");
		campos.add("mttarjeta_visa_debitos_automaticos [s] ");
		campos.add("ttarjeta_master_debitos_automaticos [n] ");
		campos.add("mttarjeta_master_debitos_automaticos [s] ");
		campos.add("tpagodeservicios [n] ");
		campos.add("mpagodeservicios [s] ");
		campos.add("tpagomiscuentas [n] ");
		campos.add("mpagomiscuentas [s] ");
		campos.add("ccajeros_propios_descuentos [n] ");
		campos.add("mcajeros_propios_descuentos [s] ");
		campos.add("ctarjeta_visa_descuentos [s] ");
		campos.add("mtarjeta_visa_descuentos [s] ");
		campos.add("ctarjeta_master_descuentos [s] ");
		campos.add("mtarjeta_master_descuentos [s] ");
		campos.add("ccuenta_descuentos [n] ");
		campos.add("mcuenta_descuentos [n] ");
		campos.add("ccomisiones_mantenimiento [s] ");
		campos.add("mcomisiones_mantenimiento [s] ");
		campos.add("ccomisiones_otras [s] ");
		campos.add("mcomisiones_otras [s] ");
		campos.add("tcambio_monedas [n] ");
		campos.add("ccambio_monedas_compra [n] ");
		campos.add("mcambio_monedas_compra [s] ");
		campos.add("ccambio_monedas_venta [n] ");
		campos.add("mcambio_monedas_venta [s] ");
		campos.add("ctransferencias_recibidas [n] ");
		campos.add("mtransferencias_recibidas [s] ");
		campos.add("ctransferencias_emitidas [s] ");
		campos.add("mtransferencias_emitidas [s] ");
		campos.add("cextraccion_autoservicio [s] ");
		campos.add("mextraccion_autoservicio [s] ");
		campos.add("ccheques_depositados [s] ");
		campos.add("mcheques_depositados [s] ");
		campos.add("ccheques_emitidos [s] ");
		campos.add("mcheques_emitidos [s] ");
		campos.add("ccheques_depositados_rechazados [n] ");
		campos.add("mcheques_depositados_rechazados [s] ");
		campos.add("ccheques_emitidos_rechazados [s] ");
		campos.add("mcheques_emitidos_rechazados [s] ");
		campos.add("tcallcenter [n] ");
		campos.add("ccallcenter_transacciones [s] ");
		campos.add("thomebanking [n] ");
		campos.add("chomebanking_transacciones [s] ");
		campos.add("tautoservicio [n] ");
		campos.add("cautoservicio_transacciones [n] ");
		campos.add("tcajas [n] ");
		campos.add("tcajas_consultas [n] ");
		campos.add("tcajas_depositos [n] ");
		campos.add("tcajas_extracciones [n] ");
		campos.add("tcajas_otras [n] ");
		campos.add("ccajeros_propio_transacciones [s] ");
		campos.add("mcajeros_propio [s] ");
		campos.add("ccajeros_ajenos_transacciones [s] ");
		campos.add("mcajeros_ajenos [s] ");
		campos.add("tmovimientos_ultimos90dias [n] ");
		campos.add("Master_marca_atraso [n] ");
		campos.add("Master_cuenta_estado [s] ");
		campos.add("Master_mfinanciacion_limite [s] ");
		campos.add("Master_Fvencimiento [s] ");
		campos.add("Master_Finiciomora [s] ");
		campos.add("Master_msaldototal [s] ");
		campos.add("Master_msaldopesos [s] ");
		campos.add("Master_msaldodolares [s] ");
		campos.add("Master_mconsumospesos [s] ");
		campos.add("Master_mconsumosdolares [s] ");
		campos.add("Master_mlimitecompra [s] ");
		campos.add("Master_madelantopesos [s] ");
		campos.add("Master_madelantodolares [s] ");
		campos.add("Master_fultimo_cierre [s] ");
		campos.add("Master_mpagado [s] ");
		campos.add("Master_mpagospesos [s] ");
		campos.add("Master_mpagosdolares [s] ");
		campos.add("Master_fechaalta [s] ");
		campos.add("Master_mconsumototal [s] ");
		campos.add("Master_tconsumos [n] ");
		campos.add("Master_tadelantosefectivo [n] ");
		campos.add("Master_mpagominimo [s] ");
		campos.add("Visa_marca_atraso [n] ");
		campos.add("Visa_cuenta_estado [s] ");
		campos.add("Visa_mfinanciacion_limite [s] ");
		campos.add("Visa_Fvencimiento [s] ");
		campos.add("Visa_Finiciomora [s] ");
		campos.add("Visa_msaldototal [s] ");
		campos.add("Visa_msaldopesos [s] ");
		campos.add("Visa_msaldodolares [s] ");
		campos.add("Visa_mconsumospesos [s] ");
		campos.add("Visa_mconsumosdolares [s] ");
		campos.add("Visa_mlimitecompra [s] ");
		campos.add("Visa_madelantopesos [s] ");
		campos.add("Visa_madelantodolares [s] ");
		campos.add("Visa_fultimo_cierre [s] ");
		campos.add("Visa_mpagado [s] ");
		campos.add("Visa_mpagospesos [s] ");
		campos.add("Visa_mpagosdolares [s] ");
		campos.add("Visa_fechaalta [s] ");
		campos.add("Visa_mconsumototal [s] ");
		campos.add("Visa_tconsumos [n] ");
		campos.add("Visa_tadelantosefectivo [n] ");
		campos.add("Visa_mpagominimo [s] ");
		campos.add("participa [n] ");
		campos.add("jestado_seguros [n] ");
		campos.add("jestado_debitos [n] ");
		campos.add("jestado_contacto [n] ");
		campos.add("jestado_visa_dias_mora_relativa_periodo [s] ");
		campos.add("jestado_master_dias_mora_relativa_periodo [s] ");
		campos.add("jestado_visa_ratio_consumo_limite [s] ");
		campos.add("jestado_master_ratio_consumo_limite [s] ");
		campos.add("jestado_visa_ratio_minimo_consumo_limite [s] ");
		campos.add("jestado_master_ratio_minimo_consumo_limite [s] ");
		campos.add("tarjeta_marca_atraso [n] ");
		campos.add("tarjeta_cuenta_estado [s] ");
		campos.add("tarjeta_mfinanciacion_limite [s] ");
		campos.add("tarjeta_Fvencimiento [n] ");
		campos.add("tarjeta_Finiciomora [n] ");
		campos.add("jestado_tarjeta_dias_mora_relativa_periodo [s] ");
		campos.add("jestado_tarjeta_ratio_consumo_limite [s] ");
		campos.add("jestado_tarjeta_ratio_minimo_consumo_limite [s] ");
		campos.add("tarjeta_msaldototal [s] ");
		campos.add("tarjeta_msaldopesos [s] ");
		campos.add("tarjeta_msaldodolares [s] ");
		campos.add("tarjeta_mconsumospesos [s] ");
		campos.add("tarjeta_mconsumosdolares [s] ");
		campos.add("tarjeta_mlimitecompra [s] ");
		campos.add("tarjeta_madelantopesos [s] ");
		campos.add("tarjeta_madelantodolares [s] ");
		campos.add("tarjeta_fultimo_cierre [n] ");
		campos.add("tarjeta_mpagado [s] ");
		campos.add("tarjeta_mpagospesos [s] ");
		campos.add("tarjeta_mpagosdolares [s] ");
		campos.add("tarjeta_fechaalta [n] ");
		campos.add("tarjeta_mconsumototal [s] ");
		campos.add("tarjeta_tconsumos [n] ");
		campos.add("tarjeta_tadelantosefectivo [n] ");
		campos.add("tarjeta_mpagominimo [s] ");
		campos.add("mrentabilidad_AVG [s] ");
		campos.add("mcomisiones_AVG [s] ");
		campos.add("Master_mfinanciacion_limite_AVG [s] ");
		campos.add("Master_msaldodolares_AVG [s] ");
		campos.add("Master_mconsumospesos_AVG [s] ");
		campos.add("Master_mconsumosdolares_AVG [s] ");
		campos.add("Master_mlimitecompra_AVG [s] ");
		campos.add("Master_madelantodolares_AVG [s] ");
		campos.add("Master_mpagado_AVG [s] ");
		campos.add("Master_mpagosdolares_AVG [s] ");
		campos.add("Master_mconsumototal_AVG [s] ");
		campos.add("Visa_mfinanciacion_limite_AVG [s] ");
		campos.add("Visa_mlimitecompra_AVG [s] ");
		campos.add("Visa_madelantodolares_AVG [s] ");
		campos.add("Visa_mpagado_AVG [s] ");
		campos.add("jestado_visa_ratio_consumo_limite_AVG [s] ");
		campos.add("tarjeta_mfinanciacion_limite_AVG [s] ");
		campos.add("tarjeta_msaldodolares_AVG [s] ");
		campos.add("tarjeta_mconsumospesos_AVG [s] ");
		campos.add("tarjeta_mconsumosdolares_AVG [s] ");
		campos.add("tarjeta_mlimitecompra_AVG [s] ");
		campos.add("tarjeta_madelantodolares_AVG [s] ");
		campos.add("tarjeta_mpagosdolares_AVG [s] ");
		campos.add("tarjeta_mconsumototal_AVG [s] ");
		return campos;
	}
	
	public static Map<String, String> getAsMap() {
		
		 Map<String, String> mapa = new HashMap<String, String>();
		 
		 for (String current : getCampos()) {
			 String[]  tmp = current.split(" ");
			 mapa.put(tmp[0] , current);
		}
		return mapa;
	}
	/**
	 * recibe los nombres de los cmapos
	 * arma el sql acorde a spss maximo 4 campos por linea y no deben exceder 90 caracters
	 * sin partir como: 
	 * 'cautoservicio_transacciones, tcajas, tcajas_consultas, tcajas_depositos, tcajas_extracciones, '+",
	 * @param lista
	 * @return
	 */
	public static  String transformToSpssSql(Collection<String> lista){
		int cota = 1;
		boolean lastcoma = true;
		StringBuilder build = new StringBuilder();
		build.append("'");
		for (String campo : lista) {
			
			if (campo.equalsIgnoreCase("clase")) {
				// salta al siguiente
				continue;
			}
			
			lastcoma = true;
			if (cota == 5) {
				lastcoma = false;
				build.append("'+\n");
				build.append("'");
				cota=0;
			}
			build.append(campo).append(", ");
			cota++;
		}
		if (lastcoma) {
			return build.substring(0, build.length()-2);
		}else{
			return build.substring(0, build.length()-4);
		}
	}
	/**
	 * recibe los params con tipos   y no deben exceder 90 caracters por linea
	 * "mtarjeta_master_descuentos [s]  ccuenta_descuentos [n]  mcuenta_descuentos [n]  ccomisiones_mantenimiento [s]  mcomisiones_mantenimiento [s]  ccomisiones_otras [s]  mcomisiones_otras [s]  tcambio_monedas [n]  ccambio_monedas_compra [n]  mcambio_monedas_compra [s]  ccambio_monedas_venta [n]  mcambio_monedas_venta [s]  ctransferencias_recibidas [n]  mtransferencias_recibidas [s]  ctransferencias_emitidas [s]  mtransferencias_emitidas [s]  cextraccion_autoservicio [s]  mextraccion_autoservicio [s]  ccheques_depositados [s] ",
	 * @param lista
	 * @return
	 */
	public static  String[] transformToSpssTreeFields(Collection<String> lista){
		int cota = 1;
		boolean lastcoma = true;
		StringBuilder build = new StringBuilder();
		build.append("\"");
		for (String campo : lista) {
			
			if (campo.equalsIgnoreCase("clase [n] ")) {
				// salta al siguiente
				continue;
			}
			
			lastcoma = true;
			if (cota == 5) {
				lastcoma = false;
				build.append("\",\n");
				build.append("\"");
				cota=0;
			}
			build.append(campo);
			cota++;
		}
		
		if (lastcoma) {
			return build.append("\"").substring(0, build.length()).split(",");
		}else{
			return build.toString().split(",");
		}
		
		
	}
	
	public static Map<String, String> getCamposAUsarRandom(int randCount) {
		Map<String, String> mapa  = getAsMap();
		List<String> tmpRand = new ArrayList<String>();
		tmpRand.addAll(mapa.keySet());
		Map<String, String> mapaSelected  = new HashMap<String, String>();
		for (int i = 0 ; i <randCount  ; i ++){
			String currentTmp = tmpRand.get(RandomUtils.nextInt(0, randCount));
			mapaSelected.put(currentTmp, mapa.get(currentTmp));
//			tmpRand.get()
		}
		
		return mapaSelected;
		
	}
	
}
