package ml.tp1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class WekaJ48ScriptsTP1 {

	@Test
	public void correrJ48Prueba() {
		final String encode =   "utf-8";
		// por los acentos
		System.setProperty("file.encoding",encode);
		
		String pathDatos = "C:/julio/dev/R/";
		String datosName = "20150526_0501_2013_y_2014-refined.arff";
		
		String exportResult = "export_corrida.txt";
		
		try {
			ArffLoader loader = new ArffLoader();
			loader.setSource(new File(pathDatos+datosName));
			Instances data = loader.getDataSet();
			
			
			//
			// seteo el indice de la columna que va a ser clasificador
			// data.setClassIndex(data.numAttributes() - 1);
			
			// codigo de cuentas
			data.setClass(data.attribute(9));
			
			
			// instanciacion de j48
			
			
			StringBuilder builder = new StringBuilder();
			
			
			
//		Variar desde 0 a 0.5 en intervalos de 0.025
			BigDecimal intervalo = new BigDecimal(0.025);
			intervalo.setScale(3,RoundingMode.HALF_EVEN);
			
			DecimalFormat  formater = new DecimalFormat ("#.###");
			
			while( intervalo.doubleValue() <= 0.5 ){
				J48 j48 = getNewj48();
				builder.append("j48 para confianza: ").append(formater.format(intervalo)).append("\n");
				float tmpFloat = intervalo.floatValue();
				j48.setConfidenceFactor(tmpFloat);
				j48.buildClassifier(data);
				intervalo = intervalo.add(new BigDecimal(0.025));
				
				//j48.buildClassifier();
				// ver: https://weka.wikispaces.com/Visualizing+a+Tree
				// ver: http://www.programcreek.com/java-api-examples/index.php?api=weka.classifiers.trees.J48
				// ver: http://www.programcreek.com/2013/01/a-simple-machine-learning-example-in-java/
				builder.append(j48).append("\n");
				
			}
			Writer writer = null;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(pathDatos + getTimeStamp(null,null)+ exportResult), encode));
				writer.write(builder.toString());
			} catch (Exception e) {
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
			
			//porque ejecutamos todo sin reventar :P
			Assert.assertTrue(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("revento");
		}
		
	
	}

	private J48 getNewj48() {
		J48 j48 = new J48();

		// config base como la de weka
		j48.setBinarySplits(false);
		j48.setDebug(false);
		j48.setMinNumObj(2);
		j48.setNumFolds(3);
		j48.setReducedErrorPruning(false);
		j48.setSaveInstanceData(false);
		j48.setSeed(1);
		j48.setSubtreeRaising(true);
		j48.setUnpruned(false);
		j48.setUseLaplace(false);

		return j48;
	}

	
	/**
	 * da un timestamp para fecha / hora
	 * @param date : default now
	 * @param format: default yyyyMMdd - HH:mm:ss
	 * @return
	 */
	public static String getTimeStamp( Date date, String format){
		Date defaultDate =  (date!= null )?date: new Date();
		String defaulFormat=  (format!= null )?format: "yyyyMMdd_HHmmss_";

		SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
		return lSDF.format(defaultDate);
	}
	
}
