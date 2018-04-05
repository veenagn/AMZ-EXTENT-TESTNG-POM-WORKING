package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ITestResult result;
	//public static WebDriverWait wait = new WebDriverWait(driver, 5000);
	
	// Read the properties files
	public Base() {
		try {
			prop = new Properties();
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("config/config.properties").getFile());
			prop.load(new FileReader(file));
		} catch (IOException e) {
			System.out.println("Properties file not found: " + e.getMessage());
		}
	}

	// Initialising the Web Driver - Before Method
	//@BeforeClass
	@Parameters("browser")
	@BeforeMethod
	public static void initialise(String browser) {
		//test=extent.createTest("Open test URL");
		browser = prop.getProperty("browserName");
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "//Users//veena//eclipse-workspace//geckodriver");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("Safari")) {
				driver = new SafariDriver();
			} else if (browser.equalsIgnoreCase("Chrome")) {
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
		//test.log(Status.PASS, "Browser: " +browser+ " opened the site under test: " +url);
	}
	
	public static String capture(WebDriver driver,String screenShotName) throws IOException
    {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		                //after execution, you could see a folder "TestsScreenshots" under src folder
		 String destination = System.getProperty("user.dir") + "/TestsScreenshots/"+screenShotName+dateName+".png";
		 File finalDestination = new File(destination);
		 FileUtils.copyFile(source, finalDestination);
		                //Returns the captured file path
		 return destination;
    }
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE)
        {
            String screenShotPath = capture(driver, "screenShotName");
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());
            //test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
            test.fail("Snapshot below: ").addScreenCaptureFromPath(screenShotPath);
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
	}

	@BeforeSuite
	public void startReport() {

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/MyOwnReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", "Mac Sierra");
		extent.setSystemInfo("Host Name", "Veena");
		extent.setSystemInfo("Environment", "Home");
		extent.setSystemInfo("User Name", "Veena Nair");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
		htmlReporter.config().setReportName("My Own Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}

	//@AfterClass
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	@AfterSuite()
	public void closeReport() {
		extent.flush();
	}
}
