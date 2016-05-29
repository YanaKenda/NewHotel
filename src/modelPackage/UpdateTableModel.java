package modelPackage;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class UpdateTableModel {
	
	private static String DB_CONNECTION = "jdbc:mysql://localhost:3306/hotel";
	private static String DB_USER = "root";
	private static String DB_PASSWORD = ""; 

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
	
	public static ArrayList<String>getTitlesInfo(String tableName){
		ArrayList<String>titlesInfo = new ArrayList<String>();
		try {
			String mysqlCommand = "SELECT * FROM" + " " +  tableName + " " + "WHERE 1 LIMIT 1";
		    Connection dbConnection = getDBConnection();
		    Statement statement = (Statement) dbConnection.createStatement();
		    ResultSet rs = statement.executeQuery(mysqlCommand); 
		    ResultSetMetaData data = (ResultSetMetaData) rs.getMetaData();
		    for(int i = 1; i < data.getColumnCount() + 1; i++) {
		    	titlesInfo.add("Name:" + " " + data.getColumnName(i) + ";" + " " + 
		    			"Type:" + " " + data.getColumnTypeName(i) + "[" + data.getColumnDisplaySize(i) + "]");
		    }
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return titlesInfo;
	}
	
	public static void insertIntoTable(String tableName, ArrayList<String>values) {
		try {
			Connection dbConnection = getDBConnection();
			Statement statement = (Statement) dbConnection.createStatement();
			String insertTableSQL = "INSERT INTO" + " " + tableName +"(";
			for(int i = 0; i < ShowTableModel.getTitles(tableName).size() - 1; i++) {
				insertTableSQL += ShowTableModel.getTitles(tableName).get(i) + ',' + " ";
			}
			insertTableSQL += ShowTableModel.getTitles(tableName).get(ShowTableModel.getTitles(tableName).size() - 1) + ")" + " " + "VALUES" + "(";
			for(int i = 0; i < values.size() - 1; i++) {
				insertTableSQL += "'" + values.get(i) + "'" + "," + " "; 
			}
			insertTableSQL += "'" + values.get(values.size() - 1) + "'" + ")";
			
			statement.executeUpdate(insertTableSQL);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}	
	}
	
	public static void refreshTable(String tableName, ArrayList<String>values, String idValue) {
		try {
			Connection dbConnection = getDBConnection();
			Statement statement = (Statement) dbConnection.createStatement();
			String refreshTableSQL = "UPDATE" + " " + tableName + " " + "SET";
			for(int i = 0; i < ShowTableModel.getTitles(tableName).size() - 1; i++) {
				refreshTableSQL += " " + ShowTableModel.getTitles(tableName).get(i) + " = " + "'" + values.get(i) + "'" + ",";
			}
			refreshTableSQL += " " + ShowTableModel.getTitles(tableName).get(ShowTableModel.getTitles(tableName).size() - 1) + 
						" = " + "'" + values.get(values.size() - 1) + "'" + " " +  "WHERE" + " " + ShowTableModel.getTitles(tableName).get(0) + " " + "=" + " " + idValue;
			statement.execute(refreshTableSQL);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}	
	}
	
	public static void deleteFromTable(String tableName, String idValue) {
		try {
			Connection dbConnection = getDBConnection();
			Statement statement = (Statement) dbConnection.createStatement();
			String deleteTableSQL = "DELETE" + " " + "FROM" + " " + tableName + " " + 
					"WHERE" + " " + ShowTableModel.getTitles(tableName).get(0) + " " + "=" + " " + idValue;
		    statement.execute(deleteTableSQL);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}	
	}
	


}
