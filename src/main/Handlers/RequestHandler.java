package main.Handlers;

import main.Request;

import java.io.IOException;

public interface RequestHandler {
    byte[] handle(Request request) throws IOException;
}
