/**
 * 
 */
package com.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.DataProviders.DataProviders;
import com.PageObjects.AddToCartPage;
import com.PageObjects.IndexPage;
import com.PageObjects.SearchResultPage;
import com.Utilities.Log;


/**
 * @author Hitendra
 *
 */
public class AddToCartPageTest extends BaseClass {
	
	private IndexPage index;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = {"Regression","Sanity"}, dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void addToCartTest(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("addToCartTest");
		index= new IndexPage();
		searchResultPage=index.searchProduct(productName);
		addToCartPage=searchResultPage.clickOnProduct();
		addToCartPage.enterQuantity(qty);
		addToCartPage.selectSize(size);
		addToCartPage.clickOnAddToCart();
		boolean result=addToCartPage.validateAddtoCart();
		Assert.assertTrue(result);
		Log.endTestCase("addToCartTest");
		
	}
}
