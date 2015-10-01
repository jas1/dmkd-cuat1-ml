package ar.com.juliospa.edu.dmkd.cuat1.dmf.model;

import java.text.DecimalFormat;

public class NodoArbol {

	private static final String LINE_SEPARATOR = "line.separator";
	private String archivoOriginario;
	private String nombreNodo;
	private String queryNodo;
	
	private Long cantBaja1;
	private Long cantBaja2;
	private Long cantContinua;
	
	private Long formulaCosto;
	private Long formulaGanancia;
	private Long formulaResultado;

	private String defaultSelect;
	private String defaultTabla;
	private String defaultGroupBy;
	
	public NodoArbol() {
		formulaGanancia = 8000L;
		formulaCosto = 200L;
		
//		defaultSelect = "select clase, count(*) as cantidad, count(*)*200 as costo, IF(clase='BAJA+2',count(*)*8000,0) as ganancia ";
		defaultSelect = "select clase, count(*) as cantidad ";
		defaultTabla =  " from dm_finanzas_201504 ";
		defaultGroupBy = " group by clase;";
	}
	
	public Long getTotalNodo(){
		if (cantBaja2 != null && cantBaja1!= null && cantContinua!=null ) {
			return cantBaja2+ cantBaja1 + cantContinua;	
		}
		return null;
	}
	
	/**
	 * devuelve la query armada  defaultSelect + defaultTabla + queryNodo + defaultGroupBy
	 * @return
	 */
	public String getQuerySelect(){
		String tmp = defaultSelect + defaultTabla + queryNodo + defaultGroupBy;
		return tmp.replace("; group by clase;", " group by clase;");
	}
	
	public static String getResumenNodoLineaTitulo(){
		String titulos="CORRIDA\tNOMBRE\tTOTAL\tCANT BAJA+2\tCANT BAJA+1\tCANT CONTINUA\t";
		titulos+="PROP BAJA+2\tPROP BAJA+1\tPROP CONTINUA\tCOSTO BAJA+2\tCOSTO BAJA+1\tCOSTO CONTINUA\t";
		titulos+="GANANCIA\tSALDO";
		
		return titulos;
	}
	
	public String getResumenNodoLinea(){
		Long total = getTotalNodo();
		String ret = archivoOriginario.substring(archivoOriginario.indexOf("null_"))+"\t"+nombreNodo + "\t"+total + "\t"+cantBaja2+"\t"+cantBaja1+"\t"+cantContinua+"\t";
		
		if (total != null && total!=0) {
			
			double proporcion1 = (double)cantBaja2/total ;
			double proporcion2 = (double)cantBaja1/total ;
			double proporcion3 = (double)cantContinua/total ;
		
			ret += proporcion1 + "\t"+proporcion2 +"\t"+proporcion3+"\t";
			ret += (cantBaja2*formulaCosto )+ "\t" + (cantBaja1*formulaCosto ) + "\t" + (cantContinua*formulaCosto )+"\t";
			ret += (cantBaja2*formulaGanancia)+ "\t" + calcularFormula();
			
		}else{
			ret += 0 + "\t"+0 +"\t"+0+"\t";
			ret += (0 )+ "\t" + (0 ) + "\t" + (0 )+"\t";
			ret += (0)+ "\t" + 0;
		}
		
		return ret;
	}
	
	public String getResumenNodo(){
		Long total = getTotalNodo();
		DecimalFormat df = new DecimalFormat("#,###.0000");
		String ret = nombreNodo + " [ " + total +" ]" + System.getProperty(LINE_SEPARATOR) ;
		ret += "BAJA+2\tBAJA+1\tCONTINUA"+ System.getProperty(LINE_SEPARATOR) ;
		ret += cantBaja2+ "\t" + cantBaja1 + "\t" + cantContinua+ System.getProperty(LINE_SEPARATOR) ;
		
		
		if (total != null && total!=0) {
			
			double proporcion1 = (double)cantBaja2/total ;
			double proporcion2 = (double)cantBaja1/total ;
			double proporcion3 = (double)cantContinua/total ;
			
			ret += proporcion1+ "\t" + proporcion2 + "\t" +proporcion3+ System.getProperty(LINE_SEPARATOR) ;	
		}
		
		if (total != null && total!=0) {
			ret += (cantBaja2*formulaCosto )+ "\t" + (cantBaja1*formulaCosto ) + "\t" + (cantContinua*formulaCosto )+ System.getProperty(LINE_SEPARATOR) ;	
		}
		if (total != null && total!=0) {
			ret += (cantBaja2*formulaGanancia )+ "\t" + (0 ) + "\t" + (0 )+ System.getProperty(LINE_SEPARATOR) ;	
		} 
		
		ret += "formula: cantBaja2 * ( formulaGanancia - formulaCosto ) - formulaCosto * ( cantBaja1 + cantContinua )" +System.getProperty(LINE_SEPARATOR) ;
		ret += "formula: " + cantBaja2 + " * ( " +  formulaGanancia + "-" + formulaCosto + " ) -" + formulaCosto + " * (" + cantBaja1 +" + " +cantContinua+ " )" +System.getProperty(LINE_SEPARATOR) ;
		ret += "valor :" + calcularFormula()+System.getProperty(LINE_SEPARATOR) ;
		
		return ret;
	}
	
	public Long calcularFormula(){
		if (cantBaja2 != null && formulaGanancia!=null && formulaCosto!=null && cantBaja1!= null && cantContinua!=null ) {
			formulaResultado = cantBaja2 * ( formulaGanancia - formulaCosto ) - formulaCosto * ( cantBaja1 + cantContinua )  ;	
		}else{
			formulaResultado = null;
		}
		return formulaResultado;
	}
	public String getArchivoOriginario() {
		return archivoOriginario;
	}
	public void setArchivoOriginario(String archivoOriginario) {
		this.archivoOriginario = archivoOriginario;
	}
	public String getNombreNodo() {
		return nombreNodo;
	}
	public void setNombreNodo(String nombreNodo) {
		this.nombreNodo = nombreNodo;
	}
	public String getQueryNodo() {
		return queryNodo;
	}
	public void setQueryNodo(String queryNodo) {
		this.queryNodo = queryNodo;
	}
	public Long getCantBaja1() {
		return cantBaja1;
	}
	public void setCantBaja1(Long cantBaja1) {
		this.cantBaja1 = cantBaja1;
	}
	public Long getCantBaja2() {
		return cantBaja2;
	}
	public void setCantBaja2(Long cantbaja2) {
		this.cantBaja2 = cantbaja2;
	}
	public Long getCantContinua() {
		return cantContinua;
	}
	public void setCantContinua(Long cantContinua) {
		this.cantContinua = cantContinua;
	}
	public Long getFormulaCosto() {
		return formulaCosto;
	}
	public void setFormulaCosto(Long formulaCosto) {
		this.formulaCosto = formulaCosto;
	}
	public Long getFormulaGanancia() {
		return formulaGanancia;
	}
	public void setFormulaGanancia(Long formulaGanancia) {
		this.formulaGanancia = formulaGanancia;
	}
	public Long getFormulaResultado() {
		return formulaResultado;
	}
	public void setFormulaResultado(Long formulaResultado) {
		this.formulaResultado = formulaResultado;
	}
}
