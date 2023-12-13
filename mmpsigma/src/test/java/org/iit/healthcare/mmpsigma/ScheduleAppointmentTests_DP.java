package org.iit.healthcare.mmpsigma;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScheduleAppointmentTests_DP {

	WebDriver driver;
	@Test
	public void validateScheduleAppointment()
	{
		WebDriverManager.chromedriver().setup();
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		HashMap<String,String> actualHMap = new HashMap<String,String>();

		driver = new ChromeDriver();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		navigateToModule("Schedule Appointment");
		driver.findElement(By.cssSelector("input[value='Create new appointment']")).click();
		driver.findElement(By.xpath("//h4[contains(text(),'Charlie')]/ancestor::ul/following-sibling::button")).click();

		driver.switchTo().frame("myframe");
		String  expectedDate= getDate("MMMMM/d/yyyy");
		expectedHMap.put("date",  getDate("MM/dd/yyyy"));
		
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

		WebElement e = waitforElementToBeDisplayed(By.linkText(expectedDay),10);
		e.click();

		e = driver.findElement(By.id("time"));
		e.click();
		Select timeSelect = new Select(e);
		timeSelect.selectByVisibleText("12Pm");

		//Explicit Wait
		e = waitforElementToBeDisplayed(By.id("ChangeHeatName"),30);
		e.click();
		driver.switchTo().defaultContent();

		e= waitforElementToBeDisplayed(By.id("sym"),30);
		String expectedSym = "Fever";
		e.sendKeys(expectedSym);
		expectedHMap.put("sym",expectedSym);

		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		String actualSym= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText();
		actualHMap.put("sym", actualSym);
		String actualDate= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText();
		actualHMap.put("date", actualDate);
		
		Assert.assertEquals(actualHMap, expectedHMap);

	}
	public void navigateToModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();
	}
	public WebElement waitforElementToBeDisplayed(By locator, int secs)
	{
		Duration duration = Duration.ofSeconds(secs);
		WebDriverWait wait = new WebDriverWait(driver,duration);
		WebElement e= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return e;
	}
	public String getDate(String format)
	{
		//Date Func
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 3);
		Date d  = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(d);
		System.out.println("Date:::::::::::"+ date);
		return date;
	}
}
