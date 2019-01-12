package main.infrastructure.web.server.request;


public class RequestBody {
    private String content;

    public RequestBody(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
