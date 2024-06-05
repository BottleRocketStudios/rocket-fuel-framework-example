package example.config;

import com.bottlerocket.config.ResourceLocatorBundle;
import com.bottlerocket.utils.InputUtils;
import com.bottlerocket.utils.InputUtilsAndroid;
import com.bottlerocket.webdriverwrapper.AppiumDriverWrapper;
import example.assertions.AssertionLibrary;
import example.assertions.AssertionLibraryAndroid;
import example.operations.UserOperations;
import example.operations.UserOperationsAndroid;
import example.operations.navops.NavigationOperations;
import example.operations.navops.NavigationOperationsAndroid;

/**
 * Implementation of the strategy pattern, contains components of the system
 * <p>
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
    public AssertionLibrary getAssertions() {
        return new AssertionLibraryAndroid();
    }
}
