package com.example.myfirstapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    class Thread implements Runnable {
        Thread() {
        }

        public void run() {
            MainActivity.this.finish();
            MainActivity.this.startActivity(new Intent(MainActivity.this, TattvaActivity.class));
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
