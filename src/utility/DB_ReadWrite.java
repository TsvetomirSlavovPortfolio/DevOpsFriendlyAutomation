package utility;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;

import utility.Log;
import utility.Constant;

public class DB_ReadWrite 
{
	static Connection dbConnection = null;
	static Statement statement = null;
	
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@"+Constant.ENV_Host+":1521:paasdb";
	private static final String DB_USER = "pcicrm1";
	private static final String DB_PASSWORD = "pcicrm1";
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static void main(String[] argv) 
	{
		try
		{
			PropertyConfigurator.configure("log4j.properties");
			selectRecords_TB_TABLE_Customer("Select CUSTOMER_ID,NAME,TYPE,SUBTYPE from TABLE_Customer where CUSTOMER_ID='100000009'"); // Retrieving Records Using Statement Class
			selectRecords_PreparedStatement(); // Retrieving Records Using PreparedStatement Class

		} catch (SQLException e) 
		{
			e.printStackTrace();
			Log.error(e.getMessage());
		}
	}

	
	public static Map<String, String> selectRecords_TB_TABLE_Customer(String sql) throws SQLException 
	{
		ResultSet rs=null;
	    Map<String,String> customer_Details_Map_DB = new HashMap<String,String>();
		//String sql = "Select CUSTOMER_ID,NAME,TYPE,SUBTYPE from TABLE_Customer where CUSTOMER_ID='100000009'";
		try 
		{
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			Log.info("\n---------------------------------********* CRM DB Retrieve Value *********---------------------------------");
			Log.info("Retrieving Records Using Statement from CRM DB where Table name is  TABLE_Customer");
			Log.info("SQL : "+sql);

			rs = statement.executeQuery(sql);

			while (rs.next()) 
			{				
				customer_Details_Map_DB.put("CUSTOMER_ID",rs.getString("CUSTOMER_ID"));
				customer_Details_Map_DB.put("NAME",rs.getString("NAME"));
				customer_Details_Map_DB.put("TYPE",rs.getString("TYPE"));
				customer_Details_Map_DB.put("SUBTYPE",rs.getString("SUBTYPE"));			    	
			}
			return customer_Details_Map_DB;

		} catch (SQLException e) {
			e.printStackTrace();
			Log.error(e.getMessage());

		} finally 		{
			if (statement != null) {statement.close();}
			if (dbConnection != null) {dbConnection.close();}
		}
		return customer_Details_Map_DB;
	}
	
	
	public static void selectRecords_PreparedStatement() throws SQLException 
	{
		PreparedStatement preparedStatement = null;
		String selectSQL = "Select CUSTOMER_ID,NAME,TYPE,SUBTYPE from TABLE_Customer where CUSTOMER_ID = ?";

		try 
		{
			dbConnection = getDBConnection();
			System.out.println("\nRetrieving Records Using PreparedStatement Class");
			System.out.println("SQL : "+selectSQL);
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, 100000009);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{

				String userID = rs.getString("CUSTOMER_ID");
				String userName = rs.getString("NAME");
				String type = rs.getString("TYPE");
				String subType = rs.getString("SUBTYPE");

				Log.info("userID : " + userID);
				Log.info("userName : " + userName);
				Log.info("type : " + type);
				Log.info("subType : " + subType);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			Log.error(e.getMessage());

		}  finally 		{
			if (statement != null) {statement.close();}
			if (dbConnection != null) {dbConnection.close();}
		}
	}
	
	
	
	
	public static void deleteRecord() throws SQLException 
	{
		String deleteTableSQL = "DELETE DBUSER WHERE USER_ID = 1";
		try 
		{
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(deleteTableSQL);
			statement.execute(deleteTableSQL);

			System.out.println("Record is deleted from DBUSER table!");

		} catch (SQLException e) 		
		{
			e.printStackTrace();
			Log.error(e.getMessage());

		} finally 		{
			if (statement != null) {statement.close();}
			if (dbConnection != null) {dbConnection.close();}
		}
	}

	public static void updateRecord() throws SQLException 
	{
		String updateTableSQL = "UPDATE DBUSER"
				+ " SET USERNAME = 'mkyong_new' "
				+ " WHERE USER_ID = 1";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(updateTableSQL);

			// execute update SQL stetement
			statement.execute(updateTableSQL);

			System.out.println("Record is updated to DBUSER table!");

		} catch (SQLException e) {
			Log.error(e.getMessage());

		} finally 		{
			if (statement != null) {statement.close();}
			if (dbConnection != null) {dbConnection.close();}
		}

	}
	
	public static void insertRecord() throws SQLException 
	{
		String insertTableSQL = "INSERT INTO DBUSER"
				+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
				+ "(1,'mkyong','system', " + "to_date('"
				+ getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);

			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {
			Log.error(e.getMessage());

		}finally 		{
			if (statement != null) {statement.close();}
			if (dbConnection != null) {dbConnection.close();}
		}

	}

	
	private static String getCurrentTimeStamp() 
	{

		java.util.Date today = new java.util.Date();
		return dateFormat.format(today.getTime());

	}
	
	private static Connection getDBConnection() 
	{
		try 
		{
			Class.forName(DB_DRIVER);
			dbConnection = DriverManager.getConnection( DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
		} catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			Log.error(e.getMessage());
		}
		return dbConnection;
	}
}