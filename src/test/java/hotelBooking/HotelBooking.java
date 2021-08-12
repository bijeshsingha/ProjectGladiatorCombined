package hotelBooking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelBooking {
	WebDriver driver;
	public HotelBooking(WebDriver driver) throws FileNotFoundException, IOException {
		this.driver = driver;
	}
	
	By e_viewRoom = By.className("bookhBtn");
	By e_selectRoom = By.className("ouline-btn");
	By e_bookNow = By.cssSelector("a.fill-btn");
	By e_title = By.cssSelector("select.sel");
	public void ClickViewRoom() throws Exception, Exception {

		WebDriverWait wt = new WebDriverWait(driver, 20);
		wt.until(ExpectedConditions.visibilityOfElementLocated(e_viewRoom));

		driver.findElement(e_viewRoom).click();
	}
	
	public void ClickSelectRoom() {
		driver.findElement(e_selectRoom).click();
	}
	
	public void clickBookNow() {
		driver.findElement(e_bookNow).click();
	}
	public void enterdata(String fName, String Lname, String email, long phno ) {
		WebElement E = driver.findElement(e_title);
		Select title = new Select(E);
		title.selectByVisibleText("Mrs.");
	}
}
