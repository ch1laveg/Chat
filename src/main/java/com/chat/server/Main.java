package com.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Maksim on 11.02.2017.
 */
public class Main {

    public static void main(String args[]) throws IOException {

        System.out.println("Strating server...");
        ServerSocket server = Server.createServerSocket(8080);
        System.out.println("Server started.");

        while (true){
            Socket socket = server.accept();
            Server.handleSocket(socket);
        }
    }

}
