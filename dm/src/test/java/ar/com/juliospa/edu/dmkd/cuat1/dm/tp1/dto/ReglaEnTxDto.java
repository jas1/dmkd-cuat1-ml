package ar.com.juliospa.edu.dmkd.cuat1.dm.tp1.dto;

import java.util.List;

public class ReglaEnTxDto {

	private int sizeMuestra;
	private ReglasTodasDto regla;
	private double currentSupport;
	private double currentConfidence;
	private double currentLift;
	
	private List<Integer> txsAntecedenteIds;
	private List<Integer> txsIds;
	
	public ReglaEnTxDto() {}
	
	public ReglasTodasDto getRegla() {
		return regla;
	}
	
	public double getSupport(){
//		#   calcula support: cantidad productos regla juntos / total de la muestra
		  // total de juntos es el txs ids.
		if (sizeMuestra != 0) {
			return txsIds.size() / sizeMuestra;
		}
		return 0;
		
	}
	
	public double getConfidence(){
//		#   calcula confianza: cantidad productos regla juntos / cantidad productos antecedente
		  // total de juntos es el txs ids.
		if (txsAntecedenteIds.size() != 0) {
			return txsIds.size() / txsAntecedenteIds.size();	
		}
		return 0;
		
	}
	
	
	public void setRegla(ReglasTodasDto regla) {
		this.regla = regla;
	}

	public double getCurrentSupport() {
		return currentSupport;
	}

	public void setCurrentSupport(double currentSupport) {
		this.currentSupport = currentSupport;
	}

	public double getCurrentConfidence() {
		return currentConfidence;
	}

	public void setCurrentConfidence(double currentConfidence) {
		this.currentConfidence = currentConfidence;
	}

	public double getCurrentLift() {
		return currentLift;
	}

	public void setCurrentLift(double currentLift) {
		this.currentLift = currentLift;
	}

	public List<Integer> getTxsIds() {
		return txsIds;
	}

	public void setTxsIds(List<Integer> txsIds) {
		this.txsIds = txsIds;
	}

	public int getSizeMuestra() {
		return sizeMuestra;
	}

	public void setSizeMuestra(int sizeMuestra) {
		this.sizeMuestra = sizeMuestra;
	}

	public List<Integer> getTxsAntecedenteIds() {
		return txsAntecedenteIds;
	}

	public void setTxsAntecedenteIds(List<Integer> txsAntecedenteIds) {
		this.txsAntecedenteIds = txsAntecedenteIds;
	}
	
}
