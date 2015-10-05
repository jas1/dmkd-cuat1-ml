package ar.com.juliospa.edu.dmkd.cuat2.dmciencias.model;

import java.util.Map;

public class DatosGalaxiaResumidos {
//	private BigDecimal campoSeq;
//	private BigDecimal campoRah;
//	private BigDecimal campoRam;
//	private BigDecimal campoRas;
//	private BigDecimal campoDE;
//	private BigDecimal campoDed;
//	private BigDecimal campoDem;
//	private BigDecimal campoDes;
//	private BigDecimal campox;
//	private BigDecimal campoy;
//	private BigDecimal campoRmag;
//	private BigDecimal campoeRmag;
//	private BigDecimal campoApRmag;
//	private BigDecimal campoApDRmag;
//	private String campoMCclass;
//	private BigDecimal campoMCz;
//	private BigDecimal campoUjMag;
//	private BigDecimal campoBjMag;
//	private BigDecimal campoVjMag;
//	private BigDecimal campousMag;
//	private BigDecimal campogsMag;
//	private BigDecimal camporsMag;
//	private BigDecimal campoUbMag;
//	private BigDecimal campoBbMag;
//	private BigDecimal campoVbMag;
//	private BigDecimal campoS280Mag;
	private String campoSeq;
	private String campoRah;
	private String campoRam;
	private String campoRas;
	private String campoDE;
	private String campoDed;
	private String campoDem;
	private String campoDes;
	private String campox;
	private String campoy;
	private String campoRmag;
	private String campoeRmag;
	private String campoApRmag;
	private String campoApDRmag;
	private String campoMCclass;
	private String campoMCz;
	private String campoUjMag;
	private String campoBjMag;
	private String campoVjMag;
	private String campousMag;
	private String campogsMag;
	private String camporsMag;
	private String campoUbMag;
	private String campoBbMag;
	private String campoVbMag;
	private String campoS280Mag;

	public static String showLineHeader() {
		StringBuilder build = new StringBuilder();
		build.append("Seq").append("\t");
		build.append("Rah").append("\t");
		build.append("Ram").append("\t");
		build.append("Ras").append("\t");
		build.append("DE-").append("\t");
		build.append("Ded").append("\t");
		build.append("Dem").append("\t");
		build.append("Des").append("\t");
		build.append("x").append("\t");
		build.append("y").append("\t");
		build.append("Rmag").append("\t");
		build.append("e_Rmag").append("\t");
		build.append("Ap_Rmag").append("\t");
		build.append("ApD_Rmag").append("\t");
		build.append("MC_class").append("\t");
		build.append("MC_z").append("\t");
		build.append("UjMag").append("\t");
		build.append("BjMag").append("\t");
		build.append("VjMag").append("\t");
		build.append("usMag").append("\t");
		build.append("gsMag").append("\t");
		build.append("rsMag").append("\t");
		build.append("UbMag").append("\t");
		build.append("BbMag").append("\t");
		build.append("VbMag").append("\t");
		build.append("S280Mag").append("\n");

		return build.toString();

	}
	public String showLine() {
		StringBuilder build = new StringBuilder();
		build.append(campoSeq).append("\t");
		build.append(campoRah).append("\t");
		build.append(campoRam).append("\t");
		build.append(campoRas).append("\t");
		build.append(campoDE).append("\t");
		build.append(campoDed).append("\t");
		build.append(campoDem).append("\t");
		build.append(campoDes).append("\t");
		build.append(campox).append("\t");
		build.append(campoy).append("\t");
		build.append(campoRmag).append("\t");
		build.append(campoeRmag).append("\t");
		build.append(campoApRmag).append("\t");
		build.append(campoApDRmag).append("\t");
		build.append(campoMCclass).append("\t");
		build.append(campoMCz).append("\t");
		build.append(campoUjMag).append("\t");
		build.append(campoBjMag).append("\t");
		build.append(campoVjMag).append("\t");
		build.append(campousMag).append("\t");
		build.append(campogsMag).append("\t");
		build.append(camporsMag).append("\t");
		build.append(campoUbMag).append("\t");
		build.append(campoBbMag).append("\t");
		build.append(campoVbMag).append("\t");
		build.append(campoS280Mag).append("\n");

		return build.toString();
	}
	
	public DatosGalaxiaResumidos( String aLine,Map<String,MetaDataArchivoTp1> meta) {
//		campoRah = new BigDecimal(aLine.substring(meta.get("Rah").getIni(),meta.get("Rah").getIni()).trim());
//		campoRam = new BigDecimal(aLine.substring(meta.get("Ram").getIni(),meta.get("Ram").getIni()).trim());
//		campoRas = new BigDecimal(aLine.substring(meta.get("Ras").getIni(),meta.get("Ras").getIni()).trim());
//		campoDE = new BigDecimal(aLine.substring(meta.get("DE-").getIni(),meta.get("DE-").getIni()).trim());
//		campoDed = new BigDecimal(aLine.substring(meta.get("Ded").getIni(),meta.get("Ded").getIni()).trim());
//		campoDem = new BigDecimal(aLine.substring(meta.get("Dem").getIni(),meta.get("Dem").getIni()).trim());
//		campoDes = new BigDecimal(aLine.substring(meta.get("Des").getIni(),meta.get("Des").getIni()).trim());
//		campox = new BigDecimal(aLine.substring(meta.get("x").getIni(),meta.get("x").getIni()).trim());
//		campoy = new BigDecimal(aLine.substring(meta.get("y").getIni(),meta.get("y").getIni()).trim());
//		campoRmag = new BigDecimal(aLine.substring(meta.get("Rmag").getIni(),meta.get("Rmag").getIni()).trim());
//		campoeRmag = new BigDecimal(aLine.substring(meta.get("e_Rmag").getIni(),meta.get("e_Rmag").getIni()).trim());
//		campoApRmag = new BigDecimal(aLine.substring(meta.get("Ap_Rmag").getIni(),meta.get("Ap_Rmag").getIni()).trim());
//		campoApDRmag = new BigDecimal(aLine.substring(meta.get("ApD_Rmag").getIni(),meta.get("ApD_Rmag").getIni()).trim());
//		campoMCclass = aLine.substring(meta.get("MC_class").getIni(),meta.get("MC_class").getIni()).trim();
//		campoMCz = new BigDecimal(aLine.substring(meta.get("MC_z").getIni(),meta.get("MC_z").getIni()).trim());
//		campoUjMag = new BigDecimal(aLine.substring(meta.get("UjMag").getIni(),meta.get("UjMag").getIni()).trim());
//		campoBjMag = new BigDecimal(aLine.substring(meta.get("BjMag").getIni(),meta.get("BjMag").getIni()).trim());
//		campoVjMag = new BigDecimal(aLine.substring(meta.get("VjMag").getIni(),meta.get("VjMag").getIni()).trim());
//		campousMag = new BigDecimal(aLine.substring(meta.get("usMag").getIni(),meta.get("usMag").getIni()).trim());
//		campogsMag = new BigDecimal(aLine.substring(meta.get("gsMag").getIni(),meta.get("gsMag").getIni()).trim());
//		camporsMag = new BigDecimal(aLine.substring(meta.get("rsMag").getIni(),meta.get("rsMag").getIni()).trim());
//		campoUbMag = new BigDecimal(aLine.substring(meta.get("UbMag").getIni(),meta.get("UbMag").getIni()).trim());
//		campoBbMag = new BigDecimal(aLine.substring(meta.get("BbMag").getIni(),meta.get("BbMag").getIni()).trim());
//		campoVbMag = new BigDecimal(aLine.substring(meta.get("VbMag").getIni(),meta.get("VbMag").getIni()).trim());
//		campoS280Mag = new BigDecimal(aLine.substring(meta.get("S280Mag").getIni(),meta.get("S280Mag").getIni()).trim());
		campoSeq = aLine.substring(meta.get("Seq").getIni(),meta.get("Seq").getEnd()).trim();
		campoRah = aLine.substring(meta.get("Rah").getIni(),meta.get("Rah").getEnd()).trim();
		campoRam = aLine.substring(meta.get("Ram").getIni(),meta.get("Ram").getEnd()).trim();
		campoRas = aLine.substring(meta.get("Ras").getIni(),meta.get("Ras").getEnd()).trim();
		campoDE = aLine.substring(meta.get("DE-").getIni(),meta.get("DE-").getEnd()).trim();
		campoDed = aLine.substring(meta.get("Ded").getIni(),meta.get("Ded").getEnd()).trim();
		campoDem = aLine.substring(meta.get("Dem").getIni(),meta.get("Dem").getEnd()).trim();
		campoDes = aLine.substring(meta.get("Des").getIni(),meta.get("Des").getEnd()).trim();
		campox = aLine.substring(meta.get("x").getIni(),meta.get("x").getEnd()).trim();
		campoy = aLine.substring(meta.get("y").getIni(),meta.get("y").getEnd()).trim();
		campoRmag = aLine.substring(meta.get("Rmag").getIni(),meta.get("Rmag").getEnd()).trim();
		campoeRmag = aLine.substring(meta.get("e_Rmag").getIni(),meta.get("e_Rmag").getEnd()).trim();
		campoApRmag = aLine.substring(meta.get("Ap_Rmag").getIni(),meta.get("Ap_Rmag").getEnd()).trim();
		campoApDRmag = aLine.substring(meta.get("ApD_Rmag").getIni(),meta.get("ApD_Rmag").getEnd()).trim();
		campoMCclass = aLine.substring(meta.get("MC_class").getIni(),meta.get("MC_class").getEnd()).trim();
		campoMCz = aLine.substring(meta.get("MC_z").getIni(),meta.get("MC_z").getEnd()).trim();
		campoUjMag = aLine.substring(meta.get("UjMag").getIni(),meta.get("UjMag").getEnd()).trim();
		campoBjMag = aLine.substring(meta.get("BjMag").getIni(),meta.get("BjMag").getEnd()).trim();
		campoVjMag = aLine.substring(meta.get("VjMag").getIni(),meta.get("VjMag").getEnd()).trim();
		campousMag = aLine.substring(meta.get("usMag").getIni(),meta.get("usMag").getEnd()).trim();
		campogsMag = aLine.substring(meta.get("gsMag").getIni(),meta.get("gsMag").getEnd()).trim();
		camporsMag = aLine.substring(meta.get("rsMag").getIni(),meta.get("rsMag").getEnd()).trim();
		campoUbMag = aLine.substring(meta.get("UbMag").getIni(),meta.get("UbMag").getEnd()).trim();
		campoBbMag = aLine.substring(meta.get("BbMag").getIni(),meta.get("BbMag").getEnd()).trim();
		campoVbMag = aLine.substring(meta.get("VbMag").getIni(),meta.get("VbMag").getEnd()).trim();
		campoS280Mag = aLine.substring(meta.get("S280Mag").getIni(),meta.get("S280Mag").getEnd()).trim();
	}

	public String getCampoSeq() {
		return campoSeq;
	}

	public void setCampoSeq(String campoSeq) {
		this.campoSeq = campoSeq;
	}

	public String getCampoRah() {
		return campoRah;
	}

	public void setCampoRah(String campoRah) {
		this.campoRah = campoRah;
	}

	public String getCampoRam() {
		return campoRam;
	}

	public void setCampoRam(String campoRam) {
		this.campoRam = campoRam;
	}

	public String getCampoRas() {
		return campoRas;
	}

	public void setCampoRas(String campoRas) {
		this.campoRas = campoRas;
	}

	public String getCampoDE() {
		return campoDE;
	}

	public void setCampoDE(String campoDE) {
		this.campoDE = campoDE;
	}

	public String getCampoDed() {
		return campoDed;
	}

	public void setCampoDed(String campoDed) {
		this.campoDed = campoDed;
	}

	public String getCampoDem() {
		return campoDem;
	}

	public void setCampoDem(String campoDem) {
		this.campoDem = campoDem;
	}

	public String getCampoDes() {
		return campoDes;
	}

	public void setCampoDes(String campoDes) {
		this.campoDes = campoDes;
	}

	public String getCampox() {
		return campox;
	}

	public void setCampox(String campox) {
		this.campox = campox;
	}

	public String getCampoy() {
		return campoy;
	}

	public void setCampoy(String campoy) {
		this.campoy = campoy;
	}

	public String getCampoRmag() {
		return campoRmag;
	}

	public void setCampoRmag(String campoRmag) {
		this.campoRmag = campoRmag;
	}

	public String getCampoeRmag() {
		return campoeRmag;
	}

	public void setCampoeRmag(String campoeRmag) {
		this.campoeRmag = campoeRmag;
	}

	public String getCampoApRmag() {
		return campoApRmag;
	}

	public void setCampoApRmag(String campoApRmag) {
		this.campoApRmag = campoApRmag;
	}

	public String getCampoApDRmag() {
		return campoApDRmag;
	}

	public void setCampoApDRmag(String campoApDRmag) {
		this.campoApDRmag = campoApDRmag;
	}

	public String getCampoMCclass() {
		return campoMCclass;
	}

	public void setCampoMCclass(String campoMCclass) {
		this.campoMCclass = campoMCclass;
	}

	public String getCampoMCz() {
		return campoMCz;
	}

	public void setCampoMCz(String campoMCz) {
		this.campoMCz = campoMCz;
	}

	public String getCampoUjMag() {
		return campoUjMag;
	}

	public void setCampoUjMag(String campoUjMag) {
		this.campoUjMag = campoUjMag;
	}

	public String getCampoBjMag() {
		return campoBjMag;
	}

	public void setCampoBjMag(String campoBjMag) {
		this.campoBjMag = campoBjMag;
	}

	public String getCampoVjMag() {
		return campoVjMag;
	}

	public void setCampoVjMag(String campoVjMag) {
		this.campoVjMag = campoVjMag;
	}

	public String getCampousMag() {
		return campousMag;
	}

	public void setCampousMag(String campousMag) {
		this.campousMag = campousMag;
	}

	public String getCampogsMag() {
		return campogsMag;
	}

	public void setCampogsMag(String campogsMag) {
		this.campogsMag = campogsMag;
	}

	public String getCamporsMag() {
		return camporsMag;
	}

	public void setCamporsMag(String camporsMag) {
		this.camporsMag = camporsMag;
	}

	public String getCampoUbMag() {
		return campoUbMag;
	}

	public void setCampoUbMag(String campoUbMag) {
		this.campoUbMag = campoUbMag;
	}

	public String getCampoBbMag() {
		return campoBbMag;
	}

	public void setCampoBbMag(String campoBbMag) {
		this.campoBbMag = campoBbMag;
	}

	public String getCampoVbMag() {
		return campoVbMag;
	}

	public void setCampoVbMag(String campoVbMag) {
		this.campoVbMag = campoVbMag;
	}

	public String getCampoS280Mag() {
		return campoS280Mag;
	}

	public void setCampoS280Mag(String campoS280Mag) {
		this.campoS280Mag = campoS280Mag;
	}
	
	
}
