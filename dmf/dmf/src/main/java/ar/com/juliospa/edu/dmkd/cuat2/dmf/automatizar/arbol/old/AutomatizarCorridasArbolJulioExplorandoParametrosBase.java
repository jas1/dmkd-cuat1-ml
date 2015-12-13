package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.old;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioResultado;

/**
 * la idea de esta clase es la corrida de varios valores para luego poder
 * comparar que corrida con que parametros y que performance tuvo
 * 
 * @author julio
 *
 */
public class AutomatizarCorridasArbolJulioExplorandoParametrosBase {

	private static final String LINE_SEP = System.getProperty("line.separator");
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

	
	public static void analizarResultadosDeMultiSeed(String outFolder) {
		
		File mainFolder = new File(outFolder);
		
		File[] carpetasTimeStamp = mainFolder.listFiles();
		for (File file : carpetasTimeStamp) {
			System.out.println("analizando:"+ file.getAbsolutePath());
//			analizarResultados();
			System.out.println(analizarResultados(file.getAbsolutePath()));
			
		}
		// nuevamente recorremos la lista de carpetas porque ahora esta el resultado del resultado
		// en resumenEjecuciones.txt
//		esto esta de la carpeta out dentro de la 1ra linea de carpetas

		System.out.println(analizarResultadosDeMultiSeedAgrupados(outFolder));
		
		
	}
	
	public static String analizarResultadosDeMultiSeedAgrupados(String outFolder) {
		
		File mainFolder = new File(outFolder);
		File[] carpetasTimeStamp = mainFolder.listFiles();
		
		StringBuilder build = new StringBuilder();
		build.append("nombre main\tnombre carpeta\tseed\ttipoArbol\tmaximaProfundidad\tminParentSize\tminChildSize\tchild size porc\tGanancia total Nodos\tmultiplicador default para muerte\tMUERTE\tDEFAULT\tSUPERA MUERTE\tSUPERA POR\tsupera en %").append(LINE_SEP);
		for (File folderTimestamp : carpetasTimeStamp) {
			
			String currentFolder = folderTimestamp.getAbsolutePath();
			
			System.out.println("analizando:"+ currentFolder);
			if (!currentFolder.endsWith("/")) {
				currentFolder = currentFolder + "/";
			}
			
//			se quiere llegar a 
//			nombre carpeta	seed	tipoArbol	maximaProfundidad	minParentSize	minChildSize	child size porc	Ganancia total Nodos	multiplicador default para muerte	MUERTE	DEFAULT	SUPERA MUERTE	SUPERA POR	supera en %

//			existe:
//			nombre archivo	nombre archivo	seed	tipoArbol	maximaProfundidad	minParentSize	minChildSize	Ganancia total Nodos	vacio	Ganancia total Nodos
			
			// si es carpeta
			if (folderTimestamp.isDirectory()) {

//				 nombre archivos que buscamos
				String nombreArchivoResultados = folderTimestamp.getAbsolutePath() + System.getProperty("file.separator") + "resumenEjecuciones.txt";
				
//				como archivo
				File resultadosTS = new File(nombreArchivoResultados);
				
				// si no existe lo dejo asentado
				if (!resultadosTS.exists()) {
//					col 2 no existe 
					build.append("no existe archivo resultados").append(nombreArchivoResultados).append(LINE_SEP);
				} else {
					// si existe levanto las lineas y voy agregando lo que me

					try {
						MathContext mc = new MathContext(6, RoundingMode.HALF_UP);
						BigDecimal acumuladorPromedio = new BigDecimal(0);
						Integer cantidadLineas = 0;
						Files.lines(resultadosTS.toPath()).forEach(linea -> procesarLineaResumenEjecuciones(linea,folderTimestamp.getName(),acumuladorPromedio,cantidadLineas, build));
						
						if (cantidadLineas != 0) {
							build.deleteCharAt(build.length()-1);
							build.append(TAB).append(acumuladorPromedio.divide(new BigDecimal(cantidadLineas))).append(LINE_SEP);							
						}
					} catch (Exception e) {
						build.append(e.getMessage()).append(LINE_SEP);
						e.printStackTrace();
					}
				}
			}
		
			// para cada resumen ejecuciones
			
		
			
			
			
			
		}
		
		String persistName = outFolder + "resumenTotal.txt";
		try {
			Files.write(Paths.get(persistName), build.toString().getBytes());
		} catch (IOException e) {
			System.out.println("error al persistir");
			System.out.println(build);
			e.printStackTrace();
		}
		return persistName;

	}
	
	private static void procesarLineaResumenEjecuciones(String linea, String nombreAnalisisFolder, BigDecimal acumuladorPromedio, Integer cantidadLineas, StringBuilder build) {
//		se quiere llegar a 
//	0				1				2		3			4					5				6				7				8						9									10		11		12				13			14
//	nombrefolder	nombre carpeta	seed	tipoArbol	maximaProfundidad	minParentSize	minChildSize	child size porc	Ganancia total Nodos	multiplicador default para muerte	MUERTE	DEFAULT	SUPERA MUERTE	SUPERA POR	supera en %

//		existe:
//		0				1				2		3			4					5				6				7						8		9
//		nombre archivo	nombre archivo	seed	tipoArbol	maximaProfundidad	minParentSize	minChildSize	Ganancia total Nodos	vacio	Ganancia total Nodos
		// es el header , no me interesa !
		if (!linea.contains("Ganancia total Nodos")) {
			
			String[] tempOld = linea.split(TAB);
			if (tempOld.length != 10) {
				build.append("no tiene 10 de largo").append(TAB);
			}else{
				
//				col 0 nombre carpeta
				build.append(nombreAnalisisFolder).append(TAB);
//				col 1 nombre carpeta
				build.append(tempOld[0]).append(TAB);
				// col 2
				build.append(tempOld[2]).append(TAB);
				build.append(tempOld[3]).append(TAB);
				build.append(tempOld[4]).append(TAB);
				build.append(tempOld[5]).append(TAB);
				build.append(tempOld[6]).append(TAB);
				// col 6 se calcula
				double tmpPorc = Double.parseDouble(tempOld[6])*100/Double.parseDouble(tempOld[5]);
				build.append(tmpPorc).append(TAB);
				// col 7 ganancia
				build.append(tempOld[9]).append(TAB);
				// col 8 default : 1.3 
				
				Double normalizador = 1.3;
				Long defaultValue = 2791200L;
				BigDecimal muerte  =  new BigDecimal(defaultValue * normalizador);
				build.append(normalizador).append(TAB);
				
				// col 9 muerte : default * normalizador
				build.append(muerte ).append(TAB);
				
				// col 10 default: default : 2791200
				build.append(defaultValue).append(TAB);
				
				// col 11 calculado if ganancia supera muerte
				BigDecimal ganancia = new BigDecimal(tempOld[9]);
				boolean supera = ganancia.compareTo(muerte) > 0;
				build.append(supera?1:0).append(TAB);	
				
				
				// col 12 supera por: numero ganancia - muerte
				BigDecimal superaMuertePor = ganancia.subtract(muerte);
				build.append(superaMuertePor).append(TAB);	
				
				// col 13 en porcentaje col 12
				MathContext mc = new MathContext(6, RoundingMode.HALF_UP);
				BigDecimal enPorcentaje = superaMuertePor.multiply(new BigDecimal(100)).divide(muerte,mc);
				build.append(enPorcentaje);
				
				// acumulo cant lineas y ganancia, para luego hacer promedio
				cantidadLineas++;
				acumuladorPromedio = acumuladorPromedio.add(ganancia);				
			}
			build.append(LINE_SEP);
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
		if (mainFolder.isDirectory()) {
			File[] carpetasTimeStamp = mainFolder.listFiles();
			StringBuilder build = new StringBuilder();
			final String lineSep = LINE_SEP;
//			origenDatosSav\toutputFolder\t
			build.append("instante corrida\tseed\ttipoArbol\tmaximaProfundidad\tminParentSize\tminChildSize\ttimeStampFolder\tGanancia total Nodos").append(lineSep);
			try {
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
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				Files.write(Paths.get(persistName), build.toString().getBytes());
			} catch (IOException e) {
				System.out.println("error al persistir");
				System.out.println(build);
				e.printStackTrace();
			}
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
