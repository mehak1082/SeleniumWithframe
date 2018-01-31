package pages;
import org.openqa.selenium.By;import org.openqa.selenium.WebDriver;import org.openqa.selenium.WebElement;import org.openqa.selenium.support.FindBy;import org.openqa.selenium.support.PageFactory;
public class FlightBookingResponse {
 WebDriver driver;
 @FindBy(xpath = "//table[@width =\"492\"]/tbody/tr[1]/td/p") WebElement response;  public FlightBookingResponse(WebDriver driver) {  this.driver = driver;  PageFactory.initElements(driver, this); }
 public String getActual() {  System.out.println("Looking Booking  Confirmation");  System.out.println(response.getText());   return response.getText(); }}
