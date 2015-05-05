package ml;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ml.domain.Hongo;
import ml.domain.HongoEvaluated;
import ml.service.HongoEvaluationService;

/**
# la idea de este script es ver de practicar para ver un poco mas la idea de ML a la practica de modo " bruto "
# si sale buenisimo , si no sale bue ... valio la pena intentarlo,
# es ver otro punto de vista respecto de la forma  de programar
# esta funcion la pongo como inicial para correr el script, y poder ir definiendo nuevas funciones ayudadoras mas abajo
 * @author julio
 *
 */
public class HongosTest {


//    objetivo <-function(elemento){
//        #venenoso: si / no
//    }
//    
//    # de esa DB una buena practica es dividir los ya evaluados en
//    hongosDB <- loadHongosAnalizados()
//    # - datos de prueba
//    # - datos de entrenamiento  
//    
//    hongosDB
//
	
	/**
	 * random DB sin categorizar
	 * @return
	 */
	private List<HongoEvaluated> randomDB(int cant) {
		ArrayList<HongoEvaluated> result = new ArrayList<HongoEvaluated>();
		for (int i = 0; i < cant; i++) {
			result.add(randomizarHongo());
		}
		
		return result;
	}
	
	private HongoEvaluated randomizarHongo() {
		//randomu 
//		Random rand = new Random();
//		rand.
		return null;
	}

	/**
	 * #punto: dada una DB de hongos, la idea es aprender a identificar cuales son los venenosos 
	 * @param hongo
	 * @return
	 */
	private HongoEvaluated objetivo(Hongo hongo) {
		HongoEvaluated eval = HongoEvaluationService.evaluar(hongo);
		return eval;
	}
	
}
