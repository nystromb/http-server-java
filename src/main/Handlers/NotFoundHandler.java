package main.Handlers;

import main.HttpExchange;
import main.Builders.Request;
import main.Builders.Response;

import java.io.IOException;

/**
 * Created by nystrom on 12/4/15.
 */
public class NotFoundHandler implements HttpExchange {
    Response response;

    @Override
    public Response exchange(Request request) throws IOException {
        response = new Response.Builder(404, "Not Found").build();
        return response;
    }
}
