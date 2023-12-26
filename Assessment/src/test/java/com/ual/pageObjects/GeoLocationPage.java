package com.ual.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeoLocationPage {

	WebDriver driver;
	public GeoLocationPage(WebDriver driver2) {
		 this.driver=driver2;
			PageFactory.initElements( driver2, this);
		}
	
	 @FindBy(xpath="//div[@id='latitude']")public WebElement latitude;
	 @FindBy(xpath="//div[@id='longitude']")public WebElement longitude;
	 @FindBy(xpath="//div[@id='address']")public WebElement location;
}
