package main.Handlers;

import main.Request;

import java.io.IOException;

public interface RequestHandler {
    String handle(Request request) throws IOException;
}
