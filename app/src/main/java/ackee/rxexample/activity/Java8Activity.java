package ackee.rxexample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import ackee.rxexample.R;
import butterknife.ButterKnife;
import butterknife.InjectViews;

/**
 * Activity that shows example of using Java 8 lambad expressions and function pointers
 */
public class Java8Activity extends AppCompatActivity {

    @InjectViews({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5})
    Button[] mBtns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        setTitle("Java 8 example");
        ButterKnife.inject(this);
        setupOldWay();
        setupLambdaWay();
        setupFunctionPointerWay();
    }

    /***
     * Setup view disabling by lambda function
     */
    private void setupLambdaWay() {
        for (final Button b : mBtns) {
            b.setOnClickListener((v) -> v.setEnabled(false));
        }
    }

    /***
     * Setup view disabling by setting function pointer
     */
    private void setupFunctionPointerWay() {
        for (final Button b : mBtns) {
            b.setOnClickListener(this::disableView);
        }
    }

    private void disableView(View v) {
        v.setEnabled(false);
    }

    /***
     * Setup view disabling with interfaces just like in old Java
     */
    private void setupOldWay() {
        for (final Button b : mBtns) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b.setEnabled(false);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_rx_activity) {
            startActivity(new Intent(this, RxActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
