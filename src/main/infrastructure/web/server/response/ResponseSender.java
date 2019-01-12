package main.infrastructure.web.server.response;

import main.infrastructure.util.logger.Logger;
import main.infrastructure.web.server.http.HttpEndpoint;
import main.infrastructure.web.server.request.Request;
import main.infrastructure.web.endpoint.DeclaredEndpoint;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Optional;

import static main.infrastructure.web.endpoint.DeclaredEndpoint.getInstance;

public class ResponseSender {

    private DeclaredEndpoint endpoints = getInstance();
    private final static Logger LOGGER = new Logger(ResponseSender.class.getName());

    public void sendResponse(PrintWriter out, Request request, boolean validationResult) {
        handleValidation(validationResult);
        Response response = getResponseForEndpoint(request);
        send(out, response);
    }

    private Response getResponseForEndpoint(Request request){
        return findEndpointForPattern(request.getEndpoint())
                .map(httpEndpoint -> endpoints.getEndpoints()
                .get(httpEndpoint)
                .apply(request))
                .orElse(null);
    }

    private Optional<HttpEndpoint> findEndpointForPattern(HttpEndpoint endpoint){
        return endpoints.getEndpoints()
                .entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .filter(e -> e.equals(endpoint))
                .findFirst();
    }

    private void send(PrintWriter out, Response response){
        if(response == null) {
            out.print("HTTP/1.1 OK 200\r\n");
            out.print("Content-Type: text/html\r\n");
            out.print("\r\n");
            out.print("<h1>ERROR: NO RESPONSE FOR THIS ENDPOINT</h1>");
            LOGGER.error("ERROR: NO RESPONSE");
        }
        else {
            LOGGER.info("\nRESPONSE:\n" + response.toString());
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
