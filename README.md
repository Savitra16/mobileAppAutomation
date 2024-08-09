                                           Mobile automation testNG framework

-> Prequisites:
Appium server installed on the machine. In case not, install it by running command npm install -g appium. For more details visit: https://appium.io/docs/en/about-appium/getting-started/?lang=en

iOS Simulator or Android Emulator or real device to execute tests.

-> Architecture Overview

<img width="1177" alt="image" src="https://github.com/user-attachments/assets/d79a3b14-6e3a-4087-9dc9-2aae47195236">

- This code sets up a way to manage an AppiumDriver for mobile testing, making sure that when tests run in parallel, each thread gets its own driver. The class uses ThreadLocal to keep things thread-safe.

Key Points:
getDriver(): Pulls the driver for the current thread.
setDriver(AppiumDriver Driver): Sets the driver for the thread and logs that it's been set.
Basically, it helps avoid conflicts when running multiple tests at once by keeping each test's driver separate.


<img width="1203" alt="image" src="https://github.com/user-attachments/assets/d6db66ad-24c1-47d6-bd06-e0673bc39b2e">

- This code is a factory class (AppFactory) that initializes an AndroidDriver for Appium testing. It handles the setup of the driver and then stores it in AppDriver using ThreadLocal. This allows the driver to be accessed throughout the project using AppDriver.getDriver().

androidLaunchApp(UiAutomator2Options options): Initializes the AndroidDriver with the provided options and sets it in AppDriver.

Key Points:
Thread-Safe: By setting the driver in ThreadLocal, the code ensures thread safety when running tests in parallel.
Reusability: The driver can be accessed from anywhere in the project using AppDriver.getDriver().

<img width="1188" alt="image" src="https://github.com/user-attachments/assets/71284552-6ec2-462c-afda-f721a088b463">
The AppOptions class is designed to set up the configuration options for running an Android Appium test using UiAutomator2Options.

Key Points:
initOptions(): This method initializes the options for running a test on an Android device. It sets up the platform details, device name, and the path to the SmartHome.apk file.

APK Setup: The APK file is located in the builds directory, and its path is set in the options.
Other Settings: The platform is set to Android 13, and UiAutomator2 is used as the automation engine. The app will not reset if it's already installed on the device.
getOptions(): This method returns the initialized UiAutomator2Options object, ensuring the options are ready to be used in a test.

-> Retry and RetryListner Logic
- The Retry class implements a retry mechanism for TestNG tests, allowing failed tests to be rerun automatically.
<img width="1348" alt="image" src="https://github.com/user-attachments/assets/acd02273-d0ed-4f0b-8b2e-c47d616c02e3">
retry(ITestResult result): This method determines if a test should be retried based on the retryCount and maxRetryCount. If the retryCount is less than maxRetryCount, the test is retried, and the retry count is incremented. It returns true if the test should be retried, otherwise false.

  -> The RetryListener class is designed to automatically apply a retry mechanism to TestNG tests by implementing the IAnnotationTransformer interface.
<img width="1129" alt="image" src="https://github.com/user-attachments/assets/7daced2d-d452-4086-9f0c-a616a4d5a8d3">

-> TestListner logic:
The TestListener class implements the ITestListener interface in TestNG to enhance test result handling, specifically focusing on test retries, skipped tests, and capturing screenshots on test failures.
<img width="1293" alt="image" src="https://github.com/user-attachments/assets/b33b12ce-1490-4d3d-96ec-10359503ded1">

-> Utils Class:
<img width="1192" alt="image" src="https://github.com/user-attachments/assets/d1ad7331-2936-4d10-a13b-1fc22d6d0fca">

This code provides utility functions for scrolling and swiping actions on a mobile or web application using Selenium WebDriver. Here's a summary of its functionality:

-> Swipe Method:
swipe(Point start, Point end, Duration duration): Simulates a swipe gesture on the screen from a starting point to an ending point over a specified duration. It uses PointerInput and Sequence from Selenium's interactions package to perform this action.

-Scroll Method:
scroll(String pageDirection, double scrollRatio): Scrolls the page in a specified direction (UP, DOWN, LEFT, or RIGHT) based on the given scrollRatio.
scrollRatio determines the distance of the scroll relative to the screen's dimensions (0 to 1). For example, 0.5 scrolls half the screen height or width.
It calculates the scroll bounds based on the screen size and performs the swipe action accordingly.

-Scroll Down Until Element Found:
scrollDownTill(By elementToFind): Continuously scrolls down until a specified element (elementToFind) is found. It uses a while loop to keep scrolling and checking for the element, with a 2-second delay between scrolls.

-Scroll Up Until Element Found:
scrollUpTill(By elementToFind): Similar to the scroll-down method but scrolls upwards until the specified element is found.

-> Base Class
<img width="1156" alt="image" src="https://github.com/user-attachments/assets/1204e6f0-79e1-4c4c-8beb-ec96e7fe5538">
This code defines a Java class BasePage that serves as a parent class for all page classes in a mobile application testing framework using Appium and Selenium for Android devices. Here's a summary of the key functionalities:

-> Initialization and Configuration:
The BasePage constructor initializes the driver and loads properties from a configuration file (qa.properties).
The getProperties method reads properties to determine the operating system and device type.

The initDriver_v8 method initializes the Android driver if the OS is specified as "ANDROID".

-> Utility Methods:
- Element Locators: Methods like getByElement and getMobileByElement return element locators based on the platform.
- Screenshot Capture: The getScreenshot method captures a screenshot and saves it to a specified directory.
- Element Interactions: Methods like sendKeys, click, getText, and getAttributeValue interact with elements on the page.
- Page Navigation: navigateBack navigates back in the app.
- Element Verification: isPageLoaded and isElementPresent check if elements are present on the page.
- Swipe Actions: Methods like swipeVerticallyBottomToUp and swipeHorizontal perform swipe actions on the screen.

- Swiping and Scrolling:
swipeVerticallyBottomToUp performs vertical swipes until a specified element at the bottom of the content is found.
swipeHorizontal and swipeVertical methods are placeholders for horizontal and vertical swiping but are commented out.

-> Driver Management: The quit method stops the driver and closes the app.

- Setup Test.java :
<img width="1111" alt="image" src="https://github.com/user-attachments/assets/e3b09751-fff6-4fca-889e-bd8d750d2532">
The provided code defines a SetupTest class in a TestNG-based test framework. This class is responsible for setting up and tearing down the test environment, as well as managing the reporting of test results. Here's a summary of the key functionalities:

-> Reporting Setup:
The class uses ExtentReports and ExtentTest from the ExtentReports library to generate and manage test reports.
The report is saved to a specified file path (report.html) in the src/test/resources/extentreporting directory.

Test Initialization:

- The beforeSuite method is annotated with @BeforeSuite, meaning it runs before any tests in the suite. This method:
Initializes the ExtentReports object for reporting.
Instantiates the BasePage class, which initializes the driver for the mobile testing framework.

->Test Teardown:
The end method is annotated with @AfterSuite, meaning it runs after all tests in the suite have completed. This method:
Flushes the ExtentReports object, ensuring all logged information is written to the report file.
Closes the ExtentReports object, finalizing the report.

Purpose:
This SetupTest class is designed to be included in every testng.xml file so that the driver is initialized once for the entire test suite, and reporting is handled consistently across all tests.















