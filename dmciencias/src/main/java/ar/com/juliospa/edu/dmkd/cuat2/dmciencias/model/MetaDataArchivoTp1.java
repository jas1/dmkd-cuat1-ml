package ar.com.juliospa.edu.dmkd.cuat2.dmciencias.model;

/**
 * metadata para el archivo de TP 1
 * ini	end	magnitud otros	nombre campo	descripcion campo
 * @author julio
 *
 */
public class MetaDataArchivoTp1 {
	private Integer ini;
	private Integer end;
	private String magnitudOtros;
	private String nombreCampo;
	private String descripcionCampo;
	
	public MetaDataArchivoTp1(String[] linea) throws Exception {
		if (linea.length != 5) {
			throw new Exception("la cantdidad campos en linea no es 5; es:"+linea.length);
		}
		
		ini = Integer.parseInt(linea[0].trim())-1;
		end = Integer.parseInt(linea[1].trim());
		magnitudOtros = linea[2].trim();
		nombreCampo = linea[3].trim();
		descripcionCampo = linea[4].trim();
	}
	
	public Integer getIni() {
		return ini;
	}
	public void setIni(Integer ini) {
		this.ini = ini;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public String getMagnitudOtros() {
		return magnitudOtros;
	}
	public void setMagnitudOtros(String magnitudOtros) {
		this.magnitudOtros = magnitudOtros;
	}
	public String getNombreCampo() {
		return nombreCampo;
	}
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	public String getDescripcionCampo() {
		return descripcionCampo;
	}
	public void setDescripcionCampo(String descripcionCampo) {
		this.descripcionCampo = descripcionCampo;
	}
}
