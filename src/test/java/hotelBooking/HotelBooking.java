package hotelBooking;

import java.io.FileInputStream;
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

public class HotelBooking extends Base {
	Properties prop = new Properties();
	@Test
	public void preCondition() throws Exception, IOException {
		prop.load(new FileInputStream("src/test/resources/settings.property"));
		driver.get(prop.getProperty("url"));
		driver.findElement(By.cssSelector(prop.getProperty("hotelTab"))).click();
		driver.findElement(By.cssSelector(prop.getProperty("search"))).click();
	}

	@Test(dependsOnMethods = "preCondition")
	public void resultPage() throws Exception {
		Assert.assertTrue(driver.getCurrentUrl().contains("newhotel/Hotel/HotelListing?"));
	}

	@Test(dependsOnMethods = "resultPage")
	public void viewRoom() throws Exception {
		
		WebDriverWait wt = new WebDriverWait(driver, 20);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.className(prop.getProperty("viewRoom"))));
		
		driver.findElement(By.className(prop.getProperty("viewRoom"))).click();
		
		Set<String> ids = driver.getWindowHandles();
		List<String> idlist = new ArrayList<String>(ids);
		driver.switchTo().window(idlist.get(1));
		
		Assert.assertTrue(driver.getCurrentUrl().contains("newhotel/Hotel/HotelDescription?"));
	}

	@Test(dependsOnMethods = "viewRoom")
	public void selectRoomButton() throws Exception {
		driver.findElement(By.className("ouline-btn")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[3]/div[2]/div[2]/div[6]/a[2]")).click();
		//WebDriverWait wt = new WebDriverWait(driver, 20);
		//wt.until(ExpectedConditions.visibilityOfElementLocated(By.className(prop.getProperty("bookNow"))));
		//Assert.assertTrue("");
	}
}
