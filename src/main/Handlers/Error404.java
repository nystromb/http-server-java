package main.Handlers;

import main.Request;

/**
 * Created by nystrom on 11/16/15.
 */
public class Error404 implements RequestHandler {

    @Override
    public byte[] handle(Request request) {

        return "HTTP/1.1 404 Not Found\r\n\r\n".getBytes();
    }
}
