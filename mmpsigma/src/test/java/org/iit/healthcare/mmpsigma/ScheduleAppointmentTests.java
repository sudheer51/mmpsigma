package org.iit.healthcare.mmpsigma;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScheduleAppointmentTests {

	WebDriver driver;
	@Test
	public void validateScheduleAppointment()
	{
		WebDriverManager.chromedriver().setup();
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
		//Date Func
		Calendar cal = Calendar.getInstance();
		cal.add(cal.DATE, 10);
		Date d  = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
		String date = sdf.format(d);
		System.out.println(date);
		
		//Interacting elements in the Frame
		driver.findElement(By.id("datepicker")).sendKeys(date);
		
		System.out.println(date);
		String dateArr[] = date.split("/");
		System.out.println(dateArr[1]);
		WebElement e = waitforElementToBeDisplayed(By.linkText(dateArr[1]),10);
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
		e.sendKeys("Fever");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
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
}
