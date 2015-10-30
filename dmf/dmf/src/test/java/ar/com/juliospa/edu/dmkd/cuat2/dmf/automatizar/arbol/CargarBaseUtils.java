package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.db.DBFinanzasDBQueryBuilder;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.db.DMFinanzasDBLoader;

/**
 * CUIDADO / WARNING
 * NO EJECUTAR TODOS LOS TEST A LA VEZ PORQUE ROMPES TOOODO D: ! !! 
 * ejecutar de a 1
 * @author julio
 *
 */
public class CargarBaseUtils {

	private Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			throw e;
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://192.168.1.113:3306/dmf", "dmkd", "dmkd");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			throw e;
		}
		if (connection != null) {
			return connection;
		} else {
			throw new Exception("Failed to make connection!");
		}
	}
	
	/**
	 * para crear la query de avg max y min de los ultimos 6
	 * en este caso se tiene filtrada la query con que ya existe una tabla que solo tiene los ultimos 6
	 * de esta forma haces la query mas rapido no tiene que fumarse la db entera
	 * crearAvgMaxMinUltimos6aLoBruto
	 * o sea , corre las funciones mas alla si tienen nulls o lo que venga ... ejecuta funcion
	 */
	@Test
	public void crearQuery() {
		DBFinanzasDBQueryBuilder qbuilder = new DBFinanzasDBQueryBuilder();
		System.out.println(qbuilder.crearAvgMaxMinUltimos6aLoBruto());
//		System.out.println(qbuilder.getColumnNamesUltimos6aLoBruto());
	}
	
	
	@Test
	public void crearQueryParaSelectHistoriaVersion1() {
		DBFinanzasDBQueryBuilder qbuilder = new DBFinanzasDBQueryBuilder();
		System.out.println(qbuilder.getSelectColumnNamesUltimos6aLoBruto("h"));
	}
	
	@Test
	public void crearQueryParaHeaderUltimos6Historia() {
		DBFinanzasDBQueryBuilder qbuilder = new DBFinanzasDBQueryBuilder();
		System.out.println(qbuilder.getSelectColumnNamesUltimos6aLoBruto(""));
	}
	
	
/**
 * para cargar la DB grande
 */
	@Test
	public void cargarBaseGrande() {
		DMFinanzasDBLoader loader = new DMFinanzasDBLoader();
//		String path0="C:/Users/julio/Desktop/dmf_wd/producto_premium_2015.txt";
		// cambiado aproposito para no correrlo sin querer.
		String path0="";
		try {
			// primero cargo la db chica para ver si funciona
			loader.cargarBaseGrande(path0, getConnection());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * paracargar la DB acotada a 201504
	 */
	@Test
	public void cargarBaseChicaPrueba() {
		DMFinanzasDBLoader loader = new DMFinanzasDBLoader();
//		String path0 = "C:/Users/julio/Desktop/dmf_wd/producto_premium_201504.txt";
		// cambiado aproposito para no correrlo sin querer.
		String path0="";
		try {
			// primero cargo la db chica para ver si funciona
			loader.cargarBaseGrande(path0, getConnection());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	@Test
	public void armaQueryHelper() {
		Map<String, String> mapaValores =  DBFinanzasDBQueryBuilder.mapaTipos();
		Map<String, Integer> mapaIndice = mapaIndice();
		Map<String, List<Integer>> mapaTipos = new HashMap<String, List<Integer>>();
		mapaTipos.put("BIGINT", new ArrayList<Integer>());
		mapaTipos.put("TEXT", new ArrayList<Integer>());
		mapaTipos.put("DOUBLE", new ArrayList<Integer>());
		mapaTipos.put("TINYINT", new ArrayList<Integer>());

		/* guardo que campos tienen que indice */
		int i = 1;
		for (String valores : mapaValores.keySet()) {
			for (String keyTipos : mapaTipos.keySet()) {
				if (mapaValores.get(valores).contains(keyTipos)) {
					List<Integer> current = mapaTipos.get(keyTipos);
					current.add(mapaIndice.get(valores));
					mapaTipos.put(keyTipos, current);
					break;// break del 2do for, si encuentra alguno corto y sigo
							// con el siguiente campo
				}
			}
			i++; // sumo 1 en campo
		}

		for (String tipos : mapaTipos.keySet()) {
			String tmp = "int[] "+tipos.toLowerCase()+"List = " + mapaTipos.get(tipos).toString().replace("[", "{").replace("]", "};");
			System.out.println(tmp);
		}

	}

	private static Map<String, String> mapaValores(String lineStr) {

		String[] line = lineStr.split("\t");

		Map<String, String> mapaValores = new HashMap<String, String>();
		mapaValores.put("numero_de_cliente", line[0]);
		mapaValores.put("foto_mes", line[1]);
		mapaValores.put("marketing_activo_ultimos90dias", line[2]);
		mapaValores.put("cliente_vip", line[3]);
		mapaValores.put("cliente_sucursal", line[4]);
		mapaValores.put("cliente_edad", line[5]);
		mapaValores.put("cliente_antiguedad", line[6]);
		mapaValores.put("mrentabilidad", line[7]);
		mapaValores.put("mrentabilidad_annual", line[8]);
		mapaValores.put("mcomisiones", line[9]);
		mapaValores.put("mactivos_margen", line[10]);
		mapaValores.put("mpasivos_margen", line[11]);
		mapaValores.put("marketing_coss_selling", line[12]);
		mapaValores.put("tpaquete1", line[13]);
		mapaValores.put("tpaquete2", line[14]);
		mapaValores.put("tpaquete3", line[15]);
		mapaValores.put("tpaquete4", line[16]);
		mapaValores.put("tpaquete5", line[17]);
		mapaValores.put("tpaquete6", line[18]);
		mapaValores.put("tpaquete7", line[19]);
		mapaValores.put("tpaquete8", line[20]);
		mapaValores.put("tpaquete9", line[21]);
		mapaValores.put("tcuentas", line[22]);
		mapaValores.put("tcuenta_corriente", line[23]);
		mapaValores.put("mcuenta_corriente_Nopaquete", line[24]);
		mapaValores.put("mcuenta_corriente_Paquete", line[25]);
		mapaValores.put("mcuenta_corriente_dolares", line[26]);
		mapaValores.put("tcaja_ahorro", line[27]);
		mapaValores.put("mcaja_ahorro_Paquete", line[28]);
		mapaValores.put("mcaja_ahorro_Nopaquete", line[29]);
		mapaValores.put("mcaja_ahorro_dolares", line[30]);
		mapaValores.put("mdescubierto_preacordado", line[31]);
		mapaValores.put("mcuentas_saldo", line[32]);
		mapaValores.put("ttarjeta_debito", line[33]);
		mapaValores.put("ctarjeta_debito_transacciones", line[34]);
		mapaValores.put("mautoservicio", line[35]);
		mapaValores.put("ttarjeta_visa", line[36]);
		mapaValores.put("ctarjeta_visa_transacciones", line[37]);
		mapaValores.put("mtarjeta_visa_consumo", line[38]);
		mapaValores.put("ttarjeta_master", line[39]);
		mapaValores.put("ctarjeta_master_transacciones", line[40]);
		mapaValores.put("mtarjeta_master_consumo", line[41]);
		mapaValores.put("cprestamos_personales", line[42]);
		mapaValores.put("mprestamos_personales", line[43]);
		mapaValores.put("cprestamos_prendarios", line[44]);
		mapaValores.put("mprestamos_prendarios", line[45]);
		mapaValores.put("cprestamos_hipotecarios", line[46]);
		mapaValores.put("mprestamos_hipotecarios", line[47]);
		mapaValores.put("tplazo_fijo", line[48]);
		mapaValores.put("mplazo_fijo_dolares", line[49]);
		mapaValores.put("mplazo_fijo_pesos", line[50]);
		mapaValores.put("tfondos_comunes_inversion", line[51]);
		mapaValores.put("mfondos_comunes_inversion_pesos", line[52]);
		mapaValores.put("mfondos_comunes_inversion_dolares", line[53]);
		mapaValores.put("ttitulos", line[54]);
		mapaValores.put("mtitulos", line[55]);
		mapaValores.put("tseguro_vida_mercado_abierto", line[56]);
		mapaValores.put("tseguro_auto", line[57]);
		mapaValores.put("tseguro_vivienda", line[58]);
		mapaValores.put("tseguro_accidentes_personales", line[59]);
		mapaValores.put("tcaja_seguridad", line[60]);
		mapaValores.put("mbonos_gobierno", line[61]);
		mapaValores.put("mmonedas_extranjeras", line[62]);
		mapaValores.put("minversiones_otras", line[63]);
		mapaValores.put("tplan_sueldo", line[64]);
		mapaValores.put("mplan_sueldo", line[65]);
		mapaValores.put("mplan_sueldo_manual", line[66]);
		mapaValores.put("cplan_sueldo_transaccion", line[67]);
		mapaValores.put("tcuenta_debitos_automaticos", line[68]);
		mapaValores.put("mcuenta_debitos_automaticos", line[69]);
		mapaValores.put("ttarjeta_visa_debitos_automaticos", line[70]);
		mapaValores.put("mttarjeta_visa_debitos_automaticos", line[71]);
		mapaValores.put("ttarjeta_master_debitos_automaticos", line[72]);
		mapaValores.put("mttarjeta_master_debitos_automaticos", line[73]);
		mapaValores.put("tpagodeservicios", line[74]);
		mapaValores.put("mpagodeservicios", line[75]);
		mapaValores.put("tpagomiscuentas", line[76]);
		mapaValores.put("mpagomiscuentas", line[77]);
		mapaValores.put("ccajeros_propios_descuentos", line[78]);
		mapaValores.put("mcajeros_propios_descuentos", line[79]);
		mapaValores.put("ctarjeta_visa_descuentos", line[80]);
		mapaValores.put("mtarjeta_visa_descuentos", line[81]);
		mapaValores.put("ctarjeta_master_descuentos", line[82]);
		mapaValores.put("mtarjeta_master_descuentos", line[83]);
		mapaValores.put("ccuenta_descuentos", line[84]);
		mapaValores.put("mcuenta_descuentos", line[85]);
		mapaValores.put("ccomisiones_mantenimiento", line[86]);
		mapaValores.put("mcomisiones_mantenimiento", line[87]);
		mapaValores.put("ccomisiones_otras", line[88]);
		mapaValores.put("mcomisiones_otras", line[89]);
		mapaValores.put("tcambio_monedas", line[90]);
		mapaValores.put("ccambio_monedas_compra", line[91]);
		mapaValores.put("mcambio_monedas_compra", line[92]);
		mapaValores.put("ccambio_monedas_venta", line[93]);
		mapaValores.put("mcambio_monedas_venta", line[94]);
		mapaValores.put("ctransferencias_recibidas", line[95]);
		mapaValores.put("mtransferencias_recibidas", line[96]);
		mapaValores.put("ctransferencias_emitidas", line[97]);
		mapaValores.put("mtransferencias_emitidas", line[98]);
		mapaValores.put("cextraccion_autoservicio", line[99]);
		mapaValores.put("mextraccion_autoservicio", line[100]);
		mapaValores.put("ccheques_depositados", line[101]);
		mapaValores.put("mcheques_depositados", line[102]);
		mapaValores.put("ccheques_emitidos", line[103]);
		mapaValores.put("mcheques_emitidos", line[104]);
		mapaValores.put("ccheques_depositados_rechazados", line[105]);
		mapaValores.put("mcheques_depositados_rechazados", line[106]);
		mapaValores.put("ccheques_emitidos_rechazados", line[107]);
		mapaValores.put("mcheques_emitidos_rechazados", line[108]);
		mapaValores.put("tcallcenter", line[109]);
		mapaValores.put("ccallcenter_transacciones", line[110]);
		mapaValores.put("thomebanking", line[111]);
		mapaValores.put("chomebanking_transacciones", line[112]);
		mapaValores.put("tautoservicio", line[113]);
		mapaValores.put("cautoservicio_transacciones", line[114]);
		mapaValores.put("tcajas", line[115]);
		mapaValores.put("tcajas_consultas", line[116]);
		mapaValores.put("tcajas_depositos", line[117]);
		mapaValores.put("tcajas_extracciones", line[118]);
		mapaValores.put("tcajas_otras", line[119]);
		mapaValores.put("ccajeros_propio_transacciones", line[120]);
		mapaValores.put("mcajeros_propio", line[121]);
		mapaValores.put("ccajeros_ajenos_transacciones", line[122]);
		mapaValores.put("mcajeros_ajenos", line[123]);
		mapaValores.put("tmovimientos_ultimos90dias", line[124]);
		mapaValores.put("Master_marca_atraso", line[125]);
		mapaValores.put("Master_cuenta_estado", line[126]);
		mapaValores.put("Master_mfinanciacion_limite", line[127]);
		mapaValores.put("Master_Fvencimiento", line[128]);
		mapaValores.put("Master_Finiciomora", line[129]);
		mapaValores.put("Master_msaldototal", line[130]);
		mapaValores.put("Master_msaldopesos", line[131]);
		mapaValores.put("Master_msaldodolares", line[132]);
		mapaValores.put("Master_mconsumospesos", line[133]);
		mapaValores.put("Master_mconsumosdolares", line[134]);
		mapaValores.put("Master_mlimitecompra", line[135]);
		mapaValores.put("Master_madelantopesos", line[136]);
		mapaValores.put("Master_madelantodolares", line[137]);
		mapaValores.put("Master_fultimo_cierre", line[138]);
		mapaValores.put("Master_mpagado", line[139]);
		mapaValores.put("Master_mpagospesos", line[140]);
		mapaValores.put("Master_mpagosdolares", line[141]);
		mapaValores.put("Master_fechaalta", line[142]);
		mapaValores.put("Master_mconsumototal", line[143]);
		mapaValores.put("Master_tconsumos", line[144]);
		mapaValores.put("Master_tadelantosefectivo", line[145]);
		mapaValores.put("Master_mpagominimo", line[146]);
		mapaValores.put("Visa_marca_atraso", line[147]);
		mapaValores.put("Visa_cuenta_estado", line[148]);
		mapaValores.put("Visa_mfinanciacion_limite", line[149]);
		mapaValores.put("Visa_Fvencimiento", line[150]);
		mapaValores.put("Visa_Finiciomora", line[151]);
		mapaValores.put("Visa_msaldototal", line[152]);
		mapaValores.put("Visa_msaldopesos", line[153]);
		mapaValores.put("Visa_msaldodolares", line[154]);
		mapaValores.put("Visa_mconsumospesos", line[155]);
		mapaValores.put("Visa_mconsumosdolares", line[156]);
		mapaValores.put("Visa_mlimitecompra", line[157]);
		mapaValores.put("Visa_madelantopesos", line[158]);
		mapaValores.put("Visa_madelantodolares", line[159]);
		mapaValores.put("Visa_fultimo_cierre", line[160]);
		mapaValores.put("Visa_mpagado", line[161]);
		mapaValores.put("Visa_mpagospesos", line[162]);
		mapaValores.put("Visa_mpagosdolares", line[163]);
		mapaValores.put("Visa_fechaalta", line[164]);
		mapaValores.put("Visa_mconsumototal", line[165]);
		mapaValores.put("Visa_tconsumos", line[166]);
		mapaValores.put("Visa_tadelantosefectivo", line[167]);
		mapaValores.put("Visa_mpagominimo", line[168]);
		mapaValores.put("participa", line[169]);
		mapaValores.put("clase", line[170]);
		return mapaValores;
	}

	

	public Map<String, Integer> mapaIndice() {
		Map<String, Integer> mapaIndice = new HashMap<String, Integer>();
		mapaIndice.put("numero_de_cliente", 1);
		mapaIndice.put("foto_mes", 2);
		mapaIndice.put("marketing_activo_ultimos90dias", 3);
		mapaIndice.put("cliente_vip", 4);
		mapaIndice.put("cliente_sucursal", 5);
		mapaIndice.put("cliente_edad", 6);
		mapaIndice.put("cliente_antiguedad", 7);
		mapaIndice.put("mrentabilidad", 8);
		mapaIndice.put("mrentabilidad_annual", 9);
		mapaIndice.put("mcomisiones", 10);
		mapaIndice.put("mactivos_margen", 11);
		mapaIndice.put("mpasivos_margen", 12);
		mapaIndice.put("marketing_coss_selling", 13);
		mapaIndice.put("tpaquete1", 14);
		mapaIndice.put("tpaquete2", 15);
		mapaIndice.put("tpaquete3", 16);
		mapaIndice.put("tpaquete4", 17);
		mapaIndice.put("tpaquete5", 18);
		mapaIndice.put("tpaquete6", 19);
		mapaIndice.put("tpaquete7", 20);
		mapaIndice.put("tpaquete8", 21);
		mapaIndice.put("tpaquete9", 22);
		mapaIndice.put("tcuentas", 23);
		mapaIndice.put("tcuenta_corriente", 24);
		mapaIndice.put("mcuenta_corriente_Nopaquete", 25);
		mapaIndice.put("mcuenta_corriente_Paquete", 26);
		mapaIndice.put("mcuenta_corriente_dolares", 27);
		mapaIndice.put("tcaja_ahorro", 28);
		mapaIndice.put("mcaja_ahorro_Paquete", 29);
		mapaIndice.put("mcaja_ahorro_Nopaquete", 30);
		mapaIndice.put("mcaja_ahorro_dolares", 31);
		mapaIndice.put("mdescubierto_preacordado", 32);
		mapaIndice.put("mcuentas_saldo", 33);
		mapaIndice.put("ttarjeta_debito", 34);
		mapaIndice.put("ctarjeta_debito_transacciones", 35);
		mapaIndice.put("mautoservicio", 36);
		mapaIndice.put("ttarjeta_visa", 37);
		mapaIndice.put("ctarjeta_visa_transacciones", 38);
		mapaIndice.put("mtarjeta_visa_consumo", 39);
		mapaIndice.put("ttarjeta_master", 40);
		mapaIndice.put("ctarjeta_master_transacciones", 41);
		mapaIndice.put("mtarjeta_master_consumo", 42);
		mapaIndice.put("cprestamos_personales", 43);
		mapaIndice.put("mprestamos_personales", 44);
		mapaIndice.put("cprestamos_prendarios", 45);
		mapaIndice.put("mprestamos_prendarios", 46);
		mapaIndice.put("cprestamos_hipotecarios", 47);
		mapaIndice.put("mprestamos_hipotecarios", 48);
		mapaIndice.put("tplazo_fijo", 49);
		mapaIndice.put("mplazo_fijo_dolares", 50);
		mapaIndice.put("mplazo_fijo_pesos", 51);
		mapaIndice.put("tfondos_comunes_inversion", 52);
		mapaIndice.put("mfondos_comunes_inversion_pesos", 53);
		mapaIndice.put("mfondos_comunes_inversion_dolares", 54);
		mapaIndice.put("ttitulos", 55);
		mapaIndice.put("mtitulos", 56);
		mapaIndice.put("tseguro_vida_mercado_abierto", 57);
		mapaIndice.put("tseguro_auto", 58);
		mapaIndice.put("tseguro_vivienda", 59);
		mapaIndice.put("tseguro_accidentes_personales", 60);
		mapaIndice.put("tcaja_seguridad", 61);
		mapaIndice.put("mbonos_gobierno", 62);
		mapaIndice.put("mmonedas_extranjeras", 63);
		mapaIndice.put("minversiones_otras", 64);
		mapaIndice.put("tplan_sueldo", 65);
		mapaIndice.put("mplan_sueldo", 66);
		mapaIndice.put("mplan_sueldo_manual", 67);
		mapaIndice.put("cplan_sueldo_transaccion", 68);
		mapaIndice.put("tcuenta_debitos_automaticos", 69);
		mapaIndice.put("mcuenta_debitos_automaticos", 70);
		mapaIndice.put("ttarjeta_visa_debitos_automaticos", 71);
		mapaIndice.put("mttarjeta_visa_debitos_automaticos", 72);
		mapaIndice.put("ttarjeta_master_debitos_automaticos", 73);
		mapaIndice.put("mttarjeta_master_debitos_automaticos", 74);
		mapaIndice.put("tpagodeservicios", 75);
		mapaIndice.put("mpagodeservicios", 76);
		mapaIndice.put("tpagomiscuentas", 77);
		mapaIndice.put("mpagomiscuentas", 78);
		mapaIndice.put("ccajeros_propios_descuentos", 79);
		mapaIndice.put("mcajeros_propios_descuentos", 80);
		mapaIndice.put("ctarjeta_visa_descuentos", 81);
		mapaIndice.put("mtarjeta_visa_descuentos", 82);
		mapaIndice.put("ctarjeta_master_descuentos", 83);
		mapaIndice.put("mtarjeta_master_descuentos", 84);
		mapaIndice.put("ccuenta_descuentos", 85);
		mapaIndice.put("mcuenta_descuentos", 86);
		mapaIndice.put("ccomisiones_mantenimiento", 87);
		mapaIndice.put("mcomisiones_mantenimiento", 88);
		mapaIndice.put("ccomisiones_otras", 89);
		mapaIndice.put("mcomisiones_otras", 90);
		mapaIndice.put("tcambio_monedas", 91);
		mapaIndice.put("ccambio_monedas_compra", 92);
		mapaIndice.put("mcambio_monedas_compra", 93);
		mapaIndice.put("ccambio_monedas_venta", 94);
		mapaIndice.put("mcambio_monedas_venta", 95);
		mapaIndice.put("ctransferencias_recibidas", 96);
		mapaIndice.put("mtransferencias_recibidas", 97);
		mapaIndice.put("ctransferencias_emitidas", 98);
		mapaIndice.put("mtransferencias_emitidas", 99);
		mapaIndice.put("cextraccion_autoservicio", 100);
		mapaIndice.put("mextraccion_autoservicio", 101);
		mapaIndice.put("ccheques_depositados", 102);
		mapaIndice.put("mcheques_depositados", 103);
		mapaIndice.put("ccheques_emitidos", 104);
		mapaIndice.put("mcheques_emitidos", 105);
		mapaIndice.put("ccheques_depositados_rechazados", 106);
		mapaIndice.put("mcheques_depositados_rechazados", 107);
		mapaIndice.put("ccheques_emitidos_rechazados", 108);
		mapaIndice.put("mcheques_emitidos_rechazados", 109);
		mapaIndice.put("tcallcenter", 110);
		mapaIndice.put("ccallcenter_transacciones", 111);
		mapaIndice.put("thomebanking", 112);
		mapaIndice.put("chomebanking_transacciones", 113);
		mapaIndice.put("tautoservicio", 114);
		mapaIndice.put("cautoservicio_transacciones", 115);
		mapaIndice.put("tcajas", 116);
		mapaIndice.put("tcajas_consultas", 117);
		mapaIndice.put("tcajas_depositos", 118);
		mapaIndice.put("tcajas_extracciones", 119);
		mapaIndice.put("tcajas_otras", 120);
		mapaIndice.put("ccajeros_propio_transacciones", 121);
		mapaIndice.put("mcajeros_propio", 122);
		mapaIndice.put("ccajeros_ajenos_transacciones", 123);
		mapaIndice.put("mcajeros_ajenos", 124);
		mapaIndice.put("tmovimientos_ultimos90dias", 125);
		mapaIndice.put("Master_marca_atraso", 126);
		mapaIndice.put("Master_cuenta_estado", 127);
		mapaIndice.put("Master_mfinanciacion_limite", 128);
		mapaIndice.put("Master_Fvencimiento", 129);
		mapaIndice.put("Master_Finiciomora", 130);
		mapaIndice.put("Master_msaldototal", 131);
		mapaIndice.put("Master_msaldopesos", 132);
		mapaIndice.put("Master_msaldodolares", 133);
		mapaIndice.put("Master_mconsumospesos", 134);
		mapaIndice.put("Master_mconsumosdolares", 135);
		mapaIndice.put("Master_mlimitecompra", 136);
		mapaIndice.put("Master_madelantopesos", 137);
		mapaIndice.put("Master_madelantodolares", 138);
		mapaIndice.put("Master_fultimo_cierre", 139);
		mapaIndice.put("Master_mpagado", 140);
		mapaIndice.put("Master_mpagospesos", 141);
		mapaIndice.put("Master_mpagosdolares", 142);
		mapaIndice.put("Master_fechaalta", 143);
		mapaIndice.put("Master_mconsumototal", 144);
		mapaIndice.put("Master_tconsumos", 145);
		mapaIndice.put("Master_tadelantosefectivo", 146);
		mapaIndice.put("Master_mpagominimo", 147);
		mapaIndice.put("Visa_marca_atraso", 148);
		mapaIndice.put("Visa_cuenta_estado", 149);
		mapaIndice.put("Visa_mfinanciacion_limite", 150);
		mapaIndice.put("Visa_Fvencimiento", 151);
		mapaIndice.put("Visa_Finiciomora", 152);
		mapaIndice.put("Visa_msaldototal", 153);
		mapaIndice.put("Visa_msaldopesos", 154);
		mapaIndice.put("Visa_msaldodolares", 155);
		mapaIndice.put("Visa_mconsumospesos", 156);
		mapaIndice.put("Visa_mconsumosdolares", 157);
		mapaIndice.put("Visa_mlimitecompra", 158);
		mapaIndice.put("Visa_madelantopesos", 159);
		mapaIndice.put("Visa_madelantodolares", 160);
		mapaIndice.put("Visa_fultimo_cierre", 161);
		mapaIndice.put("Visa_mpagado", 162);
		mapaIndice.put("Visa_mpagospesos", 163);
		mapaIndice.put("Visa_mpagosdolares", 164);
		mapaIndice.put("Visa_fechaalta", 165);
		mapaIndice.put("Visa_mconsumototal", 166);
		mapaIndice.put("Visa_tconsumos", 167);
		mapaIndice.put("Visa_tadelantosefectivo", 168);
		mapaIndice.put("Visa_mpagominimo", 169);
		mapaIndice.put("participa", 170);
		mapaIndice.put("clase", 171);

		return mapaIndice;
	}
}
