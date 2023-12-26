package com.ual.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrangeHrmPage {

	WebDriver driver;
	public OrangeHrmPage(WebDriver driver2) {
		 this.driver=driver2;
			PageFactory.initElements( driver2, this);
		}
	
	 @FindBy(xpath="//input[@placeholder='Username']")public WebElement username;
	 @FindBy(xpath="//input[@placeholder='Password']")public WebElement password;
	 @FindBy(xpath="//button[@type='submit']")public WebElement submit;
	 @FindBy(xpath="//p[text()='Invalid credentials']")public WebElement loginErrorMessage;
}
