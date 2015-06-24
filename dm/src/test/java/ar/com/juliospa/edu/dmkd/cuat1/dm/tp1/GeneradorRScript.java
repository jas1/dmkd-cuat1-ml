package ar.com.juliospa.edu.dmkd.cuat1.dm.tp1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.junit.Assert;
import org.junit.Test;

public class GeneradorRScript {

	final String ENCODE = "UTF-8";
	@Test
	public void generadorCorridasBase(){

		String destinoGenerado = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_1_data_mining/TP1/analisis_regiones_mes/generadoMesesRegion.r";
		
		Integer[] meses2015 = {1,3,4,5};
		Integer[] meses2014 = {3,4,5,6,7,8,9,10,11,12};
		Integer[] anios = {2014,2015};
		String[] regiones = {"Capital Federal","Buenos Aires", "Interior"};
		
		String paramMes ="${mes}";
		String paramAnio ="${anio}";
		String paramRegion ="${region}";
		String paramExtNomFile ="${extNomFile}";
		
		String query = "queryEjecutar <- \"select v.Venta_ID, p.DescGen from TP_Ventas_Prod vp left join Prod_Planos p on vp.Prod_ID= p.Prod_ID left join Ventas_Planas2 v on vp.Venta_ID = v.Venta_ID where v.mesVenta =${mes} AND v.anioVenta=${anio} AND v.region='${region}'\"\n";
		String nombreFile = "nombreFile <- 'analisis_regiones_mes/dmkd_dm_tp1_tx_venta_desgen_${extNomFile}'\n";
		
		String support =  "0.02";
		String conf =  "0.9";
		
		StringBuilder build = new StringBuilder();
		for (Integer anio : anios) {
			
			if (anio==2014) {
				for (Integer mes : meses2014) {
					for (String itemRegion : regiones) {
						String tmpNomFecha = anio.toString() + (mes < 10?"0"+mes: mes)+"_"+itemRegion.replace(" ","") ;
						String tmpNombreFileParams = tmpNomFecha + "_sup_"+support.replace(".", "-")+"_conf_"+conf.replace(".", "-");
						build.append("apriori").append(tmpNomFecha).append( "<- function (){ \n");
						build.append(query.replace(paramMes, mes.toString()).replace(paramAnio, anio.toString()).replace(paramRegion, itemRegion));
						build.append("coltx <- 'Venta_ID'\n");
						build.append("colprod <- 'DescGen'\n");
					    build.append(nombreFile.replace(paramExtNomFile, tmpNombreFileParams));
						build.append("support <- ").append(support).append("\n");
						build.append("confianza <- ").append(conf).append("\n");
						build.append("correrApriori(queryEjecutar,coltx, colprod,support, confianza, nombreFile )\n}\n");
					}
				}	
			}
			if (anio==2015) {
				for (Integer mes : meses2015) {
					for (String itemRegion : regiones) {
						String tmpNomFecha = anio.toString() + (mes < 10?"0"+mes: mes)+"_"+itemRegion.replace(" ","") ;
						String tmpNombreFileParams = tmpNomFecha + "_sup_"+support.replace(".", "-")+"_conf_"+conf.replace(".", "-");
						build.append("apriori").append(tmpNomFecha).append( "<- function (){ \n");
						build.append(query.replace(paramMes, mes.toString()).replace(paramAnio, anio.toString()).replace(paramRegion, itemRegion));
						build.append("coltx <- 'Venta_ID'\n");
						build.append("colprod <- 'DescGen'\n");
					    build.append(nombreFile.replace(paramExtNomFile, tmpNombreFileParams));
					    build.append("support <- ").append(support).append("\n");
					    build.append("confianza <- ").append(conf).append("\n");
						build.append("correrApriori(queryEjecutar,coltx, colprod,support, confianza, nombreFile )\n}\n");
						}
					}	
				}
			}
		writeToFile(build.toString(),ENCODE,destinoGenerado);
	}
	
	
	@Test
	public void recopilarResultados() throws Exception{
		String leerDesde = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_1_data_mining/TP1/analisis_regiones_mes/";
		File dirResultados = new File(leerDesde);
		File[] results = dirResultados.listFiles();
		
//		 la idea es a las reglas agergarle al final columa: periodo , y region, support de prueba y confidence de prueba y acumular todos los archivos en 1 solo
		StringBuilder build = new StringBuilder();
		for (File file : results) {
			if (file.getName().endsWith("_result.txt")) {
				analizarArchivo(file,build);
			}
		}
		
		String output = leerDesde + "totalizadorAnalisisRegionesMes.csv";
		
		writeToFile(build.toString(), ENCODE, output);
		
		
	}
	
	
	private void analizarArchivo(File file, StringBuilder build) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1252")); 
//	    desgen_201404_CapitalFederal_sup_0-015_conf_0-9
	    
	    String strTmp = file.getName().substring(file.getName().indexOf("desgen_")+7,file.getName().indexOf("_result"));
	    String confianza = strTmp.substring(strTmp.indexOf("conf_")+5).replace("-", ".");
	    String support = strTmp.substring(strTmp.indexOf("sup_")+4,strTmp.indexOf("_conf")).replace("-", ".");;
	    String periodo = strTmp.substring(0,strTmp.indexOf("_"));
	    String region = strTmp.substring(strTmp.indexOf("_")+1,strTmp.indexOf("_sup_"));
	    
	    try {
	        String line = br.readLine();

	        while (line != null) {
	        	build.append(line).append("\t").append(periodo).append("\t").append(region).append("\t").append(confianza).append("\t").append(support);
	        	build.append(System.lineSeparator());
	            line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
	}


	private void writeToFile(String stringToWrite, final String encoding,String pathYNombre) {
		
        Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathYNombre), encoding));
			writer.write(stringToWrite);
			System.out.println("output en: "+pathYNombre);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Assert.fail();
				}
			}
		}
	}
	
}
