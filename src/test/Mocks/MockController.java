package test.mocks;

import http.ApplicationController;
import http.builders.Request;
import http.builders.Response;

import java.io.IOException;


public class MockController extends ApplicationController {

    @Override
    protected Response get(Request request) throws IOException {
        return new Response.Builder(200).build();
    }
}
