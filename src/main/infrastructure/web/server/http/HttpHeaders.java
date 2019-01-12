package main.infrastructure.web.server.http;

import java.util.ArrayList;
import java.util.List;

public class HttpHeaders {
    private List<HttpHeader> headers = new ArrayList<>();

    public void add(HttpHeader httpHeader){
        headers.add(httpHeader);
    }

    public List<HttpHeader> get(){
        return headers;
    }

    public String toString(){
        StringBuilder toStringHeaders = new StringBuilder();
        headers.forEach(toStringHeaders::append);
        return toStringHeaders.toString();
    }

}
