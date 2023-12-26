package com.ual.hooks;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Arrays;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.ual.constants.Constants;
//import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfiguration;
import com.ual.driver.DriverManager;

import com.ual.helpers.CommonUtils;



import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.TestCaseFinished;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Hooks {

	public static String scenarioName;
	public static String startTime;
	public static String endTime;
	public static int scenario_count =0;
	public static int scenario_count_passed =0;
	public static int scenario_count_failed =0;
	public static int scenario_count_failed_rerun =0;
	private static Logger log = LogManager.getLogger(Hooks.class);
	private DriverManager driverManager;
	private WebDriver driver;
	public ExtentTest extentTest;
	public ExtentReports extent;
	public  int  rowValue;
	
	public static long startTimeInMilliForTest;
	public static long EndTimeInMilliForTest;
	
	public static Scenario scenario;
	

	
	public static  TestListenerAdapter testListenerAdapter;

	
	
	
	
	@Before (order = 0)
	public void launchBrowser(Scenario scenario) throws Exception 
	{    
		this.scenario=scenario;
		

		
		{
			System.out.println("EXECUTING SCENARIO: " + scenario.getName());
			log.info("EXECUTING SCENARIO: " + scenario.getName());
			
			
			String browserName = Constants.BROWSER.trim();
			driverManager = new DriverManager();
			driver = driverManager.init_driver(browserName);
			
			
		}
		
	}

	@Before(order = 1)
	public void beforeHook(Scenario scenario) throws ParseException, IOException {
		System.out.println("****Before Class****");
		startTime = CommonUtils.getCST("yyyy-MM-dd HH-mm-ss");
		log.info("Execution Start Time--" + startTime);
//		startTime = Utils.getCurrentDateTime(Instant.now().toString());
		startTimeInMilliForTest = Calendar.getInstance().getTimeInMillis();
		scenarioName = scenario.getName();
		scenario_count++;
		log.info("Scenario Name -- " + scenarioName);

	}

	@BeforeStep
	public void BeforeStep(Scenario scenario) {
		//System.out.println("printing row value before step:"+rowValue);
	}

	@AfterStep
	public void AfterStep(Scenario scenario) throws IOException, SQLException {
		log.info("Executing @AfterStep");
		try {
			
				
					scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
				

				//System.out.println("****Screenshot captured After Step****");
			
		} catch (Exception e) {
			//e.printStackTrace();
		} 
		log.info("Done @AfterStep");
	}

	@After(order=0)
	public void After(Scenario scenario) throws IOException, SQLException,Exception {
		log.info("Started Executing @After 0" );
		log.info("Scenario status "+scenario.getStatus());
		
		
		log.info("Done @After");
	}

	@After(order = 1)
	public void After() throws Exception {
		log.info("Started Executing @After 1" );
		
		try {
			WebDriver driver = DriverManager.getDriver();
			
		} catch (Exception e) {
			log.info("*********LOGOUT EXCEPTION @After 1*********** "+ e);
		}finally { 
			try { 
				endTime = CommonUtils.getCST("yyyy-MM-dd HH-mm-ss");
				log.info("Execution End Time--"+endTime);
				EndTimeInMilliForTest = Calendar.getInstance().getTimeInMillis();
				
				log.info("======Scenario Ends======");
				driver.quit();
				log.info("Closing Driver session !!!"); 
			 
			} catch (NullPointerException e) {
				log.info("*********EXCEPTION @After 1*********** "+ e);
			}
		}
		log.info("Done @After 1");
	}

}
