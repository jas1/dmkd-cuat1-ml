package ar.com.juliospa.edu.dmkd.cuat2.dmf.model;


public class NodoArbolSQL extends NodoArbol {

	private String queryNodo;
	private String defaultSelect;
	private String defaultTabla;
	private String defaultGroupBy;
	
	public NodoArbolSQL() {
		super();
		
//		defaultSelect = "select clase, count(*) as cantidad, count(*)*200 as costo, IF(clase='BAJA+2',count(*)*8000,0) as ganancia ";
		defaultSelect = "select clase, count(*) as cantidad ";
		defaultTabla =  " from dm_finanzas_201504 ";
		defaultGroupBy = " group by clase;";
	}

	/**
	 * devuelve la query armada  defaultSelect + defaultTabla + queryNodo + defaultGroupBy
	 * @return
	 */
	public String getQuerySelect(){
		String tmp = defaultSelect + defaultTabla + queryNodo + defaultGroupBy;
		return tmp.replace("; group by clase;", " group by clase;");
	}

	public String getQueryNodo() {
		return queryNodo;
	}

	public void setQueryNodo(String queryNodo) {
		this.queryNodo = queryNodo;
	}

	public String getDefaultSelect() {
		return defaultSelect;
	}

	public void setDefaultSelect(String defaultSelect) {
		this.defaultSelect = defaultSelect;
	}

	public String getDefaultTabla() {
		return defaultTabla;
	}

	public void setDefaultTabla(String defaultTabla) {
		this.defaultTabla = defaultTabla;
	}

	public String getDefaultGroupBy() {
		return defaultGroupBy;
	}

	public void setDefaultGroupBy(String defaultGroupBy) {
		this.defaultGroupBy = defaultGroupBy;
	}
	
	
}
