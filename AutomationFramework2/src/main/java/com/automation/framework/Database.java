package com.automation.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database extends SuperAction{
	private static String dbServer="gx-zwesqld025.database.windows.net";
	private static String userName="service";
	private static String password="AzureApp$ervice";
	
	public static Connection connect(String dbName)throws Throwable{
		String connectionString=
				"jdbc:sqlserver://"+dbServer+":1433;"
				+"database="+dbName+";"
				+"user="+userName+";"
				+"password="+password+";"
				+"encrypt=true;"
				+"trustServerCertificate=false;"
				+"hostNameInCertificate=*.database.windows.net;"
				+"loginTimeout=30;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn= DriverManager.getConnection(connectionString);
			return conn;
		}catch(SQLException e) {
			catchErrorWithMessage(e, "DB connection fails");
		}
		return null;
	}
	
	public static boolean execute(Connection conn,String sqlQuery) throws Throwable{
		Statement statement= conn.createStatement();
		boolean result=statement.execute(sqlQuery);
		return result;
	}
	
	public static int executeUpdate(Connection conn,String sqlQuery)throws Throwable{
		Statement statement =conn.createStatement();
		int rows=statement.executeUpdate(sqlQuery);
		return rows;
	}
	
	public static List<Map<String,String>> executeQuery(Connection conn, String sqlQuery)throws Throwable{
		Statement statement = conn.createStatement();
		ResultSet  rs=statement.executeQuery(sqlQuery);
		ResultSetMetaData rsmd=rs.getMetaData();
		int columnCount=rsmd.getColumnCount();
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		
		while(rs.next()) {
			Map<String,String> result=new HashMap<String, String>();
			for(int i=1;i<=columnCount;i++) {
				String name=rsmd.getColumnName(i);
				result.put(name, rs.getString(name));
			}
		results.add(result);
		}
		return results;
	}
}
