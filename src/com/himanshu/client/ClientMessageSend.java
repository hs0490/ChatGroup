package com.himanshu.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Created by hs0490 on 3/29/14.
 */
class ClientMessageSend extends Thread
{

    DatagramSocket clientSocket;

    String serverIPAddress;
    int serverPortNumber ;

    ClientMessageSend(DatagramSocket clientSocket, String serverIPAddress, int serverPortNumber){
        this.clientSocket = clientSocket;
        this.serverIPAddress = serverIPAddress;
        this.serverPortNumber = serverPortNumber;
        start();
    }

    public void run()
    {
        try
        {

            InetAddress ipAddress = InetAddress.getByName(serverIPAddress);

            while(true)
            {
                Scanner input = new Scanner(System.in);
                String message = input.nextLine();
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress,serverPortNumber);
                clientSocket.send(sendPacket);
                if (message.equals("End_of_Communication"))
                {
                    System.out.println("Chat closed....");
                    break;
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