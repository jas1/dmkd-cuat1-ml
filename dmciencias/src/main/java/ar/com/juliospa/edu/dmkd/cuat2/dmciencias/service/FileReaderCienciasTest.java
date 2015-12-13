package ar.com.juliospa.edu.dmkd.cuat2.dmciencias.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat2.dmciencias.helpers.UtilidadesGenerales;

public class FileReaderCienciasTest {

	@Test
	public void fileReaderShowLine() {
		
		String path0="C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_ciencia/tp1/COMBO17.csv";
				
		try {
			Stream<String> lines = Files.lines(Paths.get(path0), Charset.defaultCharset());
			StringBuilder build = new StringBuilder();
			lines.forEach(l ->  parsearLinea(l,build));
			
			UtilidadesGenerales.writeOutput(build, "galaxias_2", path0);
			
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



}