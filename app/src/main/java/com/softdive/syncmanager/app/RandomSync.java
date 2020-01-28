package com.softdive.syncmanager.app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import java.util.Random;

import com.softdive.syncmanager.lib.SyncManager;

public class RandomSync extends SyncManager {
    public static final String BROADCAST = RandomSync.class.getName();
    public static final String EXTRA_RESULT = "result";

    private Random random = new Random();

    @Override
    public void onSync(Context context) throws Exception {
        long result = random.nextLong();

//        if (random.nextInt(5) < 2) {
//            Log.d("SyncManager", "sync failed: " + System.currentTimeMillis());
//            throw new Exception();
//        }

        // Normally you would save to a database or file. For this example, it's just easier to
        // broadcast the result.
        Intent intent = new Intent(BROADCAST);
        intent.putExtra(EXTRA_RESULT, result);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
