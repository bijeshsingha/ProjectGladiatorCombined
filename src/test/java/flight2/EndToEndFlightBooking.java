package flight2;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class EndToEndFlightBooking {

	WebDriver driver;
	Properties prop = new Properties();
	ExtentReports report;
	ExtentTest test;

	@BeforeTest
	public void beforeTest() throws Exception {

		// load the property file
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/test/resources/f2settings.property"));

		// set the path of .exe file
		System.setProperty(prop.getProperty("ckey"), prop.getProperty("cvalue"));
		driver = new ChromeDriver();

		// maximize the window
		driver.manage().window().maximize();

		// adding implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// tc_open01 - To open the website
		driver.get(prop.getProperty("url"));

		// creating extent report
		report = new ExtentReports();

		// specifying that we need html report, with name endtoend_flight.html
		report.attachReporter(new ExtentHtmlReporter("endtoend_flight.html"));

	}

	@AfterTest
	public void afterTest() {

		// quit the browser
		driver.quit();

		// for writing report in physical memory
		report.flush();
	}

	// tc_FlightBooking - To verify end to end flow of flight booking till payment
	// page
	@Test(enabled = true, dataProvider = "dp1")
	public void endToEnd(String email, String f, String l, String contact) throws Exception {

		test = report.createTest("End to End Flight Booking");

		// passing step information
		test.info("Loading property file");

		// loading the property file
		prop.load(new FileInputStream("src/test/resources/f2settings.property"));

		test.info("Click on search and select first flight");
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();

		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();

		test.info("Enter valid data in Review tab and continue");
		// enter review tab details
		driver.findElement(By.cssSelector(prop.getProperty("insurance_no"))).click();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).clear();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).sendKeys(email);

		Thread.sleep(3000);
		driver.findElement(By.id(prop.getProperty("review_submit_id"))).click();

		test.info("Enter valid data in Traveller tab and continue");
		// enter traveler tab details
		WebElement e = driver.findElement(By.id(prop.getProperty("travel_title_id")));
		Select dd = new Select(e);
		dd.selectByValue("Mr");

		driver.findElement(By.id(prop.getProperty("travel_firstname_id"))).sendKeys(f);
		driver.findElement(By.id(prop.getProperty("travel_lastname_id"))).sendKeys(l);
		Thread.sleep(3000);
		driver.findElement(By.id(prop.getProperty("travel_contact_id"))).clear();
		driver.findElement(By.id(prop.getProperty("travel_contact_id"))).sendKeys(contact);

		// click on continue booking
		driver.findElement(By.id(prop.getProperty("travel_submit_id"))).click();

		test.info("Check if Payment tab loaded");
		// check for payment button
		String str = driver.findElement(By.xpath(prop.getProperty("payment_button"))).getAttribute("ng-click")
				.toString();
		System.out.println("ng-click = " + str);
		Thread.sleep(3000);

		Assert.assertTrue(str.contains("MakevalidateCard"), "Website not working for end to end flight booking");

		test.info("Test Passed");
	}

	@DataProvider
	public Object[][] dp1() throws Exception {
		// valid data from Excel
		XSSFWorkbook flight_data = new XSSFWorkbook(new FileInputStream("src/test/resources/Excel/FlightData.xlsx"));

		Object data[][] = new Object[1][4];

		for (int i = 0; i < 4; i++) {
			data[0][i] = flight_data.getSheet("Sheet1").getRow(1).getCell(i).toString();
		}

		return data;
	}
}
