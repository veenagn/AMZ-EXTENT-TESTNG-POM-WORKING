package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;
import junit.framework.Assert;

public class ItemDetailsPage extends Base {
	
	static WebDriverWait wait = new WebDriverWait(driver, 5000);
	
	@FindBy(id="productTitle") WebElement productTitle;
	
	@FindBy(id="a-autoid-8-announce") WebElement colourOption;
	
	@FindBy(id="priceblock_ourprice") WebElement itemPrice;
	
	@FindBy(id="quantity") WebElement itemQuantity;
	
	@FindBy(id="add-to-cart-button") WebElement addToCart;
	
	public ItemDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getProductTitle() {
		return productTitle.getText(); 
	}
	
	public void verifyProductTitle() {
		String itemToPurchase = prop.getProperty("itemToPurchase");
		String actualItem = getProductTitle();
		Assert.assertEquals(itemToPurchase, actualItem);
	}
	
	public void selectColour() {
		colourOption.click();
		wait.until(ExpectedConditions.elementToBeClickable(colourOption));
	}
}
