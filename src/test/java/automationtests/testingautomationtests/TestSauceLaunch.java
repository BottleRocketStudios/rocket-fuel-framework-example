package automationtests.testingautomationtests;

import com.bottlerocket.config.AutomationConfigProperties;
import com.bottlerocket.config.AutomationConfigPropertiesLoader;
import com.bottlerocket.config.UndefinedConfig;
import com.bottlerocket.webdriverwrapper.WebDriverWrapper;
import com.bottlerocket.webdriverwrapper.WebDriverWrapperGeneric;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ford.arnett on 3/22/23
 */
public class TestSauceLaunch {

    @Test
    public void testRunFlutterOnSauceNotFromProperties() throws Exception {
        AutomationConfigPropertiesLoader loader = new AutomationConfigPropertiesLoader();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        AutomationConfigProperties flutterConfig = loader.loadAutomationConfigurations(capabilities);


        //kludge until fixed
        flutterConfig.projectName = "testFlutterAppOnSauceLabsFromConfig";
        flutterConfig.appiumVersion = "1.22.2";
        flutterConfig.globalWait = 15;
        flutterConfig.remote = true;
        flutterConfig.remoteType = "sauce";


        flutterConfig.platformName = "android";
        flutterConfig.automationName = "flutter";
        flutterConfig.buildNumber = "1";
        //make sure this is gotten from files
        flutterConfig.capabilities.setCapability("app", "storage:filename=app-release-455.apk");

        String sauceUsername = "Leo Booth";
        flutterConfig.sauceOptions = setSauceOptionsForMobileApp(flutterConfig, sauceUsername);



        WebDriverWrapper driverWrapper;

        driverWrapper = createWebDriverWrapperOnSauceLabs(flutterConfig);
        Assert.assertNotNull(driverWrapper.getPageSource(), "page source was null, something likely went wrong");

    }

    @Test
    public void testRunFlutterOnSauceUsingPropertiesFile() throws Exception {
        AutomationConfigPropertiesLoader loader = new AutomationConfigPropertiesLoader();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        AutomationConfigProperties flutterConfig = loader.loadAutomationConfigurations(capabilities);


        //String sauceUsername = "Michelle";
        //flutterConfig.sauceOptions = setSauceOptionsForMobileApp(flutterConfig, sauceUsername);

        WebDriverWrapper driverWrapper;

        //When remote this needs to be gotten rid of or never set. This should happen at framework level
        //flutterConfig.capabilities.caps.remove("app");

        driverWrapper = createWebDriverWrapperOnSauceLabs(flutterConfig);
        Assert.assertNotNull(driverWrapper.getWindow(), "page source was null, something likely went wrong");

    }

    public static List<UndefinedConfig> setSauceOptionsForMobileApp(AutomationConfigProperties config, String sauceUsername) {
        List<UndefinedConfig> sauceOptions = new ArrayList<>();

        sauceOptions.add(UndefinedConfig.getSauceCapability("\"platformName\",           \"" + config.platformName       + "\"", ","));
        sauceOptions.add(UndefinedConfig.getSauceCapability("\"appium:platformVersion\", \"" + config.platformVersion    + "\"", ","));
        sauceOptions.add(UndefinedConfig.getSauceCapability("\"appium:deviceName\",      \"" + config.deviceName         + "\"", ","));
        sauceOptions.add(UndefinedConfig.getSauceCapability("\"appium:automationName\",  \"" + config.automationName     + "\"", ","));
        sauceOptions.add(UndefinedConfig.getSauceCapability("\"appium:app\",             \"" + config.appPath            + "\"", ","));

        sauceOptions.add(UndefinedConfig.getSauceOption("\"appiumVersion\",              \"" + config.appiumVersion      + "\"", ","));
        sauceOptions.add(UndefinedConfig.getSauceOption("\"username\",                   \"" + sauceUsername             + "\"", ","));
        sauceOptions.add(UndefinedConfig.getSauceOption("\"name\",                       \"" + config.projectName        + "\"", ","));
        sauceOptions.add(UndefinedConfig.getSauceOption("\"build\",                      \"" + config.buildNumber        + "\"", ","));

        return sauceOptions;
    }

    private WebDriverWrapper createWebDriverWrapperOnSauceLabs(AutomationConfigProperties config) {
        WebDriverWrapper driver;

        try {
            driver = new WebDriverWrapperGeneric(config);
        } catch (Exception e) {
            throw new WebDriverException("Unable to create a SauceLabs driver using the configuration provided.", e);
        }

        return driver;
    }

}
