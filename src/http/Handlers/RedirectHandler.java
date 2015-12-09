package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;

import java.io.IOException;

/**
 * Created by nystrom on 12/4/15.
 */
public class RedirectHandler implements HttpExchange {
    Response.Builder response = new Response.Builder(302);
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
