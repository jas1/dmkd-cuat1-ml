package ar.com.juliospa.edu.dmkd.cuat1.dmf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

import ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.AutomatizarCorridasArbolJulio;

/**
 * la idea de esta clase es : 
 * poder filtrar rapido los nombres de las columnas para hacer un subset de los tipos de columnas que existen
 * para luego caracterizar en R
 * como son muchas dividimos por tipos y analizamos cada tipo
 * @author julio
 *
 */
public class CaracterizacionRHelper {

	@Test	
	public void genrarCosoColumno() {
//		armarOutputR
		
		
		String[] historiaActual = new String[]{"201304","201305","201306","201307","201308","201309","201310","201311","201312","201401","201402","201403","201404","201405","201406","201407","201408","201409","201410","201411","201412","201501","201502","201503","201504","201505","201506"};
		
		StringBuilder build = new StringBuilder();
		
		for (String periodo : historiaActual) {
			build.append("alt").append(periodo).append("<-  resumenSinVarianza(con,'select * from dmf_historia_").append(periodo).append("','").append(periodo).append("')").append("\n");
			build.append("tmp").append(periodo).append( "<- length(sinVarPeriodo[,1]) - length(alt").append(periodo).append("[,1])").append("\n");
			build.append("sinVarPeriodo$'").append(periodo).append("' <- c ( as.character(alt").append(periodo).append("[,1]), 1:tmp").append(periodo).append(")").append("\n");
		}
		
		System.out.println(build.toString());
		

	}
	

	
	/**
	 * porque en DB ya filtre los tipos de columnas
	 */
	@Test
	public void caracterizacionConDB() {
		final String separator = "\t";
		final String filtro0 = "monto";
		final String filtro1 = "cantidad";
		final String filtro2 = "tiene";
		final String filtro3 = "varios";
		try {
			
//			
//			System.out.println(tipoPorQueryDb(separator, "monto"));
//			System.out.println(tipoPorQueryDb(separator, "cantidad"));
//			System.out.println(tipoPorQueryDb(separator, "tiene"));
//			System.out.println(tipoPorQueryDb(separator, "varios"));

			System.out.println(armarConsultaR(tipoPorQueryDb(separator, filtro0),filtro0));
			System.out.println(armarConsultaR(tipoPorQueryDb(separator, filtro1),filtro1));
			System.out.println(armarConsultaR(tipoPorQueryDb(separator, filtro2),filtro2));
			System.out.println(armarConsultaR(tipoPorQueryDb(separator, filtro3),filtro3));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String armarConsultaR(String tipoPorQueryDb,String filtro) {
		 
		String result = tipoPorQueryDb + "\n";
		String summaryName = "summary"+filtro;
		result += summaryName+" <- summary(currentData[,filter"+filtro+"]) \n";
		result += "write.csv("+summaryName+",file = '"+AutomatizarCorridasArbolJulio.getTimeStamp(null,null)+"variables_abril_"+summaryName+".txt',row.names=FALSE)\n";
		return result;
	}

	/**
	 * @param separator
	 * @param filtro
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	private String tipoPorQueryDb(final String separator, final String filtro) throws Exception, SQLException {
		Connection con = getConnection();
//			COLUMN_NAME, tipocol tipomonto
		String query = "select * from columnas_201504_con_booleans where tipocol ='"+filtro+"'";
		ResultSet result = obtenerResultset(query,con);
		StringBuilder build = new StringBuilder();
		while (result.next()) {
			// P.Prod_ID, P.DescGen, P.DescAdic, P.Marca, P.Proveedor,
			// P.CantEnvase, CAT.Cat_Desc AS categoria, SC.SubCat_Desc as
			// subcategoria , PS.Precio AS precio
			String colname = result.getString("COLUMN_NAME");
			String tipocol = result.getString("tipocol");
			String tipomonto = result.getString("tipomonto");
		
			build.append(colname).append(separator);
//				build.append(tipocol).append(separator);
//				build.append(tipomonto);
		}
		
		String tmp = build.toString().replace(separator, "','");
		String rCommand = "filter" + filtro+" <- c('" + tmp.substring(0,tmp.length()-2) + ")";
		return rCommand;
	}
	
/*  -------------------------------------------------------------------------------------- */
	/**
	 * este es para identificar las tiene
	 */
	@Test
	public void fileReadercolsTipoTiene() {
		String path0="C:/Users/julio/Desktop/dmf_wd/titulos_2.txt";
		final String nombreFiltroR = "Tiene";
		final String separator = "\t";
		final String empiezaCon = "t";
		
		String rCommand = filtroComandoR(path0, nombreFiltroR, separator, empiezaCon);
		System.out.println(rCommand);
//		
//		for (String string : splited) {
//			System.out.println(string);
//		}
	}
	
	@Test
	public void fileReadercolsTipoTodos() {
		String path0="C:/Users/julio/Desktop/dmf_wd/titulos_2.txt";
		final String nombreFiltroR = "Tiene";
		final String separator = "\t";
		final String empiezaCon = "t";
		
		
		
		String rCommand = filtroComandoR(path0, nombreFiltroR, separator, empiezaCon);
		System.out.println(rCommand);
		
		
		String mpath="C:/Users/julio/Desktop/dmf_wd/titulos_2.txt";
		final String mnombreFiltroR = "Monto";
		final String mseparator = "\t";
		final String mempiezaCon = "m";
		
		String mrCommand = filtroComandoR(mpath, mnombreFiltroR, mseparator, mempiezaCon);
		System.out.println(mrCommand);
		
		
		String cpath="C:/Users/julio/Desktop/dmf_wd/titulos_2.txt";
		final String cnombreFiltroR = "Cant";
		final String cseparator = "\t";
		final String cempiezaCon = "c";
		
		String crCommand = filtroComandoR(cpath, cnombreFiltroR, cseparator, cempiezaCon);
		System.out.println(crCommand);
		
		String masterpath="C:/Users/julio/Desktop/dmf_wd/titulos_2.txt";
		final String masternombreFiltroR = "Master";
		final String masterseparator = "\t";
		final String masterempiezaCon = "Master";
		
		String masterrCommand = filtroComandoR(masterpath, masternombreFiltroR, masterseparator, masterempiezaCon);
		System.out.println(masterrCommand);
		
		String visapath="C:/Users/julio/Desktop/dmf_wd/titulos_2.txt";
		final String visanombreFiltroR = "Visa";
		final String visaseparator = "\t";
		final String visaempiezaCon = "Visa";
		
		String visarCommand = filtroComandoR(visapath, visanombreFiltroR, visaseparator, visaempiezaCon);
		System.out.println(visarCommand);
		
//		
//		for (String string : splited) {
//			System.out.println(string);
//		}
	}

	/**
	 * @param path0
	 * @param nombreFiltroR
	 * @param separator
	 * @param empiezaCon
	 * @return
	 */
	private String filtroComandoR(String path0, final String nombreFiltroR, final String separator, final String empiezaCon) {
		StringBuilder build = FileReader.empiezaConBuilder(path0, empiezaCon,separator); 
		
		String tmp = build.toString().replace(separator, "','");

		String rCommand = "filter" + nombreFiltroR+" <- c('" + tmp.substring(0,tmp.length()-2) + ")";
		return rCommand;
	}

	private Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			throw e;
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://192.168.1.113:3306/dmf", "dmkd", "dmkd");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			throw e;
		}
		if (connection != null) {
			return connection;
		} else {
			throw new Exception("Failed to make connection!");
		}
	}
	
	private static ResultSet obtenerResultset(String query, Connection con) throws SQLException {
		PreparedStatement preparedStatementProductos = con.prepareStatement(query);
		ResultSet resultSet = preparedStatementProductos.executeQuery();
		return resultSet;
	}

}



