package example.assertions;


/**
 * Methods can return this to give more data in reporting rather than just being true or false
 * <p>
 * Probably move into framework?
 * <p>
 * Created by ford.arnett on 3/28/17.
 */
public class AssertionPayload {
    public boolean assertSuccessful;
    //If this needs to be an object, will need to override toString and add support for whatever objects are needed so message can be reported properly
    public String payload;
    public String failureMessage;
    public String successMessage;
    public boolean takeScreenshotFailure = true;
    public String screenShotFailFilename = "";
    public boolean takeScreenshotSuccess = true;
    public String screenShotSuccessFilename = "";
    AssertionCategories category;
    String testDescription;

    public AssertionPayload(boolean assertSuccessful, String successMessage, String failureMessage) {
        this.assertSuccessful = assertSuccessful;
        this.successMessage = successMessage;
        this.failureMessage = failureMessage;
    }

    public AssertionPayload(boolean assertSuccessful, String message, AssertionCategories category) {
        this.assertSuccessful = assertSuccessful;
        this.successMessage = "<b>" + message + "</b>";
        this.failureMessage = "<b>" + message + "</b>";
        this.category = category;
        this.testDescription = message;
        this.takeScreenshotSuccess = true;
    }


    public AssertionPayload(boolean assertSuccessful, String successMessage, String failureMessage, String screenShotFailFilename) {
        this.assertSuccessful = assertSuccessful;
        this.successMessage = successMessage;
        this.failureMessage = failureMessage;
        this.screenShotFailFilename = screenShotFailFilename;
    }

    public AssertionPayload(boolean assertSuccessful, String successMessage, String failureMessage, boolean takeScreenshotFailure) {
        this.assertSuccessful = assertSuccessful;
        this.successMessage = successMessage;
        this.failureMessage = failureMessage;
        this.takeScreenshotFailure = takeScreenshotFailure;
    }

    public AssertionPayload(boolean assertSuccessful, String successMessage, String failureMessage, String screenShotSuccessFilename, String screenShotFailFilename) {
        this.assertSuccessful = assertSuccessful;
        this.successMessage = successMessage;
        this.failureMessage = failureMessage;
        this.screenShotFailFilename = screenShotFailFilename;
        this.screenShotSuccessFilename = screenShotSuccessFilename;
        this.takeScreenshotSuccess = true;
    }

    public AssertionPayload(boolean assertSuccessful, String successMessage, String failureMessage, String screenShotSuccessFilename, String screenShotFailFilename, AssertionCategories category, String testDescription) {
        this.assertSuccessful = assertSuccessful;
        this.successMessage = successMessage;
        this.failureMessage = failureMessage;
        this.screenShotFailFilename = screenShotFailFilename;
        this.screenShotSuccessFilename = screenShotSuccessFilename;
        this.category = category;
        this.testDescription = testDescription;
        this.takeScreenshotSuccess = true;
    }

    public AssertionPayload(boolean assertSuccessful, String message) {
        this.assertSuccessful = assertSuccessful;
        this.successMessage = "Success: " + message;
        this.failureMessage = "Failure: " + message;
        this.screenShotFailFilename = screenShotFailFilename;
        this.screenShotSuccessFilename = screenShotSuccessFilename;
        this.category = category;
        this.testDescription = testDescription;
        this.takeScreenshotSuccess = true;
    }

    public AssertionPayload setScreenShotSuccessFilename(String screenShotSuccessFilename) {
        this.screenShotSuccessFilename = screenShotSuccessFilename;
        return this;
    }

    public AssertionPayload setScreenShotFailFilename(String screenShotFailFilename) {
        this.screenShotFailFilename = screenShotFailFilename;
        return this;
    }

    public AssertionPayload setTakeScreenshotSuccess(boolean takeScreenshotSuccess) {
        this.takeScreenshotSuccess = takeScreenshotSuccess;
        return this;
    }

    public AssertionPayload setTakeScreenshotFailure(boolean takeScreenshotFailure) {
        this.takeScreenshotFailure = takeScreenshotFailure;
        return this;
    }

    public AssertionPayload setTestDescription(AssertionCategories category, String testDescription) {
        this.category = category;
        this.testDescription = testDescription;
        return this;
    }
}
