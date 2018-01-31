package test;
import java.lang.reflect.Method;import java.util.ArrayList;
import org.testng.Assert;import org.testng.Reporter;import org.testng.annotations.*;
import pages.FlightBookingResponse;import pages.FlightReservation;import util.CommonActions;import util.SheetDataBean;import org.openqa.selenium.*;
public class FlightSearchBook { WebDriver driver;  Method method;  String methodName;
 @BeforeClass() public void setUp() throws Exception {  driver = CommonActions.OpenAppl();  CommonActions.takeScreenshot(driver, "");
 }
  @BeforeMethod    public void before(Method m) {        method = m;        methodName = method.getName();           }
 @Test public void flightBooking() throws Exception {    CommonActions.takeScreenshot(driver, "");  FlightReservation flightBook = new FlightReservation(driver);  FlightBookingResponse flightBookResponse = new FlightBookingResponse(driver);  flightBook.flightLink.click();  CommonActions.takeScreenshot(driver, "");
  ArrayList list = flightBook.GetTripDetails();
  for (int i = 0; i < list.size(); i++) {   Reporter.log("<br>");   Reporter.log("<br>");   SheetDataBean sheetDataBean = (SheetDataBean) list.get(i);   flightBook.flightDetails(sheetDataBean);   CommonActions.takeScreenshot(driver, "");   flightBook.submitSearch();   CommonActions.takeScreenshot(driver, "");
   String expectedText = "After flight finder - No Seats Avaialble  " + "\n" + "\n"     + "Please press your browser's back button to return to the previous page or click the \"BACK TO HOME\" icon below to go to the homepage.";   Thread.sleep(2000L);      String actualText = flightBookResponse.getActual();      Assert.assertTrue(actualText.equals(expectedText));   if ((i + 1) != (list.size())) {    driver.navigate().back();   }  } }
 @AfterClass(alwaysRun = true) public void tearDown() throws Exception {  driver.quit(); }
}
