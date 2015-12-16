package http;

import http.builders.Request;
import http.builders.Response;

import java.io.IOException;

public class ParameterHandler extends ApplicationController {
    Response response;

    @Override
    public Response get(Request request) throws IOException {
        response = new Response.Builder(200, request.getQuery()).build();
        return response;
    }
}
