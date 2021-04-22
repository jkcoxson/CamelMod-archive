package com.jkcoxson.camelmod;

import net.fabricmc.api.ModInitializer;

public class Camelmod implements ModInitializer {
    @Override
    public void onInitialize() {
        dataFolder = FabricLoader.getInstance().getConfigDir().resolve("Geyser-Fabric");
        CommandReg command = new CommandReg();
        command.RegisterCommands();
        tcamelp.Connect("jkcoxson.com",42069);
    }
    
}
