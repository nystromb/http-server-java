package test.mocks;

import http.handlers.ApplicationController;
import http.request.Request;
import http.response.Response;

import java.io.IOException;


public class MockController extends ApplicationController {

    @Override
    protected Response get(Request request) throws IOException {
        return new Response.Builder(200).build();
    }
}
