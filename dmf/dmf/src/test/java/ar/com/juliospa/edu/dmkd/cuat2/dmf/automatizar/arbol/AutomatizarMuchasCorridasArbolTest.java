package ar.com.juliospa.edu.dmkd.cuat2.dmf.automatizar.arbol;

import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioExplorandoParametrosBase;

/**
 * la idea de esta clase es testear las corridas grandes que son muchas y generan muchas carpetas
 * luego lo que queda es hacer un analisis de los resultados obtenidos.
 * @author julio
 *
 */
public class AutomatizarMuchasCorridasArbolTest {

	@Test
	public void testCampso() {
		
		for (int i = 1; i <= 37; i++) {
			if (i % 5 == 0) {
				System.out.println("campos"+i+", ");
			}else{
				System.out.print("campos"+i+", ");
			}
			
		}
		
		
	}
	
	/**
	 */
	@Test
	public void pruebaVariacionParametrosBasicosParaVerMejoresCombinaciones() {

		
		String origenDatos = "C:/Users/julio/Desktop/dmf_wd/nulos/DataSetAbril2015.sav";
		
		
//		1ra corrida se quedo duro en altura 9 estubo corriendo durante 1 dia
//		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/paramsBase/";
//		Integer[] maximaProfundidadList = { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
//		Integer[] minParentSizeList = { 10, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000 };
//		// cuenta que va a ser el porcentaje variable del current padre
//		Integer[] minChildSizeList = { 10, 25, 35, 50, 65, 75, 90 };
		
		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/paramsBase-v2/";
		// cambios: sacar lo que no fue rentable en 1ra corrida: arranco en altura 9
		Integer[] maximaProfundidadList = { 10,13, 15 };
		Integer[] minParentSizeList = {300, 400, 500, 750, 1000 };
		// cuenta que va a ser el porcentaje variable del current padre
		Integer[] minChildSizeList = { 65, 90 };
				
		AutomatizarCorridasArbolJulioExplorandoParametrosBase.ejecutarCorridasVariaParametrosBase(origenDatos,outFolder,maximaProfundidadList,minParentSizeList,minChildSizeList);
	}
	
	@Test
	public void pruebaVariacionParametrosBasicosParaVerMejoresCombinacionesAnalisisCarpeta() {
		String outFolder = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_2_dm_finanzas/java-spss-out/paramsBase-v2/";
		System.out.println(AutomatizarCorridasArbolJulioExplorandoParametrosBase.analizarResultados(outFolder));
	}
	
}
