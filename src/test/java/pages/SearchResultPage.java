package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;

public class SearchResultPage extends Base {
	
	@FindBy(name="s-ref-checkbox-419158031") WebElement primeCheckbox;
	
	@FindBy(css ="a.a-color-base:nth-child(2)")  WebElement primeLable;
	

	// Creating Constructors
	public SearchResultPage(WebDriver driver) {
		// this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public String getResultPageTitle() {
		return driver.getTitle();
	}

	// Method or function to verify Home Page
	public void verifySearchResultPageTitle() {
		String actualSearchPageTitle = prop.getProperty("expectedResultTitle");
		String expectedSearchPageTitle = getResultPageTitle();
		Assert.assertEquals(expectedSearchPageTitle, actualSearchPageTitle);
	}
	
	public void clickPrimeCheckbox() {
		primeCheckbox.click();
	}
	
	public String getPrimeLable() {
		return primeLable.getText();
	}
}
