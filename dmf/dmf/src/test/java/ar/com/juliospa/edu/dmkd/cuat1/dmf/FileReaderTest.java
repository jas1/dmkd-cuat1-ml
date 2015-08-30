package ar.com.juliospa.edu.dmkd.cuat1.dmf;

import java.nio.file.Files;

import org.junit.Test;

public class FileReaderTest {

	@Test
	public void fileReaderShowLine() {
		
		String path0="C:/Users/julio/Desktop/dmf_wd/titulos.txt";
		String path1="C:/Users/julio/Desktop/dmf_wd/producto_premium_2015.txt";
		String path2="C:/Users/julio/Desktop/dmf_wd/producto_premium_201504.txt";
				
		FileReader.sampleLine(path0);
		
		FileReader.sampleLine(path1);
		
		FileReader.sampleLine(path2);
		
		
	}
	
	@Test
	public void fileReaderShowLine0() {
		
		String path0="C:/Users/julio/Desktop/dmf_wd/producto_premium_201504.txt";

				
		FileReader.sampleLine(path0);

		
		
	}
	
	
	@Test
	public void fileReaderShowLine1() {
		
		String path0="C:/Users/julio/Desktop/dmf_wd/producto_premium_2015.txt";

				
		FileReader.sampleLine(path0);

		
		
	}
	
	@Test
	public void fileReaderShowLine2() {
		String path0="C:/Users/julio/Desktop/dmf_wd/titulos.txt";
		FileReader.sampleLine(path0);
	}
	
	@Test
	public void fileReadercols() {
		String path0="C:/Users/julio/Desktop/dmf_wd/titulos_2.txt";
		FileReader.empiezaCon(path0, "t");
	}
	
	@Test
	public void fileReaderBooleanizar() {
		String path0="C:/Users/julio/Desktop/dmf_wd/titulos_2.txt";
		FileReader.booleanizarCols(path0, "t");
		FileReader.booleanizarCols(path0, "Master_t");
		FileReader.booleanizarCols(path0, "Visa_t");
		FileReader.booleanizarCols(path0, "participa");
		
		
	}
	@Test
	public void fileReaderBooleanizar2() {
		String path0="C:/Users/julio/Desktop/dmf_wd/titulos_2.txt";
		FileReader.booleanizarCols(path0, "Master_t");
		FileReader.booleanizarCols(path0, "Visa_t");
	}
	@Test
	public void fileReaderBooleanizar3() {
		String path0="C:/Users/julio/Desktop/dmf_wd/titulos_2.txt";
		FileReader.booleanizarCols(path0, "Visa_t");
	}
}
