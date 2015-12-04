package main.Handlers;

import main.Request;
import main.Response;

import java.io.IOException;

/**
 * Created by nystrom on 12/4/15.
 */
public class RedirectHandler implements Requestable {
    Response response = new Response();

    @Override
    public Response getResponse(Request request) throws IOException {
        response.setStatus("302 Redirect");
        response.addHeader("Location", "http://localhost:5000/");
        return response;
    }
}
