# Mobile Automation Testing with Appium in Java

This project is a boilerplate for mobile application automation testing. It uses Java as the programming language,
Cucumber for behavior-driven development (BDD), and Appium for mobile application testing.

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Setup](#setup)
3. [Project Tree and Functions of Branches](#project-tree-and-functions-of-branches)
4. [Running Tests](#running-tests)
5. [Run Tests with Specific Platform](#run-tests-with-specific-platform)
6. [Run Tests with Specific Capability](#run-tests-with-specific-capability)
7. [Run Tests with Specific Tag](#run-tests-with-specific-tag)
8. [Allure Reporting](#allure-reporting)
  

## Prerequisites

- Java Development Kit (JDK) 8 or above
- Maven
- Appium
- Cucumber
- An Android or iOS Emulator/Simulator or real device
- IDE (IntelliJ IDEA, Eclipse, etc.)

## Setup

1. **Clone the repository:**

    ```
    https://github.com/kloia/mobile-automation-testing-appium-java.git
    ```

2. **Navigate to the project directory:**

    ```
    cd mobile-automation-testing-appium-java
    ```

3. **Install dependencies:**

    ```
    mvn install
    ```

4. **Edit Test Capabilities:**

   Open `DigitalAIConfig` and modify the `DesiredCapabilities` according to your test device and application.





## Project Tree and Functions of Branches
```
.gitignore                                              📍Config file with list of unwanted files and folders in Git repository
README.md                                               📍It is a text file that contains the purpose, usage and other basic information of the project
pom.xml                                                 📍Management of the libraries to be used in the project
src
   |-- test
   |   |-- java
   |   |   |-- configs.DigitalAIConfigs
   |   |   |   |-- Android.properties                    📍Configuration file for Android platform capabilities.
   |   |   |   |-- DigitalAIConfig.java
   |   |   |   |-- IOS.properties                        📍Configuration file for IOS platform capabilities.
   |   |   |-- test
   |   |   |   |-- features                              📍Cucumber feature file defining test scenarios.
   |   |   |   |   |-- Login.feature
   |   |   |   |   |-- ProductListing.feature
   |   |   |   |-- runners
   |   |   |   |   |-- TestRunner.java                    📍TestNG test runner class with Cucumber options, setting up test execution, and providing data-driven scenarios.
   |   |   |   |-- stepDefinations
   |   |   |   |   |-- Hooks.java                         📍Hooks class with setup and teardown methods using TestNG annotations for managing Appium driver instances.
   |   |   |   |   |-- LoginSteps.java
   |   |   |   |   |-- ProductListingSteps.java
   |   |   |   |- - pages
   |   |   |   |   |-- LoginPage.java
   |   |   |   |   |-- ProductListingPage.java
   |   |   |-- utilities
   |   |   |   |-- PageHelper.java                        📍Helper class for common actions and methods used in page classes.
   |   |   |   |-- ConfReader.java                        📍Configuration reader class for reading properties files.
   |   |   |   |-- DesiredCapabilitiesUtil.java           📍Utility class for creating Appium DesiredCapabilities based on the configuration.
   |   |   |   |-- ThreadLocalDriver.java                 📍Thread-safe singleton class for managing the Appium driver instance.
testng.xml                                                📍TestNG is an XML file that contains configuration settings (test suites, test methods, parameters, groups, etc.) for the testing framework.

```




## Running Tests

To run tests, you can either run them through your IDE or via the command line.

- **Command Line:**

    ```
    mvn test
    ```

- **IntelliJ IDEA:**

  Right-click on the test directory and choose "Run 'All Tests'".


## Run Tests with Specific Platform

The project is configured to support both iOS and Android platforms. Make sure to choose the appropriate configuration for your tests. Use the following commands in the terminal to run tests on Android or iOS devices:

Android: ``` mvn test -Dplatform=Android ```

iOS:  ```mvn test -Dplatform=IOS ```


## Run Tests with Specific Capability

To change a specific capability value, use the following command in the terminal:

```mvn test  -DtestName=mobile test```

Replace testName with the desired capability key, and mobile with the new value.


## Run Tests with Specific Tag

To change a specific tag, use the following command in the terminal:

```mvn test -Dtags="@login"```

Replace the @login tag with the tag you want


## Run Tests with Specific Environment

The project is configured to support both local and remote environments. Make sure to choose the appropriate configuration for your tests. To run tests in a local or remote environment, use the following commands in the terminal:

Local: ``` mvn test -Denvironment=Local ```

Remote:  ```mvn test -Denvironment=DigitalAI ```


## Allure Reporting

After the test execution, Allure reports are generated in the target/allure-results directory. Use the Allure command mentioned below to view the reports.

```allure serve target/allure-results```

