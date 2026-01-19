package org.serverutilstpa.plugin;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class TPEventData {
    public final Store<EntityStore> store;
    public final Ref<EntityStore> ref;
    public final World world;

    public TPEventData(Store<EntityStore> newStore, Ref<EntityStore> newRef, World newWorld) {
        this.store = newStore;
        this.ref = newRef;
        this.world = newWorld;
    }
}
