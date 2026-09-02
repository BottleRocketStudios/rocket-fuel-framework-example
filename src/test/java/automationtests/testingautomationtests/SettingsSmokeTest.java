package automationtests.testingautomationtests;

import automationtests.assertions.AssertionCategories;
import automationtests.assertions.AssertionPayload;
import config.ResourceLocator;
import org.testng.annotations.Test;
import testmain.TestMain;

/**
 * The mobile counterpart to {@link QuickTest} - a heartbeat check that the mobile half of the stack
 * is wired correctly: config loading, driver session creation, locator resolution, screenshots and
 * reporting.
 * <p>
 * This is deliberately <em>one</em> test that runs on both iOS and Android rather than a pair of
 * per-platform tests. Nothing in the method body is platform specific: the only difference between
 * the two runs is which locator {@link ResourceLocator#SETTINGS_ROOT_MARKER} resolves to, which the
 * framework decides at runtime from the platform under test. That reuse is the reason to pick this
 * framework over a per-platform suite, so it is worth demonstrating rather than describing.
 * <p>
 * It drives each platform's stock Settings app, so it needs no app build, no IPA or APK and no code
 * signing - which keeps it usable as a sanity check on any machine.
 * <p>
 * Prerequisites:
 * <ol>
 *     <li>A booted simulator or emulator matching the UDID/DEVICE_NAME in
 *         appconfig_ios.properties / appconfig_android.properties
 *         (<code>xcrun simctl list devices available</code>, or <code>adb devices</code>)</li>
 *     <li>An Appium <b>2.x</b> server on the LOCAL_URL from that same file, started with
 *         DEVELOPER_DIR (iOS) and ANDROID_HOME (Android) set, with the xcuitest and/or
 *         uiautomator2 drivers installed. Appium 3.x will NOT work while this project is on
 *         java-client 8.1.1, which still uses APIs removed in java-client 9.</li>
 * </ol>
 * Run with:
 * <pre>
 * ./gradlew test --tests 'automationtests.testingautomationtests.SettingsSmokeTest' -Poperatingsystem=ios
 * ./gradlew test --tests 'automationtests.testingautomationtests.SettingsSmokeTest' -Poperatingsystem=android
 * </pre>
 */
public class SettingsSmokeTest extends TestMain {

    @Test(description = "The stock Settings app launches and its root screen is present")
    public void settingsRootScreenIsPresent() {
        am.reporter.addInfoToReport("Driving the stock Settings app on <b>" + am.config.platformName
                + "</b> device " + am.config.deviceName);
        am.userOp.takeScreenshot("settings_launch");

        boolean rootScreenPresent = am.navOp.verifyContentIsPresentBy(ResourceLocator.SETTINGS_ROOT_MARKER);

        am.assertions.generalAssertion(new AssertionPayload(
                rootScreenPresent,
                "Settings launched and its root screen is present",
                "Settings did not present its root screen, so the mobile stack is not wired correctly",
                "", "",
                AssertionCategories.MobileSmoke,
                "Verify the Settings root screen is present"));
    }
}
