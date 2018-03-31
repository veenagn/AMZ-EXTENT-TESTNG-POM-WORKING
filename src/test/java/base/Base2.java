package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Base2 {

	public static WebDriver driver;
	public static Properties prop;	

	//Read the properties files
	public Base2() {
		try {
			prop = new Properties();
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("config/config.properties").getFile());
			prop.load(new FileReader(file));
		} catch (IOException e) {
			System.out.println("Properties file not found: " + e.getMessage());
		}
	}
	
	//Initialising the Web Driver - Before Method
	public static void initialise() {
		String data = prop.getProperty("browser");
		try {
			if (data.equals("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "//Users//veena//eclipse-workspace//geckodriver");
				driver = new FirefoxDriver();
			} else if (data.equals("Safari")) {
				driver = new SafariDriver();
			} else if (data.equals("Chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-fullscreen");
				driver = new ChromeDriver(options);
			}
		} catch (Exception e) {
			System.out.println("Not able to open Browser --- " + e.getMessage());
		}
		String url = prop.getProperty("url");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get(url);
	}		
}

