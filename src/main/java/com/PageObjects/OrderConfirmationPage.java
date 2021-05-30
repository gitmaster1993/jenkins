/**
 * 
 */
package com.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ActionDrivers.Action;
import com.BaseClass.BaseClass;

/**
 * @author Hitendra
 *
 */
public class OrderConfirmationPage extends BaseClass {
	
	Action action= new Action();
	
	@FindBy(xpath="//p/strong[contains(text(),'Your order on My Store is complete.')]")
	private WebElement confirmMessag;
	
	public OrderConfirmationPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public String validateConfirmMessage() {
		String confirmMsg=confirmMessag.getText();
		return confirmMsg;
	}

	
}
