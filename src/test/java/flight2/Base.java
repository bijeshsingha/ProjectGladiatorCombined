package flight2;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class Base {

	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		
		// load the property file
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/test/resources/settings.property"));
		
		// set the path of .exe file
		System.setProperty(prop.getProperty("ckey"), prop.getProperty("cvalue"));
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
		
		driver.close();
	}

}
