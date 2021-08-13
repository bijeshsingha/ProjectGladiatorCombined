package frameWork;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Base {
	
	//Webdriver object
	public WebDriver driver; 
	
	//Property instance
	public Properties prop = new Properties();
	
	//Wait object
	public WebDriverWait wt;
	
	//ExtentReports object
	public ExtentReports report;
	
	
	//before test
	@BeforeTest 
	public void beforeTest() throws Exception, Exception {
		prop.load(new FileInputStream("src/test/resources/settings.property")); //load property file
		System.setProperty(prop.getProperty("ckey"), prop.getProperty("cvalue")); //setting property
		driver = new ChromeDriver(); // creating instance of new chrome driver
		driver.manage().window().maximize(); // maximize window
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // implicit wait time 20s
		driver.get(prop.getProperty("url")); //open url
		wt = new WebDriverWait(driver, 2);
		report = new ExtentReports();
		report.attachReporter(new ExtentHtmlReporter("hBooking.html"));
	}
	
	//after test
	@AfterTest
	public void afterTest() throws Exception {
		
		// for writing report in physical memory
		report.flush();
		
		Thread.sleep(2000);
		driver.quit();
	}
}