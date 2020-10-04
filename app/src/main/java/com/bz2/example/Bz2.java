package com.bz2.example;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import java.util.Locale;

public class Bz2 extends Application {
    private boolean login;

    public String BASE_URL = " ";

    public void setLanguage(String language){
        String code = "ko";

        Locale locale = new Locale(code);
        if( locale != null ){
            Log.d("BZ2", "locale = " + locale.getLanguage());

            Locale.setDefault(locale);

            Configuration config = getBaseContext().getResources().getConfiguration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }
}
