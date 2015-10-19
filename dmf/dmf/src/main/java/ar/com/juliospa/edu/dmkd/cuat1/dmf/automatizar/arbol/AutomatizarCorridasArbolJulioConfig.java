package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol;

import java.lang.reflect.Field;

public class AutomatizarCorridasArbolJulioConfig {
	private String origenDatosSav; 
	private String outputFolder;
	private String seed= "12345";
	private String tipoArbol = "CHAID";
	private Integer maximaProfundidad = 6;
	private Integer minParentSize = 350;
	private Integer minChildSize = 245;
	
	public AutomatizarCorridasArbolJulioConfig() {}
	
	public AutomatizarCorridasArbolJulioConfig(String origenDatosSav, String outputFolder, String seed, String tipoArbol, Integer maximaProfundidad, Integer minParentSize, Integer minChildSize) {
		this.origenDatosSav = origenDatosSav;
		this.outputFolder = outputFolder;
		this.seed = seed;
		this.tipoArbol = tipoArbol;
		this.maximaProfundidad = maximaProfundidad;
		this.minParentSize = minParentSize;
		this.minChildSize = minChildSize;
	}
	
	public String getInformacionConfiguracion(){
		StringBuilder build = new StringBuilder();
		build.append("Resumen configuracion ejecucion: ").append(System.getProperty("line.separator"));
		final String separator = "\t";
		Field[] campos = AutomatizarCorridasArbolJulioConfig.class.getFields() ;
		
		for (Field field : campos) {
			field.setAccessible(true);
			try {
				build.append(field.getName()).append(separator).append(field.get(this)).append(System.getProperty("line.separator"));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return build.toString();
	}
	
	public String getOrigenDatosSav() {
		return origenDatosSav;
	}
	public void setOrigenDatosSav(String origenDatosSav) {
		this.origenDatosSav = origenDatosSav;
	}
	public String getOutputFolder() {
		return outputFolder;
	}
	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}
	public String getSeed() {
		return seed;
	}
	public void setSeed(String seed) {
		this.seed = seed;
	}
	public String getTipoArbol() {
		return tipoArbol;
	}
	public void setTipoArbol(String tipoArbol) {
		this.tipoArbol = tipoArbol;
	}
	public Integer getMaximaProfundidad() {
		return maximaProfundidad;
	}
	public void setMaximaProfundidad(Integer maximaProfundidad) {
		this.maximaProfundidad = maximaProfundidad;
	}
	public Integer getMinParentSize() {
		return minParentSize;
	}
	public void setMinParentSize(Integer minParentSize) {
		this.minParentSize = minParentSize;
	}
	public Integer getMinChildSize() {
		return minChildSize;
	}
	public void setMinChildSize(Integer minChildSize) {
		this.minChildSize = minChildSize;
	}
}
