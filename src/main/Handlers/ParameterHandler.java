package main.Handlers;

import main.Handlers.Requestable;
import main.Request;
import main.Response;

import java.io.IOException;

/**
 * Created by nystrom on 12/4/15.
 */
public class ParameterHandler implements Requestable {
    Response response = new Response();

    @Override
    public Response getResponse(Request request) throws IOException {
        response.setStatus("200");
        response.setBody(request.getQuery().getBytes());
        return response;
    }
}
