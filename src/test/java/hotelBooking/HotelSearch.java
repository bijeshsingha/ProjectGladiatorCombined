package hotelBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelSearch {
	@FindBy(css="a[href='https://www.easemytrip.com/hotels/']") WebElement e_Hotel;
	@FindBy(css="input[value='Search']") WebElement e_Search;
	
	public void goToHotelTab() {
		e_Hotel.click();
	}
	
	public void clickSearch() {
		e_Search.click();
	}
}
