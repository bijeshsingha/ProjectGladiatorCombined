package signUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class OpenUrl {
	
WebDriver driver;
	public OpenUrl(WebDriver driver){
		this.driver=driver;
	}
	By account=By.id("spnMyAcc");  				//element for hovering over the button to login
	By signUp=By.id("shwlogn");                 //sign up option selection
	
	public void openUrl() throws InterruptedException{
	WebDriverWait wt=new WebDriverWait(driver,5);
	driver.findElement(account).click();             //locating account option    
	driver.findElement(signUp).click();				//location signup option
	
	
	}
}
