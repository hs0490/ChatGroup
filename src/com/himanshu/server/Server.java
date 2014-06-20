package com.himanshu.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;


/**
 * Created by hs0490 on 3/28/14.
 */
public class Server implements Runnable{

    private static int serverPort = 5000;

    static DatagramSocket  serverSocket;

    static Hashtable<String,Integer> onlineClients;
    static Hashtable<Integer, ArrayList<Integer>> chatRoom;

    static InetAddress ipAddress;

    DatagramPacket receivePacket;

    public Server(DatagramPacket receivePacket){
        this.receivePacket = receivePacket;

    }

    public static void main(String args[]) throws IOException {
        onlineClients = new Hashtable<String,Integer>();
        chatRoom = new Hashtable<Integer, ArrayList<Integer>>();
        ipAddress = InetAddress.getByName("127.0.0.1");
        serverSocket = new DatagramSocket(serverPort);
        System.out.println("Waiting for Clients:");
        System.out.println();
        while(true){
            byte[] receiveData = new byte[100];
            DatagramPacket receivePacketOne = new DatagramPacket(receiveData,receiveData.length);
            serverSocket.receive(receivePacketOne);
            Server aServer = new Server(receivePacketOne);
            Thread one = new Thread(aServer);
            one.start();
        }

    }
    public void run(){
        try{

            String message = new String(receivePacket.getData(),0,receivePacket.getLength());

            if(message.contains("<START>")){
                String clientName = message.substring(8);
                onlineClients.put(clientName,receivePacket.getPort());
                System.out.println(clientName + " is Online");
            }
            else if(message.contains("<CONNECT>"))
                connect(message);

            else if(message.equals("<LIST>")){
                //Send datagram to client
                String clientList = "<LIST>-"+onlineClients.keySet().toString();
                byte[] sendData = clientList.getBytes();

                //Creating Datagram Packet
                DatagramPacket aPacket = new DatagramPacket(sendData,sendData.length,ipAddress,receivePacket.getPort());
                serverSocket.send(aPacket);
            }

            else if(message.equals("<OFFLINE>")){

                if(chatRoom.contains(receivePacket.getPort()))
                    exitChat();

                //Send datagram to client
                String status = "<OFFLINE>";
                byte[] sendData = status.getBytes();

                //Creating Datagram Packet
                DatagramPacket aPacket = new DatagramPacket(sendData,sendData.length,ipAddress,receivePacket.getPort());
                serverSocket.send(aPacket);

                // Removing form online Client;
                String name = getClientName(receivePacket.getPort());
                onlineClients.remove(name);

                System.out.println(name + " is Offline");

            }

            else if(message.equals("<EXIT>"))
                exitChat();

            else
                sendPacket();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(String message){
        String clientName = message.substring(10);
        int portNumber = onlineClients.get(clientName);
        ArrayList<Integer> chatClients;

        if(chatRoom.containsKey(receivePacket.getPort()))
            chatClients = chatRoom.get(receivePacket.getPort());
        else{
            chatClients = new ArrayList<Integer>();
            chatRoom.put(receivePacket.getPort(),chatClients);
        }
        chatClients.add(portNumber);
    }

    public void sendPacket() throws IOException {

        int sendingClientPort = receivePacket.getPort();
        ArrayList<Integer> chatClients = chatRoom.get(sendingClientPort);
        Iterator aIterator = chatClients.iterator();

        while(aIterator.hasNext()){
           int receivingClientPort = (Integer) aIterator.next();

            if(receivingClientPort != sendingClientPort){
                //Send datagram to client
                String clientName = getClientName(sendingClientPort);
                String sendMessage = new String(receivePacket.getData(),0,receivePacket.getLength());
                String sendMessageCommand = clientName.concat("-").concat(sendMessage);
                byte[] sendData = sendMessageCommand.getBytes();

                //Creating Datagram Packet
                DatagramPacket aPacket = new DatagramPacket(sendData,sendData.length,ipAddress,receivingClientPort);
                serverSocket.send(aPacket);

            }
        }

    }

    public void exitChat() throws IOException {

        int sendingClientPort = receivePacket.getPort();
        ArrayList<Integer> chatClients = chatRoom.get(sendingClientPort);
        Iterator aIterator = chatClients.iterator();

        while(aIterator.hasNext()){
            int receivingClientPort = (Integer) aIterator.next();


            if(receivingClientPort != sendingClientPort){
                //Send datagram to client
                String clientName = getClientName(receivePacket.getPort());
                String message = "<EXIT>-"+clientName;
                byte[] sendData = message.getBytes();

                //Creating Datagram Packet
                DatagramPacket aPacket = new DatagramPacket(sendData,sendData.length,ipAddress,receivingClientPort);
                serverSocket.send(aPacket);
            }
        }

        //Removing Client from CHATROOM
        chatRoom.remove(receivePacket.getPort());

        // removing Client for ChatClients
        for ( int key : chatRoom.keySet() ) {
            ArrayList<Integer> chatClient = chatRoom.get(key);
            Object port = receivePacket.getPort();
            chatClient.remove(port);
        }

    }

    public String getClientName( int receivingClientPort){

        for ( String key : onlineClients.keySet() ) {
            int clientPort = onlineClients.get(key);
            if(clientPort == receivingClientPort)
                return key;
        }
      return null;
    }


}












