package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import junit.framework.Assert;

public class SignedOnPage extends Base {
	
	@FindBy(id = "nav-link-yourAccount") WebElement hoverYourAccountLink;

	public SignedOnPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyLogIn() {
		Assert.assertTrue(hoverYourAccountLink.isDisplayed());
		return hoverYourAccountLink.getText();
	}
}
