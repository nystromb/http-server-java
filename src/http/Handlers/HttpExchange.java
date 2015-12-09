package http.Handlers;


import http.Builders.Request;
import http.Builders.Response;

import java.io.IOException;

public interface HttpExchange {
    Response exchange(Request request) throws IOException;
}
