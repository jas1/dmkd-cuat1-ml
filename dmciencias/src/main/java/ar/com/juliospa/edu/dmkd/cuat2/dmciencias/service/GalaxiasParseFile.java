package ar.com.juliospa.edu.dmkd.cuat2.dmciencias.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Assert;

import ar.com.juliospa.edu.dmkd.cuat2.dmciencias.model.DatosGalaxiaResumidos;
import ar.com.juliospa.edu.dmkd.cuat2.dmciencias.model.MetaDataArchivoTp1;

public abstract class GalaxiasParseFile {

	public static Map<String, MetaDataArchivoTp1> parseFileMeta(String aFilePath) throws Exception {
		Map<String, MetaDataArchivoTp1> mapa = new HashMap<String, MetaDataArchivoTp1>();

		List<String> lines = new ArrayList<String>();
		Path path = Paths.get(aFilePath);
		try (Stream<String> filteredLines = Files.lines(path)) {
			filteredLines.forEachOrdered(line -> lines.add(line));
		}
		
		boolean header = true;
		for (String line : lines) {
			if (header) {
				header = false;
			}else{
				String[] tmpLine = line.split("\t");
				mapa.put(tmpLine[3], new MetaDataArchivoTp1(tmpLine));				
			}

		}
		return mapa;
	}
	
	public static void parseFilev2(String aFilePath, Map<String, MetaDataArchivoTp1> meta,String nombreArchivo,String pathOut) throws IOException {
		List<String> lines = new ArrayList<String>();
		Path path = Paths.get(aFilePath);
		
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

		String resultFile = pathOut + fechaAlMomento + nombreArchivo;
		final Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFile), ENCODE));
		try {
			writer.write(DatosGalaxiaResumidos.showLineHeader());
			try (Stream<String> filteredLines = Files.lines(path)) {
				filteredLines.forEachOrdered(line -> fileWritter(writer,line,meta));
			}
			System.out.println("output en: " + resultFile);

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
	
	public static void fileWritter(Writer writer, String line, Map<String, MetaDataArchivoTp1> meta) {
		try {
			MetaDataArchivoTp1 tmpClass = meta.get("MC_class");	
			if (line.substring(tmpClass.getIni(),tmpClass.getEnd()).trim().equals("Galaxy")) {
				DatosGalaxiaResumidos tmpGal = new DatosGalaxiaResumidos(line,meta);
				writer.write(tmpGal.showLine());
			}			
		} catch (IOException e) {
			System.out.println("PUM");
			e.printStackTrace();
		}
		
	}
	

	public static String parseFile(String aFilePath, Map<String, MetaDataArchivoTp1> meta) throws IOException {
		List<String> lines = new ArrayList<String>();
		Path path = Paths.get(aFilePath);
		// When filteredLines is closed, it closes underlying stream as well as
		// underlying file.

		try (Stream<String> filteredLines = Files.lines(path)) {
			filteredLines.forEachOrdered(line -> lines.add(line));
		}

		List<DatosGalaxiaResumidos> galaxias = new ArrayList<DatosGalaxiaResumidos>();
		StringBuilder build = new StringBuilder();
		MetaDataArchivoTp1 tmpClass = meta.get("MC_class");
		for (String line : lines) {
			if (line.substring(tmpClass.getIni(),tmpClass.getEnd()).trim().equals("Galaxy")) {
				DatosGalaxiaResumidos tmpGal = new DatosGalaxiaResumidos(line,meta);
				galaxias.add(tmpGal);
				build.append(tmpGal.showLine());
			}
		}
		return build.toString();
	}

}
