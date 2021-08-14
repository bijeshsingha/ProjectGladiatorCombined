package signUp;

import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import frameWork.SignupBase;

public class Email extends SignupBase{
	WebDriver driver;
	
	public Email(WebDriver driver)
	{
		this.driver=driver;
	}
	By account=By.id("spnMyAcc");  				//element for hovering over the button to login
	By signUp=By.id("shwlogn");                 //sign up option selection
	By enter_email=By.id("txtEmail");           //email text box
	By continue_button=By.id("shwotp"); 		//continue button
	By otp=By.id("txtEmail1");					//otp text box
	By create=By.id("OtpLgnBtn");				//create account button
	
	public void signupEmail(String User,String status) throws InterruptedException
	{
		driver.findElement(account).click();			//locating account option    
		driver.findElement(signUp).click();				//location signup option
		driver.findElement(enter_email).sendKeys(User); //locating textbox for entering mobile number
		driver.findElement(continue_button).click();    //locating continue button
		 wt=new WebDriverWait(driver,5);
		try {
			wt.until(ExpectedConditions.invisibilityOf(driver.findElement(continue_button))); //To check invalid message is displayed or not
			}
			catch(Exception e) {
				if(status.equals("valid")) {			//If testcase is valid 
					Assert.fail(e.getMessage());	
				}
				else if(status.equals("invalid")) {     //If testcase is invalid
					System.out.println("Invalid Email id");
			}	
		}
}
	public void emailOtp(String enter_otp,String status) throws InterruptedException{
		 wt=new WebDriverWait(driver,20);
		driver.findElement(otp).sendKeys(enter_otp);   //otp will be entered manually
		driver.findElement(create).click();
		WebDriverWait wt=new WebDriverWait(driver,5);
		try {
		wt.until(ExpectedConditions.invisibilityOf(driver.findElement(create))); //To check invalid message is displayed or not
		}
		catch(Exception e) {
			if(status.equals("valid")) {				//If testcase is valid 
				Assert.fail(e.getMessage());
			}
			else if(status.equals("invalid")) {			//If testcase is invalid
				System.out.println("Invalid otp");
			}
		}
	}
  
}
