package ar.com.juliospa.edu.dmkd.cuat1.dm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ScriptsVariosLimpieza {

	
	@Test
	public void verificarNulls(){
		String conStr = "jdbc:mysql://172.16.1.226:3306/dm-tp1";
		String user = "dmkd";
		String pwd = "dmkd";
		String driverClassName = "com.mysql.jdbc.Driver";
		
		List<String> tablas = null;
		
	    tablas = buscaTablas(conStr, user, pwd, driverClassName, tablas);
	      
	    for (String tableName : tablas) {
	    	
		}
	}

	

	private List<String> buscaTablas(String conStr, String user, String pwd,
			String driverClassName, List<String> tablas) {
		String sql = "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'dm-tp1' ";
	    Connection conn = getConnection(driverClassName,conStr,user,pwd);
	    try {
	    	tablas = new ArrayList<String>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		     while(rs.next()){
		         //Retrieve by column name
		         String first = rs.getString("TABLE_NAME");
		         tablas.add(first);
		      }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tablas;
	}
	
	
	public Connection getConnection(String driverClassName,String conStr, String user, String pwd ){
	try {
		
		Class.forName(driverClassName).newInstance();

		Connection conn = DriverManager.getConnection(conStr,user,pwd);
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
