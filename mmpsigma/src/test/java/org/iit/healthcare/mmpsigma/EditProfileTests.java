package org.iit.healthcare.mmpsigma;

import org.iit.healthcare.patientmodule.HomePage;
import org.iit.healthcare.patientmodule.LoginPage;
import org.iit.healthcare.util.AppLibrary;
import org.iit.healthcare.util.BaseClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditProfileTests extends BaseClass{
	
	@Test
	public void validateFirstName()
	{
		LoginPage lPage = new LoginPage(driver);
		lPage.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage homePage = new HomePage(driver);
		homePage.navigateToModule("Profile");
		driver.findElement(By.id("Ebtn")).click();
		String expectedString = AppLibrary.generateRandomString("FNAME");
		WebElement fnameWE = driver.findElement(By.id("fname"));
		fnameWE.clear();
		fnameWE.sendKeys(expectedString);
		driver.findElement(By.id("Sbtn")).submit();
		Alert alrt = driver.switchTo().alert();
		String actualMsg = alrt.getText();
		String expectedMsg = "Your Profile has been updated.";
		alrt.accept();
		String actualString = fnameWE.getAttribute("value");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualString, expectedString);
		sa.assertEquals(actualMsg,expectedMsg);
		sa.assertAll();
		
		
		
		
	}

}