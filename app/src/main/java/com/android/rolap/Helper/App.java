package com.android.rolap.Helper;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class App extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
