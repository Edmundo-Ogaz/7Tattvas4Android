package com.example.myfirstapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;

import java.util.Calendar;

public class TattvasForDayActivity extends Activity {

    static final int DATE_DIALOG_ID = 999;
    private static Activity activity;
    TabHost th;

    class Thread implements OnTabChangeListener {
        Thread() {
        }

        public void onTabChanged(String tabId) {
            System.out.println("onTabChanged: tab number=" + TattvasForDayActivity.this.th.getCurrentTab());
            switch (TattvasForDayActivity.this.th.getCurrentTab()) {
                case 2:
                    TattvasForDayActivity.this.showDialog(TattvasForDayActivity.DATE_DIALOG_ID);
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton1:
                finish();
                startActivity(new Intent(this, TattvaActivity.class));
                return;
            case R.id.imageButton4:
                finish();
                startActivity(new Intent(this, SettingsActivity.class));
                return;
            case R.id.imageButton5:
                stopService(new Intent(this, ServiceActivity.class));
                finish();
                System.exit(0);
                return;
            default:
                return;
        }
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new C00001();
    private int day;
    private int month;
    private int year;

    class C00001 implements DatePickerDialog.OnDateSetListener {
        C00001() {
        }

        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            System.out.println(new StringBuilder(String.valueOf(selectedDay)).append(" ").append(selectedMonth).append(" ").append(selectedYear).toString());
            ServiceActivity.fillForecastDay(TattvasForDayActivity.activity, selectedDay, selectedMonth, selectedYear);
        }
    }

    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();
        this.year = c.get(1);
        this.month = c.get(2);
        this.day = c.get(5);
        switch (id) {
            case DATE_DIALOG_ID /*999*/:
                return new DatePickerDialog(this, this.datePickerListener, this.year, this.month, this.day);
            default:
                return null;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_tattvas_for_day);
        this.th = (TabHost) findViewById(R.id.tabhost);
        this.th.setup();

        String tab1 = getResources().getString(R.string.tab3);
        String tab2 = getResources().getString(R.string.tab4);
        String tab3 = getResources().getString(R.string.tab5);

        TabSpec specs = this.th.newTabSpec("tag1");
        specs.setContent(R.id.tab1_1);
        specs.setIndicator(tab1);
        this.th.addTab(specs);

        specs = this.th.newTabSpec("tag2");
        specs.setContent(R.id.tab1_2);
        specs.setIndicator(tab2);
        this.th.addTab(specs);

        specs = this.th.newTabSpec("tag3");
        specs.setContent(R.id.tab2);
        specs.setIndicator(tab3);
        this.th.addTab(specs);

        this.th.setOnTabChangedListener(new Thread());

        TabWidget tabs = this.th.getTabWidget();
        tabs.setStripEnabled(false);
        for (int i = 0; i < tabs.getChildCount(); i++) {
            //((RelativeLayout) tabs.getChildAt(i)).setBackgroundDrawable(getResources().getDrawable(R.drawable.tabhost_bg_selector));
        }
        activity = this;
        ServiceActivity.fillForecast(this);
    }
}
