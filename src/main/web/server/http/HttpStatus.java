package main.web.server.http;

public enum  HttpStatus {
    OK(200);
    private Integer value;
    HttpStatus(Integer value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " " + name();
    }
}
