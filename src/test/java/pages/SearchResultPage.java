package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.Base;

public class SearchResultPage extends Base {
	
	static WebDriverWait wait = new WebDriverWait(driver, 5000);
	
	@FindBy(name="s-ref-checkbox-419158031") WebElement primeCheckbox;
	
	@FindBy(css ="a.a-color-base:nth-child(2)")  WebElement primeLable;
	

	// Creating Constructors
	public SearchResultPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getResultPageTitle() {
		return driver.getTitle();
	}

	// Method or function to verify Home Page
	public void verifySearchResultPageTitle() {
		try {
			String actualSearchPageTitle = prop.getProperty("expectedResultTitle");
			String expectedSearchPageTitle = getResultPageTitle();
			Assert.assertEquals(actualSearchPageTitle, expectedSearchPageTitle);	
		} 
		catch(Exception e) {
			e.getMessage();
		}
	}
	
	public RefineByPrimePage clickPrimeCheckbox() {
		wait.until(ExpectedConditions.elementToBeClickable(primeCheckbox));
		primeCheckbox.click();
		return new RefineByPrimePage(driver);
	}
	
}
