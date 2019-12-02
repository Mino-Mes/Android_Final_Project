package com.example.finalproject.Util;

import android.app.Activity;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Activity activity)
    {
        this.preferences =activity.getPreferences(activity.MODE_PRIVATE);
    }

    public void saveRadioCheck(String check)
    {
        preferences.edit().putString("radioCheck",check).apply();
    }
    public String getCheck(){
        return preferences.getString("radioCheck","");
    }
}