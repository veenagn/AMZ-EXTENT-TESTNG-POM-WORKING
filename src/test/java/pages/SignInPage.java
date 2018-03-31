package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import junit.framework.Assert;

public class SignInPage extends Base{

	//WebElements
	@FindBy(css = "h1.a-spacing-small") WebElement signInLable;
	
	@FindBy(id = "ap_email") WebElement userNameTxb;
	
	@FindBy(id = "continue") WebElement signInContinueButton;
	
	//Constructor
	public SignInPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String verifySignInLable() {
		Assert.assertTrue(signInLable.isDisplayed());
		return signInLable.getText();
	}
	
	public void  enterUserName() {
		String username=prop.getProperty("expecteduser");
		userNameTxb.sendKeys(username);
	}
	
	public String getUserNameValue() {
		return userNameTxb.getAttribute("Value");
	}
	
	public PasswordPage clickSignInContinueButton() {
		signInContinueButton.click();
		return new PasswordPage(driver);
	}
}
