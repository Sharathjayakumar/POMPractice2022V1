package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest {
	
	@BeforeClass
	
	public void registerSetup() {
		registerPage = loginPage.goToRegisterPage();
	}
	
	
	@DataProvider
	public Object[][] getRegisterTestData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "marchautomationsj"+random.nextInt(1000)+"@gmail.com";
		return email;
		
	}
	
	@Test(dataProvider="getRegisterTestData")
	public void registerUserTest(String firstName, String lastName,String telephone, String password, String subscribe) {
		
		Assert.assertTrue(registerPage.registerUser(firstName,lastName,getRandomEmail(),telephone, password, subscribe));
	}
	
	

}
