package com.softdive.syncmanager.lib;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class SyncPowerReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean connected = false;
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            connected = true;
        } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            connected = false;
        }
        startWakefulService(context, SyncService.getPowerChangedIntent(context, connected));
    }

    static void enable(Context context) {
        ReceiverUtils.enable(context, SyncPowerReceiver.class);
    }

    static void disable(Context context) {
        ReceiverUtils.disable(context, SyncPowerReceiver.class);
    }
}
