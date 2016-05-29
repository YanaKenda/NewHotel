package modelPackage;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class ShowTableModel {

	private static String DB_CONNECTION = "jdbc:mysql://localhost:3306/hotel";
	private static String DB_USER = "root";
	private static String DB_PASSWORD = ""; 
	private static String tableName = "user";

	static {
		try{
			DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
		}catch(SQLException ex){
			throw new RuntimeException(ex);
		}
	}
	
	private static Connection getDBConnection() throws SQLException {
	    Connection dbConnection = null;
	    dbConnection = (Connection) DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
	    return dbConnection;
	}
	
	public static void setTableName(String newTableName){
		tableName = newTableName;
	}
	
	public static String getCurrentTableName(){
		return tableName;
	}
	
	public static int getNumberOfValues(String tableName) {
		int num = 0;
		try{
			String mysqlCommand = "SELECT" + " " + getTitles(tableName).get(0) + " " + "FROM" + " " + tableName;
			Connection dbConnection = getDBConnection();
			Statement statement = (Statement) dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(mysqlCommand); 
			while (rs.next()) {
		    	num++;
		    }	
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	    return num;
	}
	
	public static ArrayList<String>getTitles(String tableName){
		ArrayList<String>titles = new ArrayList<String>();
		try {
			String mysqlCommand = "SELECT * FROM" + " " +  tableName + " " + "WHERE 1 LIMIT 1";
		    Connection dbConnection = getDBConnection();
		    Statement statement = (Statement) dbConnection.createStatement();
		    ResultSet rs = statement.executeQuery(mysqlCommand); 
		    ResultSetMetaData data = (ResultSetMetaData) rs.getMetaData();
		    for(int i = 1; i < data.getColumnCount() + 1; i++) {
		    	titles.add(data.getColumnName(i));
		    }
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return titles;
	}
	
	public static ArrayList<String>getValues(String tableName){
		ArrayList<String>values = new ArrayList<String>();
		try {
			Connection dbConnection = getDBConnection();
			Statement statement = (Statement) dbConnection.createStatement();
			for(int i = 0; i < getTitles(tableName).size(); i++) {
				String mysqlCommand = "SELECT" + " " + getTitles(tableName).get(i) + " " + "FROM" + " " + tableName;
				ResultSet rs = statement.executeQuery(mysqlCommand); 
				while (rs.next()) {
			    	values.add(rs.getString(getTitles(tableName).get(i)));
			    }		
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return values;
	}
	
	public static ArrayList<String>getPrimaryKeys(String tableName) {
		ArrayList<String>primaryKeys = new ArrayList<String>();
		try {
			Connection dbConnection = getDBConnection();
			Statement statement = (Statement) dbConnection.createStatement();
			String mysqlCommand = "SELECT" + " " + getTitles(tableName).get(0) + " " + "FROM" + " " + tableName;
			ResultSet rs = statement.executeQuery(mysqlCommand); 
			while (rs.next()) {
				primaryKeys.add(rs.getString(getTitles(tableName).get(0)));
			}		
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return primaryKeys;
	}
	

	
	

}
