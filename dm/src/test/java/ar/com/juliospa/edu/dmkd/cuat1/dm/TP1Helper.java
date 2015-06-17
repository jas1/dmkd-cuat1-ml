package ar.com.juliospa.edu.dmkd.cuat1.dm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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

import org.junit.Assert;
import org.junit.Test;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.filters.unsupervised.attribute.NumericToNominal;

public class TP1Helper {

	// con <- dbConnect(RMySQL::MySQL(), dbname = "dm-tp1", password
	// ='dmkd',user = 'dmkd',host='192.168.1.113')

	@Test
	public void testConeccion() throws SQLException {

		System.out
				.println("-------- MySQL JDBC Connection Testing ------------");

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
			connection = DriverManager.getConnection(
					"jdbc:mysql://192.168.1.113:3306/dm-tp1", "dmkd", "dmkd");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			// System.out.println("You made it, take control your database now!");

			String detalladoAIds = "Select Venta_ID,Prod_ID FROM TP_Ventas_Prod order by Venta_ID";
			String acotadoADesc = "Select VP.Venta_ID, P.DescGen FROM TP_Ventas_Prod VP inner JOIN TP_Productos  P ON VP.Prod_ID = P.Prod_ID";
			
			
			//			si hago esto piso el mapa  :S 
			String acotadoACliente= "Select V.CLI_ID, P.DescGen "+
			"FROM TP_Ventas_Prod VP "+
			"inner JOIN TP_Productos  P ON VP.Prod_ID = P.Prod_ID "+
			"inner JOIN TP_Ventas V ON VP.Venta_ID = V.Venta_ID ";
			
			// armar itemsets
			PreparedStatement preparedStatement = connection.prepareStatement(acotadoADesc);
			
			String colsDetalladoAIds = "Select Prod_ID FROM TP_Productos order by Prod_ID";
			String colsAcotadoADesc = "select P.DescGen  from TP_Productos P  Group by P.DescGen ";
			// armo las columnas
			PreparedStatement preparedStatementProductos = connection.prepareStatement(colsAcotadoADesc);

			// Result set get the result of the SQL query
			ResultSet resultSet = preparedStatement.executeQuery();
			
			ResultSet resultSet2 = preparedStatementProductos.executeQuery();
			
//			Map<String,Integer>  mapaProdsDefaultVacio = armaMapaDefaultVacio(resultSet2);
			Map<String,Integer>  mapaProdsDefaultVacio = armaMapaDefaultVacioAcotado(resultSet2);
			
//			 arma itemsets
//			Map<String, List<String>> mapa = armaItemsets(resultSet);
			
			Map<String, List<String>> mapa = armaItemsetsAcotado(resultSet);

			
			Map<String,  Map<String,Integer>> mapaApriori = armaItemsetsParaApriori( mapa,mapaProdsDefaultVacio);
			
//			muestraItemsets(mapa);
			
			String tmpResult = muestraItemsetsAprioriLogical(mapaApriori,mapaProdsDefaultVacio);
			String tmpResultWeka = muestraItemsetsAprioriWekaLogical(mapaApriori,mapaProdsDefaultVacio);
			
			
//			System.out.println(tmpResult);
			
			
			// escribir a archivo
			final String ENCODE = "UTF-8";
			final String path = "C:/dev/20150615_dm/";
			
            Date defaultDate = new Date();
            
            // formato fecha anio, mes , dia, hora , minuto, segundo,
            // esto lo agregue para tener multiples corridas que no pisen los archivos
            String defaulFormat = "yyyyMMdd_HHmmss_";

            // esto es un formateador de fechas, para que lo muestre de fecha a formato de 'palabras''
            SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
            // esto da la fecha formateada
            String fechaAlMomento = lSDF.format(defaultDate);

            // armo donde va a guardar el archivo siendo:
            // carpeta pathDatos, y nombre de archivo compuesto por fechaAlMomento mas exportResult
            String result = path + fechaAlMomento    + "_dm_itemset.txt";
            String result2 = path + fechaAlMomento    + "_dm_itemset_weka.csv";
            
			writeToFile(tmpResult, ENCODE, result);
			writeToFile(tmpResultWeka, ENCODE, result2);
			
			armarWekaFile(result2);
			
		} else {
			System.out.println("Failed to make connection!");
		}

	}

	private void armarWekaFile(String result2) {
		
//		String nombreWeka = result2.replace(".txt", ".arff");
//		
//		CSVLoader loader = new CSVLoader();
//		loader.setSource(new File(result2));
//		Instances oldData = loader.getDataSet();
//
//		// filtro de numericos
//		NumericToNominal numToNom = new NumericToNominal(); 
//		numToNom.setDebug(false);
//		numToNom.setInvertSelection(false);
//		int[] indeces =  {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,16};
//		numToNom.setAttributeIndicesArray(indeces);
//		numToNom.setInputFormat(oldData);
//		
//		ArffSaver saver = new ArffSaver();
//		saver.setInstances(data);
//		
//		// agregado por el error de " Cannot create a new output file. Standard out is used."
//		File outFile = new File(pathDatos+dummy);
//		File outFile2 = new File(pathDatos+outReal);
////	outFile2.set
//		
//		saver.setFile(outFile);
//		saver.setDestination(outFile2);
//		saver.writeBatch();
		
	}

	/**
	 * @param stringToWrite
	 * @param encoding
	 * @param pathYNombre
	 * @param writer
	 */
	private void writeToFile(String stringToWrite, final String encoding,String pathYNombre) {
		
        Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathYNombre), encoding));
			writer.write(stringToWrite);
			System.out.println("output en: "+pathYNombre);
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
	
	private Map<String,Integer> armaMapaDefaultVacio(ResultSet resultSetTablaProds) throws SQLException{
		Map<String,Integer>  mapa = new HashMap<String, Integer>();
		while (resultSetTablaProds.next()) {
			mapa.put(resultSetTablaProds.getString("Prod_ID"), 0);
		}
		return mapa;
	}
	private Map<String,Integer> armaMapaDefaultVacioAcotado(ResultSet resultSetTablaProds) throws SQLException{
		Map<String,Integer>  mapa = new HashMap<String, Integer>();
		while (resultSetTablaProds.next()) {
			mapa.put(resultSetTablaProds.getString("DescGen"), 0);
		}
		return mapa;
	}
	
	private Map<String, Map<String,Integer>> armaItemsetsParaApriori(Map<String, List<String>> mapaItemsets,Map<String,Integer> defaultVacio) throws SQLException {
		
//		dado itemset, cuantos prod tiene 
		Map<String, Map<String,Integer>> mapa = new HashMap<String, Map<String,Integer>>();

		// recorro el mapa de itemsets
		for (String key : mapaItemsets.keySet()) {

			// tengo que tener un mapa de itemset / si lo tiene o no
			Map<String,Integer> currentMapa = mapa.get(key);
			// si mapa no existe creo uno basandome en el default
			if (currentMapa == null) {
				currentMapa = new HashMap<String, Integer>(defaultVacio);
			}
			
			
			
			// para el mapa elegido , le pongo que tiene cada item del itemset
//			for (String itemSet : mapaItemsets.keySet()) {
				
				List<String> currentItemSet = mapaItemsets.get(key);
				for (String item : currentItemSet) {
					Integer current = currentMapa.get(item);
					current = current +1;
					currentMapa.put(item, current);					
				}
//			}
			mapa.put(key, currentMapa);
		}		
		return mapa;
	}

	private String muestraItemsetsAprioriWeka( Map<String, Map<String,Integer>> mapa,Map<String,Integer> header) {
		StringBuilder build = new StringBuilder();
		final String  TAB = ",";
		final String  ENTER = "\n";
		
//		armado de header
		build.append("'").append("ID").append("'").append(TAB);
		for (String key : header.keySet()) {
			build.append("'").append(key).append("'").append(TAB);
		}
		// borro sobrante
		build.deleteCharAt(build.length()-1);
		//separa header
		build.append(ENTER);
		
		// recorro los itemset
		for (String key : mapa.keySet()) {
			build.append("'").append(key).append("'").append(TAB);
			
			Map<String,Integer> currentSet = mapa.get(key);
			for (String itemValue : currentSet.keySet()) {
				// valor de si o no itemset
				build.append("'").append(currentSet.get(itemValue)).append("'").append(TAB);	
			}
			// saca el tab sobrante
			build.deleteCharAt(build.length()-1);
			// pasa  siguiente linea
			build.append(ENTER);
		}
		// saca el enter sobrante
		build.deleteCharAt(build.length()-1);
		
		return build.toString();
	}
	
	private String muestraItemsetsAprioriWekaConSignos( Map<String, Map<String,Integer>> mapa,Map<String,Integer> header) {
		StringBuilder build = new StringBuilder();
		final String  TAB = ",";
		final String  ENTER = "\n";
		
//		armado de header
		build.append("'").append("ID").append("'").append(TAB);
		for (String key : header.keySet()) {
			build.append("'").append(key).append("'").append(TAB);
		}
		// borro sobrante
		build.deleteCharAt(build.length()-1);
		//separa header
		build.append(ENTER);
		
		// recorro los itemset
		for (String key : mapa.keySet()) {
			build.append("'").append(key).append("'").append(TAB);
			
			Map<String,Integer> currentSet = mapa.get(key);
			for (String itemValue : currentSet.keySet()) {
				// valor de si o no itemset				
				if (currentSet.get(itemValue) == 0) {
					build.append("'").append("?").append("'").append(TAB);	
				}else{
					build.append("'").append("1").append("'").append(TAB);	
				}
			}
			// saca el tab sobrante
			build.deleteCharAt(build.length()-1);
			// pasa  siguiente linea
			build.append(ENTER);
		}
		// saca el enter sobrante
		build.deleteCharAt(build.length()-1);
		
		return build.toString();
	}
	
	private String muestraItemsetsAprioriWekaLogical( Map<String, Map<String,Integer>> mapa,Map<String,Integer> header) {
		StringBuilder build = new StringBuilder();
		final String  TAB = ",";
		final String  ENTER = "\n";
		
//		armado de header
		build.append("'").append("ID").append("'").append(TAB);
		for (String key : header.keySet()) {
			build.append("'").append(key).append("'").append(TAB);
		}
		// borro sobrante
		build.deleteCharAt(build.length()-1);
		//separa header
		build.append(ENTER);
		
		// recorro los itemset
		for (String key : mapa.keySet()) {
			build.append("'").append(key).append("'").append(TAB);
			
			Map<String,Integer> currentSet = mapa.get(key);
			for (String itemValue : currentSet.keySet()) {
				// valor de si o no itemset				
				if (currentSet.get(itemValue) == 0) {
					build.append("'").append("FALSE").append("'").append(TAB);	
				}else{
					build.append("'").append("TRUE").append("'").append(TAB);	
				}
			}
			// saca el tab sobrante
			build.deleteCharAt(build.length()-1);
			// pasa  siguiente linea
			build.append(ENTER);
		}
		// saca el enter sobrante
		build.deleteCharAt(build.length()-1);
		
		return build.toString();
	}
	
	
	private String muestraItemsetsAprioriLogical( Map<String, Map<String,Integer>> mapa,Map<String,Integer> header) {
		StringBuilder build = new StringBuilder();
		final String  TAB = "\t";
		final String  ENTER = "\n";
		
//		armado de header
		build.append("ID").append(TAB);
		for (String key : header.keySet()) {
			build.append(key).append(TAB);
		}
		// borro sobrante
		build.deleteCharAt(build.length()-1);
		//separa header
		build.append(ENTER);
		
		// recorro los itemset
		for (String key : mapa.keySet()) {
			build.append(key).append(TAB);
			
			Map<String,Integer> currentSet = mapa.get(key);
			for (String itemValue : currentSet.keySet()) {
				// valor de si o no itemset
				if (currentSet.get(itemValue) == 0) {
					build.append("FALSE").append(TAB);	
				}else{
					build.append("TRUE").append(TAB);
				}
					
			}
			// saca el tab sobrante
			build.deleteCharAt(build.length()-1);
			// pasa  siguiente linea
			build.append(ENTER);
		}
		// saca el enter sobrante
		build.deleteCharAt(build.length()-1);
		
		return build.toString();
	}
	
	private String muestraItemsetsApriori( Map<String, Map<String,Integer>> mapa,Map<String,Integer> header) {
		StringBuilder build = new StringBuilder();
		final String  TAB = "\t";
		final String  ENTER = "\n";
		
//		armado de header
		build.append("ID").append(TAB);
		for (String key : header.keySet()) {
			build.append(key).append(TAB);
		}
		// borro sobrante
		build.deleteCharAt(build.length()-1);
		//separa header
		build.append(ENTER);
		
		// recorro los itemset
		for (String key : mapa.keySet()) {
			build.append(key).append(TAB);
			
			Map<String,Integer> currentSet = mapa.get(key);
			for (String itemValue : currentSet.keySet()) {
				// valor de si o no itemset
				build.append(currentSet.get(itemValue)).append(TAB);	
			}
			// saca el tab sobrante
			build.deleteCharAt(build.length()-1);
			// pasa  siguiente linea
			build.append(ENTER);
		}
		// saca el enter sobrante
		build.deleteCharAt(build.length()-1);
		
		return build.toString();
	}
	
	private void muestraItemsets(Map<String, List<String>> mapa) {
		
		StringBuilder build = new StringBuilder();
		final String  TAB = "\t";
		final String  ENTER = "\n";
		for (String key : mapa.keySet()) {
			build.append(key).append(TAB);
			
			List<String> currentItemSet = mapa.get(key);
			
			for (String item : currentItemSet) {
				build.append(item).append(TAB);
			}
			build.deleteCharAt(build.length()-1);	
			build.append(ENTER);
			
		}
		build.deleteCharAt(build.length()-1);
		System.out.println( build.toString());
	}

	
	private Map<String, List<String>> armaItemsetsAcotado(ResultSet resultSet) throws SQLException {
		
		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		while (resultSet.next()) {
			
//			String custKey = resultSet.getString("Venta_ID");
			String custKey = resultSet.getString("CLI_ID");
			
			// obtengo el itemset by key
			List<String> currentList = mapa.get(custKey);
			// si es null inicio
			if (currentList == null ) {
				currentList = new ArrayList<String>();
			}
//			agrego el item al itemset
			currentList.add(resultSet.getString("DescGen"));
			
			// guardo el itemset
			mapa.put(custKey, currentList);

		}
		return mapa;
	}
	
	private Map<String, List<String>> armaItemsets(ResultSet resultSet) throws SQLException {
		
		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		while (resultSet.next()) {
			
//			String custKey = resultSet.getString("Venta_ID");
			String custKey = resultSet.getString("CLI_ID");
			
			// obtengo el itemset by key
			List<String> currentList = mapa.get(custKey);
			// si es null inicio
			if (currentList == null ) {
				currentList = new ArrayList<String>();
			}
//			agrego el item al itemset
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

