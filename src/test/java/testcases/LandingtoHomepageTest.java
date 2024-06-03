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

public class LandingtoHomepageTest extends SetupTest {

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
	public void verifyRegionChangeFunctionality() throws InterruptedException {
		System.out.println(">>> Verify Region Change");

		test = extent.startTest("Verify Region Change").assignCategory("Sanity");
		LandingToHomepage.verify_default_region();
		LandingToHomepage.changeregion();
		LandingToHomepage.region_next_action();
		test.log(LogStatus.PASS, "Step A");
		Assert.assertTrue(true);
	}
	
	@Test(priority = 2)
	public void verifyPrivacyFunctionality() throws InterruptedException {
		System.out.println(">>> Verify Privacy check");

		test = extent.startTest("Verify Privacy check").assignCategory("Sanity");
		LandingToHomepage.verify_next_privacy();
		test.log(LogStatus.PASS, "Step B");
	}
	
	@Test(priority = 3)
	public void verifyConsentFunctionality() throws InterruptedException {
		System.out.println(">>> Verify Consent check");

		test = extent.startTest("Verify Consent check").assignCategory("Sanity");
		LandingToHomepage.verify_consent();
		test.log(LogStatus.PASS, "Step C");
	}
	
	@Test(priority = 4)
	public void verifyTermsConditions() throws InterruptedException {
		System.out.println(">>> Verify Terms and Conditions");

		test = extent.startTest("Verify Share and Accept for Terms and Conditions").assignCategory("Sanity");
		LandingToHomepage.verify_terms_conditions();
		test.log(LogStatus.PASS, "Step D");
	}

}
