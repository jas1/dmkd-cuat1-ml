package ar.com.juliospa.edu.dmkd.cuat2.dmciencias;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat2.dmciencias.model.MetaDataArchivoTp1;
import ar.com.juliospa.edu.dmkd.cuat2.dmciencias.service.GalaxiasParseFile;

public class Tp1GalaxiasParte2 {

	
	@Test
	public void fileReaderShowLine() {
		
		String path0="C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_ciencia/tp1_parte_2/campos.txt";
		String path1="C:/Users/julio/Desktop/dmciencia_wd/table3.dat";
			
		String path = "C:/Users/julio/Desktop/dmciencia_wd/";
		String name = "galaxias_filtrado.txt";
		
		try {
			Map<String, MetaDataArchivoTp1> meta = GalaxiasParseFile.parseFileMeta(path0);
			GalaxiasParseFile.parseFilev2(path1,meta,name,path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void writeOutput(String buildForFile, String nombreArchivo,
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

	private void writeToFile(String stringToWrite, final String encoding,
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
