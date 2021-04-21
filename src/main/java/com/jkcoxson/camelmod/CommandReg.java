package com.jkcoxson.camelmod;
// I hate java, I'd just like to let the world know that.
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.apache.commons.lang3.mutable.MutableObject;
import org.lwjgl.system.CallbackI;
import java.util.*;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import com.jkcoxson.camelmod.tcamelp;

public class CommandReg {
    static final MutableObject<MinecraftServer> serverReference = new MutableObject<MinecraftServer>();
    public int RegisterCommands(){
        ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> serverReference.setValue(minecraftServer));

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("corndog").executes(context -> {
                System.out.println("yeet");
                String str = "list\nsay cool\n";
                InputStream i = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
                System.setIn(i);  
                return 1;

            }));
        });
        return 0;
    }
}
