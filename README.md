# Rocket Fuel Framework

This project is all about the front end test creation portion of the Rocket Fuel Framework. If you are looking for the
'fuel' that powers all the functionality of the framework, you can find
that [here](https://github.com/BottleRocketStudios/rocket-fuel-framework/blob/main/).

## Table of Contents

- [What is the Rocket Fuel Framework?](#what-is-the-rocket-fuel-framework)
- [Getting Started](#getting-started)
- [Sounds Great! But How Do I Dive in?](#sounds-great-but-how-do-i-dive-in)
- [Lifting off (Getting your first run)](#lifting-off-getting-your-first-run)
    - [Practice with an Example Test](#practice-with-an-example-test)
        - [Creating Your Own Test](#creating-your-own-test)
            - [Create your own assertion](#create-your-assertion)
        - [Creating a Project](#creating-a-project)
- [Running Your Tests](#running-your-tests)
    - [Using an IDE](#using-an-ide)
    - [Using the gradle wrapper](#using-the-gradle-wrapper)
- [Automation Test Suite Structure and Modularity](#automation-test-suite-structure-and-modularity)
    - [Project Structure](#project-structure)
    - [Test Layer](#test-layer)
    - [Driver Wrapper](#driver-wrapper)
    - [Navigation Operations (NavOps)](#navigation-operations-navops)
    - [User Operations (UserOps)](#user-operations-userops)
    - [Assertion Payload](#assertion-payload)
    - [AssertionLibrary and Making an Assertion](#assertionlibrary-and-making-an-assertion)
    - [Resource Locator and Resource Locator Bundle(RLB)](#resource-locator-and-resource-locator-bundlerlb)
- [Features and Integrations](#features-and-integrations)
- [Prerequisites](#prerequisites)
- [Contributing](#contributing)
- [Versioning](#versioning)
- [License](#license)
- [Acknowledgements](#acknowledgments)

## What is the Rocket Fuel Framework?

The Rocket Fuel Framework is a mostly Java-based automation framework that is designed to accelerate the creation of QA
automation tests. It can be overwhelming to find an ideal structure for you and your team to build automated tests.
Sure the page object model (POM) is a great start, and you will find familiar principles from that here, but what about
all the areas that POM doesn't cover? Developing a test creation process for multiple different platforms, devices, and
applications can be a daunting
task, whether for a single person or project, or many. You may wonder, how do I maximize common features and flows
across each platform under test?
How do I minimize time spent on maintenance when tests inevitably change, and what's the best way to grow my test suite
as my application grows?
How much time do I have to spend adding in reporting, integration with Sauce Labs, and other features? What about when I
get to an edge case like needing a
code from an email, or setting up test cases using API calls, or verifying data using a database?

The Rocket Fuel Framework (RFF) is not a specific Appium or Selenium wrapper. It is also not simply a way to create and
maintain tests.
Nor is it only capable of making API and database calls, either to test those entities or to set up data for other
tests.
Instead, RFF is a rich toolbox that puts an emphasis on being versatile, easy to use, maintainable, and to allow for
code reuse across different platforms while allowing you to focus on one single codebase.

RFF is meant to be your blueprint to establish an easy, repeatable, scalable and maintainable process that provides a
set of tools to tackle whatever test you are trying to create. Many common problems such as grabbing a code from an
email, setting up
test cases using API calls, data verification using a database, and more have all been tackled numerous times before
using this framework.

Further, RFF makes it easy for you to create automated tests for a single platform like iOS, and easily reuse code to
test similar
flows and features on other platforms like Android and Web, while not having to start from scratch. All the while
allowing you to integrate with
the other framework pieces. Need to sync your UI tests with calls to an API? No problem. Need to run your tests on Sauce
Labs? Easy.
Need to monitor network traffic? Done. Need to add some crazy feature that no one has thought of yet?
The framework is easily extensible to add in whatever feature you need, and then can be used in conjunction with the
other features of the framework.

We've taken all of our experiences working on dozens of automation projects, and distilled our learnings down into this
framework, so that you don't have to solve the same problems we'd had to solve many times over. Use this framework as
the culmination of community
knowledge and efforts from a tribe of engineers across many disciplines as a springboard to go create your own magic.

Welcome to the Rocket Fuel Framework.

## Getting Started

[Woah, this is too many words, I just want to do cool stuff](#sounds-great-but-how-do-i-dive-in)

This framework is built primarily using Java, and aims to provide a versatile, easy to use, maintainable, and
reusable toolbox for writing QA automation tests.
You can use this framework to write tests for both mobile and web applications, as well as making HTTP requests, sending
shell commands, and more.

There is a well, defined structure for writing UI automation tests which has been the primary focus of this framework.
Following the suggested approach will result in a maintainable and scalable test suite, that will allow for supporting
multiple platforms
and devices with minimal effort, while allowing for code reuse if the application is sufficiently similar across
platforms. The framework itself is the engine, while the test creation process is the assembly line which puts the
engine in the car
and gets it running [found here](https://github.com/BottleRocketStudios/rocket-fuel-framework).
This repo is an example of how the test creation process works. To make a new test,
you can use this repo as a template, and start building your tests from there.
If you want examples of full tests and the parts that go with them check out [this](src/test/java/example). Starter code
mimicking the examples can be found in the main part of this repo.

If you prefer the hands-on approach, you can clone the framework and go straight to the examples package for a full
example of
how to use the framework. There are default values set to get you started, but we encourage you to change them to fit
your needs.
Alternatively, you can continue reading this readme to get a better understanding of the framework and how to use it.

If there is a feature that you are interested in but are unsure of
how to use or unable to find an example, please reach out to us, or dive into the codebase and see if you can find the
answer there.

You should be able to change the version you are using in the project
build.gradle

## Sounds Great! But How Do I Dive in?

We are glad you asked! The best way to get started is to clone this repo and take a look at the example. There's
no substitute for getting in the code and trying it out yourself. However, we also understand it can
be daunting to jump into a codebase and not know where to start. So we've provided a few examples to get you started.
If you want the quickest path to seeing something work end to end,
opt for a web test first. It's the easiest to set up and run, and you can see the results in your browser without
needing any of the Appium setup.

To get started we are going to go over to [resource](src/main/resources) and update the properties files to use
your values. The appconfig.properties should be on web, so for this intro that's perfect. You'll then want to hop over
to [web_config](src/main/resources/web_config.properties)
to update the location you want your reports to go to. You can also update the browser you want to use in the
web_config.properties file.
You will need to have the corresponding driver for the browser you are using, assuming it is not included with the
browser itself.
You'll be able to find the driver easy enough online, just make sure it matches your environment OS. From there you
should be able to run the
tests in [QuickTest](src/test/java/automationtests/testingautomationtests/QuickTest.java). This should verify your
environment is set correctly.
Note some of the tests will 'fail' as they are meant to, but you should see the reports generated in the location you
specified in the web_config.properties file.
If your test launched a browser, and you saw some tests run, you are good to go! If not, feel free to reach out to us,
and we can help you troubleshoot.

This is the 'heartbeat' sensor for the system to get a sanity check. Read on to create your first test!

## Lifting off (Getting your first run)

As mentioned previously there are essentially two components to the RFF. The framework itself, and your 'front end', ie
the project where you actually create your tests.

You will need a project to run the tests in, which is exactly why we give you this repo to get you started!
The framework is a jar file of common features that can be used across multiple projects, while the test creation front
end is a set of guidelines and examples to smooth the process of creating, scaling, and maintaining your tests.
To change to a newer version of the framework, simply update the version in the build.gradle file and make sure that
version exists where your code can get it. The easiest way to manage it is to put the jar in your local maven
repository,
but you can also put it in [framework_snapshots](framework_snapshots).

Hopefully by now you've successfully run the QuickTest and are ready to dive into a test you create!

### Practice with an Example Test

After you've run the QuickTest, you should have a good idea of how the framework works and how to run a test.
There's a few parts to creating a test. Actions that can be performed on a page(think your page objects if you are
familiar with the traditional page object
model) which we call NavOps, locator strategies to find UI elements (Resource Locator),
and the test layer.

You can find an example which shows practical use of the various components of the
framework [here](src/test/java/example/ExerciseOneTestCase.java). This test is a good example of how to use the
RFF to create a test. We recommend to use the examples as a template to create a new test, so you can start to get
familiar with the concepts and structure of the framework. Depending on your on programming familiarity,
you may want to start more simply. For those who aren't as comfortable with Java or programming, we recommend starting
with taking an existing example, and adding in a new method to one of the navOps classes. Feel free to borrow heavily
from the existing code. After you've done that, try calling that method in the test layer.
Practice by adding a few methods to navOps, using them in your test case,
then adding an assertion. Run your new test case on the example project and see the results.

When you feel comfortable with that, it's time to create your own project and test your own application.

### Creating Your Own Test

Up until now, we've been looking at examples and learning from things that have already been created. Hopefully you were
able to add some of your own to
it in order to get a feel for how the framework works. Now let's walk through creating your own test from end to end. If
you are wanting to start with an appium test
you'll need to adjust your appconfig.properties files to work on mobile. You'll also need to follow the [appium setup
guide](https://appium.io/docs/en/latest/quickstart/) for Android and/or iOS. If you've been testing
using Appium previously, you might already have this setup. If you are starting with a web test, you can continue on
with the setup you've already done.

- Create a new test class
    - You may want to make a template of this class in the future. There's a few common elements you may want to repeat.
    - Name your test class something that makes sense for what you are testing. For example, if you are testing the
      login
      page, you might name your test class LoginTest. Try grouping your tests in packages that make sense and organized
      by features.
- Decide on a test case
    - What are you testing? What is the expected outcome? What are the steps to get there? How can I quantify the
      outcome?
    - We recommend starting with login tests, as they are generally simple and can be used to test the basic
      functionality
      of your application.
- Create your test method
    - Name your test method something that makes sense for what you are testing. For example, if you are testing the
      login
      page, you might name your test method loginTest.
    - In your test method, you will want to call the methods in your NavOps class to set up your test state, and then
      call your assertion to verify the outcome.
    - There's many ways to go about the individual steps, but we'll share the way that has worked for us. You might have
      a different experience with a different IDE but the ideas
      should be the same. We recommend writing the navOps as if it exits. For example, your first step might be writing
      am.navOps.login.enterUsername("username");
      even though it doesn't exist yet. Using refactor in IntelliJ, you can then create the method in the NavOps class.
    - If you are using IntelliJ, you can right-click on the method name and select 'Refactor' -> 'Extract' -> 'Method'.
      This will create a new method in the NavOps class with the name you selected. You can then fill in the method
      with the code you want to run.
        - If you are using another IDE, you can create the method manually in the NavOps class.
    - To create your method we recommend setting a breakpoint and having your code run to the breakpoint. This will
      allow you
      to test the code you are writing in the evaluation window. If it works, you can break your code into a few navOps
      methods.
      If it doesn't work, you can make changes and try again.
    - In your navOps methods you will want to use the driverWrapper to interact with the application. For example, if
      you
      are testing a login page, you might want to use the driverWrapper to enter a username and password, and then click
      the login button.
    - am.driverWrapper contains all the methods you need to interact with the application. You can find the full list of
      methods in the `DriverWrapper` class.
- Create your assertion
  - 

### Creating a Project

Hopefully at this point

## Running Your Tests

Tests can be run directly from the IDE, or they can be run from the gradle wrapper.
Running from the IDE is easier, while running from the gradle wrapper is more flexible and can be run from the command
line or put on a schedule, or in a CI/CD pipeline.

### Using an IDE:

If you are using IntelliJ, you can
run your tests by either right-clicking on a package/file and selecting 'Run' or 'Debug' or by clicking on the green
arrow next to the test class or method. If you are using another IDE, you can run the tests in a similar way.

### Using the gradle wrapper:

Running the tests is simple. All tests should be kept in a package in the ‘test’ directory of the project so your file
structure might look something like this:

```
Project package
│
└───src
    └───test
        └───java
            └───automationtest
                └───smokesuite
                        │   SmokeTest1
                        │   SmokeTest2
                        │   SmokeTest3
                └───orderflow
                        │   OrderFlowTest1
                        │   OrderFlowTest2
                └───userprofile
                        │   UserProfileTest1
                        │   UserProfileTest2
```

In the above example there are 7 tests in total in the 'test' package, SmokeTest1, SmokeTest2, and SmokeTest3 are in
the 'smokesuite' package, and there are 2 more test packages 'orderflow', and 'userprofile' that each contain 2 more
tests. Splitting test groups into packages
is a great way to group and organize your tests, and there is no limit to the number of packages that can be placed in
your 'tests' directory.
We can trigger all tests to execute by simply using the gradlewrapper command in the terminal to trigger everything in
the 'test' package. So to run all 7 tests in the 'test' package we just need to open a terminal in the project directory
and
run the following command since even though there are sub packages, they are all in the main 'test' directory.
./gradlew test
There are several ways to filter which tests you would like to run. If you just want to run all the tests in a single
package then you simply need add the —tests parameter and the name of the package you want to run. For example
OrderFlowTest1 and OrderFlowTest2 as well as any other tests that are in the 'orderflow' package can be executed by
typing the
following the command in the terminal.
./gradlew test —tests 'orderflow.*'
There are several other ways to filter test runs including by individual test class and even by individual methods,
there are also several other parameters that can be passed to your gradle run such as running in parallel and ignoring
failures. More information on running gradle tests for JVM projects can be
found [here]https://docs.gradle.org/current/userguide/java_testing.html).

## Automation Test Suite Structure and Modularity

As mentioned previously, this repository is meant to serve as a template for your test suite and demonstrates the best
practices
that have worked for our team. You are free to take this structure
exactly as shown here, add your own NavigationOperations, ResourceLocators, and test data and be writing tests in no
time. You are also free to
change the structure to fit your needs. Note the language here is intentional, there's not an ExtentReports or Appium
layer, it's the Report layer and the Framework Layer.
This is because the framework is designed to be modular and flexible, to swap out ExtentReports and use another
reporting tool, all you need to do is implement the reporting interface.
The same goes for Appium, it can be swapped out as well. In fact, Selenium is already implemented in the framework,
which is how you can reuse mobile code for your web tests.
Our recommended structure is as follows and can be seen in the example package:

- Configuration Layer
    - In this layer, your configurations are read in, parsed, and passed into the framework.
    - The `AutomationConfigProperties` class holds all of your configurations and can be accessed
      in the `am.config`object
        - If the value is a named value supported by the framework, you can access it
          by `am.config.supportedFrameworkConfigName`
        - There are also various supported values which can be retrieved from the framework without being setup as a
          named value in the framework. These values are called UndefinedConfig
            - UndefinedConfig can be set by giving a value in the config files a specific string preceding the value.
              The framework will then store your value using the rest of your string as a key. Supported prefixes are
              EXTRA_CAPABILITY, EXTRA_SYSTEM, SAUCE_CAPABILITY,
              SAUCE_OPTION, and SECRET_VAR.
            - For example, if I want to set an extra capability for my test, I can set it in the config file as
              EXTRA_CAPABILITY_MY_VAR_NAME=myValue.
            - You can find your variables in the `am.config.extraCapabilities` ArrayList object.
    - The `TestDataManager` class is used to store any data you need to run your test cases.
      Data can be entered in the class, or read in from a file, database, or other source. This data can be
      used to set up your test state, to verify data, or to clean up after your tests.
        - In `TestDataManager` class you can set up different values for different environments or setup all your data
          for one environment.
          We recommend creating an instance of `TestDataManager` in a base class like `TestMain` and passing in the
          environment you want to use.
          You may consider setting the environment in your config files if you need to do so for a CI/CD setup.

- Test Layer
    - This is where your test cases live. This is where you will set up your test state, execute your test case, and
      assert the results
    - See the [test layer](#test-layer) section for more information

- Behavior Layer
    - This layer is where you will find the NavigationOperations and UserOperations classes. These classes are used to
      interact with the application under test.
        - NavigationOperations are used to navigate to different pages in the application, and UserOperations are used
          to preform general actions not specific to the application (like taking a screenshot, or getting the current
          time)
          or a collection of actions that are used across multiple pages. Login and add to cart are examples of
          UserOperations. We will likely have one test which has all the steps for something like log in, and then for
          other tests who's
          main focus is not logging in, we will call the login UserOperation to log in.
        - See the [Navigation Operations (NavOps)](#navigation-operations-navops)
          and [User Operations (UserOps)](#user-operations-userops) sections for more information
        - Can be accessed by am.navOps and am.userOps respectively

- Reporting Layer
    - This layer is responsible for reporting the results of your tests. Currently, ExtentReports is our preferred
      reporting system,
      but you can easily swap it out for another reporting system by implementing the `Reporter` interface.
    - TestCoverageList - Often times it can be a pain digging through the full report to see exactly what tests run.
      If you use `AssertionCategories` in your assertions, you will see an easy-to-read report each time you run your
      tests showing what tests were run.
      This is mainly a programmer helper tool, but you are free to use it as needed.
    - To actively log data to the report, you can use the `am.reporter.logTest` method.
    - If you use an assertion, the data will be logged to the report automatically.

- Framework Layer
    - This is the engine that makes this template go. For years, everytime we came across a new problem or a new way we
      needed to test, we added a new feature to the framework, integrated a new library to solve it,
      or created a new process to streamline development. Now, we hope you can benefit from this automation toolbox.
        - See the [Features and integrations](#features-and-integrations) section for more on features you can expect,
          and check out the [framework repo](https://github.com/BottleRocketStudios/rocket-fuel-framework/) for more on
          the framework
    - DriverWrapper aims to provide a consistent interface to the automation methods that your setup is using. For
      example, if you are running Appium tests, driverWrapper provides all the methods from appium that you would
      normally use. For more information see the [Driver Wrapper](#driver-wrapper) section
    - Many of the concepts in this project are powered by the framework. Logging, exception handling, reading in data
      from various sources,
      running shell commands, reporting, handling configurations, managing locator strategies, running on sauce labs and
      more are all handled by the framework.

### Project Structure

Given this project is meant to serve as a template, you are free to structure your project however you like.
This is what we've found works well for us:

```
src
│
└───main
    └───resources (User inputted configs)
        │   appconfig.properties
        │   appconfig_android.properties
        │   appconfig_ios.properties
│
└───test
    └───java
        └───automationtestinstance (Initialze and manage test state)
            │   AutomationTestInitializer
            │   AutomationTestManager
        │
        └───automationtests
            │
            └───assertions (Making assertions and holding data for assertions)
                │   AssertionLibrary
                │   AssertionPayload
                │   AssertionCategories
            │
            └───testingautomationtests (Help to make sure template and framework work as intended) 
                │   QuickTest
                │   ResourceLocatorExamples
                │   TestSauceLaunch
            |
            |
            └───yourtestpackage (We )
                │   DeviceAutomationComponents
                │   ResourceLocator
                │   TestDataManager    
        │
        └───config (Set up data needed for your tests and the framework)
            │   DeviceAutomationComponents
            │   ResourceLocator
            │   TestDataManager
        │
        └───domod (Data Object Model)
            │   URLs
            │   User
        │
        └───example (Example code for how your test suite could work)
            │   AFullExampleProject
        │
        └───operations (Behaviors on pages and shortcut gropus of behaviors)
            │   TestInitializerListener
            │   UserOperations
            │   PlatformUserOps
            │
            └───navops
                │   NavigationOperations
                │   PageNavOps
                │   PlatformSpecificNavOps
        │
        └───retryutils (Retry on failure logic)
            │   RetryAnalyzer
        │
        └───testmain (Base layer for tests, feel free to add more layers)
            │   TestMain
        │
        └───utilities (Generate random data)
            │   UserUtilities
```

### Test Layer

This layer uses navigation operations and user operations to set up the
desired test state of the application so some an expected state can be asserted.
To say this another way, this is the logic that says which operations should be
preformed and in what order, so that we can set up, and then execute our test case.

A few rules we subscribe to:

- Most tests will follow this format
    - First do any prep work you need. Calls to APIs, database, doing calculations, reading from a file all go here.
    - Begin setting up your test state. Navigate to the page you need, enter the data, add stuff to cart, etc.
    - Decide what you want to test and what a successful test means. You want something objective that you can measure.
    - Preform your test case
        - Get the data you need for your test and determine if it was successful
        - Use the assertion you want and pass it an AssertionPayload with the data it needs for your test case
    - Clean up
        - Do what you reasonably can to revert your state back to how it was before the test
- driverWrapper should not be allowed to be used in test layer. Instead, you should be calling user and nav operations
    - This promotes usability, scalability, and maintenance
- All assertions should be this layer not in the user/nav ops (aka page objects)
    - This helps with separation of concerns, as tests are only worried about testing and user/nav ops are focused on
      behaviors. It also helps in transparency in what is
      tested. [See Martin Fowler's take on the matter](https://martinfowler.com/bliki/PageObject.html)
- Think carefully about test scope and assertions
    - We recommend each test method to be targeted to a specific feature or functionality.
    - If you are writing a test for login, you should only be testing login, not login and then add to cart. This will
      help with test maintenance and debugging
    - However, multiple assertions are fine, and encouraged. If you are testing a login, you might want to assert that
      the user is logged in, and that the users name is displayed on the login screen. This is fine, as long as the
      assertions are related to the same feature or functionality
        - Not every test case needs an assertion. If you are logging in, it's reasonable to assume that the submit
          button is working.
        - If it's something you want to make sure you call out explicitly, you can log it as info to the
          report `am.reporter.logTest(Status.INFO, "Your Data");` or as a passed test
          case `am.reporter(Status.PASS, "Your passing test data");`
        - Of course, you can also use an assertion with the value of true in the payload, and it will report the same as
          other test cases. Note you might want to turn off the screenshot for this.

### Driver Wrapper

Connection to the automation methods that your setup is using. For example,
if you are running Appium tests, driverWrapper provides all the methods from
appium that you would normally use.

Having a wrapper allows us to perform actions between the testing suite and framework,
allows us to maintain a consistent interface, and protect your test suite against changes in the underlying
automation testing protocol you are using.

### Navigation Operations (NavOps)

NavigationOperations
You can think of NavOps as the series of actions and navigation (which we call behaviors)
that can be performed on a specific page or component on your application.
NavigationOperations (NavOps for short) allows you to organize your application
into a Page Object Model (POM) style collection of objects.
You can organize your pages by screen, functionality, or anything else you see fit, although organizing by screen is our
preferred method. While POM is the current standard for managing behaviors,
we have found that it can be limiting in terms of code reuse and scalability. Instead, we enhanced the POM model to
allow for central greedy initialization of each navOps object (to remove the need to initialize each page object),
more easily support multiple platforms simultaneously, and to better share common code across parts of the application.

Let’s look at an example to help explain this concept. Imagine that you have a store application with just a few pages.

The Landing page allows you login or navigate to Create Account
Create Account page allows you to create account
Inventory page allows you to view and interact with inventory

Some functionality may not be specific to any one page but instead is a small component tied to many screens.
Traditionally, you might have made a footer page object, or a nav bar page object, but with the Rocket Fuel Framework,
you have another option.
As an example, let's say your application has a menu bar that appears on most screens or a common pop up window that can
appear in various locations,
the base NavigationOperations class might be the best place to hold that logic. We've found this helps cut down on a lot
of boilerplate creating small component page objects
can create. An example of the base NavigationOperations class can be
found [here](src/test/java/example/operations/navops/NavigationOperations.java)

Any logic that is specific to a page should be kept with it's corresponding navOps for organization.
For example, behaviors for the Login page, would live in LoginNavOps.
Some behaviors in that class might be
enterUsername, enterPassword, and submitLogin that will allow the test to log a user in. An example of a Login page can
be found [here](src/test/java/example/operations/navops/NavOpsLogIn.java)

Notice in the NavigationOperations class that the Login and Inventory pages have member objects. This and the
TestInitializerListener
allow for the NavigationOperations to intialze all the page objects at once by calling each page object's init method.
With this approach you only need to define your page object classes, add them to your NavigationOperations class and
you’re done. When the AutomationTestManager, which holds your NavigationOperations object, is initialized, it
initializes all your page
objects and allows you to access them all from a single place.

So entering your username into the username field of the Login page might look something like this
am.navOps.login.enterUsername(“username”);

Rocket Fuel Framework is designed to easily run your tests on multiple platforms and operating systems and enable code
reuse between them.
So if your test needs to interact with your device in a different way due to differences in
platform you can simply extend your NavigationOperation class to be platform specific to Android, iOS, and Web. Examples
of NavigationOperationsAndroid, NavigationOperationsIos, and NavigationOperationsWeb can be
found [here](src/test/java/example/operations/navops).
With this approach you can override your login.enterUsername() method and use it exactly the same way in your test
case, regardless of which platform is running. This allows for your test layer to be exactly the same whether you are
making a
test for iOS, Android or Web, so long as the business logic is sufficiently similar. For tests that have different
business logic, there are various ways to handle that as well outside subclassing the related NavigationOperations
class.

### User Operations (UserOps)

### Assertion Payload

You can find the assertion payload [here](src/test/java/example/assertions/AssertionPayload.java). The
AssertionPayload's job is to hold
all the data that is needed to make assertions in the test. This data includes, but is not limited to, if the test past
or failed, the message to display on the report,
if a screenshot should be taken, and an AssertionCategory/description. The AssertionCategory/description is optional and
if set, will create a separate report for
that shows an easy-to-read summary of what test was run each time you run tests in the framework.

### AssertionLibrary and Making an Assertion

AssertionPayload holds all the data needed for the assertion, but when you are ready to make the assertion, you will
want to use the AssertionLibrary class. You can do that through am.assertion.generalAssertion or another assertion that
you've created.
Perhaps you want to flag a test as a warning, or you want to add your own custom assertion. You can do that by creating
a new assertion in the AssertionLibrary class.
Whatever assertion you use, you will pass in either a new AssertionPayload or one that's been loaded up with data
earlier in the test,
and the AssertionLibrary will take care of the rest. It will work out of the box, but if you want to customize it, you
can do that as well.
You can see a full example [here](src/test/java/example/ExerciseOneTestCase.java)

### Resource Locator and Resource Locator Bundle(RLB)

This is the home for various locator strategies for elements on a page. RLB is a bundle of resources that allow you to
find
what you need on screen. While, ResourceLocator holds RLBs for all of your elements across each page/component in your
application.
We prefer keeping them all in one file, to promote code reuse and make maintenance easier(all locators can be updated in
one file),
however you can keep them in your NavOps if you prefer.
We prefer keeping all the RLBs together as it avoids duplication of locators across different pages, and allows them to
be updated in one file.
They can still be organized in the file, so you can easily find what you are looking for. However, you can keep them in
your NavOps if you prefer.
The idea behind our implementation of this concept is that we want to make it as easy as
possible to capture the data you need to find your elements, while also totally separating the element onscreen from the
object
representing it in Java. This has the benefit of having an object in the system that lives outside its ability to find
an element. For example, it can hold a locator for different platforms, meaning when I write my tests I use the same
object no matter what platform. This also makes it easier to use more complex logic to find an element, have a hook for
reporting, and more. We keep them all in the same file as elements tend to bleed across pages (footers, nav bars, call
to actions,
etc.), meaning one element can also be useful across different contexts.

RLBs, which take in locator strategies on multiple platforms,
different locator strategies for the same element, or both. However, for multiple locator strategies for the same
element/platform especially when complicated, we recommend using UIElementLocator instead. It's more complicated
to set up but has a lot more features. RLB is faster for most cases and easier to use.

RLBs are primarily used to have one way to locate an element per platform, and for that data to be
created in one line of code. The data is stored at runtime and then given the platform you are running on,
the data related to that platform is retrieved. A few constructors are provided to you from the framework,
however if you want a different combination of platforms, feel free to extend the ResourceLocatorBundle class yourself!
Alternatively, you can just pass in a null value for the platforms you don't want to use, and the framework will ignore
them.

## Features and Integrations

Each time we came across a new problem or a new way we needed to test, we added a new feature to the framework,
integrated a new library to solve it,
or created a new process to streamline development. Now while you won't find our device rotating machine (we did
actually create one, and no it's not exactly practical)
in the framework, you will find a lot of other features that we have found to be useful in our automation projects.

Tools that have been used in the framework successfully:

- Selenium
- Appium
- TestNG
- JUnit
- Extent Reports
- Gradle Reports
- Sauce Labs
- MITM Proxy
- GitHub Actions (Your project can be setup to use GHA or others to run your tests on a schedule or on a PR merge)
- and more!

Features

- Familiar POM like structure, but with unique benefits for code reuse and scalability
- Locator Strategies are easily set and changed in one place. Also, supports multiple strategies for the same element on
  one or more platforms
- Easily switch between different platforms and devices
- Run locally or on Sauce Labs, or add your own run target easily
- Modular, use only the pieces that you need, ignore the rest
- Easily make HTTP requests, receive requests and integrate with APIs
- Extensible, add your own features and integrations easily by extending or subclassing
- Configurable, set what features that you want to use, screenshots, logging, run target, devices, and more!
- Easily add in calls to APIs
- Connect to databases to verify data, setup data for tests, to clean up after tests, or to run tests that don't involve
  the UI
- Monitor network traffic. Easily see what calls are being made by your app, and verify that the correct calls are being
  made. Or get data from the network to use in your tests
- Easily add in shell commands to run on your system to integrate with other tools or to run commands in your test
  environment
- Read in data from properties files, database, excel, or add your own data source
- Select from multiple reports or add your own
- Customize what is logged, or turn off logging altogether
- MockServer (experimental), easily mock out calls to APIs to test edge cases or to test when the API is not available
- Wrapper protected: Selenium, Appium, and more are wrapped in a way that makes it easy to add in new features or to
  change behaviors. No more panicking because a feature is removed, or you need to alter the behavior of a method

While there will be efforts to maintain the accuracy of this list,
the true feature list will always be in the code base itself. So if you are curious why not reach out? Or better yet
dive in yourself and see!

## Prerequisites

TLDR:

- Java 15 or higher
- IntelliJ IDEA (recommended)
- Mac, Linux, or Windows (Mostly used on Mac but shouldn't be an issues on other systems)
- One or more of the following depending on what you are testing:
    - Drivers (web)
    - Appium (local mobile)
        - Simulators
        - Real devices
    - Sauce Labs/other (remote execution)
- Gradle command line (optional)
- Github account and agree to the CLA (if you wish to contribute)
- A desire to learn and grow while also making your life easier and writing amazing automation tests

While we have run this framework on many different systems over the years, due to being a previously internal tool,
there
has been limited experience using it operating systems other than Mac. That's not to say it can't work, but there could
potentially
be issues
that we haven't seen before. If you are using something other than Mac, please reach out to us if you run into any
issues.

Outside of that, you will need Java on your system. Newer versions of Java are recommended, but the framework should
work on anything 15 or higher.

We highly recommend you use IntelliJ IDEA as your IDE, as it is the IDE that we have used to develop the framework,
and in our opinion it is an easy-to-use IDE with industry leading features. Of course, you are free to use any IDE that
you are comfortable with. I will say however, I have challenged everyone I've gotten set up to use IntelliJ, and they
have all come back to me
saying they love it. I've never had anyone change back to their old IDE after trying it. /end unsolicited plug for
IntelliJ

You will also need a way to run your tests. If you are testing web, you can run your tests locally and will just need to
acquire the corresponding driver for the browser you are using, assuming it is not included with the browser itself.
Due to the number of drivers, the number of systems to support, and the overhead
of updating it, you will likely need to provide your own. Perhaps in time we will have the drivers set in gradle but
for now, you will need to provide your own, or feel free to update the gradle and submit a PR to help others and
yourself :).

If you are testing mobile, you won't get very far if you can't run your code on anything. If running locally, you will
basically need to follow all the appium setup steps
[here](https://appium.io/docs/en/2.0/quickstart/) to get your simulators or real devices set up, as well as
appium and all of its requirements. We have done this plenty of times so again feel free to reach out if you get stuck.
Most of it's straightforward but there are a few gotchas that we can help with. Of course there's also a large Appium
community out there as well that might be able to help you quicker, so feel free to reach out there as well.
IOS is particularly tricky and often requires a little bit of black magic to work properly. It's much easier
to get running on a system like SauceLabs or a similar service, but that will require an account and are generally not
free.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull
requests.

## Versioning

Your project using the framework can be versioned however you like, but the framework itself will be versioned
with the following format:

`<major>.<minor>.<patch>`

Where:

- Major: Incremented when there are breaking changes
- Minor: Incremented when new features are added
- Patch: Incremented when bugs are fixed

These are not hard and fast rules, but we do our best. Generally major versions are rare and the only ones that are
allowed to break backwards compatibility.
Minor versions are more common and generally add new features or improve existing ones. Patch versions are the most
common and are generally bug fixes or small improvements.

## License

Apache License
Version 2.0, January 2004
http://www.apache.org/licenses/

## Acknowledgments

Too many to name, but we are forever grateful to all of those of contributed, reviewed, given ideas, provided
inspiration, tested, troubleshooted, helped with never ending PRs, documented, and more.

Thank you, this would not have been possible without you!



