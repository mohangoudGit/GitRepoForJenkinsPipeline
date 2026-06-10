package com.qa.hvcom.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hvcom.exceptions.BrowserException;

public class DriverFactory {
	
WebDriver driver;
Properties prop;

public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
public static String highlight;
/*
 * This method is used to initailize the driver on the basis of Browser passed 
 */

public WebDriver InitDriver(Properties prop) {

//	System.out.println("BrowserName: "+BrowserName);
	String BrowserName=prop.getProperty("browser");
	switch(BrowserName.toLowerCase().trim()) {

	case "chrome":
		driver=new ChromeDriver();
		break;
	case "Firefox":
		driver=new FirefoxDriver();
		break;
	case "edge":
		driver=new EdgeDriver();
		break;
	case "safari":
		driver=new SafariDriver();
		break;

	default:
		System.out.println("Please pass the valid driver:" +BrowserName );
		throw new BrowserException("Invalid Browser");	
		

	}
	
	
	driver.get(prop.getProperty("url"));// getting the url from Prop File
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	return driver;

	
}

/*
 * this initialize the porperties file 
 */

//mvn clean install -Denv="qa"
public Properties initProp() throws IOException {
	
	
	String EnvName=System.getProperty("env");
	System.out.println("Reading ENV ::"+EnvName);
	
	//	FileInputStream ip;
	FileInputStream ip=null;
	prop=new Properties();
	if(EnvName==null) {
		System.out.println("Env is null hence, hence runnin the tests in QA Environment "); // Default
		ip=new FileInputStream("./src/test/resource/config/qa.config.properties");
	}else {

		System.out.println("Running in the envName::"+EnvName);

	}



	prop=new Properties();
	ip=new FileInputStream("./src/test/resource/config/qa.config.properties");
	prop.load(ip);


	return prop;

	}



public static WebDriver getDriver() {
	return tlDriver.get();
}


}
