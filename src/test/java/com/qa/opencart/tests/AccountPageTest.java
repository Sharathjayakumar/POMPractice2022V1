package com.qa.opencart.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("Epic - 101: This epic is for accounts page of open cart application")
@Story("User Story - AccPage201: Design account page with various features")
public class AccountPageTest extends BaseTest {

	// Precondition for account page is user should be logged In

	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test(priority = 1)
	public void getAccountsPageTitleTest() {
		String actualTitle = accPage.getAccountsPageTitle();
		System.out.println("Accounts Page title is : " + actualTitle);
		Assert.assertEquals(actualTitle, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void getAccountsPageUrlTest() {
		String actualUrl = accPage.getAccountsPageUrl();
		System.out.println("Accounts Page title is : " + actualUrl);
		Assert.assertTrue(actualUrl.contains(Constants.ACCOUNT_PAGE_URL_FRACTION));
	}

	@Test(priority = 3)
	public void getAccountPageHeaderTest() {
		Assert.assertEquals(accPage.getAccountPageHeader(), Constants.ACCOUNT_PAGE_HEADER);
	}

	@Test(priority = 4)
	public void getAccountPageSectionsListTest() {
		List<String> actAccSectionList = accPage.getAccountPageSectionsList();
		System.out.println("Actual Account Session List: " + actAccSectionList);
		Assert.assertEquals(actAccSectionList, Constants.EXPECTED_ACCOUNT_PAGE_LIST);
	}

	@Test(priority = 5)
	public void isLogOutLinkExistTest() {
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}

	@Test(priority = 6)
	public void isSearchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}

	@Test
	public void logOutTest() {
		String actualMessage = accPage.clickOnLogout().getLogOutSuccessMessage();
		Assert.assertEquals(actualMessage, Constants.LOGOUT_SUCCESS_MESSAGE);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	@DataProvider
	public Object[][] getSearchKey() {

		return new Object[][] { { "macbook" }, { "iMac" }, { "Apple" }, { "Samsung" }

		};
	}

	@Test(dataProvider = "getSearchKey", priority = 7)
	public void searchTest(String searchKey) {
		searchResPage = accPage.doSearch(searchKey);
		Assert.assertTrue(searchResPage.getSearchResultsCount() > 0);
	}

	// For checking product names in PDP
	@DataProvider
	public Object[][] getProductName() {

		return new Object[][] { { "macbook", "MacBook Pro" }, { "macbook", "MacBook Air" }, { "macbook", "MacBook" },
				{ "iMac", "iMac" }, { "Apple", "Apple Cinema 30\"" }, { "Samsung", "Samsung SyncMaster 941BW" }

		};
	}

	@Test(dataProvider = "getProductName")
	public void selectProductTest(String searchKey, String productName) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String productHeader = productInfoPage.getProductHeaderName();
		System.out.println("product header: " + productHeader);
		Assert.assertEquals(productHeader, productName);

	}

	// For checking product images count in PDP
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "Samsung", "Samsung SyncMaster 941BW", 1 }

		};
	}

	@Test(dataProvider = "getProductData")
	public void selectProductsImageTest(String searchKey, String productName, int productImageCount) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String productHeader = productInfoPage.getProductHeaderName();
		System.out.println("product header: " + productHeader);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), productImageCount);
	}

	@Test(enabled = false)
	public void productInfoTest() {
		searchResPage = accPage.doSearch("macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductInfoMap = productInfoPage.getProductInformation();

		// Hard Assertion - if anyone of the below assertions fails, test will stop the
		// execution of next
		// Assert.assertTrue(actualProductInfoMap.get("name").equals("MacBook Pro"));
		// Assert.assertTrue(actualProductInfoMap.get("Brand").equals("Apple"));
		// Assert.assertTrue(actualProductInfoMap.get("Availability").equals("In
		// Stock"));
		// Assert.assertTrue(actualProductInfoMap.get("price").equals("$2,000.00"));

		// To overcome hard assertion failure, we use soft assertion

		// softAssert.assertTrue(actualProductInfoMap.get("name").equals("MacBook
		// Pro11"));
		// softAssert.assertTrue(actualProductInfoMap.get("Brand").equals("Apple"));
		// softAssert.assertTrue(actualProductInfoMap.get("Availability").equals("In
		// Stock"));
		// softAssert.assertTrue(actualProductInfoMap.get("price").equals("$2,000.00"));
		// softAssert.assertAll();

		// soft assertion using assertEquals method
		softAssert.assertEquals(actualProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actualProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();
	}

	@Test(enabled = false)

	public void productInfoDescriptionTest() {
		searchResPage = accPage.doSearch("macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");

		Assert.assertTrue(
				productInfoPage.getProductInfoPageInnerText().contains("Intel, the new Core 2 Duo MacBook Pro"));
		Assert.assertTrue(
				productInfoPage.getProductInfoPageInnerText().contains("power connection and an illuminated keyboard"));
		Assert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("Next-generation wireless"));
	}

	@Test(enabled = false)
	public void addToCartTest() {
		searchResPage = accPage.doSearch("macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		String actualMessage = productInfoPage.enterQty("2").clickOnAddToCart().getCartSuccessMessage();

		System.out.println("Cart actual message is: " + actualMessage);

		Assert.assertTrue(actualMessage.contains("MacBook Pro"));

	}

}
