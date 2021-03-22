package com.example.footballscore.viewModel;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

public class ShredPreferenc {
    static final String PREF_NAME = "Login";

    public void save(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void saveBoolean(Context context, String key, Boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getValue(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        String text = settings.getString(key, null);
        return text;
    }

    public Boolean getValueBoolean(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        Boolean text = settings.getBoolean(key, false);
        return text;
    }

    public void clear(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void remove(Context context, String key) {

        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }
}
