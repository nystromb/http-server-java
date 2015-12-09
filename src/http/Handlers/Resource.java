package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Builders.Response.Builder;

import java.io.IOException;

/**
 * Created by nystrom on 11/16/15.
 */
public class Resource implements Handler {
    static String data = "";
    Builder response = new Builder(200);

    public Response handle(Request request) throws IOException {
        switch (request.getMethod()) {
            case "GET":
                response.setBody(data);
                break;
            case "PUT":
            case "POST":
                data = request.getBody();
                break;
            case "DELETE":
                data = "";
                break;
            case "OPTIONS":
                response.addHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
                break;
        }

        return response.build();
    }
}