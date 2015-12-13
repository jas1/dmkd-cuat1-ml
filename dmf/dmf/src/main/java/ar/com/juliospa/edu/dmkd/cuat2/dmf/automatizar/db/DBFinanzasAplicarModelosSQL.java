package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.db;

import java.util.List;

public class DBFinanzasAplicarModelosSQL {

	
	private List<String> modelosSQL;
	
	public DBFinanzasAplicarModelosSQL() {}
	
	
	public void aplicarModelosBase(){
	
		
		
	}
	
	public void gatherNodos(){
		
	}
	
	
	
	

	public List<String> getModelosSQL() {
		return modelosSQL;
	}

	/**
	 * para setear todos las carpetas con modelos a evaluar
	 * los archivos que se tendran en cuenta seran los que tienen
	 * reglas.sql
	 * 
	 * @param modelosSQL
	 */
	public void setModelosSQL(List<String> modelosSQL) {
		this.modelosSQL = modelosSQL;
	}
	

	
	
}
