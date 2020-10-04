package com.bz2.example.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private final String PREFS_NAME = "_pref_name";
    private final String LANGUAGE_CODE = "_language_code";
    private final String AUTH_TOKEN = "_auth_token";
    private final String CAMERA_PERMISSION = "_camera_permission";
    private final String LOGIN_METHOD = "_login_mothod";
    private final String PUSH = "_push";
    private final String FCM_TOKEN = "_fcm_token";

    private final String EMAIL = "_email";

    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;
    private Context context;

    public PreferenceManager(Context context){
        this.context = context;
        this.sharedPreference = this.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void clearData(){
        this.editor = this.sharedPreference.edit();
        this.editor.clear();
        this.editor.apply();
    }

    public void setLanguageCode(String code){
        this.editor = this.sharedPreference.edit();
        this.editor.putString(LANGUAGE_CODE, code);
        this.editor.apply();
    }

    public String getLanguageCode(){
        return this.sharedPreference.getString(LANGUAGE_CODE, null);
    }
}
