package testmain;

import automationtestinstance.AutomationTestInitializer;
import automationtestinstance.AutomationTestManager;
import com.aventstack.extentreports.Status;
import com.bottlerocket.remote.SauceExecutor;
import com.bottlerocket.utils.Logger;
import com.testRailManager.*;
import config.TestDataManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import retryutils.RetryAnalyzer;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;


/**
 * Created by ford.arnett on 10/2/15.
 */
public class TestMain {
    protected AutomationTestManager am;
    String suiteName;
    //True for class, false for method 
    boolean runByClassOrMethod = true;
    //Note: Store run data in TDM that you can pass to your tests(Users, search params, orders, etc.).
    //This makes it easy to manage, switch with params, and manage state.
    boolean allMethodsPassed = false;

    protected String testCaseId;


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        RetryAnalyzer retryAnalyzer = new RetryAnalyzer();
        for (ITestNGMethod method : context.getAllTestMethods()) {
            method.setRetryAnalyzerClass(retryAnalyzer.getClass());
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setupEvery(Method method, Object[] testData) {
        if (!runByClassOrMethod) {
            try {
                AutomationTestInitializer initializer = new AutomationTestInitializer();
                am = initializer.initializeAutomationSystem();
            } catch (Exception e) {
                String message = "***FAILED IN @BEFORE Method*** in **TEST MAIN** Exception == " + e;
                Logger.log(method.getName() + message + " - Exception e is: " + e);
            }
        }

        //sets test name to include data-provider data if available
        startTest(method, testData);
        //adds any Groups and Description to top of report if available
        updateReportedGroupsAndDescription(method);
        SauceExecutor sauceExecutor = new SauceExecutor(am.config.remote);
        sauceExecutor.setJobName(am.driverWrapper, method.getName());
        allMethodsPassed = true;

        Logger.log(method.getName() + " <-- Method Name***");


    }

    /**
     * Run before each class
     **/
    @BeforeClass(alwaysRun = true)
    public void setUpMain(ITestContext context) {
        if (runByClassOrMethod) {
            try {
                AutomationTestInitializer initializer = new AutomationTestInitializer();
                am = initializer.initializeAutomationSystem();
            } catch (Exception e) {
                String message = "***FAILED IN @BEFORE Class*** in **TEST MAIN** Exception == " + e;
                Logger.log(Arrays.toString(context.getAllTestMethods()) + message + " - Exception e is: " + e);
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest(ITestResult testResult) {
        SauceExecutor sauceExecutor = new SauceExecutor(am.config.remote);
        IRetryAnalyzer retryAnalyzer = testResult.getMethod().getRetryAnalyzer(testResult);
        int retryCount = ((RetryAnalyzer) retryAnalyzer).getRetryCount();

        if (am == null) {
            Logger.log("Error initializing Automation Manager. Check start up and configurations");
            return;
        }

        if (testResult.getStatus() == ITestResult.FAILURE || testResult.getStatus() == ITestResult.SKIP) {
            if (retryCount < ((RetryAnalyzer) retryAnalyzer).getMaxRetryCount()) {
                am.reporter.getTest().skip(testResult.getMethod().getMethodName());
                am.reporter.logTest(Status.SKIP, testResult.getThrowable());
            } else {
                am.reporter.getTest().fail(testResult.getMethod().getMethodName());
                am.reporter.logTest(Status.FAIL, testResult.getThrowable());
            }
            sauceExecutor.testFailed(am.driverWrapper);
            allMethodsPassed = false;
        } else {
            am.reporter.getTest().pass(testResult.getMethod().getMethodName());
            sauceExecutor.testPassed(am.driverWrapper);
        }

        //If driverWrapper is never initialized Appium most likely never started so we don't want to keep records
        if (am.driverWrapper == null) {
            Logger.log("something really went wrong, the driverWrapper is not set");
            return;
        }

        String screenshotName = "After Method";
        String fullScreenShotName = am.userOp.takeScreenshotWithFileName(screenshotName);

        if(am.config.updateTestRail == true) {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                TestRailManager.addResultsToTestCase(testCaseId, TestRailManager.testCaseFailStatus, "Test Failed: " + testResult.getTestName(), am.config.getProperty("SECRET_VAR_TESTRAIL_USERNAME"), am.config.getProperty("SECRET_VAR_TESTRAIL_PASSWORD"), fullScreenShotName);
            } else if (testResult.getStatus() == ITestResult.SUCCESS) {
                TestRailManager.addResultsToTestCase(testCaseId, TestRailManager.testCasePassStatus, "Test Passed: ", am.config.getProperty("SECRET_VAR_TESTRAIL_USERNAME"), am.config.getProperty("SECRET_VAR_TESTRAIL_PASSWORD"), fullScreenShotName);
            }
        }

        am.reporter.writeTestCoverageList(new File("testCoverageListOut.txt"));
        am.reporter.write();

    }

    @AfterClass(alwaysRun = true)
    public void after() {
        if (runByClassOrMethod) {
            SauceExecutor sauceExecutor = new SauceExecutor(am.config.remote);
            if (allMethodsPassed) {
                sauceExecutor.testPassed(am.driverWrapper);
            } else {
                sauceExecutor.testFailed(am.driverWrapper);
            }
        }

        if (am.driverWrapper.notNull()) {
            Logger.log("Shutting down driver wrapper.");
            am.driverWrapper.quit();
        }
        if (am.appiumService != null && am.appiumService.isRunning()) {
            am.appiumService.stop();
        }
    }

    @AfterSuite
    public void tearDownFinal() {
        am.reporter.close();
    }

    //Gets all Groups and Description that belong to this method and add them to top of Extent Report
    private void updateReportedGroupsAndDescription(Method method) {
        String BOLD_TEXT = "<b>%s</b>";
        String TEST_DESCRIPTION = "<b>DESCRIPTION: </b>";
        String testDescription = method.getAnnotation((Test.class)).description();
        List<String> groups = asList(method.getAnnotation((Test.class)).groups());
        if (!groups.isEmpty()) {
            for (String group : groups) {
                am.reporter.addInfoToReport(String.format(BOLD_TEXT, group));
            }
        }
        if (!testDescription.isEmpty()) {
            am.reporter.addInfoToReport(TEST_DESCRIPTION + testDescription);
        }
    }

    //starts test with name and dataprovider data if applicable
    private void startTest(Method method, Object[] testData) {
        if (testData == null) {
            return;
        }
        if (testData.length > 0) {
            am.reporter.startTest(method.getName() + "[" + testData[0] + "]");
        } else {
            am.reporter.startTest(method.getName());
        }
    }
}
