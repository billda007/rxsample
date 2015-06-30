package ackee.rxexample.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import ackee.rxexample.Constants;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Class that creates api description for different sources
 * Created by David Bilik[david.bilik@ackee.cz] on {30. 6. 2015}
 **/
public class RestService {
    public static final String TAG = RestService.class.getName();
    //TODO REPLACE WITH YOUR AUTHORIZATION HEADER TO REDMINE
    private static final String BASE_AUTH = Constants.BASE_AUTH;

    public static MilacciDescription getMilacciApi() {
        return new RestAdapter
                .Builder()
                .setEndpoint(MilacciDescription.ENDPOINT)
                .setConverter(new GsonConverter(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(MilacciDescription.class);
    }


    public static RedmineDescription getRedmineApi() {
        return new RestAdapter
                .Builder()
                .setEndpoint(RedmineDescription.ENDPOINT)
                .setConverter(new GsonConverter(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                .setLogLevel(RestAdapter.LogLevel.FULL)

                .setRequestInterceptor((request) -> request.addHeader("Authorization", "Basic " + BASE_AUTH))
                .build()
                .create(RedmineDescription.class);
    }
}
