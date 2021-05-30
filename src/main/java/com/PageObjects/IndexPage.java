package com.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ActionDrivers.Action;
import com.BaseClass.BaseClass;


public class IndexPage extends BaseClass {

	Action action = new Action();

	@FindBy(xpath = "//a[@class='login']")
	private WebElement signInBtn;

	@FindBy(xpath = "//input[@id='search_query_top']")
	private WebElement searchProductBox;

	@FindBy(xpath = "//img[@class='logo img-responsive']")
	private WebElement myStoreLogo;

	@FindBy(name = "submit_search")
	private WebElement searchButton;

	public IndexPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public LoginPage clickOnSignIn() {
		action.fluentWait(getDriver(), signInBtn, 10);
		action.click(getDriver(), signInBtn);
		return new LoginPage();
		
	}
	
	public boolean validateLogo() throws Throwable {
		return action.isDisplayed(getDriver(), myStoreLogo);
	}
	
	public String getMyStoreTitle() {
		String myStoreTitel=getDriver().getTitle();
		return myStoreTitel;
	}
	
	public SearchResultPage searchProduct(String productName) throws Throwable {
		action.type(searchProductBox, productName);
		action.scrollByVisibilityOfElement(getDriver(), searchButton);
		action.click(getDriver(), searchButton);
		Thread.sleep(3000);
		return new SearchResultPage();
	}

}
