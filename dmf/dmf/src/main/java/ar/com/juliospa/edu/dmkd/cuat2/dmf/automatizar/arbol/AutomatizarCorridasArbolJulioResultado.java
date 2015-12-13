package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import ar.com.juliospa.edu.dmkd.cuat2.dmf.model.NodoResultadoTablaNormalizada;

public class AutomatizarCorridasArbolJulioResultado {

	// tiempos
	private Date tiempoInicio;
	private Date tiempoInicioEjecucion;
	private Date tiempoFinEjecucion;
	private Date tiempoFin;
	
	
	private AutomatizarCorridasArbolJulioConfig configuracionArbol;
	private List<NodoResultadoTablaNormalizada> nodosResultantes;
	
	public String persistime(){
		StringBuilder build = new StringBuilder();
		build.append(getInformacion());
		
		String persistName = configuracionArbol.getOutputFolder()+configuracionArbol.getTimeStampFolder()+"/resumen_ejecucion.txt";
		
		try {
			Files.write(Paths.get(persistName), build.toString().getBytes());
		} catch (IOException e) {
			System.out.println("error al persistir");
			System.out.println(build);
			e.printStackTrace();
		}
		return persistName;
	}
	
	
	public BigDecimal getGananciaTotalNodos(Long costo, Long ganancia, Double normalizador) {
		BigDecimal acumulado = new BigDecimal("0");
		if (nodosResultantes != null && nodosResultantes.size() > 0) {
			
			
			for (NodoResultadoTablaNormalizada nodo : nodosResultantes) {
				acumulado = acumulado.add( nodo.cuentaValor(costo, ganancia, normalizador) );
			}
		}
		return acumulado;
	}
	
	
	public String getValoresNodos(){
		StringBuilder build = new StringBuilder();
		
		if (nodosResultantes != null && nodosResultantes.size() > 0) {
			build.append(NodoResultadoTablaNormalizada.nodoCsvNombreColumnas()).append(System.getProperty("line.separator"));
			for (NodoResultadoTablaNormalizada nodo : nodosResultantes) {
				build.append(nodo.nodoCsvValoresColumnas()).append(System.getProperty("line.separator"));
			}
		}else{
			build.append("No se encontraron nodos resultantes.").append(System.getProperty("line.separator"));
		}
		return build.toString();
	}
	
	public String getGananciasNodos(Long costo, Long ganancia, Double normalizador) {
		StringBuilder build = new StringBuilder();
		
		if (nodosResultantes != null && nodosResultantes.size() > 0) {
			build.append(NodoResultadoTablaNormalizada.nodoCsvGananciaNombreColumnas()).append(System.getProperty("line.separator"));
			for (NodoResultadoTablaNormalizada nodo : nodosResultantes) {
				build.append(nodo.nodoCsvGananciaColumnas(costo, ganancia, normalizador)).append(System.getProperty("line.separator"));
			}
		}else{
			build.append("No se encontraron nodos resultantes.").append(System.getProperty("line.separator"));
		}
		return build.toString();
	}


	
	public AutomatizarCorridasArbolJulioResultado(AutomatizarCorridasArbolJulioConfig configuracionArbol) {
		tiempoInicio = new Date();
		this.configuracionArbol = configuracionArbol;
		

		
	}
	
	public String getInformacion(){
		StringBuilder build = new StringBuilder();
		build.append("Resumen Resultados: ").append(System.getProperty("line.separator"));
		final String separator = "\t";
		Field[] campos = AutomatizarCorridasArbolJulioConfig.class.getDeclaredFields() ;
		
		for (Field field : campos) {
			field.setAccessible(true);
			if(field.getType().getClass().equals(Date.class)){
				try {
					build.append(field.getName()).append(separator).append(field.get(this)).append(System.getProperty("line.separator"));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		build.append(configuracionArbol.getInformacionConfiguracion());
		build.append("Ganancia total Nodos: ").append(separator).append(getGananciaTotalNodos(configuracionArbol.getCosto(), configuracionArbol.getGanancia(), configuracionArbol.getNormalizador())).append(System.getProperty("line.separator"));
		build.append("Resumen Nodos: ").append(System.getProperty("line.separator"));
		build.append(getGananciasNodos(configuracionArbol.getCosto(), configuracionArbol.getGanancia(), configuracionArbol.getNormalizador()));
		build.append("Valores Nodos: ").append(System.getProperty("line.separator"));
		build.append(getValoresNodos());
		


		
		
		return build.toString();
	}
	
	public Date getTiempoInicio() {
		return tiempoInicio;
	}

	public void setTiempoInicio(Date tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	public Date getTiempoInicioEjecucion() {
		return tiempoInicioEjecucion;
	}

	public void setTiempoInicioEjecucion(Date tiempoInicioEjecucion) {
		this.tiempoInicioEjecucion = tiempoInicioEjecucion;
	}

	public Date getTiempoFinEjecucion() {
		return tiempoFinEjecucion;
	}

	public void setTiempoFinEjecucion(Date tiempoFinEjecucion) {
		this.tiempoFinEjecucion = tiempoFinEjecucion;
	}

	public Date getTiempoFin() {
		return tiempoFin;
	}

	public void setTiempoFin(Date tiempoFin) {
		this.tiempoFin = tiempoFin;
	}

	public AutomatizarCorridasArbolJulioConfig getConfiguracionArbol() {
		return configuracionArbol;
	}

	public void setConfiguracionArbol(AutomatizarCorridasArbolJulioConfig configuracionArbol) {
		this.configuracionArbol = configuracionArbol;
	}



	public List<NodoResultadoTablaNormalizada> getNodosResultantes() {
		return nodosResultantes;
	}



	public void setNodosResultantes(List<NodoResultadoTablaNormalizada> nodosResultantes) {
		this.nodosResultantes = nodosResultantes;
	}
}
