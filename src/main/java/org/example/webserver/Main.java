package org.example.webserver;


import org.example.webserver.server.MyServer;

public class Main {

    public static void main(String[] args) {
        MyServer server = new MyServer(8080);
        server.start();
    }
}
