package com.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.Utilities.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static Properties prop;

	// Declare ThreadLocal Driver
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
	ExtentManager extentmanager = new ExtentManager();

	// Loadconfig methos is to load config file

	@BeforeSuite

	
	
	
	
	public void loadconfig() {
		
		//extentmanager.setExtent();
		DOMConfigurator.configure("log4j.xml");
		

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\Config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static WebDriver getDriver() {
		return driver.get();
	} 

	

	public void launchApp(String browserName) {

		if (browserName.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());

		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver.set(new InternetExplorerDriver());
		}

		// Maximise Window
		getDriver().manage().window().maximize();

		// Delete All Cookes

		getDriver().manage().deleteAllCookies();

		// Implicit TimeOut

		getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitWait")),
				TimeUnit.SECONDS);

		// PageLoad Timeoit

		getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageLoadTimeOut")),
				TimeUnit.SECONDS);

		// Lauch URL

		getDriver().get(prop.getProperty("url"));

	}

	@AfterSuite

	public void afterSuite() {
		extentmanager.endReport();
		

	}

}
