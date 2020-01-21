package ro.atelieruldigital.news.utils;

import android.content.Context;
import android.content.SharedPreferences;

import ro.atelieruldigital.news.App;

public class PrefUtils {

    private static final String PREF_USER = "PREF_USER";
    private static final String DEFAULT_STRING_VALUE = "";
    private static final String PREF_USER_LIST = "PREF_USER_LIST";

    private static SharedPreferences getSharedPreferencesInstance(Context context) {
        return context.
                getSharedPreferences("ro.atelieruldigital.news.utils.preference_file_key", Context.MODE_PRIVATE);
    }


    public static void setUser (Context context, String user) {
        getSharedPreferencesInstance(context).edit().putString(PREF_USER, user).apply();
    }

    public static String getUser (Context context) {
        return getSharedPreferencesInstance(context).getString(PREF_USER, DEFAULT_STRING_VALUE);
    }

    public static void setUserPreferences (Context context, String userPreferences) {
        getSharedPreferencesInstance(context).edit().putString(PREF_USER_LIST, userPreferences).apply();
    }

    public static String getUserPreferences (Context context) {
        return getSharedPreferencesInstance(context).getString(PREF_USER_LIST, DEFAULT_STRING_VALUE);
    }
}
