package flight2;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class MyFlightScript2 extends Base {

	Properties prop = new Properties();
	ExtentTest test;
	
	@Test(priority = 1)
	public void aPropLoadMethod() throws Exception {
		
		// creating test in report
		test = report.createTest("Load Property File");
		
		// passing step information
		test.info("Loading property file");
		
		// loading the property file
		prop.load(new FileInputStream("src/test/resources/f2settings.property"));
		
		test.info("Test Passed, property file loaded.");
	}

	// tc_type_of_flight01 - checking one way trip option
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 2)
	public void checkOneway() throws Exception {

		test = report.createTest("Check One Way Flight option");
		
		test.info("Select one way trip option");
		// click on one way trip option
		driver.findElement(By.xpath(prop.getProperty("oneway"))).click();

		test.info("Click on Search");
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();

		Assert.assertTrue(driver.getCurrentUrl().contains("FlightList"), "Flight page not loaded");

		test.info("Test Passed");

	}

	// tc_type_of_flight02 - checking round trip option
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 1)
	public void checkRoundtrip() throws Exception {
		
		test = report.createTest("Check Round Trip Flight option");
		
		test.info("Click on Round Trip option");
		// click on round trip option
		driver.findElement(By.xpath(prop.getProperty("roundtrip"))).click();
		
		test.info("Select return date");
		// select random date
		driver.findElement(By.xpath(prop.getProperty("roundtrip_date"))).click();
		driver.findElement(By.id(prop.getProperty("roundtrip_id"))).click();
		
		test.info("Click on Search");
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();

		Assert.assertTrue(driver.getCurrentUrl().contains("FlightList"), "Flight Page not loaded");

		test.info("Test Passed");
	}

	// tc_type_of_flight03 - checking multi-city option
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 3)
	public void checkMulticity() throws Exception {
		
		test = report.createTest("Check Multi-city Flight option");
		
		test.info("Click on Multi-city option");
		// click on multi-city option
		driver.findElement(By.xpath(prop.getProperty("multicity"))).click();

		test.info("Select departure and destination city for first flight");
		// select required data randomly
		// departure city 1
		driver.findElement(By.id(prop.getProperty("multi_departure_id"))).click();
		driver.findElement(By.xpath(prop.getProperty("multi_departure_city"))).click();

		// destination city 1
		driver.findElement(By.id(prop.getProperty("multi_destination_id"))).click();
		driver.findElement(By.xpath(prop.getProperty("multi_destination_city"))).click();

		test.info("Select departure and destination city for second flight");
		// departure city 2
		driver.findElement(By.id(prop.getProperty("multi_departure_id2"))).click();
		driver.findElement(By.xpath(prop.getProperty("multi_departure_city2"))).click();

		// destination city 2
		driver.findElement(By.id(prop.getProperty("multi_destination_id2"))).click();
		driver.findElement(By.xpath(prop.getProperty("multi_destination_city2"))).click();

		test.info("Select date for flights");
		// date 1
		driver.findElement(By.id(prop.getProperty("date1"))).click();
		driver.findElement(By.linkText(prop.getProperty("date1_data"))).click();

		// date 2
		driver.findElement(By.id(prop.getProperty("date2"))).click();
		driver.findElement(By.linkText(prop.getProperty("date2_data"))).click();

		test.info("Click on Search");
		// click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss2"))).click();

		Assert.assertTrue(driver.getCurrentUrl().contains("multicity"), "Flight Page not loaded");

		test.info("Test Passed");

	}

	// tc_webcheck_in01 - To check Web Check-In option
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 4)
	public void webCheckIn() throws Exception {
		
		test = report.createTest("Testing Web Check-In option");
		
		test.info("Click on Web Check-In option");
		// locate web check in element
		String url = driver.findElement(By.linkText(prop.getProperty("webcheckin"))).getAttribute("href");

		// navigate to the url
		driver.navigate().to(url);
		Thread.sleep(5000);

		Assert.assertTrue(driver.getCurrentUrl().contains("web-checkin"), "Web-checkin page not working");
		
		// load the homepage again
		driver.navigate().back();
		
		test.info("Test Passed");

	}

	// tc_defence01 - To check Defense Forces Option
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 5)
	public void CheckDefence() throws Exception {
		
		test = report.createTest("Testing Defense option");
		
		test.info("Click on Defense checkbox");
		// select the defense checkbox
		driver.findElement(By.xpath(prop.getProperty("defense_tick"))).click();

		test.info("Click on Search");
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();

		test.info("Select values from dropdown");
		// select an option from dropdown
		WebElement e = driver.findElement(By.id(prop.getProperty("defense_dropdown_id")));
		e.click();
		Select dd = new Select(e);		
		dd.selectByIndex(3);
		e.click();
		Thread.sleep(3000);
		
		// check for presence of service ID input for assertion
		String opt = driver.findElement(By.id(prop.getProperty("defense_service_id")))
				.getAttribute("placeholder");

		// check if the defense form is working with service ID
		Assert.assertTrue(opt.contains("Service ID"), "Defense form not working");
		
		test.info("Close the form");
		// click on cross button at top right corner
		driver.findElement(By.className(prop.getProperty("defense_close_class"))).click();
		Thread.sleep(3000);
		
		test.info("Test Passed");
	}

	// tc_flight_book01 - To obtain flight details for a selected flight
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 6)
	public void checkFlightDetails() throws Exception {
		
		test = report.createTest("Check Flight Details");
		
		test.info("Click on Search");
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();

		test.info("click on 'Book Now' for first flight");
		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();

		test.info("Ckeck if Details are visible");
		Assert.assertTrue(driver.getCurrentUrl().contains("Review"), "Defect in List of Flights");
		
		test.info("Test Passed");
	}

	// tc_reviewdetails01 - To enter a valid email and go ahead
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 7, dataProvider = "dp1")
	public void reviewValidEmail(String email, String f, String l, String contact) throws Exception {
		
		test = report.createTest("Review flight details with valid email");
		
		test.info("Click on search and select first flight");
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();

		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();

		test.info("Enter valid email in review tab");
		// enter review tab details
		driver.findElement(By.cssSelector(prop.getProperty("insurance_no"))).click();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).clear();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).sendKeys(email);

		test.info("Click on Continue");
		Thread.sleep(3000);
		driver.findElement(By.id(prop.getProperty("review_submit_id"))).click();

		test.info("Check if next tab is loaded");
		// to check if the next tab is loaded
		String countrycode = driver.findElement(By.id(prop.getProperty("travel_assert_id")))
				.getAttribute("value");

		Assert.assertTrue(countrycode.contains("91"), "Defect in Review page email input");

		test.info("Test Passed");
	}

	// tc_reviewdetails02 - To enter an invalid email and go ahead
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 8)
	public void reviewInvalidEmail() throws Exception {
		
		test = report.createTest("Review flight details with invalid email");
		test.info("Click on search and select first flight");
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();

		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();

		test.info("Enter invalid email in review tab");
		// enter review tab details
		driver.findElement(By.cssSelector(prop.getProperty("insurance_no"))).click();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).clear();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).sendKeys(prop.getProperty("invalid_email"));

		test.info("Click on Continue");
		Thread.sleep(3000);
		driver.findElement(By.id(prop.getProperty("review_submit_id"))).click();

		test.info("Check if next tab is not loaded");
		Assert.assertTrue(driver.getCurrentUrl().contains("Review"), "Defect in Review page email input");

		test.info("Test Passed");
	}

	// tc_reviewdetails03 - To check booking with Insurance option 'Yes'
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 9, dataProvider = "dp1")
	public void reviewdInsuranceYes(String email, String f, String l, String contact) throws Exception {
		
		test = report.createTest("Review flight details with Insurance");
		test.info("Click on search and select first flight");
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();

		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();

		test.info("Click on Yes for Insurance option");
		// click on yes option
		driver.findElement(By.xpath(prop.getProperty("yesInsure"))).click();
		Thread.sleep(5000);
		
		// enter a random email from excel
		driver.findElement(By.id(prop.getProperty("review_email_id"))).clear();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).sendKeys(email);

		test.info("Check if the next tab is loaded");
		// to check if the next tab is loaded
		String countrycode = driver.findElement(By.id(prop.getProperty("travel_assert_id"))).getAttribute("value");

		Assert.assertTrue(countrycode.contains("91"), "Defect in Review page insurance option");

		test.info("Test Passed");

	}

	// tc_reviewdetails04 - To check booking with Insurance option 'No'
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 10, dataProvider = "dp1")
	public void reviewdInsuranceNo(String email, String f, String l, String contact) throws Exception {
		
		test = report.createTest("Review flight details without Insurance");
		test.info("Click on search and select first flight");
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();

		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();

		test.info("Click on No for Insurance option");
		// click on no option
		driver.findElement(By.xpath(prop.getProperty("noInsure"))).click();
		Thread.sleep(5000);

		// enter a random email from excel
		driver.findElement(By.id(prop.getProperty("review_email_id"))).clear();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).sendKeys(email);

		test.info("Check if the next tab is loaded");
		// to check if the next tab is loaded
		String countrycode = driver.findElement(By.id(prop.getProperty("travel_assert_id")))
				.getAttribute("value");

		Assert.assertTrue(countrycode.contains("91"), "Defect in Review page insurance option");

		test.info("Test Passed");

	}

	// tc_travellers01 - To enter a valid contact number and go ahead
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 11, dataProvider = "dp1")
	public void travellerSection1(String email, String f, String l, String contact) throws Exception {
		
		test = report.createTest("Check Traveller tab with valid contact number");
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
		
		// click on submit
		driver.findElement(By.id(prop.getProperty("review_submit_id"))).click();

		test.info("Enter valid title, name in Traveller tab");
		// enter traveler tab details
		WebElement e = driver.findElement(By.id(prop.getProperty("travel_title_id")));
		Select dd = new Select(e);
		dd.selectByValue("Mr");

		driver.findElement(By.id(prop.getProperty("travel_firstname_id"))).sendKeys(f);
		driver.findElement(By.id(prop.getProperty("travel_lastname_id"))).sendKeys(l);
		
		Thread.sleep(3000);
		
		test.info("Enter valid contact number in Traveller tab");
		// enter valid contact number
		driver.findElement(By.id(prop.getProperty("travel_contact_id"))).clear();
		driver.findElement(By.id(prop.getProperty("travel_contact_id"))).sendKeys(contact);

		test.info("Click on Continue");
		// click on continue booking
		driver.findElement(By.id(prop.getProperty("travel_submit_id"))).click();

		test.info("Check if Payment tab is loaded");
		// check for payment button
		String str = driver.findElement(By.xpath(prop.getProperty("payment_button"))).getAttribute("ng-click")
				.toString();
		//System.out.println("ng-click = " + str);
		Thread.sleep(3000);

		Assert.assertTrue(str.contains("MakevalidateCard"), "Defect in Traveller page contact input");

		test.info("Test Passed");
	}

	// tc_travellers01 - To enter a invalid contact number and try to go ahead
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 12, dataProvider = "dp1")
	public void travellerSection2(String email, String f, String l, String contact) throws Exception {
		
		test = report.createTest("Check Traveller tab with invalid contact number");
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

		test.info("Enter valid title, name in Traveller tab");
		// enter traveler tab details
		WebElement e = driver.findElement(By.id(prop.getProperty("travel_title_id")));
		Select dd = new Select(e);
		dd.selectByValue("Mr");

		driver.findElement(By.id(prop.getProperty("travel_firstname_id"))).sendKeys(f);
		driver.findElement(By.id(prop.getProperty("travel_lastname_id"))).sendKeys(l);
		Thread.sleep(3000);

		test.info("Enter invalid contact number in Traveller tab");
		// fetch invalid contact from property file
		driver.findElement(By.id(prop.getProperty("travel_contact_id"))).clear();
		driver.findElement(By.id(prop.getProperty("travel_contact_id"))).sendKeys(prop.getProperty("invalid_contact"));

		test.info("Click on Continue");
		// click on continue booking
		driver.findElement(By.id(prop.getProperty("travel_submit_id"))).click();

		test.info("Check if Payment tab is not loaded");
		String countrycode = driver.findElement(By.id(prop.getProperty("travel_assert_id"))).getAttribute("value");

		Assert.assertTrue(countrycode.contains("91"), "Defect in Traveller page contact input");
		
		test.info("Test Passed");
	}

	// tc_location01 - To check booking with same departure and destination location
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 13)
	public void checkSameLocation() throws Exception {
		
		test = report.createTest("Booking flight with same from, to port");
		// click on from port
		driver.findElement(By.id(prop.getProperty("location_from_id"))).click();

		test.info("Click on same from and to port city");
		// select same location for from and to port
		driver.findElement(By.id(prop.getProperty("location_city_id"))).click();

		test.info("Click on Search");
		// click on search
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();

		test.info("Check for alert");
		// check for alert
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("cannot be same"), "Website allowing same departure and destination");
		Thread.sleep(3000);
		alert.accept();
		
		test.info("Test Passed");
	}

	// tc_max_travellers01 - To increase the travellers count till 10 and check for
	// alert
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 14)
	public void maxTravelersCheck() {
		
		test = report.createTest("Checking maximum travellers allowance");
		test.info("click on travellers drop down");
		// click on traveler drop down
		driver.findElement(By.className(prop.getProperty("traveler_dropdown_class"))).click();

		test.info("Increase the travellers count till 10");
		// click on + button till max limit is reached
		WebElement plus = driver.findElement(By.xpath(prop.getProperty("plus")));
		for (int i = 0; i < 10; i++) {
			plus.click();
		}

		test.info("Check if travellers count is 9");
		String val = driver.findElement(By.id(prop.getProperty("adult_id"))).getAttribute("value");

		Assert.assertEquals(val, "9");
		test.info("Test Passed");
	}

/*	// tc_FlightBooking - To verify end to end flow of flight booking till payment
	// page
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 15, dataProvider = "dp1")
	public void endToEnd(String email, String f, String l, String contact) throws Exception {
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();

		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();

		// enter review tab details
		driver.findElement(By.cssSelector(prop.getProperty("insurance_no"))).click();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).clear();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).sendKeys(email);

		Thread.sleep(3000);
		driver.findElement(By.id(prop.getProperty("review_submit_id"))).click();

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

		// check for payment button
		String str = driver.findElement(By.xpath(prop.getProperty("payment_button"))).getAttribute("ng-click")
				.toString();
		System.out.println("ng-click = " + str);
		Thread.sleep(3000);

		Assert.assertTrue(str.contains("MakevalidateCard"), "Website not working for end to end flight booking");

	}
*/
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
