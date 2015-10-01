package ar.com.juliospa.edu.dmkd.cuat1.dmf;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class FileReaderCienciasTest {

	@Test
	public void fileReaderShowLine() {
		
		String path0="C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_ciencia/tp1/COMBO17.csv";
				
		try {
			Stream<String> lines = Files.lines(Paths.get(path0), Charset.defaultCharset());
			StringBuilder build = new StringBuilder();
			lines.forEach(l ->  parsearLinea(l,build));
			
			writeOutput(build, "galaxias_2", path0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void parsearLinea(String l, StringBuilder build) {
		String[] tmp = l.split(",");
		tmp[30] = tmp[30].replace("E", "e");
		if (tmp[30].contains("  ")) {
			String tmp2 = tmp[30].substring(0, tmp[30].lastIndexOf(" ") + 1);
			tmp[30] = tmp[30].replace(tmp2, "");

		}
		System.out.println(tmp[30]);

		for (String string : tmp) {
			build.append(string).append(",");
		}
		build.deleteCharAt(build.length() - 1);
		build.append("\n");

	}

	private void writeOutput(StringBuilder buildForFile, String nombreArchivo,
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