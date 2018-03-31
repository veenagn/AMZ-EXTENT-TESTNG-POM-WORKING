package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;

public class SearchResultPage extends Base {

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
}
