package hotelBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HotelSearch {
	WebDriver driver;

	//Constructor
	public HotelSearch(WebDriver driver) {
		this.driver = driver;
	}
	
	//Elements
	By e_Hotel = By.cssSelector("a[href='https://www.easemytrip.com/hotels/']");
	By e_Search = By.cssSelector("input[value='Search']");
	By e_sort = By.id("drpHighList");
	
	//to go to hotel Tab
	public void goToHotelTab() {
		driver.findElement(e_Hotel).click();
	}
	
	//to click on search button
	public void clickSearch() {
		driver.findElement(e_Search).click();
	}
	
	//to sort the hotel 
	public void sort() throws InterruptedException {
		Thread.sleep(2000);
		WebElement s = driver.findElement(e_sort);
		
		Select sort = new Select(s); // Specifying that element E is a aweb element
		
		//sorting with Price High to Low
		sort.selectByVisibleText("Price - High to Low"); 
		Thread.sleep(2000);
		
		//sorting with Low to High
		sort.selectByVisibleText("Price - Low to High");
		Thread.sleep(2000);
		
		//sorting with Popularity
		sort.selectByVisibleText("Popularity");
		Thread.sleep(2000);
	}
}
