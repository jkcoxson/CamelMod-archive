package com.jkcoxson.camelmod;

import net.fabricmc.api.ModInitializer;
import com.jkcoxson.camelmod.CommandReg;

public class Camelmod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommandReg command = new CommandReg();
        command.RegisterCommands();
    }
    
}
