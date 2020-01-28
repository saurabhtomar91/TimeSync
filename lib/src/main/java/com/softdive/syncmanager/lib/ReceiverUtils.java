package com.softdive.syncmanager.lib;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import static android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
import static android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
import static android.content.pm.PackageManager.DONT_KILL_APP;

class ReceiverUtils {
    static <T extends BroadcastReceiver> void enable(Context context, Class<T> receiverClass) {
        ComponentName receiver = new ComponentName(context, receiverClass);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, COMPONENT_ENABLED_STATE_ENABLED, DONT_KILL_APP);
    }

    static <T extends BroadcastReceiver> void disable(Context context, Class<T> receiverClass) {
        ComponentName receiver = new ComponentName(context, receiverClass);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, COMPONENT_ENABLED_STATE_DISABLED, DONT_KILL_APP);
    }
}
