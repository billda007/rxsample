package ackee.rxexample.model;

/**
 * Entity that rises from merging of info from Milacci server and from Redmine API server
 * Created by David Bilik[david.bilik@ackee.cz] on {30. 6. 2015}
 **/
public class MergedEntity {
    public static final String TAG = MergedEntity.class.getName();
    TimeSheet timesheet;
    Issue issue;

    public MergedEntity(TimeSheet timesheet, Issue issue) {
        this.timesheet = timesheet;
        this.issue= issue;
    }

    public Issue getIssue() {
        return issue;
    }

    public TimeSheet getTimesheet() {
        return timesheet;
    }
}
