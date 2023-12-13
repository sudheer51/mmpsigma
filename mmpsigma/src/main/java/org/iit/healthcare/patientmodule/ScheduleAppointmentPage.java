package org.iit.healthcare.patientmodule;

import java.util.HashMap;

import org.iit.healthcare.util.AppLibrary;
import org.iit.healthcare.util.MMPLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ScheduleAppointmentPage {
	private WebDriver driver;
	private MMPLibrary mmpLib;
	HashMap<String,String> expectedHMap = new HashMap<String,String>();
 	
	public ScheduleAppointmentPage(WebDriver driver)
	{
		this.driver = driver;
		mmpLib = new MMPLibrary(driver);
	}
	
	public HashMap<String, String> bookAppointment(String doctorName)
	{
		driver.findElement(By.cssSelector("input[value='Create new appointment']")).click();
		driver.findElement(By.xpath("//h4[contains(text(),'"+doctorName+"')]/ancestor::ul/following-sibling::button")).click();

		driver.switchTo().frame("myframe");
		String  expectedDate= AppLibrary.getDate("MMMMM/d/yyyy");
		expectedHMap.put("date",  AppLibrary.getDate("MM/dd/yyyy"));
		
		String dateArr[] = expectedDate.split("/");

		String expectedDay = dateArr[1];
		//Interacting elements in the Frame
		driver.findElement(By.id("datepicker")).click();
		String expectedYear = dateArr[2];
		String actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		while(!(actualYear.equals(expectedYear)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		}
		String expectedMonth = dateArr[0];//02 February
		String actualMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();//January 
		while(!(actualMonth.equals(expectedMonth)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		}

		WebElement e = mmpLib.waitforElementToBeDisplayed(By.linkText(expectedDay),10);
		e.click();

		e = driver.findElement(By.id("time"));
		e.click();
		Select timeSelect = new Select(e);
		timeSelect.selectByVisibleText("12Pm");

		//Explicit Wait
		e = mmpLib.waitforElementToBeDisplayed(By.id("ChangeHeatName"),30);
		e.click();
		driver.switchTo().defaultContent();

		e= mmpLib.waitforElementToBeDisplayed(By.id("sym"),30);
		String expectedSym = "Fever";
		e.sendKeys(expectedSym);
		expectedHMap.put("sym",expectedSym);

		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		return expectedHMap;
	}
	 
}
