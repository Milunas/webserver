package main;

import main.infrastructure.web.server.Server;

public class WebServerRunner {
    public static void main(String...args) throws Exception {
        System.out.println("APPLICATION STARTING");
        System.out.println("INITIALIZING ENDPOINTS");
        Server server = new Server();
        int port = 10;
        System.out.println("SERVER RUN ON PORT: " + port);
        server.start(port);
    }
}
