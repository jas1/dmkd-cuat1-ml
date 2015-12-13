package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.sql;

import ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol.UtilidadesGenerales;


public class ModelosSQLEjecutador {

	
	public static void ejecutarModelo(AutomatizarCorridasModeloJulioConfig config) {
			UtilidadesGenerales.ejecutarComandosSpss(config.getComandoSPSS());
			System.out.println(" El resultado es ver en la base que ids fueron inputados con que probabilidad para el periodo y si da ganancia o no.");
			System.out.println("select * from "+config.getTableInsertResultado()+" ;");
			System.out.println("select sum("+config.getModelGananciaVar()+") from "+config.getTableInsertResultado()+";");
	}
	
}
