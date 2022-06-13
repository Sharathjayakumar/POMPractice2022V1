package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil; 
	
	private By searchResults = By.cssSelector("div.product-layout.product-grid");
	

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public int getSearchResultsCount() {
		return eleUtil.waitForElementsVisible(searchResults, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		// dynamic By locator
		By productNameLink = By.linkText(productName);
		eleUtil.doClick(productNameLink);
		return new ProductInfoPage(driver);
	}
	
	

}
