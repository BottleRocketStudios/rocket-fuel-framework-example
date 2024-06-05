package automationtests.assertions;

import automationtestinstance.AutomationTestManager;
import com.aventstack.extentreports.Status;
import com.bottlerocket.errorhandling.AssertionLibraryException;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * Created by ford.arnett on 4/11/16.
 */
public abstract class AssertionLibrary {
    AutomationTestManager testManager;
    public void init(AutomationTestManager testManager) {
        this.testManager = testManager;
    }

    public void generalAssertion(AssertionPayload payload) {
        if(payload.category != null) {
            testManager.reporter.addToTestCoverageList(payload.category.toString(), payload.testDescription);
        }
        if(payload.assertSuccessful) {
            testManager.reporter.logTest(Status.PASS, payload.successMessage);
            if(payload.takeScreenshotSuccess) {
                String screenshotName = !payload.screenShotSuccessFilename.equals("") ? payload.screenShotSuccessFilename : "assertion_success_" + System.currentTimeMillis();
                testManager.userOp.takeScreenshot(screenshotName);
            }
        } else {
            //Let testNG know we have a fail during this test.
            AssertionLibraryException assertionLibraryException = new AssertionLibraryException("One or more tests in this group has failed");
            assertionLibraryException.setStackTrace(new StackTraceElement[0]);
            Reporter.getCurrentTestResult().setThrowable(assertionLibraryException);
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);

            testManager.reporter.logTest(Status.FAIL, new Throwable(payload.failureMessage));
            if(payload.takeScreenshotFailure) {
                String screenshotName = !payload.screenShotFailFilename.equals("") ? payload.screenShotFailFilename : "assertion_failed_" +  + System.currentTimeMillis();
                testManager.userOp.takeScreenshot(screenshotName);
            }
        }
    }

    public void generalAssertionWarning(AssertionPayload payload) {
        if(payload.category != null) {
            testManager.reporter.addToTestCoverageList(payload.category.toString(), payload.testDescription);
        }
        if(payload.assertSuccessful) {
            testManager.reporter.logTest(Status.PASS, payload.successMessage);
            if(payload.takeScreenshotSuccess) {
                String screenshotName = !payload.screenShotSuccessFilename.equals("") ? payload.screenShotSuccessFilename : "assertion_success_" + System.currentTimeMillis();
                testManager.userOp.takeScreenshot(screenshotName);
            }
        } else {
            AssertionLibraryException assertionLibraryException = new AssertionLibraryException("One or more tests in this group has an issue which likely requires a human to look into. " +
                    "We either did not have enough confidence to fail it, or it is an alert to further investigate. ");
            assertionLibraryException.setStackTrace(new StackTraceElement[0]);
            Reporter.getCurrentTestResult().setThrowable(assertionLibraryException);
            Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS_PERCENTAGE_FAILURE);

            testManager.reporter.logTest(Status.WARNING, new Throwable(payload.failureMessage));
            if(payload.takeScreenshotFailure) {
                String screenshotName = !payload.screenShotFailFilename.equals("") ? payload.screenShotFailFilename : "assertion_warning_" +  + System.currentTimeMillis();
                testManager.userOp.takeScreenshot(screenshotName);
            }
        }
    }

}
