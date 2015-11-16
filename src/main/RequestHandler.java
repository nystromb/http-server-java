package main;

import java.io.IOException;

public interface RequestHandler {
    String handle(Request request) throws IOException;
}
