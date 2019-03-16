package com.example.myfirstapp;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tomerrosenfeld.customanalogclockview.CustomAnalogClock;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TattvaActivity extends Activity {

    Timer timer = new Timer();
    private static boolean outOfDate = false;

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton2:
                this.timer.cancel();
                outOfDate = true;
                finish();
                startActivity(new Intent(this, TattvasForDayActivity.class));
                return;
            case R.id.imageButton4:
                this.timer.cancel();
                outOfDate = true;
                finish();
                startActivity(new Intent(this, SettingsActivity.class));
                return;
            case R.id.imageButton5:
                stopService(new Intent(this, ServiceActivity.class));
                this.timer.cancel();
                outOfDate = true;
                finish();
                System.exit(0);
                return;
            default:
                return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ServiceActivity.fillHome(this);

    }


}
