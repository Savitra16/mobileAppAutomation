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









