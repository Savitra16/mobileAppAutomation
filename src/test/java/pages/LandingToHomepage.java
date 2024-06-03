package pages;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;

import helper.Utils;

public class LandingToHomepage extends BasePage {

	/**
	 * Page elements
	 */
	
	
	static By change_region_button = By.id("com.inter.homesmart.system2:id/arrow_icon");
	static By default_region = By.xpath("//android.widget.TextView[@resource-id=\"com..inter.homesmart.system2:id/region_label\"]");
	static By region_next_button = By.xpath("//android.widget.Button[@resource-id=\"com.inter.homesmart.system2:id/primary\"]");
	static By region_select_checkbox = By.xpath("//android.widget.CheckedTextView[@resource-id=\"com.inter.homesmart.system2:id/checkbox\" and @text=\"United Kingdom\"]");
	static By region_sweden_checkbox = By.xpath("//android.widget.CheckedTextView[@resource-id=\"com.inter.homesmart.system2:id/checkbox\" and @text=\"Sweden\"]");
	static By next_button_region_change = By.xpath("//android.widget.Button[@resource-id=\"com.inter.homesmart.system2:id/primary\"]");

	static By read_privacy_statement = By.xpath("//android.widget.TextView[@resource-id=\"com.inter.homesmart.system2:id/read_privacy_statement\"]");
	static By next_button_privacy = By.xpath("//android.widget.Button[@resource-id=\"com.inter.homesmart.system2:id/primary\"]");
	
	static By consent_checkbox_yes = By.xpath("//android.widget.CheckBox[@resource-id=\"com.inter.homesmart.system2:id/consent_checkbox\"]");
	static By consent_next = By.xpath("//android.widget.Button[@resource-id=\"com.inter.homesmart.system2:id/primary\"]");
	
	
	static By share_Button = By.xpath("//android.widget.Button[@resource-id=\"com.inter.homesmart.system2:id/secondary\"]");
	static By accept_terms_conditon_button = By.xpath("//android.widget.Button[@resource-id=\"com.inter.homesmart.system2:id/primary\"]");
	static By accept_terms_conditon_button_enabled = By.xpath("//android.widget.Button[@resource-id=\"com.inter.homesmart.system2:id/primary\" and @enabled = \"true\"]");
	
	static By end_page_Scroll = By.xpath("//android.widget.ImageView[@resource-id=\"com.inter.homesmart.system2:id/hint\"]");
	
	public LandingToHomepage() throws MalformedURLException  {
		super();
	}

	/**
	 * Actions methods
	 */
	
	public static void verify_default_region()
	{
		Assert.assertTrue(isElementPresent(change_region_button));
		//Check the default region selected and validate it
		Assert.assertEquals(getText(default_region), "United States");	
	}

	public static void changeregion() throws InterruptedException
	{
		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent(change_region_button));
		click(change_region_button); // Click on region change
		Thread.sleep(2000);
		
		Utils.scrollUpTill(region_sweden_checkbox);
		click(region_sweden_checkbox);
		
		Thread.sleep(2000);
		click(next_button_region_change);
		Thread.sleep(2000);
	}
	
	public static void region_next_action() throws InterruptedException
	{
		//Click on next button
		click(region_next_button);
		Thread.sleep(2000);
	}
	
	public static void verify_next_privacy() throws InterruptedException
	{
		Thread.sleep(2000);
		Utils.scrollDownTill(read_privacy_statement);
		Utils.scroll("DOWN", 0.5);
		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent(next_button_privacy));
		click(next_button_privacy);
		Thread.sleep(2000);
	}
	
	
	public static void verify_consent() throws InterruptedException
	{
		Utils.scroll("DOWN",0.2);
		Assert.assertTrue(isElementPresent(consent_checkbox_yes));
		click(consent_checkbox_yes);
		Thread.sleep(2000);
		click(consent_next);
		Thread.sleep(2000);
		
	}
	
	public static void verify_terms_conditions() throws InterruptedException
	{
		Utils.scrollDownTill(end_page_Scroll);
		click(end_page_Scroll);
		Thread.sleep(3000);
		click(accept_terms_conditon_button);
		Thread.sleep(5000);
		
	}
	

}
