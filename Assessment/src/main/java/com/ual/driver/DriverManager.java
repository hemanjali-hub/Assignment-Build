package com.ual.driver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


import org.openqa.selenium.safari.SafariDriver;

import com.ual.constants.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	public WebDriver driver;

	public WebDriver init_driver(String browser) {
		System.out.println("browser value is "+ browser);
		if(browser.equals("chrome")) 
		{ 
			ChromeOptions chromeOptions = new ChromeOptions();
//			chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);		
			
//			chromeOptions.setExperimentalOption("w3c", false);
  System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
			driver = new ChromeDriver(chromeOptions);
//			
			dr.set(driver);
		}
		else if(browser.equals("firefox")) {
			//WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.gecko.driver", "./lib/geckodriver.exe");
			dr.set(new FirefoxDriver());

		}
		else if(browser.equals("safari")) {
			dr.set(new SafariDriver());
		}
		else if(browser.equals("edge")) {
			//WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.gecko.driver", "./lib/msedgedriver.exe");
			dr.set(new EdgeDriver());
			//driver=new EdgeDriver();
		} 
		else if(browser.equals("internetExplorer")) {
			WebDriverManager.iedriver().setup();
			dr.set(new InternetExplorerDriver());
		} 
		
		
		else {
			System.out.println("Please pass the correct browser value"+browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return getDriver();		
	}
	
	public static synchronized WebDriver getDriver() {
		return dr.get();
	}

	
	}
	
	