package main.web.server.response;

import main.web.server.http.HttpHeaders;
import main.web.server.http.HttpProtocol;
import main.web.server.http.HttpStatus;

import static main.web.server.http.HttpStatus.OK;

public class Response {
    private HttpProtocol protocol = new HttpProtocol();
    private HttpStatus status = OK;
    private HttpHeaders headers;
    private Object body;

    public HttpProtocol getProtocol() {
        return protocol;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public Object getBody(){
        return body;
    }

    public String toString() {
        System.out.println(headers.toString());
        return new StringBuilder()
                .append(protocol.toString()).append(" ")
                .append(status).append("\r\n")
                .append(headers.toString()).append("\r\n")
                .append("\r\n")
                .append(body.toString()).append("\r\n")
                .toString();
    }

    public void setProtocol(HttpProtocol protocol) {
    }

    public void setStatus(HttpStatus ok) {
        this.status = ok;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

/*    public static class ResponseBuilder {
        private HttpProtocol protocol = new HttpProtocol();
        private HttpStatus status = OK;
        private HttpHeader[] headers;
        public ResponseBuilder(){}
        public Response build(){
            return new Response(this);
        }
    }*/
}
