package frameWork;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class SignupBase {
	
  public WebDriver driver;				//WebDriver instance
  public WebDriverWait wt;           	//WebDriverWait object
  public Properties prop;				//Properties instance
  public ExtentReports report;			//ExtentReports instance
 
  @BeforeTest
	public void beforeTest() throws Exception {
	  	
	  	// load the property file
	  	prop = new Properties();
		prop.load(new FileInputStream("src/test/resources/SignUp.property"));
		
		// set the path of .exe file
		System.setProperty(prop.getProperty("ckey"), prop.getProperty("cvalue"));
		driver = new ChromeDriver();
		
		// maximize the window
		driver.manage().window().maximize();
				
		// adding implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//To open the url		
		driver.get(prop.getProperty("url"));
				
	}

  @AfterTest
	public void afterTest() {
	  	//for writing report in physical memory
		report.flush();
		// quit the browser
		driver.quit();
	}
}
