package frameWork;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base {
	public WebDriver driver;
	@BeforeTest // Pre-Condition : will be executed before test execute (Browser starting
	// process will be mentioned here).
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "/Users/nikhilpujari/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	@AfterTest
	public void afterTest() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}
}
