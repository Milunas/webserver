package main.infrastructure.web.server.request;

import main.infrastructure.util.logger.Logger;
import main.infrastructure.web.server.http.HttpEndpoint;

import java.io.BufferedReader;
import java.io.IOException;


public class RequestCreator {

    private final static Logger LOGGER = new Logger(RequestCreator.class.getName());

    // TODO fixme
    public Request create(BufferedReader in) throws IOException {
        Request request = new Request();
        String line;
        Integer lengthOfAdditionalContent = 0;
        LOGGER.info("REQUEST:\n");
        while ((line = in.readLine()) != null && (line.length() != 0)){
            LOGGER.info(line);
            if (line.contains("HTTP")){
                HttpEndpoint endpoint = handleEndpoint(line);
                request.setEndpoint(endpoint);
            }
            if(line.contains("length")){
                String[] headerAnd = line.split(": ");
                lengthOfAdditionalContent = new Integer(headerAnd[1]);
            }
        }
        if(lengthOfAdditionalContent>0) {
            String content = handleContent(in, lengthOfAdditionalContent);
            LOGGER.info(content);
            request.setRequestBody(new RequestBody(content));
        }
        return request;
    }

    private HttpEndpoint handleEndpoint(String line) {
        HttpEndpoint endpoint = new HttpEndpoint();
        String[] strings = line.split(" ");
        endpoint.setMethod(strings[0]);
        endpoint.setPath(strings[1]);
        return endpoint;
    }

    private String handleContent(BufferedReader in, Integer lengthOfAdditionalContent) throws IOException {
        char[] charArray = new char[lengthOfAdditionalContent];
        in.read(charArray, 0, lengthOfAdditionalContent);
        return new String(charArray);
    }
}
