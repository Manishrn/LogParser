package com.parser.model;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertTests {	
	
	
	@Test
	public void defaultAlertFlagForAlert(){
		Event eve = new Event();	
		eve.setId("Alert1");
		Alert alert1 = new Alert(eve, 10);
        alert1.setHost("127.0.0.1");
        alert1.setType("APPLICATION_LOG");
        alert1.setAlertFlag(false);

    	Alert alert2 = new Alert(eve, 10);
        alert2.setHost("127.0.0.1");
        alert2.setType("APPLICATION_LOG");
        
        Assert.assertEquals(alert1, alert2);
	}
	
	@Test
	public void alertWithDifferentFlag(){
		Event eve = new Event();	
		eve.setId("Alert1");
		Alert alert1 = new Alert(eve, 10);
        alert1.setHost("127.0.0.1");
        alert1.setType("APPLICATION_LOG");
        alert1.setAlertFlag(false);

    	Alert alert2 = new Alert(eve, 10);
        alert2.setHost("127.0.0.1");
        alert2.setType("APPLICATION_LOG");
        alert1.setAlertFlag(true);
        
        Assert.assertNotEquals(alert1, alert2);
	}
	
	@Test
	public void alertWithDefaultValue(){
		Event eve = new Event();	
		Alert alert1 = new Alert(eve, 10);
    	Alert alert2 = new Alert(eve, 10);        
        Assert.assertEquals(alert1.isAlertFlag(), alert2.isAlertFlag());
	}

	
}
