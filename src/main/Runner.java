package main;

import main.web.server.Server;

import java.io.IOException;

public class Runner {
    public static void main(String...args) throws IOException {
        System.out.println("APPLICATION STARTING");
        System.out.println("INITIALIZING ENDPOINTS");
        Server server = new Server();
        int port = 10;
        System.out.println("SERVER RUN ON PORT: " + port);
        server.start(port);
    }
}
