package pages;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;

import helper.Utils;

public class NavigateToDevices extends BasePage {

	/**
	 * Page elements
	 */
	

	static By add_dirigera_hub_button_home = By.xpath("//android.widget.Button");
	static By add_dirigera_button = By.xpath("//android.view.ViewGroup[@resource-id=\"android:id/content\"]/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.Button");
	
	static By get_started_button = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.Button");
	static By ethernet_next_button = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.Button");
	static By power_cable_next_button = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.Button");
	
	static By ring_light_next = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.Button");
	static By need_more_help = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.Button");
	
	static By hub_ready_next = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.Button");
	
	static By looking_for_nearby_hub = By.xpath("//android.widget.TextView[@text=\"Looking for nearby hubs...\"]");
	
	public NavigateToDevices() throws MalformedURLException  {
		super();
	}

	/**
	 * Actions methods
	 * @throws InterruptedException 
	 */
	
	public static void verify_dirigera_hub_button() throws InterruptedException
	{
		Utils.scrollDownTill(add_dirigera_hub_button_home);
		Assert.assertTrue(isElementPresent(add_dirigera_hub_button_home));
		Thread.sleep(2000);
		click(add_dirigera_hub_button_home);	
		Thread.sleep(2000);
		click(add_dirigera_button);
		Thread.sleep(2000);
		
	}

	public static void connection_setup() throws InterruptedException
	{
		click(get_started_button);
		Thread.sleep(2000);
		click(ethernet_next_button);
		Thread.sleep(2000);
		click(power_cable_next_button);
		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent(need_more_help));
		click(ring_light_next);
		Thread.sleep(2000);
		click(hub_ready_next);
		Thread.sleep(2000);
	}
	
	public static void looking_for_nearbyhub() throws InterruptedException
	{
		Assert.assertTrue(isElementPresent(looking_for_nearby_hub));
		Thread.sleep(5000);
	}
	
}
