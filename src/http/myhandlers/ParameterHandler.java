package http.myhandlers;

import http.handlers.ApplicationController;
import http.request.Request;
import http.response.Response;

import java.io.IOException;

public class ParameterHandler extends ApplicationController {
    Response response;

    @Override
    public Response get(Request request) throws IOException {
        response = new Response.Builder(200, request.getQuery()).build();
        return response;
    }
}
