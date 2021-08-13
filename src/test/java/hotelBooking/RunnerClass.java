package hotelBooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import frameWork.Base;
import frameWork.Excel;

public class RunnerClass extends Base {

	ExtentTest test;

	// To verify we are on search result page
	@Test(description = "To verify results are present")
	public void resultPage() throws Exception {
		test = report.createTest("Check the Result Page");

		test.info("Select Hotel Tab");
		new HotelSearch(driver).goToHotelTab();

		test.info("Click on search");
		new HotelSearch(driver).clickSearch();

		test.info("Sort function check");
		new HotelSearch(driver).sort();

		Assert.assertTrue(driver.getCurrentUrl().contains("newhotel/Hotel/HotelListing?"));

		test.info("Test Passed");
	}

	// To verify the view room button is working
	@Test(dependsOnMethods = "resultPage", description = "To verify the view room button is working")
	public void viewRoom() throws Exception {

		test = report.createTest("Check the Result Page");
		
		test.info("click on view room");
		new HotelBooking(driver).ClickViewRoom();

		// To switch to a diff tab
		Set<String> ids = driver.getWindowHandles();
		List<String> idlist = new ArrayList<String>(ids);
		driver.switchTo().window(idlist.get(1));

		// Assertion
		Assert.assertTrue(driver.getCurrentUrl().contains("newhotel/Hotel/HotelDescription?"));
		test.info("Test passed");
	}

	// To check select view room button is working
	@Test(dependsOnMethods = "viewRoom")
	public void selectRoomButton() throws Exception {

		test = report.createTest("Check the select Room button");
		Thread.sleep(2000);

		try {
			wt.until(ExpectedConditions.visibilityOfElementLocated(new HotelBooking(driver).e_selectRoom));
			Assert.assertEquals(true, true);
		} catch (Exception e) {
			Assert.assertEquals(false, true);
		}

		test.info("Test Passed");
	}

	// To check Book Now Function
	@Test(dependsOnMethods = "selectRoomButton")
	public void bookNow() throws Exception {

		Thread.sleep(2000);
		test = report.createTest("Check the book now button");

		new HotelBooking(driver).clickBookNow();
		Assert.assertTrue(driver.findElement(new HotelBooking(driver).e_payment).isDisplayed());
		test.info("Test Passed");
	}

	// To enter the data of the user before confirm booking
	@Test(dataProvider = "dp", description = "entering data and checking", dependsOnMethods = "bookNow")
	public void dataValid(String fName, String lName, String email, String phno) throws Exception {
		driver.manage().deleteAllCookies();

		test = report.createTest("Check for all valid data");

		new HotelBooking(driver).enterdata(fName, lName, email, phno);
		Assert.assertTrue(driver.getCurrentUrl().contains("newhotel/Travel/Traveller?"));
		test.info("Test Passed");
	}

	// Data Provider
	@DataProvider
	public Object[][] dp() {
		Object data[][] = new Object[1][4];
		Excel ex = new Excel("src/test/resources/Excel/Book1.xlsx");
		for (int j = 1; j < 5; j++) {
			data[0][j - 1] = ex.readData("Sheet1", 1, j);
		}
		return data;
	}

	// To enter the data of the user before confirm booking
	@Test(dataProvider = "dp1", description = "entering data and checking", dependsOnMethods = "dataValid")
	public void dataInvalid1(String fName, String lName, String email, String phno) throws Exception {

		driver.manage().deleteAllCookies();

		test = report.createTest("Check empty first name");

		driver.navigate().back();
		new HotelBooking(driver).clickBookNow();
		Thread.sleep(2000);
		new HotelBooking(driver).enterdata("", lName, email, phno);
		driver.switchTo().alert();

		Assert.assertEquals(driver.switchTo().alert().getText(), "please enter the first name of guest 1");
		test.info("Test Passed");
	}

	// Data Provider
	@DataProvider
	public Object[][] dp1() {
		Object data[][] = new Object[1][4];
		Excel ex = new Excel("src/test/resources/Excel/Book1.xlsx");
		for (int j = 1; j < 5; j++) {
			data[0][j - 1] = ex.readData("Sheet1", 2, j);
		}
		return data;
	}

	// To enter the data of the user before confirm booking
	@Test(dataProvider = "dp2", description = "entering data and checking", dependsOnMethods = "dataInvalid1")
	public void dataInvalid2(String fName, String lName, String email, String phno) throws Exception {

		driver.switchTo().alert().accept();
		driver.manage().deleteAllCookies();

		test = report.createTest("Check for empty last name");

		driver.navigate().back();
		new HotelBooking(driver).clickBookNow();
		Thread.sleep(2000);
		new HotelBooking(driver).enterdata(fName, "", email, phno);
		driver.switchTo().alert();

		Assert.assertEquals(driver.switchTo().alert().getText(), "please enter the last name of guest 1");
		test.info("Test Passed");
	}

	// Data Provider
	@DataProvider
	public Object[][] dp2() {
		Object data[][] = new Object[1][4];
		Excel ex = new Excel("src/test/resources/Excel/Book1.xlsx");
		for (int j = 1; j < 5; j++) {
			data[0][j - 1] = ex.readData("Sheet1", 3, j);
		}
		return data;
	}

	// To enter the data of the user before confirm booking
	@Test(dataProvider = "dp3", description = "entering data and checking", dependsOnMethods = "dataInvalid2")
	public void dataInvalid3(String fName, String lName, String email, String phno) throws Exception {

		driver.switchTo().alert().accept();
		driver.manage().deleteAllCookies();

		test = report.createTest("Check for invalid email");

		driver.navigate().back();
		new HotelBooking(driver).clickBookNow();
		Thread.sleep(2000);
		new HotelBooking(driver).enterdata(fName, lName, email, phno);
		driver.switchTo().alert();

		Assert.assertEquals(driver.switchTo().alert().getText(), "please enter the valid emailid");
		test.info("Test Passed");
	}

	// Data Provider
	@DataProvider
	public Object[][] dp3() {
		Object data[][] = new Object[1][4];
		Excel ex = new Excel("src/test/resources/Excel/Book1.xlsx");
		for (int j = 1; j < 5; j++) {
			data[0][j - 1] = ex.readData("Sheet1", 4, j);
		}
		return data;
	}

	// To enter the data of the user before confirm booking
	@Test(dataProvider = "dp4", description = "entering data and checking", dependsOnMethods = "dataInvalid3")
	public void dataInvalid4(String fName, String lName, String email, String phno) throws Exception {

		driver.switchTo().alert().accept();
		driver.manage().deleteAllCookies();

		test = report.createTest("Check the Invalid Phone number");

		driver.navigate().back();
		new HotelBooking(driver).clickBookNow();
		Thread.sleep(2000);
		new HotelBooking(driver).enterdata(fName, lName, email, phno);

		Assert.assertTrue(driver.getCurrentUrl().contains("newhotel/Travel/Traveller?"));
		test.info("Test Passed");
	}

	// Data Provider
	@DataProvider
	public Object[][] dp4() {
		Object data[][] = new Object[1][4];
		Excel ex = new Excel("src/test/resources/Excel/Book1.xlsx");
		for (int j = 1; j < 5; j++) {
			data[0][j - 1] = ex.readData("Sheet1", 5, j);
		}
		return data;
	}

}
