package com.jkcoxson.camelmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Camelmod implements ModInitializer {



    @Override
    public void onInitialize() {



        CommandReg command = new CommandReg();
        command.RegisterCommands();
        tcamelp.Connect("jkcoxson.com",42069);
    }
    
}
