package test;

import main.infrastructure.web.server.Server;

import java.io.IOException;

public class StartContext {
    public static void main(String... args) throws IOException {
        new Server().start(80);
    }
}
