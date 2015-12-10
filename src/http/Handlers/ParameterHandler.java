package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Router.AbstractRouter;

import java.io.IOException;

/**
 * Created by nystrom on 12/4/15.
 */
public class ParameterHandler extends AbstractRouter {
    Response response;

    @Override
    public Response handle(Request request) throws IOException {
        response = new Response.Builder(200, request.getQuery()).build();
        return response;
    }
}
