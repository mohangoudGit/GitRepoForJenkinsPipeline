package com.qa.hvcom.base;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.hvcom.factory.DriverFactory;
import com.qa.hvcom.pages.HomePage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected HomePage homepage; 
	/*
	 *  within class and outside of class i wanted to give access to child class only
	 *  so i  will use protected 
	 */
									
	
	@BeforeTest  // run this before any test 
	public void setup() throws IOException {
		
		df=new DriverFactory();
		prop=df.initProp();// this will initate the properties file 
		//driver =df.InitDriver("chrome");
		//intead of passing only one porperty to init driver, let us pass whole porpertues file
		driver =df.InitDriver(prop);
		
		homepage=new HomePage(driver);
		
		
	}
	@AfterTest  // run this code after every test
	public void tearDown() throws InterruptedException {
		
	//	driver.quit();
	//	homepage.SendReportInEmail2();
	}
	
	
	

}
