                                           Mobile automation testNG framework

- Prequisites:
Appium server installed on the machine. In case not, install it by running command npm install -g appium. For more details visit: https://appium.io/docs/en/about-appium/getting-started/?lang=en

iOS Simulator or Android Emulator or real device to execute tests.

- Architecture Overview
- This code sets up a way to manage an AppiumDriver for mobile testing, making sure that when tests run in parallel, each thread gets its own driver. The class uses ThreadLocal to keep things thread-safe.

getDriver(): Pulls the driver for the current thread.
setDriver(AppiumDriver Driver): Sets the driver for the thread and logs that it's been set.
Basically, it helps avoid conflicts when running multiple tests at once by keeping each test's driver separate.
<img width="1177" alt="image" src="https://github.com/user-attachments/assets/d79a3b14-6e3a-4087-9dc9-2aae47195236">





