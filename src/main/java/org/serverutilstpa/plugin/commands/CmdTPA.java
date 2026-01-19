package org.serverutilstpa.plugin.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.serverutilstpa.plugin.Plugin;
import org.serverutilstpa.plugin.TPEventData;

import javax.annotation.Nonnull;

public class CmdTPA extends AbstractPlayerCommand {
    @Nonnull
    private final RequiredArg<PlayerRef> playerArg;

    public CmdTPA(String name, String description) {
        super(name, description);
        this.playerArg = this.withRequiredArg("player", "The player to send a request to", ArgTypes.PLAYER_REF);
    }

    protected void execute(@NonNullDecl CommandContext context, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        if (this.playerArg.provided(context)) {
            PlayerRef player2 = Universe.get().getPlayer(((PlayerRef)this.playerArg.get(context)).getUuid());
            player2.sendMessage(Message.raw(context.sender().getDisplayName() + " wants to teleport to you. type /tpaccept to accept the request."));
            Plugin.tpRequests.put(player2, new TPEventData(store, ref, world));
        }

    }

    public boolean hasPermission(@NonNullDecl CommandSender sender) {
        return true;
    }
}
