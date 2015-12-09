package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;

import java.io.IOException;

/**
 * Created by nystrom on 12/4/15.
 */
public class ParameterHandler implements HttpExchange {
    Response response;

    @Override
    public Response exchange(Request request) throws IOException {
        response = new Response.Builder(200, request.getQuery()).build();
        return response;
    }
}
