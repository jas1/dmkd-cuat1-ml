package ml.tp1;

import org.junit.Test;

import weka.classifiers.trees.J48;

public class WekaJ48ScriptsTP1 {


	@Test
	public void correrJ48Prueba() {
		
		String pathDatos;
		String datosName;
		
		J48 j48 = new J48();
	
		
		for (int i = 0; i < 0.5; i++) {
			//j48.buildClassifier();
			// ver: https://weka.wikispaces.com/Visualizing+a+Tree
			// ver: http://www.programcreek.com/java-api-examples/index.php?api=weka.classifiers.trees.J48
			// ver: http://www.programcreek.com/2013/01/a-simple-machine-learning-example-in-java/
			System.out.println(j48); 
		}
		
	
	}
	
}
