package com.TestCases;
import java.util.HashMap;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.DataProviders.DataProviders;
import com.PageObjects.AccountCreationPage;
import com.PageObjects.HomePage;
import com.PageObjects.IndexPage;
import com.PageObjects.LoginPage;
import com.Utilities.ExtentManager;
import com.Utilities.Log;
import com.Utilities.RandomClass;
import com.aventstack.extentreports.Status;

public class AccountCreationPageTest extends BaseClass {
	private IndexPage indexPage;
	private LoginPage loginPage;
	private AccountCreationPage acountCreationPage;
	private HomePage homePage;
	RandomClass ran = new RandomClass();
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Sanity",dataProvider = "email", dataProviderClass = DataProviders.class)
	public void verifyCreateAccountPageTest(String email) throws Throwable {
		Log.startTestCase("verifyCreateAccountPageTest");
		indexPage= new IndexPage();
		loginPage=indexPage.clickOnSignIn();
		acountCreationPage=loginPage.createNewAccount(email);
		boolean result=acountCreationPage.validateAcountCreatePage();
		Assert.assertTrue(result);
		Log.endTestCase("verifyCreateAccountPageTest");
	}
	
	//@Test(groups = "Regression",dataProvider = "newAcountDetailsData",dataProviderClass = DataProviders.class)
	public void createAccountTest(HashMap<String,String> hashMapValue) throws Throwable {
		Log.startTestCase("createAccountTest");
		indexPage= new IndexPage();
		loginPage=indexPage.clickOnSignIn();
		//acountCreationPage=loginPage.createNewAccount(hashMapValue.get("Email"));
		String newemail = hashMapValue.get("Email");
		String NewEmail = newemail.replace("newtest", ran.RandomEmail());
		acountCreationPage=loginPage.createNewAccount(NewEmail);
		System.out.println(NewEmail);
		acountCreationPage.createAccount(
				hashMapValue.get("Gender"),
				hashMapValue.get("FirstName"),
				hashMapValue.get("LastName"),
				hashMapValue.get("SetPassword"),
				hashMapValue.get("Day"),
				hashMapValue.get("Month"),
				hashMapValue.get("Year"),
				hashMapValue.get("Company"),
				hashMapValue.get("Address"),
				hashMapValue.get("City"),
				hashMapValue.get("State"),
				hashMapValue.get("Zipcode"),
				hashMapValue.get("Country"),
				hashMapValue.get("MobilePhone"));
		homePage=acountCreationPage.validateRegistration();
		Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", homePage.getCurrURL());
		Log.endTestCase("createAccountTest");
	}

}
