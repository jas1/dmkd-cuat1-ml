package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.model.NodoResultadoTablaNormalizada;

import com.ibm.statistics.plugin.StatsException;
import com.ibm.statistics.plugin.StatsUtil;

/**
 * la idea de esta clase es automatizar las corridas del arbol
 * la idea aca es tener el proceso automatizado en todas sus pasos
 * paso 0: levantar datos > ya viene limpio
 * paso 1: limpiar datos  > ya viene limpio
 * paso 2: agregar datos  >ya viene agregado
 * paso 3: configurar algoritmo > decirle de donde levantar datos y configs especificas > viende spss
 * paso 4: ejecutar algoritmo   > tomarle el tiempo
 * paso 5: guardar resultados de algoritmo > generar resultados de la corrida especifica
 * paso 6: evaulacion de fin de corridas de algoritmo > generar resultados masivos
 * paso 7a: si fin de corridas: hacer resumen de resultados totales
 * paso 7b: si no fin de corridas: configurar algo con nuevos parametros ( paso 3 )
 * paso -1: prueba de ejecucion de spss desde java  
 * @author julio
 *
 */
public class AutomatizarCorridasArbolJulio {

	private static final String TEST = "Test";
	private static final String TRAINING = "Training";


	public static AutomatizarCorridasArbolJulioResultado ejecucionArbolVersionParametrizadoArbol(AutomatizarCorridasArbolJulioConfig configArbol,String[] commands) {

		configArbol.setComandoSPSS(commands);
		
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		resultado.setTiempoInicioEjecucion(new Date());
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol;
		try {
			nodosArbol = getElementsForArbolTrainTest(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultado.setTiempoFin(new Date());
		return resultado ;
	}
	

	public static AutomatizarCorridasArbolJulioResultado ejecucionArbol(AutomatizarCorridasArbolJulioConfig configArbol,String[] commands,ParseNodosType tipo) {

		configArbol.setComandoSPSS(commands);
		
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		resultado.setTiempoInicioEjecucion(new Date());
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol = null;
		try {
			switch (tipo) {
				case DEFAULT: getElementsForArbolDefault(configArbol);break;
				case TRAINTEST7030: nodosArbol = getElementsForArbolTrainTest(configArbol);break;
	
				default: throw new Exception("No Implementado este tipo de Parseo de elementos");
			}
			
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultado.setTiempoFin(new Date());
		return resultado ;
	}
	
	public static AutomatizarCorridasArbolJulioResultado ejecucionArbolVersionParametrizadoArbolNoVarParaPaq1(AutomatizarCorridasArbolJulioConfig configArbol) {
//		String[] commands= comandoArbolVersionParametrizadoArbolNoVarParaPaq1(configArbol);
		String[] commands= AcumuladorComandosSpss.comandoArbolVersionParametrizadoArbolNoVarParaPaq1Mezclado(configArbol);
		configArbol.setComandoSPSS(commands);
		
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		resultado.setTiempoInicioEjecucion(new Date());
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol;
		try {
			nodosArbol = getElementsForArbolTrainTest(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultado.setTiempoFin(new Date());
		return resultado ;
	}
	
	/**
	 * par aparsear el resultado del arbol si ya se tiene una carpeta con corridas, configurarla en la configuracion.
	 * a diferencia si se ejecuta completo es que no va a tener cuanto duro.
	 * @param configArbol
	 */
	public static AutomatizarCorridasArbolJulioResultado parseResultadoDefault(AutomatizarCorridasArbolJulioConfig configArbol) {
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		try {
			List<NodoResultadoTablaNormalizada> nodosArbol = getElementsForArbolDefault(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return resultado;
	}
	
	public static AutomatizarCorridasArbolJulioResultado parseResultadoTrainTest(AutomatizarCorridasArbolJulioConfig configArbol) {
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		try {
			List<NodoResultadoTablaNormalizada> nodosArbol = getElementsForArbolTrainTest(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return resultado;
	}


	/**
	 * @param configArbol
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private static List<NodoResultadoTablaNormalizada> getElementsForArbolDefault(AutomatizarCorridasArbolJulioConfig configArbol) throws IOException, Exception {
		String outputhtml = configArbol.getOutputFolder()+configArbol.getTimeStampFolder()+"/"+"output.html";
		Document doc = Jsoup.parse(new File(outputhtml), "UTF-8");
		doc.getAllElements();
		
		List<NodoResultadoTablaNormalizada> nodosArbol = new ArrayList<NodoResultadoTablaNormalizada>();
		Set<Long> sonParental = new HashSet<Long>();
		
		List<Element>tablas = doc.getElementsByTag("table");
		for (Element element : tablas) {
			if (element.getElementsContainingText("Tree Table").size() > 0) {
				List<Element> tbodys = element.getElementsByTag("tbody");
				for (Element tbody : tbodys) {
					
					
					// si tiene mas de 1 registro
					if (tbody.getElementsByTag("tr").size() > 1) {
						List<Element> registros = tbody.getElementsByTag("tr");	
						
						//aca deberian llegar todos los TR
						// armar un string. 
						// muestra	nodo	BAJA+1N	BAJA+1Porcentaje	BAJA+2N	BAJA+2Porcentaje	CONTINUAN	CONTINUAPorcentaje	TotalN	TotalPorcentaje	Categoría pronosticada	Nodo parental	VIPVariable	VIPSig	VIPChi-cuadrado	VIPgl	VIPValores_de_segmentacion
						// en el caso default no hay muestra
						for (Element registro : registros) {
							String[] current = new String[17];
							int i = 0;
							current[i] = "";
							
							List<Element> campos = registro.children();
							for (Element campo : campos) {
								current[++i] = campo.childNodes().get(0).toString(); 
							}
//								System.out.println(Arrays.asList(current).toString());
							// junto los nodos
							NodoResultadoTablaNormalizada nodo = new NodoResultadoTablaNormalizada(current);
							
							// en el caso de lo que es default aca simplemente evaluo la proporcionde mayor a 2.5 % en baja +2
							if (nodo.getBaja2porc() > 2.5) {
								nodosArbol.add(nodo);
								sonParental.add(nodo.getNndoParental());
							}
							

//								nodo.getBaja2num();
						}
					}
				}
			}
		}
		
		
		
		// filtrar los que no sean nodos finales ! 
		// o sea que no aparezcan en la columna Nodo parental
		List<NodoResultadoTablaNormalizada> nodosArbolFinal = nodosArbol.stream().filter(nodo -> !sonParental.contains(nodo.getNodo())).collect(Collectors.toList());
		
		
		return nodosArbolFinal;
	}
	
	/**
	 * @param configArbol
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private static List<NodoResultadoTablaNormalizada> getElementsForArbolTrainTest(AutomatizarCorridasArbolJulioConfig configArbol) throws IOException, Exception {
		String outputhtml = configArbol.getOutputFolder()+configArbol.getTimeStampFolder()+"/"+"output.html";
		Document doc = Jsoup.parse(new File(outputhtml), "UTF-8");
		doc.getAllElements();
		
		List<NodoResultadoTablaNormalizada> nodosArbolTrain = new ArrayList<NodoResultadoTablaNormalizada>();
		
		List<NodoResultadoTablaNormalizada> nodosArbolTest = new ArrayList<NodoResultadoTablaNormalizada>();
		Set<Long> sonParental = new HashSet<Long>();
		List<Element>tablas = doc.getElementsByTag("table");
		for (Element element : tablas) {
			if (element.getElementsContainingText("Tree Table").size() > 0) {
				List<Element> tbodys = element.getElementsByTag("tbody");
				for (Element tbody : tbodys) {
					
					
					// si tiene mas de 1 registro
					if (tbody.getElementsByTag("tr").size() > 1) {
						List<Element> registros = tbody.getElementsByTag("tr");	
						
						//aca deberian llegar todos los TR
						// armar un string. 
						// muestra	nodo	BAJA+1N	BAJA+1Porcentaje	BAJA+2N	BAJA+2Porcentaje	CONTINUAN	CONTINUAPorcentaje	TotalN	TotalPorcentaje	Categoría pronosticada	Nodo parental	VIPVariable	VIPSig	VIPChi-cuadrado	VIPgl	VIPValores_de_segmentacion
						// en el caso default no hay muestra
						
						boolean currentTrain = true;
						
						for (Element registro : registros) {
							
							// por el TH de la tabla 
							if (registro.children().get(0).childNodes().get(0).toString().contains(TEST)) {
								currentTrain = false;
							}
							String[] current = new String[17];
							int i = 0;
//							current[i] = "";
							
							List<Element> campos = registro.children();
							for (Element campo : campos) {
								
								
								
								// si el texto NO tiene Test o Training
								if (!campo.childNodes().get(0).toString().contains(TEST) && !campo.childNodes().get(0).toString().contains(TRAINING)) {
									
									// si es el primer campo
									if (i==0) {
										if (currentTrain) {
											// train
											current[i] = TRAINING;
											// aumento 1 y sigo el valor que deberia tener
											i++;
											current[i] = campo.childNodes().get(0).toString();	
										}else{
											// test
											current[i] = TEST;	
											// aumento 1 y sigo el valor que deberia tener
											i++;
											current[i] = campo.childNodes().get(0).toString();	
										}
									}else{
										// sino el valor del campo
										current[i] = campo.childNodes().get(0).toString();	
									}
									
									
								}else{
									// si es el primer campo
									if (i==0) {
										if (currentTrain) {
											// train
											current[i] = TRAINING;	
										}else{
											// test
											current[i] = TEST;	
										}
									}else{
										// sino el valor del campo
										current[i] = campo.childNodes().get(0).toString();	
									}
									// si es train o test el valor del campo
									current[i] = campo.childNodes().get(0).toString();	
								}
								
								i++; 
							}
//								System.out.println(Arrays.asList(current).toString());
							// junto los nodos
							NodoResultadoTablaNormalizada nodo;
							try {
								nodo = new NodoResultadoTablaNormalizada(current);
							} catch (Exception e) {
								System.out.println(Arrays.toString(current));
								e.printStackTrace();
								throw new Exception(e);
								
							}
							
							// en el caso de lo que es default aca simplemente evaluo la proporcionde mayor a 2.5 % en baja +2
							// y ademas en este caso DEBE SER DE TRAINING
							if (nodo.getBaja2porc() > 2.5 && nodo.getMuestra().equals(TRAINING)) {
								nodosArbolTrain.add(nodo);
								sonParental.add(nodo.getNndoParental());
							}
							// si es de test la agrego igual , y luego voy a depurar los ids de nodos que esten en training
							if (nodo.getMuestra().equals(TEST)) {
								nodosArbolTest.add(nodo);
							}
							

//								nodo.getBaja2num();
						}
					}
				}
			}
		}
		
		// a esta altura levante todos los nodos que me interesan
		List<NodoResultadoTablaNormalizada> nodosFiltradosPorSerParental = nodosArbolTrain.stream().filter(nodo -> !sonParental.contains(nodo.getNodo())).collect(Collectors.toList());
		
		List<NodoResultadoTablaNormalizada> resultado = new ArrayList<NodoResultadoTablaNormalizada>();
//		aca debe haber un segundo filtro que lo que hace es de los nodos restantes de train los busca en testing
		for (NodoResultadoTablaNormalizada nodoTrainFiltrados : nodosFiltradosPorSerParental) {
			for (NodoResultadoTablaNormalizada nodoTest : nodosArbolTest) {
				if (nodoTest.getNodo().equals(nodoTrainFiltrados.getNodo())) {
					resultado.add(nodoTest);
				}
			}
		}
		
		return resultado;
	}
	/**
	 * para hacer lac orrida default
	 * @param configArbol solo toma las propiedades dec onfiguracio: output folder, datos origen sav.
	 * @return
	 */
	public static AutomatizarCorridasArbolJulioResultado ejecucionArbol7030historia(AutomatizarCorridasArbolJulioConfig configArbol){
		
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		String[] commands= AcumuladorComandosSpss.comandoArbolVersion7030Historia(configArbol);
		
		resultado.setTiempoInicioEjecucion(new Date());
		
		for (String string : commands) {
			System.out.println(string);
		}
		
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		resultado.setTiempoFin(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol;
		try {
			nodosArbol = getElementsForArbolDefault(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado ;
	}
	
	/**
	 * para hacer lac orrida default
	 * @param configArbol solo toma las propiedades dec onfiguracio: output folder, datos origen sav.
	 * @return
	 */
	public static AutomatizarCorridasArbolJulioResultado ejecucionArbolDefault(AutomatizarCorridasArbolJulioConfig configArbol){
		
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		String[] commands= AcumuladorComandosSpss.comandoArbolDefault(configArbol);
		
		resultado.setTiempoInicioEjecucion(new Date());
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		resultado.setTiempoFin(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol;
		try {
			nodosArbol = getElementsForArbolDefault(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado ;
	}
	/**
	 * @param configArbol
	 */
	public static AutomatizarCorridasArbolJulioResultado ejecucionArbolVersionParametrizadoArbolNoChaidNoCampos(AutomatizarCorridasArbolJulioConfig configArbol) {
		AutomatizarCorridasArbolJulioResultado resultado = new AutomatizarCorridasArbolJulioResultado(configArbol);
		String[] commands= AcumuladorComandosSpss.comandoArbolVersionParametrizadoArbolNoChaidNoCampos(configArbol);
		
		resultado.setTiempoInicioEjecucion(new Date());
		ejecutarComandosSpss(commands);
		resultado.setTiempoFinEjecucion(new Date());
		
		List<NodoResultadoTablaNormalizada> nodosArbol;
		try {
			nodosArbol = getElementsForArbolTrainTest(configArbol);
			resultado.setNodosResultantes(nodosArbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultado.setTiempoFin(new Date());
		return resultado ;
	}

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
		}
	}
	
	public static String getTimeStamp(Date date, String format) {
		Date defaultDate = (date != null) ? date : new Date();
		String defaulFormat = (format != null) ? format : "yyyyMMdd_HHmmss";

		SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
		return lSDF.format(defaultDate);
	}	
	
}
