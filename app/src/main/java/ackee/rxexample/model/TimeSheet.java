package ackee.rxexample.model;

/**
 * Class representing timesheet from milacci
 * Created by David Bilik[david.bilik@ackee.cz] on {30. 6. 2015}
 **/
public class TimeSheet {
    public static final String TAG = TimeSheet.class.getName();
    int id;
    String issueName;
    String timesheetName;
    double spentHours;
    int redmineIssueId;

    public int getId() {
        return id;
    }

    public String getIssueName() {
        return issueName;
    }

    public String getTimesheetName() {
        return timesheetName;
    }

    public double getSpentHours() {
        return spentHours;
    }

    public int getRedmineIssueId() {
        return redmineIssueId;
    }
}
