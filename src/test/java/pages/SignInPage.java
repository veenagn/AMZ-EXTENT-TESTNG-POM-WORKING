package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;
import junit.framework.Assert;

public class SignInPage extends Base{
	
	static WebDriverWait wait = new WebDriverWait(driver, 1000);
	
	//WebElements
	@FindBy(css = "h1.a-spacing-small") WebElement signInLable;
	
	@FindBy(id = "ap_email") WebElement userNameTxb;
	
	@FindBy(id = "continue") WebElement signInContinueButton;
	
	@FindBy(id="auth-error-message-box") WebElement userNameErrorMessageBox;
	
	@FindBy(css =".a-list-item") WebElement errorMessage;
	
	//Constructor
	public SignInPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String verifySignInLable() {
		Assert.assertTrue(signInLable.isDisplayed());
		return signInLable.getText();
	}
	
	public void  enterUserName(String username) {
		wait.until(ExpectedConditions.visibilityOf(userNameTxb));
		userNameTxb.sendKeys(username);
	}
	
	public String getUserNameValue() {
		return userNameTxb.getAttribute("value");
	}
	
	public PasswordPage clickSignInContinueButton() {
		wait.until(ExpectedConditions.elementToBeClickable(signInContinueButton));
		signInContinueButton.click();
		return new PasswordPage(driver);
	}
	
	public void verifyErrorMessage() {
		wait.until(ExpectedConditions.visibilityOf(userNameErrorMessageBox));
		Assert.assertTrue(userNameErrorMessageBox.isDisplayed());
	}
	
	public String getErrorUserMessage() {
		return errorMessage.getText();
	}
}
