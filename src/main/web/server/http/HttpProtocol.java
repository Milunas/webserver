package main.web.server.http;

public class HttpProtocol {
    private String name = "HTTP/1.1";

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
