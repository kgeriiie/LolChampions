package com.prosupport.lolchampions;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static String getStringRes(int res) {
        if (context != null) {
            return context.getString(res);
        }

        return "";
    }
}
