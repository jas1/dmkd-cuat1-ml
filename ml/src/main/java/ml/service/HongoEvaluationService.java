package ml.service;

import java.lang.reflect.Field;

import ml.domain.EnumsRestringidas;
import ml.domain.Hongo;
import ml.domain.HongoEvaluated;

public class HongoEvaluationService {

	public static HongoEvaluated evaluar(Hongo hongo) {
		// ante la duda respondo que es venenoso, no queremos muertos
		return new HongoEvaluated(hongo, true);
	}
	
	public static HongoEvaluated randomEvaluation() {
		HongoEvaluated random = new HongoEvaluated(randomHongo(),EnumsRestringidas.getRandomPoison())  ;
		return random;
	}
	
	public static Hongo randomHongo() {
		Hongo random = new Hongo();
		random.color = EnumsRestringidas.getRandomColor();
		random.size = EnumsRestringidas.getRandomSize();
		random.airHumidity = EnumsRestringidas.getRandomAirHumidity();
		random.land = EnumsRestringidas.getRandomLand();
		random.shape = EnumsRestringidas.getRandomShape();
		random.texture = EnumsRestringidas.getRandomTexture();
		
		return random;
	}
	
	/**
	 * determinar la cantidad de hipotesis 
	 * @return
	 */
	public static int determinarHs(){
	 // cantidad de hipotesis = 1 + multiplica (var*opciones) de 1 a N
		// como estamos en OOP , para cada hongo hacemos que cada variable sea un field.
		// por eso obtenemos cada field para la clase.
		Field[] campos = Hongo.class.getDeclaredFields();
		// de cada var tenemos que considerar las opcioness, que eso estra restringido por el tipo de field 
		// en este caso una enum entonces tenemos en N particular
		int cuentaTotal = 1;
		int cuentaCampos = 1; 
		for (Field field : campos) {
			// para poder acceder
			field.setAccessible(true);
			
			if (field.isEnumConstant()) {
				
			}
			
			// cada field es una enum en este caso  
			 Class tmp = field.getType();
			 //
//			 Enum tmp2 = (Enum) tmp;
			 
			//cuentaCampos = cuenta campos * ( 1 *  )
			
		}
		
		
		return 0 ;
	}

}
