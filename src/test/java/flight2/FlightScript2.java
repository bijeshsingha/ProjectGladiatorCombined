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

public class FlightScript2 extends Base {
	
	Properties prop = new Properties();
	
	@Test(priority = 1)
	public void aPropLoadMethod() throws Exception
	{
		prop.load(new FileInputStream("src/test/resources/settings.property"));
	}
	
	
	// tc_type_of_flight01 - checking one way trip option
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 1)
	public void checkOneway() throws Exception {
		
		// click on one way trip option
		driver.findElement(By.xpath(prop.getProperty("oneway"))).click();
		
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("FlightList"));
		
		// load the homepage again
		driver.navigate().to(prop.getProperty("url"));
	
	}
	
	
	
	// tc_type_of_flight02 - checking round trip option
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority= 2)
	public void checkRoundtrip() throws Exception
	{	
		// click on round trip option
		driver.findElement(By.xpath(prop.getProperty("roundtrip"))).click();
		
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("FlightList"));
				
		// load the homepage again
		driver.navigate().to(prop.getProperty("url"));
	}
	
	
	
	// tc_type_of_flight03 - checking multi-city option
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 3)
	public void checkMulticity() throws Exception
	{	
		// click on multi-city option
		driver.findElement(By.xpath(prop.getProperty("multicity"))).click();
		
		// select required data randomly
		// departure city 1
		driver.findElement(By.id(prop.getProperty("multi_departure_id"))).click();
		driver.findElement(By.xpath(prop.getProperty("multi_departure_city"))).click();
		
		// destination city 1
		driver.findElement(By.id(prop.getProperty("multi_destination_id"))).click();
		driver.findElement(By.xpath(prop.getProperty("multi_destination_city"))).click();
		
		// departure city 2
		driver.findElement(By.id(prop.getProperty("multi_departure_id2"))).click();
		driver.findElement(By.xpath(prop.getProperty("multi_departure_city2"))).click();
				
		// destination city 2
		driver.findElement(By.id(prop.getProperty("multi_destination_id2"))).click();
		driver.findElement(By.xpath(prop.getProperty("multi_destination_city2"))).click();
				 
		// date 1
		driver.findElement(By.id(prop.getProperty("date1"))).click();
		driver.findElement(By.linkText(prop.getProperty("date1_data"))).click();
		
		// date 2
		driver.findElement(By.id(prop.getProperty("date2"))).click();
		driver.findElement(By.linkText(prop.getProperty("date2_data"))).click();
		
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss2"))).click();
			
		Assert.assertTrue(driver.getCurrentUrl().contains("multicity"));
		
		// load the homepage again
		driver.navigate().to(prop.getProperty("url"));
				
	}
	
	
	
	// tc_webcheck_in01 - To check Web Check-In option
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 4)
	public void webCheckIn() throws Exception
	{
		// locate web check in element
		driver.findElement(By.linkText(prop.getProperty("webcheckin"))).click();
		
		// load the homepage again
		driver.close();
	
	}
	
	
	// tc_defence01 - To check Defence Forces Option
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 5)
	public void CheckDefence() throws Exception
	{		
		// select the defence checkbox
		driver.findElement(By.xpath(prop.getProperty("defence_tick"))).click();
		
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
		
		// select an option from dropdown
		WebElement e = driver.findElement(By.id(prop.getProperty("defence_dropdown_id")));
		Select dd = new Select(e);
		dd.selectByIndex(3);
		Thread.sleep(3000);
		
		// click on cross button at top right corner
		driver.findElement(By.className(prop.getProperty("defence_close_class"))).click();
		Thread.sleep(3000);
	}

	
	// tc_flight_book01 - To obtain flight details for a selected flight
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 6)
	public void checkFlightDetails() throws Exception
	{
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
		
		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("Review"));
	}
	
	
	// tc_reviewdetails01 - To enter a valid email and go ahead
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 7, dataProvider = "dp1")
	public void reviewValidEmail(String email, String f, String l, String contact) throws Exception
	{
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
				
		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();
				
		// enter review tab details
		driver.findElement(By.cssSelector(prop.getProperty("insurance_no"))).click();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).sendKeys(email);
			
		Thread.sleep(3000);
		driver.findElement(By.id(prop.getProperty("review_submit_id"))).click();
		
		// to check if the next tab is loaded
		String countrycode = driver.findElement(By.id(prop.getProperty("travel_assert_id")))
				.getAttribute("value");
		
		Assert.assertTrue(countrycode.contains("91"));
		
		// load the homepage again
		driver.navigate().to(prop.getProperty("url"));
	}

	
	
	// tc_reviewdetails02 - To enter an invalid email and go ahead
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 8)
	public void reviewInvalidEmail() throws Exception
	{
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
					
		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();
					
		// enter review tab details
		driver.findElement(By.cssSelector(prop.getProperty("insurance_no"))).click();
		driver.findElement(By.id(prop.getProperty("review_email_id"))).clear();
		driver.findElement(By.id(prop.getProperty("review_email_id")))
			.sendKeys(prop.getProperty("invalid_email"));
				
		Thread.sleep(3000);
		driver.findElement(By.id(prop.getProperty("review_submit_id"))).click();
			
		Assert.assertTrue(driver.getCurrentUrl().contains("Review"));
			
		// load the homepage again
		driver.navigate().to(prop.getProperty("url"));
	}

		
	
	
	// tc_reviewdetails03 - To check booking with Insurance option 'Yes'
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 14, dataProvider = "dp1")
	public void tc_reviewdetails03(String email, String f, String l, String contact)
	{
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
							
		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();
		
		// click on yes option
		driver.findElement(By.xpath(prop.getProperty("yesInsure"))).click();
		
		// enter a random email from excel
		driver.findElement(By.id(prop.getProperty("review_email_id"))).sendKeys(email);
		
		// to check if the next tab is loaded
		String countrycode = driver.findElement(By.id(prop.getProperty("travel_assert_id")))
				.getAttribute("value");
				
		Assert.assertTrue(countrycode.contains("91"));
				
		// load the homepage again
		driver.navigate().to(prop.getProperty("url"));
		
	}
	
	
	
	// tc_reviewdetails04 - To check booking with Insurance option 'No'
	@Test(enabled = true, dependsOnMethods = "aPropLoadMethod", priority = 14, dataProvider = "dp1")
	public void tc_reviewdetails04(String email, String f, String l, String contact) throws Exception
	{
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
								
		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();
			
		// click on yes option
		driver.findElement(By.xpath(prop.getProperty("noInsure"))).click();
		Thread.sleep(3000);
		
		// enter a random email from excel
		driver.findElement(By.id(prop.getProperty("review_email_id"))).sendKeys(email);
			
		// to check if the next tab is loaded
		String countrycode = driver.findElement(By.id(prop.getProperty("travel_assert_id")))
				.getAttribute("value");
					
		Assert.assertTrue(countrycode.contains("91"));
					
		// load the homepage again
		driver.navigate().to(prop.getProperty("url"));
			
	}
		
		
	
	
	// tc_travellers01 - To enter a valid contact number and go ahead
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 9, dataProvider = "dp1")
	public void travellerSection1(String email, String f, String l, String contact) throws Exception
	{
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
		
		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();
		
		// enter review tab details
		driver.findElement(By.cssSelector(prop.getProperty("insurance_no"))).click();
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
		driver.findElement(By.id(prop.getProperty("travel_contact_id"))).sendKeys(contact);
		
		// click on continue booking
		driver.findElement(By.id(prop.getProperty("travel_submit_id"))).click();
		
		// check for payment button
		String str = driver.findElement(By.xpath(prop.getProperty("payment_button")))
				.getAttribute("ng-click").toString();
		System.out.println("ng-click = "+str);
		Thread.sleep(3000);
		
		Assert.assertTrue(str.contains("MakevalidateCard"));
		
	}

	
	
	// tc_travellers01 - To enter a invalid contact number and try to go ahead
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 10, dataProvider = "dp1")
	public void travellerSection2(String email, String f, String l, String contact) throws Exception
	{
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
			
		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();
			
		// enter review tab details
		driver.findElement(By.cssSelector(prop.getProperty("insurance_no"))).click();
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
		
		// fetch invalid contact from property file
		driver.findElement(By.id(prop.getProperty("travel_contact_id")))
			.sendKeys(prop.getProperty("invalid_contact"));
			
		// click on continue booking
		driver.findElement(By.id(prop.getProperty("travel_submit_id"))).click();
		
		String countrycode = driver.findElement(By.id(prop.getProperty("travel_assert_id"))).getAttribute("value");
		
		Assert.assertTrue(countrycode.contains("91"));
	}
	
	
	
	
	// tc_location01 - To check booking with same departure and destination location
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 11)
	public void checkSameLocation() throws Exception
	{
		// click on from port
		driver.findElement(By.id(prop.getProperty("location_from_id"))).click();
		
		// select same location for from and to port
		driver.findElement(By.id(prop.getProperty("location_city_id"))).click();
		
		// click on search
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
		
		// check for alert
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("cannot be same"));
		Thread.sleep(3000);
		alert.accept();
	}


	// tc_max_travellers01 - To increase the travellers count till 10 and check for alert
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 12)
	public void maxTravelersCheck()
	{
		// click on traveler drop down
		driver.findElement(By.className(prop.getProperty("traveler_dropdown_class"))).click();
		
		// click on + button till max limit is reached
		WebElement plus = driver.findElement(By.xpath(prop.getProperty("plus")));
		for(int i=0; i<10; i++)
		{
			plus.click();
		}
		
		String val = driver.findElement(By.id(prop.getProperty("adult_id"))).getAttribute("value");
		
		Assert.assertEquals(val, "9");
	}

	
	// tc_FlightBooking - To verify end to end flow of flight booking till payment page
	@Test(enabled = false, dependsOnMethods = "aPropLoadMethod", priority = 13, dataProvider = "dp1")
	public void endToEnd(String email, String f, String l, String contact) throws Exception
	{
		// use the default data and click on search button
		driver.findElement(By.cssSelector(prop.getProperty("searchcss"))).click();
		
		// click on book now for first flight
		driver.findElement(By.xpath(prop.getProperty("book_flight"))).click();	
		
		// enter review tab details
		driver.findElement(By.cssSelector(prop.getProperty("insurance_no"))).click();
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
		driver.findElement(By.id(prop.getProperty("travel_contact_id"))).sendKeys(contact);
				
		// click on continue booking
		driver.findElement(By.id(prop.getProperty("travel_submit_id"))).click();
				
		// check for payment button
		String str = driver.findElement(By.xpath(prop.getProperty("payment_button")))
				.getAttribute("ng-click").toString();
		System.out.println("ng-click = "+str);
		Thread.sleep(3000);
			
		Assert.assertTrue(str.contains("MakevalidateCard"));
				
		
	}
	
	
	
	
	
	
	@DataProvider
	public Object[][] dp1() throws Exception
	{
		// valid data from Excel
		XSSFWorkbook flight_data = new XSSFWorkbook(
				new FileInputStream("src/test/resources/Excel/FlightData.xlsx"));
		
		Object data[][] = new Object[1][4];
		
		for(int i=0; i<4; i++)
		{
			data[0][i] = flight_data.getSheet("Sheet1").getRow(1).getCell(i).toString();
		}
		
		return data;
	}
	
}
