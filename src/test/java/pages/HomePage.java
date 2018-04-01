package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class HomePage extends Base {

	static WebDriverWait wait = new WebDriverWait(driver,20);

	//All Page Elements to be tested in the HomePage	
	@FindBy(id = "twotabsearchtextbox") WebElement searchtxb;

	@FindBy(className = "s-suggestion") List<WebElement> autoSelectItem;

	@FindBy(id = "nav-signin-tooltip") WebElement homesignInButton;

	@FindBy(id = "nav-link-yourAccount") WebElement hoverYourAccountLink;

	@FindBy(className = "nav-text") List<WebElement> itemsInHelloSignInLink;


	// Creating Constructors
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Actions
	
	// Enter partial text in the search textbox
	public void searchItem() {
		String searchText = prop.getProperty("searchtext");
		searchtxb.sendKeys(searchText);
	}
	
	//Return the value of the string entered in the text box
	public String getSearchItem() {
		return searchtxb.getAttribute("value");
	}
	
	// When you select an item from the auto-select option, you are taken to the
	// search result page, therefore returning the search result page
	public SearchResultPage selectFromAutoSelect() {
		String selectItem = prop.getProperty("autoselect");
		List<WebElement> optionsToSelect = autoSelectItem;
		for (WebElement option : optionsToSelect) {
			wait.until(ExpectedConditions.visibilityOf(option));
			if (option.getText().equalsIgnoreCase(selectItem)) {
				option.click();
				break;
			}
		}
		return new SearchResultPage(driver);
	}
	
	//Click on the sign on button that is displayed as soon as on home page
	public SignInPage clickSignInTooltip() {
		wait.until(ExpectedConditions.elementToBeClickable(homesignInButton));
		homesignInButton.click();
		return new SignInPage(driver);
	}
	
}

