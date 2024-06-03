package testcases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import pages.*;

public class NavigateToDevicesTest extends SetupTest {

	 @BeforeClass
	public void before() {
		System.out.println("---------- Setup ExtentReports: Before Class ----------");
		extent = new ExtentReports(filePath, false);
	}

	 @AfterClass
	public void after() {
		extent.close();
	}


	@AfterMethod
	protected void afterMethod(ITestResult result) {
		System.out.println("----- Search Test Class: After Method -----");
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			test.log(LogStatus.PASS, "Test passed");
		}

		extent.endTest(test);
		
	}
	
	
	@Test(priority = 1)
	public void verifyDirigeraHubFunctionality() throws InterruptedException {
		System.out.println(">>> Verify Dirigera Hub Button");

		test = extent.startTest("Verify Dirigera Hub Button").assignCategory("Sanity");
		NavigateToDevices.verify_dirigera_hub_button();
		test.log(LogStatus.PASS, "Step A");
		Assert.assertTrue(true);
	}
	
	@Test(priority = 2)
	public void verifyConnectionSetup() throws InterruptedException {
		System.out.println(">>> Verify Connection Setup");

		test = extent.startTest("Verify Connection Setup").assignCategory("Sanity");
		NavigateToDevices.connection_setup();
		test.log(LogStatus.PASS, "Step B");
	}
	
	@Test(priority = 3)
	public void verifyLookingforNearbyHubFunctionality() throws InterruptedException {
		System.out.println(">>> Verify Looking fir nearby Hub");

		test = extent.startTest("Verify Looking for Nearby Hub").assignCategory("Sanity");
		NavigateToDevices.looking_for_nearbyhub();
		test.log(LogStatus.PASS, "Step C");
	}


}
