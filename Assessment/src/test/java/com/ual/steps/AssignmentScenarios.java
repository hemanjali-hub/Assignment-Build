package com.ual.steps;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v119.emulation.Emulation;
//import org.openqa.selenium.devtools.v120.emulation.Emulation;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.Optional;
import com.ual.constants.Constants;
import com.ual.driver.DriverManager;
import com.ual.helpers.CommonUtils;
import com.ual.pageObjects.GeoLocationPage;
import com.ual.pageObjects.OrangeHrmPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignmentScenarios {
	private static Logger log = LogManager.getLogger(AssignmentScenarios.class);
	WebDriver driver = DriverManager.getDriver();
	OrangeHrmPage ohp=new OrangeHrmPage(driver);
	GeoLocationPage glp=new GeoLocationPage(driver);
	
	@Given("Navigate to the OrangeHrm website")
	public void navigate_to_the_orange_hrm_website() {
	   driver.get(Constants.OPEN_HRM_URL);
	}

	@When("user enters {string} and {string}")
	public void user_enters_and(String username, String password) {
	   ohp.username.sendKeys(username);
	   ohp.password.sendKeys(password);
	}
	
	@Then("verify user is at hrm main page when valid credentails are provided else dispaly the error message {string}")
	public void verify_user_is_at_hrm_main_page_when_valid_credentails_are_provided_else_dispaly_the_error_message(String errorMessage) {
	    String currentUrl=driver.getCurrentUrl();
		ohp.submit.click();
	   if(currentUrl.equalsIgnoreCase(driver.getCurrentUrl())) {
	    if(ohp.loginErrorMessage.isDisplayed()) {
	    	Assert.assertTrue(ohp.loginErrorMessage.getText().equalsIgnoreCase(errorMessage));
	    	System.out.println("Login failed, Invalid Credentials are being entered");
	    }
	   }else {
		   System.out.println("Login Being Successful & User navigated to Open Hrm main page");
	   }
	}
	
	
	 Map<String,Object> cordinates= new HashMap();
	@Given("set the loation cordinates {string} {string} using dev tools")
	public void set_the_loation_cordinates_using_dev_tools(String latitude, String longitude) {

	   
	    cordinates.put("latitude", Double.parseDouble(latitude));
	    cordinates.put("longitude", Double.parseDouble(longitude));
	    cordinates.put("accuracy", 1);
	    ((ChromeDriver) driver).executeCdpCommand("Emulation.setGeolocationOverride", cordinates);
	   

	    
	}

	
	@When("Navigate to the geoLocation website")
	public void navigate_to_the_geo_location_website() {
		 driver.get(Constants.GEO_LOCATION_URL);
	}

	

	@Then("fetch the user cordinates and verify the given {string} {string} location cordinates")
	public void fetch_the_user_cordinates_and_verify_the_given_location_cordinates(String latitude, String longitude) {
	   
		CommonUtils.scrollToElement(driver,glp.location);
		 String latitudeValue=glp.latitude.getText();
		  String longitudeValue=glp.longitude.getText();
		  
		  if(latitude.equalsIgnoreCase(latitudeValue) && longitude.equalsIgnoreCase(longitudeValue)) {
			  System.out.println("The latitude and longitude cordinates are matched");
			  System.out.println("User location address: "+glp.location.getText());
			  log.info("The latitude and longitude cordinates are matched");
			  log.info(latitudeValue);
			  log.info(longitudeValue);
			  log.info("User location address: "+glp.location.getText());
	       }
		  else {
			  System.out.println("The latitude and longitude cordinates are not matched");
			  log.info("Identified latitude address: "+glp.location.getText());
			  log.info("Identified location address: "+latitudeValue);
			  log.info("Identified longitude address: "+longitudeValue);
		  }

		 
	}
}
