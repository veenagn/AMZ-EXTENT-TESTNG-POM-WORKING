package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class RefineByPrimePage extends Base {
	
	static WebDriverWait wait = new WebDriverWait(driver, 5000);
	
	@FindBy(css ="a.a-color-base:nth-child(2)")  WebElement primeLabel;
	
	@FindBy(xpath="//SPAN[@class='a-size-small a-color-base'][text()='Casserole Dishes']") WebElement subMenuItem;
	
	// Creating Constructors
	public RefineByPrimePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
	}
	
	public String getPrimeLable() {
		return primeLabel.getText();
	}
	
	public RefineByCategoryPage clickSubMenuItem() {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(subMenuItem));
		subMenuItem.click();
		return new RefineByCategoryPage(driver);
	}		
}
