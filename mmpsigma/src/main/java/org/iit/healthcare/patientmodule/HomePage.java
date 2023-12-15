package org.iit.healthcare.patientmodule;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	/**
	 * HomePage for navigation
	 */
	HashMap<String,String> actualHMap = new HashMap<String,String>();
	private By symXpath = By.xpath("//table[@class='table']/tbody/tr[1]/td[3]");
	private By dateXpath = By.xpath("//table[@class='table']/tbody/tr[1]/td[1]");
	
	private WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}

	public HashMap<String, String> fetchPatientPortalValues()
	{
		String actualSym= driver.findElement(symXpath).getText();
		actualHMap.put("sym", actualSym);
		String actualDate= driver.findElement(dateXpath).getText();
		actualHMap.put("date", actualDate);
		return actualHMap;
	}
	public void navigateToModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();
	}
}
