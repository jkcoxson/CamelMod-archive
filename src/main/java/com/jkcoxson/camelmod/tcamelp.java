package com.jkcoxson.camelmod;

import java.net.*;
import java.io.*;

import com.google.gson.JsonObject;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.parser.Parser;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.realms.util.JsonUtils;
import com.google.gson.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Locale;

public class tcamelp {
    // initialize socket and input output streams
    public static Socket           socket  = null;
    static DataOutputStream out     = null;
    static BufferedReader in        = null;
    static Path dataFolder;

    public static Boolean camalized = false;
    // constructor to put ip address and port
    static void Connect(String address, int port)
    {
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    // Load up the key and attempt to send it off
                    dataFolder = FabricLoader.getInstance().getConfigDir().resolve("CamelMod");
                    if (!dataFolder.toFile().exists()) {
                        dataFolder.toFile().mkdir();

                    }
                    File keyfile = dataFolder.resolve("key.txt").toFile();
                    String key = "";

                    try{
                        BufferedReader br = new BufferedReader(new FileReader(keyfile));
                        key = br.readLine();
                    }catch(Exception e){

                    }

                    System.out.println("Connecting to CamelBot");
                    try {
                        socket = new Socket("jkcoxson.com",42069);
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out = new DataOutputStream(socket.getOutputStream());
                        System.out.println("Connected to CamelBot");
                        Yeet("{\"packet\":\"key\",\n\"key\":\""+key+"\"}");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    Boolean go = true;
                    while (go) {
                        try {
                            String response = in.readLine();
                            if (response==null){
                                go=false;
                                Disconnect();
                            }else{
                                System.out.println("\n" + response);
                                if (response.equals("Connection verified")){
                                    System.out.println("Received key confirmation, we're in!");
                                    camalized = true;
                                }
                                // Start parse JSON pls

                                try{
                                    Gson gson = new Gson();
                                    // How the heck does this work? Thanks internet, Java is the worst
                                    JsonObject packet = gson.fromJson(response, JsonObject.class);
                                    String packetType = packet.get("packet").getAsString(); // Yeah, that makes sense NOT.
                                    System.out.println(packetType);
                                    if (packetType.equals("command")){
                                        
                                    }
                                    if (packetType.equals("players")){

                                    }
                                    if (packetType.equals("whitelist-add")){

                                    }
                                    if (packetType.equals("whitelist-remove")){

                                    }
                                    if (packetType.equals("whitelist-list")){

                                    }

                                }catch(Exception e){
                                    System.out.println(e);
                                }

                            }
                        } catch (Exception e) {
                            go = false;
                            Disconnect();
                        }
                        try{
                            Thread.sleep(3000);
                        }catch(Exception e){
                            System.out.println(e);
                        }
                    }
                    
                }

            }
        }).start();

    }
    
    static void Disconnect(){
        System.out.println("Closing all sockets to CamelBot");
        new Thread(new Runnable() {
            public void run() {
                camalized=false;
                try
                {
                    out.close();
                }
                catch(IOException i)
                {
                    //System.out.println(i);
                }
                try
                {
                    socket.close();
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
                try
                {
                    in.close();
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
        }).start();
    }


    public static void Yeet(String input){
        new Thread(new Runnable() {
            public void run() {
                if (socket.isConnected()){
                    try {
                        out.writeUTF(input);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        }).start();
    }

}
