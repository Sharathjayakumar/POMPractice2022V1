package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic - 100: This epic is for login page of open cart application")
@Story("User Store - Login101: Design login page with various features")
public class LoginPageTest extends BaseTest {

	@Description("Login Page title test....")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String actualText = loginPage.getLoginPageTitle();
		System.out.println("Login page title is : " + actualText);
		Assert.assertEquals(actualText, Constants.LOGIN_PAGE_TITLE);
	}

	@Description("Login Page url test....")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageUrlTest() {
		String actualUrl = loginPage.getLoginPageUrl();
		System.out.println("Login page url is : " + actualUrl);
		// Assert.assertEquals(actualUrl,"https://demo.opencart.com/index.php?route=account/login");

		//Assert.assertTrue(actualUrl.contains("route=account/login")); // or
		Assert.assertTrue(actualUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("Is forgot password link exist test....")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkExistTest() {
		boolean flag = loginPage.isForgotPwdLinkExist();
		Assert.assertTrue(flag);
	}
	
	@Description("Register Link exist test....")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void registerLinkExistTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}

	@Description("Login to aplication test....")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void sloginTest() {
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim())
				.isLogOutLinkExist());
	}
}
