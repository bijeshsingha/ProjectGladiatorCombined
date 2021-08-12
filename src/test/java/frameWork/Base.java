package frameWork;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base {
	public WebDriver driver;
	public Properties prop = new Properties();
	@BeforeTest // Pre-Condition : will be executed before test execute (Browser starting
	// process will be mentioned here).
	public void beforeTest() throws Exception, Exception {
		prop.load(new FileInputStream("src/test/resources/settings.property"));
		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));
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