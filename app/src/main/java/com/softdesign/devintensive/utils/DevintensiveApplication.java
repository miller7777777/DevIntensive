package com.softdesign.devintensive.utils;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DevintensiveApplication extends Application{

    public static SharedPreferences sSharedPreferences;



    @Override
    public void onCreate() {
        super.onCreate();
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }


    public static Context getContext() {
        
        return null;

        // TODO: 12.07.2016 Здесь хрень какая-то... 
    }
}
