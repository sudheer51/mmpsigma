package org.iit.healthcare.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected WebDriver driver;
	protected Properties prop;
	@BeforeSuite
	public void loadProperties() throws IOException
	{
		readProperties("mmp.properties");
	}
	@BeforeClass
	public void instantiateDriver() throws IOException
	{
		System.out.println("in intantiateDriver "+ prop.getProperty("browserType"));
		if(prop.getProperty("browserType").equals("chrome"))
		{
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(prop.getProperty("browserType").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		if(prop.getProperty("environment").equals("qa"))
		{
			readProperties("qa.properties");
		}
		else if(prop.getProperty("environment").equals("dev"))
		{
			readProperties("dev.properties");
		}

		driver.get(prop.getProperty("patient_url"));
		driver.manage().window().maximize();
	}
	public void readProperties(String fileName) throws IOException
	{
		File propFile = new File(System.getProperty("user.dir")+"//config//"+fileName);
		FileInputStream fis = new FileInputStream(propFile);
		prop = new Properties();
		prop.load(fis);
	}

}
