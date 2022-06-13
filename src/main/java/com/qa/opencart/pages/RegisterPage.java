package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class=\"radio-inline\"]/input[@value=\"1\" and @type=\"radio\"]");
	private By subscribeNo = By.xpath("//label[@class=\"radio-inline\"]/input[@value=\"0\" and @type=\"radio\"]");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type=\"submit\"]");
	
	private By registerSuccessMessage = By.cssSelector("div#content h1");
	
	private By logOutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	
	public boolean registerUser(String firstName, String lastName, String email, String telePhone, String password, String subcribe) {
		
		WebElement ele = eleUtil.waitForElementVisible(this.firstName, Constants.DEFAULT_ELEMENT_TIMEOUT);
		ele.clear();
		ele.sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telePhone, telePhone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		if(subcribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		String successMessage = eleUtil.waitForElementVisible(registerSuccessMessage, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
		
		if(successMessage.contains(Constants.ACCOUNT_REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logOutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		
		else {
			
			eleUtil.doClick(logOutLink);
			eleUtil.doClick(registerLink);
			
		}
		return false;
	}



	

	
	
	
	

}
