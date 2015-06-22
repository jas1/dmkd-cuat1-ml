package ar.com.juliospa.edu.dmkd.cuat1.dm.tp1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Regla {
//	lhs               rhs                support confidence     lift
	
	private String id;

	private String lhs;
	private String rhs;
	private String support;
	private String confidence;
	private String lift;
	
	public Regla(String linea ) {
		//id lhs               rhs                support confidence     lift
		//1 {BTP4S79-75RC} => {BTP4S79-75BLC} 0.02417417  0.8341969 29.39551
		//2 {BTP4S79-75SC} => {BTP4S79-75RC}  0.02147147  0.8411765 29.02713
		//3 {BTP4S79-75SC} => {BTP4S79-75BLC} 0.02042042  0.8000000 28.19048
		
		// nueva linea
		//9	{BOLSA DE DORMIR GRAVITY - 230 x 80 x 55,LUZ QUÍMICA AMARILLA VERDOSA} => {ANTIPARRAS PARA NIEVE}	0.0303030303030303	1	33
		
		String[] lineaSplit = linea.split("\t");
		id = lineaSplit[0];
		support = lineaSplit[2];
		confidence = lineaSplit[3];
		lift = lineaSplit[4];
		
		lhs = lineaSplit[1].substring(lineaSplit[1].indexOf("{")+1,lineaSplit[1].indexOf("}"));
		rhs = lineaSplit[1].substring(lineaSplit[1].lastIndexOf("{")+1,lineaSplit[1].lastIndexOf("}"));
		
	}

	/**
	 * @param linea
	 */
	private void parseViejo(String linea) {
		id = linea.substring(0,linea.indexOf(" "));
		lhs = linea.substring(linea.indexOf("{")+1,linea.indexOf("}"));
		rhs = linea.substring(linea.lastIndexOf("{")+1,linea.lastIndexOf("}"));
		// parte numeros
		String tmpNums =  linea.substring(linea.lastIndexOf("}")+1).trim();
		
		List<String> tmp = new ArrayList<String>();
		String[] tmpNums2 = tmpNums.split(" ");
		for (String string : tmpNums2) {
			if (string.trim().length() > 0) {
				tmp.add(string.trim());
			}
		}
		
		support = tmp.get(0);
		confidence = tmp.get(1);
		lift = tmp.get(2);
	}
	
	public Set<String> getAllIds(){
		Set<String> lista = new HashSet<String>();
		
		String[] lhsItm = lhs.split(",");
		String[] rhsItm =  rhs.split(",");
		
		for (String string : lhsItm) {
			lista.add(string);
		}
		
		for (String string : rhsItm) {
			lista.add(string);
		}
	
		return lista;
	}
	
	public String simple() {
		return id + " " + lhs + " "+ rhs+ " " + support + " "+ confidence+ " " + lift ;
	}
	public String tipoR () {
		return id + " {" + lhs + "} => {"+ rhs+ "}\t" + support + "\t"+ confidence+ "\t" + lift ;
	}	
	
	
	public String tipoExcel () {
		return id + "\t" + lhs + "\t"+ rhs+ "\t" + support + "\t"+ confidence+ "\t" + lift ;
	}
	
	public String getLhs() {
		return lhs;
	}
	public void setLhs(String lhs) {
		this.lhs = lhs;
	}
	public String getRhs() {
		return rhs;
	}
	public void setRhs(String rhs) {
		this.rhs = rhs;
	}
	public String getSupport() {
		return support;
	}
	public void setSupport(String support) {
		this.support = support;
	}
	public String getConfidence() {
		return confidence;
	}
	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}
	public String getLift() {
		return lift;
	}
	public void setLift(String lift) {
		this.lift = lift;
	}
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
}
