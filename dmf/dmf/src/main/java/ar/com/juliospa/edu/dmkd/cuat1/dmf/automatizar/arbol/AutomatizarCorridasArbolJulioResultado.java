package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol;

import java.lang.reflect.Field;
import java.util.Date;

public class AutomatizarCorridasArbolJulioResultado {

	// tiempos
	private Date tiempoInicio;
	private Date tiempoInicioEjecucion;
	private Date tiempoFinEjecucion;
	private Date tiempoFin;
	
	private AutomatizarCorridasArbolJulioConfig configuracionArbol;

	
	public AutomatizarCorridasArbolJulioResultado(AutomatizarCorridasArbolJulioConfig configuracionArbol) {
		tiempoInicio = new Date();
		this.configuracionArbol = configuracionArbol;
	}
	
	public String getInformacion(){
		StringBuilder build = new StringBuilder();
		build.append("Resumen Resultados: ").append(System.getProperty("line.separator"));
		final String separator = "\t";
		Field[] campos = AutomatizarCorridasArbolJulioConfig.class.getFields() ;
		
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
}
