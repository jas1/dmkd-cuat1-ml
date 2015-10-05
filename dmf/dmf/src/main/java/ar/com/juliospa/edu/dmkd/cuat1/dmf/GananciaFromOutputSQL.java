package ar.com.juliospa.edu.dmkd.cuat1.dmf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.model.NodoArbolSQL;

/**
 * la idea de esta clase es generar el resultado de la ganancia por nodo dado el output del arbol
 * en updates
 * 
 * formato: 
 * tener en cuenta que las barras estan escapeadas ! 
\/* Node 14 *\/.
UPDATE <TABLE>
	SET nod_001 = 14,	pre_001 = 'CONTINUA',	prb_001 = 0.942398
WHERE (Master_cuenta_estado = '')  AND  (Visa_cuenta_estado = '')  AND  (ttarjeta_visa = 'N');
\/* Node 15 *\/.
UPDATE <TABLE>
	SET nod_001 = 15,	pre_001 = 'CONTINUA',	prb_001 = 0.983278
WHERE (Master_cuenta_estado = '')  AND  (Visa_cuenta_estado = '')  AND  (ttarjeta_visa = '');
 *  
 * @author julio
 *
 */
public abstract class  GananciaFromOutputSQL {

	private static final String LINE_SEPARATOR = "line.separator";
	
	private static ResultSet obtenerResultset(String query, Connection con) throws SQLException {
		PreparedStatement preparedStatementProductos = con.prepareStatement(query);
		ResultSet resultSet = preparedStatementProductos.executeQuery();
		return resultSet;
	}
	
	public static List<NodoArbolSQL> actualizarNodosSegunBase(List<NodoArbolSQL> nodos,Connection con) throws SQLException{
		List<NodoArbolSQL> nodosResult = new ArrayList<NodoArbolSQL>();
		for (NodoArbolSQL nodoArbol : nodos) {
			nodosResult.add(popularNodoArbol(con, nodoArbol));
		}
		return nodosResult;
	}

	/**
	 * @param con
	 * @param nodoArbol
	 * @throws SQLException
	 */
	private static NodoArbolSQL popularNodoArbol(Connection con, NodoArbolSQL nodoArbol) throws SQLException {
		ResultSet result = obtenerResultset(nodoArbol.getQuerySelect(),con);
	
		nodoArbol.setCantContinua(0L);
		nodoArbol.setCantBaja2(0L);
		nodoArbol.setCantBaja1(0L);
		
		while (result.next()) {
			// P.Prod_ID, P.DescGen, P.DescAdic, P.Marca, P.Proveedor,
			// P.CantEnvase, CAT.Cat_Desc AS categoria, SC.SubCat_Desc as
			// subcategoria , PS.Precio AS precio
			String clase = result.getString("clase");
			Long cantidad = result.getLong("cantidad");
			
			/*
			 * CONTINUA
			 * BAJA+2
			 * BAJA+1
			 */
			if (clase.equals("BAJA+1")) {
				nodoArbol.setCantBaja1(cantidad);
			}else
			if (clase.equals("BAJA+2")) {
				nodoArbol.setCantBaja2(cantidad);
			}else
			if (clase.equals("CONTINUA")) {
				nodoArbol.setCantContinua(cantidad);
			}else{
				nodoArbol.setCantContinua(null);
				nodoArbol.setCantBaja2(null);
				nodoArbol.setCantBaja1(null);
			}
		}
		return nodoArbol;
	}
	
	public static List<NodoArbolSQL> parsearArchivo(String archivoPath) throws IOException {
		List<String> lines =  new ArrayList<String>();
	    Path path = Paths.get(archivoPath);
	    //When filteredLines is closed, it closes underlying stream as well as underlying file.
	    
	    try(Stream<String> filteredLines = Files.lines(path).filter(s -> s.contains("Node") || s.contains("WHERE"))){
	    	filteredLines.forEachOrdered( s -> addLine(lines,s));
	    }
	    
	    List<NodoArbolSQL> nodos =  new ArrayList<NodoArbolSQL>();
	    boolean arranco = true;
	    NodoArbolSQL current = new NodoArbolSQL();
	    for (String line : lines) {
	    	if (arranco) {
	    		current = new NodoArbolSQL();
	    		current.setArchivoOriginario(archivoPath);
	    		current.setNombreNodo(line.substring(0+3 , line.length()-4));
	    		arranco= false;
			}else{
				current.setQueryNodo(line);
				nodos.add(current);
				arranco= true;
			}
		}
	    
		return nodos;
	}
	
	private static void addLine(List<String> lines, String line) {
		lines.add(line);
	}
	
	public static String resumenArbolCompletoFromOutput(final String archivoPath, Connection con,boolean showQueries) throws IOException, SQLException {
		
		StringBuilder build = new StringBuilder();
		List<NodoArbolSQL> nodos = GananciaFromOutputSQL.parsearArchivo(archivoPath);
		
		if (showQueries) {
			for (NodoArbolSQL nodoArbol : nodos) {
				build.append(nodoArbol.getNombreNodo() +": " + nodoArbol.getQuerySelect()).append(System.getProperty(LINE_SEPARATOR));
			}
		}
		List<NodoArbolSQL> nodosUpd = GananciaFromOutputSQL.actualizarNodosSegunBase(nodos,con);
		
		build.append(NodoArbolSQL.getResumenNodoLineaTitulo()).append(System.getProperty(LINE_SEPARATOR));
		for (NodoArbolSQL nodoArbol : nodosUpd) {
//			build.append(nodoArbol.getNombreNodo() +": " + nodoArbol.getQuerySelect();
			build.append(nodoArbol.getResumenNodoLinea()).append(System.getProperty(LINE_SEPARATOR));
		}
		return build.toString();
	}
	
}
