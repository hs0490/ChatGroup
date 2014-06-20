package com.himanshu.client;

import java.io.IOException;
import java.net.DatagramSocket;

public class Client {

    public static void main(String[] args) throws IOException
    {
        String serverIPAddress = "127.0.0.1";
        int serverPortNumber = 5000;

        System.out.println("Please Enter Following Commands:");
        System.out.println("To Start chat : <START>-NAME");
        System.out.println("To Connect Client : <CONNECT>-NAME");
        System.out.println("To List Online Clients : <LIST>");
        System.out.println("Type Message On Console To Send Message");
        System.out.println("To Exit Chat : <EXIT>");
        System.out.println("To offline : <OFFLINE>");
        System.out.println("=================TYPE MESSAGE BELOW=======================");

        System.out.println();

        DatagramSocket clientSocket = new DatagramSocket();
        new ClientMessageReceive(clientSocket,serverIPAddress,serverPortNumber);
        new ClientMessageSend(clientSocket,serverIPAddress,serverPortNumber);
    }
}

