package http.Handlers;


import http.Builders.Request;
import http.Builders.Response;

import java.io.IOException;

public interface Handler {
    Response handle(Request request) throws IOException;
}
