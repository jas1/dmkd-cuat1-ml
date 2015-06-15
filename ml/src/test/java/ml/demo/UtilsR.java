package ml.demo;

import org.junit.Test;

public class UtilsR {

	@Test
	public void creameTodo() {
		for (int i = 1; i < 13; i++) {
		    System.out.println("col"+i+" <- as.data.frame(table(data["+i+"]))");
		    System.out.println("moda"+i+" <- col"+i+"$Var1[col"+i+"$Freq == max(col"+i+"$Freq ) ]");			
		}

		System.out.print("modaVec <- c( ");
		for (int i = 1; i < 14; i++) {
		    System.out.println("moda"+i+",");			
		}
		System.out.println(") ");
		

		System.out.print("map <- data.frame( ");
		for (int i = 1; i < 14; i++) {
		    System.out.print("m"+i+"=factor(0) ,");			
		}
		System.out.print(") ");
		
		for (int i = 1; i < 14; i++) {
		    System.out.println("map$m"+i+" <- moda"+i);			
		}
	}
}