package com.softdive.syncmanager.app;

import android.app.Application;

import com.softdive.syncmanager.lib.SyncManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SyncManager.start(this);
    }
}
