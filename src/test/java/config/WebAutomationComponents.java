package config;

import automationtests.assertions.AssertionLibrary;
import automationtests.assertions.AssertionLibraryWeb;
import com.bottlerocket.config.*;
import com.bottlerocket.utils.InputUtils;
import com.bottlerocket.webdriverwrapper.*;
import operations.UserOperations;
import operations.UserOperationsWeb;
import operations.navops.NavigationOperations;
import operations.navops.NavigationOperationsWeb;

/**
 * Created by ford.arnett on 8/10/18
 */
public class WebAutomationComponents implements DeviceAutomationComponents {

    @Override
    public NavigationOperations getNavigationOperations() {
        return new NavigationOperationsWeb();
    }

    @Override
    public UserOperations getUserOperations() {
        return new UserOperationsWeb();
    }

    @Override
    public void initResourceLocator() {
        ResourceLocatorBundle.runtimePlatform = "web";
    }

    @Override
    public InputUtils createInputUtils(AppiumDriverWrapper driverWrapper) {
        return null;
    }

    @Override
    public AssertionLibrary getAssertions() {
        return new AssertionLibraryWeb();
    }


}
