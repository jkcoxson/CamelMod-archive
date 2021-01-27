package com.jkcoxson.camelmod;
// I hate java, I'd just like to let the world know that.
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.apache.commons.lang3.mutable.MutableObject;
import org.lwjgl.system.CallbackI;
import sun.tools.jstat.Literal;

import java.util.*;


public class Camelmod implements ModInitializer {
    static final MutableObject<MinecraftServer> serverReference = new MutableObject<MinecraftServer>();

    @Override
    public void onInitialize() {
        System.out.println("\n\nCamelMod initialized\n");

        ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> serverReference.setValue(minecraftServer));

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("coordprint").executes(context -> {

                String toSend = "coordprint\n";
                List<ServerPlayerEntity> players = serverReference.getValue().getPlayerManager().getPlayerList();
                for (int i = 0; i<players.size();i++){
                    String username = players.get(i).getName().toString().split(",")[0].split("\'")[1];
                    toSend = toSend + username;
                    toSend = toSend + ": ";
                    toSend = toSend + serverReference.getValue().getPlayerManager().getPlayer(username).getX();
                    toSend = toSend + ',';
                    toSend = toSend + serverReference.getValue().getPlayerManager().getPlayer(username).getY();
                    toSend = toSend + ',';
                    toSend = toSend + serverReference.getValue().getPlayerManager().getPlayer(username).getZ();
                    toSend = toSend + '\n';
                }
                toSend = toSend + "8675309\n";
                System.out.println(toSend);

                return 1;
            }));
            dispatcher.register(CommandManager.literal("playerlist").executes(context -> {

                String toSend = "playerlist";
                List<ServerPlayerEntity> players = serverReference.getValue().getPlayerManager().getPlayerList();
                for (int i = 0; i<players.size();i++){
                    String username = players.get(i).getName().toString().split(",")[0].split("\'")[1];
                    toSend = toSend + username;
                    toSend = toSend + '\n';
                }
                toSend = toSend + "8675309\n";
                System.out.println(toSend);

                return 1;
            }));
        });

    }


}