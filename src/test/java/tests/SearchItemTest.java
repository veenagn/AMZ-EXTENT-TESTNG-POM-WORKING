package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;

import base.Base;
import pages.HomePage;
import pages.ItemDetailsPage;
import pages.RefineByCategoryPage;
import pages.RefineByPrimePage;
import pages.SearchResultPage;

//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterTest;

public class SearchItemTest extends Base {
	
	// This constructor will inherit the Base class constructor from the base class
	public SearchItemTest() {
		super();
	}

	@Test(priority=4)
	public void searchForItem() {
	//public SearchResultPage searchForItem() {
		test=extent.createTest("Search for Item : Verify by Search Page Title");
		test.log(Status.PASS, "Navigate to Test URL");
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
		
		//return new SearchResultPage(driver);
	}
	
	@Test(priority=5)
	public void selectPrimeItems() {
		test=extent.createTest("Select Prime Items : Verify by the Prime Items title");
		test.log(Status.PASS, "Navigate to Test URL");
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
		
		//SearchResultPage searchResultPage = searchForItem();
		
		RefineByPrimePage primeResultPage= searchResultPage.clickPrimeCheckbox();
		test.log(Status.PASS, "Verified prime items displayed by lable: " +primeResultPage.getPrimeLable());
		
	}
	
	//@Test(priority=6)
	public void selectCategory() {
		test=extent.createTest("Select Casserole Dishes from the submenu on left after filtering for prime items : Verify by the title");
		test.log(Status.PASS, "Navigate to Test URL");
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
		
		RefineByPrimePage refineByPrimePage = searchResultPage.clickPrimeCheckbox();
		test.log(Status.PASS, "Verified prime items displayed by lable: " +refineByPrimePage.getPrimeLable());
		
		RefineByCategoryPage refineByCatPage = refineByPrimePage.clickSubMenuItem();
		test.log(Status.PASS, "Verified the sub menu selected by lable: " +refineByCatPage.getSubMenuLable());		
	}
	
	//@Test(priority=7)
	public void selectItemToPurchase() throws Exception {
		test=extent.createTest("Select item to buy from result page  : Verify by the title");
		test.log(Status.PASS, "Navigate to Test URL");
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
		
		RefineByPrimePage refineByPrimePage = searchResultPage.clickPrimeCheckbox();
		test.log(Status.PASS, "Verified prime items displayed by lable: " +refineByPrimePage.getPrimeLable());
		
		RefineByCategoryPage refineByCatPage = refineByPrimePage.clickSubMenuItem();
		test.log(Status.PASS, "Verified the sub menu selected by lable: " +refineByCatPage.getSubMenuLable());	
		
		ItemDetailsPage itemDetails = refineByCatPage.findItemToPurchase();
		itemDetails.verifyProductTitle();
		test.log(Status.PASS,"The item selected to buy is :" +itemDetails.getProductTitle());
		
	}	
}
