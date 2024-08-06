package com.testRailManager;

import com.testrail.*;
import org.json.simple.*;

import java.util.*;

public class TestRailManager {

    public static String testRunId = "4512";
    public static String testRailUrl = "https://bottlerocket.testrail.net/";

    public static int testCasePassStatus = 1;
    public static int testCaseFailStatus = 5;

    public static void addResultsToTestCase(String testCaseId, int status, String error, String testRailUsername, String testRailPassword, String screenshotPath) {
        APIClient client = new APIClient(testRailUrl);
        client.setUser(testRailUsername);
        client.setPassword(testRailPassword);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("status_id", status);
        data.put("comment", "This test executed via gitHub Action " + error);

            //sends request to update test case with for testRunId with pass/fail status and comment and saves response
            JSONObject updateTestResponse = client.addResultForTestCase(testRunId, testCaseId, data);
            //retrieves test resultId from updateTestResponse so attachments can be attached
            String resultId = String.valueOf(updateTestResponse.get("id"));
            //add attachment to testRun with resultId and saves response
            JSONObject addAttachmentResponse = client.addAttachmentToResult(resultId, screenshotPath);

    }

}
