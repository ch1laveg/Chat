package com.chat.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Maksim on 11.02.2017.
 */
public class Client implements Runnable{

    private InputStream inputStream;
    private OutputStream outputStream;
    private boolean alive = true;
    private String username = "defName";

    Client(InputStream is, OutputStream os) {
        this.inputStream = is;
        this.outputStream = os;
    }

    public void run() {
        Scanner scanner = new Scanner(inputStream);
        PrintWriter pw = new PrintWriter(outputStream);

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public String toString() {
        return username;
    }
}
