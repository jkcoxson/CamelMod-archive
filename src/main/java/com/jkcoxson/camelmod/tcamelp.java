package com.jkcoxson.camelmod;

import java.net.*;
import java.io.*;
import org.json.JSONObject;

public class tcamelp {
    // initialize socket and input output streams
    static Socket           socket  = null;
    static DataOutputStream out     = null;
    static BufferedReader in        = null;
    // constructor to put ip address and port
    static void Connect(String address, int port)
    {
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    System.out.println("Connecting to CamelBot");
                    try {
                        socket = new Socket("jkcoxson.com",42069);
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out = new DataOutputStream(socket.getOutputStream());
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

}
