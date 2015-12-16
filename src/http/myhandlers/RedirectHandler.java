package http.myhandlers;

import http.handlers.ApplicationController;
import http.request.Request;
import http.response.Response;

import java.io.IOException;

public class RedirectHandler extends ApplicationController {
    Response.Builder response = new Response.Builder(302);
    String redirectPath = "/";

    public RedirectHandler(String redirectPath) {
        this.redirectPath = redirectPath;
    }

    @Override
    public Response handle(Request request) throws IOException {
        response.addHeader("Location", "http://localhost:5000" + redirectPath);
        return response.build();
    }
}
