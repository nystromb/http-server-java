package http.handlers;

import http.request.Request;
import http.response.Response;

import java.io.IOException;

public interface RequestHandler {
    Response handle(Request request) throws IOException;
}
