package ar.com.juliospa.edu.dmkd.cuat2.dmf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import ar.com.juliospa.edu.dmkd.cuat2.dmf.model.NodoArbol;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.model.NodoArbolSQL;
import ar.com.juliospa.edu.dmkd.cuat2.dmf.model.NodoResultadoTablaNormalizada;


/**
 * esta clase esta armada para levantar el output de la tabla de reglas generada por el arbol de spss
 * copy pasteada en excel y luego transformada a algo mas normal.
 * 
 * el problema de hacerlo asi es que requiere mucho laburo extra , lo ideal seria hacer export directo
 * @author julio
 *
 */
public class GananciaFromOutputTablaSPSSNormalizada {

	private static final String LINE_SEPARATOR = "line.separator";

	public static String resumenArbolCompletoFromOutputSoloTrain(String archivoPath) throws Exception {
		List<NodoResultadoTablaNormalizada> nodos =  parsearArchivoStd(archivoPath);
		return calcularGananciaSoloTrain(nodos);
	}
	
	public static String resumenArbolCompletoFromOutput(String archivoPath) throws Exception {
		List<NodoResultadoTablaNormalizada> nodos =  parsearArchivoStd(archivoPath);
		return calcularGanancia(nodos);
	}
	
	public static List<NodoResultadoTablaNormalizada> parsearArchivoStd(String archivoPath) throws Exception {
		List<String> lines =  new ArrayList<String>();
	    Path path = Paths.get(archivoPath);
	    //When filteredLines is closed, it closes underlying stream as well as underlying file.
	    
	    try(Stream<String> filteredLines = Files.lines(path)){
	    	filteredLines.forEachOrdered( line -> lines.add(line));
	    }
	    

	    List<NodoResultadoTablaNormalizada> nodos = new ArrayList<NodoResultadoTablaNormalizada>();
	    for (String line : lines) {
	    	nodos.add(new NodoResultadoTablaNormalizada(line.split("\t")));
		}
	    
	    return nodos;
	}
	

	/**
	 * este pes para c uando viene solo train. sin nada. la corrida del pibe de 19
	 * @param nodos
	 * @return
	 */
	public static String calcularGananciaSoloTrain(List<NodoResultadoTablaNormalizada> nodos){
		StringBuilder build = new StringBuilder();
		
		List<NodoArbol> nodosFiltradosTest2 = new ArrayList<NodoArbol>();
		for (NodoResultadoTablaNormalizada nodo : nodos) {
			if (nodo.getBaja2porc() > 2.5) {
				nodosFiltradosTest2.add(new NodoArbol(nodo));
			}
		}
		
		build.append(NodoArbol.getResumenNodoLineaTitulo()).append(System.getProperty(LINE_SEPARATOR));
		for (NodoArbol nodoArbol : nodosFiltradosTest2) {
//			build.append(nodoArbol.getNombreNodo() +": " + nodoArbol.getQuerySelect();
			build.append(nodoArbol.getResumenNodoLinea()).append(System.getProperty(LINE_SEPARATOR));
		}
		return build.toString();
	}
	/**
	 * depura los nodos NodoResultadoTablaNormalizada
	 * 
	 * seleccionando los nodos que tienen mayor a 2.5% de probabilidad en baja+2 en training
	 * 
	 * luego selecciona los nodos con ese ID en testing 
	 * 
	 * luego hace el calculo de ganancia de cada uno de esos nodos
	 * 
	 * 
	 */
	public static String calcularGanancia(List<NodoResultadoTablaNormalizada> nodos){
		List<NodoResultadoTablaNormalizada> nodosFiltrados = new ArrayList<NodoResultadoTablaNormalizada>();
		
		for (NodoResultadoTablaNormalizada nodo : nodos) {
			if (nodo.getMuestra().equals("Entrenamiento") && nodo.getBaja2porc() > 2.5) {
				nodosFiltrados.add(nodo);
			}
		}
		
		List<NodoResultadoTablaNormalizada> nodosFiltradosTest = new ArrayList<NodoResultadoTablaNormalizada>();
		List<NodoArbol> nodosFiltradosTest2 = new ArrayList<NodoArbol>();
		for (NodoResultadoTablaNormalizada nodo : nodos) {
			if (nodo.getMuestra().equals("Contraste") ) {
				for (NodoResultadoTablaNormalizada nodoFiltrado : nodosFiltrados) {
					if (nodoFiltrado.getNodo().equals(nodo.getNodo())) {
						nodosFiltradosTest.add(nodo);
						nodosFiltradosTest2.add(new NodoArbol(nodo));
						//break; // sigue con el que sigue
					}
				}
			}
		}
		
		StringBuilder build = new StringBuilder();
		build.append(NodoArbol.getResumenNodoLineaTitulo()).append(System.getProperty(LINE_SEPARATOR));
		for (NodoArbol nodoArbol : nodosFiltradosTest2) {
//			build.append(nodoArbol.getNombreNodo() +": " + nodoArbol.getQuerySelect();
			build.append(nodoArbol.getResumenNodoLinea()).append(System.getProperty(LINE_SEPARATOR));
		}
		return build.toString();
	} 
}
