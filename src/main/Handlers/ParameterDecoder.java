package main.Handlers;

import main.Request;

import java.io.IOException;

/**
 * Created by nystrom on 11/24/15.
 */
public class ParameterDecoder implements RequestHandler {
    @Override
    public byte[] handle(Request request) throws IOException {
        StringBuffer response = new StringBuffer();

        response.append(request.getVersion());
        response.append(" ");
        response.append("200 OK");
        response.append("\r\n");

        response.append("\r\n");
        response.append(request.getQuery());

        return response.toString().getBytes();
    }
}
