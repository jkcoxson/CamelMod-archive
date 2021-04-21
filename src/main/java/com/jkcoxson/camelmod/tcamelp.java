package com.jkcoxson.camelmod;

import java.net.*;
import java.io.*;

public class tcamelp {
    // initialize socket and input output streams
    private Socket           socket  = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    private DataInputStream  in      = null;

    // constructor to put ip address and port
    public void Connect(String address, int port)
    {
        new Thread(new Runnable() {
            public void run() {
                // establish a connection
                try
                {
                    socket = new Socket(address, port);
                    System.out.println("Connected");

                    // takes input from terminal
                    input  = new DataInputStream(System.in);

                    // sends output to the socket
                    out    = new DataOutputStream(socket.getOutputStream());
                    in     = new DataInputStream(socket.getInputStream());


                }
                catch(UnknownHostException u)
                {
                    System.out.println(u);
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
                //out.writeUTF();

                // string to read message from input
                String line = "";

                // keep reading until "Over" is input
                while (!line.equals("Over"))
                {
                    try
                    {

                        line = in.readUTF();
                        out.writeUTF(line);
                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                    }
                }

                // close the connection
                try
                {
                    input.close();
                    out.close();
                    socket.close();
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
        }).start();

    }
    
    public void Disconnect(){
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

}
