package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;

import base.Base;
import pages.HomePage;
import pages.SearchResultPage;

//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterTest;

public class SearchItemTest extends Base {
	
	// This constructor will inherit the Base class constructor from the base class
	public SearchItemTest() {
		super();
	}

	@Test(priority=4)
	public void verifySearchItem() {
		test=extent.createTest("Verify the search page title");
		HomePage home = new HomePage(driver);
		home.searchItem();
		String partialtxt=home.getSearchItem();
		test.log(Status.PASS, "Entered search item - " +partialtxt);
		
		SearchResultPage searchResultPage = home.selectFromAutoSelect();
		String fulltxt=home.getSearchItem();
		test.log(Status.PASS, "Selected " +fulltxt+  " from the auto-select drop down");
		
		searchResultPage.verifySearchResultPageTitle();
		String actualSearchPageTitle = prop.getProperty("expectedResultTitle");
		String expectedSearchPageTitle = searchResultPage.getResultPageTitle();
		test.log(Status.PASS, "The expected Page Title: " +expectedSearchPageTitle+ " match the actual Page Title: " +actualSearchPageTitle);
	}
	
	@Test(priority=5)
	public void verifyPrimeItems() {
		test=extent.createTest("Select Prime Items : Verify by the Prime Items title");
		HomePage home = new HomePage(driver);
		home.searchItem();
		String partialtxt=home.getSearchItem();
		test.log(Status.PASS, "Entered search item - " +partialtxt);
		
		SearchResultPage searchResultPage = home.selectFromAutoSelect();
		String fulltxt=home.getSearchItem();
		test.log(Status.PASS, "Selected " +fulltxt+  " from the auto-select drop down");
		
		searchResultPage.verifySearchResultPageTitle();
		String actualSearchPageTitle = prop.getProperty("expectedResultTitle");
		String expectedSearchPageTitle = searchResultPage.getResultPageTitle();
		test.log(Status.PASS, "The expected Page Title: " +expectedSearchPageTitle+ " match the actual Page Title: " +actualSearchPageTitle);
		
		searchResultPage.clickPrimeCheckbox();
		test.log(Status.PASS, "Verified prime items displayed by lable: " +searchResultPage.getPrimeLable());
		
	}

}
