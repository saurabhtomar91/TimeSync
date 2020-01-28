package com.softdive.syncmanager.lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SyncBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SyncService.start(context);
    }

    public static void enable(Context context) {
        ReceiverUtils.enable(context, SyncBootReceiver.class);
    }

    public static void disable(Context context) {
        ReceiverUtils.disable(context, SyncBootReceiver.class);
    }
}
