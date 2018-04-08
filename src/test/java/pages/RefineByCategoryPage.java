package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class RefineByCategoryPage extends Base {
	
	static WebDriverWait wait = new WebDriverWait(driver, 5000);
	
	@FindBy(linkText = "Casserole Dishes")
	WebElement subMenuItemLabel;
	
//	@FindBy(id = "pagnNextString")
//	WebElement nextPage;
//	
//	@FindBy(xpath ="//h2[@class='a-size-base s-inline  s-access-title  a-text-normal']")
//	List<WebElement> linkElement;

	boolean itemfound = false;
	String itemToPurchase = prop.getProperty("itemToPurchase");

	// Creating Constructors
	public RefineByCategoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getSubMenuLable() {
		return subMenuItemLabel.getText();
	}
	//Method/Function to find an item from the search result pages
	public ItemDetailsPage findItemToPurchase() throws Exception {
		//refresh browser - else getting stale element exception
		driver.navigate().refresh();
		while (!itemfound) {
			List<WebElement> links = driver
					.findElements(By.xpath("//h2[@class='a-size-base s-inline  s-access-title  a-text-normal']"));
			//List<WebElement> links = linkElement;
			for (WebElement itemToShop : links) {
				wait.until(ExpectedConditions.visibilityOf(itemToShop));
				if (itemToShop.getText().equalsIgnoreCase(itemToPurchase)) {
					itemToShop.click();
					itemfound = true;
					break;
				}
			}
			//if itemfound is still false - click on the next page link
			if (!itemfound) {
				try {
					WebElement nextPage = driver.findElement(By.id("pagnNextString"));
					wait.until(ExpectedConditions.elementToBeClickable(nextPage));
					nextPage.click();
				} catch (Exception e) {
					System.out.println("Exception raised: Element not found" + e.getMessage());
				}
			}

		}
		return new ItemDetailsPage(driver);
	}

}
