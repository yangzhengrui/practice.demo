package com.practice.netdemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client{
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("localhost",6666);
        try (InputStream input =socket.getInputStream()) {
            try(OutputStream output = socket.getOutputStream()){
                handle(input, output);
            }
        }
        socket.close();
        System.out.println("disconnected.");
    }

    public static void handle(InputStream input, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output,StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input,StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        System.out.println("[server] "+ reader.readLine());
        for(;;){
            System.out.println(">>>");
            String s = scanner.nextLine();
            writer.write(s);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<<"+resp);
            if (resp.equals("bye")) {
                break;
            }
        }

    }
}