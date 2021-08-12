package emtHotel;

import org.testng.annotations.Test;

import frameWork.Base;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewTest extends Base {
	WebElement cityTab;
	 Properties prop;
	  @Test(enabled = true, description = "To verify hotel landing page")
	  public void landingPage() throws FileNotFoundException, IOException{
		  prop = new Properties();
		  prop.load(new FileInputStream("src/test/resources/settings.property"));
			driver.get(prop.getProperty("url"));
			driver.findElement(By.cssSelector(prop.getProperty("hotelTab"))).click();
		  
	  }
	  @Test(enabled = true, dependsOnMethods="landingPage", description = "To verify blank entry in city field\n")
	  public void checkCity()throws Exception {
		  //explicit
		  cityTab=driver.findElement(By.className("hp_city"));
		  cityTab.click();
		  Thread.sleep(2000);
		  driver.findElement(By.cssSelector("input[value='Search']")).click();
		  Thread.sleep(2000);
		  driver.switchTo().alert().accept();
		  
	  }
	  @Test(enabled = true, dependsOnMethods="landingPage", description = "To verify same check in check out date inputs")
	  public void sameDates() throws Exception {
		  //WebDriverWait wt = new WebDriverWait(driver, 20);
		  //wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtCity")));
		  //driver.findElement(By.className("hp_city")).click();
		  List<WebElement> ls=driver.findElements(By.className("hotl-name-mar"));
		  ls.get(3).click();
		  driver.findElement(By.linkText("19")).click();
		  driver.findElement(By.linkText("19")).click();
		  driver.findElement(By.cssSelector("input[value='Search']")).click();
		  driver.switchTo().alert().accept();
		  
	  }
	  @Test(enabled = true, dependsOnMethods="sameDates", description = "To verify an expired(date before today's date) check in and check out date input")
	  public void searchBar(){
		  driver.findElement(By.id("htl_dates")).click();
		  driver.findElement(By.linkText("11")).click();
		  driver.findElement(By.linkText("17")).click();
		  driver.findElement(By.cssSelector("input[value='Search']")).click();
		  WebDriverWait wt = new WebDriverWait(driver, 20);
		  wt.until(ExpectedConditions.visibilityOfElementLocated(By.className("not-search")));
		  driver.findElement(By.cssSelector("a.modi-srec3")).click();
		  
	  }
}
