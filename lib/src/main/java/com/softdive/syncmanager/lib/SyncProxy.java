package com.softdive.syncmanager.lib;

import android.content.Context;

import java.util.Arrays;

/**
 * A class for interacting with a {@link SyncManager}. You can get and set it's configuration, and
 * force it to sync immediately. Ta get an instance of the class for a given {@link SyncManager}, use
 * {@link SyncManager#get(android.content.Context, Class)}.
 */
public final class SyncProxy {
    private Context context;
    private String name;
    private SyncManager listener;

    SyncProxy(Context context, String name) {
        this.context = context;
        this.name = name;
        listener = SyncParser.parseListeners(context).get(name);
    }

    /**
     * Syncs immediately. This is useful for a response to a user action. Use this sparingly, as
     * frequent syncs defeat the purpose of using this library.
     */
    public void sync() {
        SyncService.sync(context, name);
    }

    /**
     * Syncs sometime in the near future, randomizing per device. This is useful in response to a
     * server message, using GCM for example, so that the server is not overwhelmed with all devices
     * trying to sync at once.
     */
    public void syncInexact() {
        SyncService.syncInexact(context, name);
    }

    /**
     * Gets the current configuration for the {@link SyncManager}.
     *
     * @return the configuration
     * @see SyncManager.Config
     */
    public SyncManager.Config config() {
        return listener.config();
    }

    /**
     * Modifies the current configuration for the {@link SyncManager}.
     *
     * @param edits the edits
     * @see SyncManager#edit(SyncManager.Edit...)
     */
    public void edit(Iterable<SyncManager.Edit> edits) {
        listener.edit(edits);
        SyncService.update(context, name);
    }

    /**
     * Modifies the current configuration for the {@link SyncManager}.
     *
     * @param edits the edits
     * @see SyncManager#edit(SyncManager.Edit...)
     */
    public void edit(SyncManager.Edit... edits) {
        edit(Arrays.asList(edits));
    }
}
