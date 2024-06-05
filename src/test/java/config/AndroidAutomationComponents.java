package config;

import automationtests.assertions.AssertionLibrary;
import automationtests.assertions.AssertionLibraryAndroid;
import com.bottlerocket.config.*;
import com.bottlerocket.utils.InputUtils;
import com.bottlerocket.utils.InputUtilsAndroid;
import com.bottlerocket.webdriverwrapper.*;
import operations.UserOperations;
import operations.UserOperationsAndroid;
import operations.navops.NavigationOperations;
import operations.navops.NavigationOperationsAndroid;

/**
 * Implementation of the strategy pattern, contains components of the system
 *
 * Created by ford.arnett on 10/29/15.
 */
public class AndroidAutomationComponents implements DeviceAutomationComponents {

    @Override
    public NavigationOperations getNavigationOperations() {
        return new NavigationOperationsAndroid();
    }

    @Override
    public UserOperations getUserOperations() {
        return new UserOperationsAndroid();
    }

    @Override
    public InputUtils createInputUtils(AppiumDriverWrapper driverWrapper) {
        return new InputUtilsAndroid(driverWrapper);
    }

    @Override
    public void initResourceLocator() {
        ResourceLocatorBundle.runtimePlatform = "android";

        return;
    }

    @Override
    public AssertionLibrary getAssertions() {return new AssertionLibraryAndroid();}
}
