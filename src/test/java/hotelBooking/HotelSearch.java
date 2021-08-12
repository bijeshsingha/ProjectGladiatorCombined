package hotelBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HotelSearch {
	WebDriver driver;

	public HotelSearch(WebDriver driver) {
		this.driver = driver;
	}
	
	By e_Hotel = By.cssSelector("a[href='https://www.easemytrip.com/hotels/']");
	By e_Search = By.cssSelector("input[value='Search']");
	
	public void goToHotelTab() {
		driver.findElement(e_Hotel).click();
	}
	
	public void clickSearch() {
		driver.findElement(e_Search).click();
	}
}
