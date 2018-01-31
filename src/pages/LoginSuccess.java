package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSuccess {
	
	WebDriver driver;
	
	@FindBy(xpath = "/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td")
	WebElement actualtext;

	@FindBy(xpath = "/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[1]")
	WebElement actualtext2;
	
	public LoginSuccess(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String[] getActual() {
		System.out.println("Looking Confirmation");
		System.out.println(actualtext2.getText());
		String[] arr = new String[2];
		arr[0] = actualtext.getText();
		arr[1] = actualtext2.getText();

		return new String[] { arr[0], arr[1] };
	}

}
