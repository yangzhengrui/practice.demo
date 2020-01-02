package com.practice.netdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket dSocket = new DatagramSocket(6666);
        for(;;){
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            dSocket.receive(packet);
            String s =new String(packet.getData(),packet.getOffset(),packet.getLength(),StandardCharsets.UTF_8);
            System.out.println("server:"+s);
            byte[] data = "ACK".getBytes(StandardCharsets.UTF_8);
            packet.setData(data);
            dSocket.send(packet);
        }
    }
}