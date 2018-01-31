package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.SheetDataBean;

public class Login {

	WebDriver driver;

	@FindBy(linkText = "SIGN-ON")
	WebElement signOn;

	@FindBy(name = "userName")
	WebElement uName;

	@FindBy(name = "password")
	WebElement pwd;
	@FindBy(xpath = "//table[width=\"492\"]/tbody/tr[3]/td/a")
	WebElement signIn;

	@FindBy(name = "submit")
	WebElement submit;

	

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	
	public void submitPage() {
		this.submit.click();
	}

	public void usersignIn() throws Exception {

		if (!(signOn == null)) {
			System.out.println("Login signOn Link " + signOn);
			signOn.click();
		}

		else if (signIn != null) {
			System.out.println("Login signOn Link " + signIn);
			signIn.click();
		} else
			System.out.println("Login In Link not found");
	}


	public void enterDetails(SheetDataBean sheetDataBean) {
		uName.sendKeys(sheetDataBean.getUname());
		pwd.sendKeys(sheetDataBean.getPwd());
	}

	

	public boolean isElementPresent(By by) {
		try {
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void clearFields() {

		uName.clear();
		pwd.clear();
		
	}

}
