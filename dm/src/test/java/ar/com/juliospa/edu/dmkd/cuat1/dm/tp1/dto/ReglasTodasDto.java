package ar.com.juliospa.edu.dmkd.cuat1.dm.tp1.dto;

public class ReglasTodasDto {
	private Integer id;
	private String region;
	private Integer idregla;
	private String regla;
	private double support;
	private double confidence;
	private double lift;

	public ReglasTodasDto(Integer id,String region, Integer idregla, String regla,
			double support, double confidence, double lift) {
		
		this.id = id;
		this.region = region;
		this.idregla = idregla;
		this.regla = regla;
		this.support = support;
		this.confidence = confidence;
		this.lift = lift;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getIdregla() {
		return idregla;
	}
	public void setIdregla(Integer idregla) {
		this.idregla = idregla;
	}
	public String getRegla() {
		return regla;
	}
	public void setRegla(String regla) {
		this.regla = regla;
	}
	public double getSupport() {
		return support;
	}
	public void setSupport(double support) {
		this.support = support;
	}
	public double getConfidence() {
		return confidence;
	}
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	public double getLift() {
		return lift;
	}
	public void setLift(double lift) {
		this.lift = lift;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String show() {
		return id + "\t" + idregla + region + "\t" + regla  + "\t" + support  + "\t" + confidence  + "\t" + lift ;
	}
}
