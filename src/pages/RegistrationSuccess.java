package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationSuccess {
	@FindBy(xpath = "//table[@width=\"492\"]/tbody/tr[3]/td")
	WebElement acText;

	WebDriver driver;

	public RegistrationSuccess(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String verifyRegistration() {

		System.out.println("In Verify");

		return this.acText.getText();
	}

}
