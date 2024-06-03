package pages;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;

public class LandingPage extends BasePage {

	/**
	 * Page elements
	 */	
	static By getstarted_button = By.xpath("//android.widget.Button");
	static By landingpage_text = By.xpath("//android.widget.TextView[contains(@text, 'Home smart app')]");
	static By getstarted_buttontext = By.xpath("//android.widget.TextView[@text=\"Get started\"]");

	public LandingPage() throws MalformedURLException  {
		super();
	}

	/**
	 * Actions methods
	 * @throws InterruptedException 
	 */
	public static void getStartedClick() throws InterruptedException {
		
		System.out.println("### Clicking on Get Started");
		Thread.sleep(2000);
		Assert.assertEquals(getText(getstarted_buttontext),"Get started");
		click(getstarted_button);
	}
	
	public static void checkLandingPage() throws InterruptedException
	{
		System.out.println("### Verifying the Landing Page");
		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent(landingpage_text), "Landing Page is not correct");
	}


}
