package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By header = By.cssSelector("div#logo a");
	private By logout = By.linkText("Logout");
	private By sectionHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchBtn = By.cssSelector("div#search button");
	
	

	// 2. Create AccountsPage Class constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getAccountsPageTitle() {
		return eleUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_ELEMENT_TIMEOUT);
	}

	public String getAccountsPageUrl() {
		return eleUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, Constants.DEFAULT_ELEMENT_TIMEOUT);
	}

	public String getAccountPageHeader() {
		return eleUtil.doGetText(header);
	}

	public List<String> getAccountPageSectionsList() {
		return eleUtil.getElementsTextList(sectionHeaders);
	}
	
	public boolean isLogOutLinkExist() {
		return eleUtil.waitForElementVisible(logout, Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
	}
	
	public LoginPage clickOnLogout() {
		if(isLogOutLinkExist()) {
			eleUtil.doClick(logout);
		}
		return new LoginPage(driver);
	}
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
	}
	
	
	public SearchResultsPage doSearch(String searchKey) {
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchBtn);
			return new SearchResultsPage(driver);
		}	
		return null;
	}

}
