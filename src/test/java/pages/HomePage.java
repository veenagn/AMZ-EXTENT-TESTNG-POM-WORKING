package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class HomePage extends Base {

	static WebDriverWait wait = new WebDriverWait(driver, 5000);

	// All Page Elements to be tested in the HomePage
	@FindBy(id = "twotabsearchtextbox")
	WebElement searchtxb;

	@FindBy(className = "s-suggestion")
	List<WebElement> autoSelectItem;

	@FindBy(id = "nav-signin-tooltip")
	WebElement homesignInButton;

	@FindBy(id = "nav-link-yourAccount")
	WebElement hoverYourAccountLink;

	@FindBy(xpath = "//DIV[@id='nav-flyout-yourAccount']")
	List<WebElement> itemsInHelloSignInLink;

	@FindBy(xpath = "//span[@class='nav-action-inner']")
	WebElement altSignInButton;

	// Creating Constructors
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Actions

	// Enter partial text in the search textbox
	public void searchItem() {
		wait.until(ExpectedConditions.visibilityOf(searchtxb));
		String searchText = prop.getProperty("searchtext");
		searchtxb.sendKeys(searchText);
	}

	// Return the value of the string entered in the text box
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

	// Click on the sign on button that is displayed or from the hover menu
	public SignInPage clickSignInTooltip() throws InterruptedException {
		boolean signInDisplayed = homesignInButton.isDisplayed();
		if (!signInDisplayed) {
			Actions action = new Actions(driver);
			action.moveToElement(hoverYourAccountLink).build().perform();
			altSignInButton.click();
		} else {
			homesignInButton.click();
		}
		return new SignInPage(driver);
	}

}
