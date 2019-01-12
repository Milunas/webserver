package main.infrastructure.web.server.http;

public class HttpHeader {
    private String headerName;
    private String headerValue;

    public HttpHeader(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    public String toString(){
        return headerName + ": " + headerValue;
    }
}
