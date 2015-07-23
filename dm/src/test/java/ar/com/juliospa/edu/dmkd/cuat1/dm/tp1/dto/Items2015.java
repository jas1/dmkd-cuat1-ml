package ar.com.juliospa.edu.dmkd.cuat1.dm.tp1.dto;

public class Items2015 {
	private Integer tx;
	private String item;
	private String region;
	
	public Items2015(Integer tx, String item, String region) {
		super();
		this.tx = tx;
		this.item = item;
		this.region = region;
	}
	public Integer getTx() {
		return tx;
	}
	public void setTx(Integer tx) {
		this.tx = tx;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	
}