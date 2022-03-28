package com.parser.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {
	
	private static final String CREATE_TBL = "CREATE TABLE events "
												+ "( id varchar(20), duration varchar(10), type varchar(20), "
												+ "host varchar(20), alert varchar(10));";

    private static final String INSERT_USR = "INSERT INTO events  "
    											+ "(id, duration, type, host, alert) "
    											+ "VALUES  (?, ?, ?, ?, ?);";

    
    /**
     * This method creates a table 
     */
    public void createTable()  {
    		
    	Connection connection=null;
    	Statement statement=null;
        System.out.println(CREATE_TBL);      
        try  {
        	 connection = ConnectionHelper.getConnection();
             statement = connection.createStatement();
             statement.execute(CREATE_TBL);
        } catch (SQLException e) {
        	ConnectionHelper.printDBException(e);
        }
        finally {
        	if(connection!=null)connection=null;
        	if(statement!=null) statement=null;
		}
    }
    
    /**
     	* Inserting event record 
     	* @param id - Event Id
     	* @param duration - Event duration
     	* @param type - Event type
     	* @param host - Event host
     	* @param alert - Event alert
     */
    public void insertRecord(String id, String duration,String type, String host, String alert)  {
    	Connection connection=null;
    	PreparedStatement statement=null;
        System.out.println(INSERT_USR);
    
        try  {
        	connection = ConnectionHelper.getConnection();            
        	statement = connection.prepareStatement(INSERT_USR);
        	statement.setString(1, id);
        	statement.setString(2, duration);
        	statement.setString(3, type);
        	statement.setString(4, host);
        	statement.setString(5, alert);

            System.out.println(statement);
          
            statement.executeUpdate();
        } catch (SQLException e) {
        	ConnectionHelper.printDBException(e);
        }
        finally {
        	if(connection!=null)connection=null;
        	if(statement!=null) statement=null;
		}

    }
}
