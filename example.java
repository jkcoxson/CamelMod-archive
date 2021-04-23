// Using /corndog will print all commands done in the second it was called
// by: Noah


package com.jkcoxson.camelmod;
// I hate java, I'd just like to let the world know that.
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import org.apache.commons.lang3.mutable.MutableObject;

import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;

@SuppressWarnings("ALL")
public class CommandReg {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getRootLogger();
    static final MutableObject<MinecraftServer> serverReference = new MutableObject<MinecraftServer>();

    public int RegisterCommands(){
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("corndog").executes(context -> {
                ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> serverReference.setValue(minecraftServer));
                // serverReference.getValue().getCommandManager().execute(serverReference.getValue().getCommandSource(), "/help");

                LOGGER.info("help");

                BufferedReader reader;
                LocalTime myTime = LocalTime.now();
                String data = "";
                try {
                    reader = new BufferedReader(new FileReader("./logs/latest.log"));
                    String line = reader.readLine();
                    String time = "";
                    String h = "" + myTime.getHour();
                    String m = "" + myTime.getMinute();
                    String s = "" + myTime.getSecond();
                    if (h.length() < 2) {
                        h = "0"+h;
                    }
                    if (m.length() < 2) {
                        m = "0"+m;
                    }
                    if (s.length() < 2) {
                        s = "0"+s;
                    }
                    time = time + h + ":" + m + ":" + s;

                    while (line != null) {
                        if (line.startsWith("[")){
                            if (line.contains(time)){
                                data += line;
                            }
                            line = reader.readLine();
                        } else {
                            data += line;
                            line = reader.readLine();
                        }
                    }
                    reader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                LOGGER.info(data);



                return 1;
            }));
        });
        return 0;
    }

    public java.lang.String getMessage(java.lang.String h){
        LOGGER.traceEntry();
        return LOGGER.traceExit(h);
    }
}
