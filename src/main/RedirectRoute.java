package main;

import java.io.IOException;

/**
 * Created by nystrom on 11/17/15.
 */
public class RedirectRoute implements RequestHandler {

    @Override
    public String handle(Request request) throws IOException {
        return "HTTP/1.1 302 Found\r\nLocation: http://localhost:5000/\r\n\r\n";
    }
}