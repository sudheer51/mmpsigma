package org.iit.healthcare.util;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPLibrary {
	WebDriver driver;
	 public MMPLibrary(WebDriver driver)
	{
		this.driver = driver;
	}
	 
	public WebElement waitforElementToBeDisplayed(By locator, int secs)
	{
		Duration duration = Duration.ofSeconds(secs);
		WebDriverWait wait = new WebDriverWait(driver,duration);
		WebElement e= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return e;
	}
	 
 
	 
}
