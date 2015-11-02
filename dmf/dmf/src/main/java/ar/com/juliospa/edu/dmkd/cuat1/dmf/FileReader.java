package ar.com.juliospa.edu.dmkd.cuat1.dmf;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


/**
 * el proposito de esta clase es poder acceder a los archivos enormes que envio el profe
 * @author julio
 *
 */
public class FileReader {

	/* 
	 * primera cosa a considerar es que el enorme no lo puedo abrir con nada,
	 * yo solo queria chequear la estructura viendo 1 linea ... y no lo puedo abrir.
	 * 
	 * para verificar que tan diferentes son el enorme el mediano y los header
	 * http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
	 * http://www.oracle.com/technetwork/articles/java/architect-streams-pt2-2227132.html
	 * */
	public static String sampleLine(String aPath) {
		String sample = "";
		try (Stream<String> lines = Files.lines(Paths.get(aPath), Charset.defaultCharset())) {
			  lines.forEachOrdered(System.out::println);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sample;
	}
	
	public static StringBuilder empiezaConBuilder(String aPath,String empiezaCon,String separator) {
		StringBuilder build = new StringBuilder(); 
		try (Stream<String> lines = Files.lines(Paths.get(aPath), Charset.defaultCharset())) {
			  lines.forEachOrdered(line -> devolverColumasEmpiezanCon(line,empiezaCon,build,separator));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return build;
	}
	
	private static void devolverColumasEmpiezanCon(String line, String empiezaCon, StringBuilder build, String separator) {
		String[] split = line.split("\t");
		for (String string : split) {
			if (string.trim().startsWith(empiezaCon)) {
				build.append(string.trim()).append(separator);
			}
		}
	}

	public static String empiezaCon(String aPath,String empiezaCon) {
		String sample = "";
		try (Stream<String> lines = Files.lines(Paths.get(aPath), Charset.defaultCharset())) {
			  lines.forEachOrdered(line -> devolverColumasEmpiezanCon(line,empiezaCon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sample;
	}

	private static void devolverColumasEmpiezanCon(String line,String empiezaCon) {
		String[] split = line.split("\t");
		for (String string : split) {
			if (string.trim().startsWith(empiezaCon)) {
				System.out.println(string.trim());
			}
		}
	}
	
	public static String booleanizarCols(String aPath,String empiezaCon) {
		String sample = "";
		try (Stream<String> lines = Files.lines(Paths.get(aPath), Charset.defaultCharset())) {
			  lines.forEachOrdered(line -> devolverColumasEmpiezanConYScriptR(line,empiezaCon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sample;
	}
	
	private static void devolverColumasEmpiezanConYScriptR(String line,String empiezaCon) {
		String[] split = line.split("\t");
		for (String string : split) {
			if (string.trim().startsWith(empiezaCon) ) {
				
//			    gorriones$vivo[gorriones$vivo == 1] <- TRUE # es 1
//			    gorriones$vivo[gorriones$vivo == -1] <- FALSE # es 0 
//			    gorriones$vivo <- gorriones$vivo == 1
//			    gorriones$vivo <- as.factor(gorriones$vivo)
				
				String str1 ="datos$"+string+"[datos$"+string+ "== 'N'] <- 0";
				String str2 ="datos$"+string+"[datos$"+string+ "== 'S'] <- 1";
				String str3 ="datos$"+string+"<- as.integer(datos$"+string+")";
				String str4 ="datos$"+string+"<- as.logical(datos$"+string+")";
				
				System.out.println(str1);
				System.out.println(str2);
				System.out.println(str3);
				System.out.println(str4);
			}
		}
	}
	
}
