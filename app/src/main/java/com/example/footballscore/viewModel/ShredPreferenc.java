package com.example.footballscore.viewModel;

import android.content.Context;
import android.content.SharedPreferences;

public class ShredPreferenc {
    static final String PREF_NAME = "Login";

    public void save(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void saveBoolean(Context context, String key, Boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public String getValue(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        return settings.getString(key, null);
    }

    public Boolean getValueBoolean(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, false);
    }

    public void clear(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }

    public void remove(Context context, String key) {

        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }
}
