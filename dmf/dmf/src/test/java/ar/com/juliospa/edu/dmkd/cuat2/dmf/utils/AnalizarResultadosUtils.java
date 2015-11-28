package ar.com.juliospa.edu.dmkd.cuat2.dmf.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * la idea de esta salio para poner utilidades colgadas 
 * ej: de todos los arboles qeu corri , que columnas aparecieron bajo que parametros.
 * @author julio
 *
 */
public abstract class AnalizarResultadosUtils {
	
	
	/**
	 * busca en el path los archivos de resultados donde hay nodos. 
	 * estos sarchivos se llaman: resumen_ejecucion.txt, tiene que ser recursivo porque hay mucho para dentro
	 * @param aPath
	 * @return
	 */
	public static String buscarNodos(String aPath) {
		StringBuilder build = new StringBuilder();
		Path start = Paths.get(aPath);
		int maxDepth = 5;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		        String.valueOf(path).endsWith("resumen_ejecucion.txt"))) {
		    String joined = stream
		        .sorted()
		        .map(String::valueOf)
		        .collect(Collectors.joining("; "));
		    build.append(joined);
		} catch (IOException e) {
			System.out.println("Reventuti.");
			e.printStackTrace();
		}
		return build.toString();
		
	}
	

}
