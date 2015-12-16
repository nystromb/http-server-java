package http;

import http.builders.Request;
import http.builders.Response;

import java.io.IOException;

public interface RequestHandler {
    Response handle(Request request) throws IOException;
}
