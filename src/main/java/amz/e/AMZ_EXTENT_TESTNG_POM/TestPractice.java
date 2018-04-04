package amz.e.AMZ_EXTENT_TESTNG_POM;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestPractice {

	public static WebDriver driver;

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-fullscreen");
		driver = new ChromeDriver(options);

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://www.amazon.co.uk");

		WebElement searchtxb = driver.findElement(By.id("twotabsearchtextbox"));
		searchtxb.sendKeys("Le Cre");

		List<WebElement> optionsToSelect = driver.findElements(By.className("s-suggestion"));
		for (WebElement option : optionsToSelect) {
			// wait.until(ExpectedConditions.visibilityOf(option));
			if (option.getText().equalsIgnoreCase("le creuset in Home & Kitchen")) {
				option.click();
				break;
			}
		}
		boolean itemfound = false;
		//String itemToPurchase = "Le Creuset 21177261302430 Round Cast Iron Casserole Dish, cast iron, Chiffon Pink, 26 x 26 x 14.4 cm";
		String itemToPurchase="Le Creuset Premium Pastry Brush, Cherry";
		
		
		while (!itemfound) {
			List<WebElement> links = driver
					.findElements(By.xpath("//h2[@class='a-size-base s-inline  s-access-title  a-text-normal']"));
			System.out.println(links.size());
			System.out.println("I am here..");
			for (WebElement itemToShop : links) {
				System.out.println(">>>>>ITEMS: " +itemToShop.getText());
				if (itemToShop.getText().equalsIgnoreCase(itemToPurchase)) {
					System.out.println("I found you!!");
					itemToShop.click();
					itemfound = true;
					break;
				}
			}
			if (!itemfound) {
					try {
						WebElement nextPage = driver.findElement(By.id("pagnNextString"));
						WebDriverWait wait = new WebDriverWait(driver,2000);
						wait.until(ExpectedConditions.elementToBeClickable(nextPage));
						System.out.println("Nope- in else - click next page");
						nextPage.click();
					} catch (Exception e) {						
						System.out.println("Exception raised: Element not found" +e.getMessage());
					}
			}
		}//close of while loop
	}
	//driver.close();
}
