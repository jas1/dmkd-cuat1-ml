package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * la idea de esta clase es ayudar a crear ciertas queries 
 * que hacerlas a mano puede requerir bastante tiempo
 * @author julio
 *
 */
public class DBFinanzasDBQueryBuilder {

	
	private List<Integer> listaBigints;
	private List<Integer> listaText;
	private List<Integer> listaDoubles;
	private List<Integer> listaBools;

	public DBFinanzasDBQueryBuilder() {
		int[] bigintList = { 6, 68, 143, 79, 63, 87, 111, 7, 47, 104, 129, 83, 149, 86, 152, 139, 108, 126, 81, 161, 113, 130, 1, 165, 115, 64, 3, 123, 38, 62, 106, 148, 102, 45, 89, 85, 4, 92, 94, 35, 98, 5, 43, 100, 127, 96, 2, 13, 41, 151, 121 };
		int[] textList = { 171 };
		// 27 y 9 eran big int pero revento a mitad del proceso , el 27 revento ya muy avanzado el procesamiento en lso 3 millones y pico 
		// el 9 revento casi al inicio
		int[] doubleList = { 27,9,128, 166, 138, 133, 164, 103, 32, 99, 82, 124, 153, 76, 159, 11, 107, 29, 39, 56, 158, 140, 53, 157, 109, 51, 155, 67, 78, 74, 142, 54, 101, 169, 25, 12, 150, 84, 50, 162, 135, 105, 26, 70, 46, 31, 10, 136, 163, 95, 137, 36, 66, 154, 72, 160, 147, 156, 93, 141, 48, 134, 97, 131, 8, 132, 144, 90, 30, 122, 44, 42, 33, 88, 80 };
		int[] tinyintList = { 16, 17, 14, 15, 37, 60, 22, 20, 170, 21, 18, 19, 59, 24, 28, 65, 91, 58, 23, 119, 112, 75, 125, 61, 117, 57, 167, 146, 77, 110, 71, 168, 145, 49, 34, 116, 114, 52, 55, 118, 40, 69, 120, 73 };

		listaBigints = converToLista(bigintList);
		listaText = converToLista(textList);
		listaDoubles = converToLista(doubleList);
		listaBools = converToLista(tinyintList);
	}
	
	public String getColumnNamesUltimos6aLoBruto(){
		
		List<String> blacklistColumnas = new ArrayList<String>();
		blacklistColumnas.add("numero_de_cliente");
		blacklistColumnas.add("foto_mes");
		blacklistColumnas.add("clase");
		
		List<String> funciones = new ArrayList<String>();
		funciones.add("MAX");
		funciones.add("MIN");
		funciones.add("AVG");
		
		StringBuilder camposSelectBuilder = new StringBuilder();
		// ahora vienen todas las funciones
		Map<String, String> mapaTipos = DBFinanzasDBQueryBuilder.mapaTipos();
		for (String nombreCol : mapaTipos.keySet()) {
			
			// MAX(mrentabilidad) as mrentabilidad_MAX ,MIN(mrentabilidad) as mrentabilidad_MIN ,AVG(mrentabilidad) as mrentabilidad_AVG,
			// si no esta en listado de columnas excluidas
			if (!blacklistColumnas.contains(nombreCol)) {
				// para cada funcion crea una nueva col
				for (String funcion : funciones) {
					camposSelectBuilder.append(nombreCol).append("_").append(funcion).append(", ");
				}
				// cada vez que se acaban las funciones de 1 columna tira un enter asi quedan agrupadas de a 3
				camposSelectBuilder.append(System.getProperty("line.separator"));
			}
		}
		camposSelectBuilder.deleteCharAt(camposSelectBuilder.length()-4);
		
		return camposSelectBuilder.toString();
		
	}
	
	public String crearAvgMaxMinUltimos6aLoBruto() {
//		excluye ID_cliente 
//		excluye periodo

		List<String> blacklistColumnas = new ArrayList<String>();
		blacklistColumnas.add("numero_de_cliente");
		blacklistColumnas.add("foto_mes");
		blacklistColumnas.add("clase");
		
		List<String> funciones = new ArrayList<String>();
		funciones.add("MAX");
		funciones.add("MIN");
		funciones.add("AVG");
		
		// FIXME: falta terminar esto. 
		// query modelo, seria algo asi salvo para los campos  numero_de_cliente,foto_mes,clase
		/*
		 * 
select numero_de_cliente,foto_mes, 
MAX(mrentabilidad) as mrentabilidad_MAX ,MIN(mrentabilidad) as mrentabilidad_MIN ,AVG(mrentabilidad) as mrentabilidad_AVG, 
MAX(mrentabilidad_annual) as mrentabilidad_annual_MAX ,MIN(mrentabilidad_annual)as mrentabilidad_annual_MIN ,AVG(mrentabilidad_annual) as mrentabilidad_annual_AVG 
from dm_finanzas_historia f where numero_de_cliente = 2048 and foto_mes in(201411,201412,201501,201502,201503,201504) group by numero_de_cliente,foto_mes


numero_de_cliente	foto_mes	mrentabilidad_MAX	mrentabilidad_MIN	mrentabilidad_AVG	mrentabilidad_annual_MAX	mrentabilidad_annual_MIN	mrentabilidad_annual_AVG
2048	201412	-3246091.48	-43208455.39	-20939997.938	0	0	0.0000
2048	201501	-8269753.62	-47565196.57	-34576640.424	0	0	0.0000
2048	201503	-345293.54	-18983651.58	-8521310.702	0	0	0.0000
2048	201504	5735509.51	-15173090.36	-7483071.540000001	0	0	0.0000


		 */
		StringBuilder camposSelectBuilder = new StringBuilder();
		camposSelectBuilder.append("select numero_de_cliente,foto_mes, ");
		camposSelectBuilder.append(System.getProperty("line.separator"));
		// ahora vienen todas las funciones
		Map<String, String> mapaTipos = DBFinanzasDBQueryBuilder.mapaTipos();
		for (String nombreCol : mapaTipos.keySet()) {
			
			// MAX(mrentabilidad) as mrentabilidad_MAX ,MIN(mrentabilidad) as mrentabilidad_MIN ,AVG(mrentabilidad) as mrentabilidad_AVG,
			// si no esta en listado de columnas excluidas
			if (!blacklistColumnas.contains(nombreCol)) {
				// para cada funcion crea una nueva col
				for (String funcion : funciones) {
					camposSelectBuilder.append(funcion).append("(").append(nombreCol).append(") as ").append(nombreCol).append("_").append(funcion).append(", ");
				}
				// cada vez que se acaban las funciones de 1 columna tira un enter asi quedan agrupadas de a 3
				camposSelectBuilder.append(System.getProperty("line.separator"));
			}
		}
		camposSelectBuilder.deleteCharAt(camposSelectBuilder.length()-4);
		
		camposSelectBuilder.append(" from dm_finanzas_historia_ultimos_6 f group by numero_de_cliente,foto_mes");
		
		return camposSelectBuilder.toString();
		
		
	}
	
	/**
	 * el indice debe arrrancar en 1 , sino rompe por mysql
	 * 
	 * @param bigInts
	 * @return
	 */
	public static List<Integer> converToLista(int[] bigInts) {
		List<Integer> listaBig = new ArrayList<Integer>();
		for (Integer integer : bigInts) {
			listaBig.add(integer);
		}
		return listaBig;
	}
	/**
	 * ejemplo de lo almacenado
	 * mapaTipos.put("numero_de_cliente", "BIGINT(20)"); <br>
	 * mapaTipos.put("mrentabilidad", "DOUBLE");<br>
	 * mapaTipos.put("tpaquete6", "TINYINT");<br>
	 * mapaTipos.put("clase", "TEXT");<br>
	 * @return
	 */
	public static Map<String, String> mapaTipos() {
		Map<String, String> mapaTipos = new HashMap<String, String>();
		mapaTipos.put("numero_de_cliente", "BIGINT(20)");
		mapaTipos.put("foto_mes", "BIGINT(20)");
		mapaTipos.put("marketing_activo_ultimos90dias", "BIGINT(20)");
		mapaTipos.put("cliente_vip", "BIGINT(20)");
		mapaTipos.put("cliente_sucursal", "BIGINT(20)");
		mapaTipos.put("cliente_edad", "BIGINT(20)");
		mapaTipos.put("cliente_antiguedad", "BIGINT(20)");
		mapaTipos.put("mrentabilidad", "DOUBLE");
		mapaTipos.put("mrentabilidad_annual", "BIGINT(20)");
		mapaTipos.put("mcomisiones", "DOUBLE");
		mapaTipos.put("mactivos_margen", "DOUBLE");
		mapaTipos.put("mpasivos_margen", "DOUBLE");
		mapaTipos.put("marketing_coss_selling", "BIGINT(20)");
		mapaTipos.put("tpaquete1", "TINYINT");
		mapaTipos.put("tpaquete2", "TINYINT");
		mapaTipos.put("tpaquete3", "TINYINT");
		mapaTipos.put("tpaquete4", "TINYINT");
		mapaTipos.put("tpaquete5", "TINYINT");
		mapaTipos.put("tpaquete6", "TINYINT");
		mapaTipos.put("tpaquete7", "TINYINT");
		mapaTipos.put("tpaquete8", "TINYINT");
		mapaTipos.put("tpaquete9", "TINYINT");
		mapaTipos.put("tcuentas", "TINYINT");
		mapaTipos.put("tcuenta_corriente", "TINYINT");
		mapaTipos.put("mcuenta_corriente_Nopaquete", "DOUBLE");
		mapaTipos.put("mcuenta_corriente_Paquete", "DOUBLE");
		mapaTipos.put("mcuenta_corriente_dolares", "BIGINT(20)");
		mapaTipos.put("tcaja_ahorro", "TINYINT");
		mapaTipos.put("mcaja_ahorro_Paquete", "DOUBLE");
		mapaTipos.put("mcaja_ahorro_Nopaquete", "DOUBLE");
		mapaTipos.put("mcaja_ahorro_dolares", "DOUBLE");
		mapaTipos.put("mdescubierto_preacordado", "DOUBLE");
		mapaTipos.put("mcuentas_saldo", "DOUBLE");
		mapaTipos.put("ttarjeta_debito", "TINYINT");
		mapaTipos.put("ctarjeta_debito_transacciones", "BIGINT(20)");
		mapaTipos.put("mautoservicio", "DOUBLE");
		mapaTipos.put("ttarjeta_visa", "TINYINT");
		mapaTipos.put("ctarjeta_visa_transacciones", "BIGINT(20)");
		mapaTipos.put("mtarjeta_visa_consumo", "DOUBLE");
		mapaTipos.put("ttarjeta_master", "TINYINT");
		mapaTipos.put("ctarjeta_master_transacciones", "BIGINT(20)");
		mapaTipos.put("mtarjeta_master_consumo", "DOUBLE");
		mapaTipos.put("cprestamos_personales", "BIGINT(20)");
		mapaTipos.put("mprestamos_personales", "DOUBLE");
		mapaTipos.put("cprestamos_prendarios", "BIGINT(20)");
		mapaTipos.put("mprestamos_prendarios", "DOUBLE");
		mapaTipos.put("cprestamos_hipotecarios", "BIGINT(20)");
		mapaTipos.put("mprestamos_hipotecarios", "DOUBLE");
		mapaTipos.put("tplazo_fijo", "TINYINT");
		mapaTipos.put("mplazo_fijo_dolares", "DOUBLE");
		mapaTipos.put("mplazo_fijo_pesos", "DOUBLE");
		mapaTipos.put("tfondos_comunes_inversion", "TINYINT");
		mapaTipos.put("mfondos_comunes_inversion_pesos", "DOUBLE");
		mapaTipos.put("mfondos_comunes_inversion_dolares", "DOUBLE");
		mapaTipos.put("ttitulos", "TINYINT");
		mapaTipos.put("mtitulos", "DOUBLE");
		mapaTipos.put("tseguro_vida_mercado_abierto", "TINYINT");
		mapaTipos.put("tseguro_auto", "TINYINT");
		mapaTipos.put("tseguro_vivienda", "TINYINT");
		mapaTipos.put("tseguro_accidentes_personales", "TINYINT");
		mapaTipos.put("tcaja_seguridad", "TINYINT");
		mapaTipos.put("mbonos_gobierno", "BIGINT(20)");
		mapaTipos.put("mmonedas_extranjeras", "BIGINT(20)");
		mapaTipos.put("minversiones_otras", "BIGINT(20)");
		mapaTipos.put("tplan_sueldo", "TINYINT");
		mapaTipos.put("mplan_sueldo", "DOUBLE");
		mapaTipos.put("mplan_sueldo_manual", "DOUBLE");
		mapaTipos.put("cplan_sueldo_transaccion", "BIGINT(20)");
		mapaTipos.put("tcuenta_debitos_automaticos", "TINYINT");
		mapaTipos.put("mcuenta_debitos_automaticos", "DOUBLE");
		mapaTipos.put("ttarjeta_visa_debitos_automaticos", "TINYINT");
		mapaTipos.put("mttarjeta_visa_debitos_automaticos", "DOUBLE");
		mapaTipos.put("ttarjeta_master_debitos_automaticos", "TINYINT");
		mapaTipos.put("mttarjeta_master_debitos_automaticos", "DOUBLE");
		mapaTipos.put("tpagodeservicios", "TINYINT");
		mapaTipos.put("mpagodeservicios", "DOUBLE");
		mapaTipos.put("tpagomiscuentas", "TINYINT");
		mapaTipos.put("mpagomiscuentas", "DOUBLE");
		mapaTipos.put("ccajeros_propios_descuentos", "BIGINT(20)");
		mapaTipos.put("mcajeros_propios_descuentos", "DOUBLE");
		mapaTipos.put("ctarjeta_visa_descuentos", "BIGINT(20)");
		mapaTipos.put("mtarjeta_visa_descuentos", "DOUBLE");
		mapaTipos.put("ctarjeta_master_descuentos", "BIGINT(20)");
		mapaTipos.put("mtarjeta_master_descuentos", "DOUBLE");
		mapaTipos.put("ccuenta_descuentos", "BIGINT(20)");
		mapaTipos.put("mcuenta_descuentos", "BIGINT(20)");
		mapaTipos.put("ccomisiones_mantenimiento", "BIGINT(20)");
		mapaTipos.put("mcomisiones_mantenimiento", "DOUBLE");
		mapaTipos.put("ccomisiones_otras", "BIGINT(20)");
		mapaTipos.put("mcomisiones_otras", "DOUBLE");
		mapaTipos.put("tcambio_monedas", "TINYINT");
		mapaTipos.put("ccambio_monedas_compra", "BIGINT(20)");
		mapaTipos.put("mcambio_monedas_compra", "DOUBLE");
		mapaTipos.put("ccambio_monedas_venta", "BIGINT(20)");
		mapaTipos.put("mcambio_monedas_venta", "DOUBLE");
		mapaTipos.put("ctransferencias_recibidas", "BIGINT(20)");
		mapaTipos.put("mtransferencias_recibidas", "DOUBLE");
		mapaTipos.put("ctransferencias_emitidas", "BIGINT(20)");
		mapaTipos.put("mtransferencias_emitidas", "DOUBLE");
		mapaTipos.put("cextraccion_autoservicio", "BIGINT(20)");
		mapaTipos.put("mextraccion_autoservicio", "DOUBLE");
		mapaTipos.put("ccheques_depositados", "BIGINT(20)");
		mapaTipos.put("mcheques_depositados", "DOUBLE");
		mapaTipos.put("ccheques_emitidos", "BIGINT(20)");
		mapaTipos.put("mcheques_emitidos", "DOUBLE");
		mapaTipos.put("ccheques_depositados_rechazados", "BIGINT(20)");
		mapaTipos.put("mcheques_depositados_rechazados", "DOUBLE");
		mapaTipos.put("ccheques_emitidos_rechazados", "BIGINT(20)");
		mapaTipos.put("mcheques_emitidos_rechazados", "DOUBLE");
		mapaTipos.put("tcallcenter", "TINYINT");
		mapaTipos.put("ccallcenter_transacciones", "BIGINT(20)");
		mapaTipos.put("thomebanking", "TINYINT");
		mapaTipos.put("chomebanking_transacciones", "BIGINT(20)");
		mapaTipos.put("tautoservicio", "TINYINT");
		mapaTipos.put("cautoservicio_transacciones", "BIGINT(20)");
		mapaTipos.put("tcajas", "TINYINT");
		mapaTipos.put("tcajas_consultas", "TINYINT");
		mapaTipos.put("tcajas_depositos", "TINYINT");
		mapaTipos.put("tcajas_extracciones", "TINYINT");
		mapaTipos.put("tcajas_otras", "TINYINT");
		mapaTipos.put("ccajeros_propio_transacciones", "BIGINT(20)");
		mapaTipos.put("mcajeros_propio", "DOUBLE");
		mapaTipos.put("ccajeros_ajenos_transacciones", "BIGINT(20)");
		mapaTipos.put("mcajeros_ajenos", "DOUBLE");
		mapaTipos.put("tmovimientos_ultimos90dias", "TINYINT");
		mapaTipos.put("Master_marca_atraso", "BIGINT(20)");
		mapaTipos.put("Master_cuenta_estado", "BIGINT(20)");
		mapaTipos.put("Master_mfinanciacion_limite", "DOUBLE");
		mapaTipos.put("Master_Fvencimiento", "BIGINT(20)");
		mapaTipos.put("Master_Finiciomora", "BIGINT(20)");
		mapaTipos.put("Master_msaldototal", "DOUBLE");
		mapaTipos.put("Master_msaldopesos", "DOUBLE");
		mapaTipos.put("Master_msaldodolares", "DOUBLE");
		mapaTipos.put("Master_mconsumospesos", "DOUBLE");
		mapaTipos.put("Master_mconsumosdolares", "DOUBLE");
		mapaTipos.put("Master_mlimitecompra", "DOUBLE");
		mapaTipos.put("Master_madelantopesos", "DOUBLE");
		mapaTipos.put("Master_madelantodolares", "DOUBLE");
		mapaTipos.put("Master_fultimo_cierre", "BIGINT(20)");
		mapaTipos.put("Master_mpagado", "DOUBLE");
		mapaTipos.put("Master_mpagospesos", "DOUBLE");
		mapaTipos.put("Master_mpagosdolares", "DOUBLE");
		mapaTipos.put("Master_fechaalta", "BIGINT(20)");
		mapaTipos.put("Master_mconsumototal", "DOUBLE");
		mapaTipos.put("Master_tconsumos", "TINYINT");
		mapaTipos.put("Master_tadelantosefectivo", "TINYINT");
		mapaTipos.put("Master_mpagominimo", "DOUBLE");
		mapaTipos.put("Visa_marca_atraso", "BIGINT(20)");
		mapaTipos.put("Visa_cuenta_estado", "BIGINT(20)");
		mapaTipos.put("Visa_mfinanciacion_limite", "DOUBLE");
		mapaTipos.put("Visa_Fvencimiento", "BIGINT(20)");
		mapaTipos.put("Visa_Finiciomora", "BIGINT(20)");
		mapaTipos.put("Visa_msaldototal", "DOUBLE");
		mapaTipos.put("Visa_msaldopesos", "DOUBLE");
		mapaTipos.put("Visa_msaldodolares", "DOUBLE");
		mapaTipos.put("Visa_mconsumospesos", "DOUBLE");
		mapaTipos.put("Visa_mconsumosdolares", "DOUBLE");
		mapaTipos.put("Visa_mlimitecompra", "DOUBLE");
		mapaTipos.put("Visa_madelantopesos", "DOUBLE");
		mapaTipos.put("Visa_madelantodolares", "DOUBLE");
		mapaTipos.put("Visa_fultimo_cierre", "BIGINT(20)");
		mapaTipos.put("Visa_mpagado", "DOUBLE");
		mapaTipos.put("Visa_mpagospesos", "DOUBLE");
		mapaTipos.put("Visa_mpagosdolares", "DOUBLE");
		mapaTipos.put("Visa_fechaalta", "BIGINT(20)");
		mapaTipos.put("Visa_mconsumototal", "DOUBLE");
		mapaTipos.put("Visa_tconsumos", "TINYINT");
		mapaTipos.put("Visa_tadelantosefectivo", "TINYINT");
		mapaTipos.put("Visa_mpagominimo", "DOUBLE");
		mapaTipos.put("participa", "TINYINT");
		mapaTipos.put("clase", "TEXT");
		return mapaTipos;
	}
	
}
