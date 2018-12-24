package main.web.server.response;

import main.web.server.request.Request;
import main.web.endpoint.Endpoint;

import java.io.PrintWriter;

import static main.web.endpoint.Endpoint.getInstance;

public class ResponseSender {

    private Endpoint endpoints = getInstance();

    public void sendResponse(PrintWriter out, Request request, boolean validationResult) {
        handleValidation(validationResult);
        Response response = getResponseForEndpoint(request);
        send(out, response);
    }

    private Response getResponseForEndpoint(Request request){
        System.out.println("REDIRECTING TO: " + request.getEndpoint());
        if(endpoints.getEndpoints().containsKey(request.getEndpoint()))
        return endpoints.getEndpoints()
                .get(request.getEndpoint())
                .apply(request);
        else
            return null;
    }

    private void send(PrintWriter out, Response response){
        if(response == null) {
            out.print("HTTP/1.1 OK 200\r\n");
            out.print("Content-Type: application/json\r\n");
            out.print("\r\n");
            out.print("ERROR");
        }
        else {
            System.out.println("\nRESPONSE:\n" + response.toString());
            out.print(response.toString());
        }
        out.flush();
    }

    private void handleValidation(boolean validationResult){
        if(!validationResult){
            throw new RuntimeException("VALIDATION FAILED");
        }
    }
}
