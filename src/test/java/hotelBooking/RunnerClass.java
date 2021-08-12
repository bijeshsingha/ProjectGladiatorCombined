package hotelBooking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import frameWork.Base;

public class RunnerClass extends Base {
	public Properties prop = new Properties();

	@Test(description = "To search a Hotel")
	public void searchHotels() throws Exception, Exception {
		prop.load(new FileInputStream("src/test/resources/settings.property"));
		driver.get(prop.getProperty("url"));
		new HotelSearch(driver).goToHotelTab();
		new HotelSearch(driver).clickSearch();
	}

	@Test(dependsOnMethods = "searchHotels", description = "To verify results are present")
	public void resultPage() throws Exception {
		Assert.assertTrue(driver.getCurrentUrl().contains("newhotel/Hotel/HotelListing?"));
	}

	@Test(dependsOnMethods = "resultPage", description = "To verify the view room button is working")
	public void viewRoom() throws Exception {

		WebDriverWait wt = new WebDriverWait(driver, 20);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.className(prop.getProperty("viewRoom"))));

		new HotelBooking(driver).ClickViewRoom();

		Set<String> ids = driver.getWindowHandles();
		List<String> idlist = new ArrayList<String>(ids);
		driver.switchTo().window(idlist.get(1));

		Assert.assertTrue(driver.getCurrentUrl().contains("newhotel/Hotel/HotelDescription?"));
	}

	@Test(dependsOnMethods = "viewRoom")
	public void selectRoomButton() throws Exception {
		Thread.sleep(2000);
		//new HotelBooking(driver).ClickSelectRoom();
	}
	@Test(dependsOnMethods = "selectRoomButton")
	public void bookNow() throws Exception {
		Thread.sleep(2000);
		new HotelBooking(driver).clickBookNow();
	}
	@Test
	public void book() throws FileNotFoundException, IOException {
		new HotelBooking(driver).enterdata("1", "1", "1", 1);
	}
}
