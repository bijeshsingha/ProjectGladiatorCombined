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
import org.testng.annotations.AfterTest;

public class SignupBase {
	
  public WebDriver driver;
  public WebDriver wt;
  public WebDriver wt1;
  
  public ExtentReports report;
 
  
  
	
	@BeforeTest
	public void beforeTest() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/test/resources/SignUp.property"));
		
		report=new ExtentReports();
		  report.attachReporter(new ExtentHtmlReporter("SignUpReport.html"));
		 
		
		// set the path of .exe file
		// set the path of .exe file
				System.setProperty("webdriver.chrome.driver","E:\\chromedriver.exe");
				driver = new ChromeDriver();
				
				// maximize the window
				driver.manage().window().maximize();
				
				// adding implicit wait
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				// tc_open01 - To open the website
				driver.get(prop.getProperty("url"));
				
			}
	

	@AfterTest
	public void afterTest() {
		report.flush();
		driver.close();
		driver.quit();
	}
}
