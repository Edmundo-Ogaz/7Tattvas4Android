package com.example.myfirstapp;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import java.util.Calendar;
import java.util.Date;

public class ServiceActivity  extends Service {

    static Service service;
    static double latitude;
    static double longitude;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        System.out.println("Service online");
        service = this;

        latitude = -33.444215;
        longitude = -70.633427;

    }

    static boolean showCircle;


    public static void fillHome(Activity mActivity) {
        int nextTatva;

        ImageView tattvicClock = (ImageView) mActivity.findViewById(R.id.imageView1);
        ImageView tattvicHands = (ImageView) mActivity.findViewById(R.id.imageView2);
        ImageView tattvicPoint = (ImageView) mActivity.findViewById(R.id.imageView3);
        TextView tSunRise = (TextView) mActivity.findViewById(R.id.tSunRise);
        TextView tSunSet = (TextView) mActivity.findViewById(R.id.tSunSet);
        TextView tTattva = (TextView) mActivity.findViewById(R.id.tTattva);
        TextView tNextTattva = (TextView) mActivity.findViewById(R.id.tNextTattva);
        TextView tTimeRemain = (TextView) mActivity.findViewById(R.id.tRemain);

        showCircle = PreferenceManager.getDefaultSharedPreferences(mActivity).getBoolean("show_circle", true);

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);

        double SecRise = makeSunRise(day, latitude, longitude)[0];
        double SecSet = makeSunRise(day, latitude, longitude)[1];

        int minutesTime = makeMinutes(SecRise);
        int curTime = (cal.get(Calendar.HOUR_OF_DAY) * 60) + cal.get(Calendar.MINUTE);
        String timeSunRise;
        String timeSunSet;
        if (curTime < minutesTime) {
            SecRise = makeSunRise(day - 1, latitude, longitude)[0];
            SecSet = makeSunRise(day - 1, latitude, longitude)[1];
            minutesTime = makeMinutes(SecRise);
            curTime += 1440;
            timeSunRise = new StringBuilder(String.valueOf(makeHodinyMinuty(((int) SecRise) / 60))).append(":").append(makeSekundy(((int) SecRise) % 60)).toString();
            timeSunSet = new StringBuilder(String.valueOf(makeHodinyMinuty(((int) SecSet) / 60))).append(":").append(makeSekundy(((int) SecSet) % 60)).toString();
            tSunRise.setText(timeSunRise);
            tSunSet.setText(timeSunSet);
        } else {
            timeSunRise = new StringBuilder(String.valueOf(makeHodinyMinuty(((int) SecRise) / 60))).append(":").append(makeSekundy(((int) SecRise) % 60)).toString();
            timeSunSet = new StringBuilder(String.valueOf(makeHodinyMinuty(((int) SecSet) / 60))).append(":").append(makeSekundy(((int) SecSet) % 60)).toString();
            tSunRise.setText(timeSunRise);
            tSunSet.setText(timeSunSet);
        }
        int i = 1;
        int k = 1;
        while (curTime > ((i * 23) + minutesTime) + (i * 1)) {
            i++;
            if (k == 7) {
                k = 1;
            } else {
                k++;
            }
        }
        int Bezi = (((i * 23) + minutesTime) + (i * 1)) - curTime;
        String tTatva1 = mActivity.getResources().getString(R.string.tatvaEnd1);
        String tTatva2 = mActivity.getResources().getString(R.string.tatvaEnd2);
        if (Bezi == 0) {
            tTimeRemain.setText(tTatva2);
        } else {
            tTimeRemain.setText(new StringBuilder(String.valueOf(tTatva1)).append(" ").append(Bezi).append(" min.").toString());
        }
        if (k == 7) {
            nextTatva = 1;
        } else {
            nextTatva = k + 1;
        }
        tNextTattva.setText(jakaJeTatva(nextTatva));
        tTattva.setText(jakaJeTatva(k));
        switch (k) {
            case 1:
                tattvicClock.setImageResource(R.drawable.c1);
                tattvicPoint.setImageResource(R.drawable.b1);
                break;
            case 2:
                tattvicClock.setImageResource(R.drawable.c2);
                tattvicPoint.setImageResource(R.drawable.b2);
                break;
            case 3:
                tattvicClock.setImageResource(R.drawable.c3);
                tattvicPoint.setImageResource(R.drawable.b3);
                break;
            case 4:
                tattvicClock.setImageResource(R.drawable.c4);
                tattvicPoint.setImageResource(R.drawable.b4);
                break;
            case 5:
                tattvicClock.setImageResource(R.drawable.c5);
                tattvicPoint.setImageResource(R.drawable.b5);
                break;
            case 6:
                tattvicClock.setImageResource(R.drawable.c5);
                tattvicPoint.setImageResource(R.drawable.b5);
                break;
            case 7:
                tattvicClock.setImageResource(R.drawable.c5);
                tattvicPoint.setImageResource(R.drawable.b5);
                break;
        }
        int handPosition = 24 - Bezi;
        System.out.println("Hand position ->" + handPosition);
        switch (handPosition) {
            case 0:
                tattvicHands.setImageResource(R.drawable.ch1);
                break;
            case 1:
                tattvicHands.setImageResource(R.drawable.ch2);
                break;
            case 2:
                tattvicHands.setImageResource(R.drawable.ch3);
                break;
            case 3:
                tattvicHands.setImageResource(R.drawable.ch4);
                break;
            case 4:
                tattvicHands.setImageResource(R.drawable.ch5);
                break;
            case 5:
                tattvicHands.setImageResource(R.drawable.ch6);
                break;
            case 6:
                tattvicHands.setImageResource(R.drawable.ch7);
                break;
            case 7:
                tattvicHands.setImageResource(R.drawable.ch8);
                break;
            case 8:
                tattvicHands.setImageResource(R.drawable.ch9);
                break;
            case 9:
                tattvicHands.setImageResource(R.drawable.ch10);
                break;
            case 10:
                tattvicHands.setImageResource(R.drawable.ch11);
                break;
            case 11:
                tattvicHands.setImageResource(R.drawable.ch12);
                break;
            case 12:
                tattvicHands.setImageResource(R.drawable.ch13);
                break;
            case 13:
                tattvicHands.setImageResource(R.drawable.ch14);
                break;
            case 14:
                tattvicHands.setImageResource(R.drawable.ch15);
                break;
            case 15:
                tattvicHands.setImageResource(R.drawable.ch16);
                break;
            case 16:
                tattvicHands.setImageResource(R.drawable.ch17);
                break;
            case 17:
                tattvicHands.setImageResource(R.drawable.ch18);
                break;
            case 18:
                tattvicHands.setImageResource(R.drawable.ch19);
                break;
            case 19:
                tattvicHands.setImageResource(R.drawable.ch20);
                break;
            case 20:
                tattvicHands.setImageResource(R.drawable.ch21);
                break;
            case 21:
                tattvicHands.setImageResource(R.drawable.ch22);
                break;
            case 22:
                tattvicHands.setImageResource(R.drawable.ch23);
                break;
            case 23:
                tattvicHands.setImageResource(R.drawable.ch24);
                break;
            case 24:
                tattvicHands.setImageResource(R.drawable.ch24);
                break;
        }
        if (!showCircle) {
            tattvicPoint.setAlpha(0);
        }
    }
    public static double[] makeSunRise(int day, double latitude, double longitude) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(1);
        int month = cal.get(2);
        Date date = cal.getTime();
        int timeZone = ((cal.getTimeZone().getOffset(date.getTime()) / 1000) / 60) / 60;
        month++;
        int a = (14 - month) / 12;
        int y = (year + 4800) - a;
        double Jc = (((((double) ((((((((((((a * 12) + month) - 3) * 153) + 2) / 5) + day) + (y * 365)) + (y / 4)) - (y / 100)) + (y / 400)) - 32045)) + 0.5d) - ((double) (timeZone / 24))) - 2451545.0d) / 36525.0d;
        double Geom1 = (280.46646d + ((36000.76983d + (3.032E-4d * Jc)) * Jc)) % 360.0d;
        double Geom2 = 357.52911d + ((35999.05029d - (1.537E-4d * Jc)) * Jc);
        double EarthOr = 0.016708634d - ((4.2037E-5d + (1.267E-7d * Jc)) * Jc);
        double ObbCorr = (23.0d + ((26.0d + ((21.448d - ((46.815d + ((5.9E-4d - (0.001813d * Jc)) * Jc)) * Jc)) / 60.0d)) / 60.0d)) + (0.00256d * Math.cos(Math.toRadians(125.04d - (1934.136d * Jc))));
        double SunDec = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(ObbCorr)) * Math.sin(Math.toRadians(((Geom1 + (((Math.sin(Math.toRadians(Geom2)) * (1.914602d - ((0.004817d + (1.4E-5d * Jc)) * Jc))) + (Math.sin(Math.toRadians(2.0d * Geom2)) * (0.019993d - (1.01E-4d * Jc)))) + (Math.sin(Math.toRadians(3.0d * Geom2)) * 2.89E-4d))) - 0.00569d) - (0.00478d * Math.sin(Math.toRadians(125.04d - (1934.136d * Jc))))))));
        double VarY = Math.tan(Math.toRadians(ObbCorr / 2.0d)) * Math.tan(Math.toRadians(ObbCorr / 2.0d));
        double TimeEq = 4.0d * Math.toDegrees(((((Math.sin(2.0d * Math.toRadians(Geom1)) * VarY) - ((2.0d * EarthOr) * Math.sin(Math.toRadians(Geom2)))) + ((((4.0d * EarthOr) * VarY) * Math.sin(Math.toRadians(Geom2))) * Math.cos(2.0d * Math.toRadians(Geom1)))) - (((0.5d * VarY) * VarY) * Math.sin(4.0d * Math.toRadians(Geom1)))) - (((1.25d * EarthOr) * EarthOr) * Math.sin(2.0d * Math.toRadians(Geom2))));
        double HASun = Math.toDegrees(Math.acos((Math.cos(Math.toRadians(90.833d)) / (Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(SunDec)))) - (Math.tan(Math.toRadians(latitude)) * Math.tan(Math.toRadians(SunDec)))));
        double SolarNoon = (((720.0d - (4.0d * longitude)) - TimeEq) + ((double) (timeZone * 60))) / 1440.0d;
        double SecRise = ((24.0d * (SolarNoon - ((4.0d * HASun) / 1440.0d))) * 60.0d) * 60.0d;
        double SecSet = ((24.0d * (SolarNoon + ((4.0d * HASun) / 1440.0d))) * 60.0d) * 60.0d;
        return new double[]{SecRise, SecSet};
    }

    public static int makeMinutes(double SecTime) {
        return ((((int) SecTime) / 3600) * 60) + (((int) (SecTime % 3600.0d)) / 60);
    }

    public static String makeHodinyMinuty(int minuty) {
        int hod = minuty / 60;
        if (hod >= 24) {
            hod -= 24;
        }
        int min = minuty % 60;
        if (hod < 10 && min < 10) {
            return "0" + hod + ":" + "0" + min;
        }
        if (hod < 10) {
            return "0" + hod + ":" + min;
        }
        if (min < 10) {
            return new StringBuilder(String.valueOf(hod)).append(":").append("0").append(min).toString();
        }
        return new StringBuilder(String.valueOf(hod)).append(":").append(min).toString();
    }

    public static String makeSekundy(int sec) {
        if (sec < 10) {
            return "0" + Integer.toString(sec);
        }
        return Integer.toString(sec);
    }

    public static String jakaJeTatva(int k) {
        switch (k) {
            case 1:
                return service.getResources().getString(R.string.tat1);
            case 2:
                return service.getResources().getString(R.string.tat2);
            case 3:
                return service.getResources().getString(R.string.tat3);
            case 4:
                return service.getResources().getString(R.string.tat4);
            case 5:
                return service.getResources().getString(R.string.tat5);
            case 6:
                return service.getResources().getString(R.string.tat5);
            case 7:
                return service.getResources().getString(R.string.tat5);
            default:
                return "Náda";
        }
    }

    static LinearLayout todayForecast;
    static LinearLayout tomorrowForecast;

    public static void fillForecast(Activity mActivity) {
        int k;
        int i;
        int paddingForecast = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(mActivity).getString("paddingFor", "40")).intValue();
        todayForecast = (LinearLayout) mActivity.findViewById(R.id.todaysForecast);
        tomorrowForecast = (LinearLayout) mActivity.findViewById(R.id.tomorrowsForecast);
        String format = "%1$-12s";
        int minutesTime = countToday();
        int j = 1;
        Calendar cal = Calendar.getInstance();
        int curTime = (cal.get(11) * 60) + cal.get(12);
        TextView[] tv = new TextView[6];
        for (k = 0; k < tv.length; k++) {
            tv[k] = new TextView(mActivity);
            tv[k].setTextSize(20.0f);
            tv[k].setId(k + 1);
        }
        LinearLayout[] ll = new LinearLayout[4];
        for (k = 0; k < ll.length; k++) {
            ll[k] = new LinearLayout(mActivity);
            ll[k].setOrientation(0);
            ll[k].setGravity(17);
            ll[k].setPadding(paddingForecast, 0, paddingForecast, 0);
        }
        tv[0].setGravity(3);
        tv[0].setPadding(paddingForecast, 0, 0, 0);
        tv[1].setGravity(3);
        tv[1].setPadding(paddingForecast, 0, 0, 0);
        tv[2].setGravity(5);
        tv[0].setText(new StringBuilder(String.valueOf(mActivity.getResources().getString(R.string.for1))).append(" ").append(cal.get(5)).append(". ").append(cal.get(2) + 1).append(". ").append(cal.get(1)).toString());
        tv[1].setText(mActivity.getResources().getString(R.string.for2));
        tv[2].setText(mActivity.getResources().getString(R.string.for3));
        ll[0].addView(tv[0]);
        ll[1].addView(tv[1]);
        ll[1].addView(tv[2], new LayoutParams(-1, -2));
        todayForecast.addView(ll[0]);
        todayForecast.addView(ll[1]);
        cal.add(6, 1);
        tv[3].setGravity(3);
        tv[3].setPadding(paddingForecast, 0, 0, 0);
        tv[4].setGravity(3);
        tv[4].setPadding(paddingForecast, 0, 0, 0);
        tv[5].setGravity(5);
        tv[3].setText(new StringBuilder(String.valueOf(mActivity.getResources().getString(R.string.for1))).append(" ").append(cal.get(5)).append(". ").append(cal.get(2) + 1).append(". ").append(cal.get(1)).toString());
        tv[4].setText(mActivity.getResources().getString(R.string.for2));
        tv[5].setText(mActivity.getResources().getString(R.string.for3));
        ll[2].addView(tv[3]);
        ll[3].addView(tv[4]);
        ll[3].addView(tv[5], new LayoutParams(-1, -2));
        tomorrowForecast.addView(ll[2]);
        tomorrowForecast.addView(ll[3]);
        cal.add(6, -1);
        ImageView[] iv1 = new ImageView[61];
        TextView[] tv1 = new TextView[61];
        TextView[] tv1_2 = new TextView[61];
        LinearLayout[] ll1 = new LinearLayout[61];
        for (i = 1; i < 61; i++) {
            ll1[i] = new LinearLayout(mActivity);
            ll1[i].setOrientation(0);
            ll1[i].setGravity(17);
            tv1[i] = new TextView(mActivity);
            tv1[i].setTextSize(20.0f);
            tv1_2[i] = new TextView(mActivity);
            tv1_2[i].setTextSize(20.0f);
            iv1[i] = new ImageView(mActivity);
            switch (j) {
                case 1:
                    tv1[i].setText(String.format(format, new Object[]{mActivity.getResources().getString(R.string.tat1)}));
                    tv1_2[i].setText(new StringBuilder(String.valueOf(makeHodinyMinuty((((i * 23) + minutesTime) + (i * 1)) - 23))).append(" - ").append(makeHodinyMinuty(((i * 23) + minutesTime) + (i * 1))).toString());
                    iv1[i].setImageResource(R.drawable.iv_01);
                    break;
                case 2:
                    tv1[i].setText(String.format(format, new Object[]{mActivity.getResources().getString(R.string.tat2)}));
                    tv1_2[i].setText(new StringBuilder(String.valueOf(makeHodinyMinuty((((i * 23) + minutesTime) + (i * 1)) - 23))).append(" - ").append(makeHodinyMinuty(((i * 23) + minutesTime) + (i * 1))).toString());
                    iv1[i].setImageResource(R.drawable.iv_02);
                    break;
                case 3:
                    tv1[i].setText(String.format(format, new Object[]{mActivity.getResources().getString(R.string.tat3)}));
                    tv1_2[i].setText(new StringBuilder(String.valueOf(makeHodinyMinuty((((i * 23) + minutesTime) + (i * 1)) - 23))).append(" - ").append(makeHodinyMinuty(((i * 23) + minutesTime) + (i * 1))).toString());
                    iv1[i].setImageResource(R.drawable.iv_03);
                    break;
                case 4:
                    tv1[i].setText(String.format(format, new Object[]{mActivity.getResources().getString(R.string.tat4)}));
                    tv1_2[i].setText(new StringBuilder(String.valueOf(makeHodinyMinuty((((i * 23) + minutesTime) + (i * 1)) - 23))).append(" - ").append(makeHodinyMinuty(((i * 23) + minutesTime) + (i * 1))).toString());
                    iv1[i].setImageResource(R.drawable.iv_04);
                    break;
                case 5:
                    tv1[i].setText(String.format(format, new Object[]{mActivity.getResources().getString(R.string.tat5)}));
                    tv1_2[i].setText(new StringBuilder(String.valueOf(makeHodinyMinuty((((i * 23) + minutesTime) + (i * 1)) - 23))).append(" - ").append(makeHodinyMinuty(((i * 23) + minutesTime) + (i * 1))).toString());
                    iv1[i].setImageResource(R.drawable.iv_05);
                    break;
            }
            iv1[i].setId(i);
            iv1[i].setAdjustViewBounds(true);
            if (paddingForecast == 40) {
                iv1[i].setMaxHeight(25);
            } else {
                iv1[i].setMaxWidth(50);
            }
            tv1[i].setId(i);
            tv1[i].setGravity(3);
            tv1[i].setPadding(paddingForecast, 0, 0, 0);
            tv1_2[i].setId(i);
            tv1_2[i].setGravity(5);
            if (curTime > ((i * 23) + minutesTime) + (i * 1)) {
                tv1[i].setTextColor(Color.parseColor("#bababa"));
                tv1_2[i].setTextColor(Color.parseColor("#bababa"));
            }
            ll1[i].addView(iv1[i]);
            ll1[i].addView(tv1[i]);
            ll1[i].addView(tv1_2[i], new LayoutParams(-1, -2));
            ll1[i].setPadding(paddingForecast, 0, paddingForecast, 0);
            todayForecast.addView(ll1[i]);
            if (j == 5) {
                j = 1;
            } else {
                j++;
            }
        }
        minutesTime = countTomorrow();
        j = 1;
        ImageView[] iv2 = new ImageView[61];
        TextView[] tv2 = new TextView[61];
        TextView[] tv2_2 = new TextView[61];
        LinearLayout[] ll2 = new LinearLayout[61];
        for (i = 1; i < 61; i++) {
            ll2[i] = new LinearLayout(mActivity);
            ll2[i].setOrientation(LinearLayout.HORIZONTAL);
            ll2[i].setGravity(17);
            tv2[i] = new TextView(mActivity);
            tv2[i].setTextSize(20.0f);
            tv2_2[i] = new TextView(mActivity);
            tv2_2[i].setTextSize(20.0f);
            iv2[i] = new ImageView(mActivity);
            switch (j) {
                case 1:
                    tv2[i].setText(String.format(format, new Object[]{mActivity.getResources().getString(R.string.tat1)}));
                    tv2_2[i].setText(new StringBuilder(String.valueOf(makeHodinyMinuty((((i * 23) + minutesTime) + (i * 1)) - 23))).append(" - ").append(makeHodinyMinuty(((i * 23) + minutesTime) + (i * 1))).toString());
                    iv2[i].setImageResource(R.drawable.iv_01);
                    break;
                case 2:
                    tv2[i].setText(String.format(format, new Object[]{mActivity.getResources().getString(R.string.tat2)}));
                    tv2_2[i].setText(new StringBuilder(String.valueOf(makeHodinyMinuty((((i * 23) + minutesTime) + (i * 1)) - 23))).append(" - ").append(makeHodinyMinuty(((i * 23) + minutesTime) + (i * 1))).toString());
                    iv2[i].setImageResource(R.drawable.iv_02);
                    break;
                case 3:
                    tv2[i].setText(String.format(format, new Object[]{mActivity.getResources().getString(R.string.tat3)}));
                    tv2_2[i].setText(new StringBuilder(String.valueOf(makeHodinyMinuty((((i * 23) + minutesTime) + (i * 1)) - 23))).append(" - ").append(makeHodinyMinuty(((i * 23) + minutesTime) + (i * 1))).toString());
                    iv2[i].setImageResource(R.drawable.iv_03);
                    break;
                case 4:
                    tv2[i].setText(String.format(format, new Object[]{mActivity.getResources().getString(R.string.tat4)}));
                    tv2_2[i].setText(new StringBuilder(String.valueOf(makeHodinyMinuty((((i * 23) + minutesTime) + (i * 1)) - 23))).append(" - ").append(makeHodinyMinuty(((i * 23) + minutesTime) + (i * 1))).toString());
                    iv2[i].setImageResource(R.drawable.iv_04);
                    break;
                case 5:
                    tv2[i].setText(String.format(format, new Object[]{mActivity.getResources().getString(R.string.tat5)}));
                    tv2_2[i].setText(new StringBuilder(String.valueOf(makeHodinyMinuty((((i * 23) + minutesTime) + (i * 1)) - 23))).append(" - ").append(makeHodinyMinuty(((i * 23) + minutesTime) + (i * 1))).toString());
                    iv2[i].setImageResource(R.drawable.iv_05);
                    break;
            }
            iv2[i].setId(i);
            iv2[i].setAdjustViewBounds(true);
            if (paddingForecast == 40) {
                iv2[i].setMaxHeight(25);
            } else {
                iv2[i].setMaxWidth(50);
            }
            tv2[i].setId(i);
            tv2[i].setGravity(3);
            tv2[i].setPadding(paddingForecast, 0, 0, 0);
            tv2_2[i].setId(i);
            tv2_2[i].setGravity(5);
            ll2[i].addView(iv2[i]);
            ll2[i].addView(tv2[i]);
            ll2[i].addView(tv2_2[i], new LayoutParams(-1, -2));
            ll2[i].setPadding(paddingForecast, 0, paddingForecast, 0);
            tomorrowForecast.addView(ll2[i]);
            if (j == 5) {
                j = 1;
            } else {
                j++;
            }
        }
    }

    public static int countToday() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(5);
        int presNoc = 0;
        if ((cal.get(11) * 60) + cal.get(12) < makeMinutes(makeSunRise(day, latitude, longitude)[0])) {
            presNoc = 1;
        }
        if (presNoc == 1) {
            return makeMinutes(makeSunRise(day - 1, latitude, longitude)[0]);
        }
        return makeMinutes(makeSunRise(day, latitude, longitude)[0]);
    }

    public static int countTomorrow() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(5);
        int presNoc = 0;
        if ((cal.get(11) * 60) + cal.get(12) < makeMinutes(makeSunRise(day, latitude, longitude)[0])) {
            presNoc = 1;
        }
        if (presNoc == 1) {
            return makeMinutes(makeSunRise(day, latitude, longitude)[0]);
        }
        return makeMinutes(makeSunRise(day + 1, latitude, longitude)[0]);
    }
}
