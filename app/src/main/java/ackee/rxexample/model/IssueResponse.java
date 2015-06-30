package ackee.rxexample.model;

/**
 * Wrapper for issue. In this format it is downlaoded from redmine api
 * Created by David Bilik[david.bilik@ackee.cz] on {30. 6. 2015}
 **/
public class IssueResponse {
    public static final String TAG = IssueResponse.class.getName();
    Issue issue;

    public Issue getIssue() {
        return issue;
    }
}
