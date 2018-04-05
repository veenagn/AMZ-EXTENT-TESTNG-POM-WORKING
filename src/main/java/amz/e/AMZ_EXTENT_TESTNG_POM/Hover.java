package amz.e.AMZ_EXTENT_TESTNG_POM;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class Hover {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "//Users//veena//eclipse-workspace//geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.get("https://www.amazon.co.uk");
		
		Thread.sleep(11000);
		// Element to hover over
		WebElement homesignInButton = driver.findElement(By.id("nav-signin-tooltip"));
		
		boolean isdisplayed = homesignInButton.isDisplayed();
		System.out.println("Am I displayed: " +isdisplayed);
		if(!isdisplayed) {
			System.out.println("I am not displayed , so going to next element");
			WebElement practise = driver.findElement(By.id("nav-link-yourAccount"));
			Actions action = new Actions(driver);
			action.moveToElement(practise).build().perform();
			System.out.println("I am hovering!");
			List<WebElement> list = driver.findElements(By.className("nav-text"));
			//List<WebElement> list = driver.findElements(By.xpath("//DIV[@id='nav-flyout-yourAccount']"));
			for (WebElement option : list) {
				if (!option.getText().isEmpty()) {
					System.out.println(">>>>>>" + option.getText());
				}
			}
		}
		else {
			homesignInButton.click();
		}
		
		//WebElement practise = driver.findElement(By.id("nav-link-yourAccount"));
//		if (practise.isDisplayed()) {
//			Actions action = new Actions(driver);
//			action.moveToElement(practise).build().perform();
//			System.out.println("I am hovering!");
//			// Element list - drop down
//			List<WebElement> list = driver.findElements(By.className("nav-text"));
//			for (WebElement option : list) {
//				if (!option.getText().isEmpty()) {
//					System.out.println(">>>>>>" + option.getText());
//				}
//			}
//		} else {
//			System.out.println("Try again!");
//		}
	}
}
