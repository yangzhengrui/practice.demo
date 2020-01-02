package com.practice.netdemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server{
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("server is running...");
        for(;;){
            Socket socket = serverSocket.accept();
            System.out.println("connected from "+socket.getRemoteSocketAddress());
            Thread t = new Handler(socket);
            t.start();
        }
    }
}

class Handler extends Thread{
    Socket socket;

    public Handler(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run(){
        try (InputStream input = this.socket.getInputStream()){
            try (OutputStream output = this.socket.getOutputStream()){
                handle(input,output);
            }
        } catch (Exception e) {
            try {
                this.socket.close();
            } catch (IOException ioe) {
                //TODO: handle exception
            }
            System.out.println("client disconnected.");
        }
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output,StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input,StandardCharsets.UTF_8));
        writer.write("hello\n");
        writer.flush();
        for(;;){
            String s =reader.readLine();
            if (s.equals("bye")) {
                writer.write("bye\n");
                writer.flush();
                break;
            }
            writer.write("ok: "+s+"\n");
            writer.flush();
        }
    }

}