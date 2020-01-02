package com.practice.netdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket dSocket= new DatagramSocket();
        dSocket.setSoTimeout(1000);
        dSocket.connect(InetAddress.getByName("localhost"), 6666);

        byte[] data = "Hello".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length);
        dSocket.send(packet);

        byte[] buffer =new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        dSocket.receive(packet);
        String resp = new String(packet.getData(),packet.getOffset(),packet.getLength());
        System.out.println(resp);
        dSocket.disconnect();
    }
}