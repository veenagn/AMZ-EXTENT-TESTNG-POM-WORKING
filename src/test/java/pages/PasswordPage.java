package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import junit.framework.Assert;

public class PasswordPage  extends Base {
	
	@FindBy(xpath="//*[@for='ap_password']")  WebElement passwordLable;
	
	@FindBy(id="ap_password") WebElement passwrdTxb;
	
	@FindBy(id="signInSubmit") WebElement passWordSignInButton;
	

	public PasswordPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyPasswordPage() {
		Assert.assertTrue(passwordLable.isDisplayed());
		return passwordLable.getText();
	}
	
	public void enterPassword(String psswrd ) {
		passwrdTxb.sendKeys(psswrd);
	}
	
	public String getPasswordValue() {
		return passwrdTxb.getAttribute("value");	
	}
	
	public SignedOnPage clickPasswordSignInButton() {
		passWordSignInButton.click();
		return new SignedOnPage(driver);
	}	
}
