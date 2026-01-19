package org.serverutilstpa.plugin;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import org.serverutilstpa.plugin.commands.CmdTPA;
import org.serverutilstpa.plugin.commands.CmdTPAccept;
import org.serverutilstpa.plugin.events.ExampleEvent;

import javax.annotation.Nonnull;
import java.util.Hashtable;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class Plugin extends JavaPlugin {
    public static Hashtable<PlayerRef, TPEventData> tpRequests = new Hashtable();

    public Plugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    protected void setup() {
        this.getCommandRegistry().registerCommand(new CmdTPA("tpa", "The TPA command"));
        this.getCommandRegistry().registerCommand(new CmdTPAccept());
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEvent::onPlayerReady);
    }
}
