package main.web.server.request;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestCreator {

    // TODO fixme
    public Request create(BufferedReader in) throws IOException {
        Request request = new Request();
        String line;
        Integer lengthOfAdditionalContent = 0;
        System.out.println("REQUEST:\n");
        while ((line = in.readLine()) != null && (line.length() != 0)){
            System.out.println(line);
            if (line.contains("TTP")){
                String endpoint = handleEndpoint(line);
                request.setEndpoint(endpoint);
            }
            if(line.contains("length")){
                String[] headerAnd = line.split(": ");
                lengthOfAdditionalContent = new Integer(headerAnd[1]);
            }
        }

        if(lengthOfAdditionalContent>0) {
            String content = handleContent(in, lengthOfAdditionalContent);
            request.setRequestBody(new RequestBody(content));
        }
        return request;
    }

    private String handleEndpoint(String line) {
        String[] strings = line.split(" ");
        return strings[1];
    }

    private String handleContent(BufferedReader in, Integer lengthOfAdditionalContent) throws IOException {
        char[] charArray = new char[lengthOfAdditionalContent];
        in.read(charArray, 0, lengthOfAdditionalContent);
        return new String(charArray);
    }
}
