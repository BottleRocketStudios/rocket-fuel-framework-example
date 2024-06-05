package retryutils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 *
 * Created by ford.arnett on 10/6/21, I stole this from Mike V
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    // key for retry count value in test context
    public static final String RETRY_COUNT = "retryCount";
    private int retryCount = 0;
    private int maxRetryCount = 1;

    public int getRetryCount() {
        return retryCount;
    }

    public int getMaxRetryCount() { return maxRetryCount; }

    public void resetRetryCount() { this.retryCount = 0; }

    @Override
    public boolean retry(ITestResult result) {
        result.getTestContext().setAttribute(RETRY_COUNT, retryCount);
        //Check if test not succeed
        if (!result.isSuccess()) {
            //Check if max try count is reached
            if (retryCount < maxRetryCount) {
                //Increase the maxTry count by 1
                retryCount++;
                //Mark test as failed
                result.setStatus(ITestResult.SKIP);
                //Tells TestNG to re-run the test
                return true;
            } else {
                //If maxCount reached,test marked as failed
                result.setStatus(ITestResult.FAILURE);
                resetRetryCount();
            }
        } else {
            //If test passes, TestNG marks it as passed
            result.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

}

