package ar.com.juliospa.edu.dmkd.cuat1.dmf;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class GananciaFromOutputSqlTest {

	@Test
	public void pruebaTodos() {
		try {
			String conStr = "jdbc:mysql://192.168.1.113:3306/dmf";
			String user = "dmkd";
			String pwd = "dmkd";
			String driverClassName = "com.mysql.jdbc.Driver";
			Connection con = getConnection(driverClassName, conStr, user, pwd);
			
			File[] files = (new File("C:/Users/julio/Desktop/dmf_wd/nulos").listFiles());
			for (File file : files) {
				if (file.getName().endsWith(".sql")) {
					System.out.println(GananciaFromOutputSQL.resumenArbolCompletoFromOutput(file.getAbsolutePath(), con,false));		
				}
			}
			
			Assert.assertTrue(true);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	@Test
	public void pruebaUnico() {
		try {
			String conStr = "jdbc:mysql://192.168.1.113:3306/dmf";
			String user = "dmkd";
			String pwd = "dmkd";
			String driverClassName = "com.mysql.jdbc.Driver";
			Connection con = getConnection(driverClassName, conStr, user, pwd);
			
			final String archivoPath = "C:/Users/julio/Desktop/dmf_wd/nulos/null_70.sql";
			System.out.println(GananciaFromOutputSQL.resumenArbolCompletoFromOutput(archivoPath, con,true));

			Assert.assertTrue(true);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	/**
	 * @param archivoPath
	 * @param con
	 * @throws IOException
	 * @throws SQLException
	 */
	
	
	public Connection getConnection(String driverClassName, String conStr,
			String user, String pwd) {
		try {
			Class.forName(driverClassName).newInstance();
			Connection conn = DriverManager.getConnection(conStr, user, pwd);
			return conn;
		} catch (InstantiationException e) {

			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {

			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return null;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}
	
}
