package ml.service;

import ml.domain.Hongo;
import ml.domain.HongoEvaluated;

public class HongoEvaluationService {

	public static HongoEvaluated evaluar(Hongo hongo) {
		// ante la duda respondo que es venenoso, no queremos muertos
		return new HongoEvaluated(hongo, true);
	}

}
