package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * la idea de esta clase es la corrida de varios valores para luego poder
 * comparar que corrida con que parametros y que performance tuvo
 * 
 * @author julio
 *
 */
public class AutomatizarCorridasArbolJulioExplorandoParametrosBase {

	private static final String TAB = "\t";

	/**
	 * este va a variar profundidad , parent size, child size, todo con el
	 * dataset original este va a tener metodo de fuerza bruta. TODO: seria
	 * copado que en vez de fuerza bruta vaya buscando extremos y achicando
	 * 
	 * @param origenDatos
	 *            de donde va a leer
	 * @param outFolder
	 *            donde va a tirar los resultados
	 * @param maximaProfundidadList
	 *            listado de profundidades a evaluar
	 * @param minParentSizeList
	 *            listado de minimo tamaño del padre
	 * @param minChildSizeList
	 *            proporcion de cuanto respecto del padre es el tamaño de los
	 *            hijos
	 */
	public static void ejecutarCorridasVariaParametrosBase(String origenDatos, String outFolder, Integer[] maximaProfundidadList, Integer[] minParentSizeList, Integer[] minChildSizeList) {
		// configuracion corrida
		String seed = "12345";
		String tipoArbol = "CHAID";
		Long ganancia = 8000L;
		Long costo = 200L;
		// por el 30% de train/test
		double normalizador = 0.3;

		for (Integer maximaProfundidad : maximaProfundidadList) {
			for (Integer minParentSize : minParentSizeList) {
				for (Integer porcMinChildSize : minChildSizeList) {
					Integer minChildSize = Math.round(minParentSize * porcMinChildSize / 100);

					AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
					configArbol.setSeed(seed);
					configArbol.setOrigenDatosSav(origenDatos);
					configArbol.setTipoArbol(tipoArbol);
					configArbol.setOutputFolder(outFolder);
					configArbol.setMaximaProfundidad(maximaProfundidad);
					configArbol.setMinChildSize(minChildSize);
					configArbol.setMinParentSize(minParentSize);
					configArbol.setGanancia(ganancia);
					configArbol.setCosto(costo);
					configArbol.setNormalizador(normalizador);

					AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.ejecucionArbolVersionParametrizadoArbolNoChaidNoCampos(configArbol);
					System.out.println(result.persistime());
				}
			}
		}
	}

	/**
	 * 
	 * @param outFolder
	 *            : la carpeta donde fue el resultado del metodo:
	 *            ejecutarCorridasVariaParametrosBase, en esta carpeta se pondra
	 *            el archivo resumen de las corridas
	 */
	public static String analizarResultados(String outFolder) {
		// agarrar todos los archivos de resultados , recordar que estan
		// categorizados por carpeta, el nombre del archivo escrito es el mismo:
		// "resumen_ejecucion.txt"
		// la carpeta output tiene N carpetas siendo cada una un timestamp de
		// yyyyMMdd_HHmmss
		// la idea seria llegar a un formato yyyyMMdd_HHmmss parametros ganancia
		// tiempos ejecucion
		// ejemplo de parametros
		// origenDatosSav
		// C:/Users/julio/Desktop/dmf_wd/nulos/DataSetAbril2015.sav
		// outputFolder
		// C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/paramsBase/
		// seed 12345
		// tipoArbol CHAID
		// maximaProfundidad 8
		// minParentSize 1000
		// minChildSize 900
		// timeStampFolder 20151030_011958
		// Ganancia total Nodos: 3311333.3333333335467614233493804931640625

		if (!outFolder.endsWith("/")) {
			outFolder = outFolder + "/";
		}

		String persistName = outFolder + "resumenEjecuciones.txt";

		File mainFolder = new File(outFolder);
		File[] carpetasTimeStamp = mainFolder.listFiles();
		StringBuilder build = new StringBuilder();
		final String lineSep = System.getProperty("line.separator");
//		origenDatosSav\toutputFolder\t
		build.append("instante corrida\tseed\ttipoArbol\tmaximaProfundidad\tminParentSize\tminChildSize\ttimeStampFolder\tGanancia total Nodos").append(lineSep);
		for (File folderTimestamp : carpetasTimeStamp) {

			if (folderTimestamp.isDirectory()) {
				build.append(folderTimestamp.getName()).append(TAB);
				// File[] archivosFolderTs = folderTimestamp.listFiles();
				// archivo de resumen resultados =
				String nombreArchivoResultados = folderTimestamp.getAbsolutePath() + System.getProperty("file.separator") + "resumen_ejecucion.txt";
				File resultadosTS = new File(nombreArchivoResultados);
				// si no existe lo dejo asentado
				if (!resultadosTS.exists()) {
					build.append("no existe archivo resultados").append(nombreArchivoResultados).append(lineSep);
				} else {
					// si existe levanto las lineas y voy agregando lo que me
					// interesa
					build.append(folderTimestamp.getName()).append(TAB);

					try {
						Files.lines(resultadosTS.toPath()).forEach(linea -> procesarLinea(linea, build));
					} catch (IOException e) {
						build.append(e.getMessage()).append(lineSep);
						e.printStackTrace();
					}
					build.append(lineSep);
				}

			}

		}

		try {
			Files.write(Paths.get(persistName), build.toString().getBytes());
		} catch (IOException e) {
			System.out.println("error al persistir");
			System.out.println(build);
			e.printStackTrace();
		}
		return persistName;

	}

	private static void procesarLinea(String linea, StringBuilder builder) {
		List<String> valoresFiltro = new ArrayList<String>();
		// valoresFiltro.add("origenDatosSav");
		// valoresFiltro.add("outputFolder");
		valoresFiltro.add("seed");
		valoresFiltro.add("tipoArbol");
		valoresFiltro.add("maximaProfundidad");
		valoresFiltro.add("minParentSize");
		valoresFiltro.add("minChildSize");
		valoresFiltro.add("timeStampFolder");
		valoresFiltro.add("Ganancia total Nodos");
		for (String valorFiltro : valoresFiltro) {

			if (linea.contains(valorFiltro)) {
				if (linea.contains("Ganancia total Nodos")) {
					builder.append(linea.substring(linea.indexOf(":") + 1));
				} else {
					String[] tempCampos = linea.split(TAB);
					builder.append(tempCampos[1]).append(TAB);
				}
			}
		}

		// origenDatosSav
		// C:/Users/julio/Desktop/dmf_wd/nulos/DataSetAbril2015.sav
		// outputFolder
		// C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/paramsBase/
		// seed 12345
		// tipoArbol CHAID
		// maximaProfundidad 8
		// minParentSize 1000
		// minChildSize 900
		// timeStampFolder 20151030_011958
		// Ganancia total Nodos: 3311333.3333333335467614233493804931640625

	}

}
