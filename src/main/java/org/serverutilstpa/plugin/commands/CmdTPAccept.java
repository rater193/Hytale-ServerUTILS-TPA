package org.serverutilstpa.plugin.commands;

import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.serverutilstpa.plugin.Plugin;
import org.serverutilstpa.plugin.TPEventData;

public class CmdTPAccept extends CommandBase {
    public CmdTPAccept() {
        super("tpaccept", "Accepts a teleport request");
    }

    protected void executeSync(@NonNullDecl CommandContext context) {
        PlayerRef playerToTeleportTo = Universe.get().getPlayer(context.sender().getUuid());
        if (Plugin.tpRequests.containsKey(playerToTeleportTo)) {
            TPEventData data = (TPEventData)Plugin.tpRequests.get(playerToTeleportTo);
            World targetWorld = Universe.get().getWorld(playerToTeleportTo.getWorldUuid());
            targetWorld.execute(() -> {
                Teleport teleport = new Teleport(targetWorld, playerToTeleportTo.getTransform().getPosition(), new Vector3f(0.0F, 0.0F, 0.0F));
                data.store.addComponent(data.ref, Teleport.getComponentType(), teleport);
                context.sendMessage(Message.raw("Teleporting to " + playerToTeleportTo.getUsername()));
            });
            Plugin.tpRequests.remove(playerToTeleportTo);
        }

    }

    public boolean hasPermission(@NonNullDecl CommandSender sender) {
        return true;
    }
}