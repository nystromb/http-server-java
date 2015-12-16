package http;

import http.builders.Request;
import http.builders.Response;

public class Resource extends ApplicationController {
    private Response.Builder response = new Response.Builder();
    private String data = "";

    @Override
    protected Response get(Request request) {
        return response.status(200).setBody(data).build();
    }

    @Override
    protected Response post(Request request) {
        data = request.getBody();
        return response.status(200).build();
    }

    @Override
    protected Response put(Request request) {
        data = request.getBody();
        return response.status(200).build();
    }

    @Override
    protected Response delete(Request request) {
        data = "";
        return response.status(200).build();
    }

    @Override
    protected Response options(Request request) {
        return response.status(200).addHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT").build();
    }
}