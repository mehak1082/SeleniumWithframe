package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import util.ExcelReader;
import util.ConfigReader;
import util.SheetDataBean;

public class FlightReservation {

	WebDriver driver;

	@FindBy(linkText = "Flights")
	public WebElement flightLink;

	@FindBy(name = "passCount")
	WebElement passengerCount;

	@FindBy(name = "fromPort")
	WebElement departurePort;

	@FindBy(name = "fromMonth")
	WebElement departureMonth;

	@FindBy(name = "fromDay")
	WebElement departureDate;

	@FindBy(name = "toMonth")
	WebElement returnMonth;

	@FindBy(name = "toDay")
	WebElement returnDate;

	@FindBy(name = "toPort")
	WebElement arrivalPort;

	@FindBy(name = "airline")
	WebElement airline;

	@FindBy(xpath = "//input[@name='tripType' and @value ='oneway']")
	WebElement oneWay;

	// @FindBy(xpath = "//input[@name='tripType' and @value ='roundtrip']")
	@FindBy(css = "[type='radio'][value='roundtrip']") // with CSSSelector
	
	//with HOW to find element using what values
	// @FindBy(how = How.CSS, using = "[type='radio'][value='rounftrip']") 

	WebElement returnJourney;

	@FindBy(name = "servClass")
	List<WebElement> classPref;

	@FindBy(name = "findFlights")
	WebElement searchFlight;

	public FlightReservation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void flightDetails(SheetDataBean shtBean) {

		if (shtBean.getTrip().equalsIgnoreCase("OneWay"))
			oneWay.click();
		if (shtBean.getTrip().equalsIgnoreCase("RoundTrip"))
			returnJourney.click();

		selectValueSelectBox(passengerCount, shtBean.getPassengerCount());
		selectValueSelectBox(departurePort, shtBean.getDepartFrom());
		selectValueSelectBox(departureMonth, shtBean.getDepartureMonth());
		selectValueSelectBox(departureDate, shtBean.getDepartureDate());
		selectValueSelectBox(arrivalPort, shtBean.getArrivalPort());
		if (shtBean.getTrip().equalsIgnoreCase("RoundTrip")) {
			selectValueSelectBox(returnMonth, shtBean.getReturnMonth());
			selectValueSelectBox(returnDate, shtBean.getReturnDate());
		}

		selectRadioBox(classPref, shtBean.getTravelClass());
		selectValueSelectBox(airline, shtBean.getAirline());

	}

	public void selectValueSelectBox(WebElement object, String value) {
		new Select(object).selectByVisibleText(value);
	}

	public void selectRadioBox(List<WebElement> object, String input) {
		for (WebElement classRadio : object) {

			if ((classRadio.getAttribute("value")).contains(input)) {
				System.out.println(input + "Radio buttons: " + classRadio.getAttribute("value"));
				classRadio.click();
			}

		}

	}

	public void submitSearch() {
		searchFlight.submit();
	}

	public ArrayList GetTripDetails() throws IOException, Exception {
		ConfigReader config = new ConfigReader();
		ExcelReader objexcel = new ExcelReader(System.getProperty("user.dir") + config.getDataFilePath());
		ArrayList list = objexcel.getFlightData();
		return list;

	}

	private boolean isElementPresent(WebElement we) {
		try {
			we.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}