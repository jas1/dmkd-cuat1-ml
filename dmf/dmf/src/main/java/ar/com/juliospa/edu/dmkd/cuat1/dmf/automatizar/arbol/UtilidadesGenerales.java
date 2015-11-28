package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;

import com.ibm.statistics.plugin.StatsException;
import com.ibm.statistics.plugin.StatsUtil;

public abstract class UtilidadesGenerales {

	public static final String separator = System.getProperty("line.separator");
	
	/**
	 * @param commands
	 */
	public static void ejecutarComandosSpss(String[] commands) {
		try {
			StatsUtil.start();
			StatsUtil.submit(commands);
			StatsUtil.stop();
		} catch (StatsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Comando ejecutado");
			for (String string : commands) {
				System.out.println(string);
			}
		}
	}
	
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
	
	public static void writeOutput(StringBuilder buildForFile, String nombreArchivo,
			String path) {
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

		writeToFile(buildForFile.toString(), ENCODE, result);
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
