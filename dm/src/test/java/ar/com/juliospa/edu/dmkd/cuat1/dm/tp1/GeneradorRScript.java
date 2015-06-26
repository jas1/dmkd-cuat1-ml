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
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class GeneradorRScript {

	
	@Test
	public void recopilarResultadosClientesMonto() throws Exception{
		String leerDesde = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_1_data_mining/TP1/analisis_monto_clientes/";
		File dirResultados = new File(leerDesde);
		File[] results = dirResultados.listFiles();
		
//		 la idea es a las reglas agergarle al final columa: periodo , y region, support de prueba y confidence de prueba y acumular todos los archivos en 1 solo
		StringBuilder build = new StringBuilder();
		for (File file : results) {
			if (file.getName().endsWith("_result.txt")) {
				analizarArchivoMonto(file,build);
			}
		}
		
		String output = leerDesde + "totalizadorClientesMonto.csv";
		
		writeToFile(build.toString(), ENCODE, output);
	}	
	
	private void analizarArchivoMonto(File file, StringBuilder build) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1252")); 
//	    dmkd_dm_tp1_tx_venta_desgen_monto_ventas_alto_sup_0-0045_conf_0-9
	    
	    String strTmp = file.getName().substring(file.getName().indexOf("desgen_monto_ventas_")+20,file.getName().indexOf("_result"));
	    String confianza = strTmp.substring(strTmp.indexOf("conf_")+5).replace("-", ".");
	    String support = strTmp.substring(strTmp.indexOf("sup_")+4,strTmp.indexOf("_conf")).replace("-", ".");;
	    String cantidadVentas = strTmp.substring(0,strTmp.indexOf("_"));
	    try {
	        String line = br.readLine();

	        while (line != null) {
//	        	10	{3 STREAMERS_12,5 NINFAS SECAS_12} => {4 NINFAS SECAS_12}	0.0012012012012012	1	555
//	        	String sacadorTipoCliente = line.substring(line.indexOf("_")+1,line.indexOf(","));
	        	build.append(line).append("\t").append(cantidadVentas).append("\t").append(confianza).append("\t").append(support);
	        	build.append(System.lineSeparator());
	            line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
	}
	
	
	@Test
	public void recopilarResultadosClientePeriodo() throws Exception{
		String leerDesde = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_1_data_mining/TP1/analisis_cliente_mes_compara/";
		File dirResultados = new File(leerDesde);
		File[] results = dirResultados.listFiles();
		
//		 la idea es a las reglas agergarle al final columa: periodo , y region, support de prueba y confidence de prueba y acumular todos los archivos en 1 solo
		
		
		// cuentas
		Map<String, Long> archivoCantLineas = new HashMap<String, Long>();
		for (File file : results) {
			if (file.getName().startsWith("dmkd_dm_tp1_tx_venta_desgen_clientes_periodo_") && !file.getName().endsWith("_result.txt")) {
				archivoCantLineas.put(file.getName(), analizarLineasArchivoClientePeriodo(file));
			}
		}
		
		
		StringBuilder build = new StringBuilder();
		for (File file : results) {
			if (file.getName().endsWith("_result.txt")) {
				analizarArchivoClientePeriodo(file, build,archivoCantLineas);
			}
		}
		
		String output = leerDesde + "totalizadorClientePeriodo.csv";
		
		writeToFile(build.toString(), ENCODE, output);
	}
	
	private long analizarLineasArchivoClientePeriodo(File file) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1252"));
	    Long count = 0L;
	    try {
	        String line = br.readLine();
	        
	        while (line != null) {
	        	if (!line.contains("Venta_ID") &&  line.trim().length() > 0) {
	        		count++;	
				}
	        	
	        	line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
	    return count;
	}
	
	private void analizarArchivoClientePeriodo(File file, StringBuilder build, Map<String, Long> archivoCantLineas) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1252")); 
//	    dmkd_dm_tp1_tx_venta_desgen_clientes_periodo_medio_201503_sup_0-045_conf_0-9.txt
	    
//	    get current : archivoCantLineas 
	    Long currentLineas = archivoCantLineas.get(file.getName().replace("_result", ""));
	    
	    String strTmp = file.getName().substring(file.getName().indexOf("venta_desgen_clientes_periodo_")+30,file.getName().indexOf("_result"));
	    String confianza = strTmp.substring(strTmp.indexOf("conf_")+5).replace("-", ".");
	    String support = strTmp.substring(strTmp.indexOf("sup_")+4,strTmp.indexOf("_conf")).replace("-", ".");;
	    String cantidadVentas = strTmp.substring(0,strTmp.indexOf("_"));
	    String periodo = strTmp.substring(strTmp.indexOf("_")+1,strTmp.indexOf("_")+7);
	    try {
	        String line = br.readLine();

	        while (line != null) {
//	        	10	{3 STREAMERS_12,5 NINFAS SECAS_12} => {4 NINFAS SECAS_12}	0.0012012012012012	1	555 y le agrego la cantidad de lienas actual
//	        	String sacadorTipoCliente = line.substring(line.indexOf("_")+1,line.indexOf(","));
	        	build.append(line).append("\t").append(cantidadVentas).append("\t").append(confianza).append("\t").append(support).append("\t").append(currentLineas).append("\t").append(periodo);
	        	build.append(System.lineSeparator());
	            line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
	}
	
	
	@Test
	public void asd() {
		String asd = "venta_desgen_clientes_periodo_";
		System.out.println(asd.length());
	}
	
	@Test
	public void recopilarResultadosClientes() throws Exception{
		String leerDesde = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_1_data_mining/TP1/analisis_cantidad_monto_clientes/";
		File dirResultados = new File(leerDesde);
		File[] results = dirResultados.listFiles();
		
//		 la idea es a las reglas agergarle al final columa: periodo , y region, support de prueba y confidence de prueba y acumular todos los archivos en 1 solo
		StringBuilder build = new StringBuilder();
		for (File file : results) {
			if (file.getName().endsWith("_result.txt")) {
				analizarArchivoPeriodosCantidad(file,build);
			}
		}
		
		String output = leerDesde + "totalizadorAnalisisRegionesMes.csv";
		
		writeToFile(build.toString(), ENCODE, output);
	}	
	
	private void analizarArchivoPeriodosCantidad(File file, StringBuilder build) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1252")); 
//	    dmkd_dm_tp1_tx_venta_desgen_cantidad_ventas_alto_sup_0-0025_conf_0-9_result
	    
	    String strTmp = file.getName().substring(file.getName().indexOf("desgen_cantidad_ventas_")+23,file.getName().indexOf("_result"));
	    String confianza = strTmp.substring(strTmp.indexOf("conf_")+5).replace("-", ".");
	    String support = strTmp.substring(strTmp.indexOf("sup_")+4,strTmp.indexOf("_conf")).replace("-", ".");;
	    String cantidadVentas = strTmp.substring(0,strTmp.indexOf("_"));
	    
	    try {
	        String line = br.readLine();

	        while (line != null) {
	        	build.append(line).append("\t").append(cantidadVentas).append("\t").append(confianza).append("\t").append(support);
	        	build.append(System.lineSeparator());
	            line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
	}
	
	
	
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
				analizarArchivoPeriodosMensuales(file,build);
			}
		}
		
		String output = leerDesde + "totalizadorAnalisisRegionesMes.csv";
		
		writeToFile(build.toString(), ENCODE, output);
		
		
	}
	

	
	private void analizarArchivoPeriodosMensuales(File file, StringBuilder build) throws Exception {
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
