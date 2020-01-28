package com.softdive.syncmanager.lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SyncNetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            disable(context);
            SyncService.networkBack(context);
        }
    }

    static void enable(Context context) {
        ReceiverUtils.enable(context, SyncNetworkReceiver.class);
    }

    static void disable(Context context) {
        ReceiverUtils.disable(context, SyncNetworkReceiver.class);
    }
}
