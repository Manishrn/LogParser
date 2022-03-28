package com.parser.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionHelper {
	
	 	private static String ConnURL = "jdbc:hsqldb:file:db_alert";
	    private static String Username = "SA";
	    private static String Password = "";
		static Logger logger = Logger.getLogger(ConnectionHelper.class.getName());	

	    
	    /**
	     * This method creates the connection to HSQLDB database
	     * @return Connection object
	     */
	    public static Connection getConnection() {
	        Connection connection = null;
	        try {
	            connection = DriverManager.getConnection(ConnURL, Username, Password);
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            printDBException(e);
	            e.printStackTrace();
	        }
	        return connection;
	    }

	    /**
	     * Prints the database exception
	     * @param ex
	     */
	    public static void printDBException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);	               
	                logger.warning("Error Code: " + ((SQLException) e).getErrorCode());
	                logger.warning("Message: " + e.getMessage());	                
	            }
	        }
	    }

}
