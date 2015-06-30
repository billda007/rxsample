package ackee.rxexample.model;

/**
 * Issue from redmine api
 * Created by David Bilik[david.bilik@ackee.cz] on {30. 6. 2015}
 **/
public class Issue {
    public static final String TAG = Issue.class.getName();
    int id;
    String subject;
    String description;

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }
}
