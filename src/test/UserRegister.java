package test;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.RegistrationSuccess;
import pages.UserRegistration;
import util.CommonActions;
import util.ConfigReader;
import util.ExcelReader;
import util.SheetDataBean;

public class UserRegister {

	WebDriver driver;
	
	
	String driverObj = "webdriver.chrome.driver";
	
	UserRegistration objRegister;

	ConfigReader config = new ConfigReader();
	
	@BeforeTest
	public void setUpDriver() {
		
		//System.setProperty("webdriver.chrome.driver",config.getChromePath());
		driver = CommonActions.OpenAppl();
		//driver.get(config.getUrl());
		//driver.manage().window().maximize();
		System.out.println("Browser Launched successfully");

	}

	@Test
	public void UserRegistration() throws Exception{
		
		System.out.println("In Test");
		
		// Create Register Page object
		objRegister = new UserRegistration(driver);
		RegistrationSuccess registrationSuccess = new RegistrationSuccess(driver);
		
		CommonActions.takeScreenshot(driver,"");

		// Go to Registration Page
		objRegister.clickRegister();
		Reporter.log("Going for Registration");
		System.out.println("Registration clicked");
		
		
		// Enter Userdetail for Registration
		System.out.println("Enter Userdetail for Registration");
		
		
		ExcelReader objexcel = new ExcelReader(System.getProperty("user.dir")+ config.getDataFilePath()) ;
		ArrayList<SheetDataBean> list = objexcel.getData();
		
		for(int i =0; i<list.size();i++){			
			
			if(!(i==0))
				objRegister.clearFields();   //To clear fields when navigated back to register page
			
			SheetDataBean sheetDataBean = (SheetDataBean)list.get(i);			
			objRegister.fillUserDetails(sheetDataBean);			
			String expected = "Dear " +sheetDataBean.getFname() + " " +sheetDataBean.getLname() + ","
								+  "\n" + "Thank you for registering. You may now sign-in using the user name and password you've just entered."
								+ "\n" + "Note: Your user name is " + sheetDataBean.getUname() +".";
			System.out.println("Expected Result is: " + expected);
			//Submit the Regsitration Form
			CommonActions.takeScreenshot(driver,"");
			objRegister.submitPage();
			CommonActions.takeScreenshot(driver,"");
			System.out.println("Registration Submitted");
			Thread.sleep(2000L);
			String actualResult = registrationSuccess.verifyRegistration();
			System.out.println("Actual Result: " + actualResult);
	
			
			 Assert.assertTrue(actualResult.equals(expected));
			   
			
			// to goback to page as per number of records of testdata
			if((i+1) != (list.size())){
				driver.navigate().back();
				
			}
		}
		
		
		
	}

	@AfterTest
	public void afterTest() throws Exception {
		Thread.sleep(2000L);
		driver.quit();
	}

}
