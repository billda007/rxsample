package ackee.rxexample.rest;

import ackee.rxexample.model.Issue;
import ackee.rxexample.model.IssueResponse;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Interface describing redmine api
 * Created by David Bilik[david.bilik@ackee.cz] on {30. 6. 2015}
 **/
public interface RedmineDescription {
    public static final String ENDPOINT = "https://redmine.ackee.cz/";

    @GET("/issues/{id}.json")
    Observable<IssueResponse> getIssueDetail(@Path("id") String issueId);
}
