package com.himanshu.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by hs0490 on 3/29/14.
 */
 class ClientMessageReceive extends Thread {

    DatagramSocket clientSocket;

    String serverIPAddress;
    int serverPortNumber ;

     ClientMessageReceive(DatagramSocket clientSocket, String serverIPAddress, int serverPortNumber){
        this.clientSocket = clientSocket;
        this.serverIPAddress = serverIPAddress;
        this.serverPortNumber = serverPortNumber;
        start();
    }

    public void run(){
        try{
            while(true){
                byte[] receiveData = new byte[100];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String message = new String(receivePacket.getData(),0,receivePacket.getLength());
                if (message.contains("<LIST>")){
                    System.out.print("Clients Online : ");
                    System.out.println(message.substring(7));
                }
                else if (message.contains("<EXIT>")){
                    String clientName = message.substring(7);
                    System.out.println(clientName+" has exit the chat");
                }
                else if(message.contains("<OFFLINE>")){
                    System.out.println("Client is Offline");
                    break;
                }
                else{
                    int index = message.indexOf("-");
                    String clientName = message.substring(0,index);
                    String sentMessage = message.substring(index+1);
                    System.out.println(clientName+ ": "+sentMessage);
                }
            }
            clientSocket.close();
        }
        catch (SocketException e) {
            System.out.println("Socket Closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


