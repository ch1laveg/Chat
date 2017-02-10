package com.chat.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Maksim on 11.02.2017.
 */
public class Server {

    private static final List<Client> clients = new ArrayList<Client>();

    private Server() {
    }

    static ServerSocket createServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }

    static void handleSocket(Socket socket) throws IOException {
        Client client = new Client(socket.getInputStream(), socket.getOutputStream());
        Thread t = new Thread(client);
        t.start();
        clients.add(client);
        System.out.println("Client connected " + client);
    }

    private static class AliveChecker implements Runnable {

        public void run() {
            while (true) {
                Iterator iterator = clients.iterator();
                while (iterator.hasNext()) {
                    Client c = (Client) iterator.next();
                    if (!c.isAlive()) {
                        clients.remove(c);
                        System.out.println("Client disconnected " + c);
                    }
                }
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

}
