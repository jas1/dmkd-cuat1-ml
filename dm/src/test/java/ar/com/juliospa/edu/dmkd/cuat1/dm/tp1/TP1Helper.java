package ar.com.juliospa.edu.dmkd.cuat1.dm.tp1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dm.tp1.dto.Items2015;
import ar.com.juliospa.edu.dmkd.cuat1.dm.tp1.dto.ReglaEnTxDto;
import ar.com.juliospa.edu.dmkd.cuat1.dm.tp1.dto.ReglasTodasDto;

public class TP1Helper {

	// con <- dbConnect(RMySQL::MySQL(), dbname = "dm-tp1", password
	// ='dmkd',user = 'dmkd',host='192.168.1.113')

	@Test
	public void generarSQLS() throws SQLException {

		// select * from aux_reglas_punto_4_ocurrencias_items_2014 where region
		// = 'Capital Federal' AND descgen= 'CAÑA TANDURA'
		// AND venta_id in ( select venta_id from
		// aux_reglas_punto_4_ocurrencias_items_2014 where region = 'Capital
		// Federal' AND descgen= 'REEL AVENSIS FRENO DELANTERO' group by
		// venta_id)
		// group by venta_id
		//
		// # todos: CAÑA TANDURA,REEL AVENSIS FRENO DELANTERO} => {REEL HONORIUS
		// FRENO DELANTERO} : 3
		// select * from aux_reglas_punto_4_ocurrencias_items_2014 where region
		// = 'Capital Federal' AND descgen= 'REEL HONORIUS FRENO DELANTERO'
		// AND venta_id IN (
		// select venta_id from aux_reglas_punto_4_ocurrencias_items_2014 where
		// region = 'Capital Federal' AND descgen= 'CAÑA TANDURA'
		// AND venta_id in ( select venta_id from
		// aux_reglas_punto_4_ocurrencias_items_2014 where region = 'Capital
		// Federal' AND descgen= 'REEL AVENSIS FRENO DELANTERO' group by
		// venta_id)
		// group by venta_id
		// ) group by venta_id

		String conStr = "jdbc:mysql://192.168.1.113:3306/dm-tp1";
		String user = "dmkd";
		String pwd = "dmkd";
		String driverClassName = "com.mysql.jdbc.Driver";

		List<String> tablas = null;
		Connection con = getConnection(driverClassName, conStr, user, pwd);
		// traeme todas las reglas

		String query = "select * from aux_reglas_punto_4_completas";

		// tengo las reglas por region
		List<ReglasTodasDto> reglas = obtenerReglasTodasDeResultset(obtenerResultset(query, con));

		armaqueries(reglas);
		armaqueries2015(reglas);

	}

	private void armaqueries2015(List<ReglasTodasDto> reglasCapital) {
		System.out.println("---------------------2015-------------------------");
		String precRep = "${PRECEDENTE}";
		String antec1Rep = "${ANTECEDENTE1}";
		String antec2Rep = "${ANTECEDENTE2}";
		String regionRep = "${REGION}";

		String queryPrecedente = "select * from aux_reglas_punto_4_ocurrencias_items_2015 where region = '" + regionRep + "' AND descgen= '" + antec2Rep + "' \n" + "AND venta_id in ( select venta_id from aux_reglas_punto_4_ocurrencias_items_2015 where region = '" + regionRep + "' AND descgen= '" + antec1Rep + "' group by venta_id)  \n" + "group by venta_id";

		// # todos: CAÑA TANDURA,REEL AVENSIS FRENO DELANTERO} => {REEL HONORIUS
		// FRENO DELANTERO} : 3
		String queryTotal = "select * from aux_reglas_punto_4_ocurrencias_items_2015 where region = '" + regionRep + "' AND descgen= '" + precRep + "'  \n" + "AND venta_id IN (" + "select venta_id from aux_reglas_punto_4_ocurrencias_items_2015 where region = '" + regionRep + "' AND descgen= '" + antec2Rep + "'  \n" + "AND venta_id in ( select venta_id from aux_reglas_punto_4_ocurrencias_items_2015 where region = '" + regionRep + "' AND descgen= '" + antec1Rep + "'  group by venta_id) \n" + " group by venta_id" + ") group by venta_id";

		for (ReglasTodasDto regla : reglasCapital) {
			String reglaStr = regla.getRegla();
			String antecedentesPre = reglaStr.substring(reglaStr.indexOf("{") + 1, reglaStr.indexOf("}"));
			String consecuentePre = reglaStr.substring(reglaStr.lastIndexOf("{") + 1, reglaStr.lastIndexOf("}"));

			// # tengo los items de las reglas
			String[] antecedentes = antecedentesPre.split(",");
			String consecuente = consecuentePre.trim();

			String todosElementos = antecedentesPre + "," + consecuente;
			String[] todosPre = todosElementos.split(",");

			// # necesario por hardcode
			if (todosPre.length == 3) {
				System.out.println(System.lineSeparator() + "# precedente: " + reglaStr);
				System.out.println(queryPrecedente.replace(regionRep, regla.getRegion()).replace(antec1Rep, todosPre[0]).replace(antec2Rep, todosPre[1]));

				System.out.println(System.lineSeparator() + "# total " + reglaStr);
				System.out.println(queryTotal.replace(regionRep, regla.getRegion()).replace(precRep, todosPre[2]).replace(antec1Rep, todosPre[0]).replace(antec2Rep, todosPre[1]));
				System.out.println("");
				System.out.println("");
			} else {
				System.out.println(System.lineSeparator() + "# FALTA:  " + reglaStr + " --> REGLA TIENE:  " + todosPre.length);

			}

		}
	}

	private void armaqueries(List<ReglasTodasDto> reglasCapital) {

		String precRep = "${PRECEDENTE}";
		String antec1Rep = "${ANTECEDENTE1}";
		String antec2Rep = "${ANTECEDENTE2}";
		String regionRep = "${REGION}";

		String queryPrecedente = "select * from aux_reglas_punto_4_ocurrencias_items_2014 where region = '" + regionRep + "' AND descgen= '" + antec2Rep + "'" + "AND venta_id in ( select venta_id from aux_reglas_punto_4_ocurrencias_items_2014 where region = '" + regionRep + "' AND descgen= '" + antec1Rep + "' group by venta_id)" + "group by venta_id";

		// # todos: CAÑA TANDURA,REEL AVENSIS FRENO DELANTERO} => {REEL HONORIUS
		// FRENO DELANTERO} : 3
		String queryTotal = "select * from aux_reglas_punto_4_ocurrencias_items_2014 where region = '" + regionRep + "' AND descgen= '" + precRep + "' " + "AND venta_id IN (" + "select venta_id from aux_reglas_punto_4_ocurrencias_items_2014 where region = '" + regionRep + "' AND descgen= '" + antec2Rep + "'" + "AND venta_id in ( select venta_id from aux_reglas_punto_4_ocurrencias_items_2014 where region = '" + regionRep + "' AND descgen= '" + antec1Rep + "'  group by venta_id)" + " group by venta_id" + ") group by venta_id";

		for (ReglasTodasDto regla : reglasCapital) {
			String reglaStr = regla.getRegla();
			String antecedentesPre = reglaStr.substring(reglaStr.indexOf("{") + 1, reglaStr.indexOf("}"));
			String consecuentePre = reglaStr.substring(reglaStr.lastIndexOf("{") + 1, reglaStr.lastIndexOf("}"));

			// # tengo los items de las reglas
			String[] antecedentes = antecedentesPre.split(",");
			String consecuente = consecuentePre.trim();

			String todosElementos = antecedentesPre + "," + consecuente;
			String[] todosPre = todosElementos.split(",");

			// # necesario por hardcode
			if (todosPre.length == 3) {
				System.out.println(System.lineSeparator() + "# precedente: " + reglaStr);
				System.out.println(queryPrecedente.replace(regionRep, regla.getRegion()).replace(antec1Rep, todosPre[0]).replace(antec2Rep, todosPre[1]));

				System.out.println(System.lineSeparator() + "# total " + reglaStr);
				System.out.println(queryTotal.replace(regionRep, regla.getRegion()).replace(precRep, todosPre[2]).replace(antec1Rep, todosPre[0]).replace(antec2Rep, todosPre[1]));
				System.out.println("");
				System.out.println("");
			} else {
				System.out.println(System.lineSeparator() + "# FALTA:  " + reglaStr + " --> REGLA TIENE:  " + todosPre.length);

			}

		}
	}

	@Test
	public void consultaElementosRegla() throws Exception {
		// String conStr = "jdbc:mysql://172.16.1.226:3306/dm-tp1";
		String conStr = "jdbc:mysql://192.168.1.113:3306/dm-tp1";
		String user = "dmkd";
		String pwd = "dmkd";
		String driverClassName = "com.mysql.jdbc.Driver";

		List<String> tablas = null;
		Connection con = getConnection(driverClassName, conStr, user, pwd);
		// traeme todas las reglas

		String queryCapital = "select * from aux_reglas_punto_4_completas where region = 'Capital Federal'";
		String queryBA = "select * from aux_reglas_punto_4_completas where region = 'Buenos Aires'";
		String queryInterior = "select * from aux_reglas_punto_4_completas where region = 'Interior'";

		// tengo las reglas por region
		List<ReglasTodasDto> reglasCapital = obtenerReglasTodasDeResultset(obtenerResultset(queryCapital, con));
		List<ReglasTodasDto> reglasBA = obtenerReglasTodasDeResultset(obtenerResultset(queryBA, con));
		List<ReglasTodasDto> reglasInterior = obtenerReglasTodasDeResultset(obtenerResultset(queryInterior, con));

		// necesito los items por region para poder ver que las reglas validen
		// en 2015
		String queryItems2015Capital = "select * from aux_reglas_punto_4_ocurrencias_items_2015 where region = 'Capital Federal'";
		String queryItems2015BA = "select * from aux_reglas_punto_4_ocurrencias_items_2015 where region = 'Buenos Aires'";
		String queryItems2015Interior = "select * from aux_reglas_punto_4_ocurrencias_items_2015 where region = 'Interior'";

		List<Items2015> itemsCapital = obtenerItems2015DeResultset(obtenerResultset(queryItems2015Capital, con));
		List<Items2015> itemsBA = obtenerItems2015DeResultset(obtenerResultset(queryItems2015BA, con));
		List<Items2015> itemsInterior = obtenerItems2015DeResultset(obtenerResultset(queryItems2015Interior, con));

		Map<Integer, List<String>> txCapital = hacerMapaTx(itemsCapital);
		Map<Integer, List<String>> txInterior = hacerMapaTx(itemsBA);
		Map<Integer, List<String>> txBA = hacerMapaTx(itemsInterior);

		// para cada regla tengo que buscar los itemsets dependiendo de donde
		// sea la regla y sacar las nuevas medidas

		List<ReglaEnTxDto> resultCapital2015 = validarReglas2015(reglasCapital, txCapital, itemsCapital);
		System.out.println("capital 2015");
		System.out.println(showReglaEnTxDto(resultCapital2015));

		// para validar valores
		String queryItems2014Capital = "select * from aux_reglas_punto_4_ocurrencias_items_2014 where region = 'Capital Federal'";
		List<Items2015> itemsCapital2014 = obtenerItems2015DeResultset(obtenerResultset(queryItems2014Capital, con));
		Map<Integer, List<String>> txCapital2014 = hacerMapaTx(itemsCapital);
		List<ReglaEnTxDto> resultCapital2014 = validarReglas2015(reglasCapital, txCapital2014, itemsCapital2014);
		System.out.println("capital 2014");
		System.out.println(showReglaEnTxDto(resultCapital2014));

	}

	private String showReglaEnTxDto(List<ReglaEnTxDto> resultCapital2014) {
		StringBuilder build = new StringBuilder();
		for (ReglaEnTxDto reglaEnTxDto : resultCapital2014) {
			build.append(System.lineSeparator()).append("---------------------------------------").append(System.lineSeparator());
			build.append(System.lineSeparator()).append(reglaEnTxDto.getRegla().show()).append(System.lineSeparator());
			build.append(System.lineSeparator()).append("resumen:").append(System.lineSeparator());
			build.append(System.lineSeparator()).append("muestra size:").append(reglaEnTxDto.getSizeMuestra()).append(System.lineSeparator());
			build.append(System.lineSeparator()).append("support:").append(reglaEnTxDto.getSupport()).append(System.lineSeparator());
			build.append(System.lineSeparator()).append("confidence:").append(reglaEnTxDto.getConfidence()).append(System.lineSeparator());
			build.append(System.lineSeparator()).append("tx participantes:").append(reglaEnTxDto.getTxsIds().size()).append(System.lineSeparator());
			for (Integer tx : reglaEnTxDto.getTxsIds()) {
				build.append(tx).append(",");
			}
			build.append(System.lineSeparator()).append("tx antecedentes:").append(reglaEnTxDto.getTxsAntecedenteIds().size()).append(System.lineSeparator());
			for (Integer tx : reglaEnTxDto.getTxsAntecedenteIds()) {
				build.append(tx).append(",");
			}
		}
		return build.toString();

	}

	public List<ReglaEnTxDto> validarReglasV2(List<ReglasTodasDto> reglas, Map<Integer, List<String>> tx, List<Items2015> items) throws Exception {

		int cantTotalItemsParaMuestra = items.size();

		List<ReglaEnTxDto> resultado = new ArrayList<ReglaEnTxDto>();

		// tegno que armar todas las TX

		// # para cada regla, sacar los elementos que la constituyen
		for (ReglasTodasDto regla : reglas) {
			String reglaStr = regla.getRegla();
			String antecedentesPre = reglaStr.substring(reglaStr.indexOf("{") + 1, reglaStr.indexOf("}"));
			String consecuentePre = reglaStr.substring(reglaStr.lastIndexOf("{") + 1, reglaStr.lastIndexOf("}"));

			// # tengo los items de las reglas
			String[] antecedentes = antecedentesPre.split(",");
			String consecuente = consecuentePre.trim();

			String todosElementos = antecedentesPre + "," + consecuente;
			String[] todosPre = todosElementos.split(",");

			List<Integer> txIdsPresente = new ArrayList<Integer>();

			// # para confianza
			List<Integer> txIdsAntecedentePresente = new ArrayList<Integer>();

			// #otra forma de encararlo
			// # dame todos los que esta cada item.

			Map<String, List<Integer>> dondeItem = new HashMap<String, List<Integer>>();

			// # aca tengo mapa de donde esta cada item
			for (String itemName : todosPre) {
				for (Items2015 item : items) {
					if (itemName.equals(item.getItem())) {
						List<Integer> listitm = dondeItem.get(itemName);
						if (listitm == null) {
							listitm = new ArrayList<Integer>();
						}
						listitm.add(item.getTx());
						dondeItem.put(itemName, listitm);
					}
				}
			}

			// una vez que tengo mapa fijo los que tienen iguales

			// si son de 3 o 4 , lo hago hardcoded
			if (dondeItem.size() == 3) {

				List<Integer> lista1 = null;
				List<Integer> lista2 = null;
				List<Integer> lista3 = null;
				int count = 0;
				for (String key : dondeItem.keySet()) {
					switch (count) {
					case 0:
						lista1 = dondeItem.get(key);
						break;
					case 1:
						lista2 = dondeItem.get(key);
						break;
					case 2:
						lista3 = dondeItem.get(key);
						break;

					default:
						throw new Exception("PASTO");
					}

					count++;
				}

				boolean contiene1 = false;
				boolean contiene2 = false;

				for (Integer l1 : lista1) {
					for (Integer l2 : lista2) {
						if (l1 == l2) {
							contiene1 = true;
							for (Integer l3 : lista3) {
								if (l1 == l3) {
									contiene2 = true;
								}
							}
						}
					}
				}

				if (contiene1 && contiene2) {

				}

			}

		}
		return resultado;
	}

	private Map<Integer, String> hacerMapaTxSimple(List<Items2015> itemsCapital) {
		Map<Integer, String> result = new HashMap<Integer, String>();

		String currentList = null;

		for (Items2015 items2015 : itemsCapital) {
			// si el que estoy revisando ahora , traigo el mapa y no existe
			currentList = result.get(items2015.getTx());
			if (currentList == null) {
				// inicio el listado
				currentList = "";
			}
			// siempre se agrega
			currentList += " " + items2015.getItem();
		}

		return result;
	}

	private Map<Integer, List<String>> hacerMapaTx(List<Items2015> itemsCapital) {
		Map<Integer, List<String>> result = new HashMap<Integer, List<String>>();

		List<String> currentList = null;

		for (Items2015 items2015 : itemsCapital) {
			// si el que estoy revisando ahora , traigo el mapa y no existe
			currentList = result.get(items2015.getTx());
			if (currentList == null) {
				// inicio el listado
				currentList = new ArrayList<String>();
			}
			// siempre se agrega
			currentList.add(items2015.getItem());
			// agrego en el mapa
			result.put(items2015.getTx(), currentList);
		}

		return result;
	}

	public List<ReglaEnTxDto> validarReglas2015(List<ReglasTodasDto> reglas, Map<Integer, List<String>> tx, List<Items2015> items) {

		int cantTotalItemsParaMuestra = items.size();

		List<ReglaEnTxDto> resultado = new ArrayList<ReglaEnTxDto>();

		// tegno que armar todas las TX

		// # para cada regla, sacar los elementos que la constituyen
		for (ReglasTodasDto regla : reglas) {

			String reglaStr = regla.getRegla();
			String antecedentesPre = reglaStr.substring(reglaStr.indexOf("{") + 1, reglaStr.indexOf("}"));
			String consecuentePre = reglaStr.substring(reglaStr.lastIndexOf("{") + 1, reglaStr.lastIndexOf("}"));

			// # tengo los items de las reglas
			String[] antecedentes = antecedentesPre.split(",");
			String consecuente = consecuentePre.trim();

			String todosElementos = antecedentesPre + "," + consecuente;
			String[] todosPre = todosElementos.split(",");

			List<Integer> txIdsPresente = new ArrayList<Integer>();

			// # para confianza
			List<Integer> txIdsAntecedentePresente = new ArrayList<Integer>();

			// tx contiene todos ???
			for (int key : tx.keySet()) {
				Map<String, Boolean> validaTx = new HashMap<String, Boolean>();

				// reviso cada item de la regla
				for (String itemRegla : todosPre) {
					// inicio como que no esta
					validaTx.put(itemRegla, false);

					// reviso cada item de la TX, si tiene todos la agrego
					for (String itemTx : tx.get(key)) {
						// si esta en la tx le pongo que si esta
						if (itemTx.trim().equals(itemRegla.trim())) {
							validaTx.put(itemRegla, true);
						}
					}
				}
				// si todos ok lo agrega
				boolean flagAgregar = true;
				for (Boolean validado : validaTx.values()) {
					if (!validado) {
						flagAgregar = false;
						break;
					}
				}

				if (flagAgregar) {
					txIdsPresente.add(key);
				}

			}// fin for TX

			// tx contiene ANTECEDENTES ???
			for (int key : tx.keySet()) {
				Map<String, Boolean> validaTx = new HashMap<String, Boolean>();

				// reviso cada item antecedente de la regla
				for (String itemRegla : antecedentes) {
					// inicio como que no esta
					validaTx.put(itemRegla, false);

					// reviso cada item de la TX, si tiene todos la agrego
					for (String itemTx : tx.get(key)) {
						// si esta en la tx le pongo que si esta
						if (itemTx.trim().equals(itemRegla.trim())) {
							validaTx.put(itemRegla, true);
						}
					}
				}
				// si todos ok lo agrega
				boolean flagAgregar = true;
				for (Boolean validado : validaTx.values()) {
					if (!validado) {
						flagAgregar = false;
						break;
					}
				}

				if (flagAgregar) {
					txIdsAntecedentePresente.add(key);
				}

			}// fin for TX

			// creo el obj de almacen para ver como salio
			ReglaEnTxDto temp = new ReglaEnTxDto();
			temp.setSizeMuestra(cantTotalItemsParaMuestra);
			// le seteo la regla
			temp.setRegla(regla);
			// le seteo las tx presente
			temp.setTxsIds(txIdsPresente);

			// le seteo las tx antecedente presente
			temp.setTxsAntecedenteIds(txIdsAntecedentePresente);

			// lo agrego al resultado
			// calculo como salio la regla
			// para cada item lo tengo que buscar en la parte de items
			// # calcula support: cantidad productos regla juntos / total de la
			// muestra
			// total de juntos es el txs ids.

			// # calcula confianza: cantidad productos regla juntos / cantidad
			// productos antecedente

			// # calculalift:

			resultado.add(temp);
		}// fin for reglas

		return resultado;
	}

	public List<Items2015> obtenerItems2015DeResultset(ResultSet resultSet) throws SQLException {
		List<Items2015> result = new ArrayList<Items2015>();

		// cols: 'row_names',
		// 'region','idregla','regla','support','confidence','lift'
		while (resultSet.next()) {
			// P.Prod_ID, P.DescGen, P.DescAdic, P.Marca, P.Proveedor,
			// P.CantEnvase, CAT.Cat_Desc AS categoria, SC.SubCat_Desc as
			// subcategoria , PS.Precio AS precio
			int id = resultSet.getInt("Venta_ID");
			String region = resultSet.getString("region");
			String item = resultSet.getString("descgen");

			Items2015 tmp = new Items2015(id, item, region);
			result.add(tmp);

		}
		return result;
	}

	public List<ReglasTodasDto> obtenerReglasTodasDeResultset(ResultSet resultSet) throws SQLException {
		List<ReglasTodasDto> result = new ArrayList<ReglasTodasDto>();

		// cols: 'row_names',
		// 'region','idregla','regla','support','confidence','lift'
		while (resultSet.next()) {
			// P.Prod_ID, P.DescGen, P.DescAdic, P.Marca, P.Proveedor,
			// P.CantEnvase, CAT.Cat_Desc AS categoria, SC.SubCat_Desc as
			// subcategoria , PS.Precio AS precio
			int id = resultSet.getInt("row_names");
			String region = resultSet.getString("region");
			int idregla = resultSet.getInt("idregla");
			String regla = resultSet.getString("regla");
			double support = resultSet.getDouble("support");
			double confidence = resultSet.getDouble("confidence");
			double lift = resultSet.getDouble("lift");

			ReglasTodasDto tmp = new ReglasTodasDto(id, region, idregla, regla, support, confidence, lift);
			result.add(tmp);

		}
		return result;
	}

	public ResultSet obtenerResultset(String query, Connection con) throws SQLException {
		PreparedStatement preparedStatementProductos = con.prepareStatement(query);
		ResultSet resultSet = preparedStatementProductos.executeQuery();
		return resultSet;
	}

	public Connection getConnection(String driverClassName, String conStr, String user, String pwd) {
		try {

			Class.forName(driverClassName).newInstance();

			Connection conn = DriverManager.getConnection(conStr, user, pwd);
			return conn;
		} catch (InstantiationException e) {

			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {

			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return null;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

	@Test
	public void juntaNombreArchivos() throws Exception {

		String path = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_1_data_mining/TP1/reentrega/resultados";

		// Files.lines(Paths.get(path+"/punto4_capital_conf_0.8_sup_0.02_result.txt")).count();
		// punto4_
		Files.walk(Paths.get(path)).forEach(filePath -> {
			if (Files.isRegularFile(filePath)) {
				File tmp = filePath.toFile();
				String substrName = tmp.getName();
				if (substrName.endsWith("result.txt") && substrName.startsWith("punto4_")) {
					try {
						// System.out.println(filePath.getFileName() +
						// "\t" + Files.lines(tmp.toPath()).count());

				;
				Stream<String> stream = Files.lines(filePath, Charset.forName("Cp1252"));
				long lineCount = stream.count();

				// punto4_capital_conf_0.8_sup_0.02_result.txt

				String region = substrName.substring(substrName.indexOf("punto4_") + "punto4_".length(), substrName.indexOf("_conf_"));
				if (region.equals("capital")) {
					region = "Capital Federal";
				} else if (region.equals("BA")) {
					region = "Buenos Aires";
				} else if (region.equals("interior")) {
					region = "Interior";
				} else
					region = "NA";

				String tmpConf = substrName.substring(substrName.indexOf("conf_") + "conf_".length(), substrName.indexOf("_sup"));
				String tmpSup = substrName.substring(substrName.indexOf("sup_") + "sup_".length(), substrName.indexOf("_result"));
				System.out.println(region + "\t" + tmpConf + "\t" + tmpSup + "\t" + (lineCount));
				stream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}		);
	}

	@Test
	public void correrOrientadoAVentasProdDescGen() throws SQLException {

		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://192.168.1.113:3306/dm-tp1", "dmkd", "dmkd");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			// System.out.println("You made it, take control your database now!");

			String detalladoAIds = "Select Venta_ID,Prod_ID FROM TP_Ventas_Prod order by Venta_ID";
			String acotadoADesc = "Select VP.Venta_ID, P.DescGen FROM TP_Ventas_Prod VP inner JOIN TP_Productos  P ON VP.Prod_ID = P.Prod_ID";

			// armar itemsets
			PreparedStatement preparedStatement = connection.prepareStatement(acotadoADesc);

			String colsDetalladoAIds = "Select Prod_ID FROM TP_Productos order by Prod_ID";
			String colsAcotadoADesc = "select P.DescGen  from TP_Productos P  Group by P.DescGen ";
			// armo las columnas
			PreparedStatement preparedStatementProductos = connection.prepareStatement(colsAcotadoADesc);

			// Result set get the result of the SQL query
			ResultSet resultSet = preparedStatement.executeQuery();

			ResultSet resultSet2 = preparedStatementProductos.executeQuery();

			// Map<String,Integer> mapaProdsDefaultVacio =
			// armaMapaDefaultVacio(resultSet2);
			Map<String, Integer> mapaProdsDefaultVacio = armaMapaDefaultVacioAcotado(resultSet2);

			// arma itemsets
			// Map<String, List<String>> mapa = armaItemsets(resultSet);

			Map<String, List<String>> mapa = armaItemsetsAcotado(resultSet);

			Map<String, Map<String, Integer>> mapaApriori = armaItemsetsParaApriori(mapa, mapaProdsDefaultVacio);

			// muestraItemsets(mapa);

			String tmpResult = muestraItemsetsAprioriLogical(mapaApriori, mapaProdsDefaultVacio);
			String tmpResultWeka = muestraItemsetsAprioriWekaLogical(mapaApriori, mapaProdsDefaultVacio);

			// System.out.println(tmpResult);

			// escribir a archivo
			final String ENCODE = "UTF-8";
			final String path = "C:/dev/20150615_dm/";

			Date defaultDate = new Date();

			// formato fecha anio, mes , dia, hora , minuto, segundo,
			// esto lo agregue para tener multiples corridas que no pisen los
			// archivos
			String defaulFormat = "yyyyMMdd_HHmmss_";

			// esto es un formateador de fechas, para que lo muestre de fecha a
			// formato de 'palabras''
			SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
			// esto da la fecha formateada
			String fechaAlMomento = lSDF.format(defaultDate);

			// armo donde va a guardar el archivo siendo:
			// carpeta pathDatos, y nombre de archivo compuesto por
			// fechaAlMomento mas exportResult
			String result = path + fechaAlMomento + "_dm_itemset_ventas_prod_desc.txt";
			String result2 = path + fechaAlMomento + "_dm_itemset_weka_ventas_prod_desc.csv";

			writeToFile(tmpResult, ENCODE, result);
			writeToFile(tmpResultWeka, ENCODE, result2);

			armarWekaFile(result2);

		} else {
			System.out.println("Failed to make connection!");
		}

	}

	@Test
	public void testConeccion() throws SQLException {

		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://192.168.1.113:3306/dm-tp1", "dmkd", "dmkd");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			// System.out.println("You made it, take control your database now!");

			String detalladoAIds = "Select Venta_ID,Prod_ID FROM TP_Ventas_Prod order by Venta_ID";
			String acotadoADesc = "Select VP.Venta_ID, P.DescGen FROM TP_Ventas_Prod VP inner JOIN TP_Productos  P ON VP.Prod_ID = P.Prod_ID";

			// si hago esto piso el mapa :S
			String acotadoACliente = "Select V.CLI_ID, P.DescGen " + "FROM TP_Ventas_Prod VP " + "inner JOIN TP_Productos  P ON VP.Prod_ID = P.Prod_ID " + "inner JOIN TP_Ventas V ON VP.Venta_ID = V.Venta_ID ";

			// armar itemsets
			PreparedStatement preparedStatement = connection.prepareStatement(acotadoACliente);

			String colsDetalladoAIds = "Select Prod_ID FROM TP_Productos order by Prod_ID";
			String colsAcotadoADesc = "select P.DescGen  from TP_Productos P  Group by P.DescGen ";
			// armo las columnas
			PreparedStatement preparedStatementProductos = connection.prepareStatement(colsAcotadoADesc);

			// Result set get the result of the SQL query
			ResultSet resultSet = preparedStatement.executeQuery();

			ResultSet resultSet2 = preparedStatementProductos.executeQuery();

			// Map<String,Integer> mapaProdsDefaultVacio =
			// armaMapaDefaultVacio(resultSet2);
			Map<String, Integer> mapaProdsDefaultVacio = armaMapaDefaultVacioAcotado(resultSet2);

			// arma itemsets
			// Map<String, List<String>> mapa = armaItemsets(resultSet);

			Map<String, List<String>> mapa = armaItemsetsAcotado(resultSet);

			Map<String, Map<String, Integer>> mapaApriori = armaItemsetsParaApriori(mapa, mapaProdsDefaultVacio);

			// muestraItemsets(mapa);

			String tmpResult = muestraItemsetsAprioriLogical(mapaApriori, mapaProdsDefaultVacio);
			String tmpResultWeka = muestraItemsetsAprioriWekaLogical(mapaApriori, mapaProdsDefaultVacio);

			// System.out.println(tmpResult);

			// escribir a archivo
			final String ENCODE = "UTF-8";
			final String path = "C:/dev/20150615_dm/";

			Date defaultDate = new Date();

			// formato fecha anio, mes , dia, hora , minuto, segundo,
			// esto lo agregue para tener multiples corridas que no pisen los
			// archivos
			String defaulFormat = "yyyyMMdd_HHmmss_";

			// esto es un formateador de fechas, para que lo muestre de fecha a
			// formato de 'palabras''
			SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
			// esto da la fecha formateada
			String fechaAlMomento = lSDF.format(defaultDate);

			// armo donde va a guardar el archivo siendo:
			// carpeta pathDatos, y nombre de archivo compuesto por
			// fechaAlMomento mas exportResult
			String result = path + fechaAlMomento + "_dm_itemset.txt";
			String result2 = path + fechaAlMomento + "_dm_itemset_weka.csv";

			writeToFile(tmpResult, ENCODE, result);
			writeToFile(tmpResultWeka, ENCODE, result2);

			armarWekaFile(result2);

		} else {
			System.out.println("Failed to make connection!");
		}

	}

	private void armarWekaFile(String result2) {

		// String nombreWeka = result2.replace(".txt", ".arff");
		//
		// CSVLoader loader = new CSVLoader();
		// loader.setSource(new File(result2));
		// Instances oldData = loader.getDataSet();
		//
		// // filtro de numericos
		// NumericToNominal numToNom = new NumericToNominal();
		// numToNom.setDebug(false);
		// numToNom.setInvertSelection(false);
		// int[] indeces = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,16};
		// numToNom.setAttributeIndicesArray(indeces);
		// numToNom.setInputFormat(oldData);
		//
		// ArffSaver saver = new ArffSaver();
		// saver.setInstances(data);
		//
		// // agregado por el error de
		// " Cannot create a new output file. Standard out is used."
		// File outFile = new File(pathDatos+dummy);
		// File outFile2 = new File(pathDatos+outReal);
		// // outFile2.set
		//
		// saver.setFile(outFile);
		// saver.setDestination(outFile2);
		// saver.writeBatch();

	}

	/**
	 * @param stringToWrite
	 * @param encoding
	 * @param pathYNombre
	 * @param writer
	 */
	private void writeToFile(String stringToWrite, final String encoding, String pathYNombre) {

		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathYNombre), encoding));
			writer.write(stringToWrite);
			System.out.println("output en: " + pathYNombre);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Assert.fail();
				}
			}
		}
	}

	private Map<String, Integer> armaMapaDefaultVacio(ResultSet resultSetTablaProds) throws SQLException {
		Map<String, Integer> mapa = new HashMap<String, Integer>();
		while (resultSetTablaProds.next()) {
			mapa.put(resultSetTablaProds.getString("Prod_ID"), 0);
		}
		return mapa;
	}

	private Map<String, Integer> armaMapaDefaultVacioAcotado(ResultSet resultSetTablaProds) throws SQLException {
		Map<String, Integer> mapa = new HashMap<String, Integer>();
		while (resultSetTablaProds.next()) {
			mapa.put(resultSetTablaProds.getString("DescGen"), 0);
		}
		return mapa;
	}

	private Map<String, Map<String, Integer>> armaItemsetsParaApriori(Map<String, List<String>> mapaItemsets, Map<String, Integer> defaultVacio) throws SQLException {

		// dado itemset, cuantos prod tiene
		Map<String, Map<String, Integer>> mapa = new HashMap<String, Map<String, Integer>>();

		// recorro el mapa de itemsets
		for (String key : mapaItemsets.keySet()) {

			// tengo que tener un mapa de itemset / si lo tiene o no
			Map<String, Integer> currentMapa = mapa.get(key);
			// si mapa no existe creo uno basandome en el default
			if (currentMapa == null) {
				currentMapa = new HashMap<String, Integer>(defaultVacio);
			}

			// para el mapa elegido , le pongo que tiene cada item del itemset
			// for (String itemSet : mapaItemsets.keySet()) {

			List<String> currentItemSet = mapaItemsets.get(key);
			for (String item : currentItemSet) {
				Integer current = currentMapa.get(item);
				current = current + 1;
				currentMapa.put(item, current);
			}
			// }
			mapa.put(key, currentMapa);
		}
		return mapa;
	}

	private String muestraItemsetsAprioriWeka(Map<String, Map<String, Integer>> mapa, Map<String, Integer> header) {
		StringBuilder build = new StringBuilder();
		final String TAB = ",";
		final String ENTER = "\n";

		// armado de header
		build.append("'").append("ID").append("'").append(TAB);
		for (String key : header.keySet()) {
			build.append("'").append(key).append("'").append(TAB);
		}
		// borro sobrante
		build.deleteCharAt(build.length() - 1);
		// separa header
		build.append(ENTER);

		// recorro los itemset
		for (String key : mapa.keySet()) {
			build.append("'").append(key).append("'").append(TAB);

			Map<String, Integer> currentSet = mapa.get(key);
			for (String itemValue : currentSet.keySet()) {
				// valor de si o no itemset
				build.append("'").append(currentSet.get(itemValue)).append("'").append(TAB);
			}
			// saca el tab sobrante
			build.deleteCharAt(build.length() - 1);
			// pasa siguiente linea
			build.append(ENTER);
		}
		// saca el enter sobrante
		build.deleteCharAt(build.length() - 1);

		return build.toString();
	}

	private String muestraItemsetsAprioriWekaConSignos(Map<String, Map<String, Integer>> mapa, Map<String, Integer> header) {
		StringBuilder build = new StringBuilder();
		final String TAB = ",";
		final String ENTER = "\n";

		// armado de header
		build.append("'").append("ID").append("'").append(TAB);
		for (String key : header.keySet()) {
			build.append("'").append(key).append("'").append(TAB);
		}
		// borro sobrante
		build.deleteCharAt(build.length() - 1);
		// separa header
		build.append(ENTER);

		// recorro los itemset
		for (String key : mapa.keySet()) {
			build.append("'").append(key).append("'").append(TAB);

			Map<String, Integer> currentSet = mapa.get(key);
			for (String itemValue : currentSet.keySet()) {
				// valor de si o no itemset
				if (currentSet.get(itemValue) == 0) {
					build.append("'").append("?").append("'").append(TAB);
				} else {
					build.append("'").append("1").append("'").append(TAB);
				}
			}
			// saca el tab sobrante
			build.deleteCharAt(build.length() - 1);
			// pasa siguiente linea
			build.append(ENTER);
		}
		// saca el enter sobrante
		build.deleteCharAt(build.length() - 1);

		return build.toString();
	}

	private String muestraItemsetsAprioriWekaLogical(Map<String, Map<String, Integer>> mapa, Map<String, Integer> header) {
		StringBuilder build = new StringBuilder();
		final String TAB = ",";
		final String ENTER = "\n";

		// armado de header
		build.append("'").append("ID").append("'").append(TAB);
		for (String key : header.keySet()) {
			build.append("'").append(key).append("'").append(TAB);
		}
		// borro sobrante
		build.deleteCharAt(build.length() - 1);
		// separa header
		build.append(ENTER);

		// recorro los itemset
		for (String key : mapa.keySet()) {
			build.append("'").append(key).append("'").append(TAB);

			Map<String, Integer> currentSet = mapa.get(key);
			for (String itemValue : currentSet.keySet()) {
				// valor de si o no itemset
				if (currentSet.get(itemValue) == 0) {
					build.append("'").append("FALSE").append("'").append(TAB);
				} else {
					build.append("'").append("TRUE").append("'").append(TAB);
				}
			}
			// saca el tab sobrante
			build.deleteCharAt(build.length() - 1);
			// pasa siguiente linea
			build.append(ENTER);
		}
		// saca el enter sobrante
		build.deleteCharAt(build.length() - 1);

		return build.toString();
	}

	private String muestraItemsetsAprioriLogical(Map<String, Map<String, Integer>> mapa, Map<String, Integer> header) {
		StringBuilder build = new StringBuilder();
		final String TAB = "\t";
		final String ENTER = "\n";

		// armado de header
		build.append("ID").append(TAB);
		for (String key : header.keySet()) {
			build.append(key).append(TAB);
		}
		// borro sobrante
		build.deleteCharAt(build.length() - 1);
		// separa header
		build.append(ENTER);

		Random random = new Random();
		// recorro los itemset
		for (String key : mapa.keySet()) {
			// build.append(key).append(TAB);
			build.append(random.nextInt(10)).append(TAB);

			Map<String, Integer> currentSet = mapa.get(key);
			for (String itemValue : currentSet.keySet()) {
				// valor de si o no itemset
				if (currentSet.get(itemValue) == 0) {
					build.append("FALSE").append(TAB);
				} else {
					build.append("TRUE").append(TAB);
				}

			}
			// saca el tab sobrante
			build.deleteCharAt(build.length() - 1);
			// pasa siguiente linea
			build.append(ENTER);
		}
		// saca el enter sobrante
		build.deleteCharAt(build.length() - 1);

		return build.toString();
	}

	private String muestraItemsetsApriori(Map<String, Map<String, Integer>> mapa, Map<String, Integer> header) {
		StringBuilder build = new StringBuilder();
		final String TAB = "\t";
		final String ENTER = "\n";

		// armado de header
		build.append("ID").append(TAB);
		for (String key : header.keySet()) {
			build.append(key).append(TAB);
		}
		// borro sobrante
		build.deleteCharAt(build.length() - 1);
		// separa header
		build.append(ENTER);

		// recorro los itemset
		for (String key : mapa.keySet()) {
			build.append(key).append(TAB);

			Map<String, Integer> currentSet = mapa.get(key);
			for (String itemValue : currentSet.keySet()) {
				// valor de si o no itemset
				build.append(currentSet.get(itemValue)).append(TAB);
			}
			// saca el tab sobrante
			build.deleteCharAt(build.length() - 1);
			// pasa siguiente linea
			build.append(ENTER);
		}
		// saca el enter sobrante
		build.deleteCharAt(build.length() - 1);

		return build.toString();
	}

	private void muestraItemsets(Map<String, List<String>> mapa) {

		StringBuilder build = new StringBuilder();
		final String TAB = "\t";
		final String ENTER = "\n";
		for (String key : mapa.keySet()) {
			build.append(key).append(TAB);

			List<String> currentItemSet = mapa.get(key);

			for (String item : currentItemSet) {
				build.append(item).append(TAB);
			}
			build.deleteCharAt(build.length() - 1);
			build.append(ENTER);

		}
		build.deleteCharAt(build.length() - 1);
		System.out.println(build.toString());
	}

	private Map<String, List<String>> armaItemsetsAcotadoClientes(ResultSet resultSet) throws SQLException {

		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		while (resultSet.next()) {

			// String custKey = resultSet.getString("Venta_ID");
			String custKey = resultSet.getString("CLI_ID");

			// obtengo el itemset by key
			List<String> currentList = mapa.get(custKey);
			// si es null inicio
			if (currentList == null) {
				currentList = new ArrayList<String>();
			}
			// agrego el item al itemset
			currentList.add(resultSet.getString("DescGen"));

			// guardo el itemset
			mapa.put(custKey, currentList);

		}
		return mapa;
	}

	private Map<String, List<String>> armaItemsetsAcotado(ResultSet resultSet) throws SQLException {

		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		while (resultSet.next()) {

			String custKey = resultSet.getString("Venta_ID");

			// obtengo el itemset by key
			List<String> currentList = mapa.get(custKey);
			// si es null inicio
			if (currentList == null) {
				currentList = new ArrayList<String>();
			}
			// agrego el item al itemset
			currentList.add(resultSet.getString("DescGen"));

			// guardo el itemset
			mapa.put(custKey, currentList);

		}
		return mapa;
	}

	private Map<String, List<String>> armaItemsets(ResultSet resultSet) throws SQLException {

		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		while (resultSet.next()) {

			// String custKey = resultSet.getString("Venta_ID");
			String custKey = resultSet.getString("CLI_ID");

			// obtengo el itemset by key
			List<String> currentList = mapa.get(custKey);
			// si es null inicio
			if (currentList == null) {
				currentList = new ArrayList<String>();
			}
			// agrego el item al itemset
			currentList.add(resultSet.getString("Prod_ID"));

			// guardo el itemset
			mapa.put(custKey, currentList);

		}
		return mapa;
	}

	@Test
	public void prueba() {
		Map<String, String> mapaDef = new HashMap<String, String>();

		mapaDef.put("1", "pow");
		mapaDef.put("2", "powPow");
		mapaDef.put("3", "powTatow");

		System.out.println(mapaDef.keySet().toString());
		System.out.println(mapaDef.values().toString());

		Map<String, String> mapa = new HashMap<String, String>(mapaDef);
		mapa.put("1", "chorizow");
		System.out.println();
		System.out.println(mapa.keySet().toString());
		System.out.println(mapa.values().toString());
		System.out.println();
		System.out.println(mapaDef.keySet().toString());
		System.out.println(mapaDef.values().toString());

	}

	@Test
	public void recorredorArchivo() {

	}

}
