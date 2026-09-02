package operations.navops;


import automationtestinstance.AutomationTestManager;
import com.bottlerocket.config.ResourceLocatorBundle;
import operations.TestInitializerListener;


/**
 * Handles behaviors that don't belong to a single screen, but may not need their own navOps, like a nav bar.
 *
 * Created by ford.arnett on 9/3/15.
 */
public abstract class NavigationOperations implements TestInitializerListener {
    AutomationTestManager am;

    //NavOps classes here

    @Override
    public void init(AutomationTestManager am) {
        this.am = am;

        //init nav classes here

    }

    /**
     * Verify an element described by a {@link ResourceLocatorBundle} is present on the current screen.
     * The bundle resolves to the right locator for whichever platform the run is on.
     */
    public boolean verifyContentIsPresentBy(ResourceLocatorBundle rlb) {
        return am.driverWrapper.elementExists(rlb.getBy());
    }

}

