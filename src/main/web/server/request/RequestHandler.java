package main.web.server.request;

import main.web.server.response.ResponseSender;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestHandler extends Thread{

    private Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;

    public RequestHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
    }

    public void run() {
        try {
            handleRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void handleRequest() throws IOException {
        Request request = createRequest();
        boolean validationResult = validateRequest(request);
        sendResponse(request, validationResult);
        in.close();
        out.close();
        clientSocket.close();
    }

    private Request createRequest() throws IOException {
        RequestCreator creator = new RequestCreator();
        return creator.create(in);
    }

    private boolean validateRequest(Request request){
        RequestValidator validator = new RequestValidator();
        return validator.validate(request);
    }

    private void sendResponse(Request request, boolean validationResult){
        ResponseSender sender = new ResponseSender();
        sender.sendResponse(out, request, validationResult);
    }
}