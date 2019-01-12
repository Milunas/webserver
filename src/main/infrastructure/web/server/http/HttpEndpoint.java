package main.infrastructure.web.server.http;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpEndpoint {
    private String path;
    private String method;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpEndpoint endpoint = (HttpEndpoint) o;
        Pattern pattern = Pattern.compile(this.path);
        Matcher matcher = pattern.matcher(endpoint.path);
        return Objects.equals(method, endpoint.method)
                && matcher.matches();
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, method);
    }
}
