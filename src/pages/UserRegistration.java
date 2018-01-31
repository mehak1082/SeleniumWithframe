package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.ConfigReader;
import util.SheetDataBean;

public class UserRegistration {
	
	WebDriver driver;
	
    @FindBy(name="firstName")
    WebElement firstname;

    @FindBy(name="lastName")
    WebElement lastname;
    

    @FindBy(name="email")
    WebElement uname;

    
    @FindBy(name="password")
    WebElement pwd;
    

    @FindBy(name="confirmPassword")
    WebElement crfmPassword;
    
    @FindBy(partialLinkText="ister")
    WebElement link;
    
    @FindBy(name="submit")
    WebElement submitUser;
    
  
    
    
	ConfigReader config = new ConfigReader();
	

	// Constructor
	public UserRegistration(WebDriver driver) {
		 this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickRegister() {
		link.click();
	}


	
	public void submitPage(){		
		submitUser.click();		
	}
	
	
	public void fillUserDetails(SheetDataBean sheetDataBean) throws InterruptedException{
		
		System.out.println(sheetDataBean.getFname());
		System.out.println(sheetDataBean.getLname());
		Thread.sleep(2000L);
		firstname.sendKeys(sheetDataBean.getFname());		
		lastname.sendKeys(sheetDataBean.getLname());		
		uname.sendKeys(sheetDataBean.getUname());		
		pwd.sendKeys(sheetDataBean.getPwd());
		crfmPassword.sendKeys(sheetDataBean.getPwd());
		
	}
	
	public void clearFields(){
		
		firstname.clear();
		lastname.clear();
		uname.clear();
		pwd.clear();
		crfmPassword.clear();
	}
	
	
	/*public void navigateback(){
		driver.navigate().back();
	}
*/

}
