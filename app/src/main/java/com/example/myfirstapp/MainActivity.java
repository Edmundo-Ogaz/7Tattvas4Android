package com.example.myfirstapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tomerrosenfeld.customanalogclockview.CustomAnalogClock;

public class MainActivity extends AppCompatActivity {

    class Thread implements Runnable {
        Thread() {
        }

        public void run() {
            MainActivity.this.finish();
            MainActivity.this.startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, ServiceActivity.class));

        Handler handler = new Handler();
        handler.postDelayed(new Thread(), 2500);

    }
}
