package automationtests.testingautomationtests;

import automationtests.assertions.AssertionCategories;
import automationtests.assertions.AssertionPayload;
import config.ResourceLocator;
import org.testng.annotations.Test;
import testmain.TestMain;

/**
 * The iOS counterpart to {@link QuickTest} - a heartbeat check that the iOS half of the stack is
 * wired correctly: config loading, XCUITest session creation, locator resolution, screenshots and
 * reporting.
 * <p>
 * It drives the pre-installed Settings app (bundleId com.apple.Preferences), so it needs no app
 * build, no IPA and no code signing. That keeps it usable as a sanity check on any machine, the
 * same way QuickTest is for web.
 * <p>
 * Prerequisites:
 * <ol>
 *     <li>A booted simulator matching UDID/DEVICE_NAME in appconfig_ios.properties
 *         (<code>xcrun simctl list devices available</code>)</li>
 *     <li>An Appium 2.x server on the LOCAL_URL from that same file, with the xcuitest driver
 *         installed. Appium 3.x will NOT work while the project is on java-client 8.1.1.</li>
 * </ol>
 * Run with:
 * <pre>
 * ./gradlew test --tests 'automationtests.testingautomationtests.IosSettingsSmokeTest' -Poperatingsystem=ios
 * </pre>
 */
public class IosSettingsSmokeTest extends TestMain {

    @Test(description = "Settings launches under XCUITest and its General row is present")
    public void settingsShowsGeneralRow() {
        am.reporter.addInfoToReport("Driving bundleId <b>" + am.config.bundleId + "</b> on "
                + am.config.deviceName + " (iOS " + am.config.platformVersion + ")");
        am.userOp.takeScreenshot("ios_settings_launch");

        // ResourceLocator.IOS_SETTINGS_GENERAL_ROW is a ResourceLocatorBundle, so the same
        // constant would resolve to an Android locator on an Android run.
        boolean generalRowPresent = am.navOp.verifyContentIsPresentBy(ResourceLocator.IOS_SETTINGS_GENERAL_ROW);

        am.assertions.generalAssertion(new AssertionPayload(
                generalRowPresent,
                "Settings launched under XCUITest and the General row is present",
                "Settings did not present its General row, so the iOS stack is not wired correctly",
                "", "",
                AssertionCategories.IosSmoke,
                "Verify the Settings General row is present"));
    }
}
