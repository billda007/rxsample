package ackee.rxexample.model;

import java.util.List;

/**
 * Response with timesheets from milacci server
 * Created by David Bilik[david.bilik@ackee.cz] on {30. 6. 2015}
 **/
public class MilacciResponse {
    public static final String TAG = MilacciResponse.class.getName();
    double totalHours;
    double priceSum;
    List<TimeSheet> timesheets;

    public double getTotalHours() {
        return totalHours;
    }

    public double getPriceSum() {
        return priceSum;
    }

    public List<TimeSheet> getTimesheets() {
        return timesheets;
    }
}
