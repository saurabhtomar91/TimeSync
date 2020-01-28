package com.softdive.syncmanager.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.softdive.syncmanager.lib.SyncManager;
import com.softdive.syncmanager.lib.SyncProxy;

import static com.softdive.syncmanager.lib.SyncManager.Edit.enable;


public class MainActivity extends Activity {
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = (TextView) findViewById(R.id.result_text_view);
        Button syncNow = (Button) findViewById(R.id.sync_now_button);
        Button syncSoon = (Button) findViewById(R.id.sync_soon_button);
        final Button toggle = (Button) findViewById(R.id.toggle_enabled_button);

        final SyncProxy sync = SyncManager.get(this, RandomSync.class);

        syncNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sync.sync();
            }
        });
        syncSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sync.syncInexact();
            }
        });

        toggle.setText("Toggle Enabled (" + (sync.config().enabled() ? "Enabled" : "Disabled") + ")");
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sync.edit(enable(!sync.config().enabled()));
                toggle.setText("Toggle Enabled (" + (sync.config().enabled() ? "Enabled" : "Disabled") + ")");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(RandomSync.BROADCAST);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long result = intent.getLongExtra(RandomSync.EXTRA_RESULT, 0);
            resultTextView.setText("Result: " + result);
        }
    };
}
