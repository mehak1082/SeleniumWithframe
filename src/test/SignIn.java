package test;

import pages.Login;
import pages.LoginSuccess;
import util.CommonActions;
import util.SheetDataBean;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.*;
import util.ExcelReader;
import util.ConfigReader;
import org.openqa.selenium.*;

public class SignIn {

	static WebDriver driver;
	ConfigReader config = new ConfigReader();

	@BeforeTest()
	public void setUp() throws Exception {
		driver = CommonActions.OpenAppl();

	}

	@Test
	public void testLogin() throws Exception {
		

		Login login = new Login(driver);
		LoginSuccess loginsuccess = new LoginSuccess(driver);
		
		login.usersignIn();
		CommonActions.takeScreenshot(driver,"");

		ExcelReader objexcel = new ExcelReader(System.getProperty("user.dir") + config.getDataFilePath());
		ArrayList<SheetDataBean> list = objexcel.getData();

		for (int i = 0; i < list.size(); i++) {
			if(!(i==0))
				login.clearFields();
			Thread.sleep(2000L);
			SheetDataBean sheetDataBean = (SheetDataBean) list.get(i);
			login.enterDetails(sheetDataBean);

			String expected = "Login Successfully";
			String expected2 = "Thank you for Loggin.";
			System.out.println("Expected Result is: " + expected);
			CommonActions.takeScreenshot(driver,"");
			// Submit the Login Form
			login.submitPage();
			CommonActions.takeScreenshot(driver,"");
			System.out.println("Login submitted");
			Thread.sleep(2000L);
			String[] actualResult = loginsuccess.getActual();
			System.out.println("ActualREsult 0" +actualResult[0]);
			Assert.assertTrue(actualResult[0].equals(expected));
			System.out.println("ActualREsult 1" +actualResult[1]);

			Assert.assertTrue(actualResult[1].equals(expected2));
			
			// to goback to page as per number of records of testdata
			if ((i + 1) != (list.size())) {
				System.out.println("Checking to goBack");
				driver.navigate().back();

			}
		}

		System.out.println("Logged in Successfully");

	}

	@AfterTest()
	public void closeBrowser() throws Exception {
		driver.quit();
	}


}