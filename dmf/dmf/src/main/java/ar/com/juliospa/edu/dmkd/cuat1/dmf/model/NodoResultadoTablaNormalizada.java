package ar.com.juliospa.edu.dmkd.cuat1.dmf.model;

import java.math.BigDecimal;


//muestra	nodo	BAJA+1N	BAJA+1Porcentaje	BAJA+2N	BAJA+2Porcentaje	CONTINUAN	CONTINUAPorcentaje	TotalN	TotalPorcentaje	Categoría pronosticada	Nodo parental	VIPVariable	VIPSig	VIPChi-cuadrado	VIPgl	VIPValores_de_segmentacion


public class NodoResultadoTablaNormalizada {

	private String muestra;
	private Long nodo;
	private Long baja1num;
	private Double baja1porc;
	private Long baja2num;
	private Double baja2porc;
	private Long contNum;
	private Double contPorc;
	private Long totalNum;
	private Double totalPorc;
	private String categoriaPronisticada;
	private Long NndoParental;
	// VIP: Variable independiente primaria 
	private String vipVariable;
	private Double vipSig;
	private Double vipChi;
	private Long vipGl;
	private String vipValoresSegmentacion;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return muestra + " - " +nodo;
	}
	
	public static String nodoCsvNombreColumnas() {
		String result = "muestra\tnodo\tbaja1num\tbaja1porc\tbaja2num\tbaja2porc\tcontNum\tcontPorc\ttotalNum\ttotalPorc\tcategoriaPronisticada\tNndoParental\tvipVariable\tvipSig\tvipChi\tvipGl\tvipValoresSegmentacion";
		return result;
	}
	
	public String nodoCsvValoresColumnas() {
//		BigDecimal valorFinal = new BigDecimal( ( (ganancia-costo) * baja2num  - costo * ( baja1num + contNum )) / normalizador ); 
		String result = muestra+"\t"+nodo+"\t"+baja1num+"\t"+baja1porc+"\t"+baja2num+"\t"+baja2porc+"\t"+contNum+"\t"+contPorc+"\t"+totalNum+"\t"+totalPorc+"\t"+categoriaPronisticada+"\t"+NndoParental+"\t"+vipVariable+"\t"+vipSig+"\t"+vipChi+"\t"+vipGl+"\t"+vipValoresSegmentacion;
		return result;
	}
	public static String nodoCsvGananciaNombreColumnas() {
		String result = "ganancia\tcosto\tbaja2num\tcosto\tbaja1num\tcontNum\tnormalizador\tvalorFinal";
		return result;
	}
	public String nodoCsvGananciaColumnas(Long costo, Long ganancia, Double normalizador) {
		BigDecimal valorFinal = new BigDecimal( ( (ganancia-costo) * baja2num  - costo * ( baja1num + contNum )) / normalizador ); 
		String result = ganancia +"\t"+ costo+"\t"+ baja2num +"\t"+ costo +"\t"+ baja1num +"\t"+contNum+"\t"+ normalizador +"\t"+ valorFinal.toPlainString();
		return result;
	}
	
	public BigDecimal cuentaValor(Long costo, Long ganancia, Double normalizador) {
		BigDecimal valorFinal = new BigDecimal( ( (ganancia-costo) * baja2num  - costo * ( baja1num + contNum )) / normalizador );
		return valorFinal;
	}
	
	public String cuentaEscrita(Long costo, Long ganancia, Double normalizador) {
		
		BigDecimal valorFinal = new BigDecimal( ( (ganancia-costo) * baja2num  - costo * ( baja1num + contNum )) / normalizador ); 
		
		String result = "( (" + ganancia+ " - " + costo+ " ) * " + baja2num +" - " + costo +" * ( " +baja1num +" + " +contNum+" )) / " + normalizador + " = " + valorFinal.toPlainString();
		return result;
	}
	
	public NodoResultadoTablaNormalizada(String[] aLinea) throws Exception {
		if (aLinea.length != 17 && aLinea.length != 11 ) {
			throw new Exception("cantidad de columnas invalida: "+aLinea.length);
		}
		muestra=aLinea[0].trim();
		nodo=Long.parseLong(aLinea[1].trim());
		baja1num=Long.parseLong(aLinea[2].trim());
		baja1porc=Double.parseDouble(aLinea[3].trim().replace("%", "").replace(",", "."));
		baja2num=Long.parseLong(aLinea[4].trim());
		baja2porc=Double.parseDouble(aLinea[5].trim().replace("%", "").replace(",", "."));
		contNum=Long.parseLong(aLinea[6].trim());
		contPorc=Double.parseDouble(aLinea[7].trim().replace("%", "").replace(",", "."));
		totalNum=Long.parseLong(aLinea[8].trim());
		totalPorc=Double.parseDouble(aLinea[9].trim().replace("%", "").replace(",", "."));
		categoriaPronisticada=aLinea[10].trim();
		if (aLinea.length != 11 && !aLinea[11].contains("&nbsp;") ) {
			NndoParental=Long.parseLong(aLinea[11].trim());
			vipVariable=aLinea[12].trim();
			vipSig=Double.parseDouble(aLinea[13].trim().replace("%", "").replace(",", "."));
			vipChi=Double.parseDouble(aLinea[14].trim().replace("%", "").replace(",", "."));
			vipGl=Long.parseLong(aLinea[15].trim());
			vipValoresSegmentacion=aLinea[16].trim();
		}

	}

	public String getMuestra() {
		return muestra;
	}

	public void setMuestra(String muestra) {
		this.muestra = muestra;
	}

	public Long getNodo() {
		return nodo;
	}

	public void setNodo(Long nodo) {
		this.nodo = nodo;
	}

	public Long getBaja1num() {
		return baja1num;
	}

	public void setBaja1num(Long baja1num) {
		this.baja1num = baja1num;
	}

	public Double getBaja1porc() {
		return baja1porc;
	}

	public void setBaja1porc(Double baja1porc) {
		this.baja1porc = baja1porc;
	}

	public Long getBaja2num() {
		return baja2num;
	}

	public void setBaja2num(Long baja2num) {
		this.baja2num = baja2num;
	}

	public Double getBaja2porc() {
		return baja2porc;
	}

	public void setBaja2porc(Double baja2porc) {
		this.baja2porc = baja2porc;
	}

	public Long getContNum() {
		return contNum;
	}

	public void setContNum(Long contNum) {
		this.contNum = contNum;
	}

	public Double getContPorc() {
		return contPorc;
	}

	public void setContPorc(Double contPorc) {
		this.contPorc = contPorc;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public Double getTotalPorc() {
		return totalPorc;
	}

	public void setTotalPorc(Double totalPorc) {
		this.totalPorc = totalPorc;
	}

	public String getCategoriaPronisticada() {
		return categoriaPronisticada;
	}

	public void setCategoriaPronisticada(String categoriaPronisticada) {
		this.categoriaPronisticada = categoriaPronisticada;
	}

	public Long getNndoParental() {
		return NndoParental;
	}

	public void setNndoParental(Long nndoParental) {
		NndoParental = nndoParental;
	}

	public String getVipVariable() {
		return vipVariable;
	}

	public void setVipVariable(String vipVariable) {
		this.vipVariable = vipVariable;
	}

	public Double getVipSig() {
		return vipSig;
	}

	public void setVipSig(Double vipSig) {
		this.vipSig = vipSig;
	}

	public Double getVipChi() {
		return vipChi;
	}

	public void setVipChi(Double vipChi) {
		this.vipChi = vipChi;
	}

	public Long getVipGl() {
		return vipGl;
	}

	public void setVipGl(Long vipGl) {
		this.vipGl = vipGl;
	}

	public String getVipValoresSegmentacion() {
		return vipValoresSegmentacion;
	}

	public void setVipValoresSegmentacion(String vipValoresSegmentacion) {
		this.vipValoresSegmentacion = vipValoresSegmentacion;
	}	
}
