package com.example.myfirstapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.myfirstapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    public static final String REFRESH_ACTION = "com.packagename.REFRESH_ACTION";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean useLocation = sharedPrefs.getBoolean("PrefGps", false);
        double latitude = Double.parseDouble(sharedPrefs.getString("PrefLatitude", "0.0"));
        double longitude = Double.parseDouble(sharedPrefs.getString("PrefLongitude", "0.0"));
        int settingsTimeDifference = Integer.parseInt(sharedPrefs.getString("PrefTimeDifference", "0"));

        System.out.println("useLocation: " +  useLocation);
        System.out.println("latitude: " +  latitude);
        System.out.println("longitude: " +  longitude);
        System.out.println("settingsTimeDifference: " +  settingsTimeDifference);

        if(!useLocation){
            latitude = -33.444215;
            longitude = -70.633427;
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, settingsTimeDifference);
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
        } else {
            timeSunRise = new StringBuilder(String.valueOf(makeHodinyMinuty(((int) SecRise) / 60))).append(":").append(makeSekundy(((int) SecRise) % 60)).toString();
            timeSunSet = new StringBuilder(String.valueOf(makeHodinyMinuty(((int) SecSet) / 60))).append(":").append(makeSekundy(((int) SecSet) % 60)).toString();
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
        int nextTatva;
        if (k == 7) {
            nextTatva = 1;
        } else {
            nextTatva = k + 1;
        }
        String tNextTattva  = jakaJeTatva(nextTatva, context);
        String tTattva = jakaJeTatva(k, context);

        int Bezi = (((i * 23) + minutesTime) + (i * 1)) - curTime;
        String tTatva1 = context.getResources().getString(R.string.tatvaEnd1);
        String tTimeRemain = new StringBuilder(
                String.valueOf(tTatva1)).append(" ").append(Bezi).append(" min.").toString();
        if (Bezi == 0) {
            tTimeRemain = context.getResources().getString(R.string.tatvaEnd2);
        }

        int tattvicClock = 0;
        int tattvicPoint = 0;
        switch (k) {
            case 1:
                tattvicClock = R.drawable.c1;
                tattvicPoint = R.drawable.b1;
                tTimeRemain = "-Akaza meditar solamente";
                break;
            case 2:
                tattvicClock = R.drawable.c2;
                tattvicPoint = R.drawable.b2;
                tTimeRemain = "-Vayu velocidad y movimiento negocios sencillos y rapidos son buenos";
                break;
            case 3:
                tattvicClock = R.drawable.c3;
                tattvicPoint = R.drawable.b3;
                tTimeRemain = "-Tejas trabajar intensamente, no discutir no casese en esta hora";
                break;
            case 4:
                tattvicClock = R.drawable.c4;
                tattvicPoint = R.drawable.b4;
                tTimeRemain = "-Prithivi exito en los negocios, buena salud coma y beba y casarse, negocios y citas";
                break;
            case 5:
                tattvicClock = R.drawable.c5;
                tattvicPoint = R.drawable.b5;
                tTimeRemain = "-Apas compra de mercancias, negocios, viajes en agua es bueno, y loterias";
                break;
            case 6:
                tattvicClock = R.drawable.c5;
                tattvicPoint = R.drawable.b5;
                tTimeRemain = "-Adi ";
                break;
            case 7:
                tattvicClock = R.drawable.c5;
                tattvicPoint = R.drawable.b5;
                tTimeRemain = "-Samadhi ";
                break;
        }

        int handPosition = 24 - Bezi;
        System.out.println("Hand position ->" + handPosition);
        System.out.println("Time" + cal.getTime());
        int tattvicHands = 0;
        switch (handPosition) {
            case 0:
                tattvicHands = R.drawable.ch1;
                break;
            case 1:
                tattvicHands = R.drawable.ch2;
                break;
            case 2:
                tattvicHands = R.drawable.ch3;
                break;
            case 3:
                tattvicHands = R.drawable.ch4;
                break;
            case 4:
                tattvicHands = R.drawable.ch5;
                break;
            case 5:
                tattvicHands = R.drawable.ch6;
                break;
            case 6:
                tattvicHands = R.drawable.ch7;
                break;
            case 7:
                tattvicHands = R.drawable.ch8;
                break;
            case 8:
                tattvicHands = R.drawable.ch9;
                break;
            case 9:
                tattvicHands = R.drawable.ch10;
                break;
            case 10:
                tattvicHands = R.drawable.ch11;
                break;
            case 11:
                tattvicHands = R.drawable.ch12;
                break;
            case 12:
                tattvicHands = R.drawable.ch13;
                break;
            case 13:
                tattvicHands = R.drawable.ch14;
                break;
            case 14:
                tattvicHands = R.drawable.ch15;
                break;
            case 15:
                tattvicHands = R.drawable.ch16;
                break;
            case 16:
                tattvicHands = R.drawable.ch17;
                break;
            case 17:
                tattvicHands = R.drawable.ch18;
                break;
            case 18:
                tattvicHands = R.drawable.ch19;
                break;
            case 19:
                tattvicHands = R.drawable.ch20;
                break;
            case 20:
                tattvicHands = R.drawable.ch21;
                break;
            case 21:
                tattvicHands = R.drawable.ch22;
                break;
            case 22:
                tattvicHands = R.drawable.ch23;
                break;
            case 23:
                tattvicHands = R.drawable.ch24;
                break;
            case 24:
                tattvicHands = R.drawable.ch24;
                break;
        }

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        views.setTextViewText(R.id.tSunRise, timeSunRise);
        //views.setTextViewText(R.id.tSunSet, timeSunSet);
        views.setTextViewText(R.id.tSunSet, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(cal.getTime()));
        views.setTextViewText(R.id.tTattva, tTattva);
        views.setTextViewText(R.id.tNextTattva, tNextTattva);
        views.setTextViewText(R.id.tRemain, tTimeRemain);
        views.setImageViewResource(R.id.imageView1, tattvicClock);
        views.setImageViewResource(R.id.imageView3, tattvicPoint);
        views.setImageViewResource(R.id.imageView2, tattvicHands);

        //Refresh
        Intent refreshIntent = new Intent(context, NewAppWidget.class);
        refreshIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        refreshIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[]{appWidgetId});
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, appWidgetId, refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.imageView3, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

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

    public static String jakaJeTatva(int k, Context context) {
        switch (k) {
            case 1:
                return context.getString(R.string.tat1);
            case 2:
                return context.getString(R.string.tat2);
            case 3:
                return context.getString(R.string.tat3);
            case 4:
                return context.getString(R.string.tat4);
            case 5:
                return context.getString(R.string.tat5);
            case 6:
                return context.getString(R.string.tat6);
            case 7:
                return context.getString(R.string.tat7);
            default:
                return "Náda";
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them.
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

}

