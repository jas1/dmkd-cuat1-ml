package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.sql;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioConfig;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulioResultado;
import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.old.AutomatizarCorridasArbolJulio;

public class ArbolesSQLEjecutador {

	public static void ejecucionSpssIterativo(String outFolder, Integer[] maximaProfundidadList , Integer[] minParentSizeList, Integer[] minChildSizeList, String[] seed) {
		String tipoArbol = "CHAID";

		for (Integer maximaProfundidad : maximaProfundidadList) {
			for (Integer minParentSize : minParentSizeList) {
				for (Integer porcMinChildSize : minChildSizeList) {
					
					Integer minChildSize = Math.round(minParentSize * porcMinChildSize / 100);
					String experimentoNro = "expName_pr"+maximaProfundidad+"_par"+minParentSize+"_child"+minChildSize;
					
					for (String currentSeed : seed) {
						AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
						configArbol.setSeed(currentSeed);
//						configArbol.setOrigenDatosSav(origenDatos);
						configArbol.setTipoArbol(tipoArbol);
						configArbol.setOutputFolder(outFolder + experimentoNro + "/");
						configArbol.setMaximaProfundidad(maximaProfundidad);
						configArbol.setMinChildSize(minChildSize);
						configArbol.setMinParentSize(minParentSize);
						configArbol.setCosto(200L);
						configArbol.setGanancia(8000L);
						configArbol.setNormalizador(0.3);
						AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.ejecucionArbolVersionParametrizadoArbol(configArbol, AcumuladorComandosSpss.comandoArbolSQL7030ConVarsHistoria(configArbol));
						System.out.println(result.persistime());
					}
					
				}
			}
		}
	}
	
	public static void ejecucionSpssIterativoReducto(String outFolder, Integer[] maximaProfundidadList , Integer[] minParentSizeList, Integer[] minChildSizeList, String[] seed) {
		String tipoArbol = "CHAID";

		for (Integer maximaProfundidad : maximaProfundidadList) {
			for (Integer minParentSize : minParentSizeList) {
				for (Integer porcMinChildSize : minChildSizeList) {
					
					Integer minChildSize = Math.round(minParentSize * porcMinChildSize / 100);
					String experimentoNro = "expName_pr"+maximaProfundidad+"_par"+minParentSize+"_child"+minChildSize+"_r1";
					
					for (String currentSeed : seed) {
						AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
						configArbol.setSeed(currentSeed);
//						configArbol.setOrigenDatosSav(origenDatos);
						configArbol.setTipoArbol(tipoArbol);
						configArbol.setOutputFolder(outFolder + experimentoNro + "/");
						configArbol.setMaximaProfundidad(maximaProfundidad);
						configArbol.setMinChildSize(minChildSize);
						configArbol.setMinParentSize(minParentSize);
						configArbol.setCosto(200L);
						configArbol.setGanancia(8000L);
						configArbol.setNormalizador(0.3);
						AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.ejecucionArbolVersionParametrizadoArbol(configArbol, AcumuladorComandosSpss.comandoArbolSQL7030ConVarsHistoriaReducto1(configArbol));
						System.out.println(result.persistime());
					}
					
					String experimentoNro2 = "expName_pr"+maximaProfundidad+"_par"+minParentSize+"_child"+minChildSize+"_r2";
					for (String currentSeed : seed) {
						AutomatizarCorridasArbolJulioConfig configArbol = new AutomatizarCorridasArbolJulioConfig();
						configArbol.setSeed(currentSeed);
//						configArbol.setOrigenDatosSav(origenDatos);
						configArbol.setTipoArbol(tipoArbol);
						configArbol.setOutputFolder(outFolder + experimentoNro2 + "/");
						configArbol.setMaximaProfundidad(maximaProfundidad);
						configArbol.setMinChildSize(minChildSize);
						configArbol.setMinParentSize(minParentSize);
						configArbol.setCosto(200L);
						configArbol.setGanancia(8000L);
						configArbol.setNormalizador(0.3);
						AutomatizarCorridasArbolJulioResultado result = AutomatizarCorridasArbolJulio.ejecucionArbolVersionParametrizadoArbol(configArbol, AcumuladorComandosSpss.comandoArbolSQL7030ConVarsHistoriaReducto2(configArbol));
						System.out.println(result.persistime());
					}
					
				}
			}
		}
	}
	
}
