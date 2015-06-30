package ackee.rxexample.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ackee.rxexample.R;
import ackee.rxexample.adapter.TimesheetAdapter;
import ackee.rxexample.model.IssueResponse;
import ackee.rxexample.model.MergedEntity;
import ackee.rxexample.model.MilacciResponse;
import ackee.rxexample.rest.RestService;
import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.BlockingObservable;
import rx.schedulers.Schedulers;

/**
 * Activity that shows example of using RxAndroid
 * Created by David Bilik[david.bilik@ackee.cz] on {30. 6. 2015}
 **/
public class RxActivity extends AppCompatActivity {
    public static final String TAG = RxActivity.class.getName();

    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @InjectView(R.id.progressbar)
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        ButterKnife.inject(this);
        loadData();
        initRecyclerView();
    }

    /**
     * Initalize recycler view
     */
    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /***
     * Load data from server. First timesheets from milacci api and then for every timesheet its issue id is downloaded from redmine
     */
    private void loadData() {
        mProgressBar.setVisibility(View.VISIBLE);
        RestService.getMilacciApi().getTimeSheets().cache() //get timesheets
                .subscribeOn(Schedulers.newThread()) //perform all work on new thread
                .map(MilacciResponse::getTimesheets) // remap response - pass next only list of timesheets
                .flatMap(Observable::from) // convert from list of timesheets to emit items one by one so it can be processed separately
                .map(timeSheet -> new MergedEntity(timeSheet, BlockingObservable.from(getIssue(timeSheet.getRedmineIssueId())).first().getIssue())) // every timesheet remap to merged entity and blockingly download issue from redmine
                .toList() //all emitted items collect to list
                .observeOn(AndroidSchedulers.mainThread()) //observe this on main thread
                .subscribe(this::dataLoaded, this::onErrorHappened); // call hook methods
    }

    /**
     * Returns observable that should emit downloaded issue response from redmine
     *
     * @param id - id of issue
     * @return observable with issue
     */
    private Observable<IssueResponse> getIssue(int id) {
        return RestService.getRedmineApi().getIssueDetail(id + "");
    }

    /**
     * Called when data are loaded
     *
     * @param data - list of downlaoded data
     */
    private void dataLoaded(List<MergedEntity> data) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setAdapter(new TimesheetAdapter(data));
    }

    /**
     * Called when whatever error occured while downloading
     *
     * @param t - throwable exception
     */
    private void onErrorHappened(Throwable t) {
        t.printStackTrace();
        Snackbar.make(findViewById(android.R.id.content), "Chyba pri nacitani", Toast.LENGTH_SHORT).show();
    }
}
