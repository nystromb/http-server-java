package main.Handlers;

import main.HttpExchange;
import main.Request;
import main.Response;
import main.Response.Builder;

import java.io.IOException;

/**
 * Created by nystrom on 12/4/15.
 */
public class RedirectHandler implements HttpExchange {
    Builder response = new Builder(302);

    @Override
    public Response exchange(Request request) throws IOException {
        response.addHeader("Location", "http://localhost:5000/");
        return response.build();
    }
}
