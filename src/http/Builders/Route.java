package http.Builders;

import http.Handlers.Router;

import java.io.IOException;

/**
 * Created by nystrom on 12/8/15.
 */
public class Route implements Router {
    public Router handler;

    public Route(Router handler) {
        this.handler = handler;
    }

    @Override
    public Response handle(Request request) throws IOException {
        return handler.handle(request);
    }
}
