package main.Handlers;

import main.Builders.Request;
import main.Builders.Response;
import main.Builders.Response.Builder;

import java.io.IOException;

/**
 * Created by nystrom on 12/4/15.
 */
public class RedirectHandler implements HttpExchange {
    Builder response = new Builder(302);
    String redirectPath = "/";

    public RedirectHandler(String redirectPath) {
        this.redirectPath = redirectPath;
    }

    public RedirectHandler() {

    }

    @Override
    public Response exchange(Request request) throws IOException {
        response.addHeader("Location", "http://localhost:5000" + redirectPath);
        return response.build();
    }
}
