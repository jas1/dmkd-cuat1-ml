package ar.com.juliospa.edu.dmkd.cuat2.dmf.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.UtilidadesGenerales;

public class TestUtils {

	@Test
	public void analizarResultadosTest() {
		String path = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/";
//		String path = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/sql/default/booleans-sin2048-20151115_124612/";
		String[] filePaths = AnalizarResultadosUtils.buscarNodos(path).split(";");
		System.out.println(filePaths.length);
		
		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		
		for (String string : filePaths) {
			boolean flagTodos= false;
//			System.out.println(string);
//			"Test" es la clave para preguntar que onda cuando son archivos de 70/30
//			_test_model.xml , es el buen discriminador para saber si es un arbol que crea test
// para todos " dame todas las filas que le siguen a Valores Nodos
			try {
				List<String> lines = Files.readAllLines(Paths.get(string.trim()));
				List<String> result = new ArrayList<String>();
				for (String line : lines) {
					if (flagTodos) {
						result.add(line);
					}
					if (line.contains("Valores Nodos")) {
						flagTodos= true;
					}

					
				}
				mapa.put(string, result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
//			
//			
//			List<String> result = new ArrayList<String>();
//			
//			
//			try {
//				
//				try (Stream<String> stream = Files.lines(Paths.get(string))) {
//
//				    stream
//				        .filter(line -> wads(line,flagTodos))
//				        .map(String::trim)
//				        .forEach(line -> result.add(line));
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			
//			mapa.put(string, result);
//			
//			
//		}
//		
		StringBuilder build = new StringBuilder();
		for (String key : mapa.keySet()) {
//			System.out.println(key);
			build.append(key).append("\n");
			List<String> tmp = mapa.get(key);
//			for (String lineas :tmp ) {
//				System.out.println(lineas);
//			}
			for (String lineas :tmp ) {
				String[] tmpSplit = lineas.split("\t");
				if (tmpSplit.length > 12) {
					if (!tmpSplit[12].equals("vipVariable")) {
						build.append(tmpSplit[12]).append("\n");	
					}
				}

				
//				System.out.println(tmpSplit[12]);
			}

		}
		
		String outputPath = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/entregable/";
		String outputFile = outputPath+"hojas_arbol.txt";
		UtilidadesGenerales.writeToFile(build.toString(), "UTF-8", outputFile);
		
	}
	
}
