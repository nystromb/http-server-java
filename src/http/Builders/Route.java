package http.builders;

import http.RequestHandler;

public class Route {
    public RequestHandler handler;

    public Route(RequestHandler handler) {
        this.handler = handler;
    }
}
