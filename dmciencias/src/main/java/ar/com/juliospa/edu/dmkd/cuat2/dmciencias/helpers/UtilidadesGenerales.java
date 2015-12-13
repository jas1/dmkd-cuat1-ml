package ar.com.juliospa.edu.dmkd.cuat2.dmciencias.helpers;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;

public abstract class UtilidadesGenerales {

	// \n
	public static final String lineSeparator = System.getProperty("line.separator");
	// ;
	public static final String pathSeparator = System.getProperty("path.separator");
	// /
	public static final String fileSeparator = System.getProperty("file.separator");
	/**
	 * este es por si no se quiere especificar fecha y formato
	 * pasa null y null al otro
	 * @return
	 */
	public static String getTimeStamp() {
		return getTimeStamp(null,null);
	}

	public static String getTimeStamp(Date date, String format) {
		Date defaultDate = (date != null) ? date : new Date();
		String defaulFormat = (format != null) ? format : "yyyyMMdd_HHmmss";
	
		SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
		return lSDF.format(defaultDate);
	}
	
	
	public static void writeOutput(String buildForFile, String nombreArchivo,String path) {
		final String ENCODE = "UTF-8";
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
		// carpeta pathDatos, y nombre de archivo compuesto por fechaAlMomento
		// mas exportResult

		String result = path + fechaAlMomento + nombreArchivo;

		writeToFile(buildForFile, ENCODE, result);
	}
	
	public static void writeOutput(StringBuilder buildForFile, String nombreArchivo,
			String path) {
		writeOutput(buildForFile.toString(),nombreArchivo,path);
	}

	public static void writeToFile(String stringToWrite, final String encoding,
			String pathYNombre) {

		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(pathYNombre), encoding));
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

}
