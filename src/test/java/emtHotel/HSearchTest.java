package emtHotel;

import org.testng.annotations.Test;

import frameWork.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HSearchTest extends Base {
	 Properties prop = new Properties();
	  @Test(enabled = true, description = "To verify hotel landing page")
	  public void landingPage() throws FileNotFoundException, IOException{	  
		  prop.load(new FileInputStream("src/test/resources/hSearch.property"));//LOADING PROPERTY FILE
			driver.get(prop.getProperty("url"));
			driver.findElement(By.cssSelector(prop.getProperty("hotelTab"))).click();//CLICKING ON HOTEL IN TITLE BAR
		  
	  }
	  @Test(enabled = true, dependsOnMethods="landingPage", description = "To verify blank entry in city field\n")
	  public void checkCity(){
		  driver.manage().deleteAllCookies(); //DELETE BROWSER COOKIES
		  driver.findElement(By.className(prop.getProperty("city"))).click();;//CLICK CITY DROPDOWN
		  driver.findElement(By.cssSelector(prop.getProperty("search"))).click(); //HIT SEARCH
		  driver.switchTo().alert().accept();//ACCEPT ALERT  
	  }
	  @Test(enabled = true, dependsOnMethods="checkCity", description = "To verify same check in check out date inputs")
	  public void sameDates() throws Exception {
		  List<WebElement> ls=driver.findElements(By.className(prop.getProperty("city-list")));//LOAD ALL ELEMENTS OF CITY DROPDWON IN LIST
		  ls.get(3).click();
		  driver.findElement(By.linkText(prop.getProperty("same-date"))).click();// SELECT DATE
		  driver.findElement(By.linkText(prop.getProperty("same-date"))).click();// SELECT DATE
		  driver.findElement(By.id(prop.getProperty("close-room"))).click();// CLOSE ROOMS OPTION
		  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();//HIT SEARCH
		  driver.switchTo().alert().accept();//ACCEPT ALERT
		  
	  }
	  @Test(enabled = true, dependsOnMethods="sameDates", description = "To verify if the calander disables dates before yesterday")
	  public void disabledInDates(){
		  int count=0;
		  driver.findElement(By.id(prop.getProperty("check-in-cal"))).click();//SELECT CHECK IN DATE
		  try {			
			  driver.findElement(By.xpath(prop.getProperty("before-yest"))).click();//SELECT A DATE BEFORE YESTERDAY
		  }
		  catch(ElementClickInterceptedException e){
			  count=1;
			  System.out.println("Not interactable - Dates before yesterday disabled");//PASS CASE IF EXCEPTION IN THROWN
		  }
		  if(count==0){	//THIS CONDITION IS TO PURPOSELY FAIL THE TEST CASE IF THE TRY BLOCK RUNS
			  driver.findElement(By.xpath(prop.getProperty("valid-date"))).click();//SELECTED A VALID DATE
			  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();//HIT SEARCH
			  driver.findElement(By.id(prop.getProperty("check-in-cal"))).click();//SELECT CHECK IN DATE, WHICH WONT BE VISIABLE IF SEARCH EXECUTES
		  }
	  }
	  @Test(enabled = true, dependsOnMethods="disabledInDates", description = "To verify if the check out calender allows dates before check in date to be selected")
	  public void disabledOutDate(){
		  int count=0;
		  driver.findElement(By.xpath(prop.getProperty("today-date"))).click();//SELECT TODAY'S DATE
		  try {			
			  driver.findElement(By.xpath(prop.getProperty("before-checkin"))).click();//SELECT A DATE BEFORE THE CHECK IN DATE
			  
		  }
		  catch(ElementClickInterceptedException e){
			  count=1;
			  System.out.println("Not interactable - Dates before check in date disabled");//PASS CASE IF EXCEPTION IN THROWN
		  }
		  if(count==0){	//THIS CONDITION IS TO PURPOSELY FAIL THE TEST CASE IF THE TRY BLOCK RUNS
			  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();//HIT SEARCH
			  driver.findElement(By.id(prop.getProperty("check-in-cal"))).click();//SELECT CHECK IN DATE, WHICH WONT BE VISIABLE IF SEARCH EXECUTES
		  }
	  }
	  @Test(enabled = true, dependsOnMethods="disabledOutDate", description = "To verify output when check in date is of yesterday's")
	  public void yestDate(){
		  driver.findElement(By.id(prop.getProperty("check-in-cal"))).click();//SELECT CHECKIN CALENDER
		  driver.findElement(By.xpath(prop.getProperty("yest-date"))).click();//SELECT YESTERDAY'S DATE
		  driver.findElement(By.xpath(prop.getProperty("today-date"))).click();//SELECT TODAY'S DATE
		  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();
		  WebDriverWait wt = new WebDriverWait(driver, 20);
		  wt.until(ExpectedConditions.visibilityOfElementLocated(By.className(prop.getProperty("no-result"))));//WAIT UNTIL THE LOADING FINISHES AND A SITE ALERT APPEARS
		  driver.findElement(By.cssSelector(prop.getProperty("exit-no-result"))).click();//GO BACK TO SELECT VALID DATES - EXIT BUTTON
		  
	  }
	  @Test(enabled = true, dependsOnMethods="yestDate", description = "To verify if the gap between check in and check out date is not more than 28 days")
	  public void daysGap(){
		  driver.findElement(By.className(prop.getProperty("city"))).click();
		  List<WebElement> ls=driver.findElements(By.className(prop.getProperty("city-list")));//LOAD ALL ELEMENTS OF CITY DROPDWON IN LIST
		  ls.get(3).click();
		  driver.findElement(By.xpath(prop.getProperty("today-date"))).click();//SELECT TODAY'S DATE
		  driver.findElement(By.xpath(prop.getProperty("date-28"))).click();//SELECT A DATE 28 DATES AHEAD
		  driver.findElement(By.id(prop.getProperty("close-room"))).click();
		  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();
		  driver.switchTo().alert().accept();
		  
		  
	  }
	  @Test(enabled = true, dependsOnMethods="daysGap", description = "To verify listing of hotels after search using valid inputs")
	  public void positiveSearch(){
		  driver.findElement(By.className(prop.getProperty("city"))).click();
		  List<WebElement> ls=driver.findElements(By.className(prop.getProperty("city-list")));
		  ls.get(3).click();
		  driver.findElement(By.xpath(prop.getProperty("today-date"))).click();//SELECT TODAY'S DATE
		  driver.findElement(By.xpath(prop.getProperty("valid-date"))).click();//SELECTED A VALID DATE
		  driver.findElement(By.id(prop.getProperty("close-room"))).click();
		  driver.findElement(By.cssSelector(prop.getProperty("search"))).click();
		  WebDriverWait wt = new WebDriverWait(driver, 20);
		  wt.until(ExpectedConditions.visibilityOfElementLocated(By.className(prop.getProperty("view-btn"))));// WAIT UNTIL VIEW NOW BUTTON LOADS IN HOTEL LIST
		  driver.findElement(By.className(prop.getProperty("view-btn"))).click();//CLICK VIEW NOW
	  }
}
