package oskar.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * This class is responsible for setting up appropriate drivers for different browsers and
 * performing clean up after the test has been ran
 * 
 * @author Oskars Dauksts (dauksts.oskars@gmail.com)
 *
 */
public class DriverSetUp {

	protected static WebDriver driver;

	@BeforeClass(alwaysRun = true)
	protected final void init() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
		//Declare drivers and their capabilities
		//Chrome, firefox, internet explorer etc.
		ChromeOptions options = new ChromeOptions();
	     options.addArguments("--disable-extensions");
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		

	}

	//Clean up. Logs final results of steps passed and fail
	//quits the driver
	@AfterClass(alwaysRun = true)
	protected final void cleanUp() {
		System.out.println("*************************************");
		System.out.println("Steps Passed: " + Step.PASS);
		System.out.println("Steps Failed " + Step.FAIL);
		System.out.println("*************************************");
		driver.quit();
		System.clearProperty("webdriver.gecko.driver");
		;
	}
}
