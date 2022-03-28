package com.parser.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.parser.model.Alert;
import com.parser.utilities.DbHelper;
import com.parser.utilities.Helper;

public class LogManager {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger(LogManager.class.getName());
		BufferedReader reader = null;
		Map<String, Alert> eventAlerts= new HashMap<String, Alert> ();
		
		try {
			logger.info("Get the log file from resources");
			reader=new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/main/resources/logfile.txt"));
			eventAlerts=Helper.getAllEventsWithAlert(reader);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		finally{
			if(reader!=null) reader=null;
		}
		
		// Database connection
		logger.info("Create database object reference to insert the records");
		DbHelper dbConn = new DbHelper();
		dbConn.createTable();
		Iterator<Entry<String, Alert>> alert=eventAlerts.entrySet().iterator();
		
		// Puts the record in database
		logger.info("Insert the record in database");
		while(alert.hasNext())
		{
			Entry<String, Alert> alertEntry=alert.next();			
			dbConn.insertRecord(alertEntry.getValue().getId(),Long.toString(alertEntry.getValue().getDuration()),alertEntry.getValue().getType(),
					alertEntry.getValue().getHost(),Boolean.toString(alertEntry.getValue().isAlertFlag()));
		}
		logger.info("Execution completed");
				
	}

	

}
