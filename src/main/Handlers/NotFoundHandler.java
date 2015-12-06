package main.Handlers;

import main.Request;
import main.Response;

import java.io.IOException;

/**
 * Created by nystrom on 12/4/15.
 */
public class NotFoundHandler implements Requestable {
    Response response = new Response();

    @Override
    public byte[] getResponse(Request request) throws IOException {
        response.setStatus("404 Not Found");
        response.setBody("Not Found".getBytes());
        return response.toByteArray();
    }
}
