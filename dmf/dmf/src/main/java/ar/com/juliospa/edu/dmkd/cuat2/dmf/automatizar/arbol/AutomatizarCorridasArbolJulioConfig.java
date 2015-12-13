package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AutomatizarCorridasArbolJulioConfig {
	
	private Long costo;
	private Long ganancia;
	private Double normalizador;
	
	private String origenDatosSav; 
	private String outputFolder;
	private String seed= "12345";
	private String tipoArbol = "CHAID";
	private Integer maximaProfundidad = 6;
	private Integer minParentSize = 350;
	private Integer minChildSize = 245;
	
	// si variamos los dataset que se puedan variar las columnas
	private String columnas;
	
	private String[] comandoSPSS={""};
	
	private String timeStampFolder; 
	
	public AutomatizarCorridasArbolJulioConfig() {}
	
	/**
	 * @param origenDatosSav
	 * @param outputFolder
	 * @param seed
	 * @param tipoArbol
	 * @param maximaProfundidad
	 * @param minParentSize
	 * @param minChildSize
	 * @param costo
	 * @param ganancia
	 * @param normalizador
	 */
	public AutomatizarCorridasArbolJulioConfig(String origenDatosSav, String outputFolder, String seed, String tipoArbol, Integer maximaProfundidad, Integer minParentSize, Integer minChildSize, Long costo,Long ganancia, Double normalizador) {
		this.origenDatosSav = origenDatosSav;
		this.outputFolder = outputFolder;
		this.seed = seed;
		this.tipoArbol = tipoArbol;
		this.maximaProfundidad = maximaProfundidad;
		this.minParentSize = minParentSize;
		this.minChildSize = minChildSize;
		this.costo = costo;
		this.ganancia = ganancia;
		this.normalizador = normalizador;
	}
	
	public String getInformacionConfiguracion(){
		StringBuilder build = new StringBuilder();
		build.append("Resumen configuracion ejecucion: ").append(System.getProperty("line.separator"));
		final String separator = "\t";
		Field[] campos = AutomatizarCorridasArbolJulioConfig.class.getDeclaredFields() ;
		
		for (Field field : campos) {
			field.setAccessible(true);
			try {
				if (field.getType().isArray() ) {
					Object currentValue = field.get(this); 
					if (currentValue instanceof String[]) {
						build.append(field.getName()).append(separator).append(Arrays.toString((String[])field.get(this))).append(System.getProperty("line.separator"));						
					}else{
						build.append(field.getName()).append(separator).append("array de algo").append(field.get(this)).append(System.getProperty("line.separator"));
					}
					
	
				}else{
					build.append(field.getName()).append(separator).append(field.get(this)).append(System.getProperty("line.separator"));	
				}
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
	
	/* cabezoteada */
	public void setOutputFolder(String outputFolder) {
		
		if (outputFolder != null) {
			if (!outputFolder.endsWith("/")) {
				this.outputFolder = outputFolder+"/";	
			}else{
				this.outputFolder = outputFolder;
			}
		}else{
			throw new RuntimeException( "no podes setearlo null !");
		}
		
		
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

	public String getTimeStampFolder() {
		return timeStampFolder;
	}

	public void setTimeStampFolder(String timeStampFolder) {
		this.timeStampFolder = timeStampFolder;
	}

	public Long getCosto() {
		return costo;
	}

	public void setCosto(Long costo) {
		this.costo = costo;
	}

	public Long getGanancia() {
		return ganancia;
	}

	public void setGanancia(Long ganancia) {
		this.ganancia = ganancia;
	}

	public Double getNormalizador() {
		return normalizador;
	}

	public void setNormalizador(Double normalizador) {
		this.normalizador = normalizador;
	}

	public String[] getComandoSPSS() {
		return comandoSPSS;
	}

	public void setComandoSPSS(String[] comandoSPSS) {
		this.comandoSPSS = comandoSPSS;
	}

	public String getColumnas() {
		return columnas;
	}

	public void setColumnas(String columnas) {
		this.columnas = columnas;
	}
}
