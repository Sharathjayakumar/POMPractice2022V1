package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id=\"content\"]//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPricingData = By.xpath("(//div[@id=\"content\"]//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.xpath("//input[@id=\"input-quantity\"]");
	private By addToCart = By.xpath("//button[@id=\"button-cart\"]");
	private By cartSuccessMessage = By.cssSelector("div.alert.alert-success");

	Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getProductHeaderName() {

		return eleUtil.waitForElementVisible(productHeader, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
	}

	public int getProductImagesCount() {

		return eleUtil.waitForElementsVisible(productImages, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
	}

	public Map<String, String> getProductInformation() {

		productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeaderName());
	
		getProductMetaData();
		getProductPriceData();
		
		productInfoMap.forEach((k,v)-> System.out.println(k +":" +v));
		
		return productInfoMap;
	}

	private void getProductMetaData() {

		// Capture Meta Data

		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		System.out.println("Total product meta data: " + metaDataList.size());

		// Brand: Apple
		// Product Code: Product 18
		// Reward Points: 800
		// Availability: In Stock

		for (WebElement e : metaDataList) {
			String meta[] = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();

			productInfoMap.put(metaKey, metaValue);
		}

	}

	private void getProductPriceData() {

		// Price:
		List<WebElement> priceList = eleUtil.getElements(productPricingData);
		System.out.println("Total product price list: " + priceList.size());

		String price = priceList.get(0).getText().trim();
		String exTaxPrice = priceList.get(1).getText().trim();

		productInfoMap.put("price", price);
		productInfoMap.put("extaxprice", exTaxPrice);
	}
	
	public String getProductInfoPageInnerText() {
		
		JavascriptExecutor jsx = (JavascriptExecutor)driver;
		String pageInnerText = jsx.executeScript("return document.documentElement.innerText").toString();
		System.out.println("=====================" + pageInnerText +"=================");
		return pageInnerText;
	}
	
	public ProductInfoPage enterQty(String qty) {
		eleUtil.doSendKeys(quantity, qty);
		return this;
	}
	
	public ProductInfoPage clickOnAddToCart() {
		eleUtil.doClick(addToCart);
		return this;
	}
	
	public String getCartSuccessMessage() {
		return eleUtil.waitForElementVisible(cartSuccessMessage, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
	}

}
