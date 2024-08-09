                                           Mobile automation testNG framework

- Prequisites:
Appium server installed on the machine. In case not, install it by running command npm install -g appium. For more details visit: https://appium.io/docs/en/about-appium/getting-started/?lang=en

iOS Simulator or Android Emulator or real device to execute tests.

- Architecture Overview

<img width="1177" alt="image" src="https://github.com/user-attachments/assets/d79a3b14-6e3a-4087-9dc9-2aae47195236">
- This code sets up a way to manage an AppiumDriver for mobile testing, making sure that when tests run in parallel, each thread gets its own driver. The class uses ThreadLocal to keep things thread-safe.
Key Points:
getDriver(): Pulls the driver for the current thread.
setDriver(AppiumDriver Driver): Sets the driver for the thread and logs that it's been set.
Basically, it helps avoid conflicts when running multiple tests at once by keeping each test's driver separate.


<img width="1203" alt="image" src="https://github.com/user-attachments/assets/d6db66ad-24c1-47d6-bd06-e0673bc39b2e">
-This code is a factory class (AppFactory) that initializes an AndroidDriver for Appium testing. It handles the setup of the driver and then stores it in AppDriver using ThreadLocal. This allows the driver to be accessed throughout the project using AppDriver.getDriver().

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

- Retry and RetryListner Logic
- The Retry class implements a retry mechanism for TestNG tests, allowing failed tests to be rerun automatically.
<img width="1348" alt="image" src="https://github.com/user-attachments/assets/acd02273-d0ed-4f0b-8b2e-c47d616c02e3">
retry(ITestResult result): This method determines if a test should be retried based on the retryCount and maxRetryCount. If the retryCount is less than maxRetryCount, the test is retried, and the retry count is incremented. It returns true if the test should be retried, otherwise false.

- The RetryListener class is designed to automatically apply a retry mechanism to TestNG tests by implementing the IAnnotationTransformer interface.
<img width="1129" alt="image" src="https://github.com/user-attachments/assets/7daced2d-d452-4086-9f0c-a616a4d5a8d3">

- TestListner logic:
The TestListener class implements the ITestListener interface in TestNG to enhance test result handling, specifically focusing on test retries, skipped tests, and capturing screenshots on test failures.
<img width="1293" alt="image" src="https://github.com/user-attachments/assets/b33b12ce-1490-4d3d-96ec-10359503ded1">

- Utils Class:
<img width="1192" alt="image" src="https://github.com/user-attachments/assets/d1ad7331-2936-4d10-a13b-1fc22d6d0fca">

This code provides utility functions for scrolling and swiping actions on a mobile or web application using Selenium WebDriver. Here's a summary of its functionality:

Swipe Method:

swipe(Point start, Point end, Duration duration): Simulates a swipe gesture on the screen from a starting point to an ending point over a specified duration. It uses PointerInput and Sequence from Selenium's interactions package to perform this action.
Scroll Method:

scroll(String pageDirection, double scrollRatio): Scrolls the page in a specified direction (UP, DOWN, LEFT, or RIGHT) based on the given scrollRatio.
scrollRatio determines the distance of the scroll relative to the screen's dimensions (0 to 1). For example, 0.5 scrolls half the screen height or width.
It calculates the scroll bounds based on the screen size and performs the swipe action accordingly.
Scroll Down Until Element Found:

scrollDownTill(By elementToFind): Continuously scrolls down until a specified element (elementToFind) is found. It uses a while loop to keep scrolling and checking for the element, with a 2-second delay between scrolls.
Scroll Up Until Element Found:

scrollUpTill(By elementToFind): Similar to the scroll-down method but scrolls upwards until the specified element is found.

-














