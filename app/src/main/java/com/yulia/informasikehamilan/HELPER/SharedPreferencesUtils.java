package com.yulia.informasikehamilan.HELPER;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    private static final String SHARED_PREFS_NAME = "my_preferences";


    public static void setStringValue(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply(); // atau editor.commit();
    }

    public static String getStringValue(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        String defaultValue = ""; // Nilai default jika key tidak ditemukan
        return sharedPreferences.getString(key, defaultValue);
    }
}
