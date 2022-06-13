package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	// 1. In Page Class, maintain private By class - Locator should be private in
	// nature so that other test cannot use it.
	// Page class is a perfect example of Encapsulation

	private By emailID = By.id("input-email");
	private By passwordBy = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By logOutSuccessMessage = By.cssSelector("div#content h1");
	private By gitTest = By.cssSelector("div#content h1");
	
	// 2. Create page Class constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	// 3. Create Page actions or page methods or page behaviour

	@Step("getting login page title of open cart application")
	public String getLoginPageTitle() {
		// return driver.getTitle();
		// From ElementUtil Class
		return eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_ELEMENT_TIMEOUT);

	}

	@Step("getting login page url of open cart application")
	public String getLoginPageUrl() {
		// return driver.getCurrentUrl();
		// Using ElementUtil Class
		return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_ELEMENT_TIMEOUT);
	}

	@Step("user is able to login with username: {0} and password: {1}")
	public AccountsPage doLogin(String userName, String password) {
		System.out.println("Login Credentials are: " + userName + " : " + password);
		// driver.findElement(emailID).sendKeys(userName);
		// driver.findElement(this.password).sendKeys(password);
		// driver.findElement(passwordBy).sendKeys(password);
		// driver.findElement(loginButton).click();

		// using elementUtil
		eleUtil.waitForElementVisible(emailID, Constants.DEFAULT_ELEMENT_TIMEOUT).sendKeys(userName);
		eleUtil.doSendKeys(passwordBy, password);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}

	@Step("isForgotPwdLinkExist")
	public boolean isForgotPwdLinkExist() {
		// return driver.findElement(forgotPwdLink).isDisplayed();
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}

	@Step("isRegisterLinkExist")
	public boolean isRegisterLinkExist() {
		// return driver.findElement(registerLink).isDisplayed();
		return eleUtil.isElementDisplayed(registerLink);
	}

	@Step("Fetching success message for logout")
	public String getLogOutSuccessMessage() {
		
		return eleUtil.waitForElementVisible(logOutSuccessMessage,Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
		
	}
	@Step("Navigating to registration page after clicking on register link")
	public RegisterPage goToRegisterPage() {
		
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
