package ar.com.juliospa.edu.dmkd.cuat2.dmf.model;


public class NodoArbolSQLProbabilidades {

	private String comentario;
	private String update;
	private String set;
	private String where;
	
	public NodoArbolSQLProbabilidades() {}

	public NodoArbolSQLProbabilidades(NodoResultadoTablaNormalizada nodo, String comentario, String update, String set,
			String where) {
		this.comentario = comentario;
		this.update = update;
		this.set = set;
		this.where = where;
	}



	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getSet() {
		return set;
	}

	public void setSet(String set) {
		this.set = set;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}
}