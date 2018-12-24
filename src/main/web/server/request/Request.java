package main.web.server.request;

import main.web.server.http.HttpProtocol;

public class Request {

    private String endpoint;
    private HttpProtocol protocol;
    private RequestBody requestBody;

    public String getEndpoint() {
        return endpoint;
    }

    public HttpProtocol getProtocol() {
        return null;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setProtocol(HttpProtocol protocol) {
        this.protocol = protocol;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }
//#builder
}
