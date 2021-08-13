/*
package hotelBooking;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelBooking {
	WebDriver driver;
	public HotelBooking(WebDriver driver) {
		this.driver = driver;
	}
	
	By e_viewRoom = By.cssSelector("div.bookhBtn");
	//By e_selectRoom = By.className("ouline-btn");
	By e_bookNow = By.cssSelector("a.HotelInfoUrl(_rm);");
	//By e_title = By.cssSelector("select.sel");
	By e_fn = By.id("txtFirstName1");
	By e_ln = By.id("txtLastName1");
	By e_email = By.id("txtEmailId");
	By e_phno = By.id("txtCPhone");
	By e_payment = By.cssSelector("div.coonpayment");
	
	public void ClickViewRoom() throws Exception, Exception {

		WebDriverWait wt = new WebDriverWait(driver, 20);
		wt.until(ExpectedConditions.visibilityOfElementLocated(e_viewRoom));
		driver.get("https://hotels.easemytrip.com/newhotel/Hotel/HotelDescription?e=2021813102442&city=Bangalore,%20India&cin=13/08/2021&cOut=21/08/2021&Hotel=NA&Rooms=1&pax=2&key=15~INR~bengaluru~08-13-2021~08-21-2021~1~2_~~~EaseMyTrip~NA~NA~NA~IN&ecid=EMTHotel-8503&hid=2314011");
		//driver.findElement(e_viewRoom).click();
	}
	
	public void ClickSelectRoom() {
		driver.findElement(e_selectRoom).click();
	}
	
	public void clickBookNow() {
		driver.findElement(e_bookNow).click();
	}
	
	public void enterdata(String fName, String lName, String email, String phno ) throws Exception {
		driver.findElement(e_fn).sendKeys(fName);
		driver.findElement(e_ln).sendKeys(lName);
		driver.findElement(e_email).sendKeys(email);
		driver.findElement(e_phno).sendKeys(phno);
		Thread.sleep(2000);
		driver.findElement(e_payment).click();
	}
}
*/


package hotelBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelBooking {
	WebDriver driver;
	public HotelBooking(WebDriver driver) {
		this.driver = driver;
	}
	
	By e_viewRoom = By.className("bookhBtn");
	By e_selectRoom = By.className("ouline-btn");
	By e_bookNow = By.cssSelector("a.fill-btn");
	By e_fn = By.id("txtFirstName1");
	By e_ln = By.id("txtLastName1");
	By e_email = By.id("txtEmailId");
	By e_phno = By.id("txtCPhone");
	By e_payment = By.cssSelector("div.coonpayment");
	By e_makeP = By.linkText("Make Payment");
	
	//view room function
	public void ClickViewRoom() throws Exception, Exception {

		WebDriverWait wt = new WebDriverWait(driver, 20);
		wt.until(ExpectedConditions.visibilityOfElementLocated(e_viewRoom));

		driver.findElement(e_viewRoom).click();
	}
	
	//select Room
	public void ClickSelectRoom() {
		driver.findElement(e_selectRoom).click();
	}
	
	//Book now
	public void clickBookNow() {
		driver.findElement(e_bookNow).click();
	}
	
	//Entering data 
	public void enterdata(String fName, String lName, String email, String phno ) throws InterruptedException {
		driver.findElement(e_fn).sendKeys(fName);
		driver.findElement(e_ln).sendKeys(lName);
		driver.findElement(e_email).sendKeys(email);
		driver.findElement(e_phno).sendKeys(phno);
		Thread.sleep(2000);
		driver.findElement(e_payment).click();
		Thread.sleep(2000);
	}
}