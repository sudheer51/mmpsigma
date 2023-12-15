package org.iit.healthcare.patientmodule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	private WebDriver driver;
	
	@FindBy(how=How.ID,using="username")
	private WebElement unameField;
	
	@FindBy(how=How.ID,using="password")
	private WebElement passwordField;
	
	@FindAll({@FindBy(how=How.NAME,using="submit")})	 
	private List<WebElement> submitButton;
	
	 
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void login(String username,String password)
	{
		//driver.findElement(By.id("username")).sendKeys(username);
	 	unameField.sendKeys(username);
		passwordField.sendKeys(password);
		submitButton.get(0).click();
	}

}
