package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.Toast;

public class SettingsActivity extends PreferenceActivity {

    private static TabHost ths;
    OnPreferenceChangeListener latitudeCheckListener = new C00082();
    OnPreferenceChangeListener longitudeCheckListener = new C00093();
    OnPreferenceClickListener useChange = new C00071();

    /* renamed from: me.dhanielk.tattvas.Preferences$1 */
    class C00071 implements OnPreferenceClickListener {
        C00071() {
        }

        public boolean onPreferenceClick(Preference preference) {
            Toast.makeText(SettingsActivity.this, SettingsActivity.this.getResources().getString(R.string.w_change), 0).show();
            return true;
        }
    }

    /* renamed from: me.dhanielk.tattvas.Preferences$2 */
    class C00082 implements OnPreferenceChangeListener {
        C00082() {
        }

        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return SettingsActivity.this.latitudeCheck(newValue);
        }
    }

    /* renamed from: me.dhanielk.tattvas.Preferences$3 */
    class C00093 implements OnPreferenceChangeListener {
        C00093() {
        }

        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return SettingsActivity.this.longitudeCheck(newValue);
        }
    }

    private boolean latitudeCheck(Object newValue) {
        if (Double.parseDouble(newValue.toString()) >= -90.0d && Double.parseDouble(newValue.toString()) <= 90.0d) {
            return true;
        }
        Toast.makeText(this, getResources().getString(R.string.inv_latitude), 0).show();
        return false;
    }

    private boolean longitudeCheck(Object newValue) {
        if (Double.parseDouble(newValue.toString()) >= -180.0d && Double.parseDouble(newValue.toString()) <= 180.0d) {
            return true;
        }
        Toast.makeText(this, getResources().getString(R.string.inv_longitude), 0).show();
        return false;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton1:
                finish();
                startActivity(new Intent(this, TattvaActivity.class));
                return;
            case R.id.imageButton2:
                finish();
                startActivity(new Intent(this, TattvasForDayActivity.class));
                return;
            case R.id.imageButton5:
                stopService(new Intent(this, SettingsActivity.class));
                finish();
                System.exit(0);
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_settings);
        ths = (TabHost) findViewById(R.id.tabhosts);
        ths.setup();
        String tab1 = getResources().getString(R.string.title_activity_settings);
        TabSpec specs = ths.newTabSpec("tag1");
        specs.setContent(R.id.tabs_1);
        specs.setIndicator(tab1);
        ths.addTab(specs);
        TabWidget tabs = ths.getTabWidget();
        tabs.setStripEnabled(false);
        //((RelativeLayout) tabs.getChildAt(0)).setBackgroundDrawable(getResources().getDrawable(R.drawable.tabhost_bar3_a));
        addPreferencesFromResource(R.xml.settings);
        Preference latitude = getPreferenceScreen().findPreference("PrefLatitude");
        Preference longitude = getPreferenceScreen().findPreference("PrefLongitude");
        latitude.setOnPreferenceChangeListener(this.latitudeCheckListener);
        longitude.setOnPreferenceChangeListener(this.longitudeCheckListener);
        getPreferenceScreen().findPreference("PrefGps").setOnPreferenceClickListener(this.useChange);
    }
}
