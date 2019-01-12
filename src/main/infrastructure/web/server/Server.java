package main.infrastructure.web.server;

import main.infrastructure.web.server.request.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("APPLICATION STARTED");
        while(true) new RequestHandler(serverSocket.accept()).start();
    }

    public void stop() throws IOException{
        serverSocket.close();
    }
}
