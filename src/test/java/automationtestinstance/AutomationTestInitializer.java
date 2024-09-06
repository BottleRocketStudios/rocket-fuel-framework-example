package automationtestinstance;

/*
  Created by ford.arnett on 10/6/21
 */

import com.bottlerocket.config.*;
import com.bottlerocket.webdriverwrapper.AppiumDriverWrapperAndroid;
import com.bottlerocket.webdriverwrapper.AppiumDriverWrapperIos;
import com.bottlerocket.webdriverwrapper.WebDriverWrapperGeneric;
import com.bottlerocket.reporters.*;
import com.bottlerocket.utils.*;
import com.bottlerocket.webdriverwrapper.uiElementLocator.*;
import config.*;
import io.appium.java_client.service.local.*;
import org.openqa.selenium.remote.*;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;


/**
 * initializes driverWrapper, reporter, operations, and configs for the test run
 */
public class AutomationTestInitializer {
    public DeviceAutomationComponents deviceAutomationComponents;
    private AutomationConfigProperties config;

    /**
     * Starts up all components of the automation system and gets them ready for use. This must be done before any of the system objects are to be used.
     *
     * @return the newly created WebDriverWrapper
     * @throws Exception if an error occurred
     */
    public AutomationTestManager initializeAutomationSystem() throws Exception {
//        DeviceAutomationComponents device;
        AutomationTestManager am = new AutomationTestManager();
        AutomationConfigPropertiesLoader loader = new AutomationConfigPropertiesLoader();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        config = am.config = loader.loadAutomationConfigurations(capabilities);


        setupFirst(am);
        UIElementLocator.setTestPlatformForCurrentTestRun(config.platformName);
        //Example to set a capability based on parameters used to start the test
//        testDataManager.setEnvCapabilities(capabilities, testDataManager.getCurrentEnvType(), testDataManager.getCurrentLocale());

        if (config.platFormTypeIsAndroid()) {
            initializeAndroidSystem(am);
        } else if (config.platFormTypeIsIos()) {
            initializeIosSystem(am);
        } else if (config.platFormTypeIsWeb()) {
            initializeSeleniumSystem(am);
        } else {
            throw new Error("Operating system not recognized, check config files");
        }

        selectReporter(am);
        am.initializeComponents();

        return am;
    }

    private void initializeAndroidSystem(AutomationTestManager am) throws Exception {
        URL serverAddress;
        if (config.customAppiumInstance) {
            serverAddress = createAppiumSession();
        } else if (config.remote) {
            serverAddress = new URL(config.remoteDriverURL);
        } else {
            serverAddress = new URL(config.localDriverURL);
        }

        am.driverWrapper = new AppiumDriverWrapperAndroid(serverAddress, config, config.globalWait);
    }

    private void initializeIosSystem(AutomationTestManager am) throws Exception {
        URL serverAddress;
        if (config.customAppiumInstance) {
            serverAddress = createAppiumSession();
        } else if (config.remote) {
            serverAddress = new URL(am.config.remoteDriverURL);
        } else {
            serverAddress = new URL(am.config.localDriverURL);
        }

        am.driverWrapper = new AppiumDriverWrapperIos(serverAddress, am.config, config.globalWait);
    }

    private void initializeSeleniumSystem(AutomationTestManager am) throws Exception {
        am.driverWrapper = new WebDriverWrapperGeneric(am.config, am.config.globalWait, am.config.browserName);
    }

    public AutomationTestManager initializeAPITestingSuite() {
        throw new UnsupportedOperationException("do this later");
    }

    private URL createAppiumSession() {
        File logPathDir = new File(config.reportOutputDirectory);
        //noinspection ResultOfMethodCallIgnored
        logPathDir.mkdirs();
        File logPathFile = new File(logPathDir + "/appium_out.txt");
        Logger.log("Appium logs file " + logPathFile.getAbsolutePath());

        AppiumServiceBuilder builder = new AppiumServiceBuilder().usingAnyFreePort().withLogFile(logPathFile);
        AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildService(builder);
        appiumService.start();

        silenceAppiumConsoleLogging(appiumService);

        return appiumService.getUrl();
    }

    /**
     * This will remove the appium logs from System out. From what I can tell this should have been added in a recent update by default but I'm still seeing the appium output in the console.
     * I would love to get rid of this entire method once the default behavior changes.
     * https://github.com/appium/java-client/pull/483
     */
    private void silenceAppiumConsoleLogging(AppiumDriverLocalService appiumService) {
        Field streamField = null;
        Field streamsField = null;
        try {
            streamField = AppiumDriverLocalService.class.getDeclaredField("stream");
            streamField.setAccessible(true);
            streamsField = Class.forName("io.appium.java_client.service.local.ListOutputStream")
                    .getDeclaredField("streams");
            streamsField.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            ((ArrayList<OutputStream>) streamsField.get(streamField.get(appiumService))).clear(); // remove System.out logging
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setup methods that must be run first before other setup methods can take place.
     */
    private void setupFirst(AutomationTestManager testManager) throws Exception {
        DeviceAutomationComponents device;
        //Set iOS or Android here
        if (testManager.config.platFormTypeIsAndroid()) {
            device = new AndroidAutomationComponents();
        } else if (testManager.config.platFormTypeIsIos()) {
            device = new IosAutomationComponents();
        } else if (testManager.config.platFormTypeIsWeb()) {
            device = new WebAutomationComponents();
        } else {
            throw new Exception("Platform not recognized");
        }
        //Set configurations
        setComponentsInManager(device, testManager);
//        testManager.config.loadConfigVariables();

        return;
    }

    public void setComponentsInManager(DeviceAutomationComponents deviceAutomationComponents, AutomationTestManager automationTestManager) {
        this.deviceAutomationComponents = deviceAutomationComponents;
        deviceAutomationComponents.initResourceLocator();
        automationTestManager.navOp = deviceAutomationComponents.getNavigationOperations();
        automationTestManager.userOp = deviceAutomationComponents.getUserOperations();
        automationTestManager.assertions = deviceAutomationComponents.getAssertions();
    }

    /**
     * Select the reporter to be used in the system. Currently there's only one we would want to use, however we can just use an if statement looking at @see       * Select the reporter to be used in the system. Currently there's only one we would want to use, however we can just use an if statement looking at {@link AutomationTestManager#reporter} to support selecting one at runtime. to support selecting one at runtime.
     *
     * @param automationTestManager The test manager to set the reporter of
     */
    private void selectReporter(AutomationTestManager automationTestManager) {
        String fileName = "";
        if (automationTestManager.reporter == null) {
            if (automationTestManager.config.remote == true) {
//            if(automationTestManager.config.reportOutputDirectory.contains("null")) {
//                fileName = System.getProperty("user.dir") + "/extent_reports/" + automationTestManager.config.reportOutputDirectory.substring(4)  + "/report";
                fileName = System.getProperty("user.dir") + "/extent_reports/index";
            } else {
                fileName = automationTestManager.config.reportOutputDirectory + "/report";
            }
            automationTestManager.reporter = new ExtentReporter(fileName);
        }
        Logger.log("Reports are being logged with the Extent reporter at " + fileName + ".html");

        automationTestManager.reporter.initializeReporter(false);
        automationTestManager.driverWrapper.setAutomationReporter(automationTestManager.reporter);
        setSystemInfo(automationTestManager.reporter, automationTestManager.config);
    }


    private static void setSystemInfo(AutomationReporter reporter, AutomationConfigProperties config) {
        if (reporter.getTest() == null) {
            reporter.addSystemInfo("Project Name", config.projectName);
            reporter.addSystemInfo("Device Name", config.deviceName);
            reporter.addSystemInfo("Udid", config.udid);
            reporter.addSystemInfo("Device Version", config.platformVersion);
            reporter.addSystemInfo("Build Number", config.buildNumber);
            reporter.addSystemInfo("Global Wait", String.valueOf(config.globalWait));
        }
    }

}

