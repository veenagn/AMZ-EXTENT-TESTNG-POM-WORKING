package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Base;
import pages.HomePage;
import pages.ItemDetailsPage;
import pages.RefineByCategoryPage;
import pages.RefineByPrimePage;
import pages.SearchResultPage;

public class ShopForItemTest extends Base{
	
	public ShopForItemTest() {
		super();
	}
	
  @Test
  public void shopForItem() throws Exception {
	  
	  test = extent.createTest("Shop for item : Select Colour and quantity and add to basket");
		test.log(Status.PASS, "Navigate to Test URL");
		HomePage home = new HomePage(driver);
		home.searchItem();
		String partialtxt = home.getSearchItem();
		test.log(Status.PASS, "Entered search item - " + partialtxt);

		SearchResultPage searchResultPage = home.selectFromAutoSelect();
		String fulltxt = home.getSearchItem();
		test.log(Status.PASS, "Selected " + fulltxt + " from the auto-select drop down");

		String actualSearchPageTitle = prop.getProperty("expectedResultTitle");
		String expectedSearchPageTitle = searchResultPage.getResultPageTitle();
		if (actualSearchPageTitle.equalsIgnoreCase(expectedSearchPageTitle)) {
			test.log(Status.PASS, "The expected Page Title: " + expectedSearchPageTitle
					+ " match the actual Page Title: " + actualSearchPageTitle);
		} else {
			test.log(Status.WARNING, "The expected Page Title: " + expectedSearchPageTitle
					+ " does not match the actual Page Title: " + actualSearchPageTitle);
		}

		RefineByPrimePage refineByPrimePage = searchResultPage.clickPrimeCheckbox();
		test.log(Status.PASS, "Verified prime items displayed by lable: " + refineByPrimePage.getPrimeLable());

		RefineByCategoryPage refineByCatPage = refineByPrimePage.clickSubMenuItem();
		test.log(Status.PASS, "Verified the sub menu selected by lable: " + refineByCatPage.getSubMenuLable());

		ItemDetailsPage itemDetails = refineByCatPage.findItemToPurchase();
		itemDetails.verifyProductTitle();
		test.log(Status.PASS, "The item selected to buy is :" + itemDetails.getProductTitle());
		
		itemDetails.selectColour();
		test.log(Status.PASS, "The colour is selected is : " +itemDetails.getColour());	  
		
		String quan = itemDetails.selectQuantity();
		test.log(Status.PASS, "The quantity selected is : " +quan);					
		}		
  }

