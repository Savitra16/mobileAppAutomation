package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import helper.AppDriver;
import helper.AppFactory;
import helper.AppOptions;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/*
 * Base page is parent class of all page classes
 * contains all static general methods
 * reading property file + driver repo 
 * 
 * @author: Savitra
 */
public class BasePage {

	// static AppiumDriver driver;
	static AndroidDriver driver;
	static String userpath=System.getProperty("user.dir");

	public Properties properties;
	String cmd_osdevice, osdevice, device;
	static String os;

	public BasePage() throws MalformedURLException {
		getProperties();
		initDriver_v8(os, device); // Initializing the Driver
	}

	public void getProperties() {
		// reading from properties file
		properties = new Properties();
		try {
			properties.load(new FileInputStream("src/test/resources/qa.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		cmd_osdevice = properties.getProperty("osdevice");

		if (cmd_osdevice != null && !cmd_osdevice.trim().isEmpty()) {
			os = cmd_osdevice.split("#")[0];
			device = cmd_osdevice.split("#")[1];
			System.out.println("###------ Reading from mvn cmd line -----###");
			System.out.println("#OS = " + os);
			System.out.println("#Device = " + device);

		}
	}

	public void initDriver_v8(String os, String device) throws MalformedURLException {
		System.out.println("###------ Initialising Driver v8 -----###");
		
		if (os.equals("ANDROID")) {
			AppOptions option = new AppOptions();
			AppFactory.androidLaunchApp(option.getOptions());

			//Android Driver
			driver = (AndroidDriver) AppDriver.getDriver();
		}  else {
			System.out.println("Driver: no driver selected");
			System.out.println("Running test with driver: " + os + " & device: " + device);
		}

	}

	/**
	 * Based on android and ios, this method will return By locator of any element
	 */
	public static By getByElement(By android_element, By ios_element) {
		if (os.equalsIgnoreCase("ANDROID")) {
			return android_element;
		}
		return null;
	}

	public static MobileBy getMobileByElement(MobileBy android_element, MobileBy ios_element) {
		if (os.equalsIgnoreCase("ANDROID")) {
			return android_element;
		}
		return null;
	}

	public static void quit() {
		driver.quit();
	}


	/*
	 * This method will take screen shot and place in specified folder
	 */
	public static void getScreenshot(String testclass, String testname) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss'.png'").format(new Date());
		String dir;

		// todo: path need to be relative
		if (os.equalsIgnoreCase("android")) {
			System.out.println("BasePage > getScreenShot");
			dir = "src/test/resources/screenshots/android/";
} else {
			dir = "src/test/resources/screenshots/ios/";
		}

		String path = dir + testclass + "_" + testname + "_" + timestamp;
		System.out.println("path:" + path);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(path));
	}

	public static void sendKeys(By locator, String keyword) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(keyword);
	}

	public static void navigateBack() {
		driver.navigate().back();
	}

	public static void click(By locator) {
		driver.findElement(locator).click();
	}

	public static boolean isPageLoaded(By locator) {
		return driver.findElement(locator).isDisplayed();
	}

	public static boolean isElementPresent(By locator) {
		return driver.findElement(locator).isDisplayed();
	}

	public static List getElementList(By locator) {
		return driver.findElements(locator);
	}

	public static String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	public static String getAttributeValue(By locator, String attribute) {
		return driver.findElement(locator).getAttribute(attribute);
	}

	/**
	 * Method will swipe from bottom to top direction means page will move towards
	 * downward accept parameter like 0.90, 0.10 and swipe accordingly
	 */

	public static void swipeVerticallyBottomToUp(double top, double bottom, By bottomElement) {
		
		 Dimension size = driver.manage().window().getSize();

	        int startX = size.width / 2;
	        int startY = (int) (size.height * top);
	        int endY = (int) (size.height * bottom);

	        TouchAction touchAction = new TouchAction(driver);

	        // Define the wait duration between swipes
	        Duration duration = Duration.ofMillis(1000);

	        // Perform repeated swipes until the end of content is reached
	        while (!isEndOfContent(bottomElement)) {
	            touchAction.press(PointOption.point(startX, startY))
	                       .moveTo(PointOption.point(startX, endY))
	                       .waitAction(WaitOptions.waitOptions(duration))
	                       .release()
	                       .perform();
	        }
	}
	
	 private static boolean isEndOfContent(By bottomElement) {
	    boolean elemeentfound= false;
		 try {
	            // Replace "bottomElementLocator" with the locator of an element at the bottom of the page
			 elemeentfound = driver.findElement(bottomElement).isDisplayed();
	        } catch (org.openqa.selenium.NoSuchElementException e) {
	            // Element not found, continue scrolling
	        }
	        return elemeentfound;
	    }

	public static void swipeHorizontal(double startPercentage, double finalPercentage, double anchorPercentage,
			int duration) throws Exception {
		/*
		 * TouchAction action = new TouchAction(driver); Dimension size =
		 * driver.manage().window().getSize(); int width=size.width; int
		 * height=size.height;
		 * 
		 * int middleOfY=height/2; int startXCoordinate= (int)(width*.7); int
		 * endXCoordinate= (int)(width*.2);
		 * 
		 * action.press(PointOption.point(middleOfY, startXCoordinate))
		 * .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
		 * .moveTo(PointOption.point(middleOfY, endXCoordinate)).release().perform();
		 */
	}

	public static void swipeVertical(double startPercentage, double finalPercentage, double anchorPercentage,
			int duration) throws Exception {
		/*
		 * TouchAction action =new TouchAction(driver); Dimension size
		 * =driver.manage().window().getSize(); int width=size.width; int
		 * height=size.height; int middleOfX=width/2; int startYCoordinate=
		 * (int)(height*.7); int endYCoordinate= (int)(height*.2);
		 * 
		 * action.press(PointOption.point(middleOfX, startYCoordinate))
		 * .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
		 * .moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
		 */
	}
	

}
