package ml.tp1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class BalancesTest {

	private static final String TAB= "\t";
	private static final String ENTER = "\n";
	private static final String ENCODE = "UTF-8";
	
	@Test
	public void testBalances() {
		final String TAB = "\t";
		final String ENTER = "\n";
		final String ENCODE = "UTF-8";
		final String path = "C:/dev/R/workingdirs/dmkd_ml_ssn_balances/";
		Table table;
		Writer writer = null;
		try {

			table = DatabaseBuilder.open(new File(path + "2015-1.mdb"))
					.getTable("Balance");

			StringBuilder lBuild = new StringBuilder();
			for (Column col : table.getColumns()) {

				lBuild.append(col.getName()).append(TAB);
			}
			lBuild.deleteCharAt(lBuild.length() - 1);
			lBuild.append(ENTER);

			for (Row row : table) {
				for (Column col : table.getColumns()) {

					if (col.getType() == DataType.DOUBLE) {

						BigDecimal big = new BigDecimal((Double) row.get(col
								.getName()));

						lBuild.append(big.toPlainString()).append(TAB);
					} else {
						lBuild.append(row.get(col.getName())).append(TAB);
					}

				}
				lBuild.deleteCharAt(lBuild.length() - 1);
				lBuild.append(ENTER);
			}
			lBuild.deleteCharAt(lBuild.length() - 1);

			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(path + "2015-1.txt"), ENCODE));
			writer.write(lBuild.toString());

		} catch (IOException e) {
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

	@Test
	public void testBalancesArff() {
		final String path = "C:/dev/R/workingdirs/dmkd_ml_ssn_balances/";
		final String inputFile = "2015-1_enclosed-test.txt";
		final String outputFile = "dummy-test.arff";
		final String outputFile2 = "2015-1_enclosed-test2.arff";
		
		final String inputFile2 = "2015-1_enclosed-train.txt";
		final String outputFile22 = "dummy-train.arff";
		final String outputFile222 = "2015-1_enclosed-train.arff";

		armarArff(path, inputFile, outputFile, outputFile2);
		armarArff(path, inputFile2, outputFile22, outputFile222);

	}

	private void armarArff(final String path, final String inputFile,
			final String outputFile, final String outputFile2) {
		System.setProperty("file.encoding", "utf-8");
		
		// load CSV
		CSVLoader loader = new CSVLoader();

		try {
			loader.setSource(new File(path+inputFile));
			Instances data = loader.getDataSet();

			// save ARFF
			ArffSaver saver = new ArffSaver();
			saver.setInstances(data);
			
			// agregado por el error de " Cannot create a new output file. Standard out is used."
			File outFile = new File(path+outputFile);
			File outFile2 = new File(path+outputFile2);
//			outFile2.set
			
			saver.setFile(outFile);
			saver.setDestination(outFile2);
			saver.writeBatch();
			Assert.assertTrue(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testBalancesEncolsing() {

		final String path = "C:/dev/R/workingdirs/dmkd_ml_ssn_balances/";
		final String BASE = "2015-1.mdb";
		final String exportTotal = "2015-1_enclosed-total.txt";
		final String exportTrain = "2015-1_enclosed-train.txt";
		final String exportTest = "2015-1_enclosed-test.txt";
		final String TABLA = "Balance";
		Table table;
		Writer writer = null;
		try {

			table = DatabaseBuilder.open(new File(path + BASE)).getTable(TABLA);

			StringBuilder lBuildTotal = new StringBuilder();
			StringBuilder lBuildTrain = new StringBuilder();
			StringBuilder lBuildTest = new StringBuilder();
			
			
			buildFile(path, exportTotal, table, lBuildTotal,null,null);
			// genera solo para la 0213
			buildFile(path, exportTrain, table, lBuildTrain,false,"0213");
			// genera solo para la todas menos la 0213
			buildFile(path, exportTest, table, lBuildTest,true,"0213");

		} catch (IOException e) {
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

	private void buildFile(String path, String export,
			Table table, StringBuilder builder,Boolean not, String ciaId) {
		Writer writer=null;
		try {
			for (Column col : table.getColumns()) {

				builder.append(col.getName()).append(TAB);
			}
			builder.deleteCharAt(builder.length() - 1);
			builder.append(ENTER);

			for (Row row : table) {
				
				// para casos de prueba / test
				if (not != null) {
					armaCia(table, builder,row,ciaId,not.booleanValue());
				}else{
					// para el generico
					armaTodos(table, builder, row);	
				}
				builder.deleteCharAt(builder.length() - 1);
				builder.append(ENTER);
			}
			builder.deleteCharAt(builder.length() - 1);

			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(path + export), ENCODE));
			writer.write(builder.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (IOException e) {
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
	

	private void armaCia(Table table, StringBuilder builder, Row row, String ciaId, boolean not) {
		
		String currentCia = (String)row.get("cod_cia") ;
		
		if (not) {
			if(!currentCia.equals(ciaId)){
				armaTodos(table, builder, row);
			}
		}else{
			if(currentCia.equals(ciaId)){
				armaTodos(table, builder, row);
			}			
		}

		

	}
	

	private void armaTodos(Table table, StringBuilder builder, Row row) {
		for (Column col : table.getColumns()) {
			// si es el campo numerico
			if (col.getType() == DataType.DOUBLE) {

				BigDecimal big = new BigDecimal((Double) row.get(col
						.getName()));

				builder.append(big.toPlainString()).append(TAB);
			} else {
				// si es id padre, que esta cargado como texto , que no
				// deberia.
				if (col.getName().equals("id_padre")) {
					String lTmp = (String) row.get(col.getName());
					if (lTmp == null) {
						lTmp = "0";
					}
					builder.append(lTmp).append(TAB);
				} else {
					// si es numerico.
					if (col.getType() == DataType.LONG
							|| col.getType() == DataType.INT) {
						builder.append(row.get(col.getName())).append(
								TAB);
					} else {
						// resto del mundo
						builder.append("'")
								.append(row.get(col.getName()))
								.append("'").append(TAB);
					}
				}
			}
		}
	}
}
