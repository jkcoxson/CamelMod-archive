package com.jkcoxson.camelmod;

import java.net.*;
import java.io.*;
import org.json.JSONObject;

public class tcamelp {
    // initialize socket and input output streams
    static Socket           socket  = null;
    static DataInputStream  input   = null;
    static DataOutputStream out     = null;
    static BufferedReader in        = null;
    static String line = "";
    static Boolean systemAGo = true;
    // constructor to put ip address and port
    static void Connect(String address, int port)
    {
        new Thread(new Runnable() {
            public void run() {
                while (0==0){
                    // establish a connection

                    systemAGo = true;
                    line = "";
                    System.out.println("Attempting a connection to CamelBot.");
                    try
                    {
                        socket = new Socket(address, port);
                        System.out.println("Connected to CamelBot, activating now.");

                        // takes input from terminal
                        input  = new DataInputStream(System.in);

                        // sends output to the socket
                        out    = new DataOutputStream(socket.getOutputStream());
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                    }
                    catch(Exception e)
                    {
                        systemAGo = false;
                        System.out.println(e);
                    }

                    // string to read message from input
                    JSONObject keyReq = new JSONObject();
                    keyReq.put("key","yur mom");
                    try {
                        out.writeUTF(keyReq.toString());
                    }catch (Exception e){

                    }


                    while (systemAGo)
                    {
                        System.out.println("while loop");
                        try
                        {

                            line = in.readLine();
                            System.out.println(line);
                        }
                        catch(IOException i)
                        {
                            System.out.println(i);
                            System.out.println("Connection to CamelBot lost");
                            Disconnect();

                        }
                    }

                    // close the connection
                    if (systemAGo) {
                        try {
                            input.close();
                            out.close();
                            socket.close();
                        } catch (IOException i) {
                            System.out.println(i);
                        }
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }
        }).start();

    }
    
    static void Disconnect(){
        System.out.println("Closing all sockets to CamelBot, standby.");
        new Thread(new Runnable() {
            public void run() {
                systemAGo=false;
                try
                {
                    input.close();
                    out.close();
                    socket.close();
                    in.close();
                    Socket           socket  = null;
                     DataInputStream  input   = null;
                     DataOutputStream out     = null;
                    DataInputStream  in      = null;
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                line = "Over";
            }
        }).start();
    }

}
