package main.infrastructure.web.server.request;

import main.infrastructure.web.server.http.HttpEndpoint;
import main.infrastructure.web.server.http.HttpProtocol;

public class Request {

    private HttpEndpoint endpoint;
    private HttpProtocol protocol;
    private RequestBody requestBody;

    public HttpEndpoint getEndpoint() {
        return endpoint;
    }

    public HttpProtocol getProtocol() {
        return null;
    }

    public void setEndpoint(HttpEndpoint endpoint) {
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
