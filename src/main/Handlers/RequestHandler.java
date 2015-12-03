package main.Handlers;

import main.Request;
import main.Response;

import java.io.IOException;

public interface RequestHandler {
    Response handle(Request request) throws IOException;
}
