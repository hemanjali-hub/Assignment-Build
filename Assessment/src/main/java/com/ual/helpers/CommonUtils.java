package com.ual.helpers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ual.constants.Constants;

import io.restassured.specification.RequestSpecification;

public class CommonUtils {
	private static Logger log = LogManager.getLogger(CommonUtils.class.getName());

	
	
	public static void loadProperties() throws Exception
	{
		FileReader reader = null;
			String file = System.getProperty("user.dir")+"\\testInputs\\testInputs.properties";
			reader = new FileReader(file);
			
			Properties properties = new Properties(); 
			properties.load(reader);
			
			Constants.BROWSER = properties.getProperty("BROWSER");
			Constants.OPEN_HRM_URL = properties.getProperty("OPEN_HRM_URL");
			Constants.GEO_LOCATION_URL = properties.getProperty("GEO_LOCATION_URL");
			
}
	
	public static String getCST(String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		format.setTimeZone(TimeZone.getTimeZone("CST"));
		return format.format(new Date());
	}
	
	public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll until the element is in view
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
