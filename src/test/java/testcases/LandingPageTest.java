package testcases;

import java.io.IOException;
import java.net.MalformedURLException;
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

public class LandingPageTest extends SetupTest {

	@BeforeClass
	public void before() throws MalformedURLException{
		System.out.println("---------- Setup ExtentReports: Before Class ----------");
		extent = new ExtentReports(filePath, true);
	}
	
	
	
	@AfterClass
	public void after(){
		extent.close();
	}
	
	
	@AfterMethod
	protected void afterMethod(ITestResult result) {
		System.out.println("----- Login Test Class: After Method -----");
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
	public void verifyLandingPage() throws InterruptedException {
		System.out.println("### LandingPage test 1");
		test = extent.startTest("verifyGetStarted"); //Title of Test in Report
		LandingPage.checkLandingPage();
		test.log(LogStatus.PASS, "Step 1 - Verify Landing page");
		System.out.println("Verify Landing page");
		String img = test.addScreenCapture(screenShotPath);
	    test.log(LogStatus.INFO, "Image", "Image example: " + img);
	}
	
	@Test(priority = 2)
	public void verifyGetStarted() throws InterruptedException {
		System.out.println("### GetStarted test 2");
		test = extent.startTest("verifyGetStartedClick").assignCategory("Sanity");
		System.out.println("Verify Clicking GetStarted page");

		LandingPage.getStartedClick();
		test.log(LogStatus.PASS, "Step 2");
		String img = test.addScreenCapture(screenShotPath);
	    test.log(LogStatus.INFO, "Image", "Image example: " + img);
	}
	
}
