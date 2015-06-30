package ackee.rxexample.rest;

import ackee.rxexample.model.MilacciResponse;
import retrofit.http.GET;
import rx.Observable;

/**
 * Interface describing milacci api
 * Created by David Bilik[david.bilik@ackee.cz] on {30. 6. 2015}
 **/
public interface MilacciDescription {
    public static final String TAG = MilacciDescription.class.getName();

    public static final String ENDPOINT = "http://private-1686e-milacci.apiary-mock.com/api/";

    @GET("/timesheets")
    Observable<MilacciResponse> getTimeSheets();
}
