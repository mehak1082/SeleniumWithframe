package util;
import java.awt.Rectangle;import java.awt.Robot;import java.awt.Toolkit;import java.awt.image.BufferedImage;import java.io.File;import java.text.SimpleDateFormat;import java.util.Calendar;import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;import org.openqa.selenium.By;import org.openqa.selenium.Keys;import org.openqa.selenium.NoSuchElementException;import org.openqa.selenium.OutputType;import org.openqa.selenium.TakesScreenshot;import org.openqa.selenium.WebDriver;import org.openqa.selenium.chrome.ChromeDriver;import org.openqa.selenium.firefox.FirefoxDriver;import org.openqa.selenium.ie.InternetExplorerDriver;import org.testng.Reporter;
public class CommonActions { static ConfigReader config = new ConfigReader();
 public static WebDriver OpenAppl() {  WebDriver driver = LaunchBrowser();  OpenURL(driver);  return driver; }
 public static void OpenURL(WebDriver driver) {  System.out.println("Open URL");  driver.get(config.getUrl());
  driver.manage().window().maximize(); }
 public static WebDriver LaunchBrowser() {  String browsername = config.getBrowser();
  WebDriver driver = null;  switch (browsername.toUpperCase()) {  case "CHROME":   System.out.println(browsername);   System.setProperty("webdriver.chrome.driver", config.getChromePath());   driver = new ChromeDriver();   break;  case "FIREFOX":
   System.out.println(browsername);   System.setProperty("webdriver.gecko.driver", config.getFirefoxPath());   driver = new FirefoxDriver();   driver.manage().window().maximize();   break;  case "IE":
   System.out.println(browsername);   System.setProperty("webdriver.ie.driver", config.getExpPath());   driver = new InternetExplorerDriver();   break;    }
  driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);  System.out.println("Working");
  return driver; }
 public static boolean isElementPresent(WebDriver drv, String excelData) {  try {   drv.findElement(By.xpath(excelData));   return true;  } catch (NoSuchElementException e) {   return false;  } }
 public static void takeScreenshot(WebDriver driver, String testName) throws Exception {    String timeStamp;  File eventcapture = null;  File scrFile;    timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_sss").format(Calendar.getInstance().getTime());  StackTraceElement[] element = new Throwable().getStackTrace();  String callerClassName = element[1].getClassName();  String TestClass = callerClassName.substring(5);
  if (testName.equals("alertHandling")) {
   BufferedImage scrImg = new Robot()     .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));      eventcapture = new File(System.getProperty("user.dir")     + "\\Study\\MyTest\\Screenshots\\JavasciprtAlerts\\" + timeStamp + ".png");   ImageIO.write(scrImg, "png", eventcapture);  } else {   
   eventcapture = new File(System.getProperty("user.dir") + "\\Study\\MyTest\\Screenshots\\" + TestClass     + "\\" + timeStamp + ".png");   scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);   FileUtils.copyFile(scrFile, eventcapture);  }
  String filePath = eventcapture.toString();  System.out.println("Snapshot filepathc" + filePath);  String path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";  System.out.println("File Link: " + path);  Reporter.log(path);
 }
 /*  * public static void captureScreen(WebDriver driver) throws Exception {  * String timeStamp; File screenShotName; // The below method will save the  * screen shot in drive with name // "screenshot.png" timeStamp = new  * SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime  * ()); screenShotName = new File( System.getProperty("user.dir") +  * "\\Study\\MyTest\\Screenshots\\" + timeStamp + ".png"); BufferedImage  * scrFile = new Robot() .createScreenCapture(new  * Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));  * ImageIO.write(scrFile, "png", screenShotName); String filePath =  * screenShotName.toString(); System.out.println("Snapshot filepathc" +  * filePath); String path = "<img src=\"file://" + filePath +  * "\" alt=\"\"/>"; System.out.println("File Link: " + path);  * Reporter.log(path);  *   * }  */
 public static String clickElements(WebDriver drv, String locator, String locatorValue) throws Exception {  // Find by XPATH  System.out.println("Object passed is: " + locator);  System.out.println("ObjectType PAssed is: " + locatorValue + drv);  switch (locator.toUpperCase()) {  case "XPATH":
   drv.findElement(By.xpath(locatorValue)).click();      break;
  case "NAME":   drv.findElement(By.name(locatorValue)).click();      break;
  case "LINK":   drv.findElement(By.linkText(locatorValue)).click();   break;
  }  return null; }
 public static String sendKeys(WebDriver drv, String locator, String locatorValue, String data) throws Exception {
  System.out.println("");
  System.out.println("Object passed is: " + locator);  System.out.println("ObjectType PAssed is: " + locatorValue + drv);  System.out.println("Data PAssed is: " + data);
  switch (locator.toUpperCase()) {
  case "XPATH":   drv.findElement(By.xpath(locatorValue)).sendKeys(data);   break;
  case "NAME":   drv.findElement(By.name(locatorValue)).sendKeys(data);   break;
  case "LINK":   drv.findElement(By.linkText(locatorValue)).sendKeys(data);   break;
  }  return null;
 }
 public static String pressEnter(WebDriver drv, String locator, String locatorValue) throws Exception {
  System.out.println("");
  System.out.println("Object passed is: " + locator);  System.out.println("ObjectType PAssed is: " + locatorValue + drv);
  switch (locator.toUpperCase()) {
  case "XPATH":   drv.findElement(By.xpath(locatorValue)).sendKeys(Keys.ENTER);   break;
  case "NAME":   drv.findElement(By.name(locatorValue)).sendKeys(Keys.ENTER);   break;
  case "LINK":   drv.findElement(By.linkText(locatorValue)).sendKeys(Keys.ENTER);   break;
  }  return null;
 }
 public static String switchToFrame(WebDriver drv, String locator, String locatorValue) {  System.out.println("Object passed is: " + locator);  System.out.println("ObjectType PAssed is: " + locatorValue + drv);
  switch (locator.toUpperCase()) {  case "XPATH":   drv.switchTo().frame(drv.findElement(By.xpath(locatorValue)));   break;  case "ID":   drv.switchTo().frame(drv.findElement(By.id(locatorValue)));   break;  case "NO":   // drv.switchTo().defaultContent();   drv.switchTo().parentFrame();
   break;  }  return null; }
 
}
