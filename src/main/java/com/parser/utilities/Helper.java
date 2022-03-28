package com.parser.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.model.Alert;
import com.parser.model.Event;
import com.parser.model.State;

public class Helper {
	
	static Logger logger = Logger.getLogger(Helper.class.getName());			
	
	/**
	 * This method provides all the alert events 
	 * @param reader - input for this method, which will be converted to POJO. 
	 * @return
	 */
		
	public static  HashMap<String, Alert> getAllEventsWithAlert(BufferedReader reader) {
		// TODO Auto-generated method stub
		final int TimeLimit=4;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String,Event> logEvents= new HashMap<String,Event>();	
		HashMap<String, Alert> alerts = new HashMap<String, Alert>();
		String line=null;
		try {
			line = reader.readLine();
			 while (line != null) {
			        Event event = mapper.readValue(line, Event.class);
			        if(logEvents.containsKey(event.getId()) && event.getState().name().equals(State.FINISHED.toString()))
			        	{
			        	Event events=logEvents.get(event.getId());
			        	long timeDifference=calculateTimeDifference(event, events); 	
			        	
			        	Alert alert = new Alert(events, timeDifference);               
		                if (timeDifference > TimeLimit) {		                	
		                	logger.info("Alert value is higher than " +TimeLimit);
		                    alert.setAlertFlag(Boolean.TRUE);                      
		                }
		                alerts.put(events.getId(), alert);
		                logger.info("Event id: "+logEvents.get(event.getId()).getId()+ " Time difference is :"+timeDifference);
			        	}
			        else if(logEvents.containsKey(event.getId()) && event.getState().name().equals(State.STARTED.toString()))
		        	{
			        	Event evnt=logEvents.get(event.getId());
			        	long timeDifference=calculateTimeDifference(evnt, event);
			        	Alert alert = new Alert(evnt, timeDifference);               
		                if (timeDifference > TimeLimit) {
		                	logger.info("Alert value is less than/equal to " +TimeLimit);
		                    alert.setAlertFlag(Boolean.TRUE);                      
		                }
		                alerts.put(evnt.getId(), alert);
		                logger.info("Event id: "+logEvents.get(event.getId()).getId()+ " Time difference is :"+timeDifference);
		        	}	       
			        else{
			        	logEvents.put(event.getId(), event);
			        }		        
			        line = reader.readLine();
			    }
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			if(reader!=null) reader=null;
		}
		return alerts;

	   	    
	}
	
	/**
	 * This method returns the time difference between two events
	 * @param event1 - first event type input for this method.
	 * @param event2 - second event type input for this method.
	 * @return
	 */

	private static long calculateTimeDifference(Event event1, Event event2) {
		// TODO Auto-generated method stub
		return event1.getTimestamp()-event2.getTimestamp();
	} 
	

}
