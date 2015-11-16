package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

public abstract class ArbolFileSources {
	
	public static final String DM_FINANZAS_201504_TARJETAS_HISTO_AVG = "dm_finanzas_201504_con_avg_histo_tarj.sav";
	public static final String DM_FINANZAS_201503_TARJETAS_HISTO_AVG = "dm_finanzas_201503_con_avg_histo_tarj.sav";
	public static final String DM_FINANZAS_201502_TARJETAS_HISTO_AVG = "dm_finanzas_201502_con_avg_histo_tarj.sav";
	public static final String DM_FINANZAS_201501_TARJETAS_HISTO_AVG = "dm_finanzas_201501_con_avg_histo_tarj.sav";
	public static final String DM_FINANZAS_201412_TARJETAS_HISTO_AVG = "dm_finanzas_201412_con_avg_histo_tarj.sav";
	public static final String DM_FINANZAS_201411_TARJETAS_HISTO_AVG = "dm_finanzas_201411_con_avg_histo_tarj.sav";
	public static final String DM_FINANZAS_201504_TARJETAS = "dm_finanzas_201504_con_booleans_depurado-var-paquete_add_tarjeta.sav";
	public static final String DM_FINANZAS_201504_CON_BOOLEANS_DEPURADO_VAR_PAQUETE_SAV = "dm_finanzas_201504_con_booleans_depurado-var-paquete.sav";
	//	private static final String DATA_SET_ABRIL2015_SAV = "DataSetAbril2015.sav";
	public static final String DATA_SET_ABRIL2015_SAV = "dm_finanzas_201504_RAW_post_problema_decimales.sav";
	//	private final String userFolder = "C:/Users/jspairani";
	public static final String userFolder = "C:/Users/julio";
	/**
	 * @param maximaProfundidadList
	 * @param minParentSizeList
	 * @param minChildSizeList
	 * @param seed
	 */
	public static void complexCalc(Integer[] maximaProfundidadList, Integer[] minParentSizeList, Integer[] minChildSizeList, String[] seed) {
		int currentCompl = maximaProfundidadList.length * minParentSizeList.length * minChildSizeList.length * seed.length;
				
		System.out.println("Current Complejidad: " + currentCompl );
	}
	public static int complexCalc(Integer[] maximaProfundidadList, Integer[] minParentSizeList, Integer[] minChildSizeList, String[] seed, String[] files) {
		int currentCompl = maximaProfundidadList.length * minParentSizeList.length * minChildSizeList.length * seed.length * files.length;
		
		System.out.println("Current Complejidad: " + currentCompl );
		return currentCompl;
	}
	
	public static int complexCalcConTiempo(Integer[] maximaProfundidadList, Integer[] minParentSizeList, Integer[] minChildSizeList, String[] seed, String[] files,int tiempoEstimadoPromedioCorridaArbolMinutos) {
		int currentCompl = maximaProfundidadList.length * minParentSizeList.length * minChildSizeList.length * seed.length * files.length;
		
		System.out.println("Current Complejidad: " + currentCompl );
		
		int minutos = tiempoEstimadoPromedioCorridaArbolMinutos*currentCompl;
		double horas = minutos / 60;
		
		System.out.println("tiempo estimado corrida: "+ minutos +" minutos => "+ horas + " horas");
		return currentCompl;
	}

	
}