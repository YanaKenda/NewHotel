package modelPackage;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ShowAllTablesModel {
	
	private static String DB_CONNECTION = "jdbc:mysql://localhost:3306/hotel";
	private static String DB_USER = "root";
	private static String DB_PASSWORD = ""; 
	private static String MYSQL_COMMAND = "SHOW TABLES";
	
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
	
	public static ArrayList<String>getTables(){
		ArrayList<String>tables = new ArrayList<String>();
		try {
			Connection dbConnection = getDBConnection();
			Statement statement = (Statement) dbConnection.createStatement();
			String mysqlCommand = MYSQL_COMMAND;
			ResultSet rs = statement.executeQuery(mysqlCommand); 
			while (rs.next()) {
			    tables.add(rs.getString(1));
			}		
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return tables;
	}

}
